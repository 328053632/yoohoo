package com.yoohoo.en.bean.message;

/**
 * 申请补课消息
 */
public class RemediationMessage extends BaseMessage
{
    private static final long serialVersionUID = -1458040205040371156L;

    private String lessonDate;

    private String lessonTime;

    private String lessonName;

    private Long lessonId;

    private String chapterName;

    private int chapterId;

    private String className;


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

    public String getLessonName()
    {
        return lessonName;
    }

    public void setLessonName(String lessonName)
    {
        this.lessonName = lessonName;
    }

    public Long getLessonId()
    {
        return lessonId;
    }

    public void setLessonId(Long lessonId)
    {
        this.lessonId = lessonId;
    }

    public String getChapterName()
    {
        return chapterName;
    }

    public void setChapterName(String chapterName)
    {
        this.chapterName = chapterName;
    }

    public int getChapterId()
    {
        return chapterId;
    }

    public void setChapterId(int chapterId)
    {
        this.chapterId = chapterId;
    }

    public String getClassName()
    {
        return className;
    }

    public void setClassName(String className)
    {
        this.className = className;
    }

    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder("RemediationMessage{");
        sb.append("lessonDate='").append(lessonDate).append('\'');
        sb.append(", lessonTime='").append(lessonTime).append('\'');
        sb.append(", lessonName='").append(lessonName).append('\'');
        sb.append(", lessonId=").append(lessonId);
        sb.append(", chapterName='").append(chapterName).append('\'');
        sb.append(", chapterId=").append(chapterId);
        sb.append('}');
        return sb.toString();
    }
}
