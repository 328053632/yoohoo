package com.yoohoo.en.session;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 自定义session
 * @author Administrator
 *
 */
public class MySession implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String userInfoKey = "userInfo";
	private Map<String, Object> map;
	public MySession(){
		map = new HashMap<String, Object>();
	}
	
	public void setAttribute(String key, Object val){
		map.put(key, val);
	}
	
	public Object getAttribute(String key){
		return map.get(key);
	}
}
