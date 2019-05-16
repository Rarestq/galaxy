package com.wuxiu.galaxy.service.core;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RestController;

/**
 * 服务启动类
 *
 * @author: wuxiu
 * @date: 2019/5/16 16:31
 */
@ComponentScan(basePackages = {
        "com.wuxiu.galaxy.dal",
        "com.wuxiu.galaxy.service"})
@MapperScan("com.wuxiu.galaxy.dal.dao")
@EnableTransactionManagement
@EnableSwagger2Doc
@RestController
@SpringBootApplication(exclude = {HibernateJpaAutoConfiguration.class},
        scanBasePackages = "com.wuxiu.galaxy")
public class GalaxyServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(GalaxyServiceApplication.class, args);
    }

}
