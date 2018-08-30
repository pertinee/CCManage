package com.lcz.manage.sys.controller;

import com.alibaba.fastjson.JSON;
import com.lcz.manage.sys.bean.SysOssBean;
import com.lcz.manage.sys.enums.CloudService;
import com.lcz.manage.sys.service.SysConfigService;
import com.lcz.manage.sys.service.SysOssService;
import com.lcz.manage.util.*;
import com.lcz.manage.util.exception.CCException;
import com.lcz.manage.util.oss.CloudStorageConfig;
import com.lcz.manage.util.oss.OSSFactory;
import com.lcz.manage.util.validator.ValidatorUtils;
import com.lcz.manage.util.validator.group.AliyunGroup;
import com.lcz.manage.util.validator.group.QcloudGroup;
import com.lcz.manage.util.validator.group.QiniuGroup;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 文件上传
 * 
 * @author luchunzhou
 */
@Controller
public class SysOssController {
	@Autowired
	private SysOssService sysOssService;
    @Autowired
    private SysConfigService sysConfigService;

    private final static String KEY = ConfigConstant.CLOUD_STORAGE_CONFIG_KEY;

	/**
	 * 初始化
	 * @return
	 */
	@RequestMapping(value = "/sys/oss", method = RequestMethod.GET)
	public String index(){
		return "sys/oss";
	}


	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/sys/oss/list")
	@RequiresPermissions("sys:oss:all")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
		Query query = new Query(params);
		List<SysOssBean> sysOssList = sysOssService.queryList(query);
		int total = sysOssService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(sysOssList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}


    /**
     * 云存储配置信息
     */
	@ResponseBody
    @RequestMapping("/sys/oss/config")
    @RequiresPermissions("sys:oss:all")
    public R config(){
        CloudStorageConfig config = sysConfigService.getConfigObject(KEY, CloudStorageConfig.class);

        return R.ok().put("config", config);
    }


	/**
	 * 保存云存储配置信息
	 */
	@ResponseBody
	@RequestMapping("/sys/oss/saveConfig")
	@RequiresPermissions("sys:oss:all")
	public R saveConfig(@RequestBody CloudStorageConfig config){
		//校验类型
		ValidatorUtils.validateEntity(config);

		if(config.getType() == CloudService.QINIU.getValue()){
			//校验七牛数据
			ValidatorUtils.validateEntity(config, QiniuGroup.class);
		}else if(config.getType() == CloudService.ALIYUN.getValue()){
			//校验阿里云数据
			ValidatorUtils.validateEntity(config, AliyunGroup.class);
		}else if(config.getType() == CloudService.QCLOUD.getValue()){
			//校验腾讯云数据
			ValidatorUtils.validateEntity(config, QcloudGroup.class);
		}
		

        sysConfigService.updateValueByKey(KEY, JSON.toJSONString(config));

		return R.ok();
	}
	

	/**
	 * 上传文件
	 */
	@ResponseBody
	@RequestMapping("/sys/oss/upload")
	@RequiresPermissions("sys:oss:all")
	public R upload(@RequestParam("file") MultipartFile file) throws Exception {
		if (file.isEmpty()) {
			throw new CCException("上传文件不能为空");
		}

		//上传文件
		String url = OSSFactory.build().upload(file.getBytes());

		//保存文件信息
		SysOssBean ossEntity = new SysOssBean();
		ossEntity.setUrl(url);
		ossEntity.setCreateDate(new Date());
		sysOssService.save(ossEntity);

		return R.ok().put("url", url);
	}


	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/sys/oss/delete")
	@RequiresPermissions("sys:oss:all")
	public R delete(@RequestBody String[] ids){
		sysOssService.deleteBatch(ids);

		return R.ok();
	}

}
