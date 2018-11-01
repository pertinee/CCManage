package com.lcz.manage.sys.controller;

import com.lcz.manage.sys.bean.SysUserBean;
import com.lcz.manage.util.ShiroUtils;

/**
 * Controller公共组件
 * 
 * @author luchunzhou
 */
public abstract class SysBaseController {

	protected SysUserBean getUser() {
		return ShiroUtils.getUser();
	}

	protected String getUserId() {
		return getUser().getUserId();
	}

	protected String getDeptId() {
		return getUser().getDeptId();
	}
}
