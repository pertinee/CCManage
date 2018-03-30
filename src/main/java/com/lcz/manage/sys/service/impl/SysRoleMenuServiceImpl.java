package com.lcz.manage.sys.service.impl;

import com.lcz.manage.sys.bean.SysRoleMenuBean;
import com.lcz.manage.sys.dao.SysRoleMenuDao;
import com.lcz.manage.sys.service.SysRoleMenuService;
import com.lcz.manage.util.IdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * 角色与菜单对应关系
 * 
 * @author luchunzhou
 */
@Service("sysRoleMenuService")
public class SysRoleMenuServiceImpl implements SysRoleMenuService {
	@Autowired
	private SysRoleMenuDao sysRoleMenuDao;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveOrUpdate(String roleId, List<String> menuIdList) {
		//先删除角色与菜单关系
		sysRoleMenuDao.delete(roleId);

		if(menuIdList.size() == 0){
			return ;
		}

		//保存角色与菜单关系
		for(int i = 0 ; i < menuIdList.size() ; i ++){
			SysRoleMenuBean sysRoleMenu = new SysRoleMenuBean();
			sysRoleMenu.setId(IdUtils.uuid());
			sysRoleMenu.setRoleId(roleId);
			sysRoleMenu.setMenuId(menuIdList.get(i));
			sysRoleMenuDao.save(sysRoleMenu);
		}

		// 有错误：batch插入id要不唯一，换成上面的方法
//		Map<String, Object> map = new HashMap<>();
//		map.put("roleId", roleId);
//		map.put("menuIdList", menuIdList);
//		sysRoleMenuDao.save(map);
	}

	@Override
	public List<String> queryMenuIdList(String roleId) {
		return sysRoleMenuDao.queryMenuIdList(roleId);
	}

}
