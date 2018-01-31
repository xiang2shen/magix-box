package com.magicbox.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.magicbox.base.support.Page;
import com.magicbox.base.utilities.XCollectionUtils;
import com.magicbox.mapper.MemberMapper;
import com.magicbox.model.Member;
import com.magicbox.model.MemberExample;

@Service
public class MemberService {
	
	@Autowired
    private MemberMapper memberMapper;

    protected Page<Member> selectPageByExample(MemberExample example) {
        return new Page<>(example.getPageNo(), example.getLimit(), memberMapper.selectByExample(example), memberMapper.countByExample(example));
    }

	public void updateOpenId(Long memberId, String openId) {
		if (null != memberId && StringUtils.isNotBlank(openId)) {
			
			Member record = new Member();
			record.setId(memberId);
			record.setOpenId(openId);
			
			memberMapper.updateByPrimaryKeySelective(record);
			
		}
	}
	
	public Member selectOneByOpenId(String openId) {
		if (StringUtils.isBlank(openId)) {
			return null;
		}
		
		MemberExample example = new MemberExample();
		example.or().andOpenIdEqualTo(openId);
		
		return XCollectionUtils.getFirstElement(memberMapper.selectByExample(example));
	}

	public void updateSellerId(Long memberId, Long sellerId) {
		if (null == memberId || null == sellerId) {
			return;
		}
		
		Member record = new Member();
		record.setId(memberId);
		record.setSellerId(sellerId);
		
		memberMapper.updateByPrimaryKeySelective(record);
	}

}