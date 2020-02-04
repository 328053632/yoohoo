package com.yoohoo.en.web.recharge.config;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AliPayConfig {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2018062360399944";
	
	// 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCUvEftxs1w5AvwPebGdX/aKRBvA0dgymJtOEQuLOgGBrbk3nDfrFxIhiLC3nB9C6iofFk98VmlO+G4GPjFBHM0nLDwvt0k7erYXPUfZ8Bxj7duF1s4C043pD8JKuP2yfu/48hzwEnHw20yIplmG1WUb90nqYWXHukfc3BCLmIz6z4Uv89pzPp+Ox5TXmi+0+YOnGtuxYdUV6PP43Qw8NpsXf/Ev4jQzti7mjj1kqYqszOrT+PJVFu0ZKnpxpvzH2r8sxxQy0lxBo6GFM6EkW1oLt5VJXYjZ7Wh0tJ1FVh0ETKGrnrjXYSbf+gBkkIMwE8pTu7t88KC3IgGByxXE/yXAgMBAAECggEANG/AqBqbmT4820DnmHYaUg6CJbExO/V+B+V7XBAm6OD7H+swPhslGch4ft0vKVav6l6Uk9r/8NoQtPfQ1JzCBP1tACM6EefzEL6NCLpqRV8WmzCOz05lEAbO1muRnRMOt1MV+4ECQfQrDi7WiAIBBOj/Phls6J9ULuti22JA6u5thTglQedttFmTrnVbRm4QswY3Cq1a/Evktq/BrqxNwUaZdIzy4Z46ryBA/uxYcEqgkUY1psVwdjy711gfKxMJS+NPrDO/lr/1T9l9g53Ounllmmr/RqagsTD/Te4jNRysf4J/uJ8Fa+5TIBahVK6GCWtV1igvzzW55CKjJ3Mg0QKBgQDUv4xxOlWmt8iZQmq2XTywi/aeHtSMXhpvFmBIEKVJ1DmxgVzQQZuFLP8n/kjQpyjoQPaH7P1hygwHqdb7ExDObvpdqFG+oAX05y58YrlCAJ7r1HLNQnjWWcsikSqC6vA4F/0Slv6oFsGBjlyt6RAybkR76qqqX0jAHdjTyQmg7wKBgQCy+TJ/lzL9tZd+L2vOK/5fQhvEugEdx1QNcK3nPOoiivPvHm4KLanPnT4uuXhatXFxrN0njYzHBKXLAvx227YlWKdMgf1iPImOtYyR6vCveSfcvnNJS/GBYbHEtcxOvxmgJAD1TJhSL+Wk+BTjcg6ukHTyK7zhDn9ZOIPj57qO2QKBgH/FitN19XrrZc6l1PExjgF6DeNpIL4sxjNxfOHLHXn5u6yfxN5OiNiy7YtXbOPjgO38FhQMgSD597+igTrJeI6CmFsne/DAqtMrBQAvSpQZS1jXfxtHkMsropRgGugFsb/+qD9B8C+DezQUDBVmpBOlpfvGNDmegFLhmheX5fCBAoGBALBbdJJbqGhuvzgdfGqt2Tt+xiQlcaIpX77asNJyqPie2d5fqukms/UcKkEai+k+b+om/8nQ5s7Z2pFggOySdjr4z+pJESjzJE1Wwoyq6L7QcSJJHQs15WWroeOHjSpvjs3JjJQrBNG/1tenidXR93u2ZzaXOMjAY2bElNpH5wKJAoGAS2262PxLt7Re9D+O+n9N9JjoaN8vYy3lOpVbgwhMYUK0GNFclvTwbEvzb5pq5PsLjgzmL2pYaEybF4Jd08Wx7cRH0d0JVwTHmw/MCdmLmFKZ9nsU4TKtzEjqymcPMJhJxWtjCNS6WI/ZhwwUwDiNPSzk02KoP6SHPRKab3r+8wQ=";
	
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAlU27ilel8fff+0j6hZcJ1nY8tXR2Xs90fGwyV9dx4Yr7w2F8UYp+eXzWQKNzTdi7SmhasgK3VpSQZ8z3EYePhhaW6MKAH/O29xNr+ngbGAfuiMAhoth9Nt8obj3tCmpl91SmID2zo5iJ7pudmpYebhdizbU0d0yheG0AylunJFqmNSRFpaWwEiMqTeFfGWDz/0TQtgqGiM7AMSoKhhOJy9Vd9h7iGf9DSeF/Jnn+BROQV4ERdrlkRHxK+rUbCm07TiszG/UMgOwNk9BYE4QAAmayQ6cORv+6aiEvXAnwmdZIvbf0qlkGeE36BDWzx+JHl3aJASAHmepVel26wjYj5wIDAQAB";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://www.yoohooabc.com/callback/alipay_notify_url";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://www.yoohooabc.com/callback/alipay_return_url";
	
	// 签名方式
	public static String sign_type = "RSA2";
	
	// 字符编码格式
	public static String charset = "utf-8";
	
	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipay.com/gateway.do";
	
	// 支付宝网关
	public static String log_path = "D:\\AliPay\\";
	
//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /** 
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_error_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

