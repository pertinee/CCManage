package com.lcz.manage.sys.dao;

import com.lcz.manage.util.common.base.BaseDao;
import com.lcz.manage.sys.bean.SysRoleMenuBean;

import java.util.List;

/**
 * 角色与菜单对应关系
 * 
 * @author luchunzhou
 */
public interface SysRoleMenuDao extends BaseDao<SysRoleMenuBean> {
	
	/**
	 * 根据角色ID，获取菜单ID列表
	 */
	List<String> queryMenuIdList(String roleId);
}
