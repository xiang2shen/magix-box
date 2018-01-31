package com.magicbox.service.api;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.magicbox.base.support.ResponseWrapper;
import com.magicbox.base.utilities.BeanChecker;
import com.magicbox.mapper.MemberMapper;
import com.magicbox.mapper.SellerMapper;
import com.magicbox.model.Member;
import com.magicbox.model.Seller;
import com.magicbox.service.MemberService;
import com.magicbox.service.SellerService;

@Service
public class MemberApiService {
	
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(MemberApiService.class);

	@Autowired
	private MemberService memberService;
	@Autowired
    private MemberMapper memberMapper;
	@Autowired
	private SellerService sellerService;
	@Autowired
	private SellerMapper sellerMapper;
	
	private static final String ALIPAY_PREFIX = "al_";
	private static final String WEIXIN_PREFIX = "wx_";
	
	public ResponseWrapper<Member> joinOpenId(String openId) {
		BeanChecker.getInstance().notBlank(openId);
		
		Member member = memberService.selectOneByOpenId(openId);
		
		Date now = new Date();
		
		if (null == member) {
			member = new Member();
			member.setOpenId(openId);
			member.setRegisterTime(now);
			member.setCreateTime(now);
			member.setLastLoginTime(now);
			member.setUpdateTime(now);
			
			memberMapper.insert(member);
		}
		
		return ResponseWrapper.succeed(member);
	}
	
	public String getRealOpenId(String openId) {
		BeanChecker.getInstance().notBlank(openId);
		
		if (openId.startsWith(ALIPAY_PREFIX)) {
			return openId.substring(ALIPAY_PREFIX.length());
		} else if (openId.startsWith(WEIXIN_PREFIX)) {
			return openId.substring(WEIXIN_PREFIX.length());
		}
		
		throw new IllegalArgumentException("无效的openId");
	}
	
	public boolean isAlipayOpenId(String openId) {
		BeanChecker.getInstance().notBlank(openId);
		
		if (openId.startsWith(ALIPAY_PREFIX)) {
			return true;
		} else if (openId.startsWith(WEIXIN_PREFIX)) {
			return false;
		}
		
		throw new IllegalArgumentException("无效的openId");
	}
	
	public boolean isWeixinOpenId(String openId) {
		BeanChecker.getInstance().notBlank(openId);
		
		if (openId.startsWith(ALIPAY_PREFIX)) {
			return false;
		} else if (openId.startsWith(WEIXIN_PREFIX)) {
			return true;
		}
		
		throw new IllegalArgumentException("无效的openId");
	}
	
	public ResponseWrapper<Member> joinSellerWithMobile(String mobile, String openId) {
		BeanChecker.getInstance().mobile(mobile).notBlank(openId);
		
		Member member = memberService.selectOneByOpenId(openId);
		if (null == member) {
			member = joinOpenId(openId).getBody();
		}
		
		Seller seller = sellerService.selectOneByMobile(mobile);
		if (null == seller) {
			Date now = new Date();
			seller = new Seller();
			seller.setSellerMobile(mobile);
			seller.setSellerStatus(1);
			seller.setCreateTime(now);
			seller.setUpdateTime(now);
			
			sellerMapper.insert(seller);
		}
		
		if (! seller.getId().equals(member.getSellerId())) {	// 重新绑定卖家ID
			memberService.updateSellerId(member.getId(), seller.getId());
		}
		
		return ResponseWrapper.succeed(member);
	}
	
	public ResponseWrapper<Boolean> isSeller(Long memberId) {
		BeanChecker.getInstance().notNull(memberId);
		
		Member member = memberMapper.selectByPrimaryKey(memberId);
		if (null != member && null != member.getSellerId()) {
			return ResponseWrapper.succeed(Boolean.TRUE);
		}
		return ResponseWrapper.succeed(Boolean.FALSE);
	}

	public ResponseWrapper<Seller> findSellerByMemberId(Long memberId) {
		BeanChecker.getInstance().notNull(memberId);
		
		Member member = memberMapper.selectByPrimaryKey(memberId);
		if (null == member || null == member.getSellerId()) {
			return ResponseWrapper.succeed();
		}
		
		Seller seller = sellerMapper.selectByPrimaryKey(member.getSellerId());
		
		return ResponseWrapper.succeed(seller);
	}

}
