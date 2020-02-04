package com.yoohoo.en.utils;

import javax.servlet.http.HttpSession;
import com.yoohoo.en.constant.ApplicationConstant;
import com.yoohoo.en.dao.model.TStudentInfo;
import com.yoohoo.en.dao.model.TTeacherInfo;

public enum SessionUtil {
    INSTANCE;

    /**
     * 更新用户信息到session
     * @param session
     * @param object
     */
    public void putSessionStudent(HttpSession session, Object object)
    {
        session.setAttribute(ApplicationConstant.LOGIN_STUDENT_TAG, object);
    }

    public TStudentInfo getLoginStudent(HttpSession session)
    {
        return (TStudentInfo)session.getAttribute(ApplicationConstant.LOGIN_STUDENT_TAG);
    }

    public void putSessionTeacher(HttpSession session, Object object)
    {
        session.setAttribute(ApplicationConstant.LOGIN_TEACHER_TAG, object);
    }

    public TTeacherInfo getLoginTeacher(HttpSession session)
    {
        return (TTeacherInfo)session.getAttribute(ApplicationConstant.LOGIN_TEACHER_TAG);
    }
}
