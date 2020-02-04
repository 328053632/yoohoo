package com.yoohoo.en.ehcache;

import javax.annotation.Resource;

import com.yoohoo.en.utils.MD5Util;
import org.springframework.cache.Cache;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.stereotype.Service;

@Service
public class EhcacheManager
{
    @Resource(name = "cacheManager")
    private EhCacheCacheManager cacheCacheManager;
    
    public Cache getCache(String cacheName)
    {
        return cacheCacheManager.getCache(cacheName);
    }
    
    public void put(Cache cache, Object key, Object value)
    {
        cache.put(key, value);
    }
    
    public void put(String cacheName, Object key, Object value)
    {
        Cache cache = getCache(cacheName);
        cache.put(key, value);
    }
    
    public Object get(Cache cache, Object key)
    {
        return cache.get(key).get();
    }
    
    public Object get(String cacheName, Object key)
    {
        Cache cache = getCache(cacheName);
        return cache.get(key).get();
    }

    public static void main(String args[])
    {
       System.out.println( MD5Util.MD5Encode("11212"+"12"+"2018-03-01 15:02:33"+""+"0123456789"));
    }
}
