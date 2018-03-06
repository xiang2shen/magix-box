package com.magicbox.mqtt;

public interface MqttClient {

	boolean publish(String topic, String content);

	boolean publish(String topic, String content, int qos);

	boolean subcribe(String topic, AbstractMqttCallback callback);

}
