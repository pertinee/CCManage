package com.lcz.manage.sys.bean;


import com.lcz.manage.util.common.base.BaseBean;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 系统配置信息
 * 
 * @author luchunzhou
 */
@Data
public class SysConfigBean extends BaseBean {

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
