package com.magicbox.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.magicbox.base.exception.ErrorCodes;
import com.magicbox.base.support.ResponseWrapper;
import com.magicbox.dto.ShopAssistantDTO;
import com.magicbox.service.api.SellerApiService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("卖家API")
@RestController
@RequestMapping("/seller")
public class SellerController extends BaseController {
	
	@Autowired
	private SellerApiService sellerApiService;
	
	@ApiOperation("查询店员列表")
	@PostMapping("/findShopAssistants")
	public ResponseWrapper<List<ShopAssistantDTO>> findShopAssistants(@RequestParam String token) {
		
		Long memberId = getMemberId(token);
		
		if (null == memberId) {
			return ResponseWrapper.fail(ErrorCodes.INVALID_TOKEN);
		}
		
		return sellerApiService.findShopAssistants(memberId);
	}
	
	@ApiOperation("审核店员")
	@PostMapping("/checkShopAssistant")
	public ResponseWrapper<?> checkShopAssistant(
			@RequestParam String token,
			@RequestParam Long shopAssistantId,
			@RequestParam Boolean isSuccess
			) {
		
		Long memberId = getMemberId(token);
		
		if (null == memberId) {
			return ResponseWrapper.fail(ErrorCodes.INVALID_TOKEN);
		}
		
		return sellerApiService.checkShopAssistant(memberId, shopAssistantId, isSuccess);
	}
	
	@ApiOperation("删除店员")
	@PostMapping("/deleteShopAssistant")
	public ResponseWrapper<?> deleteShopAssistant(
			@RequestParam String token,
			@RequestParam Long shopAssistantId
			) {
		
		Long memberId = getMemberId(token);
		
		if (null == memberId) {
			return ResponseWrapper.fail(ErrorCodes.INVALID_TOKEN);
		}
		
		return sellerApiService.deleteShopAssistant(memberId, shopAssistantId);
	}
}
