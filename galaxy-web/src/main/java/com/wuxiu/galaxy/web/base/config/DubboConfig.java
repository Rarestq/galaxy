package com.wuxiu.galaxy.web.base.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * dubbo 配置
 *
 * @author: wuxiu
 * @date: 2019/5/16 17:32
 */
@Configuration
@ImportResource(locations = "classpath:dubbo/dubbo-all.xml")
public class DubboConfig {
}
