package com.me.billManager;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;

public class ProductRepo {
	
	public void save(Products product) {
		 EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
		 
		try{
			
			em.getTransaction().begin();
			em.persist(product);
			em.getTransaction().commit();
			
		}catch(PersistenceException e) {
			
			if(em.getTransaction().isActive()) {
				em.getTransaction().rollback();
				System.err.print("Product Saving Error: " + e);
			}
			
		}
		
	}
	
	public  List<Products> getAllProducts(){
		EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
		
		try {
			return em.createQuery("SELECT p FROM Products p", Products.class).getResultList();
		}finally {
			em.close();
		}
	}
	
	public void initDefaultProducts() {
		
		List<Products>productList = getAllProducts();
		
		if(productList.isEmpty()) {
			
			  save(new Products("Crispy Chicken Burger", 220, 95, 50));
		        save(new Products("Chicken Tenders (6 Pcs)", 180, 75, 60));
		        save(new Products("Spicy Hot Wings (8 Pcs)", 240, 110, 40));
		        save(new Products("Grilled Chicken Wrap", 190, 80, 35));
		        save(new Products("Family Chicken Bucket", 450, 210, 20));
		        save(new Products("Cajun French Fries", 75, 25, 100));
			
		}
		
      
		
	}
	
	

}
