package com.lcz.manage.config;

import com.lcz.manage.sys.bean.SysDictBean;
import com.lcz.manage.sys.service.SysConfigService;
import com.lcz.manage.sys.service.SysDictService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * 数据初始化
 * @Order(value=1) 有多个CommandLineRunner接口时可以指定执行顺序（小的先执行）
 * CommandLineRunner表示在所有的bean以及applicationCOntenxt完成后的操作
 *
 */
@Component
public class DataInitConfig implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(DataInitConfig.class);
    @Autowired
    SysDictService sysDictService;


    @Override
    public void run(String... args) throws Exception {
        log.info("执行数据初始化操作【开始】");
        //查询所有菜单时包含存入redis操作，所以不需要再存入redis
        sysDictService.queryDictList(new SysDictBean());
        log.info("执行数据初始化操作【完成】");
    }
}