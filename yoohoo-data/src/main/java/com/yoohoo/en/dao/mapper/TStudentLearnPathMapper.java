package com.yoohoo.en.dao.mapper;

import com.yoohoo.en.dao.model.TStudentLearnPath;
import com.yoohoo.en.dao.model.TStudentLearnPathExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TStudentLearnPathMapper {
    public static final String DATA_SOURCE_NAME = "null";

    int countByExample(TStudentLearnPathExample example);

    int deleteByExample(TStudentLearnPathExample example);

    int deleteByPrimaryKey(Integer pathId);

    int insert(TStudentLearnPath record);

    int insertSelective(TStudentLearnPath record);

    List<TStudentLearnPath> selectByExample(TStudentLearnPathExample example);

    TStudentLearnPath selectByPrimaryKey(Integer pathId);

    int updateByExampleSelective(@Param("record") TStudentLearnPath record, @Param("example") TStudentLearnPathExample example);

    int updateByExample(@Param("record") TStudentLearnPath record, @Param("example") TStudentLearnPathExample example);

    int updateByPrimaryKeySelective(TStudentLearnPath record);

    int updateByPrimaryKey(TStudentLearnPath record);

    int insertBatch(List<TStudentLearnPath> records);
}