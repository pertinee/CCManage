package com.lcz.manage.sys.enums;

/**
 * 云服务商
 */
public enum CloudService {
    /**
     * 七牛云
     */
    QINIU(1),
    /**
     * 阿里云
     */
    ALIYUN(2),
    /**
     * 腾讯云
     */
    QCLOUD(3);

    private int value;

    CloudService(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}