package com.yoohoo.en.web.recharge.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yoohoo.en.dao.mapper.TStudentRechargeRecordMapper;
import com.yoohoo.en.dao.model.TStudentRechargeRecord;
import com.yoohoo.en.web.recharge.service.RechargeCallBackService;

/**
 * Created By LiWenLong On 2018/8/20 13:45
 * E-Mail:it_lwl@163.com
 */

@Service
public class RechargeCallBackServiceImpl implements RechargeCallBackService {

    @Autowired
    TStudentRechargeRecordMapper studentRechargeRecordMapper;


    @Transactional
    @Override
    public void inserTransactionRecord(TStudentRechargeRecord tStudentRechargeRecord) {
        studentRechargeRecordMapper.insertSelective(tStudentRechargeRecord);
    }

    @Override
    public TStudentRechargeRecord findOrderByOrderId(String out_trade_no) {
        TStudentRechargeRecord tStudentRechargeRecord = studentRechargeRecordMapper.selectByPrimaryKey(out_trade_no);
        return tStudentRechargeRecord;
    }
}
