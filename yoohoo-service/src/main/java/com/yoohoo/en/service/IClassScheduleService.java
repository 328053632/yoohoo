package com.yoohoo.en.service;

import com.yoohoo.en.dao.model.TClassSchedule;
import com.yoohoo.en.dao.model.ext.TClassScheduleExt;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * 班级课程表
 * 
 * @author YuanzongInfo-HYL
 * @email admin@yuanzonginfo.com
 * @date 2018-02-03 14:22:14
 */
public interface IClassScheduleService {
	
	TClassSchedule queryObject(Long scheduleId);
	
	List<TClassSchedule> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(TClassSchedule tClassSchedule);
	
	void update(TClassSchedule tClassSchedule);
	
	void delete(Integer scheduleId);
	
	void deleteBatch(List<Integer> scheduleIds);

	List<TClassScheduleExt> queryListExt(Map<String, Object> map);

	void saveSchedule(TClassScheduleExt classScheduleExt);

	Integer updateTime(TClassScheduleExt classScheduleExt);

	void syncStuSchedule(TClassSchedule classSchedule);

    Integer selectByScheduleId(Long scheduleId);


    boolean checkTimeSchedule(String lessonTimeS, String endTimeStr, Integer teacherId) throws ParseException;
}
