package com.proathlete.service;


import com.proathlete.dao.GreetingDao;
import com.proathlete.model.Greeting;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class HelloWorldServiceTest {

    private HelloWorldService helloWorldService;
    GreetingDao greetingDao;

    @Before
    public void setup(){
        greetingDao = mock(GreetingDao.class);
        helloWorldService = new HelloWorldServiceImpl(greetingDao);

    }

    @Test
    public void helloWorldService_says_hello(){
        Greeting greeting = new Greeting();
        greeting.setGreeting("Hello!");
        greeting.setId(1L);
        when(greetingDao.getById(anyLong())).thenReturn(greeting);
        String hello = helloWorldService.sayHello();
        assertTrue("Hello!".equals(hello));
    }


}
