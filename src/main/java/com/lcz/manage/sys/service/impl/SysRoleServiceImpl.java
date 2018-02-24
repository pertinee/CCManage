package com.lcz.manage.sys.service.impl;

import com.lcz.manage.sys.bean.SysRoleBean;
import com.lcz.manage.sys.dao.SysRoleDao;
import com.lcz.manage.sys.service.SysRoleMenuService;
import com.lcz.manage.sys.service.SysRoleService;
import com.lcz.manage.sys.service.SysUserRoleService;
import com.lcz.manage.sys.service.SysUserService;
import com.lcz.manage.util.Constant;
import com.lcz.manage.util.IdUtils;
import com.lcz.manage.util.exception.CCException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 角色
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年9月18日 上午9:45:12
 */
@Service("sysRoleService")
public class SysRoleServiceImpl implements SysRoleService {
	@Autowired
	private SysRoleDao sysRoleDao;
	@Autowired
	private SysRoleMenuService sysRoleMenuService;
	@Autowired
	private SysUserService sysUserService;

	@Override
	public SysRoleBean queryObject(String roleId) {
		return sysRoleDao.queryObject(roleId);
	}

	@Override
	public List<SysRoleBean> queryList(Map<String, Object> map) {
		return sysRoleDao.queryList(map);
	}

	@Override
	public int queryTotal(Map<String, Object> map) {
		return sysRoleDao.queryTotal(map);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void save(SysRoleBean role) {
		role.setRoleId(IdUtils.uuid());
		role.setCreateDate(new Date());
		sysRoleDao.save(role);
		
		//检查权限是否越权
		checkPrems(role);
		
		//保存角色与菜单关系
		sysRoleMenuService.saveOrUpdate(role.getRoleId(), role.getMenuIdList());
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void update(SysRoleBean role) {
		sysRoleDao.update(role);
		
		//检查权限是否越权
		checkPrems(role);
		
		//更新角色与菜单关系
		sysRoleMenuService.saveOrUpdate(role.getRoleId(), role.getMenuIdList());
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void deleteBatch(String[] roleIds) {
		sysRoleDao.deleteBatch(roleIds);
	}
	
	@Override
	public List<String> queryRoleIdList(String createUserId) {
		return sysRoleDao.queryRoleIdList(createUserId);
	}

	/**
	 * 检查权限是否越权
	 */
	private void checkPrems(SysRoleBean role){
		//如果不是超级管理员，则需要判断角色的权限是否超过自己的权限
		if(role.getCreateUserId().equals(Constant.SUPER_ADMIN)){
			return ;
		}
		
		//查询用户所拥有的菜单列表
		List<String> menuIdList = sysUserService.queryAllMenuId(role.getCreateUserId());
		
		//判断是否越权
		if(!menuIdList.containsAll(role.getMenuIdList())){
			throw new CCException("新增角色的权限，已超出你的权限范围");
		}
	}
}
