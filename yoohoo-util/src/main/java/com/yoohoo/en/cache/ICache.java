package com.yoohoo.en.cache;

import net.rubyeye.xmemcached.MemcachedClient;

import com.opensymphony.oscache.base.Cache;

public interface ICache
{
    public MemcachedClient getMemcache();
    
    public Cache getOScache();
}
