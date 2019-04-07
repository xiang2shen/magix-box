package com.magicbox.service.api;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.magicbox.base.constants.FrameStatusEnum;
import com.magicbox.base.exception.ErrorCodes;
import com.magicbox.base.support.ResponseWrapper;
import com.magicbox.base.utilities.BeanChecker;
import com.magicbox.mapper.FrameMapper;
import com.magicbox.model.Frame;
import com.magicbox.model.Seller;
import com.magicbox.model.Shop;
import com.magicbox.service.FrameService;
import com.magicbox.service.ShopService;

@Service
public class FrameApiService {

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(FrameApiService.class);
	
	@Autowired
	private FrameMapper frameMapper;
	@Autowired
	private FrameService frameService;
	@Autowired
	private MemberApiService memberApiService;
	@Autowired
	private ShopService shopService;
	
	public ResponseWrapper<Frame> createFrameWhenAbsent(String frameCode) {
		BeanChecker.getInstance().notBlank(frameCode);
		
		Frame frame = frameService.selectOneByFrameCode(frameCode);
		if (null == frame) {
			
			frame = new Frame();
			frame.setFrameCode(frameCode);
			frame.setFrameStatus(FrameStatusEnum.OFFLINE.getCode());
			
			frameMapper.insertSelective(frame);
		}
		
		
		return ResponseWrapper.succeed(frame);
	}

	public ResponseWrapper<?> bindShopWithFrame(Long memberId, String shopCode, String frameCode) {
		// 校验卖家
		Seller seller = memberApiService.findSellerByMemberId(memberId).getBody();
		if (null == seller) {
			return ResponseWrapper.fail(ErrorCodes.NOT_SELLER);
		}

		// 校验店铺是否属于卖家
		Shop shop = shopService.selectOneByShopCode(shopCode);
		if (null == shop) {
			return ResponseWrapper.fail(ErrorCodes.SHOP_NOT_FOUND);
		}
		if (!shop.getSellerId().equals(seller.getId())) {
			return ResponseWrapper.fail(ErrorCodes.SHOP_NOT_BELONG_TO_SELLER);
		}
		
		// 校验设备是否没有绑定过其他店铺
		Frame frame = frameService.selectOneByFrameCode(frameCode);
		if (null == frame) {
			return ResponseWrapper.fail(ErrorCodes.FRAME_NOT_FOUND);
		}
		if (StringUtils.isNotBlank(frame.getShopCode())) {
			return ResponseWrapper.fail(ErrorCodes.FRAME_ALREADY_BIND);
		}
		
		frameService.updateShopCode(frame.getId(), shopCode);
		return ResponseWrapper.succeed();
	}
}
