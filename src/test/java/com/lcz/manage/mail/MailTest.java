package com.lcz.manage.mail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 测试邮件发送
 * Created by luchunzhou on 2018/3/10.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MailTest {
    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String username;

    @Test
    public void testSendSimple() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(username);
        message.setTo("1090061055@qq.com");
        message.setSubject("CCManage权限管理系统");
        message.setText("CCManage提醒您：下雨了，记得收衣服。");
        javaMailSender.send(message);
    }



}
