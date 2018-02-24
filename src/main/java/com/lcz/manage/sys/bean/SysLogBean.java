package com.lcz.manage.sys.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 日志表
 * @author luchunzhou
 */
@Data
public class SysLogBean implements Serializable {
    private String id;

    private String username;

    private String operation;

    private String method;

    private String params;

    private String ip;

    private Date createDate;

}