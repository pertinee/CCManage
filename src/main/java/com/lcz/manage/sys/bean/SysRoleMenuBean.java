package com.lcz.manage.sys.bean;


import lombok.Data;

import java.io.Serializable;

/**
 * 角色与菜单对应关系
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年9月18日 上午9:28:13
 */
@Data
public class SysRoleMenuBean implements Serializable {

	private String id;

	/**
	 * 角色ID
	 */
	private String roleId;

	/**
	 * 菜单ID
	 */
	private String menuId;

}