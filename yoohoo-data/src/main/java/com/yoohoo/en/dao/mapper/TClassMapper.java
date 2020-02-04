package com.yoohoo.en.dao.mapper;

import com.yoohoo.en.dao.model.TClass;
import com.yoohoo.en.dao.model.TClassExample;
import java.util.List;

import com.yoohoo.en.dao.model.ext.TClassExt;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TClassMapper {
    public static final String DATA_SOURCE_NAME = "null";

    int countByExample(TClassExample example);

    int deleteByExample(TClassExample example);

    int deleteByPrimaryKey(Long classId);

    int insert(TClass record);

    int insertSelective(TClass record);

    List<TClass> selectByExample(TClassExample example);

    TClass selectByPrimaryKey(Long classId);

    int updateByExampleSelective(@Param("record") TClass record, @Param("example") TClassExample example);

    int updateByExample(@Param("record") TClass record, @Param("example") TClassExample example);

    int updateByPrimaryKeySelective(TClass record);

    int updateByPrimaryKey(TClass record);

    int insertBatch(List<TClass> records);

    String selectTeacherName(Long classId);

    List<TClassExt> queryList(TClass tClass);

    Integer queryTotal(TClass tClass);
}