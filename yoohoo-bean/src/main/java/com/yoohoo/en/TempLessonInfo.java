package com.yoohoo.en;

import java.io.Serializable;
import java.util.Date;

public class TempLessonInfo implements Serializable
{
    private static final long serialVersionUID = 365353160801219935L;

    private String className;

    private String lessonName;

    private String coverUrl;

    private Date beginTime;

    private Date endTime;

    private int masterTeacherId;

    private int teacherId;

    private int assistantId;

    private int eteacherId;

    private int lessonId;

    private int classId;

    public String getClassName()
    {
        return className;
    }

    public void setClassName(String className)
    {
        this.className = className;
    }

    public String getLessonName()
    {
        return lessonName;
    }

    public void setLessonName(String lessonName)
    {
        this.lessonName = lessonName;
    }

    public String getCoverUrl()
    {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl)
    {
        this.coverUrl = coverUrl;
    }

    public Date getBeginTime()
    {
        return beginTime;
    }

    public void setBeginTime(Date beginTime)
    {
        this.beginTime = beginTime;
    }

    public Date getEndTime()
    {
        return endTime;
    }

    public void setEndTime(Date endTime)
    {
        this.endTime = endTime;
    }

    public int getMasterTeacherId()
    {
        return masterTeacherId;
    }

    public void setMasterTeacherId(int masterTeacherId)
    {
        this.masterTeacherId = masterTeacherId;
    }

    public int getTeacherId()
    {
        return teacherId;
    }

    public void setTeacherId(int teacherId)
    {
        this.teacherId = teacherId;
    }

    public int getAssistantId()
    {
        return assistantId;
    }

    public void setAssistantId(int assistantId)
    {
        this.assistantId = assistantId;
    }

    public int getEteacherId()
    {
        return eteacherId;
    }

    public void setEteacherId(int eteacherId)
    {
        this.eteacherId = eteacherId;
    }

    public int getLessonId()
    {
        return lessonId;
    }

    public void setLessonId(int lessonId)
    {
        this.lessonId = lessonId;
    }

    public int getClassId()
    {
        return classId;
    }

    public void setClassId(int classId)
    {
        this.classId = classId;
    }
}
