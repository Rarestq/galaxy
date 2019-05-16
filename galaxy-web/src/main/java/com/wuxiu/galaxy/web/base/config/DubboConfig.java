package com.wuxiu.galaxy.web.base.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * All rights Reserved, Designed By www.maihaoche.com
 *
 * @author: wuxiu
 * @date: 2019/5/16 17:32
 */
@Configuration
@ImportResource(locations = "classpath:dubbo/dubbo-all.xml")
public class DubboConfig {
}
