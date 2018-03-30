package com.lcz.manage.sys.service;



import com.lcz.manage.sys.bean.SysDeptBean;

import java.util.List;
import java.util.Map;

/**
 * 部门管理
 * 
 * @author luchunzhou
 */
public interface SysDeptService {
	
	SysDeptBean queryObject(String deptId);
	
	List<SysDeptBean> queryList(Map<String, Object> map);

	void save(SysDeptBean sysDept);
	
	void update(SysDeptBean sysDept);
	
	void delete(String deptId);

	/**
	 * 查询子部门ID列表
	 * @param parentId  上级部门ID
	 */
	List<String> queryDetpIdList(String parentId);

	/**
	 * 获取子部门ID(包含本部门ID)，用于数据过滤
	 */
	String getSubDeptIdList(String deptId);

}
