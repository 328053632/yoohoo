package com.yoohoo.en.web.teacher.service;

import com.yoohoo.en.dao.model.TTeacherPositionRelation;

/**
 * Created By LiWenLong On 2018/9/14 17:42
 * E-Mail:it_lwl@163.com
 */
public interface TTeacherPositionRelationService   {
    TTeacherPositionRelation selectTeacherByIdAndType(Integer teacherId, String teacherType);
}
