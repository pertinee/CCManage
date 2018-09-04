package com.lcz.manage.util.oss;


import com.lcz.manage.sys.enums.CloudService;
import com.lcz.manage.sys.service.SysConfigService;
import com.lcz.manage.sys.constants.CcConstants;
import com.lcz.manage.util.SpringContextUtils;

/**
 * 文件上传Factory
 *
 * @author luchunzhou
 */
public final class OSSFactory {
    private static SysConfigService sysConfigService;

    static {
        OSSFactory.sysConfigService = (SysConfigService) SpringContextUtils.getBean("sysConfigService");
    }

    public static CloudStorageService build(){
        //获取云存储配置信息
        CloudStorageConfig config = sysConfigService.getConfigObject(CcConstants.CLOUD_STORAGE_CONFIG_KEY, CloudStorageConfig.class);

        if(config.getType() == CloudService.QINIU.getValue()){
            return new QiniuCloudStorageService(config);
        }else if(config.getType() == CloudService.ALIYUN.getValue()){
            return new AliyunCloudStorageService(config);
        }else if(config.getType() == CloudService.QCLOUD.getValue()){
            return new QcloudCloudStorageService(config);
        }

        return null;
    }

}
