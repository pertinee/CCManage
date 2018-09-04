package com.lcz.manage.sys.controller;

import com.lcz.manage.sys.bean.ScheduleJobLogBean;
import com.lcz.manage.sys.constants.CcConstants;
import com.lcz.manage.sys.service.ScheduleJobLogService;
import com.lcz.manage.util.PageUtils;
import com.lcz.manage.util.Query;
import com.lcz.manage.util.R;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 定时任务日志
 * 
 * @author luchunzhou
 */
@Controller
public class ScheduleJobLogController {
	@Autowired
	private ScheduleJobLogService scheduleJobLogService;

	/**
	 * 初始化
	 * @return
	 */
	@RequestMapping(value = "/sys/schedule_log", method = RequestMethod.GET)
	public String index(){
		return "sys/schedule_log";
	}
	
	/**
	 * 定时任务日志列表
	 */
	@ResponseBody
	@RequestMapping(value = "/sys/scheduleLog/list", method = RequestMethod.GET)
	@RequiresPermissions("sys:schedule:log")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
		Query query = new Query(params);
		List<ScheduleJobLogBean> jobList = scheduleJobLogService.queryList(query);
		int total = scheduleJobLogService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(jobList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	/**
	 * 定时任务日志信息
	 */
	@ResponseBody
	@RequestMapping("/sys/scheduleLog/info/{logId}")
	public R info(@PathVariable("logId") Long logId){
		ScheduleJobLogBean log = scheduleJobLogService.queryObject(logId);
		
		return R.ok().put("log", log);
	}
}
