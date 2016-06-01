package org.iti.agrimarket.view;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import javax.servlet.http.HttpServlet;
import org.apache.logging.log4j.Logger;

import javax.validation.Valid;
import org.iti.agrimarket.business.OfferService;
import org.iti.agrimarket.business.UserService;
import org.iti.agrimarket.constant.Constants;
import org.iti.agrimarket.model.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Amr
 */
@Controller
public class SignUpController extends HttpServlet {

    private Logger logger;

    @Autowired
    UserService userService;

    @Autowired
    OfferService offerService;

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String signUp(@ModelAttribute("userForm") @Valid User user, BindingResult br, Model model) {

        if (br.hasErrors()) {
            return "signup";
        }

//        System.out.println("iam in User");
//        System.out.println("-------------------------------------------------");
//
//        System.out.println("user name : " + user.getFullName());
//        System.out.println("user Govern : " + user.getGovernerate());
//        System.out.println("user mobile : " + user.getMobile());
//        System.out.println("-------------------------------------------------");
        // JOptionPane.showMessageDialog(null,"iam in user");
        user.setLat(0.0);
        user.setLong_(0.0);
        user.setLoggedIn(true);
        user.setRatesAverage(0);
        user.setRegistrationChannel(0);   // web
        user.setImageUrl("images/amr.jpg");

        System.out.println("user image -----------------" + user.getImage());

//        if (user.getId() == null || (userService.getUser(user.getId())) == null) {
//            // return missing parameter error 
//
//     //       logger.trace(Constants.INVALID_PARAM);
//
//        }
        int res = userService.addUser(user);

        //Use the generated id to form the image name
        String name = user.getId() + String.valueOf(new Date().getTime());

        if (user.getImage() != null) {
            try {
                byte[] bytes = user.getImage();
                // MagicMatch match = Magic.getMagicMatch(bytes);
                //      final String ext = "." + match.getExtension();
                final String ext = "." + "jpg";

                File parentDir = new File(Constants.IMAGE_PATH + Constants.USER_PATH);
                if (!parentDir.isDirectory()) {
                    parentDir.mkdirs();
                }
                BufferedOutputStream stream
                        = new BufferedOutputStream(new FileOutputStream(new File(Constants.IMAGE_PATH + Constants.USER_PATH + name)));
                stream.write(bytes);
                stream.close();
                user.setImageUrl(Constants.IMAGE_PRE_URL + Constants.USER_PATH + name + ext);
                userService.updateUser(user);
            } catch (Exception e) {
                //           logger.error(e.getMessage());

            }
        } else {

            userService.updateUser(user);

        }
        return "index";
    }
    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public ModelAndView account() {
        User user = new User();
        System.out.println("hello################");
        return new ModelAndView("signup", "userForm", user);
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        // Convert multipart object to byte[]
        binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
    }

}
