package com.lcz.manage.sys.bean;


import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 系统配置信息
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年12月4日 下午6:43:36
 */
@Data
public class SysConfigBean {
	private String id;
	@NotBlank(message="参数名不能为空")
	private String key;
	@NotBlank(message="参数值不能为空")
	private String value; 
	private String remark;
	/**
	 * 状态  0：禁用   1：正常
	 */
	private Integer status;

}
