package com.magicbox.service.api;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.springframework.util.ResourceUtils;

import com.github.wxpay.sdk.WXPayConfig;

public class WeixinPayConfig implements WXPayConfig {
	
	private byte[] certData;

    public WeixinPayConfig() {
    	try {
    		File file = ResourceUtils.getFile("classpath:apiclient_cert.p12");
    		InputStream certStream = new FileInputStream(file);
    		this.certData = new byte[(int) file.length()];
    		certStream.read(this.certData);
    		certStream.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
    }

	@Override
	public String getAppID() {
		return "wx5441656a0f166b06";
	}

	public String getMchID() {
        return "1495985482";
    }

    public String getKey() {
        return "f4f48d14d6cc44be9fc4c8546432af0b";
    }
    
    public InputStream getCertStream() {
        ByteArrayInputStream certBis = new ByteArrayInputStream(this.certData);
        return certBis;
    }

    public int getHttpConnectTimeoutMs() {
        return 8000;
    }

    public int getHttpReadTimeoutMs() {
        return 10000;
    }

}
