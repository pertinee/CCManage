package com.lcz.manage.sys.dao;

import com.lcz.manage.util.common.base.BaseDao;
import com.lcz.manage.sys.bean.SysUserBean;

import java.util.List;
import java.util.Map;

/**
 * @author:luchunzhou
 * @date:2017/12/18
 * @time:16:02
 */
public interface SysUserDao extends BaseDao<SysUserBean> {

	/**
	 * 根据用户名查询用户
	 * @param username
	 * @return
	 */
	SysUserBean queryByUserName(String username);

	/**
	 * 查询用户的所有菜单ID
	 * @param userId
	 * @return
	 */
	List<String> queryAllMenuId(String userId);

	/**
	 * 查询用户的所有权限
	 * @param userId  用户ID
	 */
    List<String> queryAllPerms(String userId);

	/**
	 * 修改密码
	 * @param map
	 * @return
	 */
	int updatePassword(Map<String, Object> map);
}
