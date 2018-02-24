package com.lcz.manage.sys.bean;


import lombok.Data;

import java.io.Serializable;

/**
 * 用户与角色对应关系
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年9月18日 上午9:28:39
 */
@Data
public class SysUserRoleBean implements Serializable {

	private String id;

	/**
	 * 用户ID
	 */
	private String userId;

	/**
	 * 角色ID
	 */
	private String roleId;

}
