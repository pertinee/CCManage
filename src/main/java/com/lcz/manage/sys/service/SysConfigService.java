package com.lcz.manage.sys.service;

import com.lcz.manage.sys.bean.SysConfigBean;

import java.util.List;
import java.util.Map;

/**
 * 系统配置信息
 * 
 * @author luchunzhou
 */
public interface SysConfigService {
	
	/**
	 * 保存配置信息
	 */
	public void save(SysConfigBean config);
	
	/**
	 * 更新配置信息
	 */
	public void update(SysConfigBean config);
	
	/**
	 * 根据key，更新value
	 */
	public void updateValueByKey(String key, String value);
	
	/**
	 * 删除配置信息
	 */
	public void deleteBatch(String[] ids);

	/**
	 * 根据key删除配置信息
	 */
	public void deleteBatchByKey(String[] keys);
	
	/**
	 * 获取List列表
	 */
	public List<SysConfigBean> queryList(Map<String, Object> map);
	/**
	 * 获取总记录数
	 */
	public int queryTotal(Map<String, Object> map);
	
	public SysConfigBean queryObject(String id);

	public SysConfigBean queryByKey(String key);
	
	/**
	 * 根据key，获取配置的value值
	 * 
	 * @param key           key
	 * @param defaultValue  缺省值
	 */
	public String getValue(String key, String defaultValue);
	
	/**
	 * 根据key，获取value的Object对象
	 * @param key    key
	 * @param clazz  Object对象
	 */
	public <T> T getConfigObject(String key, Class<T> clazz);
	
}
