package com.magicbox.service;

import java.util.Collections;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.magicbox.base.support.Page;
import com.magicbox.mapper.ProductTagRelMapper;
import com.magicbox.model.ProductTagRel;
import com.magicbox.model.ProductTagRelExample;

@Service
public class ProductTagRelService {
	
	@Autowired
    private ProductTagRelMapper productTagRelMapper;

    protected Page<ProductTagRel> selectPageByExample(ProductTagRelExample example) {
        return new Page<>(example.getPageNo(), example.getLimit(), productTagRelMapper.selectByExample(example), productTagRelMapper.countByExample(example));
    }

	public void deleteByProductCode(String productCode) {
		if (StringUtils.isNotBlank(productCode)) {
			ProductTagRelExample example = new ProductTagRelExample();
			example.or().andProductCodeEqualTo(productCode);
			
			productTagRelMapper.deleteByExample(example);
		}
	}

	public List<ProductTagRel> selectListByProductCodeList(List<String> productCodeList) {
		if (CollectionUtils.isEmpty(productCodeList)) {
			return Collections.emptyList();
		}
		
		ProductTagRelExample example = new ProductTagRelExample();
		example.or().andProductCodeIn(productCodeList);
		
		return productTagRelMapper.selectByExample(example);
	}

	public List<ProductTagRel> selectListByProductCode(String productCode) {
		if (StringUtils.isBlank(productCode)) {
			return Collections.emptyList();
		}
		
		ProductTagRelExample example = new ProductTagRelExample();
		example.or().andProductCodeEqualTo(productCode);
		
		return productTagRelMapper.selectByExample(example);
	}

	public List<ProductTagRel> selectListByTagId(Long tagId) {
		if (null == tagId) {
			return Collections.emptyList();
		}
		
		ProductTagRelExample example = new ProductTagRelExample();
		example.or().andTagIdEqualTo(tagId);
		
		return productTagRelMapper.selectByExample(example);
	}
}