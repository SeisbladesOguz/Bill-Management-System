package com.me.billManager.services;

import com.me.billManager.entity.Products;
import com.me.billManager.entity.Tables;
import com.me.billManager.repo.OrdersRepo;

public class OrderService {
    
  private OrdersRepo ordersRepo = new OrdersRepo();
  
  public void orderManageSaveRepo(Tables table , Products product) {
	  ordersRepo.save(table, product);
  }
  
}