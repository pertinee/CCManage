package com.lcz.manage.sys.bean;

import com.lcz.manage.util.common.base.BaseBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据字典前端显示
 *
 * @author luchunzhou
 */
public class SysDictFront extends BaseBean {

    /**
     * 字典名称
     */
    private String dictName;

    /**
     * 字典数值
     */
    private String dictValue;

    /**
     * 字典提示
     */
    private String dictPrompt;

    /**
     * 0-隐藏 1-只读 2-可修改
     */
    private String accessLevel;

    private String accessLevelCn;

    /**
     * 排序，小在前
     */
    private String orderId;

    /**
     * 备注
     */
    private String remark;

    public String getDictName() {
        return dictName;
    }

    public void setDictName(String dictName) {
        this.dictName = dictName;
    }

    public String getDictValue() {
        return dictValue;
    }

    public void setDictValue(String dictValue) {
        this.dictValue = dictValue;
    }

    public String getDictPrompt() {
        return dictPrompt;
    }

    public void setDictPrompt(String dictPrompt) {
        this.dictPrompt = dictPrompt;
    }

    public String getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(String accessLevel) {
        this.accessLevel = accessLevel;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getAccessLevelCn() {
        return accessLevelCn;
    }

    public void setAccessLevelCn(String accessLevelCn) {
        this.accessLevelCn = accessLevelCn;
    }
}