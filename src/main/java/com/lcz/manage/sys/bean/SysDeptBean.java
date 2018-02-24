package com.lcz.manage.sys.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.List;


/**
 * 部门管理
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-06-20 15:23:47
 */
@Data
public class SysDeptBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//部门ID
	private String deptId;
	//上级部门ID，一级部门为0
	private String parentId;
	//部门名称
	private String name;
	//上级部门名称
	private String parentName;
	//排序
	private Integer orderNum;
	/**
	 * ztree属性
	 */
	private Boolean open;

	private List<?> list;

}
