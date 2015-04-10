package com.proathlete.config;

import com.proathlete.api.HelloWorldController;
import dagger.ObjectGraph;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.ScanningHibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class App extends Application<Config> {


    public static void main(String[] args) throws Exception {       //NOSONAR throws Exception required by parent class

        new App().run(args);
    }

    @Override
    public void initialize(Bootstrap<Config> productCatalogConfigBootstrap) {
        productCatalogConfigBootstrap.addBundle(hibernateBundle);
    }

    @Override
    public void run(Config config, Environment environment) throws Exception {

        //Init Dagger
        ObjectGraph objectGraph = ObjectGraph.create(new DaggerModule(hibernateBundle.getSessionFactory()));


        //Add endpoints to Jersey
        environment.jersey().register(objectGraph.get(HelloWorldController.class));


    }

    private final ScanningHibernateBundle<Config> hibernateBundle =
            new ScanningHibernateBundle<Config>("com.proathlete.model") {
                @Override
                public DataSourceFactory getDataSourceFactory(Config configuration) {
                    return configuration.getDataSourceFactory();
                }
            };
}

