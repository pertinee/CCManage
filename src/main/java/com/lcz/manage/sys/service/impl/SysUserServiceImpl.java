package com.lcz.manage.sys.service.impl;

import com.lcz.manage.sys.bean.SysUserBean;
import com.lcz.manage.sys.dao.SysUserDao;
import com.lcz.manage.sys.service.SysRoleService;
import com.lcz.manage.sys.service.SysUserRoleService;
import com.lcz.manage.sys.service.SysUserService;
import com.lcz.manage.util.Constant;
import com.lcz.manage.util.IdUtils;
import com.lcz.manage.util.ShiroUtils;
import com.lcz.manage.util.exception.CCException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.apache.shiro.crypto.hash.Sha256Hash;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author:luchunzhou
 * @date:2017/12/18
 * @time:16:00
 */
@Service("sysUserService")
public class SysUserServiceImpl implements SysUserService{

    @Autowired
    private SysUserDao sysUserDao;
    @Autowired
    private SysUserRoleService sysUserRoleService;
    @Autowired
    private SysRoleService sysRoleService;


    @Override
    public List<SysUserBean> queryList(Map<String, Object> map) {
        return sysUserDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return sysUserDao.queryTotal(map);
    }

    @Override
    public SysUserBean queryByUserName(String username) {
        return sysUserDao.queryByUserName(username);
    }

    @Override
    public SysUserBean queryObject(String id) {
        return sysUserDao.queryObject(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(SysUserBean sysUser) {
        sysUser.setUserId(IdUtils.uuid());
        //sha256加密
        sysUser.setPassword(new Sha256Hash(sysUser.getPassword()).toHex());
        sysUser.setCreateUserId(ShiroUtils.getUserId());
        sysUser.setCreateDate(new Date());
        sysUserDao.save(sysUser);

        // 检查角色是否越权
        checkRole(sysUser);

        // 保存用户与角色关系
        sysUserRoleService.saveOrUpdate(sysUser.getUserId(), sysUser.getRoleIdList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(SysUserBean sysUser) {
        //sha256加密
        sysUser.setPassword(new Sha256Hash(sysUser.getPassword()).toHex());
        sysUserDao.update(sysUser);

        // 检查角色是否越权
        checkRole(sysUser);

        // 更新用户与角色关系
        sysUserRoleService.saveOrUpdate(sysUser.getUserId(), sysUser.getRoleIdList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String id) {
        sysUserDao.delete(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(String[] userIds) {
        sysUserDao.deleteBatch(userIds);
    }

    @Override
    public List<String> queryAllMenuId(String userId) {
        return sysUserDao.queryAllMenuId(userId);
    }

    @Override
    public List<String> queryAllPerms(String userId) {
        return sysUserDao.queryAllPerms(userId);
    }

    @Override
    public int updatePassword(String userId, String password, String newPassword) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("password", password);
        map.put("newPassword", newPassword);
        return sysUserDao.updatePassword(map);
    }

    /**
     * 检查角色是否越权
     */
    private void checkRole(SysUserBean user){
        //如果不是超级管理员，则需要判断用户的角色是否自己创建
        if(user.getCreateUserId().equals(Constant.SUPER_ADMIN)){
            return ;
        }

        //查询用户创建的角色列表
        List<String> roleIdList = sysRoleService.queryRoleIdList(user.getCreateUserId());

        //判断是否越权
        if(!roleIdList.containsAll(user.getRoleIdList())){
            throw new CCException("新增用户所选角色，不是本人创建");
        }
    }
}
