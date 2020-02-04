package com.yoohoo.en.cache.util;

import java.net.URL;

public class CacheConfigPathTools {

	private static CacheConfigPathTools tools = new CacheConfigPathTools();
	private URL cacheBaseDir;
	private URL memCacheDir;
	private String osCacheDir;

	private CacheConfigPathTools() {
		cacheBaseDir = this.getClass().getResource("/cache/cache.xml");
		memCacheDir = this.getClass().getResource("/cache/memcache_config/memcachedAllConfig.xml");
		osCacheDir = "/cache/oscache_config/";
	}

	public static CacheConfigPathTools getInstance() {
		return tools;
	}

	public URL getCacheBaseDir() {
		return cacheBaseDir;
	}

	public void setCacheBaseDir(URL cacheBaseDir) {
		this.cacheBaseDir = cacheBaseDir;
	}

	public URL getMemCacheDir() {
		return memCacheDir;
	}

	public void setMemCacheDir(URL memCacheDir) {
		this.memCacheDir = memCacheDir;
	}

	public String getOsCacheDir() {
		return osCacheDir;
	}

	public void setOsCacheDir(String osCacheDir) {
		this.osCacheDir = osCacheDir;
	}

}
