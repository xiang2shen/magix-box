package com.magicbox.service;

import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.magicbox.base.constants.ShopAssistantStatusEnum;
import com.magicbox.base.support.Page;
import com.magicbox.base.utilities.XCollectionUtils;
import com.magicbox.mapper.ShopAssistantMapper;
import com.magicbox.model.ShopAssistant;
import com.magicbox.model.ShopAssistantExample;

@Service
public class ShopAssistantService {
    @Autowired
    private ShopAssistantMapper shopAssistantMapper;

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
}