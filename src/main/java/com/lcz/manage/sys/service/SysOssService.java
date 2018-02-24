package com.lcz.manage.sys.service;


import com.lcz.manage.sys.bean.SysOssBean;

import java.util.List;
import java.util.Map;

/**
 * 文件上传
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-03-25 12:13:26
 */
public interface SysOssService {
	
	SysOssBean queryObject(String id);
	
	List<SysOssBean> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(SysOssBean sysOss);
	
	void update(SysOssBean sysOss);
	
	void delete(String id);
	
	void deleteBatch(String[] ids);
}
