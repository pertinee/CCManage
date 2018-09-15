package com.lcz.manage.util.common.base;

import java.io.Serializable;

/**
 * 基础Bean
 *
 * @author:luchunzhou
 * @date:2018/3/19
 * @time:13:32
 */
public abstract class BaseBean implements Serializable {

    /**
     * 主键id
     */
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
