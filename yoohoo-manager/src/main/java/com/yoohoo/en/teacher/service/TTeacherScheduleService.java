package com.yoohoo.en.teacher.service;

import com.yoohoo.en.dao.model.TTeacherInfo;
import com.yoohoo.en.dao.model.TTeacherSchedule;

import java.text.ParseException;
import java.util.List;

/**
 * Created By LiWenLong On 2018/11/13 14:16
 * E-Mail:it_lwl@163.com
 */
public interface TTeacherScheduleService {
    void saveTeacherSchedule(TTeacherSchedule tTeacherSchedule) throws ParseException;

   List<String> queryTeacherSchedule(Integer teacherId, String date) throws ParseException;

    List<TTeacherInfo> getTeacherListByTime(String dateTime, String timeSchedule) throws ParseException;
}
