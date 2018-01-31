package com.magicbox.service;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.magicbox.base.constants.OrderStatusEnum;
import com.magicbox.base.support.Page;
import com.magicbox.base.utilities.PageUtils;
import com.magicbox.base.utilities.XCollectionUtils;
import com.magicbox.mapper.OrderMapper;
import com.magicbox.model.Order;
import com.magicbox.model.OrderExample;

@Service
public class OrderService {
	
    @Autowired
    private OrderMapper orderMapper;

    protected Page<Order> selectPageByExample(OrderExample example) {
        return new Page<>(example.getPageNo(), example.getLimit(), orderMapper.selectByExample(example), orderMapper.countByExample(example));
    }

	public Order selectOneByOrderCode(String orderCode) {
		if (StringUtils.isBlank(orderCode)) {
			return null;
		}
		
		OrderExample example = new OrderExample();
		example.or().andOrderCodeEqualTo(orderCode);
		
		return XCollectionUtils.getFirstElement(orderMapper.selectByExample(example));
	}

	public void updateStatusByOrderCode(String orderCode, String bizCode, OrderStatusEnum preStatusEnum, OrderStatusEnum curStatusEnum) {
		if (StringUtils.isBlank(orderCode) || null == preStatusEnum || null == curStatusEnum) {
			return;
		}
		
		OrderExample example = new OrderExample();
		example.or().andOrderCodeEqualTo(orderCode).andOrderStatusEqualTo(preStatusEnum.getCode());
		
		Order record = new Order();
		record.setOrderStatus(curStatusEnum.getCode());
		if (OrderStatusEnum.PAY.equals(curStatusEnum)) {
			record.setPayTime(new Date());
			record.setPayCode(bizCode);
		}
		
		orderMapper.updateByExampleSelective(record, example);
	}

	public Page<Order> selectPageByMemberId(Long memberId, Integer pageNo, Integer pageSize) {
		if (null == memberId) {
			return PageUtils.emptyPage(pageNo, pageSize);
		}
		
		OrderExample example = new OrderExample();
		example.initPage(pageNo, pageSize);
		example.setOrderByClause("id desc");
		example.or().andMemberIdEqualTo(memberId);
		
		return selectPageByExample(example);
	}

	public Page<Order> selectPageBySellerId(Long sellerId, Integer pageNo, Integer pageSize) {
		if (null == sellerId) {
			return PageUtils.emptyPage(pageNo, pageSize);
		}
		
		OrderExample example = new OrderExample();
		example.initPage(pageNo, pageSize);
		example.setOrderByClause("id desc");
		example.or().andSellerIdEqualTo(sellerId);
		
		return selectPageByExample(example);
	}

}