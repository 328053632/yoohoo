package com.yoohoo.en.dao.mapper;

import com.yoohoo.en.dao.model.TLessonTeacherRelation;
import com.yoohoo.en.dao.model.TLessonTeacherRelationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TLessonTeacherRelationMapper {
    int countByExample(TLessonTeacherRelationExample example);

    int deleteByExample(TLessonTeacherRelationExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TLessonTeacherRelation record);

    int insertSelective(TLessonTeacherRelation record);

    List<TLessonTeacherRelation> selectByExample(TLessonTeacherRelationExample example);

    TLessonTeacherRelation selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TLessonTeacherRelation record, @Param("example") TLessonTeacherRelationExample example);

    int updateByExample(@Param("record") TLessonTeacherRelation record, @Param("example") TLessonTeacherRelationExample example);

    int updateByPrimaryKeySelective(TLessonTeacherRelation record);

    int updateByPrimaryKey(TLessonTeacherRelation record);
	
	 int insertBatch(List<TLessonTeacherRelation> list);
	
	 List<TLessonTeacherRelation> queryList(Integer lessonId,Integer positionType);
}