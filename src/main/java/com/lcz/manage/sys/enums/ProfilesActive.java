package com.lcz.manage.sys.enums;

/**
 * 系统运行环境 0：开发   1：测试   2：发布
 *
 * @author luchunzhou
 * @date 2018年10月14日 下午8:28:40
 */
public enum ProfilesActive {

    DEV("dev", "开发"),
    TEST("test", "测试"),
    PRO("pro", "发布");

    private String code;
    private String name;

    ProfilesActive(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static ProfilesActive find(String code) {
        for (ProfilesActive type : values()) {
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
