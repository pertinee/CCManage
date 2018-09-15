package com.lcz.manage.api.bean;


import java.io.Serializable;

/**
 * Created by luchunzhou on 2018/2/9.
 */
public class TestBean implements Serializable{

    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
