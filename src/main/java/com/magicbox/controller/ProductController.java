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
import com.magicbox.dto.ProductDTO;
import com.magicbox.dto.ProductInBoxDTO;
import com.magicbox.model.Box;
import com.magicbox.model.Product;
import com.magicbox.model.ProductTag;
import com.magicbox.service.api.ProductApiService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("商品API")
@RestController
@RequestMapping("/product")
public class ProductController extends BaseController {

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
	
	@Autowired
	private ProductApiService productApiService;
	
	@ApiOperation("根据店铺编号分页查询商品列表")
	@PostMapping("/findProductPageByShopCode")
	public ResponseWrapper<Page<ProductDTO>> findProductPageByShopCode(
			@RequestParam String shopCode,
			@RequestParam Integer pageNo,
			@RequestParam Integer pageSize
			) {
		
		return productApiService.findProductPageByShopCode(shopCode, pageNo, pageSize);
	}
	
	@ApiOperation("创建商品")
	@PostMapping("/createProduct")
	public ResponseWrapper<Product> createProduct(
			@RequestParam String token, 
			@RequestParam String imgCsv, 
			@RequestParam String tagIdCsv, 
			Product product) {
		
		Long memberId = getMemberId(token);
		
		if (null == memberId) {
			return ResponseWrapper.fail(ErrorCodes.INVALID_TOKEN);
		}
		
		List<String> imgList = CsvUtils.parse(imgCsv);
		List<Long> tagIdList = CsvUtils.parseLong(tagIdCsv);
		
		return productApiService.createOrUpdateProduct(memberId, product, imgList, tagIdList);
	}
	
	@ApiOperation("修改商品")
	@PostMapping("/updateProduct")
	public ResponseWrapper<Product> updateProduct(
			@RequestParam String token, 
			@RequestParam String imgCsv, 
			@RequestParam String tagIdCsv, 
			Product product) {
		
		Long memberId = getMemberId(token);
		
		if (null == memberId) {
			return ResponseWrapper.fail(ErrorCodes.INVALID_TOKEN);
		}
		
		List<String> imgList = CsvUtils.parse(imgCsv);
		List<Long> tagIdList = CsvUtils.parseLong(tagIdCsv);
		
		return productApiService.createOrUpdateProduct(memberId, product, imgList, tagIdList);
	}
	
	@ApiOperation("根据盒子编号查询商品")
	@PostMapping("/findProductByBoxCode")
	public ResponseWrapper<ProductInBoxDTO> findProductByBoxCode(@RequestParam String boxCode) {
		
		return productApiService.findProductByBoxCode(boxCode);
	}
	
	@ApiOperation("根据商品编号查询盒子列表")
	@PostMapping("/findBoxListByProductCode")
	public ResponseWrapper<List<Box>> findBoxListByProductCode(@RequestParam String productCode) {
		
		return productApiService.findBoxListByProductCode(productCode);
	}
	
	@ApiOperation("根据商品编号查询商品")
	@PostMapping("/findProductByProductCode")
	public ResponseWrapper<ProductDTO> findProductByProductCode(@RequestParam String productCode) {
		
		return productApiService.findProductByProductCode(productCode);
	}
	
	@ApiOperation("买家分页查询商品列表")
	@PostMapping("/findProductPageForBuyer")
	public ResponseWrapper<Page<ProductDTO>> findProductPageForBuyer(
			@RequestParam(required = false) String shopCode,
			@RequestParam(required = false) String productName,
			@RequestParam(required = false) Long tagId,
			@RequestParam Integer pageNo,
			@RequestParam Integer pageSize
			) {
		
		return productApiService.findProductPageForBuyer(productName, shopCode, tagId, pageNo, pageSize);
	}
	
	@ApiOperation("创建商品标签")
	@PostMapping("/createProductTag")
	public ResponseWrapper<ProductTag> createProductTag(
			@RequestParam String token, 
			@RequestParam String shopCode,
			@RequestParam String tagName) {
		
		Long memberId = getMemberId(token);
		
		if (null == memberId) {
			return ResponseWrapper.fail(ErrorCodes.INVALID_TOKEN);
		}
		
		return productApiService.createProductTag(memberId, shopCode, tagName);
	}
	
	@ApiOperation("根据店铺查询标签列表")
	@PostMapping("/findProductTagListByShopCode")
	public ResponseWrapper<List<ProductTag>> findProductTagListByShopCode(@RequestParam String shopCode) {
		
		return productApiService.findProductTagListByShopCode(shopCode);
	}
}
