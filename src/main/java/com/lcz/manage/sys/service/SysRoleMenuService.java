package com.lcz.manage.sys.service;

import java.util.List;


/**
 * 角色与菜单对应关系
 * 
 * @author luchunzhou
 */
public interface SysRoleMenuService {
	
	void saveOrUpdate(String roleId, List<String> menuIdList);
	
	/**
	 * 根据角色ID，获取菜单ID列表
	 */
	List<String> queryMenuIdList(String roleId);
	
}
