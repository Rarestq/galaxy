package com.wuxiu.galaxy.web;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import com.spring4all.swagger.EnableSwagger2Doc;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RestController;


@ComponentScan(basePackages = {
        "com.wuxiu.galaxy.dal",
        "com.wuxiu.galaxy.service",
        "com.wuxiu.galaxy.integration",
        "com.wuxiu.galaxy.web"})
@MapperScan("com.wuxiu.galaxy.dal.dao")
@EnableTransactionManagement
@EnableSwagger2Doc
@EnableDubboConfiguration
@RestController
@SpringBootApplication(exclude = {HibernateJpaAutoConfiguration.class },
        scanBasePackages = "com.wuxiu.galaxy")
public class GalaxyWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(GalaxyWebApplication.class, args);
    }

}
