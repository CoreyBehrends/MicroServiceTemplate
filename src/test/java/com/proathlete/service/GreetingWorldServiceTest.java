package com.proathlete.service;


import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

public class GreetingWorldServiceTest {

    private HelloWorldService helloWorldService;


    @Before
    public void setup(){
        helloWorldService = new HelloWorldServiceImpl();

    }

    @Test
    public void helloWorldService_says_hello(){
        String hello = helloWorldService.sayHello();
        assertTrue("Hello!".equals(hello));
    }


}
