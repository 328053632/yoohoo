package com.yoohoo.en.cache.bean;

public class InvalidCacheBean
{
    // on:打开；off关闭
    private String switchs;
    
    // 无效缓存有效时间，单位分钟
    private int invalidTime;
    
    //
    private boolean supported;
    
    public String getSwitchs()
    {
        return switchs;
    }
    
    public void setSwitchs(String switchs)
    {
        this.switchs = switchs;
    }
    
    public int getInvalidTime()
    {
        return invalidTime;
    }
    
    public void setInvalidTime(int invalidTime)
    {
        this.invalidTime = invalidTime;
    }
    
    public boolean isSupported()
    {
        return supported;
    }
    
    public void setSupported(boolean supported)
    {
        this.supported = supported;
    }
}