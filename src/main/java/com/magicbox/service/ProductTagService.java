package com.magicbox.service;

import java.util.Collections;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.magicbox.base.support.Page;
import com.magicbox.base.utilities.XCollectionUtils;
import com.magicbox.mapper.ProductTagMapper;
import com.magicbox.model.ProductTag;
import com.magicbox.model.ProductTagExample;

@Service
public class ProductTagService {
	
	@Autowired
    private ProductTagMapper productTagMapper;

    protected Page<ProductTag> selectPageByExample(ProductTagExample example) {
        return new Page<>(example.getPageNo(), example.getLimit(), productTagMapper.selectByExample(example), productTagMapper.countByExample(example));
    }

	public ProductTag selectOneByTagNameAndShopCode(String tagName, String shopCode) {
		if (StringUtils.isBlank(tagName) || StringUtils.isBlank(shopCode)) {
			return null;
		}
		
		ProductTagExample example = new ProductTagExample();
		example.or().andTagNameEqualTo(tagName).andShopCodeEqualTo(shopCode);
		
		return XCollectionUtils.getFirstElement(productTagMapper.selectByExample(example));
	}

	public List<ProductTag> selectListByShopCode(String shopCode) {
		if (StringUtils.isBlank(shopCode)) {
			return Collections.emptyList();
		}
		
		ProductTagExample example = new ProductTagExample();
		example.setOrderByClause("id desc");
		example.or().andShopCodeEqualTo(shopCode);
		
		return productTagMapper.selectByExample(example);
	}

	public List<ProductTag> selectListByIdList(List<Long> tagIdList) {
		if (CollectionUtils.isEmpty(tagIdList)) {
			return Collections.emptyList();
		}
		
		ProductTagExample example = new ProductTagExample();
		example.setOrderByClause("id desc");
		example.or().andIdIn(tagIdList);
		
		return productTagMapper.selectByExample(example);
	}
}