/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iti.agrimarket.business;

import java.util.List;
import org.iti.agrimarket.model.pojo.Category;

/**
 *
 * @author Israa
 */
public interface CategoryService {

    
    public int addCategory(Category category);
    
    public Category getCategory(Integer categoryId);
    
    public List<Category> getChildrenOf(Integer categoryId);

    public void updateCategory(Category category);

    public void deleteCategory(Integer id);
    
    public List<Category> searchCategory(String name);
    
    public List<Category> getAllCategories();
}
