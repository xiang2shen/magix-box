package com.magicbox.mqtt.callback;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.magicbox.base.constants.FrameStatusEnum;
import com.magicbox.base.utilities.JsonUtils;
import com.magicbox.model.Frame;
import com.magicbox.mqtt.AbstractMqttCallback;
import com.magicbox.service.FrameService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ClientOfflineCallback extends AbstractMqttCallback {
	
	private static final String CLIENT_ID = "clientid";
	
	@Autowired
	private FrameService frameService;
	
	@Override
	public void callback(String topic, String message) {
		log.debug("mqtt消息到达,topic={}", topic);
		
		if (StringUtils.isNotBlank(message) && message.contains(CLIENT_ID)) {
			
			@SuppressWarnings("unchecked")
			Map<String, Object> map = JsonUtils.parseObject(message, Map.class);
			if (null != map) {
				String frameCode = (String) map.get(CLIENT_ID);
				
				Frame frame = frameService.selectOneByFrameCode(frameCode);
				if (null != frame && FrameStatusEnum.ONLINE.getCode().equals(frame.getFrameStatus())) {
					
					log.info("clientId={}已下线", frameCode);
					frameService.updateFrameStatus(frame.getId(), FrameStatusEnum.OFFLINE);
				}
			}
		}
	}

}
