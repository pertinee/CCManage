package com.lcz.manage.sys.bean;

import com.lcz.manage.util.common.base.BaseBean;
import lombok.Data;

/**
 * 数据字典详情表
 *
 * @author luchunzhou
 */
@Data
public class SysDictInfoBean extends BaseBean {

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

    /**
     * 排序，小在前
     */
    private String orderId;

    /**
     * 备注
     */
    private String remark;

}