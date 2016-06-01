/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iti.agrimarket.view;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.iti.agrimarket.business.OfferService;
import org.iti.agrimarket.model.pojo.UserOfferProductFixed;
import org.springframework.beans.factory.annotation.Autowired;
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

    @RequestMapping(value = "/getoffers")
    public String OfferProducts(HttpServletRequest request ,@RequestParam(value = "category", required = false) String categoryName,
            @RequestParam(value = "search", required = false) String offerName, Model model) {
        System.out.println("all offer products: " + categoryName);
        List<UserOfferProductFixed> offerproducts = null;
        request.setAttribute("okkllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllll", request.getParameter("category"));
        System.out.println("attribute: "+request.getAttribute("selectedModule"));

        if (categoryName != null && !categoryName.equals("") && !categoryName.equals("Select Category")
                && (offerName == null || offerName.equals(""))) {
            offerproducts = offerService.getOffersByParent(categoryName);
        } else if (offerName != null && !offerName.equals("") && (categoryName == null || categoryName.equals("Select Category"))) {
            System.out.println("producct%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
            offerproducts = offerService.getOffersByProduct(offerName);
        } else if (offerName != null && categoryName != null && !categoryName.equals("")) {
            offerproducts = offerService.getOffersByProductAndCategory(offerName, categoryName);
////            if (offerproducts.isEmpty()) {
//                model.addAttribute("getAllOfferProducts", "dcddcdc");
//                return  "offers_page";
//            }
        } else {
            offerproducts = offerService.getAllOfferProducts();
        }
        model.addAttribute("getAllOfferProducts", offerproducts);
        return "offers_page";
    }
}
