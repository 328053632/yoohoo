package com.yoohoo.en.mcore.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yoohoo.en.mcore.utils.ShiroUtils;
import com.yoohoo.en.msys.entity.SysUserEntity;

/**
 * Controller公共组件
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年11月9日 下午9:42:26
 */
public abstract class AbstractController {
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	protected SysUserEntity getUser() {
		return ShiroUtils.getUserEntity();
	}

	protected Long getUserId() {
		return getUser().getUserId();
	}
}
