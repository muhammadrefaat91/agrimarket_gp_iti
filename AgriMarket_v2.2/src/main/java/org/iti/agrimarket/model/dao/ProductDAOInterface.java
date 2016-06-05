/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iti.agrimarket.model.dao;

import java.util.List;
import org.iti.agrimarket.model.pojo.Category;
import org.iti.agrimarket.model.pojo.Product;

/**
 *
 * @author Israa
 */
public interface ProductDAOInterface {
    
    //Israa
    public int create(Product product);
    
    public void edit(Product product);
    
    public void destroy(Integer id);
    
    public List<Product> findProductEntities() ;

    public Product findProduct(Integer id);
    
    //Israa
    
    public List<Product> getChildrenOf(Integer categoryId);
    
    
    public List<Product> searchProduct(String productName);

//amr
  public List<Product> getAllProducts();

}
