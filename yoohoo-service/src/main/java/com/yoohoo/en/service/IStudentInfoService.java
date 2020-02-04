package com.yoohoo.en.service;

import com.yoohoo.en.dao.model.TStudentInfo;
import com.yoohoo.en.dao.model.ext.LessonInfoExt;

import java.util.List;

public interface IStudentInfoService
{
    /**
     * 查询学生信息, 根据用户名密码
     * @param account
     * @param password
     * @return
     */
    TStudentInfo query(String account, String password);

    /**
     * 查询学生信息,根据用户名
     * @param account
     * @return
     */
    TStudentInfo query(String account);

    /**
     * 查询学生信息,根据用户名
     * @param account
     * @return
     */
    TStudentInfo queryRegistered(String account);

    /**
     * 查询学生信息, 根据id
     * @param id
     * @return
     */
    TStudentInfo query(int id);

    /**
     * 插入学生信息
     * @param studentInfo
     * @return
     */
    Long insert(TStudentInfo studentInfo);

    /**
     * 发送短信验证码
     * @param phone
     * @param serviceType
     */
    boolean sendVerificationCode(String phone, int serviceType);

    /**
     * 绑定孩子信息
     * @param studentInfo
     * @return
     */
    boolean update(TStudentInfo studentInfo);

    /**
     * 插入或者更新用户信息
     * @param studentInfo
     * @return
     */
    TStudentInfo insertOrUpdate(TStudentInfo studentInfo);
    
    /**
     * 检查手机号码是否已经注册过
     * @param msisdn
     * @return
     */
    boolean checkUserIsExists(String msisdn);

    /**
     * 查询学生电亮章节列表
     * @param studentId
     * @return
     */
    List<LessonInfoExt> queryStudentLearnPath(Long studentId);
}
