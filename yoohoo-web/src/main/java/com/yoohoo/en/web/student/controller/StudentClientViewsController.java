package com.yoohoo.en.web.student.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yoohoo.en.dao.model.TStudentInfo;
import com.yoohoo.en.utils.SessionUtil;

@Controller
@RequestMapping("/student/views")
public class StudentClientViewsController
{
    @RequestMapping("/index")
    public String index()
    {
        return "student/index";
    }

    /**
     * 首页
     *
     * @return
     */
    @RequestMapping("/study")
    public String study(HttpSession session, ModelMap map, Integer tabInx)
    {
        TStudentInfo loginStudent = SessionUtil.INSTANCE.getLoginStudent(session);
        if (null == loginStudent)
        {
            return "student/login";
        }
        if (tabInx == null || tabInx > 1 || tabInx < 0)
        {
            tabInx = 0;
        }
        map.put("tabInx", tabInx);
        return "student/my-study";
    }

    /**
     * 我的在学课程
     * @return
     */
    @RequestMapping("/myLesson")
    public String myLesson(HttpSession session)
    {
        TStudentInfo loginStudent = SessionUtil.INSTANCE.getLoginStudent(session);
        if (null == loginStudent)
        {
            return "student/login";
        }
        return "student/lessons-study-list";
        /*student/lessons-study-list.jsp页面*/
    }

    /**
     * 课程库
     *
     * @return
     */
    @RequestMapping("/lesson/library")
    public String lessonLib()
    {
        return "student/lessons-lib";
    }

    /**
     * 登录页
     *
     * @return
     */
    @RequestMapping("/login")
    public String login()
    {
        return "student/login";
    }

    /**
     * 注册页
     *
     * @return
     */
    @RequestMapping("/register")
    public String register()
    {
        return "student/register";
    }

    /**
     * 添加孩子
     *
     * @return
     */
    @RequestMapping("/addChild")
    public String addChild()
    {
        return "student/add-child";
    }

    /**
     * 课程详情页
     *
     * @param lessonId
     * @return
     */
    @RequestMapping("/lesson/detail/{lessonId}")
    public String lessonDetail(@PathVariable("lessonId") Integer lessonId, ModelMap map)
    {
        map.put("lessonId", lessonId);
        return "student/lessons-detail";
    }

    /**
     * 学生个人中心
     *
     * @return
     */
    @RequestMapping("/account")
    public String account(HttpSession session)
    {
        TStudentInfo loginStudent = SessionUtil.INSTANCE.getLoginStudent(session);
        if (null == loginStudent)
        {
            return "student/login";
        }

        return "student/account";
    }

    /**
     * 消费记录页面
     *
     * @param session
     * @return
     */
    @RequestMapping("/log/consume")
    public String consumeLog(HttpSession session)
    {
        TStudentInfo loginStudent = SessionUtil.INSTANCE.getLoginStudent(session);
        if (null == loginStudent)
        {
            return "student/login";
        }
        return "student/consume-log";
    }

    /**
     * 退出登录
     *
     * @return
     */
    @RequestMapping("/logout")
    public String logout(HttpSession session)
    {
        SessionUtil.INSTANCE.putSessionStudent(session, null);
        return "student/login";
    }

    /**
     * 重置密码
     *
     * @return
     */
    @RequestMapping("/resetpwd")
    public String resetpwd(HttpSession session)
    {
        return "student/resetpwd";
    }

    @RequestMapping("/OpenBook")
    public String OpenBook(HttpSession session ,String valus,String way)
    {
        session.setAttribute("valus",valus);
        session.setAttribute("way",way);
        return "student/OpenBook";
    }



    /**
     * 学生  在学课程
     */


    @RequestMapping("/lesson/detail/studied/{lessonId}")
    public String studied(@PathVariable("lessonId") Integer lessonId, ModelMap map){
        map.put("lessonId", lessonId);
        return "student/studed-lesson-detail";
    }


    /**
     *
     * 学生端视频
     */

    @RequestMapping("/Teacher-ViewVideo/{teacherId}")
    public String ViewVideo(@PathVariable("teacherId") Integer teacherId,ModelMap map){
        map.put("teacherId", teacherId);
        return "student/student-Teacher-ViewVideo";
    }

}
