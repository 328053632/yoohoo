package com.yoohoo.en.service.impl;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.yoohoo.en.service.SessionSerivce;
import com.yoohoo.en.session.MySession;

/**
 * 自定义session服务类
 * 
 * @author lhy
 *
 */
@Service
@CacheConfig(cacheNames = {"sessionCache"})
public class SessionSerivceImpl implements SessionSerivce {

	@Override
	@Cacheable(key = "#p0")
	public MySession getSession(String sessionId) {
		return null;
	}

	@Override
	@CachePut(key = "#p0")
	public MySession createSession(String sessionId) {
		return new MySession();
	}
}
