package com.yoohoo.en.dao.model.ext;

import com.yoohoo.en.dao.model.TClass;

public class TClassExt extends TClass {
    private String classItem;

    private String lessonName;

    private String masterTeacher;

    private String teacher;

    private String assistant;

    private String eTeacher;

    private String lastUpdateAccount;

    private String beginDateStr;

    private String endDateStr;

    public String getClassItem() {
        return classItem;
    }

    public void setClassItem(String classItem) {
        this.classItem = classItem;
    }

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public String getMasterTeacher() {
        return masterTeacher;
    }

    public void setMasterTeacher(String masterTeacher) {
        this.masterTeacher = masterTeacher;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getAssistant() {
        return assistant;
    }

    public void setAssistant(String assistant) {
        this.assistant = assistant;
    }

    public String geteTeacher() {
        return eTeacher;
    }

    public void seteTeacher(String eTeacher) {
        this.eTeacher = eTeacher;
    }

    public String getLastUpdateAccount() {
        return lastUpdateAccount;
    }

    public void setLastUpdateAccount(String lastUpdateAccount) {
        this.lastUpdateAccount = lastUpdateAccount;
    }

    public String getBeginDateStr() {
        return beginDateStr;
    }

    public void setBeginDateStr(String beginDateStr) {
        this.beginDateStr = beginDateStr;
    }

    public String getEndDateStr() {
        return endDateStr;
    }

    public void setEndDateStr(String endDateStr) {
        this.endDateStr = endDateStr;
    }
}