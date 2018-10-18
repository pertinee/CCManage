package com.lcz.manage.sys.controller;

import com.lcz.manage.sys.bean.SysDictBean;
import com.lcz.manage.sys.bean.SysDictFront;
import com.lcz.manage.sys.bean.SysDictInfoBean;
import com.lcz.manage.sys.service.SysDictService;
import com.lcz.manage.util.PageUtils;
import com.lcz.manage.util.Query;
import com.lcz.manage.util.R;
import com.lcz.manage.util.annotation.SysLog;
import com.lcz.manage.util.validator.ValidatorUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 数据字典
 *
 * @author luchunzhou
 * @date 2018/9/16
 */
@Controller
public class SysDictController extends SysBaseController{

    private static final Logger logger = Logger.getLogger(SysDictController.class);

    @Autowired
    private SysDictService sysDictService;

    /**
     * 初始化
     * @return
     */
    @RequestMapping(value = "/sys/dict", method = RequestMethod.GET)
    public String index(){
        return "sys/dict";
    }

    /**
     * 列表
     */
    @ResponseBody
    @RequestMapping(value = "/sys/dict/list", method = RequestMethod.GET)
    @RequiresPermissions("sys:dict:list")
    public R list(@RequestParam Map<String, Object> params){
        //查询列表数据
        Query query = new Query(params);
        List<SysDictFront> sysDictFrontList = sysDictService.queryDictFrontList(query);
        int total = sysDictService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(sysDictFrontList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 保存数据字典
     */
    @ResponseBody
    @SysLog("保存数据字典")
    @RequestMapping("/sys/dict/save")
    @RequiresPermissions("sys:dict:save")
    public R save(@RequestBody SysDictFront dictFront){
        ValidatorUtils.validateEntity(dictFront);

        //sysDictService.save(dictFront);

        return R.ok();
    }

    /**
     * 修改数据字典
     */
    @ResponseBody
    @SysLog("修改数据字典")
    @RequestMapping("/sys/dict/update")
    @RequiresPermissions("sys:dict:update")
    public R update(@RequestBody SysDictFront dictFront){
        ValidatorUtils.validateEntity(dictFront);

        //sysDictService.update(dictFront);

        return R.ok();
    }

    /**
     * 删除
     */
    @ResponseBody
    @SysLog("删除数据字典")
    @RequestMapping("/sys/dict/delete")
    @RequiresPermissions("sys:dict:delete")
    public R delete(@RequestBody String[] ids){
        sysDictService.deleteBatch(ids);

        return R.ok();
    }

}