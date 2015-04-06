package com.proathlete.dao;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.proathlete.AbstractDaoHarness;
import com.proathlete.model.Greeting;
import org.hibernate.Transaction;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class GreetingDaoTest extends AbstractDaoHarness {

    private GreetingDao greetingDao;


    @Test
    public void greetingDao_Persists_Entities() throws JsonProcessingException {

        Transaction trans = sessionFactory.getCurrentSession().beginTransaction();

        greetingDao = new GreetingDaoImpl(sessionFactory);
        Greeting greeting = new Greeting();
        greeting.setGreeting("Hello!");
        greeting = greetingDao.save(greeting);
        trans.commit();

        //Read new entity back out

        trans = sessionFactory.getCurrentSession().beginTransaction();
        Greeting newGreeting = greetingDao.getById(greeting.getId());
        trans.commit();

        //Compare values
        assertEquals(greeting.getGreeting(), newGreeting.getGreeting());


    }

    @Test
    public void greetingDao_Deletes_Entities() throws JsonProcessingException {

        Transaction trans = sessionFactory.getCurrentSession().beginTransaction();

        greetingDao = new GreetingDaoImpl(sessionFactory);
        Greeting greeting = new Greeting();
        greeting.setGreeting("Hello!");
        greeting = greetingDao.save(greeting);
        trans.commit();

        //Read new entity back out, then delete

        trans = sessionFactory.getCurrentSession().beginTransaction();
        greetingDao.delete(greeting);
        trans.commit();

        trans = sessionFactory.getCurrentSession().beginTransaction();
        Greeting newGreeting = greetingDao.getById(greeting.getId());
        trans.commit();

        assertNull(newGreeting);





    }





}
