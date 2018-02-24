package com.lcz.manage.sys.service.impl;

import com.lcz.manage.sys.bean.SysMenuBean;
import com.lcz.manage.sys.dao.SysMenuDao;
import com.lcz.manage.sys.service.SysMenuService;
import com.lcz.manage.sys.service.SysUserService;
import com.lcz.manage.util.Constant;
import com.lcz.manage.util.IdUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


@Service("sysMenuService")
public class SysMenuServiceImpl implements SysMenuService {

	@Autowired
	private SysMenuDao sysMenuDao;
	@Autowired
	private SysUserService sysUserService;

	@Override
	public List<SysMenuBean> queryListParentId(String parentId, List<String> menuIdList) {
		List<SysMenuBean> menuList = sysMenuDao.queryListParentId(parentId);
		if(menuIdList == null){
			return menuList;
		}

		List<SysMenuBean> userMenuList = new ArrayList<>();
		for(SysMenuBean menu : menuList){
			if(menuIdList.contains(menu.getMenuId())){
				userMenuList.add(menu);
			}
		}
		return userMenuList;
	}

	@Override
	public List<SysMenuBean> queryListParentId(String parentId) {
		return sysMenuDao.queryListParentId(parentId);
	}

	@Override
	public List<SysMenuBean> queryNotButtonList() {
		return sysMenuDao.queryNotButtonList();
	}

	@Override
	public List<SysMenuBean> getUserMenuList(String userId) {
		//系统管理员，拥有最高权限
		if("1".equals(userId)){
			return getAllMenuList(null);
		}

		//用户菜单列表
		List<String> menuIdList = sysUserService.queryAllMenuId(userId);
		return getAllMenuList(menuIdList);
	}

	@Override
	public Set<String> getUserPermissions(String userId) {
		List<String> permsList;

		//系统管理员，拥有最高权限
		if("1".equals(userId)){
			List<SysMenuBean> menuList = queryList(new HashMap<>());
			permsList = new ArrayList<>(menuList.size());
			for(SysMenuBean menu : menuList){
				permsList.add(menu.getPerms());
			}
		}else{
			permsList = sysUserService.queryAllPerms(userId);
		}

		//用户权限列表
		Set<String> permsSet = new HashSet<String>();
		for(String perms : permsList){
			if(StringUtils.isBlank(perms)){
				continue;
			}
			permsSet.addAll(Arrays.asList(perms.trim().split(",")));
		}
		return permsSet;
	}

	@Override
	public SysMenuBean queryObject(String menuId) {
		return sysMenuDao.queryObject(menuId);
	}

	@Override
	public List<SysMenuBean> queryList(Map<String, Object> map) {
		return sysMenuDao.queryList(map);
	}

	@Override
	public int queryTotal(Map<String, Object> map) {
		return sysMenuDao.queryTotal(map);
	}

	@Override
	public void save(SysMenuBean menu) {
		menu.setMenuId(IdUtils.uuid());
		sysMenuDao.save(menu);
	}

	@Override
	public void update(SysMenuBean menu) {
		sysMenuDao.update(menu);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void deleteBatch(String[] menuIds) {
		sysMenuDao.deleteBatch(menuIds);
	}

	@Override
	public List<SysMenuBean> queryUserList(String userId) {
		return sysMenuDao.queryUserList(userId);
	}

	/**
	 * 获取所有菜单列表
	 */
	private List<SysMenuBean> getAllMenuList(List<String> menuIdList){
		//查询根菜单列表
		List<SysMenuBean> menuList = queryListParentId("0", menuIdList);
		//递归获取子菜单
		getMenuTreeList(menuList, menuIdList);

		return menuList;
	}

	/**
	 * 递归
	 */
	private List<SysMenuBean> getMenuTreeList(List<SysMenuBean> menuList, List<String> menuIdList){
		List<SysMenuBean> subMenuList = new ArrayList<SysMenuBean>();

		for(SysMenuBean entity : menuList){
			if(entity.getType() == Constant.MenuType.CATALOG.getValue()){//目录
				entity.setList(getMenuTreeList(queryListParentId(entity.getMenuId(), menuIdList), menuIdList));
			}
			subMenuList.add(entity);
		}

		return subMenuList;
	}

	/**
	 * 查询系统菜单ID（不可删除）
	 */
	@Override
	public List<String> querySysMenuList() {
		List<SysMenuBean> sysMenuList = sysMenuDao.querySysMenuList();
		if(sysMenuList.size() > 0){
			List<String> strList = new ArrayList<>();
			for(SysMenuBean each : sysMenuList){
				strList.add(each.getMenuId());
			}
			return strList;
		}else{
			return null;
		}
	}
}