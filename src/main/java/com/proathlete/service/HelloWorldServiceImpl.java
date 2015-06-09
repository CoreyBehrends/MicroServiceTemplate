package com.proathlete.service;

import com.proathlete.client.GreetingClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class HelloWorldServiceImpl implements HelloWorldService {

    GreetingClient greetingClient;
    private static final Logger LOGGER = LoggerFactory.getLogger(HelloWorldServiceImpl.class);


    public HelloWorldServiceImpl(GreetingClient greetingClient){
        this.greetingClient = greetingClient;
    }

    @Override
    public String sayHello() {

        try {
            return greetingClient.getGreeting().getGreeting();
        } catch (IOException e) {
            LOGGER.error("An error occurred while saying hello",e);
        }

        return "";
    }
}
