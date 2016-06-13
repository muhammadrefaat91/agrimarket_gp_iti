/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iti.agrimarket.administration.view;

import java.util.List;
import java.util.Locale;
import javax.servlet.ServletContext;
import org.iti.agrimarket.business.CategoryService;
import org.iti.agrimarket.business.OfferService;
import org.iti.agrimarket.model.pojo.Category;
import org.iti.agrimarket.model.pojo.UserOfferProductFixed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Israa
 */
@Controller
public class AllCategoriesController {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private OfferService offerService;
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

    public OfferService getOfferService() {
        return offerService;
    }

    public void setOfferService(OfferService offerService) {
        this.offerService = offerService;
    }

    @RequestMapping(value = {"/admin/categories_page.htm"}, method = RequestMethod.GET)
    public String allCategories(Locale locale, Model model) {
        locale = LocaleContextHolder.getLocale();
        List<Category> categorys = null;
        categorys = categoryService.getAllCategories();

        model.addAttribute("categories", categorys);
        model.addAttribute("lang", locale);
        return "admin/categories_page";
    }
    
    @RequestMapping(value = {"/admin/preview_category.htm"}, method = RequestMethod.GET)
    public String getCategory(@RequestParam(value = "id")Integer categoryId , Locale locale, Model model) {
        locale = LocaleContextHolder.getLocale();
        Category category = null;
        category = categoryService.getCategoryEager(categoryId);

        model.addAttribute("category", category);
        model.addAttribute("lang", locale);
        return "admin/preview_category";
    }

}
