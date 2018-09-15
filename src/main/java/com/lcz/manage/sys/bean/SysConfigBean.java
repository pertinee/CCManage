package com.lcz.manage.sys.bean;


import com.lcz.manage.util.common.base.BaseBean;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 系统配置信息
 * 
 * @author luchunzhou
 */
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

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}
