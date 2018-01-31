package com.magicbox.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.wxpay.sdk.WXPayUtil;
import com.magicbox.base.support.ResponseWrapper;
import com.magicbox.base.utilities.IPUtils;
import com.magicbox.model.Order;
import com.magicbox.service.api.PaymentApiService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("支付API")
@RestController
@RequestMapping("/payment")
public class PaymentController extends BaseController {
	
	private static final Logger logger = LoggerFactory.getLogger(PaymentController.class);
	
	@Autowired
	private PaymentApiService paymentApiService;
	
	@ApiOperation("支付宝支付成功回调")
	@PostMapping("/alipayCallback")
	public String alipayCallback(HttpServletRequest request) {
		Map<String, String> params = new HashMap<String, String>();
		Map<String, String[]> requestParams = request.getParameterMap();
		for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
			}
			// 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
			// valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
			params.put(name, valueStr);
		}
		
		logger.info("支付宝支付回调,param={}", params);
		
		ResponseWrapper<?> resp = paymentApiService.alipayCallback(params);
		
		return resp.isSuccessful() ? "success" : "fail";
	}
	
	@ApiOperation("微信支付成功回调")
	@PostMapping("/weixinCallback")
	public String weixinCallback(HttpServletRequest request) throws Exception {
		
		String xml = IOUtils.toString(request.getInputStream(), "UTF-8");
		Map<String, String> dataMap = WXPayUtil.xmlToMap(xml); 
		
		logger.info("微信支付回调,param={}", dataMap);
		
		ResponseWrapper<?> resp = paymentApiService.weixinCallback(dataMap);
		
		return resp.isSuccessful() ? 
				"<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>" 
				: "<xml><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA[FAIL]]></return_msg></xml>";
	}
	
	@ApiOperation("test")
	@PostMapping("/test")
	public ResponseWrapper<String> test(HttpServletRequest request) {
		Order order = new Order();
		order.setOrderCode("1234556");
		order.setRealTotal(1);
		order.setProductName("测试商品");
		order.setPayWay(1);
		
		return paymentApiService.pay(order, IPUtils.getIp(request));
	}
	
	
}
