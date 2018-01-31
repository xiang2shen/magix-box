package com.magicbox.service;

import java.util.Collections;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.magicbox.base.support.Page;
import com.magicbox.mapper.ProductImageMapper;
import com.magicbox.model.ProductImage;
import com.magicbox.model.ProductImageExample;

@Service
public class ProductImageService {
	
	@Autowired
    private ProductImageMapper productImageMapper;

    protected Page<ProductImage> selectPageByExample(ProductImageExample example) {
        return new Page<>(example.getPageNo(), example.getLimit(), productImageMapper.selectByExample(example), productImageMapper.countByExample(example));
    }

	public List<ProductImage> selectListByProductCode(String productCode) {
		if (StringUtils.isBlank(productCode)) {
			return Collections.emptyList();
		}
		
		ProductImageExample example = new ProductImageExample();
		example.or().andProductCodeEqualTo(productCode);
		example.setOrderByClause("sort asc");
		
		return productImageMapper.selectByExample(example);
	}

	public void deleteByProductCode(String productCode) {
		if (StringUtils.isNotBlank(productCode)) {
			
			ProductImageExample example = new ProductImageExample();
			example.or().andProductCodeEqualTo(productCode);
			
			productImageMapper.deleteByExample(example);
		}
	}

	public List<ProductImage> selectListByProductCodeList(List<String> productCodeList) {
		if (CollectionUtils.isEmpty(productCodeList)) {
			return Collections.emptyList();
		}
		
		ProductImageExample example = new ProductImageExample();
		example.or().andProductCodeIn(productCodeList);
		example.setOrderByClause("sort asc");
		
		return productImageMapper.selectByExample(example);
	}
	
}