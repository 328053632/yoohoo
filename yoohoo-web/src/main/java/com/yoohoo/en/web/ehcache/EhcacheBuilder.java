package com.yoohoo.en.web.ehcache;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;

public class EhcacheBuilder
{
    private EhcacheBuilder ins=new EhcacheBuilder();
    public static CacheManager manager = null;
    
    private static Cache baseCache = null;
    
    private static Cache businessAllocationCache = null;
    
    private static Cache smsCache = null;
    //当前获取到的最大的未释放的分配任务ID
    private long  maxAllocationId=-1;
    //当前获取到的最小的未释放的分配任务的ID
    private long  minAllocationId=-1;
    
    
    private EhcacheBuilder()
    {
    }
    
    public EhcacheBuilder getInstance()
    {
        return ins;
    }
    
    public static void init()
    {
        manager = CacheManager.create();
        baseCache = new Cache("baseCache", 0, false, true, 0, 0);
        manager.addCache(baseCache);
        
        businessAllocationCache = new Cache("businessAllocationCache", 0, false, true, 0, 0);
        manager.addCache(businessAllocationCache);
        
        smsCache = new Cache("smsCache", 0, false, false, 30 * 60, 30 * 60);
        manager.addCache(smsCache);
    }
    
    
    public static void distoryCache()
    {
        baseCache.dispose();
        businessAllocationCache.disableDynamicFeatures();
        smsCache.dispose();
    }
    
}
