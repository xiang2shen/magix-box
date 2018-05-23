package com.magicbox.service.api;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.magicbox.base.exception.ErrorCodes;
import com.magicbox.base.support.Page;
import com.magicbox.base.support.ResponseWrapper;
import com.magicbox.base.utilities.BeanChecker;
import com.magicbox.base.utilities.PageUtils;
import com.magicbox.base.utilities.XBeanUtils;
import com.magicbox.dto.ProductDTO;
import com.magicbox.dto.ProductInBoxDTO;
import com.magicbox.mapper.ProductImageMapper;
import com.magicbox.mapper.ProductMapper;
import com.magicbox.mapper.ProductTagMapper;
import com.magicbox.mapper.ProductTagRelMapper;
import com.magicbox.model.Box;
import com.magicbox.model.Product;
import com.magicbox.model.ProductImage;
import com.magicbox.model.ProductTag;
import com.magicbox.model.ProductTagRel;
import com.magicbox.model.Seller;
import com.magicbox.model.Shop;
import com.magicbox.service.BoxService;
import com.magicbox.service.ProductImageService;
import com.magicbox.service.ProductService;
import com.magicbox.service.ProductTagRelService;
import com.magicbox.service.ProductTagService;
import com.magicbox.service.ShopService;

@Service
public class ProductApiService {
	
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(ProductApiService.class);

	@Autowired
	private ProductService productService;
	@Autowired
	private ProductMapper productMapper;
	@Autowired
	private ProductImageService productImageService;
	@Autowired
	private ProductImageMapper productImageMapper;
	@Autowired
	private ShopService shopService;
	@Autowired
	private MemberApiService memberApiService;
	@Autowired
	private BoxService boxService;
	@Autowired
	private ProductTagMapper productTagMapper;
	@Autowired
	private ProductTagService productTagService;
	@Autowired
	private ProductTagRelService productTagRelService;
	@Autowired
	private ProductTagRelMapper productTagRelMapper;
	
	public ResponseWrapper<Page<ProductDTO>> findProductPageByShopCode(String shopCode, Integer pageNo, Integer pageSize) {
		BeanChecker.getInstance().notBlank(shopCode).page(pageNo, pageSize);
		
		Page<Product> productPage = productService.selectPageByShopCode(shopCode, pageNo, pageSize);
		Page<ProductDTO> dtoPage = toProductDTOPage(productPage);
		
		return ResponseWrapper.succeed(dtoPage);
	}
	
	private Page<ProductDTO> toProductDTOPage(Page<Product> productPage) {
		List<ProductDTO> items = toProductDTO(productPage.getItems());
		return new Page<>(productPage.getPageNo(), productPage.getPageSize(), items, productPage.getCount());
	}
	
	public List<ProductDTO> toProductDTO(List<Product> productList) {
		if (CollectionUtils.isEmpty(productList)) {
			return Collections.emptyList();
		}
		
		List<String> productCodeList = XBeanUtils.extractField(productList, String.class, "productCode");
		// 图片
		List<ProductImage> imageList = productImageService.selectListByProductCodeList(productCodeList);
		Map<String, List<ProductImage>> imageMap = XBeanUtils.listToValueListMap(imageList, String.class, "productCode");
		// 标签
		List<ProductTagRel> tagRelList = productTagRelService.selectListByProductCodeList(productCodeList);
		Map<String, List<ProductTagRel>> tagRelMap = XBeanUtils.listToValueListMap(tagRelList, String.class, "productCode");
		// 盒子
		List<Box> boxList = boxService.selectListByProductCodeList(productCodeList);
		Map<String, List<Box>> boxMap = XBeanUtils.listToValueListMap(boxList, String.class, "productCode");
		
		List<ProductDTO> dtoList = XBeanUtils.copyList(productList, ProductDTO.class);
		for (ProductDTO dto : dtoList) {
			dto.setImageList(imageMap.get(dto.getProductCode()));
			
			List<ProductTagRel> tagRelValList = tagRelMap.get(dto.getProductCode());
			if (CollectionUtils.isNotEmpty(tagRelValList)) {
				List<Long> tagIdList = XBeanUtils.extractField(tagRelValList, Long.class, "tagId");
				List<ProductTag> tagList =  productTagService.selectListByIdList(tagIdList);
				dto.setTagList(tagList);
			}
			
			dto.setBoxList(boxMap.get(dto.getProductCode()));
		}
		
		return dtoList;
	}

	public ResponseWrapper<Product> createOrUpdateProduct(Long memberId, Product product, List<String> productImageList, List<Long> tagIdList) {
		BeanChecker.getInstance().notNull(memberId).notNull(product).notBlank(product.getShopCode()).notEmpty(productImageList);
		
		Seller seller = memberApiService.findSellerByMemberId(memberId).getBody();
		if (null == seller) {
			return ResponseWrapper.fail(ErrorCodes.NOT_SELLER);
		}
		
		Shop shop = shopService.selectOneByShopCode(product.getShopCode());
		if (null == shop) {
			return ResponseWrapper.fail(ErrorCodes.SHOP_NOT_FOUND);
		}
		
		if (!shop.getSellerId().equals(seller.getId())) {
			return ResponseWrapper.fail(ErrorCodes.SHOP_NOT_BELONG_TO_SELLER);
		}
		
		Date now = new Date();
		product.setUpdateTime(now);
		
		if (StringUtils.isBlank(product.getProductCode())) {	// 新建
			
			String productCode = "PRD" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
			
			product.setCreateTime(now);
			product.setProductCode(productCode);
			productMapper.insert(product);
			
		} else {	// 修改
			
			Product productPO = productService.selectOneByProductCode(product.getProductCode());
			if (null == productPO) {
				return ResponseWrapper.fail(ErrorCodes.PRODUCT_NOT_FOUND);
			}
			if (!productPO.getShopCode().equals(product.getShopCode())) {
				return ResponseWrapper.fail(ErrorCodes.SHOP_NOT_BELONG_TO_SELLER);
			}
			
			productPO.setProductName(product.getProductName());
			productPO.setProductDesc(product.getProductDesc());
			productPO.setProductPrice(product.getProductPrice());
			productPO.setProductStock(product.getProductStock());
			
			productMapper.updateByPrimaryKeySelective(productPO);
			
			productImageService.deleteByProductCode(product.getProductCode());
		}
		
		createProductImageList(productImageList, product.getProductCode());
		bindProductTag(product.getProductCode(), product.getShopCode(), tagIdList);
		
		return ResponseWrapper.succeed(product);
	}

	private void bindProductTag(String productCode, String shopCode, List<Long> tagIdList) {
		productTagRelService.deleteByProductCode(productCode);
		
		for (Long tagId : tagIdList) {
			if (null != tagId) {
				ProductTag tag = productTagMapper.selectByPrimaryKey(tagId);
				if (null != tag && tag.getShopCode().equals(shopCode)) {
					
					Date now = new Date();
					
					ProductTagRel rel = new ProductTagRel();
					rel.setProductCode(productCode);
					rel.setTagId(tagId);
					rel.setCreateTime(now);
					rel.setUpdateTime(now);
					
					productTagRelMapper.insert(rel);
				}
			}
		}
	}

	private void createProductImageList(List<String> productImageList, String productCode) {
		Date now = new Date();
		int imgSort = 1;
		for (String imgUrl : productImageList) {
			if (StringUtils.isNotBlank(imgUrl)) {
				
				ProductImage image = new ProductImage();
				image.setProductCode(productCode);
				image.setImgUrl(imgUrl);
				image.setSort(imgSort++);
				image.setCreateTime(now);
				image.setUpdateTime(now);

				productImageMapper.insert(image);
			}
		}
	}

	public ResponseWrapper<ProductInBoxDTO> findProductByBoxCode(String boxCode) {
		BeanChecker.getInstance().notBlank(boxCode);
		
		Box box = boxService.selectOneByBoxCode(boxCode);
		if (null == box) {
			return ResponseWrapper.fail(ErrorCodes.BOX_NOT_FOUND);
		}
		if (StringUtils.isBlank(box.getProductCode())) {
			return ResponseWrapper.fail(ErrorCodes.BOX_NO_PRODUCT);
		}
		
		Product product = productService.selectOneByProductCode(box.getProductCode());
		if (null == product) {
			return ResponseWrapper.fail(ErrorCodes.PRODUCT_NOT_FOUND);
		}
		
		ProductInBoxDTO dto = new ProductInBoxDTO();
		dto.setProduct(product);
		dto.setBox(box);
		
		return ResponseWrapper.succeed(dto);
	}

	public ResponseWrapper<ProductDTO> findProductByProductCode(String productCode) {
		BeanChecker.getInstance().notBlank(productCode);
		
		Product product = productService.selectOneByProductCode(productCode);
		if (null == product) {
			return ResponseWrapper.fail(ErrorCodes.PRODUCT_NOT_FOUND);
		}
		
		List<ProductImage> imageList = productImageService.selectListByProductCode(productCode);
		List<ProductTagRel> tagRelList = productTagRelService.selectListByProductCode(productCode);
		List<Long> tagIdList = XBeanUtils.extractField(tagRelList, Long.class, "tagId");
		List<ProductTag> tagList =  productTagService.selectListByIdList(tagIdList);
		List<Box> boxList = boxService.selectListByProductCode(productCode);
		
		ProductDTO dto = new ProductDTO();
		BeanUtils.copyProperties(product, dto);
		dto.setImageList(imageList);
		dto.setTagList(tagList);
		dto.setBoxList(boxList);
		
		return ResponseWrapper.succeed(dto);
	}

	public ResponseWrapper<List<Box>> findBoxListByProductCode(String productCode) {
		BeanChecker.getInstance().notBlank(productCode);
		
		List<Box> boxList = boxService.selectListByProductCode(productCode);
		
		return ResponseWrapper.succeed(boxList);
	}

	public ResponseWrapper<Page<ProductDTO>> findProductPageForBuyer(String productName, String shopCode, Long tagId,
			Integer pageNo, Integer pageSize) {
		BeanChecker.getInstance().page(pageNo, pageSize);
		
		List<String> productCodeList = null;
		if (null != tagId) {
			List<ProductTagRel> relList = productTagRelService.selectListByTagId(tagId);
			if (CollectionUtils.isEmpty(relList)) {
				return ResponseWrapper.succeed(PageUtils.emptyPage(pageNo, pageSize));
			}
			
			productCodeList = XBeanUtils.extractField(relList, String.class, "productCode");
		}
		
		Page<Product> productPage = productService.selectPageConditionally(productName, shopCode, productCodeList, pageNo, pageSize);
		Page<ProductDTO> dtoPage = toProductDTOPage(productPage);
		
		return ResponseWrapper.succeed(dtoPage);
	}

	public ResponseWrapper<ProductTag> createProductTag(Long memberId, String shopCode, String tagName) {
		BeanChecker.getInstance().notNull(memberId).notBlank(shopCode).notBlank(tagName);
		
		Seller seller = memberApiService.findSellerByMemberId(memberId).getBody();
		if (null == seller) {
			return ResponseWrapper.fail(ErrorCodes.NOT_SELLER);
		}
		
		Shop shop = shopService.selectOneByShopCode(shopCode);
		if (null == shop) {
			return ResponseWrapper.fail(ErrorCodes.SHOP_NOT_FOUND);
		}
		
		if (!shop.getSellerId().equals(seller.getId())) {
			return ResponseWrapper.fail(ErrorCodes.SHOP_NOT_BELONG_TO_SELLER);
		}
		
		ProductTag tag = productTagService.selectOneByTagNameAndShopCode(tagName, shopCode);
		if (null == tag) {
			Date now = new Date();
			tag = new ProductTag();
			
			tag.setTagName(tagName);
			tag.setShopCode(shopCode);
			tag.setCreateTime(now);
			tag.setUpdateTime(now);
			productTagMapper.insert(tag);
		}
		
		return ResponseWrapper.succeed(tag);
	}

	public ResponseWrapper<List<ProductTag>> findProductTagListByShopCode(String shopCode) {
		BeanChecker.getInstance().notBlank(shopCode);
		
		List<ProductTag> tagList = productTagService.selectListByShopCode(shopCode);
		
		return ResponseWrapper.succeed(tagList);
	}

}
