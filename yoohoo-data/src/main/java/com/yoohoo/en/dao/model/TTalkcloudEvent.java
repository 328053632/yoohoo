package com.yoohoo.en.dao.model;

import java.io.Serializable;
import java.util.Date;

public class TTalkcloudEvent implements Serializable {
    private Integer eventId;

    private String roomId;

    private Long lessonId;

    private Integer chapterId;

    private Long classId;

    private Integer eventType;

    private Date addTime;

    private static final long serialVersionUID = 1L;

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId == null ? null : roomId.trim();
    }

    public Long getLessonId() {
        return lessonId;
    }

    public void setLessonId(Long lessonId) {
        this.lessonId = lessonId;
    }

    public Integer getChapterId() {
        return chapterId;
    }

    public void setChapterId(Integer chapterId) {
        this.chapterId = chapterId;
    }

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    public Integer getEventType() {
        return eventType;
    }

    public void setEventType(Integer eventType) {
        this.eventType = eventType;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    private TTalkcloudEvent(Builder b) {
        eventId = b.eventId;
        roomId = b.roomId;
        lessonId = b.lessonId;
        chapterId = b.chapterId;
        classId = b.classId;
        eventType = b.eventType;
        addTime = b.addTime;
    }

    public TTalkcloudEvent() {
        super();
    }

    public static class Builder {
        private Integer eventId;

        private String roomId;

        private Long lessonId;

        private Integer chapterId;

        private Long classId;

        private Integer eventType;

        private Date addTime;

        public Builder eventId(Integer eventId) {
            this.eventId = eventId;
            return this;
        }

        public Builder roomId(String roomId) {
            this.roomId = roomId;
            return this;
        }

        public Builder lessonId(Long lessonId) {
            this.lessonId = lessonId;
            return this;
        }

        public Builder chapterId(Integer chapterId) {
            this.chapterId = chapterId;
            return this;
        }

        public Builder classId(Long classId) {
            this.classId = classId;
            return this;
        }

        public Builder eventType(Integer eventType) {
            this.eventType = eventType;
            return this;
        }

        public Builder addTime(Date addTime) {
            this.addTime = addTime;
            return this;
        }

        public TTalkcloudEvent build() {
            return new TTalkcloudEvent(this);
        }
    }
}