package com.lcz.manage.sys.dao;

import com.lcz.manage.util.common.base.BaseDao;
import com.lcz.manage.sys.bean.ScheduleJobBean;

import java.util.Map;

/**
 * 定时任务
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年12月1日 下午10:29:57
 */
public interface ScheduleJobDao extends BaseDao<ScheduleJobBean> {
	
	/**
	 * 批量更新状态
	 */
	int updateBatch(Map<String, Object> map);
}
