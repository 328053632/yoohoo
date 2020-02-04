package com.yoohoo.en.bean;

import java.io.Serializable;
import java.util.HashMap;

public class ServiceParam extends HashMap implements Serializable
{
    private static final long serialVersionUID = -6285352658722836889L;
    //课程ID
    private Long lessonId;
    //班级ID
    private int classId;
    //页码
    private int pageNo;

    private int limit;

    private Long userId;
    //分类ID
    private int categoryId;

    private String name;

    public Long getLessonId() {
        return lessonId;
    }

    public void setLessonId(Long lessonId) {
        this.lessonId = lessonId;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public int getPageNo()
    {
        return pageNo;
    }

    public void setPageNo(int pageNo)
    {
        this.pageNo = pageNo;
    }

    public int getLimit()
    {
        return limit;
    }

    public void setLimit(int limit)
    {
        this.limit = limit;
    }

    public Long getUserId()
    {
        return userId;
    }

    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public int getCategoryId()
    {
        return categoryId;
    }

    public void setCategoryId(int categoryId)
    {
        this.categoryId = categoryId;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
}
