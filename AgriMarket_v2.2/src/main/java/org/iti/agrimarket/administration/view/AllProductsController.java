/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iti.agrimarket.administration.view;

import org.iti.agrimarket.view.*;
import java.util.List;
import java.util.Locale;
import javax.servlet.ServletContext;
import org.iti.agrimarket.business.CategoryService;
import org.iti.agrimarket.business.OfferService;
import org.iti.agrimarket.business.ProductService;
import org.iti.agrimarket.model.pojo.Category;
import org.iti.agrimarket.model.pojo.Product;
import org.iti.agrimarket.model.pojo.UserOfferProductFixed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
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
public class AllProductsController {

    @Autowired
    private ProductService productService;
    @Autowired
    private ServletContext servletContext;

    public ServletContext getServletContext() {
        return servletContext;
    }

    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    public ProductService getProductService() {
        return productService;
    }

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }


    @RequestMapping(value = {"admin/products_page.htm"}, method = RequestMethod.GET)
    public String allCategories(Locale locale,Model model) {
        locale = LocaleContextHolder.getLocale();
        List<Product> products=productService.getAllProductsEager();
        model.addAttribute("products", products);
        model.addAttribute("lang",locale);
        return "admin/products_page";
    }

    @RequestMapping(value = {"/admin/preview_product.htm"}, method = RequestMethod.GET)
    public String getCategory(@RequestParam(value = "id")Integer productId , Locale locale, Model model) {
        locale = LocaleContextHolder.getLocale();
       
        Product product=productService.getProductEager(productId);
        model.addAttribute("product", product);
        model.addAttribute("lang", locale);
        return "admin/preview_product";
    }
}
