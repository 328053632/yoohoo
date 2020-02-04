package com.yoohoo.en.ehcache.cache;

import org.springframework.cache.Cache;

public abstract class BaseCache
{
    public abstract Cache getCache();
    
    public void put(Object key, Object value)
    {
        getCache().put(key, value);
    }
    
    public Object get(Object key)
    {
        if (getCache().get(key) == null)
        {
            return null;
        }
        return getCache().get(key).get();
    }
}
