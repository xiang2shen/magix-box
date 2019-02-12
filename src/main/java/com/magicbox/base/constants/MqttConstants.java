package com.magicbox.base.constants;

public interface MqttConstants {
	
	String TOPIC_SYN_STOCK = "synStock";
	String TOPIC_PANG = "pang";
	String TOPIC_OPEN_RESULT = "openResult";
	String TOPIC_UPDATE_RESULT = "updateStockResult";

	
	String TOPIC_PING = "ping/";
	String TOPIC_OPEN_AFTER_PAY = "openAfterPay/";
	String TOPIC_UPDATE_STOCK = "updateStock/";
	String TOPIC_RESET = "reset/";
	
	
	String TOPIC_CLIENT_ONLINE = "$SYS/brokers/+/clients/+/connected";
	String TOPIC_CLIENT_OFFLINE = "$SYS/brokers/+/clients/+/disconnected";
	
	String FORCE_OPEN_ORDER_CODE = "0000";	// 强制开锁虚拟订单号
}
