package com.yoohoo.en.msys.dao;

import java.util.List;

import com.yoohoo.en.mcore.dao.BaseDao;
import com.yoohoo.en.msys.entity.SysMenuEntity;

/**
 * 菜单管理
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年9月18日 上午9:33:01
 */
public interface SysMenuDao extends BaseDao<SysMenuEntity> {
	
	/**
	 * 根据父菜单，查询子菜单
	 * @param parentId 父菜单ID
	 */
	List<SysMenuEntity> queryListParentId(Long parentId);
	
	/**
	 * 获取不包含按钮的菜单列表
	 */
	List<SysMenuEntity> queryNotButtonList();
}
