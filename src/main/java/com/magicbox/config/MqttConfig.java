package com.magicbox.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.magicbox.mqtt.EmqMqttClient;
import com.magicbox.mqtt.MqttClient;


@Configuration
public class MqttConfig {
	
	private static final String BROKER_URL = "tcp://39.104.76.187:1883";
	private static final String CLIENT_ID = "hequ-app1";
	private static final String USERNAME = "hequ";
	private static final String PASSWORD = "fedF451HIKK3etnwv5GEAHRE";
	
	@Bean
	public MqttClient mqttClient() {
		EmqMqttClient client = new EmqMqttClient();
		client.init(BROKER_URL, CLIENT_ID, USERNAME, PASSWORD);
		return client;
	}
	
}
