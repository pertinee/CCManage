package com.lcz.manage.sys.service.impl;

import com.lcz.manage.sys.bean.SysDeptBean;
import com.lcz.manage.sys.dao.SysDeptDao;
import com.lcz.manage.sys.service.SysDeptService;
import com.lcz.manage.util.IdUtils;
import com.qiniu.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service("sysDeptService")
public class SysDeptServiceImpl implements SysDeptService {
	@Autowired
	private SysDeptDao sysDeptDao;
	
	@Override
	public SysDeptBean queryObject(String deptId){
		return sysDeptDao.queryObject(deptId);
	}
	
	@Override
	public List<SysDeptBean> queryList(Map<String, Object> map){
		return sysDeptDao.queryList(map);
	}
	
	@Override
	public void save(SysDeptBean sysDept){
		sysDept.setId(IdUtils.uuid());
		sysDeptDao.save(sysDept);
	}
	
	@Override
	public void update(SysDeptBean sysDept){
		sysDeptDao.update(sysDept);
	}
	
	@Override
	public void delete(String deptId){
		sysDeptDao.delete(deptId);
	}

	@Override
	public List<String> queryDetpIdList(String parentId) {
		return sysDeptDao.queryDetpIdList(parentId);
	}

	@Override
	public String getSubDeptIdList(String deptId){
		//部门及子部门ID列表
		List<String> deptIdList = new ArrayList<>();

		//获取子部门ID
		List<String> subIdList = queryDetpIdList(deptId);
		getDeptTreeList(subIdList, deptIdList);

		//添加本部门
		deptIdList.add(deptId);

		String deptFilter = StringUtils.join(deptIdList, ",");
		return deptFilter;
	}

	/**
	 * 递归
	 */
	private void getDeptTreeList(List<String> subIdList, List<String> deptIdList){
		for(String deptId : subIdList){
			List<String> list = queryDetpIdList(deptId);
			if(list.size() > 0){
				getDeptTreeList(list, deptIdList);
			}

			deptIdList.add(deptId);
		}
	}
}
