package com.proathlete.config;


import com.proathlete.api.HelloWorldController;
import com.proathlete.client.GreetingClientImpl;
import com.proathlete.service.HelloWorldService;
import com.proathlete.service.HelloWorldServiceImpl;
import dagger.Module;
import dagger.Provides;
import io.dropwizard.client.HttpClientBuilder;
import io.dropwizard.setup.Environment;
import org.apache.http.client.HttpClient;

import javax.inject.Singleton;

@Module(
        injects = {HelloWorldController.class},
        library = true
)
public class DaggerModule {

    private HttpClient httpClient;


    public DaggerModule(Environment environment, Config config) {
        httpClient = new HttpClientBuilder(environment).using(config.getHttpClientConfiguration()).build("product_service");
    }

    @Provides
    @Singleton
    public HelloWorldController provideHelloWorldController(HelloWorldService helloWorldService) {

        return new HelloWorldController(helloWorldService);
    }

    @Provides
    @Singleton
    public HelloWorldService provideHelloWorldService() {

        return new HelloWorldServiceImpl(new GreetingClientImpl(httpClient));
    }
}
