package com.proathlete.service;


import com.proathlete.client.GreetingClient;
import com.proathlete.model.Greeting;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class HelloWorldServiceTest {

    private HelloWorldService helloWorldService;
    GreetingClient greetingClient;

    @Before
    public void setup(){
        greetingClient = mock(GreetingClient.class);
        helloWorldService = new HelloWorldServiceImpl(greetingClient);

    }

    @Test
    public void helloWorldService_says_hello() throws IOException {
        Greeting greeting = new Greeting();
        greeting.setGreeting("Hello!");
        greeting.setId(1L);
        when(greetingClient.getGreeting()).thenReturn(greeting);
        String hello = helloWorldService.sayHello();
        assertTrue("Hello!".equals(hello));

        //Handles exceptions
        when(greetingClient.getGreeting()).thenThrow(new IOException("Bla"));
        hello = helloWorldService.sayHello();
        assertTrue("".equals(hello));

    }


}
