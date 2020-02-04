package com.yoohoo.en.web.recharge.service.impl;

import java.io.IOException;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.jdom.JDOMException;
import org.springframework.stereotype.Service;

import com.yoohoo.en.web.recharge.config.WxPayConfig;
import com.yoohoo.en.web.recharge.service.StudentRechargeService;
import com.yoohoo.en.web.recharge.wxutil.AmounUtils;
import com.yoohoo.en.web.recharge.wxutil.HttpUtil;
import com.yoohoo.en.web.recharge.wxutil.PayToolUtil;
import com.yoohoo.en.web.recharge.wxutil.XMLUtil4jdom;

/**
 * Created By LiWenLong On 2018/8/21 9:48
 * E-Mail:it_lwl@163.com
 */
@Service
public class StudentRechargeServiceImpl  implements StudentRechargeService {
    @Override
    public String weixinPay(String out_trade_no, String total_amount) throws JDOMException, IOException {

        String currTime = PayToolUtil.getCurrTime();
        String strTime = currTime.substring(8, currTime.length());
        String strRandom = PayToolUtil.buildRandom(4) + "";
        String nonce_str = strTime + strRandom;
        String total = AmounUtils.changeY2F(total_amount);
        SortedMap<Object,Object> packageParams = new TreeMap<Object,Object>();
        packageParams.put("appid", WxPayConfig.APP_ID);
        packageParams.put("mch_id", WxPayConfig.MCH_ID);
        packageParams.put("nonce_str", nonce_str);
        packageParams.put("body", "金额充值");  //（调整为自己的名称）
        packageParams.put("out_trade_no", out_trade_no);
        packageParams.put("total_fee", total); //价格的单位为分
        packageParams.put("spbill_create_ip",  WxPayConfig.CREATE_IP);
        packageParams.put("notify_url",  WxPayConfig.NOTIFY_URL);
        packageParams.put("trade_type", "NATIVE");
        String sign = PayToolUtil.createSign("UTF-8", packageParams,WxPayConfig.API_KEY);
        packageParams.put("sign", sign);
        String requestXML = PayToolUtil.getRequestXml(packageParams);
        System.out.println(requestXML);
        String resXml = HttpUtil.postData(WxPayConfig.UFDODER_URL, requestXML);
        Map map = XMLUtil4jdom.doXMLParse(resXml);
        String urlCode = (String) map.get("code_url");
        return urlCode;
    }
}
