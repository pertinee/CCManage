package com.lcz.manage.sys.service.impl;

import com.lcz.manage.sys.bean.SysUserRoleBean;
import com.lcz.manage.sys.dao.SysUserRoleDao;
import com.lcz.manage.sys.service.SysUserRoleService;
import com.lcz.manage.util.IdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * 用户与角色对应关系
 * 
 * @author luchunzhou
 */
@Service("sysUserRoleService")
public class SysUserRoleServiceImpl implements SysUserRoleService {
	@Autowired
	private SysUserRoleDao sysUserRoleDao;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveOrUpdate(String userId, List<String> roleIdList) {
		if(roleIdList.size() == 0){
			return ;
		}
		
		//先删除用户与角色关系
		sysUserRoleDao.delete(userId);
		
		//保存用户与角色关系
		for(int i = 0 ; i < roleIdList.size() ; i ++){
			SysUserRoleBean sysUserRole = new SysUserRoleBean();
			sysUserRole.setId(IdUtils.uuid());
			sysUserRole.setUserId(userId);
			sysUserRole.setRoleId(roleIdList.get(i));
			sysUserRoleDao.save(sysUserRole);
		}

		// 有错误：batch插入id要不唯一，换成上面的方法
//		Map<String, Object> map = new HashMap<>();
//		map.put("userId", userId);
//		map.put("roleIdList", roleIdList);
//		sysUserRoleDao.save(map);
	}

	@Override
	public List<String> queryRoleIdList(String userId) {
		return sysUserRoleDao.queryRoleIdList(userId);
	}

	@Override
	public void delete(String userId) {
		sysUserRoleDao.delete(userId);
	}
}
