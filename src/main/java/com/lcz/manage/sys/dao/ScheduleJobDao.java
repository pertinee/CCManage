package com.lcz.manage.sys.dao;

import com.lcz.manage.util.common.base.BaseDao;
import com.lcz.manage.sys.bean.ScheduleJobBean;

import java.util.Map;

/**
 * 定时任务
 * 
 * @author luchunzhou
 */
public interface ScheduleJobDao extends BaseDao<ScheduleJobBean> {
	
	/**
	 * 批量更新状态
	 */
	int updateBatch(Map<String, Object> map);
}
