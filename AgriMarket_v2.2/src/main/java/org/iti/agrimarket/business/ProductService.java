/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iti.agrimarket.business;

import java.util.List;
import org.iti.agrimarket.model.pojo.Category;
import org.iti.agrimarket.model.pojo.Product;

/**
 *
 * @author Israa
 */
public interface ProductService {
 
    
    //Israa
    public int addProduct(Product product);
    
    
    public void updateProduct(Product product);
    
    public void deleteProduct(Integer id);
    
    public Product getProduct(Integer id);
    
    public List<Product> getChildrenOf(Integer categoryId);   
    public List<Product> searchProduct(String name); 
    
    
    //amr
      public List<Product> getAllProducts();
      public int create(Product product);
      
      
      public List<Product> getAllProductsEager();

    public Product getProductEager(Integer productId);
    
}
