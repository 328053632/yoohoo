package com.yoohoo.en.dao.mapper;

import com.yoohoo.en.dao.model.TClassStudentR;
import com.yoohoo.en.dao.model.TClassStudentRExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TClassStudentRMapper {
    public static final String DATA_SOURCE_NAME = "null";

    int countByExample(TClassStudentRExample example);

    int deleteByExample(TClassStudentRExample example);

    int insert(TClassStudentR record);

    int insertSelective(TClassStudentR record);

    List<TClassStudentR> selectByExample(TClassStudentRExample example);

    int updateByExampleSelective(@Param("record") TClassStudentR record, @Param("example") TClassStudentRExample example);

    int updateByExample(@Param("record") TClassStudentR record, @Param("example") TClassStudentRExample example);

    int insertBatch(List<TClassStudentR> records);
}