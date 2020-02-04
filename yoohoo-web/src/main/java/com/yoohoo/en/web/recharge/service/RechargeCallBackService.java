package com.yoohoo.en.web.recharge.service;

import com.yoohoo.en.dao.model.TStudentRechargeRecord;

/**
 * Created By LiWenLong On 2018/8/20 13:43
 * E-Mail:it_lwl@163.com
 */
public interface RechargeCallBackService {
    void inserTransactionRecord(TStudentRechargeRecord tStudentRechargeRecord);

    TStudentRechargeRecord findOrderByOrderId(String out_trade_no);
}
