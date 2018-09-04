package com.lcz.manage.sys.controller;

import com.lcz.manage.sys.bean.ScheduleJobBean;
import com.lcz.manage.sys.constants.CcConstants;
import com.lcz.manage.sys.service.ScheduleJobService;
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
 * 定时任务
 * 
 * @author luchunzhou
 */
@Controller
public class ScheduleJobController {
	@Autowired
	private ScheduleJobService scheduleJobService;

	/**
	 * 初始化
	 * @return
	 */
	@RequestMapping(value = "/sys/schedule", method = RequestMethod.GET)
	public String index(){
		return "sys/schedule";
	}
	
	/**
	 * 定时任务列表
	 */
	@ResponseBody
	@RequestMapping(value = "/sys/schedule/list", method = RequestMethod.GET)
	@RequiresPermissions("sys:schedule:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
		Query query = new Query(params);
		List<ScheduleJobBean> jobList = scheduleJobService.queryList(query);
		int total = scheduleJobService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(jobList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	/**
	 * 定时任务信息
	 */
	@ResponseBody
	@RequestMapping(value = "/sys/schedule/info/{jobId}", method = RequestMethod.GET)
	@RequiresPermissions("sys:schedule:info")
	public R info(@PathVariable("jobId") Long jobId){
		ScheduleJobBean schedule = scheduleJobService.queryObject(jobId);
		
		return R.ok().put("schedule", schedule);
	}
	
	/**
	 * 保存定时任务
	 */
	@SysLog("保存定时任务")
	@ResponseBody
	@RequestMapping("/sys/schedule/save")
	@RequiresPermissions("sys:schedule:save")
	public R save(@RequestBody ScheduleJobBean scheduleJob){
		ValidatorUtils.validateEntity(scheduleJob);
		
		scheduleJobService.save(scheduleJob);
		
		return R.ok();
	}
	
	/**
	 * 修改定时任务
	 */
	@SysLog("修改定时任务")
	@ResponseBody
	@RequestMapping("/sys/schedule/update")
	@RequiresPermissions("sys:schedule:update")
	public R update(@RequestBody ScheduleJobBean scheduleJob){
		ValidatorUtils.validateEntity(scheduleJob);
				
		scheduleJobService.update(scheduleJob);
		
		return R.ok();
	}
	
	/**
	 * 删除定时任务
	 */
	@SysLog("删除定时任务")
	@ResponseBody
	@RequestMapping("/sys/schedule/delete")
	@RequiresPermissions("sys:schedule:delete")
	public R delete(@RequestBody Long[] jobIds){
		scheduleJobService.deleteBatch(jobIds);
		
		return R.ok();
	}
	
	/**
	 * 立即执行任务
	 */
	@SysLog("立即执行任务")
	@ResponseBody
	@RequestMapping("/sys/schedule/run")
	@RequiresPermissions("sys:schedule:run")
	public R run(@RequestBody Long[] jobIds){
		scheduleJobService.run(jobIds);
		
		return R.ok();
	}
	
	/**
	 * 暂停定时任务
	 */
	@SysLog("暂停定时任务")
	@ResponseBody
	@RequestMapping("/sys/schedule/pause")
	@RequiresPermissions("sys:schedule:pause")
	public R pause(@RequestBody Long[] jobIds){
		scheduleJobService.pause(jobIds);
		
		return R.ok();
	}
	
	/**
	 * 恢复定时任务
	 */
	@SysLog("恢复定时任务")
	@ResponseBody
	@RequestMapping("/sys/schedule/resume")
	@RequiresPermissions("sys:schedule:resume")
	public R resume(@RequestBody Long[] jobIds){
		scheduleJobService.resume(jobIds);
		
		return R.ok();
	}

}
