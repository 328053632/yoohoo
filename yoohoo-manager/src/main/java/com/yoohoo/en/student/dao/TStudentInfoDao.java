package com.yoohoo.en.student.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.yoohoo.en.mcore.dao.BaseDao;
import com.yoohoo.en.student.entity.TStudentInfoEntity;

/**
 * 学生信息表
 * 
 * @author dev-group
 * @email admin@yuanzonginfo.com
 * @date 2018-02-01 21:25:01
 */
public interface TStudentInfoDao extends BaseDao<TStudentInfoEntity> {

	List<Long> queryIdList(Map<String, Object> map);

	void clearUnfinishLesson(TStudentInfoEntity tStudentInfo);

	void clearClassSchedule(List<Long> scheduleIdList);

	List<Long> queryUnFinishScheduleIdList(TStudentInfoEntity tStudentInfo);

	int queryUnConfirmLessons(@Param("studentId") Long studentId, @Param("subAdminUserId") Long subAdminUserId);
	
	
	
}
