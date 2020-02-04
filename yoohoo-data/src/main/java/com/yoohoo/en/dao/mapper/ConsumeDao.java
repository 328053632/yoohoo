package com.yoohoo.en.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yoohoo.en.dao.model.ext.ConsumePackInfo;
import com.yoohoo.en.dao.model.ext.StudentConsumeInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsumeDao {
	
	List<StudentConsumeInfo> queryClassConsumeList(@Param("classId") Integer classId);
	
	List<StudentConsumeInfo> queryStudentConsumeByScheduleId(@Param("scheduleId") Long scheduleId);
	
	List<ConsumePackInfo> queryClassScheduleExtByCondition(
			@Param("lessonId") Integer lessonId,
			@Param("classId") Integer classId,
			@Param("classItemId") Integer classItemId,
			@Param("dateStr") String dateStr,
			@Param("addUserId") Long addUserId);
	
	int changeStudentBlance(@Param("userId") Long userId,@Param("amount") Integer amount,@Param("rechargeType") Integer rechargeType);
}
