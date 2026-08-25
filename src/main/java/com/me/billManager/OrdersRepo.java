package com.me.billManager;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;

public class OrdersRepo {
	
	public void save(Products product , Tables table) {
		
			
		EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
		try {
			
			em.getTransaction().begin();
			em.persist(product);
			em.persist(table);
			em.getTransaction().commit();
			
		}catch(Exception e) {
			if(em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			System.err.print("Error: " + e);
		}finally {
			em.close();
		}
		
	}

	
}
