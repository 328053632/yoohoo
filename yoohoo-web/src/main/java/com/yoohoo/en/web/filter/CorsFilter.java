package com.yoohoo.en.web.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.InputStream;
import java.util.Properties;

/**
 * 跨域资源共享  解决前后端分离
 * @author 35168
 *
 */
public class CorsFilter extends OncePerRequestFilter{
	public static String corsOrigin = null;
	static {
		Properties prop = new Properties();
		    try {
		          // 通过输入缓冲流进行读取配置文件
		          InputStream InputStream = CorsFilter.class.getResourceAsStream("/config.properties");;
		          // 加载输入流
		          prop.load(InputStream);
		          // 根据关键字获取value值
		          corsOrigin = prop.getProperty("cors.origin");
		        } catch (Exception e) {
		          e.printStackTrace();
		        }
	}
	private static Logger logger = LoggerFactory.getLogger(CorsFilter.class);
 
	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
//		String origin = corsOrigin;
		String origin = request.getHeader("Origin");
		if(null != origin && !"".equals(origin)){
			logger.debug("跨域资源共享  解决前后端分离Access-Control-Allow-Origin："+origin+"请求uri：" + request.getRequestURI());
			response.addHeader("Access-Control-Allow-Origin",origin);
			response.addHeader("Access-Control-Allow-Credentials","true");//允许携带cookie
			response.setHeader("Access-Control-Allow-Headers", "Content-Type,x-requested-with");
			response.setHeader("Access-Control-Allow-Methods", "OPTIONS, GET, PUT, POST, DELETE");
			if(request.getMethod().equals(RequestMethod.OPTIONS.name())){
				response.setStatus(200);
				response.setHeader("Access-Control-Max-Age", "86400");
				return;
			}
		}
	    filterChain.doFilter(request,response);
	}
}