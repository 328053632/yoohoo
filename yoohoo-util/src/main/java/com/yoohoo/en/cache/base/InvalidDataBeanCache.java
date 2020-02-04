package com.yoohoo.en.cache.base;

import net.rubyeye.xmemcached.MemcachedClient;

import org.apache.log4j.Logger;

import com.opensymphony.oscache.base.Cache;
import com.opensymphony.oscache.base.NeedsRefreshException;

public class InvalidDataBeanCache extends BaseOScache
{
    /** 日志对象 */
    private static Logger logger = Logger.getLogger(InvalidDataBeanCache.class);
    
    /** 失效缓存memcache前缀 */
    private static final String PREFIX_MEM_KEY = "INVALID_MEM_";
    
    /** 失效缓存oscache前缀 */
    private static final String PREFIX_OS_KEY = "INVALID_OS_";
    
    private static final String NULL = "0";
    
    private static final String CONTENT = "1";
    
    private int cacheTime = 60;
    
    public boolean contains(String obj)
    {
        try
        {
            Object o = getOs(obj);
            
            return (CONTENT.equals(o));
        }
        catch (NeedsRefreshException e)
        {
            try
            {
                getOScache().putInCache(obj, NULL);
            }
            catch (Exception ex)
            {
                logger.error("Error in putInCache", ex);
                getOScache().cancelUpdate(obj);
            }
            return false;
        }
    }
    
    protected int getRefreshTime()
    {
        return this.cacheTime;
    }
    
    public void setCacheTime(int cacheTime)
    {
        this.cacheTime = cacheTime;
    }
    
    public Cache getOScache()
    {
        if (null == cache)
        {
            synchronized (this)
            {
                if (null == cache)
                {
                    cache = super.createCache("oscache_invalid.properties");
                }
            }
        }
        return cache.getCache();
    }
    
    public MemcachedClient getMemcache()
    {
        return null;
    }
    
    public void putOs(String key)
    {
        this.putOs(key, CONTENT);
    }
    
    @Override
    public Object getOs(String key)
        throws NeedsRefreshException
    {
        return super.getOs(key);
    }
    
    @Override
    public String getPrefixMemKey()
    {
        return PREFIX_MEM_KEY;
    }
    
    @Override
    public String getPrefixOsKey()
    {
        return PREFIX_OS_KEY;
    }
}