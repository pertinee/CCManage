package com.lcz.manage.sys.dao;


import com.lcz.manage.sys.bean.SysDictBean;
import com.lcz.manage.sys.bean.SysDictFront;

import java.util.List;
import java.util.Map;

/**
 * 系统日志
 *
 * @author luchunzhou
 */
public interface SysDictDao {

    List<SysDictFront> queryList(Map<String, Object> map);

    SysDictBean queryDict(String id);

    List<SysDictBean> queryDictList(SysDictBean sysDict);

    void saveDict(SysDictBean sysDict);

    int updateDict(SysDictBean sysDict);

    int deleteDict(String id);

    int deleteBatchDict(String[] id);

    List<SysDictFront> queryDictFrontList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

}
