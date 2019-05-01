package com.wuxiu.galaxy.test.base;

import com.wuxiu.galaxy.web.GalaxyWebApplication;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 测试基类
 * @author wuxiu
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = GalaxyWebApplication.class)
@PropertySource("classpath:application-local.yml")
public abstract class BaseTest {


}
