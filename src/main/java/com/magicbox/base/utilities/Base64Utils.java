package com.magicbox.base.utilities;

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.binary.Base64;

/**
 * base64工具
 * 
 * @author xiangshuo
 *
 */
public final class Base64Utils {
	
	private static final String CHARSET = "UTF-8";
	
    private Base64Utils() {}
    
    /**
     * base64编码
     * 
     * @param text
     * @return
     */
    public static String encode(String text) {
    	if (null == text) {
			return null;
		}
    	
    	try {
			return Base64.encodeBase64String(text.getBytes(CHARSET));
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
    }
    
    /**
     * base64解码
     * 
     * @param text
     * @return
     */
    public static String decode(String text) {
    	if (null == text) {
			return null;
		}
    	
    	try {
			return new String(Base64.decodeBase64(text.getBytes(CHARSET)), CHARSET);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
    	
    }
}
