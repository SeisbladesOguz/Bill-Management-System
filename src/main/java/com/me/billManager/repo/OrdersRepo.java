package com.me.billManager.repo;

import com.me.billManager.entity.Orders;
import com.me.billManager.entity.Products;
import com.me.billManager.entity.Tables;
import com.me.billManager.util.JPAUtil;
import jakarta.persistence.EntityManager;

public class OrdersRepo {
    
    public void save(Tables table, Products product) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        try {
            em.getTransaction().begin();
            
           
            Orders order = new Orders(table, product);
            em.persist(order);
            
            em.getTransaction().commit();
        } catch(Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}