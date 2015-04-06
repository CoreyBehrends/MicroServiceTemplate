package com.proathlete.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.proathlete.productcatalog.AbstractDaoHarness;
import com.proathlete.productcatalog.catalog.domain.Product;
import com.proathlete.productcatalog.catalog.domain.ProductImpl;
import com.proathlete.productcatalog.catalog.domain.Sku;
import com.proathlete.productcatalog.catalog.domain.SkuImpl;
import com.proathlete.productcatalog.persistance.CrudMethods;
import org.hibernate.Transaction;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;


public class GreetingDaoTest extends AbstractDaoHarness {

    protected CrudMethods<Product> productDao;
    protected CrudMethods<Sku> skuDao;

    @Test
    public void productDao_Persists_Entities() throws JsonProcessingException {

        Transaction trans = sessionFactory.getCurrentSession().beginTransaction();

        productDao = new ProductDaoImpl(sessionFactory);
        skuDao = new SkuDaoImpl(sessionFactory);
        ProductImpl entity = new ProductImpl();

        SkuImpl defaultSku = new SkuImpl();
        defaultSku.setPrice(BigDecimal.valueOf(100));
        entity.getSkus().add(defaultSku);

        entity.setName("The Manderson");
        entity.setModelNumber("BFG 9000");
        entity.setDescription("Desc");
        entity.setShortDescription("Short");
        productDao.save(entity);
        trans.commit();

        trans = sessionFactory.getCurrentSession().beginTransaction();
        ProductImpl queryProduct = (ProductImpl ) productDao.getById(entity.getId());
        trans.commit();

        assertNotNull("Prod id was null",entity.getId());



        assertEquals(entity.getId(), queryProduct.getId());
        assertEquals(entity.getId(), queryProduct.getSkus().get(0).getProductId());
        assertEquals(entity.getDescription(), queryProduct.getDescription());
        assertEquals(entity.getModelNumber(), queryProduct.getModelNumber());
    }

    @Test
    public void productDao_Deletes_Entities(){



        Transaction trans = sessionFactory.getCurrentSession().beginTransaction();

        productDao = new ProductDaoImpl(sessionFactory);
        Product entity = new ProductImpl();

        SkuImpl defaultSku = new SkuImpl();
        defaultSku.setPrice(BigDecimal.valueOf(100));
        entity.setName("The Manderson");

        productDao.save(entity);

        Product queryProduct = productDao.getById(entity.getId());

        trans.commit();

        assertNotNull("Product was not found", queryProduct);

        trans = sessionFactory.getCurrentSession().beginTransaction();
        productDao.delete(queryProduct);
        trans.commit();

        trans = sessionFactory.getCurrentSession().beginTransaction();
        queryProduct = productDao.getById(entity.getId());
        trans.commit();

        assertNull("Product not deleted", queryProduct);

    }

}
