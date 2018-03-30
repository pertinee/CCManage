package com.lcz.manage.util.common.base;

import lombok.Data;

import java.io.Serializable;

/**
 * 基础Bean
 *
 * @author:luchunzhou
 * @date:2018/3/19
 * @time:13:32
 */
@Data
public abstract class BaseBean implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 主键id
     */
    private String id;
}
