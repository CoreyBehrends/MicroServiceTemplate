package com.proathlete.config;

import com.proathlete.api.HelloWorldController;
import dagger.ObjectGraph;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class App extends Application<Config> {


    public static void main(String[] args) throws Exception {       //NOSONAR throws Exception required by parent class
        new App().run(args);
    }

    @Override
    public void initialize(Bootstrap<Config> productCatalogConfigBootstrap) {

    }

    @Override
    public void run(Config config, Environment environment) throws Exception {

        //Init Dagger
        ObjectGraph objectGraph = ObjectGraph.create(new DaggerModule(environment,config));

        //Add endpoints to Jersey
        environment.jersey().register(objectGraph.get(HelloWorldController.class));

    }

}

