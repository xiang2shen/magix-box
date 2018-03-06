package com.magicbox.mqtt.callback;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.magicbox.base.utilities.CsvUtils;
import com.magicbox.model.Box;
import com.magicbox.model.Frame;
import com.magicbox.mqtt.AbstractMqttCallback;
import com.magicbox.service.api.BoxApiService;
import com.magicbox.service.api.FrameApiService;

@Component
public class SynStockCallback extends AbstractMqttCallback {
	
	@Autowired
	private FrameApiService frameApiService;
	@Autowired
	private BoxApiService boxApiService;

	@Override
	@SuppressWarnings("unused")
	public void callback(String topic, String message) {
		List<String> strList = CsvUtils.parse(message, "|");
		if (strList.isEmpty()) {
			return;
		}
		
		String frameCode = strList.get(0);
		Frame frame = frameApiService.createFrameWhenAbsent(frameCode).getBody();
		
		for (int i = 1; i < strList.size(); i+=2) {
			String boxCode = strList.get(i);
			int stock = Integer.parseInt(strList.get(i + 1));
			
			Box box = boxApiService.createOrUpdateBox(frameCode, boxCode, stock).getBody();
		}
		
	}
}
