package com.yoohoo.en.bean.message;


/**
 * 申请请假消息
 */
public class LeaveMessage extends BaseMessage
{
    private static final long serialVersionUID = -1613959396393068279L;

    private Long studentId;

    private Long classId;

    private String className;

    private String lessonName;

    private String chapterName;

    private String lessonTime;

    public Long getStudentId()
    {
        return studentId;
    }

    public void setStudentId(Long studentId)
    {
        this.studentId = studentId;
    }

    public Long getClassId()
    {
        return classId;
    }

    public void setClassId(Long classId)
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

    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder("LeaveMessage{");
        sb.append("studentId=").append(studentId);
        sb.append(", classId=").append(classId);
        sb.append(", className='").append(className).append('\'');
        sb.append(", lessonName='").append(lessonName).append('\'');
        sb.append(", chapterName='").append(chapterName).append('\'');
        sb.append(", lessonTime=").append(lessonTime);
        sb.append('}');
        return sb.toString();
    }
}
