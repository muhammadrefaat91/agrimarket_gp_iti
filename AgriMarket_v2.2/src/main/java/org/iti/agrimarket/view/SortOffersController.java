/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iti.agrimarket.view;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import org.iti.agrimarket.business.OfferService;
import org.iti.agrimarket.model.pojo.UserOfferProductFixed;
import org.iti.agrimarket.util.SortOffers;
import org.iti.agrimarket.util.SortOffersByDate;
import org.iti.agrimarket.util.SortOffersByPrice;
import org.iti.agrimarket.util.SortOffersByQuantity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author muhammad
 */
@Controller
@RequestMapping("web")
public class SortOffersController {
    
    @Autowired
    private OfferService offerService;

    public OfferService getOfferService() {
        return offerService;
    }

    public void setOfferService(OfferService offerService) {
        this.offerService = offerService;
    }

    
    
    @RequestMapping(value = "/sort.htm",method = RequestMethod.GET)
    public String addRate(HttpServletRequest request ,@RequestParam(value = "sortType", required = false) String sortType,
            @RequestParam(value = "searchText", required = false) String productName,Locale locale, Model model) {
        SortOffers sortOffers;
        locale = LocaleContextHolder.getLocale();
        List<UserOfferProductFixed> offers = (List<UserOfferProductFixed>) request.getSession().getAttribute("getAllOfferProducts");
        System.out.println("List<UserOfferProductFixed> fixeds"+offers);
       
        System.out.println("Sort....."+sortType);
        
        if (sortType != null &&  (sortType.equals("Price") || sortType.equals("السعر"))){
            sortOffers = new SortOffersByPrice();
            sortOffers.sort(offers, 0, offers.size()-1);
        }else if(sortType != null &&  (sortType.equals("Quantity") || sortType.equals("الكميه"))){
            sortOffers = new SortOffersByQuantity();
            sortOffers.sort(offers, 0, offers.size()-1);
        }else if(sortType != null &&  (sortType.equals("Newest") || sortType.equals("الجديد"))){
            sortOffers = new SortOffersByDate();
            sortOffers.sort(offers, 0, offers.size()-1);
        }else{
            offers = offerService.getAllOfferProducts();
        }
        model.addAttribute("getAllOfferProducts", offers);
        System.out.println("sorted"+offers);
        model.addAttribute("lang", locale);

        return "offers_page";
    }

}
