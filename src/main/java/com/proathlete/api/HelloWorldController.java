package com.proathlete.api;

import com.codahale.metrics.annotation.ExceptionMetered;
import com.codahale.metrics.annotation.Timed;
import com.proathlete.service.HelloWorldService;
import io.dropwizard.hibernate.UnitOfWork;

import javax.ws.rs.GET;
import javax.ws.rs.Path;


@Path("/")
public class HelloWorldController {

    private HelloWorldService helloWorldService;
    public HelloWorldController(HelloWorldService helloWorldService){
       this.helloWorldService = helloWorldService;

    }

    @GET
    @Path("hello/")
    @Timed(name = "sayHello_timing")
    @ExceptionMetered(name = "sayHello_exception_rate")
    @UnitOfWork
    public String sayHello(){
        return helloWorldService.sayHello();
    }
}
