package com.magicbox.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.magicbox.base.exception.ErrorCodes;
import com.magicbox.base.support.ResponseWrapper;
import com.magicbox.model.Box;
import com.magicbox.service.api.BoxApiService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("盒子API")
@RestController
@RequestMapping("/box")
public class BoxController extends BaseController {

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(BoxController.class);
	
	@Autowired
	private BoxApiService boxApiService;
	
	@ApiOperation("绑定盒子与商品")
	@PostMapping("/bindBoxWithProduct")
	public ResponseWrapper<Box> bindBoxWithProduct(
			@RequestParam String token,
			@RequestParam String productCode,
			@RequestParam String boxCode
			) {
		
		Long memberId = getMemberId(token);
		
		if (null == memberId) {
			return ResponseWrapper.fail(ErrorCodes.INVALID_TOKEN);
		}
		
		return boxApiService.bindBoxWithProduct(memberId, productCode, boxCode);
	}
	
	@ApiOperation("更新盒子库存")
	@PostMapping("/updateStock")
	public ResponseWrapper<?> updateStock(
			@RequestParam String token,
			@RequestParam String boxCode,
			@RequestParam Integer stock
			) {
		
		Long memberId = getMemberId(token);
		
		if (null == memberId) {
			return ResponseWrapper.fail(ErrorCodes.INVALID_TOKEN);
		}
		
		return boxApiService.updateStock(memberId, boxCode, stock);
	}
	
	@ApiOperation("重置盒子")
	@PostMapping("/resetBox")
	public ResponseWrapper<?> resetBox(
			@RequestParam String token,
			@RequestParam String boxCode
			) {
		
		Long memberId = getMemberId(token);
		
		if (null == memberId) {
			return ResponseWrapper.fail(ErrorCodes.INVALID_TOKEN);
		}
		
		return boxApiService.resetBox(memberId, boxCode);
	}
	
	@ApiOperation("开锁盒子")
	@PostMapping("/openBox")
	public ResponseWrapper<?> openBox(
			@RequestParam String token,
			@RequestParam String boxCode
			) {
		
		Long memberId = getMemberId(token);
		
		if (null == memberId) {
			return ResponseWrapper.fail(ErrorCodes.INVALID_TOKEN);
		}
		
		return boxApiService.openBox(memberId, boxCode);
	}
	
	@ApiOperation("创建盒子（测试专用）")
	@PostMapping("/createBox.sec")
	public ResponseWrapper<Box> createBox(
			@RequestParam String token,
			Box box
			) {
		
		Long memberId = getMemberId(token);
		
		if (null == memberId) {
			return ResponseWrapper.fail(ErrorCodes.INVALID_TOKEN);
		}
		
		return boxApiService.createBox(memberId, box);
	}
}
