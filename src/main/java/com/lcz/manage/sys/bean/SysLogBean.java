package com.lcz.manage.sys.bean;

import com.lcz.manage.util.common.base.BaseBean;
import lombok.Data;

import java.util.Date;

/**
 * 日志表
 * @author luchunzhou
 */
@Data
public class SysLogBean extends BaseBean {

    private String username;

    private String operation;

    private String method;

    private String params;

    private String ip;

    private Date createDate;

}