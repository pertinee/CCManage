package com.lcz.manage.sys.controller;

import com.lcz.manage.sys.bean.SysUserBean;
import com.lcz.manage.util.ShiroUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Controller公共组件
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年11月9日 下午9:42:26
 */
public abstract class SysBaseController {
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
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
