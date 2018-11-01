package com.lcz.manage.sys.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 测试定时任务(演示Demo，可删除)
 * testTask为spring bean的名称
 *
 * @author luchunzhou
 */
@Component("testTask")
public class TestTask {
    private static final Logger logger = LoggerFactory.getLogger(TestTask.class);
    public void test(String params) {
        logger.info("我是带参数的test方法，正在被执行，参数为：" + params);
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public void test2() {
        logger.info("我是不带参数的test2方法，正在被执行");
    }
}
