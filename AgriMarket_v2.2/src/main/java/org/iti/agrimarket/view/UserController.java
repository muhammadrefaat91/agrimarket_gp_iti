/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iti.agrimarket.view;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jmimemagic.Magic;
import net.sf.jmimemagic.MagicException;
import net.sf.jmimemagic.MagicMatch;
import net.sf.jmimemagic.MagicMatchNotFoundException;
import net.sf.jmimemagic.MagicParseException;
import org.iti.agrimarket.business.UserService;
import org.iti.agrimarket.constant.Constants;
import org.iti.agrimarket.model.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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
        System.out.println("hhhhhhhhhhhhhhhhhhhhhh" + file.getName());

        String language = locale.getLanguage();
        locale = LocaleContextHolder.getLocale();
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            user.setFullName(fullName);
            user.setGovernerate(governerate);
            if (file != null) {
                try {
                    user.setImage(file.getBytes());
                    byte[] image = user.getImage();

                    MagicMatch match = null;
                    try {
                        match = Magic.getMagicMatch(image);
                    } catch (MagicParseException ex) {
                        Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (MagicMatchNotFoundException ex) {
                        Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (MagicException ex) {
                        Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                     String ext = null;
                    if(match != null)
                     ext = "." + match.getExtension();

                    File parentDir = new File(Constants.IMAGE_PATH + Constants.USER_PATH);
                    if (!parentDir.isDirectory()) {
                        parentDir.mkdirs();
                    }
                    BufferedOutputStream stream
                            = new BufferedOutputStream(new FileOutputStream(new File(Constants.IMAGE_PATH + Constants.USER_PATH + file.getOriginalFilename())));
                    stream.write(image);
                    stream.close();
                    user.setImageUrl(Constants.IMAGE_PRE_URL + Constants.USER_PATH + file.getOriginalFilename() + ext);

                } catch (IOException ex) {
                    Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
                }
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

        return "profile";
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
