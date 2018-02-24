package com.lcz.manage.sys.controller;

import com.lcz.manage.sys.bean.SysUserBean;
import com.lcz.manage.sys.service.SysUserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;



/**
 * 管理页初始化
 * Created by luchunzhou on 2017/12/18.
 */
@Controller
public class SysIndexController extends SysBaseController{

    private Logger logger = Logger.getLogger(SysIndexController.class);

    @Autowired
    private SysUserService sysUserService;

    /**
     * 后台管理页
     * @param model
     * @return
     */
    @RequestMapping("/sys")
    public String index(Model model){
        SysUserBean sysUser = getUser();
        SysUserBean frontUser = new SysUserBean();
        frontUser.setUsername(sysUser.getUsername());
        frontUser.setEmail(sysUser.getEmail());
        model.addAttribute("currentUser",frontUser);
        return "sys/index";
    }
}