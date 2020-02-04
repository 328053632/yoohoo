package com.yoohoo.en.bean.message;

/**
 * 申请试听消息
 */
public class AuditionMessage extends BaseMessage
{
    private static final long serialVersionUID = 71830396208160280L;

    /**
     * 申请课程
     */
    private String lessonName;

    /**
     * 预约时间, yyyy-MM-dd
     */
    private String lessonDate;

    /**
     * 预约上课时间: hh:mm-hh:mm
     */
    private String lessonTime;

    /**
     * 班级类型
     */
    private String classItemId;

    /**
     * 班级类型
     */
    private String className;

    /**
     * 班级
     */
    private String grade;

    /**
     * 推荐人手机号码
     */
    private String recommendPhone;

    private String teacherName;

    private String suggest;


    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getSuggest() {
        return suggest;
    }

    public void setSuggest(String suggest) {
        this.suggest = suggest;
    }

    public String getLessonName()
    {
        return lessonName;
    }

    public void setLessonName(String lessonName)
    {
        this.lessonName = lessonName;
    }

    public String getLessonDate()
    {
        return lessonDate;
    }

    public void setLessonDate(String lessonDate)
    {
        this.lessonDate = lessonDate;
    }

    public String getLessonTime()
    {
        return lessonTime;
    }

    public void setLessonTime(String lessonTime)
    {
        this.lessonTime = lessonTime;
    }

    public String getClassItemId()
    {
        return classItemId;
    }

    public void setClassItemId(String classItemId)
    {
        this.classItemId = classItemId;
    }

    public String getRecommendPhone()
    {
        return recommendPhone;
    }

    public void setRecommendPhone(String recommendPhone)
    {
        this.recommendPhone = recommendPhone;
    }

    public String getClassName()
    {
        return className;
    }

    public void setClassName(String className)
    {
        this.className = className;
    }

    public String getGrade()
    {
        return grade;
    }

    public void setGrade(String grade)
    {
        this.grade = grade;
    }
}
