package com.magicbox.mqtt.callback;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.magicbox.base.utilities.CsvUtils;
import com.magicbox.mqtt.AbstractMqttCallback;
import com.magicbox.service.api.BoxApiService;
import com.magicbox.service.api.FrameApiService;

import lombok.extern.slf4j.Slf4j;

/**
 * 同步库存消息回调
 * 
 * @author xiangshuo
 *
 */
@Component
@Slf4j
public class SynStockCallback extends AbstractMqttCallback {
	
	@Autowired
	private FrameApiService frameApiService;
	@Autowired
	private BoxApiService boxApiService;
	
	@Override
	public void callback(String topic, String message) {
		log.debug("mqtt消息到达,topic={}", topic);
		
		List<String> params = CsvUtils.parse(message, "|");
		if (params.isEmpty()) {
			return;
		}
		String frameCode = params.get(0);
		frameApiService.createFrameWhenAbsent(frameCode).getBody();
		
		for (int i = 1; i < params.size(); i+=4) {
			String boxCode = params.get(i);
			Integer boxPosition = Integer.parseInt(params.get(i + 1));
			Integer capacity = Integer.parseInt(params.get(i + 2));
			Integer stock = Integer.parseInt(params.get(i + 3));
			
			boxApiService.createOrUpdateBox(frameCode, boxCode, boxPosition, capacity, stock).getBody();
		}
	}

}
