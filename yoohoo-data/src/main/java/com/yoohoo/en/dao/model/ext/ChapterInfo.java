package com.yoohoo.en.dao.model.ext;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class ChapterInfo implements Serializable
{
    private static final long serialVersionUID = -3300391890193965689L;

    /**
     * 课节标题
     */
    private String title;

    /**
     * 课节Id
     */
    private int chapterId;

    /**
     * 课节介绍
     */
    private String introduce;

    /**
     * 类型:
     */
    private int type;

    /**
     * 添加时间
     */
    private Date addTime;

    /**
     * 课程id
     */
    private int lessonId;

    /**
     * 状态
     */
    private int status;

    /**
     * 课节排序字段
     */
    private int orderNum;

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public int getChapterId()
    {
        return chapterId;
    }

    public void setChapterId(int chapterId)
    {
        this.chapterId = chapterId;
    }

    public String getIntroduce()
    {
        return introduce;
    }

    public void setIntroduce(String introduce)
    {
        this.introduce = introduce;
    }

    public int getType()
    {
        return type;
    }

    public void setType(int type)
    {
        this.type = type;
    }

    public Date getAddTime()
    {
        return addTime;
    }

    public void setAddTime(Date addTime)
    {
        this.addTime = addTime;
    }

    public int getLessonId()
    {
        return lessonId;
    }

    public void setLessonId(int lessonId)
    {
        this.lessonId = lessonId;
    }

    public int getStatus()
    {
        return status;
    }

    public void setStatus(int status)
    {
        this.status = status;
    }

    public int getOrderNum()
    {
        return orderNum;
    }

    public void setOrderNum(int orderNum)
    {
        this.orderNum = orderNum;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        ChapterInfo info = (ChapterInfo)o;
        return chapterId == info.chapterId &&
            type == info.type &&
            lessonId == info.lessonId &&
            status == info.status &&
            orderNum == info.orderNum &&
            Objects.equals(title, info.title) &&
            Objects.equals(introduce, info.introduce) &&
            Objects.equals(addTime, info.addTime);
    }

    @Override
    public int hashCode()
    {

        return Objects.hash(title, chapterId, introduce, type, addTime, lessonId, status, orderNum);
    }

    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder("ChapterInfo{");
        sb.append("title='").append(title).append('\'');
        sb.append(", chapterId=").append(chapterId);
        sb.append(", introduce='").append(introduce).append('\'');
        sb.append(", type=").append(type);
        sb.append(", addTime=").append(addTime);
        sb.append(", lessonId=").append(lessonId);
        sb.append(", status=").append(status);
        sb.append(", orderNum=").append(orderNum);
        sb.append('}');
        return sb.toString();
    }
}
