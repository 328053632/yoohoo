package com.yoohoo.en.bean.request;

import java.io.Serializable;

public class LoginRequest implements Serializable
{
    /**
     * 账号
     */
    private String account;

    /**
     * 密码
     */
    private String password;


    /**
     *
     * 老师类型
     * @return
     */
    private String teacherType;
    
    private Integer type;



    public String getTeacherType() {
        return teacherType;
    }


    public void setTeacherType(String teacherType) {
        this.teacherType = teacherType;
    }


    public String getAccount()
    {
        return account;
    }

    public void setAccount(String account)
    {
        this.account = account;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public Integer getType() {
		return type;
	}


	public void setType(Integer type) {
		this.type = type;
	}


	@Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder("LoginRequest{");
        sb.append("account='").append(account).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
