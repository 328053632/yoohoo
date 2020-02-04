package com.yoohoo.en.web.teacher.service;

import java.util.List;

import com.yoohoo.en.dao.model.TTeacherScheduleNv;
import com.yoohoo.en.web.params.TeacherScheduleQuery;

/**
 * Created By LiWenLong On 2018/11/13 14:16
 * E-Mail:it_lwl@163.com
 */
public interface TTeacherScheduleService {
    void saveTeacherSchedule(List<TTeacherScheduleNv> list);
    
    void deleteTeacherScheduleAfterToday(Long teacherId);

    List<TTeacherScheduleNv> queryTeacherSchedule(TeacherScheduleQuery query);

	void updateTeacherSchedule(List<TTeacherScheduleNv> list);
}
