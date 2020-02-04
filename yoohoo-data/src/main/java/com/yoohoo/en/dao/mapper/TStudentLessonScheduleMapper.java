package com.yoohoo.en.dao.mapper;

import com.yoohoo.en.dao.model.TLessonInfo;
import com.yoohoo.en.dao.model.TStudentLessonSchedule;
import com.yoohoo.en.dao.model.TStudentLessonScheduleExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TStudentLessonScheduleMapper {
    public static final String DATA_SOURCE_NAME = "null";

    int countByExample(TStudentLessonScheduleExample example);

    List<TStudentLessonSchedule> ByExample(Long scheduleId);

    int deleteByExample(TStudentLessonScheduleExample example);

    int deleteByPrimaryKey(Integer recordId);

    int insert(TStudentLessonSchedule record);

    int insertSelective(TStudentLessonSchedule record);

    List<TStudentLessonSchedule> selectByExample(TStudentLessonScheduleExample example);

    TStudentLessonSchedule selectByPrimaryKey(Integer recordId);

    int updateByExampleSelective(@Param("record") TStudentLessonSchedule record, @Param("example") TStudentLessonScheduleExample example);

    int updateByExample(@Param("record") TStudentLessonSchedule record, @Param("example") TStudentLessonScheduleExample example);

    int updateByPrimaryKeySelective(TStudentLessonSchedule record);

    int updateByPrimaryKey(TStudentLessonSchedule record);

    int insertBatch(List<TStudentLessonSchedule> records);

    void deleteBatch(List<TStudentLessonSchedule> delList);

    List<TLessonInfo> queryLessonSchedulCount(List<Long> lessonIdList);

    //List<TTeacherInfo> queryTeacherLessonSchedule(Integer teacherId);
}