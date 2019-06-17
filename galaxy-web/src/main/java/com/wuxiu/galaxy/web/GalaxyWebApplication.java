package com.wuxiu.galaxy.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RestController;


/**
 * 项目启动类
 *
 * @author wuxiu
 */
@ComponentScan(basePackages = {
        "com.wuxiu.galaxy.integration",
        "com.wuxiu.galaxy.web"})
//@EnableSwagger2Doc
@RestController
@SpringBootApplication(exclude = {HibernateJpaAutoConfiguration.class},
        scanBasePackages = "com.wuxiu.galaxy")
public class GalaxyWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(GalaxyWebApplication.class, args);
    }

}
