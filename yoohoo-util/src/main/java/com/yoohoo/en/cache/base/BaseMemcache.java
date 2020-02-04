package com.yoohoo.en.cache.base;

import com.yoohoo.en.cache.ICache;
import com.yoohoo.en.cache.IMemcache;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

public abstract class BaseMemcache implements IMemcache, ICache
{
	/** 日志对象 */
	private static Logger logger = Logger.getLogger(BaseMemcache.class);

	/** 内容放入缓存的最大尝试次数 */
	private static final int MAX_RETRY_TIMES = 3;

	/**
	 * 从Memcache中取数据
	 */
	@Override
	public Object getMem(String key) {
		// 入口日志
		if (logger.isDebugEnabled()) {
			logger.debug("Enter getDataFromCache() key = " + key);
		}

		// 入参有效性检查
		if (StringUtils.isEmpty(key)) {
			if (logger.isDebugEnabled()) {
				logger.debug("Enter getM() key is null");
			}
			return null;
		}

		Object object = null;

		try {
			if (null == this.getMemcache()) {
				logger.error("Cache Name = " + this.getClass().getName()
						+ "Memcached getMemcache() is null");
				return null;
			}

			object = this.getMemcache().get(this.getPrefixMemKey() + key);
		} catch (Exception e) {
			logger.error("getDataFromCache is fail", e);
		}

		// 出口日志
		if (logger.isDebugEnabled()) {
			logger.debug("Exit getM() key" + this.getPrefixMemKey() + key);
		}

		return object;
	}

	public boolean putMem(String key, Object value, int memcacheInvalidTime) {
		int retryTimes = 0;

		while (MAX_RETRY_TIMES > retryTimes) {
			try {
				// 调用OSCache的方法将对象保存到缓存中，且不通知集群中的其他节点
				if (null == this.getMemcache()) {
					logger.error("Cache Name = " + this.getClass().getName()
							+ "Memcached getIMemcachedClient() is null");
					return true;
				}
				this.getMemcache().set(this.getPrefixMemKey() + key,
						memcacheInvalidTime, value);

				return true;
			} catch (Exception ex) {
				logger.error("key = " + this.getPrefixMemKey() + key
						+ "  and retryTimes = " + retryTimes, ex);

				// 捕获到异常，再试一次
				retryTimes++;
			}
		}
		return false;
	}

	public boolean delMem(String key) {
		if (StringUtils.isNotEmpty(key)) {
			int retryTimes = 0;

			while (MAX_RETRY_TIMES > retryTimes) {
				try {
					if (null == this.getMemcache()) {
						logger.error("Cache Name = "
								+ this.getClass().getName()
								+ "Memcached deleteFromMemcache() is null");
						return false;
					}
					this.getMemcache().delete(this.getPrefixMemKey() + key);
					return true;
				} catch (Exception e) {
					logger.info("delete data from memcached failed!key = "
							+ key + " and retryTimes = " + retryTimes, e);
					retryTimes++;
				}
			}
		}

		return false;
	}
}