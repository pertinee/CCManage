package com.lcz.manage.sys.service;

import com.lcz.manage.sys.bean.SysRoleBean;

import java.util.List;
import java.util.Map;


/**
 * 角色
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年9月18日 上午9:42:52
 */
public interface SysRoleService {
	
	SysRoleBean queryObject(String roleId);
	
	List<SysRoleBean> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(SysRoleBean role);
	
	void update(SysRoleBean role);
	
	void deleteBatch(String[] roleIds);
	
	/**
	 * 查询用户创建的角色ID列表
	 */
	List<String> queryRoleIdList(String createUserId);
}
