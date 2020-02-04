package com.yoohoo.en.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 课程表信息
 */
public class ScheduleInfo implements Serializable
{
    private static final long serialVersionUID = -3622995839007313214L;

    /**
     * 上课时间
     */
    private Date date;

    /**
     * 结束时间
     */
    private Date endTime;

    /**
     * 课程名称
     */
    private String lessonName;

    /**
     * 课节名字
     */
    private String chapterName;

    /**
     * 上课时间: hh:mm
     */
    private String lessonTime;

    /**
     * 上课日期: M月d日
     */
    private String lessonDate;

    /**
     * 课程类型: -1退课 1-正常 2请假 3旁听 4试听 6补课
     */
    private int type;

    /**
     * 状态, 0删除 1正常 2已确认
     */
    private int status;

    private int classId;

    private int scheduleId;

    /**
     * 进入状态: -1: 未开始, 0:已开始  1:已结束
     */
    private int joinStatus;

    /**
     * 课程id
     */
    private int lessonId;

    /**
     * 章节id
     */
    private int chapterId;

    public ScheduleInfo()
    {
    }

    public ScheduleInfo(Builder builder)
    {
        this.setChapterName(builder.chapterName);
        this.setLessonDate(builder.lessonDate);
        this.setChapterName(builder.lessonName);
        this.setLessonTime(builder.lessonTime);
        this.setType(builder.type);
        this.setStatus(builder.status);
        this.setDate(builder.date);
        this.setClassId(builder.classId);
        this.setScheduleId(builder.scheduleId);
        this.setEndTime(builder.endTime);
        this.setJoinStatus(builder.joinStatus);
        this.setLessonId(builder.lessonId);
        this.setChapterId(builder.chapterId);
    }

    public String getLessonName()
    {
        return lessonName;
    }

    public void setLessonName(String lessonName)
    {
        this.lessonName = lessonName;
    }

    public String getChapterName()
    {
        return chapterName;
    }

    public void setChapterName(String chapterName)
    {
        this.chapterName = chapterName;
    }

    public String getLessonTime()
    {
        return lessonTime;
    }

    public void setLessonTime(String lessonTime)
    {
        this.lessonTime = lessonTime;
    }

    public String getLessonDate()
    {
        return lessonDate;
    }

    public void setLessonDate(String lessonDate)
    {
        this.lessonDate = lessonDate;
    }

    public int getType()
    {
        return type;
    }

    public void setType(int type)
    {
        this.type = type;
    }

    public int getStatus()
    {
        return status;
    }

    public void setStatus(int status)
    {
        this.status = status;
    }

    public Date getDate()
    {
        return date;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }

    public int getClassId()
    {
        return classId;
    }

    public void setClassId(int classId)
    {
        this.classId = classId;
    }

    public int getScheduleId()
    {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId)
    {
        this.scheduleId = scheduleId;
    }

    public Date getEndTime()
    {
        return endTime;
    }

    public void setEndTime(Date endTime)
    {
        this.endTime = endTime;
    }

    public int getJoinStatus()
    {
        return joinStatus;
    }

    public void setJoinStatus(int joinStatus)
    {
        this.joinStatus = joinStatus;
    }

    public int getLessonId()
    {
        return lessonId;
    }

    public void setLessonId(int lessonId)
    {
        this.lessonId = lessonId;
    }

    public int getChapterId()
    {
        return chapterId;
    }

    public void setChapterId(int chapterId)
    {
        this.chapterId = chapterId;
    }

    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder("ScheduleInfo{");
        sb.append("lessonName='").append(lessonName).append('\'');
        sb.append(", chapterName='").append(chapterName).append('\'');
        sb.append(", lessonTime='").append(lessonTime).append('\'');
        sb.append(", lessonDate='").append(lessonDate).append('\'');
        sb.append(", type=").append(type);
        sb.append('}');
        return sb.toString();
    }

    private static class Builder
    {
        private String lessonName;

        private String chapterName;

        private String lessonTime;

        private String lessonDate;

        private int type;

        private int status;

        private Date date;

        private Date endTime;

        private int classId;

        private int scheduleId;

        private int joinStatus;

        private int lessonId;

        private int chapterId;

        public Builder lessonName(String lessonName)
        {
            this.lessonName = lessonName;
            return this;
        }

        public Builder chapterName(String chapterName)
        {
            this.chapterName = chapterName;
            return this;
        }

        public Builder lessonTime(String lessonTime)
        {
            this.lessonTime = lessonTime;
            return this;
        }

        public Builder lessonDate(String lessonDate)
        {
            this.lessonDate = lessonDate;
            return this;
        }

        public Builder type(int type)
        {
            this.type = type;
            return this;
        }

        public Builder status(int status)
        {
            this.status = status;
            return this;
        }

        public Builder date(Date date)
        {
            this.date = date;
            return this;
        }

        public Builder classId(int classId)
        {
            this.classId = classId;
            return this;
        }

        public Builder scheduleId(int scheduleId)
        {
            this.scheduleId = scheduleId;
            return this;
        }

        public Builder endTime(Date endTime){
            this.endTime = endTime;
            return this;
        }

        public Builder joinStatus(int joinStatus)
        {
            this.joinStatus = joinStatus;
            return this;
        }

        public Builder lessonId(int lessonId){
            this.lessonId = lessonId;
            return this;
        }

        public Builder chapterId(int chapterId){
            this.chapterId = chapterId;
            return this;
        }

        public ScheduleInfo build()
        {
            return new ScheduleInfo(this);
        }
    }
}
