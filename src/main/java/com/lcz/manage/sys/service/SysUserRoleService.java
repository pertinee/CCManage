package com.lcz.manage.sys.service;

import java.util.List;


/**
 * 用户与角色对应关系
 * 
 * @author luchunzhou
 */
public interface SysUserRoleService {
	
	void saveOrUpdate(String userId, List<String> roleIdList);
	
	/**
	 * 根据用户ID，获取角色ID列表
	 */
	List<String> queryRoleIdList(String userId);
	
	void delete(String userId);
}
