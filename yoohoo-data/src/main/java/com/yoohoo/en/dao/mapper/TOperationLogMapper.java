package com.yoohoo.en.dao.mapper;

import com.yoohoo.en.dao.model.TOperationLog;
import com.yoohoo.en.dao.model.TOperationLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TOperationLogMapper {
    public static final String DATA_SOURCE_NAME = "null";

    int countByExample(TOperationLogExample example);

    int deleteByExample(TOperationLogExample example);

    int deleteByPrimaryKey(Integer operateId);

    int insert(TOperationLog record);

    int insertSelective(TOperationLog record);

    List<TOperationLog> selectByExample(TOperationLogExample example);

    TOperationLog selectByPrimaryKey(Integer operateId);

    int updateByExampleSelective(@Param("record") TOperationLog record, @Param("example") TOperationLogExample example);

    int updateByExample(@Param("record") TOperationLog record, @Param("example") TOperationLogExample example);

    int updateByPrimaryKeySelective(TOperationLog record);

    int updateByPrimaryKey(TOperationLog record);

    int insertBatch(List<TOperationLog> records);
}