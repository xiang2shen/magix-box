package com.magicbox.service.api;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.magicbox.base.exception.ErrorCodes;
import com.magicbox.base.support.Page;
import com.magicbox.base.support.ResponseWrapper;
import com.magicbox.base.utilities.BeanChecker;
import com.magicbox.base.utilities.PageUtils;
import com.magicbox.base.utilities.XBeanUtils;
import com.magicbox.dto.ProductDTO;
import com.magicbox.dto.ShopDTO;
import com.magicbox.mapper.ShopMapper;
import com.magicbox.model.Product;
import com.magicbox.model.Seller;
import com.magicbox.model.Shop;
import com.magicbox.service.ProductService;
import com.magicbox.service.ShopService;
import com.magicbox.service.api.MapApiService.Coordinate;

@Service
public class ShopApiService {
	
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(ShopApiService.class);

	@Autowired
	private ShopService shopService;
	@Autowired
	private MemberApiService memberApiService;
	@Autowired
	private ShopMapper shopMapper;
	@Autowired
	private MapApiService mapApiService;
	@Autowired
	private ProductService productService;
	@Autowired
	private ProductApiService productApiService;

	public ResponseWrapper<Page<Shop>> findShopPageByMemberId(Long memberId, Integer pageNo, Integer pageSize) {
		BeanChecker.getInstance().notNull(memberId).page(pageNo, pageSize);
		
		Seller seller = memberApiService.findSellerByMemberId(memberId).getBody();
		if (null == seller) {
			return ResponseWrapper.fail(ErrorCodes.NOT_SELLER);
		}
		
		Page<Shop> shopPage = shopService.selectPageBySellerId(seller.getId(), pageNo, pageSize);
		return ResponseWrapper.succeed(shopPage);
	}

	public ResponseWrapper<Shop> createShop(Long memberId, Shop shop) {
		BeanChecker.getInstance().notNull(memberId).notNull(shop).notBlank(shop.getShopName());
		
		Seller seller = memberApiService.findSellerByMemberId(memberId).getBody();
		if (null == seller) {
			return ResponseWrapper.fail(ErrorCodes.NOT_SELLER);
		}
		
		String shopCode = "S" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		
		Date now = new Date();
		
		shop.setShopCode(shopCode);
		shop.setShopStatus(1);
		shop.setSellerId(seller.getId());
		shop.setCreateTime(now);
		shop.setUpdateTime(now);
		
		Coordinate coordinate = mapApiService.findCoordinateByAddress(shop.getShopAddress(), shop.getShopCityName()).getBody();
		if (null != coordinate) {
			shop.setShopLongitude(coordinate.getLongitude().toString());
			shop.setShopLatitude(coordinate.getLatitude().toString());
		}
		
		shopMapper.insert(shop);
		
		return ResponseWrapper.succeed(shop);
	}

	public ResponseWrapper<Shop> UpdateShop(Long memberId, Shop shop) {
		BeanChecker.getInstance().notNull(memberId).notNull(shop).notBlank(shop.getShopCode()).notBlank(shop.getShopName());
		
		Seller seller = memberApiService.findSellerByMemberId(memberId).getBody();
		if (null == seller) {
			return ResponseWrapper.fail(ErrorCodes.NOT_SELLER);
		}
		
		Shop shopPO = shopService.selectOneByShopCode(shop.getShopCode());
		if (null == shopPO) {
			return ResponseWrapper.fail(ErrorCodes.SHOP_NOT_FOUND);
		}
		if (!shopPO.getSellerId().equals(seller.getId())) {
			return ResponseWrapper.fail(ErrorCodes.SHOP_NOT_BELONG_TO_SELLER);
		}
		
		shopPO.setShopName(shop.getShopName());
		shopPO.setShopDesc(shop.getShopDesc());
		shopPO.setShopProvinceCode(shop.getShopProvinceCode());
		shopPO.setShopProvinceName(shop.getShopProvinceName());
		shopPO.setShopCityCode(shop.getShopCityCode());
		shopPO.setShopCityName(shop.getShopCityName());
		shopPO.setShopDistrictCode(shop.getShopDistrictCode());
		shopPO.setShopDistrictName(shop.getShopDistrictName());
		shopPO.setShopCountyCode(shop.getShopCountyCode());
		shopPO.setShopCountyName(shop.getShopCountyName());
		shopPO.setShopAddress(shop.getShopAddress());
		shopPO.setShopPhoto(shop.getShopPhoto());
		
		Coordinate coordinate = mapApiService.findCoordinateByAddress(shop.getShopAddress(), shop.getShopCityName()).getBody();
		if (null != coordinate) {
			shop.setShopLongitude(coordinate.getLongitude().toString());
			shop.setShopLatitude(coordinate.getLatitude().toString());
		}
		
		shopMapper.updateByPrimaryKeySelective(shopPO);

		return ResponseWrapper.succeed(shopPO);
	}

	public ResponseWrapper<Shop> findShopByShopCode(Long memberId, String shopCode) {
		BeanChecker.getInstance().notNull(memberId).notBlank(shopCode);
		
//		Seller seller = memberApiService.findSellerByMemberId(memberId).getBody();
//		if (null == seller) {
//			return ResponseWrapper.fail(ErrorCodes.NOT_SELLER);
//		}
		
		Shop shop = shopService.selectOneByShopCode(shopCode);
		if (null == shop) {
			return ResponseWrapper.fail(ErrorCodes.SHOP_NOT_FOUND);
		}
//		if (!shop.getSellerId().equals(seller.getId())) {
//			return ResponseWrapper.fail(ErrorCodes.SHOP_NOT_BELONG_TO_SELLER);
//		}
		
		return ResponseWrapper.succeed(shop);
	}

	public ResponseWrapper<Page<ShopDTO>> findShopPageForBuyer(String shopName, Integer pageNo, Integer pageSize) {
		BeanChecker.getInstance().page(pageNo, pageSize);
		
		Page<Shop> shopPage = shopService.selectPageConditionally(shopName, pageNo, pageSize);
		if (PageUtils.isEmpty(shopPage)) {
			return ResponseWrapper.succeed(PageUtils.emptyPage(pageNo, pageSize));
		}
		
		List<String> shopCodeList = XBeanUtils.extractField(shopPage.getItems(), String.class, "shopCode");
		List<Product> productList = productService.selectListByShopCodeList(shopCodeList);
		List<ProductDTO> productDTOList = productApiService.toProductDTO(productList);
		Map<String, List<ProductDTO>> productDTOMap = XBeanUtils.listToValueListMap(productDTOList, String.class, "shopCode");
		
		Page<ShopDTO> shopDTOPage = PageUtils.copy(shopPage, ShopDTO.class);
		for (ShopDTO eachShop : shopDTOPage) {
			List<ProductDTO> pDTOList = productDTOMap.get(eachShop.getShopCode());
			eachShop.setProductList(pDTOList);
		}
		
		return ResponseWrapper.succeed(shopDTOPage);
	}
}
