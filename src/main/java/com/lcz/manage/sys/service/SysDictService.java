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
	 * 保存数据字典
	 * @param dict
	 */
	public void saveDict(SysDictBean dict);

	/**
	 * 更新数据字典
	 * @param dict
	 */
	public void updateDict(SysDictBean dict);

	/**
	 * 批量删除数据字典
	 * @param ids
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
	 * @param sysDict
	 * @return
	 */
	public List<SysDictBean> queryDictList(SysDictBean sysDict);

	/**
	 * 保存数据字典详情
	 * @param dict
	 */
	public void saveDictInfo(SysDictInfoBean dict);

	/**
	 * 批量删除数据字典详情
	 * @param keys
	 */
	public void deleteBatchDictInfo(List<SysDictInfoKey> keys);

	/**
	 * 获取数据字典详情列表
	 * @param sysDict
	 * @return
	 */
	public List<SysDictInfoBean> queryDictInfoList(SysDictInfoBean sysDict);

	/**
	 * 前台显示数据字典列表
	 * @param map
	 * @return
	 */
	public List<SysDictFront> queryDictFrontList(Map<String, Object> map);

	/**
	 * 前台查询数据字典数量
	 * @param map
	 * @return
	 */
	public int queryTotal(Map<String, Object> map);

	/**
	 * 删除数据字典
	 * @param ids
	 */
	public void deleteBatch(String[] ids);

	/**
	 * 更新数据字典-面向前台
	 * @param dictFront
	 */
	public void saveDict(SysDictFront dictFront);

	/**
	 * 保存数据字典-面向前台
	 * @param dictFront
	 */
	public void updateDict(SysDictFront dictFront);
}
