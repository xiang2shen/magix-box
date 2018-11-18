package com.magicbox.mqtt.callback;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.magicbox.base.utilities.CsvUtils;
import com.magicbox.mqtt.AbstractMqttCallback;
import com.magicbox.service.BoxService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class UpdateResultCallback extends AbstractMqttCallback {
	
	@Autowired
	private BoxService boxService;
	
	@Override
	public void callback(String topic, String message) {
		log.debug("mqtt消息到达,topic={}", topic);
		
		List<String> params = CsvUtils.parse(message, "|");
		if (params.isEmpty()) {
			return;
		}
		
		String frameCode = params.get(0);
		Integer sign = Integer.parseInt(params.get(1));
		String boxCode = params.get(2);
		Integer stock = Integer.parseInt(params.get(3));
		
		boxService.updateStockByBoxCode(boxCode, stock);
		
		if (SUCCESS_SIGN != sign) {
			log.error("货架[{}]上的盒子[{}]更新库存开锁失败,sign={}", frameCode, boxCode, sign);
		}
	}

}
