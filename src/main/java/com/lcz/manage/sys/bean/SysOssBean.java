package com.lcz.manage.sys.bean;

import com.lcz.manage.util.common.base.BaseBean;
import java.util.Date;


/**
 * 文件上传
 * 
 * @author luchunzhou
 */
public class SysOssBean extends BaseBean {

	/**
	 * URL地址
	 */
	private String url;
	/**
	 * 创建时间
	 */
	private Date createDate;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
}
