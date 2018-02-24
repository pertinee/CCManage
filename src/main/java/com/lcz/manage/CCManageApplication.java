package com.lcz.manage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.lcz.manage.*.dao")
public class CCManageApplication {

	public static void main(String[] args) {
		SpringApplication.run(CCManageApplication.class, args);
	}
}
