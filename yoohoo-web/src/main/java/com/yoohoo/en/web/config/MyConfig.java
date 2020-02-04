package com.yoohoo.en.web.config;

import java.io.InputStream;
import java.util.Properties;

public class MyConfig {

	public static String yoohoooManagerUrl = null;
	static {
		Properties prop = new Properties();
		    try {
		          // 通过输入缓冲流进行读取配置文件
		          InputStream InputStream = MyConfig.class.getResourceAsStream("/config.properties");;
		          // 加载输入流
		          prop.load(InputStream);
		          // 根据关键字获取value值
		          yoohoooManagerUrl = prop.getProperty("yoohoo.manager.url");
		        } catch (Exception e) {
		          e.printStackTrace();
		        }
	}
}
