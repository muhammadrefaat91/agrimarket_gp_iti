/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iti.agrimarket.view;

import java.util.List;
import java.util.Locale;
import javax.servlet.ServletContext;
import org.iti.agrimarket.business.CategoryService;
import org.iti.agrimarket.business.OfferService;
import org.iti.agrimarket.model.pojo.Category;
import org.iti.agrimarket.model.pojo.UserOfferProductFixed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Israa
 */
@Controller
public class IndexController {

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

    @RequestMapping(value = {"index.htm"}, method = RequestMethod.GET)
    public String allCategories(Locale locale,Model model) {
        locale = LocaleContextHolder.getLocale();
        List<Category> categorys = null;
        System.out.println("index controller");
        if (servletContext.getAttribute("allcategories") == null) {
            categorys = categoryService.getChildrenOf(1);
        }
        System.out.println("categories = " + categorys);
        servletContext.setAttribute("allcategories", categorys);

        List<UserOfferProductFixed> latestOffers = offerService.getLatestOffers();

        System.out.println("latest offers : "+latestOffers);
        model.addAttribute("latestOffers", latestOffers);
        model.addAttribute("lang",locale);
        System.out.println(model.containsAttribute("latestOffers"));
        return "index";
    }

}
