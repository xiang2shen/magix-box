package com.magicbox.base.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.magicbox.base.support.ResponseHead;

/**
 * 会员模块错误码枚举
 * 
 * @author xiangshuo
 *
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCodes implements ResponseHead {
	
	MEMBER_NOT_FOUND("MEM10000", "会员不存在"),
	PASSWORD_ERROR("MEM10001", "密码错误"),
	MOBILE_EXIST("MEM10002", "手机号已存在"),
	CAPTCHA_ERROR("MEM10003", "验证码错误"),
	INVALID_TOKEN("MEM10010", "无效的token"),
	ALIPAY_ID_EXIST("MEM10011", "支付宝ID已绑定"),
	WEIXIN_ID_EXIST("MEM10012", "微信ID已绑定"),
	NOT_SELLER("MEM10020", "该会员不是卖家"),
	SHOP_NOT_FOUND("SHOP10021", "该店铺不存在"),
	SHOP_NOT_BELONG_TO_SELLER("SHOP10022", "该店铺不属于当前卖家"),
	PRODUCT_NOT_FOUND("PRD10023", "该商品不存在"),
	BOX_NOT_FOUND("BOX10024", "该盒子不存在"),
	BOX_NOT_BELONG_TO_SHOP("BOX10025", "该盒子不属于当前店铺"),
	BOX_NO_STOCK("PRD10026", "该盒子的商品库存不足"),
	BOX_NO_PRODUCT("BOX10027", "该盒子没有商品"),
	ORDER_NOT_FOUND("ORD10028", "该订单不存在"),
	MAP_API_ERROR("MAP10029", "地图接口失败");
	
	
	
	private ErrorCodes(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	private String code;
	private String msg;

	@Override
	public String getCode() {
		return code;
	}

	@Override
	public String getMsg() {
		return msg;
	}

}
