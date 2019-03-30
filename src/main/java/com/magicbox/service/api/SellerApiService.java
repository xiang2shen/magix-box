package com.magicbox.service.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.magicbox.base.constants.ShopAssistantStatusEnum;
import com.magicbox.base.exception.ErrorCodes;
import com.magicbox.base.support.ResponseWrapper;
import com.magicbox.base.utilities.BeanChecker;
import com.magicbox.base.utilities.XBeanUtils;
import com.magicbox.dto.ShopAssistantDTO;
import com.magicbox.mapper.ShopAssistantMapper;
import com.magicbox.model.Member;
import com.magicbox.model.Seller;
import com.magicbox.model.ShopAssistant;
import com.magicbox.service.MemberService;
import com.magicbox.service.ShopAssistantService;

@Service
public class SellerApiService {
	
	@Autowired
	private ShopAssistantService shopAssistantService;
	@Autowired
	private ShopAssistantMapper shopAssistantMapper;
	@Autowired
	private MemberApiService memberApiService;
	@Autowired
	private MemberService memberService;
	
	public ResponseWrapper<List<ShopAssistantDTO>> findShopAssistants(Long memberId) {
		BeanChecker.getInstance().notNull(memberId);
		
		Seller seller = memberApiService.findSellerByMemberId(memberId).getBody();
		if (null == seller) {
			return ResponseWrapper.fail(ErrorCodes.NOT_SELLER);
		}
		
		List<ShopAssistant> shopAssistants = shopAssistantService.selectListBySellerId(seller.getId());
		List<ShopAssistantDTO> shopAssistantDTOs = XBeanUtils.copyList(shopAssistants, ShopAssistantDTO.class);
		
		shopAssistantDTOs.forEach(dto -> {
			
			Member member = memberService.selectOneByShopAssistantId(dto.getId());
			dto.setMember(member);
		});
		
		return ResponseWrapper.succeed(shopAssistantDTOs);
	}

	public ResponseWrapper<?> checkShopAssistant(Long memberId, Long shopAssistantId, Boolean isSuccess) {
		BeanChecker.getInstance().notNull(memberId).notNull(shopAssistantId).notNull(isSuccess);
		
		Seller seller = memberApiService.findSellerByMemberId(memberId).getBody();
		if (null == seller) {
			return ResponseWrapper.fail(ErrorCodes.NOT_SELLER);
		}
		
		ShopAssistant assistant = shopAssistantMapper.selectByPrimaryKey(shopAssistantId);
		if (null == assistant || ! assistant.getSellerId().equals(seller.getId())) {
			return ResponseWrapper.fail(ErrorCodes.NO_AUTH);
		}
		
		shopAssistantService.updateStatus(shopAssistantId, isSuccess ? ShopAssistantStatusEnum.CHECK_SUCCESS : ShopAssistantStatusEnum.CHECK_FAILURE);
		return ResponseWrapper.succeed();
	}

	public ResponseWrapper<?> deleteShopAssistant(Long memberId, Long shopAssistantId) {
		BeanChecker.getInstance().notNull(memberId).notNull(shopAssistantId);
		
		Seller seller = memberApiService.findSellerByMemberId(memberId).getBody();
		if (null == seller) {
			return ResponseWrapper.fail(ErrorCodes.NOT_SELLER);
		}
		
		ShopAssistant assistant = shopAssistantMapper.selectByPrimaryKey(shopAssistantId);
		if (null == assistant || ! assistant.getSellerId().equals(seller.getId())) {
			return ResponseWrapper.fail(ErrorCodes.NO_AUTH);
		}
		
		Member assistantMember = memberService.selectOneByShopAssistantId(shopAssistantId);
		
		memberService.deleteShopAssistantId(assistantMember.getId());
		return ResponseWrapper.succeed();
	}
}
