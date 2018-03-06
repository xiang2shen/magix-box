package com.magicbox.mqtt.callback;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.magicbox.base.utilities.CsvUtils;
import com.magicbox.mapper.FrameHealthLogMapper;
import com.magicbox.model.FrameHealthLog;
import com.magicbox.mqtt.AbstractMqttCallback;

@Component
public class PangCallback extends AbstractMqttCallback {
	
	@Autowired
	private SynStockCallback synStockCallback;
	@Autowired
	private FrameHealthLogMapper frameHealthLogMapper;
	
	@Override
	public void callback(String topic, String message) {
		synStockCallback.callback(topic, message);
		
		List<String> strList = CsvUtils.parse(message, "|");
		if (strList.isEmpty()) {
			return;
		}
		
		String frameCode = strList.get(0);
		
		Date now = new Date();
		FrameHealthLog healthLog = new FrameHealthLog();
		healthLog.setFrameCode(frameCode);
		healthLog.setLogContent("设备健康检查,message=" + message);
		healthLog.setCreateTime(now);
		healthLog.setUpdateTime(now);
		frameHealthLogMapper.insert(healthLog);
		
	}

}
