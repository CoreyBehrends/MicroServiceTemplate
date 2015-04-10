package com.proathlete.service;

import com.proathlete.dao.GreetingDao;
import com.proathlete.model.Greeting;

public class HelloWorldServiceImpl implements HelloWorldService {

    GreetingDao greetingDao;

    public HelloWorldServiceImpl(GreetingDao greetingDao){
        this.greetingDao = greetingDao;
    }

    @Override
    public String sayHello() {
        Greeting greeting = new Greeting();
        greeting.setGreeting("Hello!");
        greetingDao.save(greeting);

        return greetingDao.getById(1L).getGreeting();
    }
}
