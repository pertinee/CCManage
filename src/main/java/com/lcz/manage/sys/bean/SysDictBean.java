package com.lcz.manage.sys.bean;

import com.lcz.manage.util.common.base.BaseBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据字典名称表
 *
 * @author luchunzhou
 */
public class SysDictBean extends BaseBean {

    /**
     * 字典名称
     */
    private String dictName;

    /**
     * 备注
     */
    private String remark;

    /**
     * 数据字典详情集合
     */
    private List<SysDictInfoBean> dictInfoList = new ArrayList<>();

    public String getDictName() {
        return dictName;
    }

    public void setDictName(String dictName) {
        this.dictName = dictName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<SysDictInfoBean> getDictInfoList() {
        return dictInfoList;
    }

    public void setDictInfoList(List<SysDictInfoBean> dictInfoList) {
        this.dictInfoList = dictInfoList;
    }
}