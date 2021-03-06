package com.lcz.manage.sys.controller;

import com.lcz.manage.sys.bean.SysMenuBean;
import com.lcz.manage.sys.enums.MenuType;
import com.lcz.manage.sys.service.SysMenuService;
import com.lcz.manage.sys.constants.CcConstants;
import com.lcz.manage.util.R;
import com.lcz.manage.util.StringUtils;
import com.lcz.manage.util.annotation.SysLog;
import com.lcz.manage.util.exception.CCException;
import org.apache.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Set;


/**
 * 菜单
 *
 * @author luchunzhou
 * @date 2017/12/18
 */
@Controller
public class SysMenuController extends SysBaseController{

    private static final Logger logger = Logger.getLogger(SysMenuController.class);

    @Autowired
    private SysMenuService sysMenuService;

    /**
     * 初始化
     * @return
     */
    @RequestMapping(value = "/sys/menu", method = RequestMethod.GET)
    public String index(){
        return "sys/menu";
    }

    /**
     * 所有菜单列表
     */
    @ResponseBody
    @RequestMapping(value = "/sys/menu/list", method = RequestMethod.GET)
    @RequiresPermissions("sys:menu:list")
    public List<SysMenuBean> list(){
        List<SysMenuBean> menuList = sysMenuService.queryList(new HashMap<String, Object>());

        return menuList;
    }

    /**
     * 选择菜单(添加、修改菜单)
     */
    @ResponseBody
    @RequestMapping(value = "/sys/menu/select", method = RequestMethod.GET)
    @RequiresPermissions("sys:menu:select")
    public R select(){
        //查询列表数据
        List<SysMenuBean> menuList = sysMenuService.queryNotButtonList();

        //添加顶级菜单
        SysMenuBean root = new SysMenuBean();
        root.setMenuId("0");
        root.setName("一级菜单");
        root.setParentId("-1");
        root.setOpen(true);
        menuList.add(root);

        return R.ok().put("menuList", menuList);
    }

    /**
     * 角色授权菜单
     */
    @ResponseBody
    @RequestMapping(value = "/sys/menu/perms", method = RequestMethod.GET)
    @RequiresPermissions("sys:menu:perms")
    public R perms(){
        //查询列表数据
        List<SysMenuBean> menuList = null;

        //只有超级管理员，才能查看所有管理员列表
        if(getUserId().equals(CcConstants.SUPER_ADMIN)){
            menuList = sysMenuService.queryList(new HashMap<String, Object>());
        }else{
            menuList = sysMenuService.queryUserList(getUserId());
        }

        return R.ok().put("menuList", menuList);
    }

    /**
     * 菜单信息
     */
    @ResponseBody
    @RequestMapping(value = "/sys/menu/info/{menuId}", method = RequestMethod.GET)
    @RequiresPermissions("sys:menu:info")
    public R info(@PathVariable("menuId") String menuId){
        SysMenuBean menu = sysMenuService.queryObject(menuId);
        return R.ok().put("menu", menu);
    }

    /**
     * 保存
     */
    @ResponseBody
    //@Transactional
    @SysLog("保存菜单")
    @RequestMapping(value = "/sys/menu/save", method = RequestMethod.POST)
    @RequiresPermissions("sys:menu:save")
    public R save(@RequestBody SysMenuBean menu){
        //数据校验
        verifyForm(menu);

        sysMenuService.save(menu);

        return R.ok();
    }

    /**
     * 修改
     */
    @ResponseBody
    //@Transactional
    @SysLog("修改菜单")
    @RequestMapping(value = "/sys/menu/update", method = RequestMethod.POST)
    @RequiresPermissions("sys:menu:update")
    public R update(@RequestBody SysMenuBean menu){
        //数据校验
        verifyForm(menu);

        sysMenuService.update(menu);

        return R.ok();
    }


    /**
     * 删除
     */
    @ResponseBody
    //@Transactional
    @SysLog("删除菜单")
    @RequestMapping("/sys/menu/delete")
    @RequiresPermissions("sys:menu:delete")
    public R delete(String menuId){
        List<String> sysMenuIdList = sysMenuService.querySysMenuList();
        if(sysMenuIdList.contains(menuId)){
            return R.error("系统菜单，不能删除");
        }

        //判断是否有子菜单或按钮
        List<SysMenuBean> menuList = sysMenuService.queryListParentId(menuId);
        if(!CollectionUtils.isEmpty(menuList)){
            return R.error("请先删除子菜单或按钮");
        }

        sysMenuService.deleteBatch(new String[]{menuId});

        return R.ok();
    }


    /**
     * 批量删除
     */
    @ResponseBody
    //@Transactional
    @SysLog("批量删除菜单")
    @RequestMapping("/sys/menu/deletes")
    @RequiresPermissions("sys:menu:delete")
    public R deleteBatch(@RequestBody String[] menuIds){
        List<String> sysMenuIdList = sysMenuService.querySysMenuList();
        for(String menuId : menuIds){
            if(sysMenuIdList.contains(menuId)){
                return R.error("系统菜单，不能删除");
            }
        }
        sysMenuService.deleteBatch(menuIds);

        return R.ok();
    }

    /**
     * 用户菜单列表
     */
    @ResponseBody
    @RequestMapping(value = "/sys/menu/user", method = RequestMethod.GET)
    public R user(){
        List<SysMenuBean> menuList = sysMenuService.getUserMenuList(getUserId());
        Set<String> permissions = sysMenuService.getUserPermissions(getUserId());
        return R.ok().put("menuList", menuList).put("permissions", permissions);
    }

    /**
     * 验证参数是否正确
     */
    private void verifyForm(SysMenuBean menu){
        if(StringUtils.isEmpty(menu.getName())){
            throw new CCException("菜单名称不能为空");
        }

        if(StringUtils.isEmpty(menu.getParentId())){
            throw new CCException("上级菜单不能为空");
        }

        //菜单
        if(MenuType.MENU.getCode().equals(menu.getType())){
            if(StringUtils.isEmpty(menu.getUrl())){
                throw new CCException("菜单URL不能为空");
            }
        }

        //上级菜单类型
        String parentType = MenuType.CATALOG.getCode();
        if(!CcConstants.IS_TOP_MENU.equals(menu.getParentId())){
            SysMenuBean parentMenu = sysMenuService.queryObject(menu.getParentId());
            parentType = parentMenu.getType();
        }

        //目录、菜单
        if(MenuType.CATALOG.getCode().equals(menu.getType()) ||
                MenuType.MENU.getCode().equals(menu.getType())){
            if(!MenuType.CATALOG.getCode().equals(parentType)){
                throw new CCException("上级菜单只能为目录类型");
            }
        }

        //按钮
        if(MenuType.BUTTON.getCode().equals(menu.getType())){
            if(!MenuType.MENU.getCode().equals(parentType)){
                throw new CCException("上级菜单只能为菜单类型");
            }
        }
    }
}