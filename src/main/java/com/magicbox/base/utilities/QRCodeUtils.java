package com.magicbox.base.utilities;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

/**
 * 二维码生成工具类
 * 
 * @author xiangshuo
 *
 */
public final class QRCodeUtils {
	
	private static final Logger logger = LoggerFactory.getLogger(QRCodeUtils.class);

	private QRCodeUtils() {}
	
	public static void generateQRImage(int width, int height, OutputStream os, String text, String format) {
		
		try {
			
			Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
			hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
			
			BitMatrix bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, width, height, hints);	// 生成矩阵
			
			MatrixToImageWriter.writeToStream(bitMatrix, format, os);	// 输出图像
		} catch (Exception e) {
			
			logger.error("生成二维码图片失败", e);
		}
		
	}
}
