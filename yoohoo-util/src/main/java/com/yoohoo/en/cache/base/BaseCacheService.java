package com.yoohoo.en.cache.base;

import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.opensymphony.oscache.base.Cache;
import com.opensymphony.oscache.base.NeedsRefreshException;
import com.yoohoo.en.cache.FacadeCache;
import com.yoohoo.en.cache.bean.CacheConfig;
import com.yoohoo.en.cache.bean.InvalidCacheBean;
import com.yoohoo.en.cache.util.CacheConfigPathTools;
import com.yoohoo.en.cache.util.LockHandle;
import com.yoohoo.en.cache.util.MemcachedStrategyManager;
import com.yoohoo.en.cache.util.SharedLock;

import net.rubyeye.xmemcached.MemcachedClient;

public abstract class BaseCacheService extends BaseOScache implements FacadeCache
{
	/** 日志对象 */
	private static Logger logger = Logger.getLogger(BaseCacheService.class);
	

	// 缓存配置文件
	public static Map<String, CacheConfig> config =

	ParseCacheXML.loadMemcacheXML(CacheConfigPathTools.getInstance().getCacheBaseDir());

	private AtomicLong threadsCount = new AtomicLong(0);

	private InvalidDataBeanCache invalidDataCache = null;

	private Object lock = new Object();

	// 并发访问server限制,默认200
	private int maxThread = 200;

	// 获取数据的模式，默认OScache
	private String getCacheModel = "2";

	// 删除数据的模式，默认OScache
	private String deleteCacheModel = "2";

	// 更新数据的模式，默认OScache
	private String updateCacheModel = "2";

	// oscache配置文件名称
	private String oscacheFileName = "";

	// memcache对象名称
	private String memcacheName = "";

	// 无效缓存对象
	private InvalidCacheBean invalidBean = null;

	// memcache对象
	private MemcachedClient client = null;

	// 缓存名称
	private String cacheName = "";

	// OScache失效时间
	private int oscacheInvalidTime = 0;

	// Memcache失效时间
	private int memcacheInvalidTime = 0;

	// 悬锁控制调用server接口
	private boolean needLock;

	private SharedLock sharedLock = new SharedLock();

	protected BaseCacheService(String cacheName) {
		this.cacheName = cacheName;

		if (null == config.get(cacheName)) {
			String msg = "cacheName=" + cacheName + " 缓存配置信息不存在。";
			logger.error(msg);
		} else {
			if (!initAndcheckParam()) {
				logger.error("cacheName=" + cacheName + "配置信息不正确。");
			}
		}
	}

	/**
	 * 初始化、校验配置文件的有效性
	 * 
	 * @return
	 */
	private boolean initAndcheckParam() {
		// 加载memcached配置文件信息
		// MemcacheConfigPath.load(SystemConfig.getInstance().getMemcachePath()
		// + "memcachedAllConfig.xml",
		// SystemConfig.getInstance().getMemcachePath()
		// + "memcached.properties");

		// 初始化配置信息
		this.maxThread = config.get(cacheName).getMaxThread();
		this.getCacheModel = config.get(cacheName).getGetcaheModel();
		this.deleteCacheModel = config.get(cacheName).getDeleteCacheModel();
		this.updateCacheModel = config.get(cacheName).getUpdateCacheModel();
		this.oscacheFileName = config.get(cacheName).getOscacheFile();
		this.memcacheName = config.get(cacheName).getMemcacheName();
		this.invalidBean = config.get(cacheName).getInvalidBean();
		this.oscacheInvalidTime = config.get(cacheName).getOscacheInvalidTime();
		this.memcacheInvalidTime = config.get(cacheName).getMemcacheInvalidTime();
		this.needLock = config.get(cacheName).isNeedLock();

		/** memcache缓存失效时间为0时，表示永久有效 */
		if (this.memcacheInvalidTime < 0) {
			this.memcacheInvalidTime = 0;
		} else {
			// 转换为分钟
			this.memcacheInvalidTime = this.memcacheInvalidTime * 60;
		}

		/** oscache缓存失效时间为-1时，表示永久有效 */
		if (this.oscacheInvalidTime <= 0) {
			this.oscacheInvalidTime = -1;
		} else {
			// 转换为分钟
			this.oscacheInvalidTime = this.oscacheInvalidTime * 60;
		}

		if (StringUtils.isEmpty(this.getCacheModel) || StringUtils.isEmpty(this.deleteCacheModel)
				|| StringUtils.isEmpty(this.updateCacheModel)) {
			return false;
		}

		// oscache和memcache配置不能同时为空
		if (StringUtils.isEmpty(this.oscacheFileName) && StringUtils.isEmpty(this.memcacheName)) {
			return false;
		}

		// 使用memcache情况校验
		if (StringUtils.isNotEmpty(this.memcacheName) && !"2".equals(this.getCacheModel)
				&& !"2".equals(this.deleteCacheModel) && !"2".equals(this.updateCacheModel)) {
			client = MemcachedStrategyManager.getInstance().getMemcachedClient(this.memcacheName);
			if (client == null) {
				logger.error("memcache=" + this.memcacheName + "is not corrected!");
				return false;
			}
		}

		return true;
	}

	/****
	 * 获取数据
	 * 
	 * @param key
	 * @param array
	 * @return
	 */
	public Object get(String key, Object[] array) {
		Object object = null;
		Object oldValue = null;

		// 获取缓存数据
		try {
			object = getCacheData(key, array);
		} catch (NeedsRefreshException e) {
			oldValue = e.getCacheContent();
		}

		// 从数据库获取数据
		if (object == null) {

			// 是否无效key值
			if (!isInvalidKey(key)) {
				return null;
			}
			object = fetchData(key, array, oldValue);
		}

		return object;
	}

	/****
	 * 从缓存中获取数据
	 * 
	 * @param key
	 * @param array
	 * @return
	 */
	private Object getCacheData(String key, Object[] array) throws NeedsRefreshException {
		Object object = null;

		if (GET_CACHE_ALL.equals(getCacheModel)) {
			NeedsRefreshException ex = null;
			// 从oscache中获取数据
			try {
				object = this.getOs(key);
			} catch (NeedsRefreshException e) {
				ex = e;
			}

			// 从memcache中获取数据
			if (null == object) {
				object = this.getMem(key);

				// 类型转换
				if (object != null) {
					object = classSwitch(object);
				}
			}
			if (object == null && ex != null) {
				throw ex;
			}
		} else if (GET_MEMCACHE_ONLY.equals(getCacheModel)) {
			// 从memcache获取数据
			object = this.getMem(key);

			if (object != null) {
				object = classSwitch(object);
			}
		} else if (GET_OSCACHE_ONLY.equals(getCacheModel)) {
			// 从OScache获取数据
			try {
				object = this.getOs(key);

			} catch (NeedsRefreshException e) {
				throw e;
			}
		}

		return object;
	}

	/***
	 * 存放数据
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean put(String key, Object value) {

		if (logger.isDebugEnabled()) {
			logger.debug("更新缓存：" + JSON.toJSONString(new Object[][] { { "key", key }, { "value", value } }));
		}

		if (value == null) {
			return false;
		}
		if (UPDATE_CACHE_ALL.equals(updateCacheModel)) {
			this.putMem(key, value, memcacheInvalidTime);
			this.putOs(key, value);
		} else if (UPDATE_MEMCACHE_ONLY.equals(updateCacheModel)) {
			this.putMem(key, value, memcacheInvalidTime);
		} else if (UPDATE_OSCACHE_ONLY.equals(updateCacheModel)) {
			this.putOs(key, value);
		}
		return true;
	}

	/***
	 * 删除数据
	 * 
	 * @param key
	 * @return
	 */
	public boolean delete(String key) {
		if (DEl_CACHE_ALL.equals(deleteCacheModel)) {
			this.delMem(key);
			this.delOs(key);
		} else if (DEl_MEMCACHE_ONLY.equals(deleteCacheModel)) {
			this.delMem(key);
		} else if (DEl_OSCACHE_ONLY.equals(deleteCacheModel)) {
			this.delOs(key);
		}
		return true;
	}

	/***
	 * 从数据库获取数据
	 * 
	 * @param key
	 * @param array
	 * @return
	 */
	public Object fetchData(String key, Object[] array, Object oldValue) {
		if (StringUtils.isEmpty(key)) {
			return null;
		}

		// 加锁再刷新，保护server
		LockHandle handle = lock(key);
		Object value = null;

		try {
			if (threadsCount.get() >= maxThread) {
				logger.error("server request past maxThread Key = " + key + " and refreshTimes = "
						+ String.valueOf(oscacheInvalidTime));
				return null;
			} else if (threadsCount.get() < 0) {
				threadsCount.set(1);
			} else {
				threadsCount.incrementAndGet();
			}

			if (null == value) {
				// 还没有数据，从数据库获取数据
				value = fetchNewData(key, array, oldValue);
			}
		} finally {
			unlock(handle);
			threadsCount.decrementAndGet();
		}
		return value;
	}

	/***
	 * 从server获取数据
	 * 
	 * @param key
	 * @param array
	 * @param oldValue
	 * @return
	 */
	private Object fetchNewData(String key, Object[] array, Object oldValue) {
		int refreshMode = config.get(cacheName).getRefreshMode();

		LockHandle handle = null;
		try {
			handle = lock(key);

			// 旧的缓存数据为null， 默认为同步方式
			if (oldValue == null) {
				refreshMode = REFRESH_MODE_SYNC;
			}

			if (refreshMode == REFRESH_MODE_SYNC) {
				Object obj = fetchDataFromServer(key, array);

				if (null != obj) {
					put(key, obj);

				} else if (config.get(cacheName).isNeedLock()) {
					addInvalidData(key);
				}
				return obj;
			} else {
				return oldValue;
			}
		} finally {
			unlock(handle);
		}
	}

	/***
	 * 加入无效缓存参数
	 * 
	 * @param key
	 */
	private void addInvalidData(String key) {
		if (null != invalidBean) {
			// 构造无效缓存
			if (null == invalidDataCache) {
				synchronized (lock) {
					if (null == invalidDataCache) {
						invalidDataCache = new InvalidDataBeanCache();
						invalidDataCache.getOScache();

						// 失效时间至少一分钟
						if (invalidBean.getInvalidTime() < 1) {
							invalidBean.setInvalidTime(1);
						}
						invalidDataCache.setCacheTime(invalidBean.getInvalidTime());
					}
				}
			}
			if (null != invalidDataCache && !invalidDataCache.contains(key)) {
				invalidDataCache.putOs(key);
			}
		}
	}

	@Override
	public Cache getOScache() {
		if (null == cache) {
			synchronized (this) {
				if (null == cache) {
					cache = super.createCache(oscacheFileName);
				}
			}
		}
		if (cache == null) {
			logger.error("OS cache缓存=" + cacheName + " 初始化失败!!");
			return null;
		}

		return cache.getCache();
	}

	private LockHandle lock(String key) {
		LockHandle handle = null;

		if (needLock) {
			handle = sharedLock.lock(key);
		}
		return handle;
	}

	private void unlock(LockHandle handle) {
		if (handle != null) {
			handle.release();
		}
	}

	/****
	 * Memcache获取的对象进行转换
	 * 
	 * @param value
	 * @return
	 */
	public Object classSwitch(Object value) {
		return value;
	}

	/***
	 * oscache 失效时间
	 */
	@Override
	protected int getRefreshTime() {
		return oscacheInvalidTime;
	}

	/***
	 * 是否有效key
	 * 
	 * @param key
	 * @return
	 */
	private boolean isInvalidKey(String key) {
		if (null == this.invalidDataCache) {
			return true;
		}

		return !invalidDataCache.contains(key);
	}

	@Override
	public MemcachedClient getMemcache() {
		return client;
	}

	/***
	 * 查询数据库数据
	 * 
	 * @param key
	 * @param params
	 * @return
	 */
	public abstract Object fetchDataFromServer(String key, Object[] params);

}
