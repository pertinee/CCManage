package com.lcz.manage.sys.enums;

/**
 * 是否枚举
 *
 * @author luchunzhou
 * @date 2018年8月30日 下午3:45:47
 */
public enum YesOrNo {

    YES("1", "是"), NO("0", "否");

    private String code;
    private String name;

    YesOrNo(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static YesOrNo find(String code) {
        for (YesOrNo type : values()) {
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
