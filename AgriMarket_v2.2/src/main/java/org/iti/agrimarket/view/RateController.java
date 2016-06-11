/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iti.agrimarket.view;


import java.util.List;
import org.iti.agrimarket.business.UserRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author muhammad
 */
@Controller
@RequestMapping("web")
public class RateController {
    @Autowired
    private UserRateService userRateService;

    public UserRateService getUserRateService() {
        return userRateService;     
    }

    public void setUserRateService(UserRateService userRateService) {
        this.userRateService = userRateService;
    }
    
    

    @RequestMapping(value = "/addRate",method = RequestMethod.GET)
    public @ResponseBody String addRate(@RequestParam(value = "rating", required = true) int rating,
           @RequestParam(value = "reviewText", required = false) String reviewText ,@RequestParam(value = "uID", required = true) int userId, Model model) {

       int rate=  (Integer) rating;
        System.out.println("@@@@@@@@@@@Rate& userID"+rate+"%%%%%%%%%"+userId);
      userRateService.calUserWeights(2,1,rate,reviewText);
              System.out.println("@@@@@@@@@@@Rate");

//        if (list.isEmpty()) {
//            System.out.println("is empty");
//        }
//        for (Integer list1 : list) {
//            System.out.println("list"+list1.toString());
//        }
//        if (user ==null) {
////            System.out.println("path"+request.get);
//            return "redirect:/login.htm";
//        }
//        model.addAttribute("userHasOffer", user);
    
        return "no_errors";
    }
}
