package com.yoohoo.en.cache.util;

import com.yoohoo.en.cache.bean.KryoTranscoder;
import com.yoohoo.en.cache.bean.MemcachePoolBean;
import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.MemcachedClientBuilder;
import net.rubyeye.xmemcached.XMemcachedClientBuilder;
import net.rubyeye.xmemcached.command.BinaryCommandFactory;
import net.rubyeye.xmemcached.impl.KetamaMemcachedSessionLocator;
import net.rubyeye.xmemcached.utils.AddrUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MemcachedStrategyManager {
	/** 日志对象 */
	private static Logger logger = Logger.getLogger(MemcachedStrategyManager.class);

	private static MemcachedStrategyManager instance = new MemcachedStrategyManager();

	private Map<String, MemcachedClient> memcachedClientmap = new HashMap<String, MemcachedClient>();

	static {
		// 读取memcahedAllconfig.xml配置
		// /WEB-INF/classes/cache/memcache_config/
		MemcacheConfigPath.load( CacheConfigPathTools.getInstance().getMemCacheDir());

	}

	public static MemcachedStrategyManager getInstance() {
		return instance;
	}

	public MemcachedClient getMemcachedClient(String cacheName) {
		MemcachedClient client = memcachedClientmap.get(cacheName);
		if (client == null) {
			client = createMemcachedClient(cacheName);
			memcachedClientmap.put(cacheName, client);
		}
		return client;
	}

	// 构造memcach客户端
	private static MemcachedClient createMemcachedClient(String cacheName) {
		if (StringUtils.isEmpty(cacheName)) {
			logger.error("MemcachedStrategyManager.createMemcachedClient method cacheName is null");
			return null;
		}
		MemcachedClient client = null;
		try {
			String poolId = MemcacheConfigPath.getMemcaheMap().get(cacheName);

			MemcachePoolBean pbean = MemcacheConfigPath.getPoolMap().get(poolId);

			MemcachedClientBuilder builder = new XMemcachedClientBuilder(
					AddrUtil.getAddressMap(StringUtils.trim(pbean.getAddressList())));

			builder.setSessionLocator(new KetamaMemcachedSessionLocator());

			builder.setCommandFactory(new BinaryCommandFactory());

			builder.setConnectionPoolSize(pbean.getConnectSize());

			if (!(pbean.isDefaultTranscode())) {
				int initSize = pbean.getInitBufSize();
				int maxSize = pbean.getMaxBufSize();
				KryoTranscoder transcoder = new KryoTranscoder(initSize, maxSize);

				transcoder.setCompressionThreshold(pbean.getCompressionThreshold());
				builder.setTranscoder(transcoder);
			}

			builder.setFailureMode(pbean.isFailureMode());

			try {
				client = builder.build();

				client.setMergeFactor(pbean.getMergeFactor());
				client.setConnectTimeout(pbean.getConnectTimeout());
				client.setName(pbean.getPoolId());
			} catch (IOException e) {
				logger.error("MemcachedStrategyManager.createMemcachedClient is error", e);
			}
		} catch (Exception e) {
			logger.error("MemcachedStrategyManager.createMemcachedClient is error", e);
		}

		return client;
	}
}