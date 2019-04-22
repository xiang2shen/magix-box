package com.magicbox.mqtt.callback;

import java.util.List;

import com.magicbox.base.constants.OrderStatusEnum;
import com.magicbox.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.magicbox.base.utilities.CsvUtils;
import com.magicbox.mqtt.AbstractMqttCallback;
import com.magicbox.service.BoxService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class OpenResultCallback extends AbstractMqttCallback {
	
	@Autowired
	private BoxService boxService;
	@Autowired
	private OrderService orderService;

	@Override
	public void callback(String topic, String message) {
		log.debug("mqtt消息到达,topic={}", topic);
		
		List<String> params = CsvUtils.parse(message, "|");
		if (params.isEmpty()) {
			return;
		}
		
		String frameCode = params.get(0);
		String orderCode = params.get(1);
		Integer sign = Integer.parseInt(params.get(2));
		String boxCode = params.get(3);
		Integer stock = Integer.parseInt(params.get(4));

		if (SUCCESS_SIGN != sign) {
			log.error("货架[{}]上的盒子[{}]下单开锁失败,sign={}, orderCode={}", frameCode, boxCode, sign, orderCode);
			return;
		}

		orderService.updateStatusByOrderCode(orderCode, null, OrderStatusEnum.PAY, OrderStatusEnum.DONE);
		boxService.updateStockByBoxCode(boxCode, stock);
	}

}
