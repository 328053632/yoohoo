package com.yoohoo.en.dao.mapper;

import com.yoohoo.en.dao.model.TStudentRechargeRecord;
import com.yoohoo.en.dao.model.TStudentRechargeRecordExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TStudentRechargeRecordMapper {
    int countByExample(TStudentRechargeRecordExample example);

    int deleteByExample(TStudentRechargeRecordExample example);

    int deleteByPrimaryKey(String orderId);

    int insert(TStudentRechargeRecord record);

    int insertSelective(TStudentRechargeRecord record);

    List<TStudentRechargeRecord> selectByExample(TStudentRechargeRecordExample example);

    TStudentRechargeRecord selectByPrimaryKey(String orderId);

    int updateByExampleSelective(@Param("record") TStudentRechargeRecord record, @Param("example") TStudentRechargeRecordExample example);

    int updateByExample(@Param("record") TStudentRechargeRecord record, @Param("example") TStudentRechargeRecordExample example);

    int updateByPrimaryKeySelective(TStudentRechargeRecord record);

    int updateByPrimaryKey(TStudentRechargeRecord record);
}