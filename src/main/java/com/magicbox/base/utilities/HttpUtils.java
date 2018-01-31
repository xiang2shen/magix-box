package com.magicbox.base.utilities;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.net.ssl.SSLContext;

import org.apache.commons.io.IOUtils;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

/**
 * http与https客户端工具类
 * 
 * @author xiangshuo
 *
 */
public final class HttpUtils {
	
	private static final Logger logger = LoggerFactory.getLogger(HttpUtils.class);
	
	public static final String GET = "get";
	public static final String POST = "post";
	
	private static final int DEFAULT_TIMEOUT = 10000;

	/**
	 * 发送get请求
	 * 
	 * @param url 请求地址
	 * @param params 请求参数
	 * @param ssl 是否https协议
	 * @return
	 */
	public static String get(String url, Map<String, Object> params, boolean ssl) {
		return request(url, params, GET, ssl, DEFAULT_TIMEOUT, false);
	}
	
	/**
	 * 发送http协议的get请求
	 * 
	 * @param url 请求地址
	 * @param params 请求参数
	 * @return
	 */
	public static String get(String url, Map<String, Object> params) {
		return get(url, params, false);
	}
	
	/**
	 * 发送不带参数的http协议的get请求
	 * 
	 * @param url 请求地址
	 * @return
	 */
	public static String get(String url) {
		return get(url, null);
	}
	
	/**
	 * 发送post请求
	 * 
	 * @param url 请求地址
	 * @param params 请求参数
	 * @param ssl 是否https协议
	 * @return
	 */
	public static String post(String url, Map<String, Object> params, boolean ssl, boolean requestBody) {
		return request(url, params, POST, ssl, DEFAULT_TIMEOUT, requestBody);
	}
	
	/**
	 * 发送post请求
	 * 
	 * @param url 请求地址
	 * @param params 请求参数
	 * @param ssl 是否https协议
	 * @return
	 */
	public static String post(String url, Map<String, Object> params, boolean ssl) {
		return post(url, params, ssl, false);
	}
	
	/**
	 * 发送http协议的post请求
	 * 
	 * @param url 请求地址
	 * @param params 请求参数
	 * @return
	 */
	public static String post(String url, Map<String, Object> params) {
		return post(url, params, false);
	}
	
	/**
	 * 发送不带参数的http协议的post请求
	 * 
	 * @param url 请求地址
	 * @return
	 */
	public static String post(String url) {
		return post(url, null);
	}
	
	/**
	 * 发送http请求
	 * 
	 * @param url 请求地址
	 * @param params 请求参数
	 * @param method 请求方法："get"或"post"
	 * @param ssl 是否https协议
	 * @param timeout 请求超时时间(毫秒)
	 * @param requestBody 是否请求体传参
	 * @return
	 */
	public static String request(String url, Map<String, Object> params, String method, boolean ssl, int timeout, boolean requestBody) {
		logger.info("start to get http request,url={},params={},method={},ssl={}", url, params, method, ssl);
		
		try {
			CloseableHttpResponse response = null;
			
			try {
				CloseableHttpClient httpclient = getHttpClient(ssl);
				
				if (GET.equals(method)) {	// get请求需要拼接url
					url = getUrl(params, url);
				}
				
				HttpRequestBase httpReq = null;
				if (GET.equals(method)) {
					httpReq = new HttpGet(url);
				} else if (POST.equals(method)) {
					httpReq = getHttpPost(url, params, requestBody);	// post请求需要添加参数
				} else {
					throw new RuntimeException("unsupported method: " + method);
				}
				
				configRequest(timeout, httpReq);	// 配置请求
				
				long startTime = System.currentTimeMillis();
				
				response = httpclient.execute(httpReq);
				
				if (HttpStatus.SC_OK != response.getStatusLine().getStatusCode()) {	// 响应码错误
					logger.error("fail to send http request,url={}, responseCode={}", url, response.getStatusLine().getStatusCode());
				}
				
				String respStr = getResponseContent(response);
				
				long endTime = System.currentTimeMillis();
				logger.info("got http response,response={}, cost {} ms", respStr, endTime - startTime);
				return respStr;
			} finally {
				if (null != response) {
					response.close();
				}
			}
		} catch (Exception e) {
			logger.error("fail to send http request,url={}", url, e);
			throw new RuntimeException(e);
		}
	}

	private static void configRequest(int timeout, HttpRequestBase httpReq) {
		RequestConfig requestConfig = RequestConfig.custom()
		        .setSocketTimeout(timeout)
		        .setConnectTimeout(timeout)
		        .setConnectionRequestTimeout(timeout)
		        .build();
		httpReq.setConfig(requestConfig);
	}

	private static CloseableHttpClient getHttpClient(boolean ssl)
			throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException {
		if (ssl) {
			SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
				
				@Override
				public boolean isTrusted(X509Certificate[] chain,
						String authType) throws CertificateException {
					return true;	//信任所有
				}
				
			}).build();
			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext);
			return HttpClients.custom().setSSLSocketFactory(sslsf).build();
		} else {
			return HttpClients.createDefault();
		}
	}


	private static String getResponseContent(CloseableHttpResponse response) throws IOException {
		InputStream is = response.getEntity().getContent();
		byte[] buf = IOUtils.toByteArray(is);
		return new String(buf, Consts.UTF_8);
	}


	private static HttpPost getHttpPost(String url, Map<String, Object> params, boolean requestBody) {
		HttpPost httppost = new HttpPost(url);
		
		if (null == params) {
			params = Collections.emptyMap();
		}
		
		HttpEntity entity = null;
		if (requestBody) {
			entity = new StringEntity(JSON.toJSONString(params), ContentType.APPLICATION_JSON);
		} else {
			List<NameValuePair> formparams = new ArrayList<NameValuePair>();
			for (Entry<String, Object> entry : params.entrySet()) {
				if (null != entry && null != entry.getKey() && null != entry.getValue()) {
					formparams.add(new BasicNameValuePair(entry.getKey(), entry.getValue().toString()));
				}
			}
			entity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);
		}
		
		httppost.setEntity(entity);
		return httppost;
	}


	private static String getUrl(Map<String, Object> params, String url) throws URISyntaxException {
		URIBuilder uriBuilder = new URIBuilder(url);
		
		if (null != params && params.size() > 0) {
			for (Entry<String, Object> entry : params.entrySet()) {
				if (null != entry && null != entry.getKey() && null != entry.getValue()) {
					uriBuilder.setParameter(entry.getKey(), entry.getValue().toString());
				}
			}
		}
		
		return uriBuilder.build().toString();
	}
	
}
