package com.magicbox.controller;

import java.util.UUID;
import java.util.concurrent.ExecutorService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipaySystemOauthTokenRequest;
import com.alipay.api.request.AlipayUserInfoShareRequest;
import com.alipay.api.response.AlipaySystemOauthTokenResponse;
import com.alipay.api.response.AlipayUserInfoShareResponse;
import com.alipay.constants.AlipayServiceEnvConstants;
import com.magicbox.base.exception.ErrorCodes;
import com.magicbox.base.support.ResponseWrapper;
import com.magicbox.base.utilities.CaptchaUtils;
import com.magicbox.dto.SessionMember;
import com.magicbox.model.Member;
import com.magicbox.redis.RedisClient;
import com.magicbox.service.api.MemberApiService;
import com.magicbox.service.api.SmsApiService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("会员API")
@RestController
@RequestMapping("/member")
public class MemberController extends BaseController {
	
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	private RedisClient redisClient;
	@Autowired
	private ExecutorService executorService;
	@Autowired
	private MemberApiService memberApiService;
	@Autowired
	private SmsApiService smsApiService;
	
	private static final long SESSION_TTL = 24 * 60 * 60;	// session有效时间
	
	@ApiOperation("微信/支付宝openId加入")
	@PostMapping("/joinOpenId.sec")
	public ResponseWrapper<String> joinOpenId(@RequestParam String openId) {
		
		ResponseWrapper<Member> resp = memberApiService.joinOpenId(openId);
		
		String token = genToken(resp.getBody());
		
		return ResponseWrapper.succeed(token);
	}
	
	@ApiOperation("发送短信验证码")
	@PostMapping("/sendJoinCaptcha")
	public ResponseWrapper<?> sendJoinCaptcha(@RequestParam String mobile) {
		
		String smsCaptcha = CaptchaUtils.generateVerifyCode(4);
		redisClient.put(getSMSCaptchaKey(mobile), smsCaptcha);
		
		executorService.execute(new Runnable() {
			
			@Override
			public void run() {
				String msg = String.format("您的验证码是%s。如非本人操作，请忽略本短信。", smsCaptcha);
				logger.info("发送短信验证码:{}", msg);
				smsApiService.sendMessage(mobile, msg);
			}
		});
		
		return ResponseWrapper.succeed();
	}
	
	private String getSMSCaptchaKey(String mobile) {
		return "smsCaptcha:" + mobile;
	}
	
	@ApiOperation("卖家手机注册/登录")
	@PostMapping("/joinSellerWithMobile.sec")
	public ResponseWrapper<String> joinSellerWithMobile(
			@RequestParam String mobile, 
			@RequestParam String openId,
			@RequestParam String smsCaptcha
			) {
		
		String realCaptcha = redisClient.get(getSMSCaptchaKey(mobile));
		if (!smsCaptcha.equalsIgnoreCase(realCaptcha)) {
			
			return ResponseWrapper.fail(ErrorCodes.CAPTCHA_ERROR);
		}
		
		ResponseWrapper<Member> resp = memberApiService.joinSellerWithMobile(mobile, openId);
		if (resp.isFailed()) {
			return ResponseWrapper.fail(resp.getHead());
		}
		
		String token = genToken(resp.getBody());
		
		return ResponseWrapper.succeed(token);
	}
	
	@ApiOperation("是否是卖家")
	@PostMapping("/isSeller")
	public ResponseWrapper<Boolean> isSeller(@RequestParam String token) {
		Long memberId = getMemberId(token);
		
		if (null == memberId) {
			return ResponseWrapper.fail(ErrorCodes.INVALID_TOKEN);
		}
		
		ResponseWrapper<Boolean> resp = memberApiService.isSeller(memberId);
		
		return resp;
	}
	
//	@ApiOperation("手机登录")
//	@PostMapping("/loginMobile")
//	public ResponseWrapper<String> loginMobile(
//			@RequestParam String mobile, 
//			@RequestParam String password) {
//		
//		ResponseWrapper<Member> resp = memberApiService.loginMobile(mobile, password);
//		if (resp.isFailed()) {
//			return ResponseWrapper.fail(resp.getHead());
//		}
//		
//		String token = genToken(resp.getBody());
//		
//		return ResponseWrapper.succeed(token);
//	}
	
	private String genToken(Member member) {
		String token = UUID.randomUUID().toString();
		
		redisClient.put(getSessionCacheKey(token), new SessionMember(member.getId()), SESSION_TTL);
		
		return token;
	}
	
//	@ApiOperation("修改密码")
//	@PostMapping("/updatePassword")
//	public ResponseWrapper<?> updatePassword(
//			@RequestParam String mobile,
//			@RequestParam String smsCaptcha,
//			@RequestParam String password) {
//		
//		String realCaptcha = redisClient.get(getSMSCaptchaKey(mobile));
//		if (!smsCaptcha.equalsIgnoreCase(realCaptcha)) {
//			
//			return ResponseWrapper.fail(ErrorCodes.CAPTCHA_ERROR);
//		}
//		
//		ResponseWrapper<Member> resp = memberApiService.updatePassword(mobile, password);
//		if (resp.isFailed()) {
//			return ResponseWrapper.fail(resp.getHead());
//		}
//		
//		return ResponseWrapper.succeed();
//	}

//	@ApiOperation("绑定支付宝")
//	@PostMapping("/bindAlipayId.sec")
//	public ResponseWrapper<?> bindAlipayId(
//			@RequestParam String token,
//			@RequestParam String alipayId) {
//		
//		Long memberId = getMemberId(token);
//		
//		if (null == memberId) {
//			return ResponseWrapper.fail(MemberError.INVALID_TOKEN);
//		}
//		
//		return memberApiService.bindAlipayId(memberId, alipayId);
//	}
//	
//	@ApiOperation("绑定微信")
//	@PostMapping("/bindWeixinId.sec")
//	public ResponseWrapper<?> bindWeixinId(
//			@RequestParam String token,
//			@RequestParam String weixinId) {
//		
//		Long memberId = getMemberId(token);
//		
//		if (null == memberId) {
//			return ResponseWrapper.fail(MemberError.INVALID_TOKEN);
//		}
//		
//		return memberApiService.bindWeixinId(memberId, weixinId);
//	}
	
//	@ApiOperation("alipayId登录")
//	@PostMapping("/loginWithAlipayId.sec")
//	public ResponseWrapper<String> loginWithAlipayId(@RequestParam String alipayId) {
//		ResponseWrapper<Member> resp = memberApiService.loginWithAlipayId(alipayId);
//		
//		if (resp.isFailed()) {
//			return ResponseWrapper.fail(resp.getHead());
//		}
//		
//		String token = genToken(resp.getBody());
//		
//		return ResponseWrapper.succeed(token);
//	}
//	
//	@ApiOperation("weixinId登录")
//	@PostMapping("/loginWithWeixinId.sec")
//	public ResponseWrapper<String> loginWithWeixinId(@RequestParam String weixinId) {
//		ResponseWrapper<Member> resp = memberApiService.loginWithWeixinId(weixinId);
//		
//		if (resp.isFailed()) {
//			return ResponseWrapper.fail(resp.getHead());
//		}
//		
//		String token = genToken(resp.getBody());
//		
//		return ResponseWrapper.succeed(token);
//	}
	
	@ApiOperation("获取支付宝会员信息")
	@PostMapping("/getUserDataByAlipayAuthCode.sec")
	public ResponseWrapper<String> getUserDataByAlipayAuthCode(@RequestParam String authCode)
			throws AlipayApiException {
		AlipayClient alipayClient = new DefaultAlipayClient(AlipayServiceEnvConstants.ALIPAY_GATEWAY,
				AlipayServiceEnvConstants.APP_ID, AlipayServiceEnvConstants.PRIVATE_KEY, "json", "UTF-8",
				AlipayServiceEnvConstants.ALIPAY_PUBLIC_KEY, AlipayServiceEnvConstants.SIGN_TYPE);
		
		AlipaySystemOauthTokenRequest authCodeRequest = new AlipaySystemOauthTokenRequest();
		authCodeRequest.setCode(authCode);
		authCodeRequest.setGrantType("authorization_code");
		AlipaySystemOauthTokenResponse authTokenResponse = alipayClient.execute(authCodeRequest);
		
//		AlipayUserInfoShareResponse accessTokenResponse = alipayClient.execute(new AlipayUserInfoShareRequest(), authTokenResponse.getAccessToken());
		if (authTokenResponse.isSuccess()) {
			
			return ResponseWrapper.succeed(authTokenResponse.getUserId());
		}
		
		logger.error("获取支付宝会员信息失败，msg={}, code{}", authTokenResponse.getMsg() + authTokenResponse.getSubMsg(), authTokenResponse.getCode() + authTokenResponse.getSubCode());
		return ResponseWrapper.fail();
	}
	
	public static void main(String[] args) throws Exception {
		AlipayClient alipayClient = new DefaultAlipayClient(AlipayServiceEnvConstants.ALIPAY_GATEWAY,
				AlipayServiceEnvConstants.APP_ID, AlipayServiceEnvConstants.PRIVATE_KEY, "json", "UTF-8",
				AlipayServiceEnvConstants.ALIPAY_PUBLIC_KEY, AlipayServiceEnvConstants.SIGN_TYPE);
		
		AlipaySystemOauthTokenRequest authCodeRequest = new AlipaySystemOauthTokenRequest();
		authCodeRequest.setCode("45914798ace34806bfe1b2e1da64NX95");
		authCodeRequest.setGrantType("authorization_code");
		AlipaySystemOauthTokenResponse authTokenResponse = alipayClient.execute(authCodeRequest);
		System.out.println(authTokenResponse.getAccessToken());
		System.out.println(authTokenResponse.getUserId());
		
		AlipayClient alipayClient2 = new DefaultAlipayClient(AlipayServiceEnvConstants.ALIPAY_GATEWAY,
				AlipayServiceEnvConstants.APP_ID, AlipayServiceEnvConstants.PRIVATE_KEY, "json", "UTF-8",
				AlipayServiceEnvConstants.ALIPAY_PUBLIC_KEY, AlipayServiceEnvConstants.SIGN_TYPE);
		
		AlipayUserInfoShareResponse accessTokenResponse = alipayClient2.execute(new AlipayUserInfoShareRequest(), "authbseBcd97b88b058c42a0b4fbddd00d033E95");
		if (!accessTokenResponse.isSuccess()) {
			
			logger.error("获取支付宝会员信息失败，msg={}, code{}", accessTokenResponse.getMsg() + accessTokenResponse.getSubMsg(), accessTokenResponse.getCode() + accessTokenResponse.getSubCode());
		}
	}
	
//	@ApiOperation("同步用户微信数据")
//	@PostMapping("/syncWxUserData.sec")
//	public AlipayUserInfoShareResponse syncWxUserData(@RequestParam String accessToken)
//			throws AlipayApiException {
//		AlipayClient alipayClient = new DefaultAlipayClient(AlipayServiceEnvConstants.ALIPAY_GATEWAY,
//				AlipayServiceEnvConstants.APP_ID, AlipayServiceEnvConstants.PRIVATE_KEY, "json", "UTF-8",
//				AlipayServiceEnvConstants.ALIPAY_PUBLIC_KEY, AlipayServiceEnvConstants.SIGN_TYPE);
//		
//		AlipayUserInfoShareRequest request = new AlipayUserInfoShareRequest();
//		AlipayUserInfoShareResponse response = alipayClient.execute(request, accessToken);
//		
//		return response;
//	}
	
}
