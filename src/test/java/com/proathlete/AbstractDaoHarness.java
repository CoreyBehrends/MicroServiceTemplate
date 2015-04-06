package com.proathlete;

import com.proathlete.model.Greeting;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.junit.Before;

public class AbstractDaoHarness {


    protected SessionFactory sessionFactory;

    protected static Configuration getConfiguration() {
        Configuration configuration = new Configuration();
        configuration.setProperty("hibernate.connection.driver_class", "org.h2.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:h2:~/test");

        configuration.setProperty("hibernate.connection.pool_size", "1");
        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        configuration.setProperty("hibernate.cache.provider_class", "org.hibernate.cache.internal.NoCachingRegionFactory");
        configuration.setProperty("hibernate.show_sql", "true");
        configuration.setProperty("hibernate.hbm2ddl.auto", "create");
        configuration.setProperty("hibernate.connection.autocommit", "false");
        configuration.setProperty("hibernate.current_session_context_class","thread");
        configuration.addAnnotatedClass(Greeting.class);


        return configuration;
    }

    @Before
    public void setup(){
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(getConfiguration().getProperties());
        sessionFactory = getConfiguration().buildSessionFactory(builder.build());
    }
}
