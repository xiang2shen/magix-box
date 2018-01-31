package com.magicbox.service.api;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.alipay.api.response.AlipayTradeWapPayResponse;
import com.alipay.constants.AlipayServiceEnvConstants;
import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayUtil;
import com.magicbox.base.constants.PaymentWayEnum;
import com.magicbox.base.support.ResponseWrapper;
import com.magicbox.base.utilities.BeanChecker;
import com.magicbox.base.utilities.JsonUtils;
import com.magicbox.model.Order;

@Service
public class PaymentApiService {
	
	private static final Logger logger = LoggerFactory.getLogger(PaymentApiService.class);
	
	@Autowired
	private OrderApiService orderApiService;
	
	private WeixinPayConfig weixinPayConfig = new WeixinPayConfig();

	public ResponseWrapper<String> pay(Order order, String clientIP) {
		BeanChecker.getInstance().notNull(order)
		.notBlank(order.getOrderCode()).positive(order.getRealTotal()).notBlank(order.getProductName()).notNull(order.getPayWay());
		
		if (PaymentWayEnum.ALI_PAY.equals(PaymentWayEnum.getEnumByCode(order.getPayWay()))) {	// 支付宝
			
			return payWithAlipay(order);
		} else {	// 微信支付
			
			return payWithWeixin(order, clientIP);
		}
		
	}
	
	private ResponseWrapper<String> payWithWeixin(Order order, String clientIP) {
		BeanChecker.getInstance().notBlank(clientIP);
		
        WXPay wxpay = new WXPay(weixinPayConfig);

        Map<String, String> data = new HashMap<String, String>();
        data.put("body", "盒趣自动售卖-" + order.getProductName());
        data.put("out_trade_no", order.getOrderCode());
//        data.put("device_info", "");
//        data.put("fee_type", "CNY");
        data.put("total_fee", order.getRealTotal().toString());
        data.put("spbill_create_ip", clientIP);
        data.put("notify_url", "https://www.51hequ.com/gateway/payment/weixinCallback");
        data.put("trade_type", "JSAPI");  // 此处指定为公众号支付
        data.put("openid", order.getMemberOpenId().substring(3));

        try {
            Map<String, String> weixinResp = wxpay.unifiedOrder(data);
            if (null != weixinResp && "SUCCESS".equals(weixinResp.get("result_code")) && "SUCCESS".equals(weixinResp.get("return_code"))) {
				
            	Map<String, String> resp = new HashMap<>();
            	resp.put("appId", weixinPayConfig.getAppID());
            	resp.put("timeStamp", String.valueOf(System.currentTimeMillis() / 1000));
            	resp.put("nonceStr", WXPayUtil.generateNonceStr());
            	resp.put("package", "prepay_id=" + weixinResp.get("prepay_id"));
            	resp.put("signType", "MD5");
            	resp.put("paySign", WXPayUtil.generateSignature(resp, weixinPayConfig.getKey()));
            	
            	resp.put("orderCode", order.getOrderCode());
            	
            	return ResponseWrapper.succeed(JsonUtils.format(resp));
			} else {
				logger.error("微信支付异常orderCode={}, resp={}", order.getOrderCode(), weixinResp);
			}
        } catch (Exception e) {
            logger.error("微信支付异常orderCode={}", order.getOrderCode(), e);
        }
        
        return ResponseWrapper.fail();
	}

	private ResponseWrapper<String> payWithAlipay(Order order) {
		AlipayClient alipayClient = new DefaultAlipayClient(AlipayServiceEnvConstants.ALIPAY_GATEWAY,
				AlipayServiceEnvConstants.APP_ID, AlipayServiceEnvConstants.PRIVATE_KEY, "json", "UTF-8",
				AlipayServiceEnvConstants.ALIPAY_PUBLIC_KEY, AlipayServiceEnvConstants.SIGN_TYPE);
		
//		AlipayTradeCreateRequest request = new AlipayTradeCreateRequest();
		AlipayTradeWapPayRequest request = new AlipayTradeWapPayRequest();
		request.setNotifyUrl("https://www.51hequ.com/gateway/payment/alipayCallback");
		request.setReturnUrl("https://www.51hequ.com/payok/" + order.getOrderCode());
		request.setBizContent("{" +
		"\"out_trade_no\":\"" + order.getOrderCode() + "\"," +
//		"\"seller_id\":\"2088102146225135\"," +
		"\"total_amount\":" + order.getRealTotal() / 100D + "," +
//		"\"discountable_amount\":8.88," +
		"\"subject\":\"" + "盒趣自动售卖" + "\"," +
		"\"body\":\"" + order.getProductName() + "\"" +
//		"\"buyer_id\":\"2088822716315443\"," +
//		"\"goods_detail\":[{" +
//		"\"goods_id\":\"apple-01\"," +
//		"\"goods_name\":\"ipad\"," +
//		"\"quantity\":1," +
//		"\"price\":2000," +
//		"\"goods_category\":\"34543238\"," +
//		"\"body\":\"特价手机\"," +
//		"\"show_url\":\"http://www.alipay.com/xxx.jpg\"" +
//		"}]," +
//		"\"operator_id\":\"Yx_001\"," +
//		"\"store_id\":\"NJ_001\"," +
//		"\"terminal_id\":\"NJ_T_001\"," +
//		"\"extend_params\":{" +
//		"\"sys_service_provider_id\":\"2088511833207846\"" +
//		"}," +
//		"\"timeout_express\":\"90m\"," +
//		"\"business_params\":\"{\\\"data\\\":\\\"123\\\"}\"" +
		"}");
//		AlipayTradeWapPayModel model = new AlipayTradeWapPayModel();
//		model.setOutTradeNo(order.getOrderCode());
//		model.setSubject("盒趣自动售卖");
//		model.setTotalAmount("0.01");
//		model.setBody("盒趣自动售卖");
//		model.setTimeoutExpress("100m");
//		model.setProductCode("2123");
//		request.setBizModel(model);
		try {
			
//			AlipayTradeCreateResponse response = alipayClient.execute(request);
			AlipayTradeWapPayResponse resp = alipayClient.pageExecute(request);
//			String form = resp.getBody(); //调用SDK生成表单
//			System.out.println(resp.getMsg());
//			System.out.println(resp.getSubMsg());
//			System.out.println(form);
			if (resp.isSuccess()) {
				
				Map<String, String> respMap = new HashMap<>();
				respMap.put("form", resp.getBody());
				respMap.put("orderCode", order.getOrderCode());
				
				return ResponseWrapper.succeed(JsonUtils.format(respMap));
			} else {
				logger.error("支付宝创建支付失败,code={},msg={}", resp.getSubCode(), resp.getSubMsg());
				return ResponseWrapper.fail();
			}
		} catch (Exception e) {
			logger.error("支付宝创建支付失败", e);
			return ResponseWrapper.fail();
		}
	}
	
	public static void main(String[] args) throws Exception {
//		Order order = new Order();
//		order.setOrderCode("1234556");
//		order.setRealTotal(1);
//		order.setProductName("测试商品");
//		order.setPayWay(1);
//		order.setMemberOpenId("wx_oNvx90g4qMepucrQePS6lQHVZ_yg");
//		
//		System.out.println(new PaymentApiService().pay(order, "61.135.169.121"));
		
		
		Map<String, String> map = new HashMap<>();
		map.put("gmt_create", "2018-01-15 16:09:02");
		map.put("charset", "UTF-8");
		map.put("seller_email", "247516185@qq.com");
		map.put("subject", "盒趣自动售卖");
		map.put("sign", "d+3m0grdtXYV3OseKG3zbclqrcuizX/E8thrvVuD6a5tj/cWWbOHJdHCLQLQe4JxbZOr85U4T05zG17omJrmSgTdGYwTIbu2ZIOVzugpAhNFq4qZXwplJXBK/2zvLH7rR3aECPxcC1tb11r9Rbc6jzLq4cA70pQrq6HiQxCcP/zc1pstuR5ZOd3IJgAYj4zz5zUNpKSr3Xkgpsj5MvO20v5daHyIBxSeFYHSaeUUQaxuhVEllxvyTlz19Z3pNoFIbYn1AOVucGuSOPuFCMqsQhIBxMgirherGeg4qCRwlAAg6taONVy2cPtcBXbn1doo6cNqsX6rTfqH/usii7/IWg==");
		map.put("body", "可乐");
		map.put("buyer_id", "2088812516727632");
		map.put("invoice_amount", "0.01");
		map.put("notify_id", "cca5b55b50e19216b4377484e449a22kv2");
		map.put("fund_bill_list", "[{\"amount\":\"0.0\",\"fundChannel\":\"ALIPAYACCOUNT\"}]");
		map.put("notify_type", "trade_status_sync");
		map.put("trade_status", "TRADE_SUCCESS");
		map.put("receipt_amount", "0.01");
		map.put("buyer_pay_amount", "0.01");
		map.put("app_id", "2017122701264862");
		map.put("sign_type", "RSA2");
		map.put("seller_id", "2088821678579759");
		map.put("gmt_payment", "2088821678579759");
		map.put("notify_time", "2018-01-15 16:09:03");
		map.put("version", "1.0");
		map.put("out_trade_no", "ORD20180115160857");
		map.put("total_amount", "0.01");
		map.put("trade_no", "2018011521001004630215292919");
		map.put("auth_app_id", "2017122701264862");
		map.put("buyer_logon_id", "m13***@icloud.com");
		map.put("point_amount", "0.00");
		
		System.out.println(AlipaySignature.rsaCheckV1(map, AlipayServiceEnvConstants.PUBLIC_KEY, "UTF-8", "RSA2"));
	}
	
	public ResponseWrapper<?> alipayCallback(Map<String, String> params) {
		try {
			String out_trade_no = new String(params.get("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
			//支付宝交易号
			
			String trade_no = new String(params.get("trade_no").getBytes("ISO-8859-1"),"UTF-8");
			
			//交易状态
			String trade_status = new String(params.get("trade_status").getBytes("ISO-8859-1"),"UTF-8");
			
			//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
			//计算得出通知验证结果
			//boolean AlipaySignature.rsaCheckV1(Map<String, String> params, String publicKey, String charset, String sign_type)
			boolean verify_result = AlipaySignature.rsaCheckV1(params, AlipayServiceEnvConstants.ALIPAY_PUBLIC_KEY, "UTF-8", "RSA2");
			
			if (verify_result) {// 验证成功
				//////////////////////////////////////////////////////////////////////////////////////////
				// 请在这里加上商户的业务逻辑程序代码

				// ——请根据您的业务逻辑来编写程序（以下代码仅作参考）——

				if (trade_status.equals("TRADE_FINISHED") || trade_status.equals("TRADE_SUCCESS")) {
					
					orderApiService.doAfterPay(out_trade_no, trade_no);
					return ResponseWrapper.succeed(out_trade_no);
				} else {

					logger.error("支付单[{}]支付宝回调通知失败, 交易状态[{}]错误", out_trade_no, trade_status);
				}

			} else {// 验证失败

				logger.error("支付单[{}]支付宝回调通知签名失败", out_trade_no);
			}
		} catch (Exception e) {
			
			logger.error("支付单[{}]支付宝回调通知失败", e);
		}
		
		return ResponseWrapper.fail();
	}

	public ResponseWrapper<?> weixinCallback(Map<String, String> params) {
		WXPay wxpay = new WXPay(weixinPayConfig);

		try {
			if (wxpay.isPayResultNotifySignatureValid(params)) {
				
				if ("SUCCESS".equals(params.get("return_code")) && "SUCCESS".equals(params.get("result_code"))) {
					
					String out_trade_no = params.get("out_trade_no");
					String transaction_id = params.get("transaction_id");
					logger.info("支付单微信回调通知成功，订单号={}", out_trade_no);
					
					orderApiService.doAfterPay(out_trade_no, transaction_id);
					
					return ResponseWrapper.succeed(out_trade_no);
				} else {
					logger.error("支付单微信回调通知失败，返回码错误, param={}", params);
				}
			} else {
				
				logger.error("支付单微信回调通知失败，签名错误, param={}", params);
			}
		} catch (Exception e) {
			logger.error("支付单微信回调通知失败, param={}", params, e);
		}

		return ResponseWrapper.fail();
	}
}
