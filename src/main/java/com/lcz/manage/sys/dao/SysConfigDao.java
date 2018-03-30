package com.lcz.manage.sys.dao;

import com.lcz.manage.util.common.base.BaseDao;
import com.lcz.manage.sys.bean.SysConfigBean;
import org.apache.ibatis.annotations.Param;

/**
 * 系统配置信息
 * 
 * @author luchunzhou
 */
public interface SysConfigDao extends BaseDao<SysConfigBean> {
	
	/**
	 * 根据key，查询value
	 */
	SysConfigBean queryByKey(String paramKey);
	
	/**
	 * 根据key，更新value
	 */
	int updateValueByKey(@Param("key") String key, @Param("value") String value);
	
}
