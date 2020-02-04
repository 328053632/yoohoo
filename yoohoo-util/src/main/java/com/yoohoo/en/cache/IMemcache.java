package com.yoohoo.en.cache;

public interface IMemcache
{
    public Object getMem(String key);
    
    public boolean putMem(String key, Object value, int memcacheInvalidTime);
    
    public boolean delMem(String key);
    
    /***
     * memcache key的前缀
     * 
     * @return
     */
    public String getPrefixMemKey();
}
