package com.yoohoo.en.web.recharge.service;

import org.jdom.JDOMException;

import java.io.IOException;

/**
 * Created By LiWenLong On 2018/8/21 9:47
 * E-Mail:it_lwl@163.com
 */
public interface StudentRechargeService {
    String weixinPay(String out_trade_no, String total_amount) throws JDOMException, IOException;
}
