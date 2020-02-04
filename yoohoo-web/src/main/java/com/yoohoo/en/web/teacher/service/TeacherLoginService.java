package com.yoohoo.en.web.teacher.service;

import com.yoohoo.en.bean.response.LoginResponse;
import com.yoohoo.en.constant.ResponseCode;
import com.yoohoo.en.dao.model.TTeacherInfo;
import com.yoohoo.en.dao.model.TTeacherPositionRelation;
import com.yoohoo.en.service.ITeacherInfoService;
import com.yoohoo.en.utils.SessionUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class TeacherLoginService
{
    @Autowired
    private ITeacherInfoService teacherInfoService;


    @Autowired
    private TTeacherPositionRelationService teacherPositionRelationService;
    public LoginResponse login(String account, String password,String teacherType, HttpSession session)
    {
        LoginResponse response = new LoginResponse();
        TTeacherInfo tTeacherInfo = teacherInfoService.query(account);

        if (tTeacherInfo == null)
        {
            response.setResultCodeInfo(ResponseCode.USER_NOT_EXIST);
            return response;
        }

        if (!StringUtils.equals(tTeacherInfo.getPassword(), password))
        {
            response.setResultCodeInfo(ResponseCode.PASSWORD_NOT_MATCHING);
            return response;
        }

        TTeacherPositionRelation tTeacherPositionRelation = teacherPositionRelationService.selectTeacherByIdAndType(tTeacherInfo.getTeacherId(), teacherType);
        if(tTeacherPositionRelation==null){
            response.setResultCodeInfo(ResponseCode.TEACHER_TYPE_NOT_EXIT);
            return  response;
        }

        tTeacherInfo.setTeacherType(Integer.parseInt(teacherType));
        if(teacherType.equals("1")){
            teacherType="老师端";
        }else if(teacherType.equals("2")){
            teacherType="课长端";
        }else{
            teacherType="电教端";
        }
        tTeacherInfo.setType(teacherType);
        SessionUtil.INSTANCE.putSessionTeacher(session, tTeacherInfo);
        return response;
    }
}
