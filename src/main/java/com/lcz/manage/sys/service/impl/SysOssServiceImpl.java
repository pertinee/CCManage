package com.lcz.manage.sys.service.impl;

import com.lcz.manage.sys.bean.SysOssBean;
import com.lcz.manage.sys.dao.SysOssDao;
import com.lcz.manage.sys.service.SysOssService;
import com.lcz.manage.util.IdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("sysOssService")
public class SysOssServiceImpl implements SysOssService {
	@Autowired
	private SysOssDao sysOssDao;
	
	@Override
	public SysOssBean queryObject(String id){
		return sysOssDao.queryObject(id);
	}
	
	@Override
	public List<SysOssBean> queryList(Map<String, Object> map){
		return sysOssDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return sysOssDao.queryTotal(map);
	}
	
	@Override
	public void save(SysOssBean sysOss){
		sysOss.setId(IdUtils.uuid());
		sysOssDao.save(sysOss);
	}
	
	@Override
	public void update(SysOssBean sysOss){
		sysOssDao.update(sysOss);
	}
	
	@Override
	public void delete(String id){
		sysOssDao.delete(id);
	}
	
	@Override
	public void deleteBatch(String[] ids){
		sysOssDao.deleteBatch(ids);
	}
	
}
