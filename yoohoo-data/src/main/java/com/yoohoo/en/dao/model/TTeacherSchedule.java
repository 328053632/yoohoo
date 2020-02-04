package com.yoohoo.en.dao.model;

import java.util.Date;

public class TTeacherSchedule {
    private Integer id;

    private Integer teacherId;

    private String date;

    private String timeScheduleStr;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTimeScheduleStr() {
        return timeScheduleStr;
    }

    public void setTimeScheduleStr(String timeScheduleStr) {
        this.timeScheduleStr = timeScheduleStr == null ? null : timeScheduleStr.trim();
    }

    @Override
    public String toString() {
        return "TTeacherSchedule{" +
                "id=" + id +
                ", teacherId=" + teacherId +
                ", date='" + date + '\'' +
                ", timeScheduleStr='" + timeScheduleStr + '\'' +
                '}';
    }
}