/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iti.agrimarket.view;

import java.io.IOException;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.iti.agrimarket.business.UserService;
import org.iti.agrimarket.model.pojo.User;
import org.iti.agrimarket.model.pojo.UserOfferProductFixed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @RequestMapping(value = {"/getUser.htm"})
    public String getUser(@RequestParam(value = "id", required = true) int offerId, Locale locale, Model model) {
        User user = null;
        String language = locale.getLanguage();
        System.out.println("id@@@@@@@@@@@@@@@@@@" + offerId);

        user = userService.getUserEager(offerId);
//        for (Object col : user.getUserOfferProductFixeds()) {
//            UserOfferProductFixed fixed = (UserOfferProductFixed) col;
//            System.out.println("user offer product fixed" + fixed.getId());
//        }
        if (user != null) {
            model.addAttribute("userHasOffer", user);
        }
        model.addAttribute("lang", locale);
        return "view_user";
    }

    @RequestMapping(value = {"/profile.htm"})
    public String getUserProfile(HttpServletRequest request, Locale locale, Model model) {
        String language = locale.getLanguage();

        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
//            for (Object col : user.getUserOfferProductFixeds()) {
//                UserOfferProductFixed fixed = (UserOfferProductFixed) col;
//                System.out.println("user offer product fixed" + fixed.getId());
//            }

            model.addAttribute("user", user);
        }
        model.addAttribute("lang", locale);

        return "profile";
    }

    @RequestMapping(value = {"/uprofile.htm"})
    public String updateUserProfile(@RequestParam(value = "fullName", required = true) String fullName,
            @RequestParam(value = "mail", required = true) String mail, @RequestParam(value = "mobile", required = true) String mobil,
            @RequestParam(value = "governerate", required = true) String governerate,
            @RequestParam(value = "file", required = false) MultipartFile file,
            HttpServletRequest request, Locale locale, Model model) {
        System.out.println("hhhhhhhhhhhhhhhhhhhhhh"+file.getName());

        String language = locale.getLanguage();
        locale = LocaleContextHolder.getLocale();
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            user.setFullName(fullName);
                user.setGovernerate(governerate);
                try {
                    user.setImage(file.getBytes());
                } catch (IOException ex) {
                    Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
                }
                user.setMail(mail);
                user.setMobile(mobil);
            int res = userService.updateUser(user);
            if (res != 0) {
                
                request.getSession().setAttribute("user", user);
            }
            model.addAttribute("user", user);
        }
        model.addAttribute("lang", locale);

        return "edit-profile";
    }

    @RequestMapping(value = {"/logout.htm"})
    public String logout(HttpServletRequest request, Locale locale, HttpServletResponse response, Model model) {
        model.addAttribute("lang", locale);
        request.getSession().removeAttribute("user");
        try {
            response.sendRedirect(request.getContextPath() + "/index.htm");
        } catch (IOException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
        model.addAttribute("lang", locale);
        return "profile";
    }
}
