package com.yoohoo.en.dao.mapper;

import com.yoohoo.en.dao.model.TClassSchedule;
import com.yoohoo.en.dao.model.TClassScheduleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TClassScheduleMapper {
    int countByExample(TClassScheduleExample example);

    int deleteByExample(TClassScheduleExample example);

    int deleteByPrimaryKey(Integer scheduleId);

    int insert(TClassSchedule record);

    int insertSelective(TClassSchedule record);

    List<TClassSchedule> selectByExample(TClassScheduleExample example);

    TClassSchedule selectByPrimaryKey(Long scheduleId);

    int updateByExampleSelective(@Param("record") TClassSchedule record, @Param("example") TClassScheduleExample example);

    int updateByExample(@Param("record") TClassSchedule record, @Param("example") TClassScheduleExample example);

    int updateByPrimaryKeySelective(TClassSchedule record);

    int updateByPrimaryKey(TClassSchedule record);
	
    int insertBatch(List<TClassSchedule> records);

    void updateTeacher(Integer teacherId, Long classId,Integer eteacherId,Integer masterteacherId);

}