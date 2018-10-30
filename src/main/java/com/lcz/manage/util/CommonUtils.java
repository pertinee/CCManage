package com.lcz.manage.util;

import com.lcz.manage.sys.constants.CcConstants;
import com.lcz.manage.sys.redis.SysConfigRedis;
import com.lcz.manage.sys.service.SysConfigService;
import com.lcz.manage.util.exception.CCException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.text.ParseException;
import java.util.Date;

/**
 * 说明: 公共方法
 *
 * @author lcz <lucz25053@hsjry.com>
 * @version CFM-V1.0
 * @date 2018年10月30日19:31:58
 */
@Component
public class CommonUtils {

    private static final Logger logger = Logger.getLogger(CommonUtils.class);

    @Autowired
    private SysConfigService testSysConfigService;

    private static SysConfigService sysConfigService;

    @Autowired
    private SysConfigRedis testSysConfigRedis;

    private static SysConfigRedis sysConfigRedis;

    @PostConstruct
    public void init() {
        sysConfigService = testSysConfigService;
        sysConfigRedis = testSysConfigRedis;
    }

    public CommonUtils(SysConfigService configService, SysConfigRedis configRedis) {
        sysConfigService = configService;
        sysConfigRedis = configRedis;
    }

    /**
     * 判断是否在系统营业时间
     * @return
     */
    public static boolean executeLegalTime() {
        sysConfigRedis.delete(CcConstants.SYS_BUSINESS_START_TIME);
        sysConfigRedis.delete(CcConstants.SYS_BUSINESS_END_TIME);
        String startTime = sysConfigService.getValue(CcConstants.SYS_BUSINESS_START_TIME, null);
        String endTime = sysConfigService.getValue(CcConstants.SYS_BUSINESS_END_TIME, null);
        if(StringUtils.isEmpty(startTime) || StringUtils.isEmpty(endTime)){
            logger.error("系统营业开始、结束时间未配置");
            throw new CCException("系统营业开始、结束时间未配置");
        }
        Date date = new Date();
        String day = DateUtils.formatDate(date, "yyyyMMdd");

        String startTimeStr = day+startTime.replaceAll(":", "")+"00";
        String endTimeStr = day+endTime.replaceAll(":", "")+"00";
        logger.debug("系统营业开始时间"+startTimeStr);
        logger.debug("系统营业结束时间"+endTimeStr);
        Long startTimeDetail;
        Long endTimeDetail;
        try {
            startTimeDetail = DateUtils.strToDate(startTimeStr, "yyyyMMddHHmmss").getTime();
            endTimeDetail = DateUtils.strToDate(endTimeStr,"yyyyMMddHHmmss").getTime();
        } catch (ParseException e) {
            logger.error("日期解析失败");
            throw new CCException("日期解析失败", e);
        }
        Long nowTime = date.getTime();
        if(nowTime >= startTimeDetail && nowTime <= endTimeDetail){
            return true;
        }else{
            return false;
        }
    }
}
