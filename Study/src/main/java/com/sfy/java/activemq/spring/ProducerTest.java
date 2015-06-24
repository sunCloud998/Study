package com.sfy.java.activemq.spring;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

/**
 * Created by Administrator on 2015/6/23.
 */
@ContextConfiguration(locations={"classpath:spring/spring.xml"})
public class ProducerTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    private ProducerService producerService;

    @Test
    public void send(){
        producerService.send();
    }

}
