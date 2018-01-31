package com.magicbox.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.magicbox.base.support.Page;
import com.magicbox.base.utilities.XCollectionUtils;
import com.magicbox.mapper.SellerMapper;
import com.magicbox.model.Seller;
import com.magicbox.model.SellerExample;

@Service
public class SellerService {
	
	@Autowired
    private SellerMapper sellerMapper;

    protected Page<Seller> selectPageByExample(SellerExample example) {
        return new Page<>(example.getPageNo(), example.getLimit(), sellerMapper.selectByExample(example), sellerMapper.countByExample(example));
    }

	public Seller selectOneByMobile(String mobile) {
		if (StringUtils.isBlank(mobile)) {
			return null;
		}
		
		SellerExample example = new SellerExample();
		example.or().andSellerMobileEqualTo(mobile);
		
		return XCollectionUtils.getFirstElement(sellerMapper.selectByExample(example));
	}
	
}