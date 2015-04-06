package com.proathlete.config;


import com.proathlete.api.HelloWorldController;
import com.proathlete.service.HelloWorldService;
import com.proathlete.service.HelloWorldServiceImpl;
import dagger.Module;
import dagger.Provides;
import org.hibernate.SessionFactory;

import javax.inject.Singleton;

@Module(
        injects = {HelloWorldController.class},
        library = true
)
public class DaggerModule {

    private SessionFactory sessionFactory;

    public DaggerModule(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Provides
    @Singleton
    public HelloWorldController provideHelloWorldController(HelloWorldService helloWorldService) {

        return new HelloWorldController(helloWorldService);
    }

    @Provides
    @Singleton
    public HelloWorldService provideHelloWorldService() {

        return new HelloWorldServiceImpl();
    }


}
