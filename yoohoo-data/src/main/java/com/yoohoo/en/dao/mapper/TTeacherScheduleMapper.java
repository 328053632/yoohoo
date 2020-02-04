package com.yoohoo.en.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.yoohoo.en.dao.model.TTeacherSchedule;
import com.yoohoo.en.dao.model.TTeacherScheduleExample;
import com.yoohoo.en.dao.model.TTeacherScheduleNv;

@Repository
public interface TTeacherScheduleMapper {
    int countByExample(TTeacherScheduleExample example);

    int deleteByExample(TTeacherScheduleExample example);

    int deleteTeacherScheduleAfterToday(TTeacherScheduleNv model);

    int insert(TTeacherSchedule record);

    int insertSelective(TTeacherSchedule record);

    List<TTeacherSchedule> selectByExample(TTeacherScheduleExample example);

    int updateByExampleSelective(@Param("record") TTeacherSchedule record, @Param("example") TTeacherScheduleExample example);

    int updateByExample(@Param("record") TTeacherSchedule record, @Param("example") TTeacherScheduleExample example);

    List<Integer> queryTeacherByDateAndTimeSchedule(@Param("dateTime") String dateTime, @Param("timeSchedule") String timeSchedule, @Param("addUserId") Long addUserId);
}