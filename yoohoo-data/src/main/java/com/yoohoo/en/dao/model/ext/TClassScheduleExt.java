package com.yoohoo.en.dao.model.ext;

import com.yoohoo.en.dao.model.TClassSchedule;

public class TClassScheduleExt extends TClassSchedule
{
    private static final long serialVersionUID = 2937874999483199977L;

    private String chapterName;

    private String lessonTimeStr;

    private String endTimeStr;

    private int orderNum;


    private  String assistantName;


    private  String lessonTimeS;

    public String getLessonTimeS() {
        return lessonTimeS;
    }

    public void setLessonTimeS(String lessonTimeS) {
        this.lessonTimeS = lessonTimeS;
    }




    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getAssistantName() {
        return assistantName;
    }

    public void setAssistantName(String assistantName) {
        this.assistantName = assistantName;
    }

    public String getChapterName()
    {
        return chapterName;
    }

    public void setChapterName(String chapterName)
    {
        this.chapterName = chapterName;
    }

    public String getEndTimeStr()
    {
        return endTimeStr;
    }

    public void setEndTimeStr(String endTimeStr)
    {
        this.endTimeStr = endTimeStr;
    }

    public String getLessonTimeStr()
    {
        return lessonTimeStr;
    }

    public void setLessonTimeStr(String lessonTimeStr)
    {
        this.lessonTimeStr = lessonTimeStr;
    }

    public int getOrderNum()
    {
        return orderNum;
    }

    public void setOrderNum(int orderNum)
    {
        this.orderNum = orderNum;
    }
}