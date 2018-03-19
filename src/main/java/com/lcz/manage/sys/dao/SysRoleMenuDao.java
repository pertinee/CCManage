package com.lcz.manage.sys.dao;

import com.lcz.manage.util.common.base.BaseDao;
import com.lcz.manage.sys.bean.SysRoleMenuBean;

import java.util.List;

/**
 * 角色与菜单对应关系
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年9月18日 上午9:33:46
 */
public interface SysRoleMenuDao extends BaseDao<SysRoleMenuBean> {
	
	/**
	 * 根据角色ID，获取菜单ID列表
	 */
	List<String> queryMenuIdList(String roleId);
}
