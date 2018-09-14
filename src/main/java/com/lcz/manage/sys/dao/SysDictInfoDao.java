package com.lcz.manage.sys.dao;

import com.lcz.manage.sys.bean.SysDictInfoBean;

import java.util.List;

/**
 * 系统日志
 *
 * @author luchunzhou
 */
public interface SysDictInfoDao {

    SysDictInfoBean queryDictInfo(String id, String dictValue);

    List<SysDictInfoBean> queryDictInfoList(SysDictInfoBean sysDict);

    void saveDictInfo(SysDictInfoBean sysDict);

    /**
     * 更新字典详情
     *
     * @param sysDict 必须传入 id、dictValue
     * @return
     */
    int updateDictInfo(SysDictInfoBean sysDict);

    int deleteDictInfo(String id, String dictValue);

}
