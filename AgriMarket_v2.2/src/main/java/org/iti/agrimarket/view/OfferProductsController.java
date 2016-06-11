/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iti.agrimarket.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import org.iti.agrimarket.business.OfferService;
import org.iti.agrimarket.model.pojo.UserOfferProductFixed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 *
 * @author muhammad
 */
@Controller
@RequestMapping("web")
@SessionAttributes("getAllOfferProducts")
public class OfferProductsController {

    @Autowired
    private OfferService offerService;

    public OfferService getOfferService() {
        return offerService;
    }

    public void setOfferService(OfferService offerService) {
        this.offerService = offerService;
    }

    @RequestMapping(value = {"getoffers.htm"})
    public String OfferProducts(HttpServletRequest request,
            @RequestParam(value = "lang", required = false) Locale locale,
            @RequestParam(value = "name", required = false) String productName,
            @RequestParam(value = "category", required = false) String categoryName, Model model) {
        locale = LocaleContextHolder.getLocale();

        String language = locale.getLanguage();
        List<UserOfferProductFixed> offerproducts = null;
        System.out.println("language!!!!!!!!!!!!!!!" + language);

        if (productName != null && !productName.equals("")) {
            offerproducts = offerService.getOffersByProduct(productName);
        } else if (categoryName != null && !categoryName.equals("")) {
//            offerproducts = offerService.getOffersByCategory(categoryName);
        } else {
            offerproducts = offerService.getAllOfferProducts();
        }

        model.addAttribute("getAllOfferProducts", offerproducts);

        return "offers_page";
    }
}
