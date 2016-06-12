/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iti.agrimarket.view;

import java.util.Locale;
import org.iti.agrimarket.business.OfferService;
import org.iti.agrimarket.model.pojo.UserOfferProductFixed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author muhammad
 */
@Controller
@RequestMapping("web")
public class OfferProductController {

    @Autowired
    private OfferService offerService;

    public OfferService getOfferService() {
        return offerService;
    }

    public void setOfferService(OfferService offerService) {
        this.offerService = offerService;
    }

    @RequestMapping(value = {"/getOffer.htm"})
    public String OfferProducts(@RequestParam(value = "id", required = true) int offerId,Locale locale, Model model) {
        UserOfferProductFixed offerProductFixed = null;
        locale = LocaleContextHolder.getLocale();
        System.out.println("id" + offerId);
        offerProductFixed = offerService.getOfferProductEager(offerId);
        model.addAttribute("offerProduct", offerProductFixed);
        model.addAttribute("lang",locale);
        return "preview";
    }
}
