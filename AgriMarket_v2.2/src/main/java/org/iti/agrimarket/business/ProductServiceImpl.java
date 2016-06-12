/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iti.agrimarket.business;

import java.util.List;
import org.iti.agrimarket.model.dao.ProductDAOInterface;
import org.iti.agrimarket.model.pojo.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Israa
 */
@Service("productService")
public class ProductServiceImpl implements ProductService {
    
    @Autowired
    private ProductDAOInterface productDao;
    
    public ProductDAOInterface getProductDao() {
        return productDao;
    }
    
    public void setProductDao(ProductDAOInterface productDao) {
        this.productDao = productDao;
    }
    
    @Override
    public List<Product> getChildrenOf(Integer categoryId) {
        return productDao.getChildrenOf(categoryId);
    }
    
    @Override
    public int addProduct(Product product) {
        return productDao.create(product);
    }
    
    @Override
    public void updateProduct(Product product) {
        productDao.edit(product);
    }
    
    @Override
    public Product getProduct(Integer id) {
        return productDao.findProduct(id);
    }
    
    @Override
    public void deleteProduct(Integer id) {
        productDao.destroy(id);
    }

    @Override
    public List<Product> searchProduct(String name) {
        return productDao.searchProduct(name);
    }
    
    
    
     @Override
    public List<Product> getAllProducts() {
    
    return productDao.getAllProducts();
    }
    
    @Override
      public int create(Product product){
       return productDao.create(product);
       }
    }
