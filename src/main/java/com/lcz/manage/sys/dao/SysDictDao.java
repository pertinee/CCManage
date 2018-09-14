package com.lcz.manage.sys.dao;


import com.lcz.manage.sys.bean.SysDictBean;

import java.util.List;

/**
 * 系统日志
 *
 * @author luchunzhou
 */
public interface SysDictDao {

    SysDictBean queryDict(String id);

    List<SysDictBean> queryDictList(SysDictBean sysDict);

    void saveDict(SysDictBean sysDict);

    int updateDict(SysDictBean sysDict);

    int deleteDict(String id);

}
