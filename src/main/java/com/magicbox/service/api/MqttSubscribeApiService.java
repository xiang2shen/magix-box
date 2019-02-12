package com.magicbox.service.api;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.magicbox.base.constants.MqttConstants;
import com.magicbox.base.support.ResponseWrapper;
import com.magicbox.mqtt.MqttClient;
import com.magicbox.mqtt.callback.ClientOfflineCallback;
import com.magicbox.mqtt.callback.ClientOnlineCallback;
import com.magicbox.mqtt.callback.OpenResultCallback;
import com.magicbox.mqtt.callback.PangCallback;
import com.magicbox.mqtt.callback.SynStockCallback;
import com.magicbox.mqtt.callback.UpdateResultCallback;

@Service
public class MqttSubscribeApiService {
	
	@Autowired
	private MqttClient mqttClient;
	
	@Autowired
	private SynStockCallback synStockCallback;
	@Autowired
	private PangCallback pangCallback;
	@Autowired
	private OpenResultCallback openResultCallback;
	@Autowired
	private UpdateResultCallback updateResultCallback;
	@Autowired
	private ClientOnlineCallback clientOnlineCallback;
	@Autowired
	private ClientOfflineCallback clientOfflineCallback;
	
	@PostConstruct
	public ResponseWrapper<?> subscribeSynStock() {
		mqttClient.subscribe(MqttConstants.TOPIC_SYN_STOCK, synStockCallback);
		return ResponseWrapper.succeed();
	}
	
	@PostConstruct
	public ResponseWrapper<?> subscribePang() {
		mqttClient.subscribe(MqttConstants.TOPIC_PANG, pangCallback);
		return ResponseWrapper.succeed();
	}
	
	@PostConstruct
	public ResponseWrapper<?> subscribeOpenResult() {
		mqttClient.subscribe(MqttConstants.TOPIC_OPEN_RESULT, openResultCallback);
		return ResponseWrapper.succeed();
	}
	
	@PostConstruct
	public ResponseWrapper<?> subscribeUpdateResult() {
		mqttClient.subscribe(MqttConstants.TOPIC_UPDATE_RESULT, updateResultCallback);
		return ResponseWrapper.succeed();
	}
	
	@PostConstruct
	public ResponseWrapper<?> subscribeClientOnline() {
		mqttClient.subscribe(MqttConstants.TOPIC_CLIENT_ONLINE, clientOnlineCallback);
		return ResponseWrapper.succeed();
	}
	
	@PostConstruct
	public ResponseWrapper<?> subscribeClientOffline() {
		mqttClient.subscribe(MqttConstants.TOPIC_CLIENT_OFFLINE, clientOfflineCallback);
		return ResponseWrapper.succeed();
	}
}
