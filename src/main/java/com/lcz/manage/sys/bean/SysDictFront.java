package com.lcz.manage.sys.bean;

import com.lcz.manage.util.common.base.BaseBean;
import com.lcz.manage.util.validator.group.AddGroup;
import com.lcz.manage.util.validator.group.UpdateGroup;

import javax.validation.constraints.NotNull;

/**
 * 数据字典前端显示
 *
 * @author luchunzhou
 */
public class SysDictFront extends BaseBean {

    /**
     * 字典id
     */
    @NotNull(message="字典id不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String dictId;

    /**
     * 字典名称
     */
    @NotNull(message="字典名称不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String dictName;

    /**
     * 字典数值
     */
    @NotNull(message="字典数值不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String dictValue;

    /**
     * 字典提示
     */
    @NotNull(message="字典提示不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String dictPrompt;

    /**
     * 0-隐藏 1-只读 2-可修改
     */
    @NotNull(message="字典权限不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String accessLevel;

    private String accessLevelCn;

    /**
     * 排序，小在前
     */
    @NotNull(message="排序不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String orderId;

    /**
     * 备注
     */
    private String remark;

    public String getDictId() {
        return dictId;
    }

    public void setDictId(String dictId) {
        this.dictId = dictId;
    }

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