package com.yoohoo.en.dao.model;

import java.util.Date;

import com.yoohoo.en.dao.model.common.BaseInfoModel;

public class TClass extends BaseInfoModel {
    private Long classId;

    private String className;

    private Integer classItemId;

    private Long lessonId;

    private Date beginDate;

    private Date endDate;

    private Integer masterTeacherId;

    private Integer teacherId;

    private Integer assistantId;

    private Integer eTeacherId;

    private Integer studentNum;

    private Date lastUpdateTime;

    private Integer lastUpdateAdmin;

    private Integer classType;


    //用于关联表查询老师姓名字段
    private  String teacherName;


    //设置起始下标
    Integer star;
    //设置limit
    Integer limit;
    //设置page
    Integer page;

    public Integer getStar() {
        return star;
    }

    public void setStar(Integer star) {
        this.star = star;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className == null ? null : className.trim();
    }

    public Integer getClassItemId() {
        return classItemId;
    }

    public void setClassItemId(Integer classItemId) {
        this.classItemId = classItemId;
    }

    public Long getLessonId() {
        return lessonId;
    }

    public void setLessonId(Long lessonId) {
        this.lessonId = lessonId;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getMasterTeacherId() {
        return masterTeacherId;
    }

    public void setMasterTeacherId(Integer masterTeacherId) {
        this.masterTeacherId = masterTeacherId;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public Integer getAssistantId() {
        return assistantId;
    }

    public void setAssistantId(Integer assistantId) {
        this.assistantId = assistantId;
    }

    public Integer geteTeacherId() {
        return eTeacherId;
    }

    public void seteTeacherId(Integer eTeacherId) {
        this.eTeacherId = eTeacherId;
    }

    public Integer getStudentNum() {
        return studentNum;
    }

    public void setStudentNum(Integer studentNum) {
        this.studentNum = studentNum;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public Integer getLastUpdateAdmin() {
        return lastUpdateAdmin;
    }

    public void setLastUpdateAdmin(Integer lastUpdateAdmin) {
        this.lastUpdateAdmin = lastUpdateAdmin;
    }

    public Integer getClassType() {
        return classType;
    }

    public void setClassType(Integer classType) {
        this.classType = classType;
    }
}