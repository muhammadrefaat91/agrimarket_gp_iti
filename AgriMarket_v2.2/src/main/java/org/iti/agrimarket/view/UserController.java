/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iti.agrimarket.view;


import org.iti.agrimarket.business.UserService;
import org.iti.agrimarket.model.pojo.User;
import org.iti.agrimarket.model.pojo.UserOfferProductFixed;
import org.springframework.beans.factory.annotation.Autowired;
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
public class UserController {

    @Autowired
    private UserService userService;

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

   

    @RequestMapping(value = "/getUser")
    public String OfferProducts(@RequestParam(value = "id", required = true) int offerId, Model model) {
        User user = null;
        System.out.println("id@@@@@@@@@@@@@@@@@@" + offerId);

        user =  userService.getUserEager(offerId);
        for (Object col : user.getUserOfferProductFixeds()) {
            UserOfferProductFixed fixed = (UserOfferProductFixed) col;
            System.out.println("user offer product fixed"+fixed.getId());
        }

        model.addAttribute("userHasOffer", user);
        return "view_user";
    }
}