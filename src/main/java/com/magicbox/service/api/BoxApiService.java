package com.magicbox.service.api;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.magicbox.base.exception.ErrorCodes;
import com.magicbox.base.support.ResponseWrapper;
import com.magicbox.base.utilities.BeanChecker;
import com.magicbox.mapper.BoxMapper;
import com.magicbox.model.Box;
import com.magicbox.model.Product;
import com.magicbox.model.Seller;
import com.magicbox.model.Shop;
import com.magicbox.mqtt.MqttClient;
import com.magicbox.service.BoxService;
import com.magicbox.service.ProductService;
import com.magicbox.service.ShopService;

@Service
public class BoxApiService {
	
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(BoxApiService.class);

	@Autowired
	private ProductService productService;
	@Autowired
	private BoxService boxService;
	@Autowired
	private BoxMapper boxMapper;
	@Autowired
	private ShopService shopService;
	@Autowired
	private MemberApiService memberApiService;
	@Autowired
	private MqttClient mqttClient;
	

	public ResponseWrapper<Box> bindBoxWithProduct(Long memberId, String productCode, String boxCode) {
		BeanChecker.getInstance().notNull(memberId).notBlank(productCode).notBlank(boxCode);
		
		// 校验卖家
		Seller seller = memberApiService.findSellerByMemberId(memberId).getBody();
		if (null == seller) {
			return ResponseWrapper.fail(ErrorCodes.NOT_SELLER);
		}
		
		Product product = productService.selectOneByProductCode(productCode);
		if (null == product) {
			return ResponseWrapper.fail(ErrorCodes.PRODUCT_NOT_FOUND);
		}
		
		Box box = boxService.selectOneByBoxCode(boxCode);
		if (null == box) {
			return ResponseWrapper.fail(ErrorCodes.BOX_NOT_FOUND);
		}
		
		// 校验店铺是否相同
		Shop shop = shopService.selectOneByShopCode(product.getShopCode());
		if (null == shop) {
			return ResponseWrapper.fail(ErrorCodes.SHOP_NOT_FOUND);
		}
		if (!shop.getSellerId().equals(seller.getId())) {
			return ResponseWrapper.fail(ErrorCodes.SHOP_NOT_BELONG_TO_SELLER);
		}
		if (!shop.getShopCode().equals(box.getShopCode())) {
			return ResponseWrapper.fail(ErrorCodes.BOX_NOT_BELONG_TO_SHOP);
		}
		
		boxService.updateProductCodeByBoxCode(productCode, boxCode);
		
		return ResponseWrapper.succeed(boxService.selectOneByBoxCode(boxCode));
	}


	public ResponseWrapper<Box> createBox(Long memberId, Box box) {
		BeanChecker.getInstance().notNull(memberId).notNull(box).notBlank(box.getShopCode());
		
		// 校验卖家
		Seller seller = memberApiService.findSellerByMemberId(memberId).getBody();
		if (null == seller) {
			return ResponseWrapper.fail(ErrorCodes.NOT_SELLER);
		}
		
		Shop shop = shopService.selectOneByShopCode(box.getShopCode());
		if (null == shop) {
			return ResponseWrapper.fail(ErrorCodes.SHOP_NOT_FOUND);
		}
		if (!shop.getSellerId().equals(seller.getId())) {
			return ResponseWrapper.fail(ErrorCodes.SHOP_NOT_BELONG_TO_SELLER);
		}
		if (!shop.getShopCode().equals(box.getShopCode())) {
			return ResponseWrapper.fail(ErrorCodes.BOX_NOT_BELONG_TO_SHOP);
		}
		
		String boxCode = "BOX" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		Date now = new Date();
		
		box.setBoxCode(boxCode);
		box.setFrameCode("testFrameCode");
		box.setBoxStatus(1);
		box.setBoxModel("测试型号");
		box.setCapacity(8);
		box.setCreateTime(now);
		box.setUpdateTime(now);

		boxMapper.insert(box);
		
		return ResponseWrapper.succeed(box);
	}

	public ResponseWrapper<Box> createOrUpdateBox(String frameCode, String boxCode, Integer stock) {
		BeanChecker.getInstance().notBlank(frameCode).notBlank(boxCode).positive(stock);
		
		Box box = boxService.selectOneByBoxCode(boxCode);
		if (null == box) {
			
			box = new Box();
			Date now = new Date();
			
			box.setFrameCode(frameCode);
			box.setBoxCode(boxCode);
			box.setBoxStatus(1);
			box.setCreateTime(now);
			box.setUpdateTime(now);
			
			boxMapper.insert(box);
		} else {
			
			box.setFrameCode(frameCode);	// 更新设备号
			box.setProductStock(stock);
			boxMapper.updateByPrimaryKeySelective(box);
		}
		
		return ResponseWrapper.succeed(boxService.selectOneByBoxCode(boxCode));
	}
}
