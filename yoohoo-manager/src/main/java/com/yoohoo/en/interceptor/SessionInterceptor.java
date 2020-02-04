package com.yoohoo.en.interceptor;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.yoohoo.en.constant.ResponseCode;
import com.yoohoo.en.mcore.utils.R;
import com.yoohoo.en.service.SessionSerivce;
import com.yoohoo.en.session.MySession;

public class SessionInterceptor implements HandlerInterceptor {

	final static Logger logger = LoggerFactory.getLogger(SessionInterceptor.class);

	@Autowired
	private SessionSerivce sessionService;
	
	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		
	}

	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res,
			Object obj) throws Exception {
		String sessionId = null;
		sessionId = req.getHeader("sessionId");
		if(StringUtils.isBlank(sessionId)){
			printErrorMessage(req,res, JSON.toJSONString(R.error(Integer.parseInt(ResponseCode.PARAM_ERROR.getCode()),"sessionId不可为空")));
			return false;
		}
		MySession session = sessionService.getSession(sessionId);
		if(session == null){
			printErrorMessage(req,res, JSON.toJSONString(R.error(Integer.parseInt(ResponseCode.USER_NOT_LOGIN.getCode()),"sessionId失效")));
			return false;
		}
	    Object userObj = session.getAttribute(MySession.userInfoKey);
	       if(userObj == null){
			printErrorMessage(req,res, JSON.toJSONString(R.error(Integer.parseInt(ResponseCode.USER_NOT_LOGIN.getCode()),"用户未登陆")));
			return false;
		}
		if(StringUtils.isBlank(sessionId)){
			sessionId = req.getAttribute("sessionId")+"";
		}
		if(StringUtils.isBlank(sessionId)){
			printErrorMessage(req,res, JSON.toJSONString(R.error(Integer.parseInt(ResponseCode.USER_NOT_LOGIN.getCode()),"无效的请求")));
			return false;
		}
        res.addCookie(new Cookie("sessionId",sessionId));
		return true;
	}
	
	/**
	 * 打印错误信息
	 * @param resp
	 * @param message
	 */
	private void printErrorMessage(HttpServletRequest req, ServletResponse resp, String message) {
		logger.info(req.getRequestURI() + "："+message);
		resp.setCharacterEncoding("UTF-8");
		PrintWriter pw = null;
		try {
			pw = resp.getWriter();
			pw.write(message);
			pw.flush();
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
