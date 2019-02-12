package com.magicbox.service.api;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.magicbox.base.support.ResponseWrapper;
import com.yunpian.sdk.YunpianClient;
import com.yunpian.sdk.model.Result;
import com.yunpian.sdk.model.SmsSingleSend;

@Service
public class SmsApiService {
	
	private static final Logger logger = LoggerFactory.getLogger(SmsApiService.class);
	
	private static final String YUNPIAN_API_KEY = "0e762648b32ee85b0e1a5f1d65c5caf5";

	public ResponseWrapper<?> sendMessage(String mobile, String content) {
		
		YunpianClient clnt = new YunpianClient(YUNPIAN_API_KEY).init();

		//发送短信API
		Map<String, String> param = clnt.newParam(2);
		param.put(YunpianClient.MOBILE, mobile);
		param.put(YunpianClient.TEXT, content);
		Result<SmsSingleSend> r = clnt.sms().single_send(param);
		//获取返回结果，返回码:r.getCode(),返回码描述:r.getMsg(),API结果:r.getData(),其他说明:r.getDetail(),调用异常:r.getThrowable()

		//账户:clnt.user().* 签名:clnt.sign().* 模版:clnt.tpl().* 短信:clnt.sms().* 语音:clnt.voice().* 流量:clnt.flow().* 隐私通话:clnt.call().*

		//释放clnt
		clnt.close();
		if (!r.isSucc()) {
			logger.error("发送短信失败, code={}, msg={}", r.getCode(), r.getMsg());
			return ResponseWrapper.fail();
		}
		
		return ResponseWrapper.succeed();
	}

	public static void main(String[] args) {
		YunpianClient clnt = new YunpianClient(YUNPIAN_API_KEY).init();

		//发送短信API
		Map<String, String> param = clnt.newParam(2);
		param.put(YunpianClient.MOBILE, "17602165837");
		param.put(YunpianClient.TEXT, "您的验证码是1234。如非本人操作，请忽略本短信");
		Result<SmsSingleSend> r = clnt.sms().single_send(param);
		//获取返回结果，返回码:r.getCode(),返回码描述:r.getMsg(),API结果:r.getData(),其他说明:r.getDetail(),调用异常:r.getThrowable()

		//账户:clnt.user().* 签名:clnt.sign().* 模版:clnt.tpl().* 短信:clnt.sms().* 语音:clnt.voice().* 流量:clnt.flow().* 隐私通话:clnt.call().*

		//释放clnt
		clnt.close();
		logger.error("发送短信失败, code={}, msg={}", r.getCode(), r.getMsg());
		if (!r.isSucc()) {
			logger.error("发送短信失败, code={}, msg={}", r.getCode(), r.getMsg());
		}
	}
}