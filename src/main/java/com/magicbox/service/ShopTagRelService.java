package com.magicbox.service;

import java.util.Collections;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.magicbox.base.support.Page;
import com.magicbox.dao.ShopTagDAO;
import com.magicbox.dto.ShopTagRelDTO;
import com.magicbox.mapper.ShopTagRelMapper;
import com.magicbox.model.ShopTagRel;
import com.magicbox.model.ShopTagRelExample;

@Service
public class ShopTagRelService {
	
	@Autowired
    private ShopTagRelMapper shopTagRelMapper;
	@Autowired
	private ShopTagDAO shopTagDAO;

    protected Page<ShopTagRel> selectPageByExample(ShopTagRelExample example) {
        return new Page<>(example.getPageNo(), example.getLimit(), shopTagRelMapper.selectByExample(example), shopTagRelMapper.countByExample(example));
    }

	public List<ShopTagRel> selectListByTagId(Long tagId) {
		if (null == tagId) {
			return Collections.emptyList();
		}
		
		ShopTagRelExample example = new ShopTagRelExample();
		example.setOrderByClause("id desc");
		example.or().andTagIdEqualTo(tagId);
		
		return shopTagRelMapper.selectByExample(example);
	}

	public List<ShopTagRel> selectListByShopCodeList(List<String> shopCodeList) {
		if (CollectionUtils.isEmpty(shopCodeList)) {
			return Collections.emptyList();
		}
		
		ShopTagRelExample example = new ShopTagRelExample();
		example.setOrderByClause("id desc");
		example.or().andShopCodeIn(shopCodeList);
		
		return shopTagRelMapper.selectByExample(example);
	}

	public List<ShopTagRelDTO> selectDTOListByShopCodeList(List<String> shopCodeList) {
		if (CollectionUtils.isEmpty(shopCodeList)) {
			return Collections.emptyList();
		}
		
		return shopTagDAO.selectDTOListByShopCodeList(shopCodeList);
	}

	public void deleteByShopCode(String shopCode) {
		if (StringUtils.isNotBlank(shopCode)) {
			ShopTagRelExample example = new ShopTagRelExample();
			example.or().andShopCodeEqualTo(shopCode);
			shopTagRelMapper.deleteByExample(example);
		}
		
	}
}