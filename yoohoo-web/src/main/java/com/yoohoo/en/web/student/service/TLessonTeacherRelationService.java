package com.yoohoo.en.web.student.service;

import com.yoohoo.en.dao.model.TTeacherInfo;

import java.util.List;
import java.util.Map;

/**
 * Created By LiWenLong On 2018/11/8 11:58
 * E-Mail:it_lwl@163.com
 */
public interface TLessonTeacherRelationService {
    Map<Integer,List<TTeacherInfo>>  queryLessonTeacher(int lessonId);
}
