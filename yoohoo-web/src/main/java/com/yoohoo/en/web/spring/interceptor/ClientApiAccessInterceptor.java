package com.yoohoo.en.web.spring.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class ClientApiAccessInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		if (this.doUserLoginByHeader(request, response)) {
			return super.preHandle(request, response, handler);
		}
		return false;
	}

	private boolean doUserLoginByHeader(HttpServletRequest request, HttpServletResponse response) {
		// 判断客户端的请求头中是否包含合法的用户唯一标识，如果包含，判断用户是否注册，如果没有注册则完成注册，如果已注册将用户信息放入到session中
		return true;
	}

}
