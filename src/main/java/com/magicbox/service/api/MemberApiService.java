package com.magicbox.service.api;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.magicbox.base.constants.ShopAssistantStatusEnum;
import com.magicbox.base.exception.ErrorCodes;
import com.magicbox.base.support.ResponseWrapper;
import com.magicbox.base.utilities.BeanChecker;
import com.magicbox.mapper.MemberMapper;
import com.magicbox.mapper.SellerMapper;
import com.magicbox.mapper.ShopAssistantMapper;
import com.magicbox.mapper.ShopMapper;
import com.magicbox.model.Member;
import com.magicbox.model.Seller;
import com.magicbox.model.Shop;
import com.magicbox.model.ShopAssistant;
import com.magicbox.service.MemberService;
import com.magicbox.service.SellerService;
import com.magicbox.service.ShopAssistantService;
import com.magicbox.service.ShopService;

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
	@Autowired
	private ShopAssistantService shopAssistantService;
	@Autowired
	private ShopAssistantMapper shopAssistantMapper;
	@Autowired
	private ShopMapper shopMapper;
	@Autowired
	private ShopService shopService;
	
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
			return ResponseWrapper.fail(ErrorCodes.NOT_SELLER);
		}
		
		Seller seller = sellerMapper.selectByPrimaryKey(member.getSellerId());
		
		return ResponseWrapper.succeed(seller);
	}

	public ResponseWrapper<Member> joinShopAssistantWithMobile(String mobile, String openId, Long shopId) {
		BeanChecker.getInstance().mobile(mobile).notBlank(openId).notNull(shopId);
		
		Member member = memberService.selectOneByOpenId(openId);
		if (null == member) {
			member = joinOpenId(openId).getBody();
		}
		
		Shop shop = shopMapper.selectByPrimaryKey(shopId);
		if (null == shop) {
			return ResponseWrapper.fail(ErrorCodes.SHOP_NOT_FOUND);
		}
		
		ShopAssistant assistant = shopAssistantService.selectOneByMobile(mobile);
		if (null == assistant) {
			assistant = new ShopAssistant();
			assistant.setAssistantMobile(mobile);
			assistant.setShopId(shopId);
			assistant.setSellerId(shop.getSellerId());
			assistant.setAssistantStatus(ShopAssistantStatusEnum.UNCHECK.getCode());
			
			shopAssistantMapper.insertSelective(assistant);
		}
		
		if (! assistant.getId().equals(member.getShopAssistantId())) {	// 重新绑定店员ID
			memberService.updateShopAssistantId(member.getId(), assistant.getId());
		}
		
		return ResponseWrapper.succeed(member);
	}

	public ResponseWrapper<Boolean> isShopAssistant(Long memberId) {
		BeanChecker.getInstance().notNull(memberId);
		
		Member member = memberMapper.selectByPrimaryKey(memberId);
		if (null != member && null != member.getShopAssistantId()) {
			return ResponseWrapper.succeed(Boolean.TRUE);
		}
		return ResponseWrapper.succeed(Boolean.FALSE);
	}

	public ResponseWrapper<List<Shop>> getShopListBySellerOrShopAssistantAuth(Long memberId) {
		BeanChecker.getInstance().notNull(memberId);
		
		Member member = memberMapper.selectByPrimaryKey(memberId);
		if (null == member) {
			return ResponseWrapper.fail(ErrorCodes.MEMBER_NOT_FOUND);
		}
		
		if (null != member.getSellerId()) {
			Seller seller = sellerMapper.selectByPrimaryKey(member.getSellerId());
			if (null != seller) {
				List<Shop> shops = shopService.selectListBySellerId(seller.getId());
				return ResponseWrapper.succeed(shops);
			}
		}
		
		if (null != member.getShopAssistantId()) {
			ShopAssistant assistant = shopAssistantMapper.selectByPrimaryKey(member.getShopAssistantId());
			if (null != assistant) {
				Shop shop = shopMapper.selectByPrimaryKey(assistant.getShopId());
				List<Shop> shops = new ArrayList<>(1);
				shops.add(shop);
				return ResponseWrapper.succeed(shops);
			}
		}
		
		return ResponseWrapper.succeed(Collections.emptyList());
	}

}
