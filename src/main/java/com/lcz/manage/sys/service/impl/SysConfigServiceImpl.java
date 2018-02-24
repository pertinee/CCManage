package com.lcz.manage.sys.service.impl;

import com.alibaba.fastjson.JSON;
import com.lcz.manage.sys.bean.SysConfigBean;
import com.lcz.manage.sys.dao.SysConfigDao;
import com.lcz.manage.sys.redis.SysConfigRedis;
import com.lcz.manage.sys.service.SysConfigService;
import com.lcz.manage.util.IdUtils;
import com.lcz.manage.util.exception.CCException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service("sysConfigService")
public class SysConfigServiceImpl implements SysConfigService {
	@Autowired
	private SysConfigDao sysConfigDao;
	@Autowired
	private SysConfigRedis sysConfigRedis;
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void save(SysConfigBean config) {
		config.setId(IdUtils.uuid());
		sysConfigDao.save(config);
		sysConfigRedis.saveOrUpdate(config);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void update(SysConfigBean config) {
		sysConfigDao.update(config);
		sysConfigRedis.saveOrUpdate(config);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updateValueByKey(String key, String value) {
		sysConfigDao.updateValueByKey(key, value);
		sysConfigRedis.delete(key);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void deleteBatch(String[] ids) {
		for(String id : ids){
			SysConfigBean config = queryObject(id);
			sysConfigRedis.delete(config.getKey());
		}
		sysConfigDao.deleteBatch(ids);
	}

	@Override
	public List<SysConfigBean> queryList(Map<String, Object> map) {
		return sysConfigDao.queryList(map);
	}

	@Override
	public int queryTotal(Map<String, Object> map) {
		return sysConfigDao.queryTotal(map);
	}

	@Override
	public SysConfigBean queryObject(String id) {
		return sysConfigDao.queryObject(id);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public String getValue(String key, String defaultValue) {
		SysConfigBean config = sysConfigRedis.get(key);
		if(config == null){
			config = sysConfigDao.queryByKey(key);
			sysConfigRedis.saveOrUpdate(config);
		}

		return config == null ? null : config.getValue();
	}
	
	@Override
	public <T> T getConfigObject(String key, Class<T> clazz) {
		String value = getValue(key, null);
		if(StringUtils.isNotBlank(value)){
			return JSON.parseObject(value, clazz);
		}

		try {
			return clazz.newInstance();
		} catch (Exception e) {
			throw new CCException("获取参数失败");
		}
	}
}
