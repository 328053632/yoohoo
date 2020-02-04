package com.yoohoo.en.cache.bean;


public class CacheConfig
{
    /** 缓存名称 */
    private String cacheName;
    
    /** 获取缓存模式 */
    private String getcaheModel;
    
    /** 删除缓存模式 */
    private String deleteCacheModel;
    
    /** 更新缓存模式 */
    private String updateCacheModel;
    
    /** 并发悬锁控制 */
    private boolean needLock;
    
    /** 并发线程数 */
    private int maxThread;
    
    /** OSCache更新模式 */
    private int refreshMode;
    
    /** OScache初始化配置文件 */
    private String oscacheFile;
    
    /** memcache名称 */
    private String memcacheName;
    
    /** 无效缓存信息 */
    private InvalidCacheBean invalidBean;
    
    // OScache失效时间
    private int oscacheInvalidTime;
    
    // Memcache失效时间
    private int memcacheInvalidTime;
    
    public InvalidCacheBean getInvalidBean()
    {
        return invalidBean;
    }
    
    public void setInvalidBean(InvalidCacheBean invalidBean)
    {
        this.invalidBean = invalidBean;
    }
    
    public String getGetcaheModel()
    {
        return getcaheModel;
    }
    
    public void setGetcaheModel(String getcaheModel)
    {
        this.getcaheModel = getcaheModel;
    }
    
    public String getDeleteCacheModel()
    {
        return deleteCacheModel;
    }
    
    public void setDeleteCacheModel(String deleteCacheModel)
    {
        this.deleteCacheModel = deleteCacheModel;
    }
    
    public String getUpdateCacheModel()
    {
        return updateCacheModel;
    }
    
    public void setUpdateCacheModel(String updateCacheModel)
    {
        this.updateCacheModel = updateCacheModel;
    }
    
    public boolean isNeedLock()
    {
        return needLock;
    }
    
    public void setNeedLock(boolean needLock)
    {
        this.needLock = needLock;
    }
    
    public int getMaxThread()
    {
        return maxThread;
    }
    
    public void setMaxThread(int maxThread)
    {
        this.maxThread = maxThread;
    }
    
    public int getRefreshMode()
    {
        return refreshMode;
    }
    
    public void setRefreshMode(int refreshMode)
    {
        this.refreshMode = refreshMode;
    }
    
    public String getCacheName()
    {
        return cacheName;
    }
    
    public void setCacheName(String cacheName)
    {
        this.cacheName = cacheName;
    }
    
    public String getOscacheFile()
    {
        return oscacheFile;
    }
    
    public void setOscacheFile(String oscacheFile)
    {
        this.oscacheFile = oscacheFile;
    }
    
    public String getMemcacheName()
    {
        return memcacheName;
    }
    
    public void setMemcacheName(String memcacheName)
    {
        this.memcacheName = memcacheName;
    }
    
    public int getOscacheInvalidTime()
    {
        return oscacheInvalidTime;
    }
    
    public void setOscacheInvalidTime(int oscacheInvalidTime)
    {
        this.oscacheInvalidTime = oscacheInvalidTime;
    }
    
    public int getMemcacheInvalidTime()
    {
        return memcacheInvalidTime;
    }
    
    public void setMemcacheInvalidTime(int memcacheInvalidTime)
    {
        this.memcacheInvalidTime = memcacheInvalidTime;
    }
}