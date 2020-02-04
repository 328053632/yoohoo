package com.yoohoo.en.dao.model;

import java.io.Serializable;
import java.util.Date;

public class TStudentLearnPath implements Serializable {
    private Integer pathId;

    private Long studentId;

    private Integer lessonId;

    private Integer chapterId;

    private Date addTime;

    private static final long serialVersionUID = 1L;

    public Integer getPathId() {
        return pathId;
    }

    public void setPathId(Integer pathId) {
        this.pathId = pathId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Integer getLessonId() {
        return lessonId;
    }

    public void setLessonId(Integer lessonId) {
        this.lessonId = lessonId;
    }

    public Integer getChapterId() {
        return chapterId;
    }

    public void setChapterId(Integer chapterId) {
        this.chapterId = chapterId;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    private TStudentLearnPath(Builder b) {
        pathId = b.pathId;
        studentId = b.studentId;
        lessonId = b.lessonId;
        chapterId = b.chapterId;
        addTime = b.addTime;
    }

    public TStudentLearnPath() {
        super();
    }

    public static class Builder {
        private Integer pathId;

        private Long studentId;

        private Integer lessonId;

        private Integer chapterId;

        private Date addTime;

        public Builder pathId(Integer pathId) {
            this.pathId = pathId;
            return this;
        }

        public Builder studentId(Long studentId) {
            this.studentId = studentId;
            return this;
        }

        public Builder lessonId(Integer lessonId) {
            this.lessonId = lessonId;
            return this;
        }

        public Builder chapterId(Integer chapterId) {
            this.chapterId = chapterId;
            return this;
        }

        public Builder addTime(Date addTime) {
            this.addTime = addTime;
            return this;
        }

        public TStudentLearnPath build() {
            return new TStudentLearnPath(this);
        }
    }
}