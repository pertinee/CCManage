package com.lcz.manage.sys.bean;


import com.lcz.manage.util.common.base.BaseBean;

/**
 * 用户与角色对应关系
 * 
 * @author luchunzhou
 */
public class SysUserRoleBean extends BaseBean {

	/**
	 * 用户ID
	 */
	private String userId;

	/**
	 * 角色ID
	 */
	private String roleId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
}
