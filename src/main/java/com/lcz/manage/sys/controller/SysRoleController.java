package com.lcz.manage.sys.controller;

import com.lcz.manage.sys.bean.SysRoleBean;
import com.lcz.manage.sys.service.SysRoleMenuService;
import com.lcz.manage.sys.service.SysRoleService;
import com.lcz.manage.sys.constants.CcConstants;
import com.lcz.manage.util.PageUtils;
import com.lcz.manage.util.Query;
import com.lcz.manage.util.R;
import com.lcz.manage.util.annotation.SysLog;
import org.apache.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;



/**
 * 权限管理
 *
 * @author luchunzhou
 * @date 2017/12/18
 */
@Controller
public class SysRoleController extends SysBaseController{

    private Logger logger = Logger.getLogger(SysRoleController.class);

    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysRoleMenuService sysRoleMenuService;

    /**
     * 初始化
     * @return
     */
    @RequestMapping("/sys/role")
    public String index(){
        return "sys/role";
    }


    /**
     * 角色列表
     */
    @ResponseBody
    @RequestMapping(value = "/sys/role/list", method = RequestMethod.GET)
    @RequiresPermissions("sys:role:list")
    public R list(@RequestParam Map<String, Object> params){
        //如果不是超级管理员，则只查询自己创建的角色列表
        if(!getUserId().equals(CcConstants.SUPER_ADMIN)){
            params.put("createUserId", getUserId());
        }

        //查询列表数据
        Query query = new Query(params);
        List<SysRoleBean> list = sysRoleService.queryList(query);
        int total = sysRoleService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(list, total, query.getLimit(), query.getPage());

        return R.ok().put(CcConstants.PAGE, pageUtil);
    }

    /**
     * 角色列表
     */
    @ResponseBody
    @RequestMapping(value = "/sys/role/select", method = RequestMethod.GET)
    @RequiresPermissions("sys:role:select")
    public R select(){
        Map<String, Object> map = new HashMap<>();

        //如果不是超级管理员，则只查询自己所拥有的角色列表
        if(getUserId().equals(CcConstants.SUPER_ADMIN)){
            map.put("createUserId", getUserId());
        }
        List<SysRoleBean> list = sysRoleService.queryList(map);

        return R.ok().put("list", list);
    }

    /**
     * 角色信息
     */
    @ResponseBody
    @RequestMapping(value = "/sys/role/info/{roleId}", method = RequestMethod.GET)
    @RequiresPermissions("sys:role:info")
    public R info(@PathVariable("roleId") String roleId){
        SysRoleBean role = sysRoleService.queryObject(roleId);

        //查询角色对应的菜单
        List<String> menuIdList = sysRoleMenuService.queryMenuIdList(roleId);
        role.setMenuIdList(menuIdList);

        return R.ok().put("role", role);
    }

    /**
     * 保存角色
     */
    @ResponseBody
    //@Transactional
    @SysLog("保存角色")
    @RequestMapping(value = "/sys/role/save", method = RequestMethod.POST)
    @RequiresPermissions("sys:role:save")
    public R save(@RequestBody SysRoleBean role){
        role.setCreateUserId(getUserId());
        sysRoleService.save(role);

        return R.ok();
    }

    /**
     * 修改角色
     */
    @ResponseBody
    //@Transactional
    @SysLog("修改角色")
    @RequestMapping(value = "/sys/role/update", method = RequestMethod.POST)
    @RequiresPermissions("sys:role:update")
    public R update(@RequestBody SysRoleBean role){
        role.setCreateUserId(getUserId());
        sysRoleService.update(role);

        return R.ok();
    }

    /**
     * 删除角色
     */
    @ResponseBody
    //@Transactional
    @SysLog("删除角色")
    @RequestMapping(value = "/sys/role/delete")
    @RequiresPermissions("sys:role:delete")
    public R delete(@RequestBody String[] roleIds){
        sysRoleService.deleteBatch(roleIds);

        return R.ok();
    }


}