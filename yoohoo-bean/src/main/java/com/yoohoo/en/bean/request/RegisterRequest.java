package com.yoohoo.en.bean.request;

import java.io.Serializable;

public class RegisterRequest implements Serializable
{
    private static final long serialVersionUID = -1657546442396701460L;

    private String msisdn;//手机号

    private String password;

    private String code;//验证码

    public String getMsisdn() { return msisdn; }

    public void setMsisdn(String msisdn)
    {
        this.msisdn = msisdn;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder("RegisterRequest{");
        sb.append("msisdn='").append(msisdn).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", code='").append(code).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
