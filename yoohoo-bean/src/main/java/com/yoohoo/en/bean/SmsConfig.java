package com.yoohoo.en.bean;

import java.io.Serializable;

public class SmsConfig implements Serializable
{
    private static final long serialVersionUID = 5172607603514654164L;

    /**
     * 发送短信接口
     */
    private String url;

    /**
     * 短信模板
     */
    private String template;

    /**
     * 账号
     */
    private String account;

    /**
     * 密码
     */
    private String password;

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    public String getTemplate()
    {
        return template;
    }

    public void setTemplate(String template)
    {
        this.template = template;
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
}
