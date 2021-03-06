/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iti.agrimarket.view;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.iti.agrimarket.business.UserService;
import org.iti.agrimarket.model.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
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
@Scope(value = "session")
public class LoginController {

    @Autowired
    private UserService userService;

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/userlogin", method = RequestMethod.POST)
    public String login(HttpServletRequest request,Model model) {
        String email = request.getParameter("mail");
        String password = request.getParameter("password");
        User result = userService.userLogin(email, password);
        if (result == null) {
            model.addAttribute("error", "Invalid Email or password!");
            return "sign_in";
        }
        request.getSession().setAttribute("user", result);
        return "index";

    }
    
    @RequestMapping(value = "/dummylogin.htm", method = RequestMethod.GET)
    public String loginDummy(HttpServletRequest request, HttpServletResponse response,Model model) {
       User result = userService.getUserEager(1);
        if (result == null) {
            model.addAttribute("error", "Invalid Email or password!");
            return "sign_in";
        }
        request.getSession().setAttribute("user", result);
        try {
            response.sendRedirect(request.getContextPath()+"/index.htm");
        } catch (IOException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "profile";

    }

}
