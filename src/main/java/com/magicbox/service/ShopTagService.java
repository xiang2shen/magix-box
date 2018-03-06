package com.magicbox.service;

import java.util.Collections;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.magicbox.base.support.Page;
import com.magicbox.mapper.ShopTagMapper;
import com.magicbox.model.ShopTag;
import com.magicbox.model.ShopTagExample;

@Service
public class ShopTagService {
	
	@Autowired
    private ShopTagMapper shopTagMapper;

    protected Page<ShopTag> selectPageByExample(ShopTagExample example) {
        return new Page<>(example.getPageNo(), example.getLimit(), shopTagMapper.selectByExample(example), shopTagMapper.countByExample(example));
    }

	public List<ShopTag> selectListByIdList(List<Long> idList) {
		if (CollectionUtils.isEmpty(idList)) {
			return Collections.emptyList();
		}
		
		ShopTagExample example = new ShopTagExample();
		example.setOrderByClause("id desc");
		example.or().andIdIn(idList);
		
		return shopTagMapper.selectByExample(example);
	}
}
