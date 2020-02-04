package com.yoohoo.en.dao.mapper;

import com.yoohoo.en.dao.model.TLessonInfo;
import com.yoohoo.en.dao.model.TLessonInfoExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TLessonInfoMapper {
    public static final String DATA_SOURCE_NAME = "null";

    int countByExample(TLessonInfoExample example);

    int deleteByExample(TLessonInfoExample example);

    int deleteByPrimaryKey(Integer lessonId);

    int insert(TLessonInfo record);

    int insertSelective(TLessonInfo record);

    List<TLessonInfo> selectByExample(TLessonInfoExample example);

    TLessonInfo selectByPrimaryKey(Long lessonId);

    int updateByExampleSelective(@Param("record") TLessonInfo record, @Param("example") TLessonInfoExample example);

    int updateByExample(@Param("record") TLessonInfo record, @Param("example") TLessonInfoExample example);

    int updateByPrimaryKeySelective(TLessonInfo record);

    int updateByPrimaryKey(TLessonInfo record);

    int insertBatch(List<TLessonInfo> records);
}