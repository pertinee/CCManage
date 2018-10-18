package com.lcz.manage.sys.service;

import com.lcz.manage.sys.bean.SysDictBean;
import com.lcz.manage.sys.bean.SysDictInfoBean;
import com.lcz.manage.sys.bean.SysDictInfoKey;
import com.lcz.manage.sys.bean.SysDictFront;

import java.util.List;
import java.util.Map;

/**
 * 数据字典信息服务
 * 
 * @author luchunzhou
 */
public interface SysDictService {
	
	/**
	 * 保存配置信息
	 */
	public void saveDict(SysDictBean dict);
	
	/**
	 * 更新配置信息
	 */
	public void updateDict(SysDictBean dict);

	/**
	 * 批量删除数据字典
	 */
	public void deleteBatchDict(String[] ids);


	/**
	 * 查询数据字典对象
	 * @param id
	 * @return
	 */
	public SysDictBean queryDict(String id);

	/**
	 * 获取数据字典列表
	 */
	public List<SysDictBean> queryDictList(SysDictBean sysDict);

	/**
	 * 保存配置信息详情
	 */
	public void saveDictInfo(SysDictInfoBean dict);

	/**
	 * 更新配置信息详情
	 */
	public void updateDictInfo(SysDictInfoBean dict);

	/**
	 * 批量删除数据字典详情
	 */
	public void deleteBatchDictInfo(List<SysDictInfoKey> keys);

	/**
	 * 获取数据字典详情列表
	 */
	public List<SysDictInfoBean> queryDictInfoList(SysDictInfoBean sysDict);

	/**
	 * 前台显示数据字典列表
	 */
	public List<SysDictFront> queryDictFrontList(Map<String, Object> map);

	public int queryTotal(Map<String, Object> map);

	/**
	 * 删除数据字典
	 */
	public void deleteBatch(String[] ids);

	public void saveDict(SysDictFront dictFront);
}
