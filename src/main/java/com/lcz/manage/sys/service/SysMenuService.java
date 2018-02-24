package com.lcz.manage.sys.service;

import com.lcz.manage.sys.bean.SysMenuBean;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author:luchunzhou
 * @date:2017/12/18
 * @time:15:57
 */
public interface SysMenuService {

    /**
     * 根据父菜单，查询子菜单
     * @param parentId 父菜单ID
     * @param menuIdList  用户菜单ID
     */
    List<SysMenuBean> queryListParentId(String parentId, List<String> menuIdList);

    /**
     * 根据父菜单，查询子菜单
     * @param parentId 父菜单ID
     */
    List<SysMenuBean> queryListParentId(String parentId);

    /**
     * 获取不包含按钮的菜单列表
     */
    List<SysMenuBean> queryNotButtonList();

    /**
     * 获取用户菜单列表
     */
    List<SysMenuBean> getUserMenuList(String userId);

    /**
     * 获取用户权限列表
     */
    Set<String> getUserPermissions(String userId);


    /**
     * 查询菜单
     */
    SysMenuBean queryObject(String menuId);

    /**
     * 查询菜单列表
     */
    List<SysMenuBean> queryList(Map<String, Object> map);

    /**
     * 查询总数
     */
    int queryTotal(Map<String, Object> map);

    /**
     * 保存菜单
     */
    void save(SysMenuBean menu);

    /**
     * 修改
     */
    void update(SysMenuBean menu);

    /**
     * 删除
     */
    void deleteBatch(String[] menuIds);

    /**
     * 查询用户的权限列表
     */
    List<SysMenuBean> queryUserList(String userId);

    /**
     * 查询系统菜单ID（不可删除）
     */
    List<String> querySysMenuList();
}
