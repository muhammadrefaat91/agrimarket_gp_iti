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
public class OfferProductController {

    @Autowired
    private OfferService offerService;

    public OfferService getOfferService() {
        return offerService;
    }

    public void setOfferService(OfferService offerService) {
        this.offerService = offerService;
    }

    @RequestMapping(value = "/getOffer")
    public String OfferProducts(@RequestParam(value = "id", required = true) int offerId, Model model) {
        UserOfferProductFixed offerProductFixed = null;
        System.out.println("id" + offerId);
        offerProductFixed = offerService.getOfferProductEager(offerId);
        model.addAttribute("offerProduct", offerProductFixed);
        return "preview";
    }
}
