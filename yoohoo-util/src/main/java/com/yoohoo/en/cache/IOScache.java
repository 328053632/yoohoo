package com.yoohoo.en.cache;

import com.opensymphony.oscache.base.NeedsRefreshException;

public interface IOScache {
	/***
	 * oscache缓存获取
	 * 
	 * @param key
	 * @param refreshTime
	 * @return
	 * @throws NeedsRefreshException
	 */
	public Object getOs(String key) throws NeedsRefreshException;

	/***
	 * oscache缓存存放
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean putOs(String key, Object value);

	/***
	 * oscache缓存删除
	 * 
	 * @param key
	 * @return
	 */
	public boolean delOs(String key);

	/***
	 * oscache key的前缀
	 * 
	 * @return
	 */
	public String getPrefixOsKey();
}