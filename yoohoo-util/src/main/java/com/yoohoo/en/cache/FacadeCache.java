package com.yoohoo.en.cache;

public interface FacadeCache {

	 /** 删除memcache数据 */
    String DEl_MEMCACHE_ONLY = "1";
    
    /** 删除OSCACHE数据 */
    String DEl_OSCACHE_ONLY = "2";
    
    /** 删除MEMCACHE 和 OSCACHE数据 */
    String DEl_CACHE_ALL = "3";
    
    /** 更新memcache数据 */
    String UPDATE_MEMCACHE_ONLY = "1";
    
    /** 更新OSCACHE数据 */
    String UPDATE_OSCACHE_ONLY = "2";
    
    /** 更新MEMCACHE 和 OSCACHE数据 */
    String UPDATE_CACHE_ALL = "3";
    
    /** 获取MEMCACHE数据 */
    String GET_MEMCACHE_ONLY = "1";
    
    /** 获取OSCACHE数据 */
    String GET_OSCACHE_ONLY = "2";
    
    /** 获取MEMCACHE 和 OSCACHE缓存数据 */
    String GET_CACHE_ALL = "3";
    
    /** 同步调用server接口，刷新内容 */
    int REFRESH_MODE_SYNC = 1;
    
    /** 异步调用server接口，刷新内容 */
    int REFRESH_MODE_ASYNC = 2;
    
}
