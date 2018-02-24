package com.lcz.manage.sys.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 文件上传
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-03-25 12:13:26
 */
@Data
public class SysOssBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//id
	private String id;
	//URL地址
	private String url;
	//创建时间
	private Date createDate;

}
