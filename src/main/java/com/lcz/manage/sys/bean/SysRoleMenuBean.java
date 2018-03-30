package com.lcz.manage.sys.bean;


import com.lcz.manage.util.common.base.BaseBean;
import lombok.Data;

/**
 * 角色与菜单对应关系
 * 
 * @author luchunzhou
 */
@Data
public class SysRoleMenuBean extends BaseBean {

	/**
	 * 角色ID
	 */
	private String roleId;

	/**
	 * 菜单ID
	 */
	private String menuId;

}
