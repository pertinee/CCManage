package com.lcz.manage.sys.dao;

import com.lcz.manage.util.common.base.BaseDao;
import com.lcz.manage.sys.bean.SysRoleBean;

import java.util.List;

/**
 * 角色管理
 * 
 * @author luchunzhou
 */
public interface SysRoleDao extends BaseDao<SysRoleBean> {
	
	/**
	 * 查询用户创建的角色ID列表
	 */
	List<String> queryRoleIdList(String createUserId);
}
