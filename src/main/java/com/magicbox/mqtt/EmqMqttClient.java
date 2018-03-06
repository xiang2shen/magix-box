package com.magicbox.mqtt;

import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmqMqttClient implements MqttClient {

	private static final Logger logger = LoggerFactory.getLogger(EmqMqttClient.class);
	
	private static final int DEFAULT_QOS = 2;
	
	private org.eclipse.paho.client.mqttv3.MqttClient client;
	
	public void init(String broker, String clientId, String username, String password) {
		try {
			client = new org.eclipse.paho.client.mqttv3.MqttClient(broker, clientId, new MemoryPersistence());
			MqttConnectOptions options = new MqttConnectOptions();
			options.setUserName(username);
			options.setPassword(password.toCharArray());
			options.setCleanSession(false);
			client.connect(options);
		} catch (MqttException e) {
			logger.error("连接EMQ服务器失败", e);
			throw new RuntimeException("连接EMQ服务器失败");
		}
	}
	
	@Override
	public boolean publish(String topic, String content) {
		return publish(topic, content, DEFAULT_QOS);
	}
	
	@Override
	public boolean publish(String topic, String content, int qos) {
		try {
			MqttMessage message = new MqttMessage(content.getBytes());
			message.setQos(qos);
			client.publish(topic, message);
			return true;
		} catch (MqttException e) {
			logger.error("发送消息[{}]到主题[{}]失败", content, topic, e);
			return false;
		}
	}
	
	@Override
	public boolean subcribe(String topic, AbstractMqttCallback callback) {
		try {
			client.subscribe(topic);
			client.setCallback(callback);
			return true;
		} catch (MqttException e) {
			logger.error("订阅主题[{}]失败", topic, e);
			return false;
		}
	}
	
}
