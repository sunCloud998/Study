package com.sfy.exercise.common;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 测试类的基类
 * 主要用于启动Spring框架、加载配置文件
 */
@ContextConfiguration(locations={"classpath:spring/spring.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public abstract class TestBase extends AbstractJUnit4SpringContextTests {

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }
}
