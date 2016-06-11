/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iti.agrimarket.view;

import java.util.List;
import javax.servlet.ServletContext;
import org.iti.agrimarket.business.CategoryService;
import org.iti.agrimarket.model.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author muhammad
 */
@Controller
@RequestMapping("web")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ServletContext servletContext;

    public ServletContext getServletContext() {
        return servletContext;
    }

    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    public CategoryService getCategoryService() {
        return categoryService;
    }

    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @RequestMapping(value = {"/allgategories"}, method = RequestMethod.GET)
    public String allCategories() {
        List<Category> categorys = null;
        System.out.println("allcategories");
        if (servletContext.getAttribute("allcategories") == null) {
            categorys = categoryService.getChildrenOf(1);
//            servletContext.setAttribute("allcategories", categorys);
        } else {
            categorys = (List<Category>) servletContext.getAttribute("allcategories");
        }
        servletContext.setAttribute("allcategories", categorys);

        return "offers_page";
    }
    
    
    @RequestMapping(value = {"/allcategories"}, method = RequestMethod.GET)
    public String allCategories1() {
        List<Category> categorys = null;
        System.out.println("allcategories1");
        if (servletContext.getAttribute("allcategories1") == null) {
            categorys = categoryService.getChildrenOf(1);
        servletContext.setAttribute("allcategories1", categorys);}

        return "index";
    }
}
