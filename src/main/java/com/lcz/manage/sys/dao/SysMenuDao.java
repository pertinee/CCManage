package com.lcz.manage.sys.dao;

import com.lcz.manage.util.common.base.BaseDao;
import com.lcz.manage.sys.bean.SysMenuBean;

import java.util.List;

/**
 * 菜单管理
 * 
 * @author luchunzhou
 */
public interface SysMenuDao extends BaseDao<SysMenuBean> {

	/**
	 * 根据父菜单，查询子菜单
	 * @param parentId 父菜单ID
	 */
	List<SysMenuBean> queryListParentId(String parentId);

	/**
	 * 获取不包含按钮的菜单列表
	 */
	List<SysMenuBean> queryNotButtonList();

	/**
	 * 查询用户的权限列表
	 */
	List<SysMenuBean> queryUserList(String userId);

	/**
	 * 查询系统菜单（不可删除）
	 */
    List<SysMenuBean> querySysMenuList();
}
