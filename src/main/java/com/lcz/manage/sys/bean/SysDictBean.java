package com.lcz.manage.sys.bean;

import com.lcz.manage.util.common.base.BaseBean;
import lombok.Data;

import java.util.Date;

/**
 * 数据字典名称表
 *
 * @author luchunzhou
 */
@Data
public class SysDictBean extends BaseBean {

    /**
     * 字典名称
     */
    private String dictName;

    /**
     * 备注
     */
    private String remark;

}