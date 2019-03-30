package com.magicbox.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.magicbox.base.exception.ErrorCodes;
import com.magicbox.base.support.ResponseWrapper;
import com.magicbox.dto.ShopAssistantDTO;
import com.magicbox.service.ShopAssistantService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("店员API")
@RestController
@RequestMapping("/shopAssistant")
public class ShopAssistantController extends BaseController {
	
	@Autowired
	private ShopAssistantService shopAssistantService;
	
	@ApiOperation("根据ID查询店员")
	@PostMapping("/findShopAssistant")
	public ResponseWrapper<ShopAssistantDTO> findShopAssistant(
			@RequestParam String token
			) {
		
		Long memberId = getMemberId(token);
		
		if (null == memberId) {
			return ResponseWrapper.fail(ErrorCodes.INVALID_TOKEN);
		}
		
		return shopAssistantService.findShopAssistants(memberId);
	}
	
}
