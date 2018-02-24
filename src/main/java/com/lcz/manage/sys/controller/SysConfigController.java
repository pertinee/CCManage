package com.lcz.manage.sys.controller;

import com.lcz.manage.sys.bean.SysConfigBean;
import com.lcz.manage.sys.service.SysConfigService;
import com.lcz.manage.util.PageUtils;
import com.lcz.manage.util.Query;
import com.lcz.manage.util.R;
import com.lcz.manage.util.annotation.SysLog;
import com.lcz.manage.util.validator.ValidatorUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 系统配置信息
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年12月4日 下午6:55:53
 */
@Controller
public class SysConfigController extends SysBaseController {
	@Autowired
	private SysConfigService sysConfigService;

	/**
	 * 初始化
	 * @return
	 */
	@RequestMapping(value = "/sys/config", method = RequestMethod.GET)
	public String index(){
		return "sys/config";
	}

	/**
	 * 所有配置列表
	 */
	@ResponseBody
	@RequestMapping("/sys/config/list")
	@RequiresPermissions("sys:config:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
		Query query = new Query(params);
		List<SysConfigBean> configList = sysConfigService.queryList(query);
		int total = sysConfigService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(configList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 配置信息
	 */
	@ResponseBody
	@RequestMapping("/sys/config/info/{id}")
	@RequiresPermissions("sys:config:info")
	public R info(@PathVariable("id") String id){
		SysConfigBean config = sysConfigService.queryObject(id);
		
		return R.ok().put("config", config);
	}
	
	/**
	 * 保存配置
	 */
	@ResponseBody
	@SysLog("保存配置")
	@RequestMapping("/sys/config/save")
	@RequiresPermissions("sys:config:save")
	public R save(@RequestBody SysConfigBean config){
		ValidatorUtils.validateEntity(config);

		sysConfigService.save(config);
		
		return R.ok();
	}
	
	/**
	 * 修改配置
	 */
	@ResponseBody
	@SysLog("修改配置")
	@RequestMapping("/sys/config/update")
	@RequiresPermissions("sys:config:update")
	public R update(@RequestBody SysConfigBean config){
		ValidatorUtils.validateEntity(config);
		
		sysConfigService.update(config);
		
		return R.ok();
	}
	
	/**
	 * 删除配置
	 */
	@ResponseBody
	@SysLog("删除配置")
	@RequestMapping("/sys/config/delete")
	@RequiresPermissions("sys:config:delete")
	public R delete(@RequestBody String[] ids){
		sysConfigService.deleteBatch(ids);
		
		return R.ok();
	}

}
