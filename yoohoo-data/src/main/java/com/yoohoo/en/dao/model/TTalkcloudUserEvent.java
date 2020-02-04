package com.yoohoo.en.dao.model;

import java.io.Serializable;
import java.util.Date;

public class TTalkcloudUserEvent implements Serializable {
    private Integer eventId;

    private String roomId;

    private Long lessonId;

    private Integer chapterId;

    private Long classId;

    private String userId;

    private Integer userType;

    private Integer action;

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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public Integer getAction() {
        return action;
    }

    public void setAction(Integer action) {
        this.action = action;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    private TTalkcloudUserEvent(Builder b) {
        eventId = b.eventId;
        roomId = b.roomId;
        lessonId = b.lessonId;
        chapterId = b.chapterId;
        classId = b.classId;
        userId = b.userId;
        userType = b.userType;
        action = b.action;
        addTime = b.addTime;
    }

    public TTalkcloudUserEvent() {
        super();
    }

    public static class Builder {
        private Integer eventId;

        private String roomId;

        private Long lessonId;

        private Integer chapterId;

        private Long classId;

        private String userId;

        private Integer userType;

        private Integer action;

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

        public Builder userId(String userId) {
            this.userId = userId;
            return this;
        }

        public Builder userType(Integer userType) {
            this.userType = userType;
            return this;
        }

        public Builder action(Integer action) {
            this.action = action;
            return this;
        }

        public Builder addTime(Date addTime) {
            this.addTime = addTime;
            return this;
        }

        public TTalkcloudUserEvent build() {
            return new TTalkcloudUserEvent(this);
        }
    }
}