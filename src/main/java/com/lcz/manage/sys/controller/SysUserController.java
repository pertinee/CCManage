package com.lcz.manage.sys.controller;

import com.lcz.manage.sys.bean.SysRoleBean;
import com.lcz.manage.sys.bean.SysUserBean;
import com.lcz.manage.sys.service.SysRoleService;
import com.lcz.manage.sys.service.SysUserService;
import com.lcz.manage.util.PageUtils;
import com.lcz.manage.util.Query;
import com.lcz.manage.util.R;
import com.lcz.manage.util.ShiroUtils;
import com.lcz.manage.util.annotation.SysLog;
import com.lcz.manage.util.validator.Assert;
import com.lcz.manage.util.validator.ValidatorUtils;
import com.lcz.manage.util.validator.group.AddGroup;
import com.lcz.manage.util.validator.group.UpdateGroup;
import org.apache.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 用户管理
 *
 * @author:luchunzhou
 * @date:2017/12/18
 * @time:16:20
 */
@Controller
public class SysUserController extends SysBaseController{

    private Logger logger = Logger.getLogger(SysUserController.class);

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysRoleService sysRoleService;

    /**
     * 修改登录用户密码 ok
     */
    @ResponseBody
    @SysLog("修改密码")
    @RequestMapping(value = "/sys/user/password", method = RequestMethod.POST)
    public R password(String password, String newPassword){
        Assert.isBlank(newPassword, "新密码不为能空");
        //sha256加密
        password = new Sha256Hash(password).toHex();
        //sha256加密
        newPassword = new Sha256Hash(newPassword).toHex();

        //更新密码
        int count = sysUserService.updatePassword(getUserId(), password, newPassword);
        if(count == 0){
            return R.error("原密码不正确");
        }

        //退出
        ShiroUtils.logout();

        return R.ok();
    }

    /**
     * 跳转到用户列表
     */
    @RequestMapping(value = "/sys/user/page", method = RequestMethod.GET)
    @RequiresPermissions("sys:user:list")
    public String userPage(Model model){
        Map<String, Object> map = new HashMap<>();
        List<SysRoleBean> sysRoleList = sysRoleService.queryList(map);
        model.addAttribute("sysRoleList",sysRoleList);
        //查询列表数据
        return "sys/user";
    }

    /**
     * 所有用户列表
     */
    @ResponseBody
    @RequestMapping(value = "/sys/user/list", method = RequestMethod.GET)
    @RequiresPermissions("sys:user:list")
    public R list(@RequestParam Map<String, Object> params){
        //查询列表数据
        Query query = new Query(params);
        List<SysUserBean> userList = sysUserService.queryList(params);
        int total = sysUserService.queryTotal(params);
        PageUtils pageUtil = new PageUtils(userList, total, query.getLimit(), query.getPage());
        return R.ok().put("page",pageUtil);
    }

    /**
     * 用户信息
     */
    @ResponseBody
    @RequestMapping(value = "/sys/user/info/{userId}", method = RequestMethod.GET)
    @RequiresPermissions("sys:user:info")
    public R info(@PathVariable("userId") String userId){
        SysUserBean user = sysUserService.queryObject(userId);
        return R.ok().put("user", user);
    }

    /**
     * 保存用户
     */
    @ResponseBody
    //@Transactional
    @SysLog("保存用户")
    @RequestMapping("/sys/user/save")
    @RequiresPermissions("sys:user:save")
    public R save(@Validated @RequestBody SysUserBean user){
        ValidatorUtils.validateEntity(user, AddGroup.class);

        sysUserService.save(user);
        return R.ok();
    }

    /**
     * 修改用户
     */
    @ResponseBody
    //@Transactional
    @SysLog("修改用户")
    @RequestMapping("/sys/user/update")
    @RequiresPermissions("sys:user:update")
    public R update(@Validated @RequestBody SysUserBean user){
        ValidatorUtils.validateEntity(user, UpdateGroup.class);

        sysUserService.update(user);
        return R.ok();
    }

    /**
     * 删除一个用户
     */
    @ResponseBody
    //@Transactional
    @SysLog("删除用户")
    @RequestMapping(value = "/sys/user/delete/{userId}")
    @RequiresPermissions("sys:user:delete")
    public R delete(@PathVariable("userId") String userId){
        sysUserService.delete(userId);
        return R.ok();
    }

    /**
     * 删除用户
     */
    @ResponseBody
    //@Transactional
    @SysLog("批量删除用户")
    @RequestMapping(value = "/sys/user/delete", method = RequestMethod.POST)
    @RequiresPermissions("sys:user:delete")
    public R deleteBatch(@RequestBody String[] userIds){
        sysUserService.deleteBatch(userIds);
        return R.ok();
    }

}
