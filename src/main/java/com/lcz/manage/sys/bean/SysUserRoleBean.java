package com.lcz.manage.sys.bean;


import com.lcz.manage.util.common.base.BaseBean;
import lombok.Data;

/**
 * 用户与角色对应关系
 * 
 * @author luchunzhou
 */
@Data
public class SysUserRoleBean extends BaseBean {

	/**
	 * 用户ID
	 */
	private String userId;

	/**
	 * 角色ID
	 */
	private String roleId;

}
