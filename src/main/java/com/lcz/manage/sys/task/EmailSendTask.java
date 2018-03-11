package com.lcz.manage.sys.task;

import com.alibaba.fastjson.JSON;
import com.lcz.manage.sys.bean.SysLogBean;
import com.lcz.manage.sys.service.SysLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * 定时向管理员发送日志
 * Created by luchunzhou on 2018/3/10.
 */
@Component("emailSendTask")
public class EmailSendTask {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${spring.mail.username}")
    private String username;

    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private SysLogService sysLogService;

    /**
     * 每天发送日志
     * @param email
     */
    public void sendLogDaily(String email){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(username);
        message.setTo(email);
        message.setSentDate(new Date());
        message.setSubject("CCManage权限管理系统");
        List<SysLogBean> sysLogBeanList = sysLogService.queryLogDaily();
        message.setText(JSON.toJSONString(sysLogBeanList));
        javaMailSender.send(message);
        logger.info("【邮件发送成功】");
    }

}
