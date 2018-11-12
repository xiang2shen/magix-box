package com.magicbox.mqtt;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractMqttCallback implements MqttCallback {
	
	private static final Logger logger = LoggerFactory.getLogger(AbstractMqttCallback.class);
	
	protected static final int SUCCESS_SIGN = 1;

	@Override
	public void connectionLost(Throwable ex) {
		logger.error("mqtt连接断开", ex);
	}

	@Override
	public void deliveryComplete(IMqttDeliveryToken token) {
		// do nothing
	}

	@Override
	public void messageArrived(String topic, MqttMessage message) throws Exception {
		logger.debug("收到mqtt消息，topic={}, msg={}", topic, message.toString());
		callback(topic, message.toString());
	}

	/**
	 * 订阅后，消息回调
	 * 
	 * @param topic
	 * @param message
	 */
	public abstract void callback(String topic, String message);
}
