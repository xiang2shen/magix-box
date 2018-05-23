package com.magicbox.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.magicbox.base.support.ResponseWrapper;
import com.magicbox.mqtt.MqttClient;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("MQTT测试API")
@RestController
@RequestMapping("/mqtt")
public class MqttTestController extends BaseController {

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(MqttTestController.class);
	
	@Autowired
	private MqttClient mqttClient;
	
	@ApiOperation("发送MQTT消息")
	@PostMapping("/sendMsg")
	public ResponseWrapper<Boolean> sendMsg(
			@RequestParam String topic,
			@RequestParam String content
			) {
		
		boolean rs = mqttClient.publish(topic, content);
		return ResponseWrapper.succeed(rs);
	}
	
}
