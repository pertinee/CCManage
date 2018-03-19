package com.lcz.manage.sys.bean;


import com.lcz.manage.util.common.base.BaseBean;
import lombok.Data;

/**
 * 用户与角色对应关系
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年9月18日 上午9:28:39
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
