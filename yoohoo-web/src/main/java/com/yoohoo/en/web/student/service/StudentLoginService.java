package com.yoohoo.en.web.student.service;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yoohoo.en.bean.response.LoginResponse;
import com.yoohoo.en.constant.ResponseCode;
import com.yoohoo.en.dao.model.TStudentInfo;
import com.yoohoo.en.service.IStudentInfoService;
import com.yoohoo.en.utils.SessionUtil;

@Service
public class StudentLoginService
{
    @Autowired
    private IStudentInfoService studentInfoService;

    public LoginResponse login(String account, String password, HttpSession session)
    {
        LoginResponse response = new LoginResponse();

        TStudentInfo studentInfo = studentInfoService.queryRegistered(account);

        if (null == studentInfo)
        {
            // 用户不存在
            response.setResultCodeInfo(ResponseCode.USER_NOT_EXIST);
            return response;
        }

        if (!StringUtils.equals(studentInfo.getPasswd(), password))
        {
            // 密码错误
            response.setResultCodeInfo(ResponseCode.PASSWORD_NOT_MATCHING);
            return response;
        }
        // 登录成功
        SessionUtil.INSTANCE.putSessionStudent(session, studentInfo);
        return response;
    }
}
