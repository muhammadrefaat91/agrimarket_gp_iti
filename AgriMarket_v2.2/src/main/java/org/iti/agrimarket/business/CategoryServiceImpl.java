/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iti.agrimarket.business;

import java.util.List;
import org.iti.agrimarket.model.dao.CategoryDAOInterface;
import org.iti.agrimarket.model.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Israa
 */
@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryDAOInterface categoryDao;

    public CategoryDAOInterface getCategoryDao() {
        return categoryDao;
    }

    public void setCategoryDao(CategoryDAOInterface categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Override
    public List<Category> getChildrenOf(Integer categoryId) {
        return categoryDao.getChildrenOf(categoryId);
    }

    @Override
    public Category getCategory(Integer categoryId) {
        return categoryDao.findCategory(categoryId);
    }

    @Override
    public int addCategory(Category category) {
        return categoryDao.create(category);
    }

    @Override
    public void updateCategory(Category category) {
        categoryDao.edit(category);
    }

    @Override
    public void deleteCategory(Integer id) {
        categoryDao.destroy(id);
    }

    @Override
    public List<Category> searchCategory(String name) {
        return categoryDao.searchCategory(name);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryDao.findCategoryEntities();
    }

}
