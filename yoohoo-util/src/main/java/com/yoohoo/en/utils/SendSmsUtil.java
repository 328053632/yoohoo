package com.yoohoo.en.utils;

import com.alibaba.fastjson.JSON;
import com.yoohoo.en.bean.SmsConfig;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class SendSmsUtil
{
    private static Logger logger = Logger.getLogger(SendSmsUtil.class);

    /**
     * 发送短信默认配置文件 在 ApplicationInitListener 中初始化
     */
    public static SmsConfig smsConfig;

    public static boolean sendVerifyCodeSms(String toMsisdn, String verifyCode, SmsConfig smsConfig)
    {
        SendSmsUtil.smsConfig = smsConfig;
        return SendSmsUtil.sendVerifyCodeSms(toMsisdn, verifyCode);
    }

    public static boolean sendVerifyCodeSms(String toMsisdn, String verifyCode)
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("给手机号码" + toMsisdn + "下发的验证码是：" + verifyCode);
        }

        if (smsConfig == null)
        {
            logger.error("加载短信配置出现问题");
            return false;
        }

        HttpClient client = new HttpClient();
        PostMethod method = new PostMethod(smsConfig.getUrl());

        String content = smsConfig.getTemplate().replace("{verifycode}", verifyCode);

        client.getParams().setContentCharset("UTF-8");
        method.setRequestHeader("ContentType", "application/x-www-form-urlencoded;charset=UTF-8");

        // 密码可以使用明文密码或使用32位MD5加密
        NameValuePair[] data = { // 提交短信
            new NameValuePair("account", smsConfig.getAccount()),
            new NameValuePair("password", smsConfig.getPassword()),
            new NameValuePair("mobile", toMsisdn),
            new NameValuePair("content", content),};

        method.setRequestBody(data);

        try
        {
            client.executeMethod(method);

            String SubmitResult = method.getResponseBodyAsString();

            Document doc = DocumentHelper.parseText(SubmitResult);
            Element root = doc.getRootElement();

            String code = root.elementText("code");
            String msg = root.elementText("msg");
            String smsid = root.elementText("smsid");

            String logStr = "短信发送结果："
                + JSON.toJSONString(new String[][] {{"code", code}, {"msg", msg}, {"smsid", smsid}});
            // 短信发送成功
            if ("2".equals(code))
            {
                if (logger.isDebugEnabled())
                {
                    logger.debug(logStr);
                }
                return true;
            }
            else
            {
                logger.error(logStr);
            }

        }
        catch (Exception e)
        {
            logger.error("给手机号码" + toMsisdn + "下发验证码" + verifyCode + "失败！", e);
        }

        return false;
    }
}