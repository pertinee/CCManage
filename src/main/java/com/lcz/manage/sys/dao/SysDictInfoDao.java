package com.lcz.manage.sys.dao;

import com.lcz.manage.sys.bean.SysDictInfoBean;
import com.lcz.manage.sys.bean.SysDictInfoKey;

import java.util.List;

/**
 * 系统日志
 *
 * @author luchunzhou
 */
public interface SysDictInfoDao {

    SysDictInfoBean queryDictInfo(SysDictInfoKey key);

    List<SysDictInfoBean> queryDictInfoList(SysDictInfoBean sysDict);

    void saveDictInfo(SysDictInfoBean sysDict);

    int updateDictInfo(SysDictInfoBean sysDict);

    int deleteDictInfo(SysDictInfoKey key);

}
