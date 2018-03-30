package com.lcz.manage.sys.dao;

import com.lcz.manage.util.common.base.BaseDao;
import com.lcz.manage.sys.bean.SysDeptBean;

import java.util.List;

/**
 * 部门管理
 * 
 * @author luchunzhou
 */
public interface SysDeptDao extends BaseDao<SysDeptBean> {

    /**
     * 查询子部门ID列表
     * @param parentId  上级部门ID
     */
    List<String> queryDetpIdList(String parentId);
}
