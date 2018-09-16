package com.lcz.manage.sys.enums;

/**
 * 字典详情类型  0-隐藏 1-只读 2-可修改
 *
 * @author luchunzhou
 * @date 2018年9月16日 下午4:40:22
 */
public enum AccessLevel {

    HIDDEN("0", "隐藏"),
    READONLY("1", "只读"),
    EDITABLE("2", "可修改");

    private String code;
    private String name;

    AccessLevel(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static AccessLevel find(String code) {
        for (AccessLevel type : values()) {
            if (type.getCode().equals(code)) {
                return type;
            }
        }
        return null;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
