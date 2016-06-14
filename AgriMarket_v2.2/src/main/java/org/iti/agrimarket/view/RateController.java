/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iti.agrimarket.view;


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
    
    

    @RequestMapping(value = "/addRate.htm",method = RequestMethod.GET)
    public @ResponseBody String addRate(@RequestParam(value = "rating", required = true) int rating,
           @RequestParam(value = "reviewText", required = false) String reviewText ,
           @RequestParam(value = "raterID", required = true) int raterId,@RequestParam(value = "ratedID", required = true) int ratedId, Model model) {

       int rate=  (Integer) rating;
        System.out.println("@@@@@@@@@@@Rater& ratedID"+raterId+"%%%%%%%%%"+ratedId);
      userRateService.calUserWeights(raterId,ratedId,rate,reviewText);


        return "no_errors";
    }
}
