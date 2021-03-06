package com.lcz.manage.sys.service;

import com.lcz.manage.sys.bean.SysUserBean;

import java.util.List;
import java.util.Map;

/**
 * @author:luchunzhou
 * @date:2017/12/18
 * @time:15:57
 */
public interface SysUserService {

    List<SysUserBean> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    SysUserBean queryByUserName(String username);

    SysUserBean queryObject(String id);

    void save(SysUserBean sysUser);

    void update(SysUserBean sysUser);

    void delete(String id);

    void deleteBatch(String[] userIds);

    /**
     * 查询用户的所有菜单ID
     */
    List<String> queryAllMenuId(String userId);

    /**
     * 查询用户的所有权限
     * @param userId  用户ID
     */
    List<String> queryAllPerms(String userId);

    /**
     * 修改密码
     * @param userId       用户ID
     * @param password     原密码
     * @param newPassword  新密码
     */
    int updatePassword(String userId, String password, String newPassword);
}
