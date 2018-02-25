package com.lcz.manage.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo("通用API接口文档"))
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.lcz.manage.api.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo(String desc) {
        Contact contact = new Contact("luchunzhou", "https://www.luchunzhou.cn/", "1090061055@qq.com");
        return new ApiInfoBuilder()
                .title("CCManage API接口文档")
                .description(desc)
                .contact(contact)
                .version("1.0.0")
                .build();
    }

}