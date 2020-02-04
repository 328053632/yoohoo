package com.yoohoo.en.web.student.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.yoohoo.en.bean.request.LoginRequest;
import com.yoohoo.en.bean.response.BaseResp;
import com.yoohoo.en.dao.model.TTeacherInfo;
import com.yoohoo.en.service.ITeacherInfoService;
import com.yoohoo.en.service.TeacherScheduleNvService;
import com.yoohoo.en.web.params.TeacherScheduleQuery;

@RestController
@RequestMapping("/stu/course")
public class StudentCourseController {
    @Autowired
    private TeacherScheduleNvService tTeacherScheduleService;
    @Autowired
    private ITeacherInfoService teacherInfoService;

    @RequestMapping(value = "/queryTeacherByTime", method = RequestMethod.GET)
    @ResponseBody
    public BaseResp queryTeacherByTime(TeacherScheduleQuery query) {
    	
        return null;
    }

    @RequestMapping(value = "/queryTeacher", method = RequestMethod.GET)
    @ResponseBody
    public BaseResp queryTimeByTeacher(TeacherScheduleQuery query) {
    	List<Long> teacherIds = tTeacherScheduleService.queryTeacherIdsByTime(query.getBeginTime(), query.getEndTime());
    	List<TTeacherInfo> teachers = teacherInfoService.query(teacherIds);
        BaseResp response = new BaseResp();
        response.put("teachers", teachers);
        return response;
    }

    @RequestMapping(value = "/course", method = RequestMethod.POST)
    @ResponseBody
    public BaseResp course(LoginRequest request, HttpSession session) {
    	
        return null;
    }
}
