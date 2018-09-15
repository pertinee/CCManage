package com.lcz.manage.sys.bean;


import com.lcz.manage.util.common.base.BaseBean;

/**
 * 角色与菜单对应关系
 * 
 * @author luchunzhou
 */
public class SysRoleMenuBean extends BaseBean {

	/**
	 * 角色ID
	 */
	private String roleId;

	/**
	 * 菜单ID
	 */
	private String menuId;

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
}
