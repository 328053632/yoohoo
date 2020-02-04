package com.yoohoo.en.web.teacher.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yoohoo.en.utils.SessionUtil;

@Controller
@RequestMapping("/teacher/views")
public class TeacherClientViewsController
{
    /**
     * 首页
     *
     * @return
     */
    @RequestMapping("/index")
    public String index()
    {
        return "teacher/index";
    }

    /**
     * 登录页
     *
     * @return
     */
    @RequestMapping("/login")
    public String login()
    {
        return "teacher/login";
    }

    /**
     * 退出登录
     *
     * @return
     */
    @RequestMapping("/logout")
    public String logout(HttpSession session)
    {
        SessionUtil.INSTANCE.putSessionTeacher(session, null);
        return "teacher/login";
    }

    @RequestMapping("/lesson/library")
    public String lessonLib(){
        return "/teacher/lessons-lib";
    }



    @RequestMapping("/detail/{lessonId}")
    public String detail(ModelMap model, @PathVariable("lessonId") int lessonId){
        model.put("lessonId",lessonId);
        return "/teacher/lessons-detail";
    }

    /**
     *
     * 老师端视频
     */
    @RequestMapping("/Teacher-ViewVideo/{teacherId}")
    public String ViewVideo(@PathVariable("teacherId") Integer teacherId,ModelMap map){
        map.put("teacherId", teacherId);
        return "teacher/Teacher-ViewVideo";
    }

    /**
     *
     * 测试日历
     */

    @RequestMapping("/dateTime")
    public String dateTime(){
        return "teacher/rl";
    }
}

