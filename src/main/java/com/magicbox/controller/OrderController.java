package com.magicbox.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.magicbox.base.exception.ErrorCodes;
import com.magicbox.base.support.Page;
import com.magicbox.base.support.ResponseWrapper;
import com.magicbox.base.utilities.IPUtils;
import com.magicbox.dto.OrderDTO;
import com.magicbox.service.api.OrderApiService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("订单API")
@RestController
@RequestMapping("/order")
public class OrderController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
	
	@Autowired
	private OrderApiService orderApiService;
	
	@ApiOperation("下单")
	@PostMapping("/createOrder")
	public ResponseWrapper<String> createOrder(
			@RequestParam String token,
			@RequestParam String boxCode,
			@RequestParam Integer payWay,
			HttpServletRequest request
			) {
		
		Long memberId = getMemberId(token);
		
		if (null == memberId) {
			return ResponseWrapper.fail(ErrorCodes.INVALID_TOKEN);
		}
		
		String clientIP = IPUtils.getIp(request);
		logger.info("下单客户端IP={}", clientIP);
		
		return orderApiService.createOrder(memberId, boxCode, payWay, clientIP);
	}
	
	@ApiOperation("买家根据订单编号查询订单")
	@PostMapping("/findOrderByOrderCodeFromBuyer")
	public ResponseWrapper<OrderDTO> findOrderByOrderCodeFromBuyer(
			@RequestParam String token,
			@RequestParam String orderCode
			) {
		
		Long memberId = getMemberId(token);
		
		if (null == memberId) {
			return ResponseWrapper.fail(ErrorCodes.INVALID_TOKEN);
		}
		
		
		return orderApiService.findOrderByOrderCodeFromBuyer(memberId, orderCode);
	}
	
	@ApiOperation("卖家根据订单编号查询订单")
	@PostMapping("/findOrderByOrderCodeFromSeller")
	public ResponseWrapper<OrderDTO> findOrderByOrderCodeFromSeller(
			@RequestParam String token,
			@RequestParam String orderCode
			) {
		
		Long memberId = getMemberId(token);
		
		if (null == memberId) {
			return ResponseWrapper.fail(ErrorCodes.INVALID_TOKEN);
		}
		
		
		return orderApiService.findOrderByOrderCodeFromSeller(memberId, orderCode);
	}
	
	@ApiOperation("买家查询订单列表")
	@PostMapping("/findOrderPageFromBuyer")
	public ResponseWrapper<Page<OrderDTO>> findOrderPageFromBuyer(
			@RequestParam String token,
			@RequestParam Integer pageNo,
			@RequestParam Integer pageSize
			) {
		
		Long memberId = getMemberId(token);
		
		if (null == memberId) {
			return ResponseWrapper.fail(ErrorCodes.INVALID_TOKEN);
		}
		
		return orderApiService.findOrderPageFromBuyer(memberId, pageNo, pageSize);
	}
	
	@ApiOperation("卖家查询订单列表")
	@PostMapping("/findOrderPageFromSeller")
	public ResponseWrapper<Page<OrderDTO>> findOrderPageFromSeller(
			@RequestParam String token,
			@RequestParam Integer pageNo,
			@RequestParam Integer pageSize
			) {
		
		Long memberId = getMemberId(token);
		
		if (null == memberId) {
			return ResponseWrapper.fail(ErrorCodes.INVALID_TOKEN);
		}
		
		return orderApiService.findOrderPageFromSeller(memberId, pageNo, pageSize);
	}
	
}
