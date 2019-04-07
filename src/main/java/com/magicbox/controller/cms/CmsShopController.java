package com.magicbox.controller.cms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.magicbox.base.exception.ErrorCodes;
import com.magicbox.base.support.Page;
import com.magicbox.base.support.ResponseWrapper;
import com.magicbox.controller.BaseController;
import com.magicbox.model.Shop;
import com.magicbox.service.api.ShopApiService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("CMS店铺API")
@RestController
@RequestMapping("/cms/shop")
public class CmsShopController extends BaseController {
	
	@Autowired
	private ShopApiService shopApiService;
	
	@ApiOperation("查询店铺列表")
	@PostMapping("/list")
	public ResponseWrapper<Page<Shop>> listShops(
			@RequestParam String token,
			@RequestParam(required = false) String shopName,
			@RequestParam Integer pageNo,
			@RequestParam Integer pageSize
			) {
		
		Long userId = getUserId(token);
		if (null == userId) {
			return ResponseWrapper.fail(ErrorCodes.INVALID_TOKEN);
		}
		
		return shopApiService.findShopPageConditionally(shopName, pageNo, pageSize);
	}
}
