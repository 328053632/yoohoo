package com.yoohoo.en.dao.mapper;

import com.yoohoo.en.dao.model.TTeacherPositionRelation;
import com.yoohoo.en.dao.model.TTeacherPositionRelationExample;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TTeacherPositionRelationMapper {
    int countByExample(TTeacherPositionRelationExample example);

    int deleteByExample(TTeacherPositionRelationExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TTeacherPositionRelation record);

    int insertSelective(TTeacherPositionRelation record);

    List<TTeacherPositionRelation> selectByExample(TTeacherPositionRelationExample example);

    TTeacherPositionRelation selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TTeacherPositionRelation record, @Param("example") TTeacherPositionRelationExample example);

    int updateByExample(@Param("record") TTeacherPositionRelation record, @Param("example") TTeacherPositionRelationExample example);

    int updateByPrimaryKeySelective(TTeacherPositionRelation record);

    int updateByPrimaryKey(TTeacherPositionRelation record);

    void insertBatchList(List<TTeacherPositionRelation> list);

    List<TTeacherPositionRelation> queryList(@Param("teacherId") Integer teacherId, @Param("addUserId") Long addUserId);
}