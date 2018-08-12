package com.magicbox.mqtt;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.junit.Test;

public class MQTTTest {

	String topic        = "queryStock/123";
	String content      = "ORD20180115161304|BOX00001";
	int qos             = 2;
	String broker       = "tcp://39.104.76.187:1883";
	String clientId     = "xiang1121";
	String username     = "hequ";
	String password     = "fedF451HIKK3etnwv5GEAHRE";
	
	@Test
	public void subcribe() throws InterruptedException, MqttException {
        MqttClient c = new MqttClient(broker, "xiang-sub", new MemoryPersistence());
        MqttConnectOptions co = new MqttConnectOptions();
        co.setUserName(username);
        co.setPassword(password.toCharArray());
        co.setCleanSession(false);
        c.connect(co);
        c.subscribe(topic);
//        c.setTimeToWait(10000);
        c.setCallback(new MqttCallback() {
			
			@Override
			public void messageArrived(String topic, MqttMessage message) throws Exception {
				System.out.println(message);
			}
			
			@Override
			public void deliveryComplete(IMqttDeliveryToken token) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void connectionLost(Throwable cause) {
				System.out.println("11111111");
			}
		});

        while (true) {
			
		}
	}
	
	@Test
	public void send() throws InterruptedException, MqttException {
		try {
			MqttClient sampleClient = new MqttClient(broker, "xiang-send", new MemoryPersistence());
			MqttConnectOptions connOpts = new MqttConnectOptions();
			connOpts.setUserName(username);
			connOpts.setPassword(password.toCharArray());
			connOpts.setCleanSession(false);
			System.out.println("Connecting to broker: " + broker);
			sampleClient.connect(connOpts);

			System.out.println("Connected");
			System.out.println("Publishing message: " + content);
//			MqttMessage message = new MqttMessage("��051SMC	u\"|��051SMC	u\"|��051SMC	u".getBytes());
			MqttMessage message = new MqttMessage("123".getBytes());
			message.setQos(qos);
			sampleClient.publish(topic, message);

			System.out.println("Message published");
			// sampleClient.disconnect();
			System.out.println("Disconnected");
		} catch (MqttException me) {
			System.out.println("reason " + me.getReasonCode());
			System.out.println("msg " + me.getMessage());
			System.out.println("loc " + me.getLocalizedMessage());
			System.out.println("cause " + me.getCause());
			System.out.println("excep " + me);
			me.printStackTrace();
		}
		
//		while (true) {
//			
//		}
	}
}
