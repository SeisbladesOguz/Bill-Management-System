package com.me.billManager;

import java.util.List; 
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;

public class TableRepo {

    public void save(Tables table) {
    	EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        try  {
            em.getTransaction().begin();
            em.persist(table);
            em.getTransaction().commit();
        }catch(PersistenceException e) {
        	
        	
        	if(em.getTransaction().isActive()) {
        		em.getTransaction().rollback();
        		System.err.print("Save problem" + e.getMessage() + "RollBack");
        		
        	}
        	
        }
        
        finally {
        	em.close();
        }
    } 

    public List<Tables> getAllTables() {
    	EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        try  {
            return em.createQuery("SELECT t FROM Tables t", Tables.class).getResultList();
        }catch(PersistenceException e) {
        	System.err.print("Listing problem" +  e.getMessage());
        }
		return null;	
    }
    
    public void initDefaultTablesIfEmpty() {
        List<Tables> list = getAllTables();
        if (list.isEmpty()) {
            for (int i = 1; i <= 10; i++) {
                save(new Tables(i));
            }
        }
    }
}