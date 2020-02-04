package com.yoohoo.en.web.teacher.service;

import java.text.ParseException;
import java.util.List;

/**
 * Created By LiWenLong On 2018/11/13 15:15
 * E-Mail:it_lwl@163.com
 */
public interface TClassScheduleService {
    List<String> queryTeacherSchedule(Integer teacherId, String dateTime) throws ParseException;
}
