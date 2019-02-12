package com.magicbox.service.api;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.magicbox.base.constants.MqttConstants;
import com.magicbox.base.constants.OrderStatusEnum;
import com.magicbox.base.constants.PaymentWayEnum;
import com.magicbox.base.exception.ErrorCodes;
import com.magicbox.base.support.Page;
import com.magicbox.base.support.ResponseWrapper;
import com.magicbox.base.utilities.BeanChecker;
import com.magicbox.base.utilities.PageUtils;
import com.magicbox.base.utilities.XBeanUtils;
import com.magicbox.dto.OrderDTO;
import com.magicbox.mapper.MemberMapper;
import com.magicbox.mapper.OrderMapper;
import com.magicbox.model.Box;
import com.magicbox.model.FrameHealthLog;
import com.magicbox.model.Member;
import com.magicbox.model.Order;
import com.magicbox.model.Product;
import com.magicbox.model.ProductImage;
import com.magicbox.model.Seller;
import com.magicbox.model.Shop;
import com.magicbox.mqtt.MqttClient;
import com.magicbox.service.BoxService;
import com.magicbox.service.FrameHealthLogService;
import com.magicbox.service.OrderService;
import com.magicbox.service.ProductImageService;
import com.magicbox.service.ProductService;
import com.magicbox.service.ShopService;

@Service
public class OrderApiService {
	
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(OrderApiService.class);

	@Autowired
	private ProductService productService;
	@Autowired
	private BoxService boxService;
	@Autowired
	private ShopService shopService;
	@Autowired
	private MemberApiService memberApiService;
	@Autowired
	private MemberMapper memberMapper;
	@Autowired
	private OrderMapper orderMapper;
	@Autowired
	private OrderService orderService;
	@Autowired
	private ProductImageService productImageService;
	@Autowired
	private PaymentApiService paymentApiService;
	@Autowired
	private MqttClient mqttClient;
	@Autowired
	private FrameHealthLogService frameHealthLogService;
	

//	public ResponseWrapper<Box> bindBoxWithProduct(Long memberId, String productCode, String boxCode) {
//		BeanChecker.getInstance().notNull(memberId).notBlank(productCode).notBlank(boxCode);
//		
//		// 校验卖家
//		Seller seller = memberApiService.findSellerByMemberId(memberId).getBody();
//		if (null == seller) {
//			return ResponseWrapper.fail(ErrorCodes.NOT_SELLER);
//		}
//		
//		Product product = productService.selectOneByProductCode(productCode);
//		if (null == product) {
//			return ResponseWrapper.fail(ErrorCodes.PRODUCT_NOT_FOUND);
//		}
//		
//		Box box = boxService.selectOneByBoxCode(boxCode);
//		if (null == box) {
//			return ResponseWrapper.fail(ErrorCodes.BOX_NOT_FOUND);
//		}
//		
//		// 校验店铺是否相同
//		Shop shop = shopService.selectOneByShopCode(product.getShopCode());
//		if (null == shop) {
//			return ResponseWrapper.fail(ErrorCodes.SHOP_NOT_FOUND);
//		}
//		if (!shop.getSellerId().equals(seller.getId())) {
//			return ResponseWrapper.fail(ErrorCodes.SHOP_NOT_BELONG_TO_SELLER);
//		}
//		if (!shop.getShopCode().equals(box.getShopCode())) {
//			return ResponseWrapper.fail(ErrorCodes.BOX_NOT_BELONG_TO_SHOP);
//		}
//		
//		boxService.updateProductCodeByBoxCode(productCode, boxCode);
//		
//		return ResponseWrapper.succeed(boxService.selectOneByBoxCode(boxCode));
//	}


	public ResponseWrapper<String> createOrder(Long memberId, String boxCode, Integer quantity, Integer payWay, String clientIP) {
		BeanChecker.getInstance().notNull(memberId).notBlank(boxCode).positive(quantity).enumMap(payWay, PaymentWayEnum.toMap());
		
		Member member = memberMapper.selectByPrimaryKey(memberId);
		if (null == member) {
			return ResponseWrapper.fail(ErrorCodes.MEMBER_NOT_FOUND);
		}
		
		Box box = boxService.selectOneByBoxCode(boxCode);
		if (null == box) {
			return ResponseWrapper.fail(ErrorCodes.BOX_NOT_FOUND);
		}
		
		if (StringUtils.isBlank(box.getProductCode())) {
			return ResponseWrapper.fail(ErrorCodes.BOX_NO_PRODUCT);
		}
		
		Product product = productService.selectOneByProductCode(box.getProductCode());
		if (null == product) {
			return ResponseWrapper.fail(ErrorCodes.PRODUCT_NOT_FOUND);
		}
		List<ProductImage> productImageList = productImageService.selectListByProductCode(box.getProductCode());
		
		Shop shop = shopService.selectOneByShopCode(box.getShopCode());
		if (null == shop) {
			return ResponseWrapper.fail(ErrorCodes.SHOP_NOT_FOUND);
		}
		
		// 设备健康检查
		if (!checkFrameHealth(box)) {
			return ResponseWrapper.fail(ErrorCodes.FRAME_HEALTH_ERROR);
		}
		
		if (box.getProductStock() < quantity) {
			return ResponseWrapper.fail(ErrorCodes.BOX_NO_STOCK);
		}
		
		Date now = new Date();
		String orderCode = "ORD" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		
		Order order = new Order();
		order.setOrderCode(orderCode);
		order.setOrderStatus(OrderStatusEnum.UNPAY.getCode());
		order.setMemberId(memberId);
		order.setMemberOpenId(member.getOpenId());
		order.setSellerId(shop.getSellerId());
		order.setShopCode(shop.getShopCode());
		order.setFrameCode(box.getFrameCode());
		order.setBoxCode(boxCode);
		order.setProductCode(box.getProductCode());
		order.setProductName(product.getProductName());
		order.setProductPrice(product.getProductPrice());
		order.setProductQuantity(quantity);
		order.setProductImg(productImageList.size() > 0 ? productImageList.get(0).getImgUrl() : null);
		
		order.setDueTotal(order.getProductPrice() * order.getProductQuantity());
		order.setRealTotal(order.getProductPrice() * order.getProductQuantity());
		order.setDiscount(order.getDueTotal() - order.getRealTotal());

		order.setPayWay(payWay);
		
		order.setCreateTime(now);
		order.setUpdateTime(now);
		
		String payStr = paymentApiService.pay(order, clientIP).getBody();
		if (StringUtils.isBlank(payStr)) {
			return ResponseWrapper.fail();
		}
		
		orderMapper.insert(order);
		
		return ResponseWrapper.succeed(payStr);
	}

	private boolean checkFrameHealth(Box box) {
		FrameHealthLog frameHealthLog = null;
		int loopTimes = 0;
		do {
			
			frameHealthLog = frameHealthLogService.selectOneByFrameCodeAndRecentSecond(box.getFrameCode(), 60);
			if (null == frameHealthLog) {
				mqttClient.publish(MqttConstants.TOPIC_PING + box.getFrameCode(), box.getBoxCode());
				
				try {
					Thread.sleep(500L);
				} catch (InterruptedException e) {}
			}
		} while (null == frameHealthLog || loopTimes++ > 10);
		
		return null != frameHealthLog;
	}
	
	public ResponseWrapper<?> doAfterPay(String orderCode, String payCode) {
		BeanChecker.getInstance().notBlank(orderCode);
		
		Order order = orderService.selectOneByOrderCode(orderCode);
		if (null == order) {
			return ResponseWrapper.fail(ErrorCodes.ORDER_NOT_FOUND);
		}
		
		if (OrderStatusEnum.UNPAY.getCode().equals(order.getOrderStatus())) {
			orderService.updateStatusByOrderCode(orderCode, payCode, OrderStatusEnum.UNPAY, OrderStatusEnum.PAY);
		}
		
		boxService.minusStockByBoxCode(order.getBoxCode(), order.getProductQuantity());
		
		String msgContent = order.getOrderCode() + "|" + order.getBoxCode() + "|" + order.getProductQuantity();
		mqttClient.publish(MqttConstants.TOPIC_OPEN_AFTER_PAY + order.getFrameCode(), msgContent);
		
		return ResponseWrapper.succeed();
	}


	public ResponseWrapper<OrderDTO> findOrderByOrderCodeFromBuyer(Long memberId, String orderCode) {
		BeanChecker.getInstance().notNull(memberId).notBlank(orderCode);
		
		Member member = memberMapper.selectByPrimaryKey(memberId);
		if (null == member) {
			return ResponseWrapper.fail(ErrorCodes.MEMBER_NOT_FOUND);
		}
		
		Order order = orderService.selectOneByOrderCode(orderCode);
		if (null == order || !order.getMemberId().equals(member.getId())) {
			return ResponseWrapper.fail(ErrorCodes.ORDER_NOT_FOUND);
		}
		
		Shop shop = shopService.selectOneByShopCode(order.getShopCode());
		
		OrderDTO orderDTO =	new OrderDTO();
		BeanUtils.copyProperties(order, orderDTO);
		
		if (null != shop) {
			orderDTO.setShopName(shop.getShopName());
		}
		
		return ResponseWrapper.succeed(orderDTO);
	}


	public ResponseWrapper<OrderDTO> findOrderByOrderCodeFromSeller(Long memberId, String orderCode) {
		BeanChecker.getInstance().notNull(memberId).notBlank(orderCode);
		
		// 校验卖家
		Seller seller = memberApiService.findSellerByMemberId(memberId).getBody();
		if (null == seller) {
			return ResponseWrapper.fail(ErrorCodes.NOT_SELLER);
		}
		
		Order order = orderService.selectOneByOrderCode(orderCode);
		if (null == order || !order.getSellerId().equals(seller.getId())) {
			return ResponseWrapper.fail(ErrorCodes.ORDER_NOT_FOUND);
		}
		
		Shop shop = shopService.selectOneByShopCode(order.getShopCode());
		
		OrderDTO orderDTO =	new OrderDTO();
		BeanUtils.copyProperties(order, orderDTO);
		
		if (null != shop) {
			orderDTO.setShopName(shop.getShopName());
		}
		
		return ResponseWrapper.succeed(orderDTO);
	}

	public ResponseWrapper<Page<OrderDTO>> findOrderPageFromBuyer(Long memberId, Integer pageNo, Integer pageSize) {
		BeanChecker.getInstance().notNull(memberId).page(pageNo, pageSize);
		
		Page<Order> page = orderService.selectPageByMemberId(memberId, pageNo, pageSize);
		if (PageUtils.isEmpty(page)) {
			return ResponseWrapper.succeed(PageUtils.emptyPage(pageNo, pageSize));
		}
		
		List<String> shopCodeList = XBeanUtils.extractField(page.getItems(), String.class, "shopCode");
		List<Shop> shopList = shopService.selectListByShopCodeList(shopCodeList);
		Map<String, Shop> shopMap = XBeanUtils.listToMap(shopList, String.class, "shopCode");
		
		Page<OrderDTO> orderDTOPage = PageUtils.copy(page, OrderDTO.class);
		for (OrderDTO orderDTO : orderDTOPage) {
			Shop shop = shopMap.get(orderDTO.getShopCode());
			if (null != shop) {
				orderDTO.setShopName(shop.getShopName());
			}
		}
		
		return ResponseWrapper.succeed(orderDTOPage);
	}


	public ResponseWrapper<Page<OrderDTO>> findOrderPageFromSeller(Long memberId, Integer pageNo, Integer pageSize) {
		BeanChecker.getInstance().notNull(memberId).page(pageNo, pageSize);
		
		// 校验卖家
		ResponseWrapper<List<Shop>> authResp = memberApiService.getShopListBySellerOrShopAssistantAuth(memberId);
		if (authResp.isFailed()) {
			return ResponseWrapper.fail(authResp.getHead());
		}
		List<Shop> shops = authResp.getBody();
		List<String> shopCodes = shops.stream().map(Shop::getShopCode).collect(Collectors.toList());
		
		Page<Order> page = orderService.selectPageByShopCodes(shopCodes, pageNo, pageSize);
		if (PageUtils.isEmpty(page)) {
			return ResponseWrapper.succeed(PageUtils.emptyPage(pageNo, pageSize));
		}
		
		Map<String, Shop> shopMap = XBeanUtils.listToMap(shops, String.class, "shopCode");
		
		Page<OrderDTO> orderDTOPage = PageUtils.copy(page, OrderDTO.class);
		for (OrderDTO orderDTO : orderDTOPage) {
			Shop shop = shopMap.get(orderDTO.getShopCode());
			if (null != shop) {
				orderDTO.setShopName(shop.getShopName());
			}
		}
		
		return ResponseWrapper.succeed(orderDTOPage);
	}

}
