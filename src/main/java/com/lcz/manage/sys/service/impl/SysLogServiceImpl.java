package com.lcz.manage.sys.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.lcz.manage.sys.bean.SysLogBean;
import com.lcz.manage.sys.dao.SysLogDao;
import com.lcz.manage.sys.service.SysLogService;
import com.lcz.manage.util.IdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("sysLogService")
public class SysLogServiceImpl implements SysLogService {
	@Autowired
	private SysLogDao sysLogDao;
	
	@Override
	public SysLogBean queryObject(String id){
		return sysLogDao.queryObject(id);
	}
	
	@Override
	public List<SysLogBean> queryList(Map<String, Object> map){
		return sysLogDao.queryList(map);
	}

	@Override
	public List<SysLogBean> queryLogDaily(){
		return sysLogDao.queryLogDaily();
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return sysLogDao.queryTotal(map);
	}
	
	@Override
	public void save(SysLogBean sysLogBean){
		if(StringUtils.isEmpty(sysLogBean.getId())){
			sysLogBean.setId(IdUtils.uuid());
		}
		sysLogDao.save(sysLogBean);
	}
	
	@Override
	public void update(SysLogBean sysLogBean){
		sysLogDao.update(sysLogBean);
	}
	
	@Override
	public void delete(String id){
		sysLogDao.delete(id);
	}
	
	@Override
	public void deleteBatch(String[] ids){
		sysLogDao.deleteBatch(ids);
	}
	
}
