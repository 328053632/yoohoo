package com.yoohoo.en.dao.model.ext;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.JSON;

public class ConsumeLog extends StuTypeItem implements Serializable
{
    private static final long serialVersionUID = 1429509489963820732L;

    /**
     * 消费数量
     */
    private int amount;

    /**
     * 课堂开始时间
     */
    private Date startTime;

    /**
     * 课堂结束时间
     */
    private Date endTime;

    /**
     * 班级名称
     */
    private String className;

    /**
     * 课程名称
     */
    private String lessonName;

    /**
     * 课节名称
     */
    private String chapterName;

    /**
     * 时间字符串
     */
    private String timeStr;
    
    private String  studentName;
    
    private Integer consumeType;
    
    private Integer teacherId;
    
    private String teacherName;
    
    private Integer afterBalance;
    
    private Date addTime;
    
    private String mark;
    
    private String memo;

    private String masterTeacherName;

    private String assistantTeacherName;

    private String eTeacherName;


    private Integer scheduleId;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Integer scheduleId) {
        this.scheduleId = scheduleId;
    }

    public int getAmount()
    {
        return amount;
    }

    public void setAmount(int amount)
    {
        this.amount = amount;
    }

    public Date getStartTime()
    {
        return startTime;
    }

    public void setStartTime(Date startTime)
    {
        this.startTime = startTime;
    }

    public Date getEndTime()
    {
        return endTime;
    }

    public void setEndTime(Date endTime)
    {
        this.endTime = endTime;
    }

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

    public String getChapterName()
    {
        return chapterName;
    }

    public void setChapterName(String chapterName)
    {
        this.chapterName = chapterName;
    }

    public String getTimeStr()
    {
        return timeStr;
    }

    public void setTimeStr(String timeStr)
    {
        this.timeStr = timeStr;
    }
    
    public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public Integer getConsumeType() {
		return consumeType;
	}

	public void setConsumeType(Integer consumeType) {
		this.consumeType = consumeType;
	}

	public Integer getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public Integer getAfterBalance() {
		return afterBalance;
	}

	public void setAfterBalance(Integer afterBalance) {
		this.afterBalance = afterBalance;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

    public String getMasterTeacherName() {
        return masterTeacherName;
    }

    public void setMasterTeacherName(String masterTeacherName) {
        this.masterTeacherName = masterTeacherName;
    }

    public String getAssistantTeacherName() {
        return assistantTeacherName;
    }

    public void setAssistantTeacherName(String assistantTeacherName) {
        this.assistantTeacherName = assistantTeacherName;
    }

    public String geteTeacherName() {
        return eTeacherName;
    }

    public void seteTeacherName(String eTeacherName) {
        this.eTeacherName = eTeacherName;
    }

    public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	@Override
    public String toString()
    {
		return JSON.toJSONString(this);
    }
	
}
