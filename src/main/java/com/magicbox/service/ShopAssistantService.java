package com.magicbox.service;

import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.magicbox.base.constants.ShopAssistantStatusEnum;
import com.magicbox.base.exception.ErrorCodes;
import com.magicbox.base.support.Page;
import com.magicbox.base.support.ResponseWrapper;
import com.magicbox.base.utilities.BeanChecker;
import com.magicbox.base.utilities.XCollectionUtils;
import com.magicbox.dto.ShopAssistantDTO;
import com.magicbox.mapper.MemberMapper;
import com.magicbox.mapper.ShopAssistantMapper;
import com.magicbox.model.Member;
import com.magicbox.model.ShopAssistant;
import com.magicbox.model.ShopAssistantExample;

@Service
public class ShopAssistantService {
    @Autowired
    private ShopAssistantMapper shopAssistantMapper;
    @Autowired
    private ShopAssistantService shopAssistantService;
    @Autowired
    private MemberMapper memberMapper;

    protected Page<ShopAssistant> selectPageByExample(ShopAssistantExample example) {
        return new Page<>(example.getPageNo(), example.getLimit(), shopAssistantMapper.selectByExample(example), shopAssistantMapper.countByExample(example));
    }

	public ShopAssistant selectOneByMobile(String mobile) {
		if (StringUtils.isBlank(mobile)) {
			return null;
		}
		
		ShopAssistantExample example = new ShopAssistantExample();
		example.or().andAssistantMobileEqualTo(mobile);
		
		return XCollectionUtils.getFirstElement(shopAssistantMapper.selectByExample(example));
	}

	public List<ShopAssistant> selectListBySellerId(Long sellerId) {
		if (null == sellerId) {
			return Collections.emptyList();
		}
		
		ShopAssistantExample example = new ShopAssistantExample();
		example.or().andSellerIdEqualTo(sellerId);
		
		return shopAssistantMapper.selectByExample(example);
	}

	public void updateStatus(Long shopAssistantId, ShopAssistantStatusEnum statusEnum) {
		if (null != shopAssistantId && null != statusEnum) {
			
			ShopAssistant record = new ShopAssistant();
			record.setId(shopAssistantId);
			record.setAssistantStatus(statusEnum.getCode());
			shopAssistantMapper.updateByPrimaryKeySelective(record);
		}
	}

	public ResponseWrapper<ShopAssistantDTO> findShopAssistants(Long memberId) {
		BeanChecker.getInstance().notNull(memberId);
		
		Member member = memberMapper.selectByPrimaryKey(memberId);
		if (null == member) {
			return ResponseWrapper.fail(ErrorCodes.MEMBER_NOT_FOUND);
		}
		
		if (null == member.getShopAssistantId()) {
			return ResponseWrapper.fail(ErrorCodes.NOT_SHOP_ASSISTANT); 
		}
		
		ShopAssistant shopAssistant = shopAssistantMapper.selectByPrimaryKey(member.getShopAssistantId());
		ShopAssistantDTO shopAssistantDTO = new ShopAssistantDTO();
		BeanUtils.copyProperties(shopAssistant, shopAssistantDTO);
		shopAssistantDTO.setMember(member);
		
		return ResponseWrapper.succeed(shopAssistantDTO);
	}
}