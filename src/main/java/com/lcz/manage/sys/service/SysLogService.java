package com.lcz.manage.sys.service;

import com.lcz.manage.sys.bean.SysLogBean;

import java.util.List;
import java.util.Map;

/**
 * 系统日志
 * 
 * @author luchunzhou
 */
public interface SysLogService {
	
	SysLogBean queryObject(String id);
	
	List<SysLogBean> queryList(Map<String, Object> map);

	List<SysLogBean> queryLogDaily();
	
	int queryTotal(Map<String, Object> map);
	
	void save(SysLogBean sysLogBean);
	
	void update(SysLogBean sysLogBean);
	
	void delete(String id);
	
	void deleteBatch(String[] ids);
}
