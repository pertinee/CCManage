package com.lcz.manage.sys.service;


import com.lcz.manage.sys.bean.ScheduleJobLogBean;

import java.util.List;
import java.util.Map;

/**
 * 定时任务日志
 * 
 * @author luchunzhou
 */
public interface ScheduleJobLogService {

	/**
	 * 根据ID，查询定时任务日志
	 */
	ScheduleJobLogBean queryObject(Long jobId);
	
	/**
	 * 查询定时任务日志列表
	 */
	List<ScheduleJobLogBean> queryList(Map<String, Object> map);
	
	/**
	 * 查询总数
	 */
	int queryTotal(Map<String, Object> map);
	
	/**
	 * 保存定时任务日志
	 */
	void save(ScheduleJobLogBean log);
	
}
