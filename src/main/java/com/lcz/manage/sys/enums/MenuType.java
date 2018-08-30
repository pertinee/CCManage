package com.lcz.manage.sys.enums;

/**
 * 菜单类型 0：目录   1：菜单   2：按钮
 *
 * @author luchunzhou
 * @date 2018年8月30日 下午3:47:40
 */
public enum MenuType {

    CATALOG("0", "目录"),
    MENU("1", "菜单"),
    BUTTON("2", "按钮");

    private String code;
    private String name;

    MenuType(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static MenuType find(String code) {
        for (MenuType type : values()) {
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
