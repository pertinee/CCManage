package com.lcz.manage.sys.bean;

import com.lcz.manage.util.common.base.BaseBean;
import lombok.Data;

import java.util.List;


/**
 * 部门管理
 *
 * @author luchunzhou
 */
@Data
public class SysDeptBean extends BaseBean {
    /**
     * 上级部门ID，一级部门为0
     */
    private String parentId;
    /**
     * 部门名称
     */
    private String name;
    /**
     * 上级部门名称
     */
    private String parentName;
    /**
     * 排序
     */
    private Integer orderNum;
    /**
     * ztree属性
     */
    private Boolean open;

    private List<?> list;

}
