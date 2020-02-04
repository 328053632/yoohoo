package com.yoohoo.en.dao.model.ext;

import java.io.Serializable;
import java.util.List;

public class LessonInfo implements Serializable
{
    private static final long serialVersionUID = -7481197750464716977L;

    /**
     * 课程Id
     */
    private int lessonId;

    /**
     * 课程名称
     */
    private String lessonName;

    /**
     * 课程封面地址
     */
    private String coverUrl;

    /**
     * 课程类型
     */
    private int type;

    /**
     * 课节名称
     */
    private String chapterName;

    /**
     * 上课时间段
     */
    private String timeQuantum;

    /**
     * 班级id
     */
    private int classId;

    /**
     * 班级名称
     */
    private String className;

    /**
     * 班主任
     */
    private String headTeacher;

    /**
     * 老师
     */
    private String teacher;

    /**
     * 助教
     */
    private String assistant;

    /**
     * 电教
     */
    private String electrified;

    /**
     * 课节列表
     */
    private List<ChapterInfo> chapterList;

    public int getLessonId()
    {
        return lessonId;
    }

    public void setLessonId(int lessonId)
    {
        this.lessonId = lessonId;
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

    public int getType()
    {
        return type;
    }

    public void setType(int type)
    {
        this.type = type;
    }

    public String getChapterName()
    {
        return chapterName;
    }

    public void setChapterName(String chapterName)
    {
        this.chapterName = chapterName;
    }

    public int getClassId()
    {
        return classId;
    }

    public void setClassId(int classId)
    {
        this.classId = classId;
    }

    public String getClassName()
    {
        return className;
    }

    public void setClassName(String className)
    {
        this.className = className;
    }

    public String getTimeQuantum()
    {
        return timeQuantum;
    }

    public void setTimeQuantum(String timeQuantum)
    {
        this.timeQuantum = timeQuantum;
    }

    public String getHeadTeacher()
    {
        return headTeacher;
    }

    public void setHeadTeacher(String headTeacher)
    {
        this.headTeacher = headTeacher;
    }

    public String getTeacher()
    {
        return teacher;
    }

    public void setTeacher(String teacher)
    {
        this.teacher = teacher;
    }

    public String getAssistant()
    {
        return assistant;
    }

    public void setAssistant(String assistant)
    {
        this.assistant = assistant;
    }

    public String getElectrified()
    {
        return electrified;
    }

    public void setElectrified(String electrified)
    {
        this.electrified = electrified;
    }

    public List<ChapterInfo> getChapterList()
    {
        return chapterList;
    }

    public void setChapterList(List<ChapterInfo> chapterList)
    {
        this.chapterList = chapterList;
    }

    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder("LessonInfo{");
        sb.append("lessonId=").append(lessonId);
        sb.append(", lessonName='").append(lessonName).append('\'');
        sb.append(", coverUrl='").append(coverUrl).append('\'');
        sb.append(", type=").append(type);
        sb.append(", chapterName='").append(chapterName).append('\'');
        sb.append(", timeQuantum='").append(timeQuantum).append('\'');
        sb.append(", classId=").append(classId);
        sb.append(", className='").append(className).append('\'');
        sb.append(", headTeacher='").append(headTeacher).append('\'');
        sb.append(", teacher='").append(teacher).append('\'');
        sb.append(", assistant='").append(assistant).append('\'');
        sb.append(", electrified='").append(electrified).append('\'');
        sb.append(", chapterList=").append(chapterList);
        sb.append('}');
        return sb.toString();
    }
}
