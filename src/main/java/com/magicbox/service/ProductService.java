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
import com.magicbox.mapper.ProductMapper;
import com.magicbox.model.Product;
import com.magicbox.model.ProductExample;

@Service
public class ProductService {
	
	@Autowired
    private ProductMapper productMapper;

    protected Page<Product> selectPageByExample(ProductExample example) {
        return new Page<>(example.getPageNo(), example.getLimit(), productMapper.selectByExample(example), productMapper.countByExample(example));
    }

	public Product selectOneByProductCode(String productCode) {
		if (StringUtils.isBlank(productCode)) {
			return null;
		}
		
		ProductExample example = new ProductExample();
		example.or().andProductCodeEqualTo(productCode);
		
		return XCollectionUtils.getFirstElement(productMapper.selectByExample(example));
	}

	public Page<Product> selectPageByShopCode(String shopCode, Integer pageNo, Integer pageSize) {
		if (StringUtils.isBlank(shopCode)) {
			return PageUtils.emptyPage(pageNo, pageSize);
		}
		
		ProductExample example = new ProductExample();
		example.initPage(pageNo, pageSize);
		example.setOrderByClause("id desc");
		
		example.or().andShopCodeEqualTo(shopCode);
		
		return selectPageByExample(example);
	}

	public Page<Product> selectPageConditionally(String productName, String shopCode, List<String> productCodeList,
			Integer pageNo, Integer pageSize) {
		ProductExample example = new ProductExample();
		example.initPage(pageNo, pageSize);
		example.setOrderByClause("id desc");
		
		ProductExample.Criteria criteria = example.or();
		if (StringUtils.isNotBlank(productName)) {
			criteria.andProductNameLike("%" + productName + "%");
		}
		if (StringUtils.isNotBlank(shopCode)) {
			criteria.andShopCodeEqualTo(shopCode);
		}
		if (CollectionUtils.isNotEmpty(productCodeList)) {
			criteria.andProductCodeIn(productCodeList);
		}
		
		return selectPageByExample(example);
	}

	public List<Product> selectListByShopCodeList(List<String> shopCodeList) {
		if (CollectionUtils.isEmpty(shopCodeList)) {
			return Collections.emptyList();
		}
		
		ProductExample example = new ProductExample();
		example.setOrderByClause("id desc");
		example.or().andShopCodeIn(shopCodeList);
		
		return productMapper.selectByExample(example);
	}
	
}