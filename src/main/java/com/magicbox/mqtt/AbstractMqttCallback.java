package com.magicbox.mqtt;

import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractMqttCallback implements IMqttMessageListener {
	
	private static final Logger logger = LoggerFactory.getLogger(AbstractMqttCallback.class);
	
	protected static final int SUCCESS_SIGN = 1;

	@Override
	public void messageArrived(String topic, MqttMessage message) throws Exception {
		logger.debug("收到mqtt消息，topic={}, msg={}", topic, message.toString());
		try {
			callback(topic, message.toString());
		} catch (Exception e) {
			logger.error("消费mqtt消息异常,topic={},message={}", topic, message, e);
		}
	}

	/**
	 * 订阅后，消息回调
	 * 
	 * @param topic
	 * @param message
	 */
	public abstract void callback(String topic, String message);
}
