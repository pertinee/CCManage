package com.lcz.manage.sys.bean;

import com.lcz.manage.util.common.base.BaseBean;
import java.util.List;


/**
 * 部门管理
 *
 * @author luchunzhou
 */
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

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public Boolean getOpen() {
        return open;
    }

    public void setOpen(Boolean open) {
        this.open = open;
    }

    public List<?> getList() {
        return list;
    }

    public void setList(List<?> list) {
        this.list = list;
    }
}
