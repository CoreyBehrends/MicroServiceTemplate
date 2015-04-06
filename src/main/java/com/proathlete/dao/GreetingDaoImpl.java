package com.proathlete.dao;

import com.proathlete.model.Greeting;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;


public class GreetingDaoImpl extends AbstractDAO<Greeting> implements GreetingDao {

    public GreetingDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Greeting getById(Long id) {
        return (Greeting) criteria().add(Restrictions.eq("id", id)).uniqueResult();
    }

    @Override
    public Greeting save(Greeting entity) {
        return persist(entity);
    }

    @Override
    public boolean delete(Greeting entity) {
        currentSession().delete(entity);
        return true;
    }
}
