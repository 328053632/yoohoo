package com.yoohoo.en.web.recharge.config;

public class WxPayConfig {
	//初始化
	public final static String APP_ID = "wxfef383450b7bd3a6"; //公众账号appid（改为自己实际的）
	public final static String APP_SECRET = "a164efd881f1fe120615832a065979eb";
	public final static String MCH_ID = "1437705502"; //商户号（改为自己实际的）
	public final static String API_KEY = "YoohooABC1234YaYing1234cheng1234"; //（改为自己实际的）key设置路径：微信商户平台(pay.weixin.qq.com)-->账户设置-->API安全-->密钥设置

	//有关url
	public final static String UFDODER_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
	public final static String NOTIFY_URL = "http://www.yoohooabc.com/callback/wxpay_notify_url"; //微信支付回调接口，就是微信那边收到（改为自己实际的）
	//企业向个人账号付款的URL
	public final static String SEND_EED_PACK_URL = "https://api.mch.weixin.qq.com/mmpaymkttransfers/promotion/transfers";

	public final static String CREATE_IP = "47.100.126.2";//发起支付ip（改为自己实际的）
	
}
