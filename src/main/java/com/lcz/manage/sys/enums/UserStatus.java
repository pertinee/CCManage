package com.lcz.manage.sys.enums;

/**
 * 用户状态 0：禁用   1：正常
 *
 * @author luchunzhou
 * @date 2018年8月30日 下午3:47:40
 */
public enum UserStatus {
    NORMAL("1", "正常"),
    DISABLED("0", "禁用");

    private String code;
    private String name;

    UserStatus(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static UserStatus find(String code) {
        for (UserStatus type : values()) {
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
