package com.lcz.manage.sys.controller;

import com.lcz.manage.sys.bean.SysLogBean;
import com.lcz.manage.sys.constants.CcConstants;
import com.lcz.manage.sys.service.SysLogService;
import com.lcz.manage.util.PageUtils;
import com.lcz.manage.util.Query;
import com.lcz.manage.util.R;
import org.apache.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;


/**
 * 系统日志
 * 
 * @author luchunzhou
 */
@Controller
public class SysLogController {

	private static final Logger logger = Logger.getLogger(SysLogController.class);

	@Autowired
	private SysLogService sysLogService;

	/**
	 * 初始化
	 * @return
	 */
	@RequestMapping(value = "/sys/log", method = RequestMethod.GET)
	public String index(){
		return "sys/log";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping(value = "/sys/log/list", method = RequestMethod.GET)
	@RequiresPermissions("sys:log:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
		Query query = new Query(params);
		List<SysLogBean> sysLogBeanList = sysLogService.queryList(query);
		int total = sysLogService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(sysLogBeanList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
}
