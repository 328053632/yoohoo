package com.yoohoo.en.bean.response;

import com.yoohoo.en.constant.ResponseCode;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 状态对象BaseResp
 * 封装状态码（code）和状态信息（message）
 */
public class BaseResp implements Serializable {
    private static final long serialVersionUID = 5115466597814584411L;

    /**
     * 返回结果码
     */
    private String code;

    /**
     * 返回消息
     */
    private String message;

    /**
     * 数据
     */
    private Map<String, Object> data = new HashMap<>();



    public BaseResp()
    {
        setResultCodeInfo(ResponseCode.SUCCESS);
    }

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public Map<String, Object> getData()
    {
        return data;
    }

    public void setData(Map<String, Object> data)
    {
        this.data = data;
    }

    public void setResultCodeInfo(ResponseCode codeInfo)
    {
        setCode(codeInfo.getCode());
        setMessage(codeInfo.getMessage());
    }

    public void put(String kye, Object value){
        this.data.put(kye,value);
    }
}
