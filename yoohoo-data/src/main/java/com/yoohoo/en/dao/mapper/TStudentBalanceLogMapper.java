package com.yoohoo.en.dao.mapper;

import com.yoohoo.en.dao.model.TStudentBalanceLog;
import com.yoohoo.en.dao.model.TStudentBalanceLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TStudentBalanceLogMapper {
    public static final String DATA_SOURCE_NAME = "null";

    int countByExample(TStudentBalanceLogExample example);

    int deleteByExample(TStudentBalanceLogExample example);

    int deleteByPrimaryKey(Integer logId);

    int insert(TStudentBalanceLog record);

    int insertSelective(TStudentBalanceLog record);

    List<TStudentBalanceLog> selectByExample(TStudentBalanceLogExample example);

    TStudentBalanceLog selectByPrimaryKey(Integer logId);

    int updateByExampleSelective(@Param("record") TStudentBalanceLog record, @Param("example") TStudentBalanceLogExample example);

    int updateByExample(@Param("record") TStudentBalanceLog record, @Param("example") TStudentBalanceLogExample example);

    int updateByPrimaryKeySelective(TStudentBalanceLog record);

    int updateByPrimaryKey(TStudentBalanceLog record);

    int insertBatch(List<TStudentBalanceLog> records);
}