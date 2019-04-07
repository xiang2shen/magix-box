package com.magicbox.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.magicbox.base.exception.ErrorCodes;
import com.magicbox.base.support.ResponseWrapper;
import com.magicbox.model.Frame;
import com.magicbox.service.FrameService;
import com.magicbox.service.api.FrameApiService;
import com.magicbox.service.api.ShopApiService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("设备API")
@RestController
@RequestMapping("/frame")
public class FrameController extends BaseController {

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(FrameController.class);
	
	@Autowired
	private ShopApiService shopApiService;
	@Autowired
	private FrameApiService frameApiService;
	@Autowired
	private FrameService frameService;
	
	@ApiOperation("查询店铺下的设备列表")
	@PostMapping("/listFrameByShopCode")
	public ResponseWrapper<List<Frame>> listFrameByShopCode(
			@RequestParam String token,
			@RequestParam String shopCode
			) {
		
		Long memberId = getMemberId(token);
		
		if (null == memberId) {
			return ResponseWrapper.fail(ErrorCodes.INVALID_TOKEN);
		}
		
		List<Frame> frames = frameService.selectListByShopCode(shopCode);
		return ResponseWrapper.succeed(frames);
	}
	
	@ApiOperation("绑定店铺与设备")
	@PostMapping("/bindShopWithFrame")
	public ResponseWrapper<?> bindShopWithFrame(
			@RequestParam String token,
			@RequestParam String shopCode,
			@RequestParam String frameCode
			) {
		
		Long memberId = getMemberId(token);
		
		if (null == memberId) {
			return ResponseWrapper.fail(ErrorCodes.INVALID_TOKEN);
		}
		
		return frameApiService.bindShopWithFrame(memberId, shopCode, frameCode);
	}
}
