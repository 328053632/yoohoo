package com.yoohoo.en.cache.base;

import com.opensymphony.oscache.base.Cache;
import com.opensymphony.oscache.base.NeedsRefreshException;
import com.opensymphony.oscache.general.GeneralCacheAdministrator;
import com.yoohoo.en.cache.IOScache;
import com.yoohoo.en.cache.util.CacheConfigPathTools;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URL;
import java.util.Arrays;
import java.util.Properties;

public abstract class BaseOScache extends BaseMemcache implements IOScache
{
	/** 日志对象 */
	private static Logger logger = Logger.getLogger(BaseOScache.class);

	/** 缓存命中次数 */
	private volatile long hitCount = 0L;

	/** 总请求次数 */
	private volatile long reqCount = 0L;

	/** 内容放入缓存的最大尝试次数 */
	private static final int MAX_RETRY_TIMES = 3;

	/** 命中率 */
	private double hitRate = 0D;

	/** 缓存对象 */
	protected GeneralCacheAdministrator cache = null;

	/** 缓存失效时间为-1时，表示永久有效 */
	private static final int VALID_FOREVER = -1;

	/***
	 * oscache缓存获取
	 * 
	 * @param key
	 * @return
	 * @throws NeedsRefreshException
	 */
	@Override
	public Object getOs(String key) throws NeedsRefreshException {
		// 入口日志
		if (logger.isDebugEnabled()) {
			logger.debug("Enter get() key = " + key);
		}

		if (null == this.getOScache()) {
			return null;
		}

		// 入参有效性检查
		if (StringUtils.isEmpty(key)) {
			if (logger.isDebugEnabled()) {
				logger.debug("Enter get() key is null");
			}
			return null;
		}

		int refreshTime = getRefreshTime();
		if (VALID_FOREVER > refreshTime) {
			refreshTime = VALID_FOREVER;
		}
		Object obj = null;
		try {
			obj = this.getOScache().getFromCache(this.getPrefixOsKey() + key,
					refreshTime);
			hitCount++;
		} catch (NeedsRefreshException e) {
			this.cancelUpdate(key);
			throw e;
		}

		return obj;
	}

	/***
	 * oscache缓存存放
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	@Override
	public boolean putOs(String key, Object value) {
		if (null == this.getOScache()) {
			return true;
		}

		// 入参有效性检查
		if (StringUtils.isEmpty(key)) {
			logger.error("input key is null when put");
			return false;
		}

		if (null == value) {
			logger.error("input value is null when put");
			return false;
		}

		int retryTimes = 0;

		while (MAX_RETRY_TIMES > retryTimes) {
			try {
				// 调用OSCache的方法将对象保存到缓存中，且不通知集群中的其他节点
				this.getOScache().putInCache(this.getPrefixOsKey() + key,
						value, null, null, Cache.NESTED_EVENT);

				// 成功放入缓存，则退出
				return true;
			} catch (Throwable ex) {
				// 捕获到异常，再试一次
				logger.error(
						"put value in oscache error! key = "
								+ this.getPrefixOsKey() + key
								+ ",retryTimes = " + retryTimes, ex);
				retryTimes++;
			}
		}

		if (logger.isDebugEnabled()) {
			logger.debug("Exit put");
		}
		return false;
	}

	/***
	 * oscache缓存删除
	 * 
	 * @param key
	 * @return
	 */
	@Override
	public boolean delOs(String key) {
		if (null == this.getOScache()) {
			return true;
		}
		// 入参有效性检查
		if (StringUtils.isEmpty(key)) {
			logger.error("input key is null when delete");
			return false;
		}

		this.getOScache().removeEntry(this.getPrefixOsKey() + key);
		return true;
	}

	/***
	 * OScache的命中率
	 * 
	 * @return
	 */
	public String getHitRate() {
		if (reqCount == 0L){
			return "RequestCount = " + reqCount + ",HitCount = " + hitCount
				+ ",HitRate = 0%";
		}
		hitRate = (double) hitCount / reqCount;
		BigDecimal bd = new BigDecimal(hitRate);
		bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);

		return "RequestCount = " + reqCount + ",HitCount = " + hitCount
				+ ",HitRate = " + bd.doubleValue() * 100 + "%";
	}

	/**
	 * 根据缓存配置文件初始化缓存对象
	 * 
	 * @param fileName
	 *            String 缓存配置文件名
	 * @return GeneralCacheAdministrator 通用缓存对象
	 */
	public GeneralCacheAdministrator createCache(String fileName) {
		// 入参有效性检查
		if ((null == fileName) || ("".equals(fileName.trim()))) {
			logger.error("oscache配置文件：" + fileName + " 不存在。");
			return null;
		}

		if (logger.isDebugEnabled()) {
			logger.info("create cahce fileName = " + fileName);
		}

		// 缓存配置文件路径
		String filePath = CacheConfigPathTools.getInstance().getOsCacheDir()
				+ fileName;
		GeneralCacheAdministrator cache = null;
		Properties property = new Properties();
		InputStream input = null;

		try {
			URL url = this.getClass().getResource(filePath);
			input = url.openStream();
			property.load(input);
			// 根据配置文件中约定的属性，构造缓存
			cache = new GeneralCacheAdministrator(property);
		} catch (IOException ex) {
			logger.error(
					"create cache failed Error Message = " + ex.toString(), ex);

			// 根据默认属性构造缓存
			cache = new GeneralCacheAdministrator(new Properties());
		} finally {
			try {
				if (null != input) {
					input.close();
				}
			} catch (IOException ex1) {
				logger.error(ex1.toString());
			}
		}

		return cache;
	}

	/**
	 * 缓存取消更新，并删除key对应的内容
	 * 
	 * @param key
	 *            String 内容的关键字
	 */
	protected void cancelUpdate(String key) {
		if (StringUtils.isNotEmpty(key)) {
			try {
				this.getOScache().cancelUpdate(this.getPrefixOsKey() + key);
			} catch (IllegalStateException ex) {
				logger.error("", ex);
			}
		}
	}

	/***
	 * oscache 失效时间
	 */
	protected abstract int getRefreshTime();

	/****************************
	 * 以group为删除缓存KEY 开始
	 *****************************/

	/***
	 * oscache缓存存放
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean putOs(String key, Object value, String[] groups) {

		// System.out.println("groups put = " + groups);
		if (null == this.getOScache()) {
			return true;
		}

		// 入参有效性检查
		if (StringUtils.isEmpty(key)) {
			logger.error("input key is null when put");
			return false;
		}

		if (null == value) {
			logger.error("input value is null when put");
			return false;
		}

		int retryTimes = 0;

		while (MAX_RETRY_TIMES > retryTimes) {
			try {
				// 调用OSCache的方法将对象保存到缓存中，且不通知集群中的其他节点
				/*
				 * this.getOScache().putInCache(this.getPrefixOsKey() + key,
				 * value, null, null, Cache.NESTED_EVENT);
				 */
				this.getOScache().putInCache(this.getPrefixOsKey() + key,
						value, groups);
				// 成功放入缓存，则退出
				return true;
			} catch (Throwable ex) {
				// 捕获到异常，再试一次
				logger.error(
						"put value in oscache error! key = "
								+ this.getPrefixOsKey() + key
								+ ",retryTimes = " + retryTimes + ",groups = "
								+ Arrays.toString(groups), ex);
				retryTimes++;
			}
		}

		if (logger.isDebugEnabled()) {
			logger.debug("Exit put");
		}
		return false;
	}

}
