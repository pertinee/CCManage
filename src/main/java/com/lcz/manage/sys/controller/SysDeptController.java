package com.lcz.manage.sys.controller;

import com.lcz.manage.sys.bean.SysDeptBean;
import com.lcz.manage.sys.service.SysDeptService;
import com.lcz.manage.util.Constants;
import com.lcz.manage.util.R;
import com.lcz.manage.util.annotation.SysLog;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;


/**
 * 部门管理
 * 
 * @author luchunzhou
 */
@Controller
public class SysDeptController extends SysBaseController {

	@Autowired
	private SysDeptService sysDeptService;

	/**
	 * 初始化
	 * @return
	 */
	@RequestMapping(value = "/sys/dept", method = RequestMethod.GET)
	public String index(){
		return "sys/dept";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/sys/dept/list")
	@RequiresPermissions("sys:dept:list")
	public List<SysDeptBean> list(){
		List<SysDeptBean> deptList = sysDeptService.queryList(new HashMap<String, Object>());

		return deptList;
	}

	/**
	 * 选择部门(添加、修改菜单)
	 */
	@ResponseBody
	@RequestMapping("/sys/dept/select")
	@RequiresPermissions("sys:dept:select")
	public R select(){
		List<SysDeptBean> deptList = sysDeptService.queryList(new HashMap<String, Object>());

		//添加一级部门
		if(getUserId().equals(Constants.SUPER_ADMIN)){
			SysDeptBean root = new SysDeptBean();
			root.setId("0");
			root.setName("一级部门");
			root.setParentId("-1");
			root.setOpen(true);
			deptList.add(root);
		}

		return R.ok().put("deptList", deptList);
	}

	/**
	 * 上级部门Id(管理员则为0)
	 */
	@ResponseBody
	@RequestMapping("/sys/dept/info")
	@RequiresPermissions("sys:dept:list")
	public R info(){
		String id = "0";
		if(!getUserId().equals(Constants.SUPER_ADMIN)){
			SysDeptBean dept = sysDeptService.queryObject(getDeptId());
			id = dept.getParentId();
		}

		return R.ok().put("id", id);
	}
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/sys/dept/info/{id}")
	@RequiresPermissions("sys:dept:info")
	public R info(@PathVariable("id") String id){
		SysDeptBean dept = sysDeptService.queryObject(id);
		
		return R.ok().put("dept", dept);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	//@Transactional
	@SysLog("保存部门")
	@RequestMapping("/sys/dept/save")
	@RequiresPermissions("sys:dept:save")
	public R save(@RequestBody SysDeptBean dept){
		sysDeptService.save(dept);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	//@Transactional
	@SysLog("修改部门")
	@RequestMapping("/sys/dept/update")
	@RequiresPermissions("sys:dept:update")
	public R update(@RequestBody SysDeptBean dept){
		sysDeptService.update(dept);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	//@Transactional
	@SysLog("删除部门")
	@RequestMapping("/sys/dept/delete")
	@RequiresPermissions("sys:dept:delete")
	public R delete(String id){
		//判断是否有子部门
		List<String> deptList = sysDeptService.queryDetpIdList(id);
		if(!CollectionUtils.isEmpty(deptList)){
			return R.error("请先删除子部门");
		}

		sysDeptService.delete(id);
		
		return R.ok();
	}
	
}
