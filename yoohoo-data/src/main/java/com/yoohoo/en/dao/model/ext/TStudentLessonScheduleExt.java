package com.yoohoo.en.dao.model.ext;

import com.yoohoo.en.dao.model.TStudentLessonSchedule;

public class TStudentLessonScheduleExt extends TStudentLessonSchedule {
    private String studentName;

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
}