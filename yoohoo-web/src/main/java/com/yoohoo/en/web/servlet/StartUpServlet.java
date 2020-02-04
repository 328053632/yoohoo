package com.yoohoo.en.web.servlet;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import java.util.Properties;

/**
 * 对系统进行一些初始化配置
 * 
 * @author heyunliang
 * 
 */
public class StartUpServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger.getLogger(StartUpServlet.class);

	public static ApplicationContext applicationContext;

	private ServletContext application;

	public void init(ServletConfig config) throws ServletException {
		log.info("init begin ... ");

		application = config.getServletContext();
		application.setAttribute("ctxPath", application.getContextPath());
		// 设置服务器版本号
		application.setAttribute("_dt",System.currentTimeMillis());
		applicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(config.getServletContext());

		String projectName = config.getServletContext().getContextPath();
		log.info("projectName = " + projectName);

		String webAppRootPath = config.getServletContext().getRealPath("/");
		log.info("webAppRootPath = " + webAppRootPath);

		log.info("init success ");
	}

	public void destroy() {
		log.info("开始停止应用");
		application.removeAttribute("ctxPath");
		super.destroy();
	}

	public static String getConfig(String key) {

		Properties properties = (Properties) applicationContext.getBean("configProperties");
		if (null != properties && !properties.isEmpty()) {
			return (String) properties.get(key);

		}
		log.error("未获取到配置项信息！ key:"+key);
		return null;

	}

}