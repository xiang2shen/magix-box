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
import com.magicbox.base.support.Page;
import com.magicbox.base.support.ResponseWrapper;
import com.magicbox.base.utilities.CsvUtils;
import com.magicbox.dto.ShopDTO;
import com.magicbox.model.Shop;
import com.magicbox.model.ShopTag;
import com.magicbox.service.api.ShopApiService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("店铺API")
@RestController
@RequestMapping("/shop")
public class ShopController extends BaseController {

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(ShopController.class);
	
	@Autowired
	private ShopApiService shopApiService;
	
	@ApiOperation("卖家分页查询店铺列表")
	@PostMapping("/findShopPageByMemberId")
	public ResponseWrapper<Page<Shop>> findShopPageByMemberId(
			@RequestParam String token,
			@RequestParam Integer pageNo,
			@RequestParam Integer pageSize
			) {
		
		Long memberId = getMemberId(token);
		
		if (null == memberId) {
			return ResponseWrapper.fail(ErrorCodes.INVALID_TOKEN);
		}
		
		return shopApiService.findShopPageByMemberId(memberId, pageNo, pageSize);
	}
	
	@ApiOperation("创建店铺")
	@PostMapping("/createShop")
	public ResponseWrapper<Shop> createShop(
			@RequestParam String token, 
			@RequestParam(required = false) String tagIdCsv, 
			Shop shop) {
		
		Long memberId = getMemberId(token);
		
		if (null == memberId) {
			return ResponseWrapper.fail(ErrorCodes.INVALID_TOKEN);
		}
		
		return shopApiService.createShop(memberId, shop, CsvUtils.parseLong(tagIdCsv));
	}
	
	@ApiOperation("更新店铺")
	@PostMapping("/updateShop")
	public ResponseWrapper<Shop> updateShop(
			@RequestParam String token, 
			@RequestParam(required = false) String tagIdCsv, 
			Shop shop) {
		
		Long memberId = getMemberId(token);
		
		if (null == memberId) {
			return ResponseWrapper.fail(ErrorCodes.INVALID_TOKEN);
		}
		
		return shopApiService.updateShop(memberId, shop, CsvUtils.parseLong(tagIdCsv));
	}
	
	@ApiOperation("根据店铺编号查询店铺")
	@PostMapping("/findShopByShopCode")
	public ResponseWrapper<ShopDTO> findShopByShopCode(@RequestParam String token, @RequestParam String shopCode) {
		
		Long memberId = getMemberId(token);
		
		if (null == memberId) {
			return ResponseWrapper.fail(ErrorCodes.INVALID_TOKEN);
		}
		
		return shopApiService.findShopByShopCode(memberId, shopCode);
	}
	
	@ApiOperation("买家分页查询店铺列表")
	@PostMapping("/findShopPageForBuyer")
	public ResponseWrapper<Page<ShopDTO>> findShopPageForBuyer(
			@RequestParam(required = false) String shopName,
			@RequestParam Integer pageNo,
			@RequestParam Integer pageSize
			) {
		
		return shopApiService.findShopPageForBuyer(shopName, pageNo, pageSize);
	}
	
	@ApiOperation("查询店铺类别列表")
	@PostMapping("/findShopCategoryList")
	public ResponseWrapper<List<String>> findShopCategoryList() {
		
		return shopApiService.findShopCategoryList();
	}
	
	@ApiOperation("查询店铺特性列表")
	@PostMapping("/findShopPropertyList")
	public ResponseWrapper<List<String>> findShopPropertyList() {
		
		return shopApiService.findShopPropertyList();
	}
	
	@ApiOperation("搜索店铺和商品")
	@PostMapping("/searchShopAndProduct")
	public ResponseWrapper<Page<ShopDTO>> searchShopAndProduct(
			@RequestParam(required = false) String text,
			@RequestParam(required = false) Long shopTagId,
			@RequestParam Double lon,
			@RequestParam Double lat,
			@RequestParam Integer pageNo,
			@RequestParam Integer pageSize
			) {
		
		return shopApiService.searchShopAndProduct(text, shopTagId, lon, lat, pageNo, pageSize);
	}
	
	@ApiOperation("查询店铺标签")
	@PostMapping("/findShopTagList")
	public ResponseWrapper<List<ShopTag>> findShopTagList() {
		
		return shopApiService.findShopTagList();
	}
}
