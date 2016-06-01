/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iti.agrimarket.model.dao;

import java.util.List;
import org.iti.agrimarket.model.pojo.Category;

/**
 *
 * @author Israa
 */
public interface CategoryDAOInterface {
    
    public int create(Category category);
    
    public void edit(Category category);
    
    public void destroy(Integer id);
    
    public List<Category> findCategoryEntities() ;

    public Category findCategory(Integer id);
    
    //Israa
    
    public List<Category> getChildrenOf(Integer categoryId);
    
    public List<Category> searchCategory(String categoryName);
    
}
