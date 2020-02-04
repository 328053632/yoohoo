package com.yoohoo.en.service;

import com.yoohoo.en.dao.model.TStudentLessonSchedule;
import com.yoohoo.en.dao.model.ext.TStudentLessonScheduleExt;

import java.util.List;
import java.util.Map;

/**
 * 班级表
 * 
 * @author YuanzongInfo-HYL
 * @email admin@yuanzonginfo.com
 * @date 2018-02-03 14:22:14
 */
public interface IStudentLessonScheduleService {

	List<TStudentLessonScheduleExt> getListExt(Map<String, Object> map);

	void saveBatchStuSchedule(List<TStudentLessonSchedule> list);

	int delStuSchedule(int recordId);

	void saveBatchNomalStuSchedule(List<TStudentLessonSchedule> list);

    void delBatchStuScheduleByStuId(Long classId, Long studentId);
}
