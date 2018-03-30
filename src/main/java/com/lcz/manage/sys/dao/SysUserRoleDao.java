package com.lcz.manage.sys.dao;

import com.lcz.manage.util.common.base.BaseDao;
import com.lcz.manage.sys.bean.SysUserRoleBean;

import java.util.List;

/**
 * 用户与角色对应关系
 * 
 * @author luchunzhou
 */
public interface SysUserRoleDao extends BaseDao<SysUserRoleBean> {
	
	/**
	 * 根据用户ID，获取角色ID列表
	 */
	List<String> queryRoleIdList(String userId);
}
