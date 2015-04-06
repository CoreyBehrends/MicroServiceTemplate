package com.proathlete.model;

import com.proathlete.persistance.AbstractEntityImpl;

import javax.persistence.Entity;

@Entity
public class Greeting extends AbstractEntityImpl {


    private String greeting;

    public String getGreeting() {
        return greeting;
    }

    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }
}
