package com.me.billManager.services;

import java.util.List;
import com.me.billManager.entity.Products;
import com.me.billManager.repo.ProductRepo;

public class ProductService {
    
    private ProductRepo productRepo = new ProductRepo();
    
    public List<Products> getAllProducts() {
        return productRepo.getAllProducts();
    }
    
    public void defaultProducts() {
        productRepo.initDefaultProducts();
    }
}