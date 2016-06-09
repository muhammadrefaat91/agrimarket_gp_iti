/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iti.agrimarket.view;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.iti.agrimarket.business.OfferService;
import org.iti.agrimarket.model.pojo.UserOfferProductFixed;
import org.iti.agrimarket.util.SortOffers;
import org.iti.agrimarket.util.SortOffersByDate;
import org.iti.agrimarket.util.SortOffersByPrice;
import org.iti.agrimarket.util.SortOffersByQuantity;
import org.springframework.beans.factory.annotation.Autowired;
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

    
    
    @RequestMapping(value = "/sort",method = RequestMethod.GET)
    public String addRate(HttpServletRequest request ,@RequestParam(value = "searchType", required = false) String searchType,
            @RequestParam(value = "searchText", required = false) String productName, Model model) {
        SortOffers sortOffers;
        List<UserOfferProductFixed> offers = (List<UserOfferProductFixed>) request.getSession().getAttribute("getAllOfferProducts");
        System.out.println("List<UserOfferProductFixed> fixeds"+offers);
       
        System.out.println("Sort....."+searchType);
//    List<UserOfferProductFixed> offerproducts = null;
        
        if (searchType != null &&  searchType.equals("Price")){
            sortOffers = new SortOffersByPrice();
            sortOffers.sort(offers, 0, offers.size()-1);
        }else if(searchType != null &&  searchType.equals("Quantity")){
            sortOffers = new SortOffersByQuantity();
            sortOffers.sort(offers, 0, offers.size()-1);
        }else if(searchType != null &&  searchType.equals("Newest")){
            sortOffers = new SortOffersByDate();
            sortOffers.sort(offers, 0, offers.size()-1);
        }else{
            offers = offerService.getAllOfferProducts();
        }
//        request.getSession().setAttribute("getAllOfferProducts", offers);
        model.addAttribute("getAllOfferProducts", offers);
        System.out.println("sorted"+offers);

        return "offers_page";
    }
    
 
   

     

//    static void quickSortDate(Date arr[], int left, int right) {
//        int index = partitionDate(arr, left, right);
//        if (left < index - 1) {
//            quickSortDate(arr, left, index - 1);
//        }
//        if (index < right) {
//            quickSortDate(arr, index, right);
//        }
//    }
}
