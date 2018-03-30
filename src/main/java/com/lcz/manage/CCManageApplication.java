package com.lcz.manage;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.lcz.manage.*.dao")
public class CCManageApplication {
	private static Logger log = LoggerFactory.getLogger(CCManageApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(CCManageApplication.class, args);
		log.info("========== CCManage启动成功 ==========");
	}
}
