package com.lcz.manage.util.oss;

import com.lcz.manage.util.DateUtils;
import com.lcz.manage.util.IdUtils;
import org.apache.commons.lang.StringUtils;

import java.io.InputStream;
import java.util.Date;

/**
 * 云存储(支持七牛、阿里云、腾讯云、又拍云)
 *
 * @author luchunzhou
 */
public abstract class CloudStorageService {
    /** 云存储配置信息 */
    CloudStorageConfig config;

    /**
     * 文件路径
     * @param prefix 前缀
     * @return 返回上传路径
     */
    public String getPath(String prefix) {
        //生成uuid
        String uuid = IdUtils.uuid2();
        //文件路径
        String path = DateUtils.dateToString(new Date()) + "/" + uuid;

        if(StringUtils.isNotBlank(prefix)){
            path = prefix + "/" + path;
        }

        return path;
    }

    /**
     * 文件上传
     * @param data    文件字节数组
     * @param path    文件路径，包含文件名
     * @return        返回http地址
     */
    public abstract String upload(byte[] data, String path);

    /**
     * 文件上传
     * @param data    文件字节数组
     * @return        返回http地址
     */
    public abstract String upload(byte[] data);

    /**
     * 文件上传
     * @param inputStream   字节流
     * @param path          文件路径，包含文件名
     * @return              返回http地址
     */
    public abstract String upload(InputStream inputStream, String path);

    /**
     * 文件上传
     * @param inputStream  字节流
     * @return             返回http地址
     */
    public abstract String upload(InputStream inputStream);

}
