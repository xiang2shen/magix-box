package com.magicbox.service;

import java.util.Collections;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.magicbox.base.support.Page;
import com.magicbox.base.utilities.PageUtils;
import com.magicbox.base.utilities.XCollectionUtils;
import com.magicbox.mapper.ShopMapper;
import com.magicbox.model.Shop;
import com.magicbox.model.ShopExample;

@Service
public class ShopService {
	
	@Autowired
    private ShopMapper shopMapper;

    protected Page<Shop> selectPageByExample(ShopExample example) {
        return new Page<>(example.getPageNo(), example.getLimit(), shopMapper.selectByExample(example), shopMapper.countByExample(example));
    }

	public Page<Shop> selectPageBySellerId(Long sellerId, Integer pageNo, Integer pageSize) {
		if (null == sellerId) {
			return PageUtils.emptyPage(pageNo, pageSize);
		}
		
		ShopExample example = new ShopExample();
		example.initPage(pageNo, pageSize);
		example.setOrderByClause("id desc");
		
		example.or().andSellerIdEqualTo(sellerId);
		
		return selectPageByExample(example);
	}

	public Shop selectOneByShopCode(String shopCode) {
		if (StringUtils.isBlank(shopCode)) {
			return null;
		}
		
		ShopExample example = new ShopExample();
		example.or().andShopCodeEqualTo(shopCode);
		
		return XCollectionUtils.getFirstElement(shopMapper.selectByExample(example));
	}

	public List<Shop> selectListByShopCodeList(List<String> shopCodeList) {
		if (CollectionUtils.isEmpty(shopCodeList)) {
			return Collections.emptyList();
		}
		
		ShopExample example = new ShopExample();
		example.setOrderByClause("id desc");
		example.or().andShopCodeIn(shopCodeList);
		
		return shopMapper.selectByExample(example);
	}

	public Page<Shop> selectPageConditionally(String shopName, Integer pageNo, Integer pageSize) {
		ShopExample example = new ShopExample();
		example.initPage(pageNo, pageSize);
		example.setOrderByClause("id desc");
		
		ShopExample.Criteria criteria = example.or();
		if (StringUtils.isNotBlank(shopName)) {
			criteria.andShopNameLike("%" + shopName + "%");
		}
		
		return selectPageByExample(example);
	}

	public List<Shop> selectListBySellerId(Long sellerId) {
		if (null == sellerId) {
			return Collections.emptyList();
		}
		
		ShopExample example = new ShopExample();
		example.setOrderByClause("id desc");
		
		example.or().andSellerIdEqualTo(sellerId);
		
		return shopMapper.selectByExample(example);
	}
}