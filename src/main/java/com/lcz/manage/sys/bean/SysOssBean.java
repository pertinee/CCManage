package com.lcz.manage.sys.bean;

import com.lcz.manage.util.common.base.BaseBean;
import lombok.Data;

import java.util.Date;


/**
 * 文件上传
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-03-25 12:13:26
 */
@Data
public class SysOssBean extends BaseBean {

	/**
	 * URL地址
	 */
	private String url;
	/**
	 * 创建时间
	 */
	private Date createDate;

}
