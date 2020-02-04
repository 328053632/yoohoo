package com.yoohoo.en.bean.message;

import java.io.Serializable;

public class BaseMessage implements Serializable
{
    private static final long serialVersionUID = 3332530724999597324L;

    private String name;

    private String applyPhone;

    private int messageType;

    private Long studentId;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getApplyPhone()
    {
        return applyPhone;
    }

    public void setApplyPhone(String applyPhone)
    {
        this.applyPhone = applyPhone;
    }

    public int getMessageType()
    {
        return messageType;
    }

    public void setMessageType(int messageType)
    {
        this.messageType = messageType;
    }

    public Long getStudentId()
    {
        return studentId;
    }

    public void setStudentId(Long studentId)
    {
        this.studentId = studentId;
    }
}
