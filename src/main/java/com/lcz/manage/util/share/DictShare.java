package com.lcz.manage.util.share;

import com.lcz.manage.sys.bean.SysDictBean;
import com.lcz.manage.sys.bean.SysDictInfoBean;

import java.util.List;

public abstract class DictShare {

	/**
	 * 数据字典对象
	 * @param dictId
	 * @return
	 */
	public abstract SysDictBean getDict(String dictId);

	/**
	 * 数据字典详情集合
	 * @param dictId
	 * @return
	 */
	public abstract List<SysDictInfoBean> getDictInfoList(String dictId);

	/**
	 * 单个数据字典的转换
	 * @param dictId
	 * @param value
	 * @return
	 */
	public abstract String getDictPrompt(String dictId, String value);
	
}
