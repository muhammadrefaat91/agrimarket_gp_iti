package org.iti.agrimarket.view;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.BufferedInputStream;
import javax.servlet.http.HttpServlet;
import org.iti.agrimarket.business.OfferService;
import org.iti.agrimarket.util.Validation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.FileOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.iti.agrimarket.business.UserService;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.logging.Level;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jmimemagic.Magic;
import net.sf.jmimemagic.MagicException;
import net.sf.jmimemagic.MagicMatch;
import net.sf.jmimemagic.MagicMatchNotFoundException;
import net.sf.jmimemagic.MagicParseException;
import org.apache.logging.log4j.Logger;
import org.iti.agrimarket.constant.Constants;
import org.iti.agrimarket.model.pojo.User;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 *
 * @author Amr
 */
@Controller
@SessionAttributes("user")
@RequestMapping("web")
public class SignUpController extends HttpServlet {

    private Logger logger;

    @Autowired
    UserService userService;

    @Autowired
    OfferService offerService;

    String userName = null;

    String userEmail = null;

    String imgUrl = null;


    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public ModelAndView drawSignUpForm() {
        User user = new User();
        System.out.println("hello################");
        return new ModelAndView("signup", "userForm", user);
    }

    /**
     * upload image and form data
     *
     */
    @RequestMapping(method = RequestMethod.POST, value = "/signupgplus")
    public @ResponseBody
    String signupUserFb(Model model, @RequestParam("name") String name, @RequestParam("email") String email, @RequestParam("img") String img) {

        System.out.println("save user func          google plus---------");
        System.out.println("full Name : " + name);
        System.out.println("email : " + email);
        System.out.println("img : " + img);

        //   ModelAndView modelAndView = new ModelAndView();
        User userObj = userService.getUserByEmail(email);
        if (userObj != null) {

            System.out.println("name : " + userObj.getFullName());

            model.addAttribute("user", userObj);

//            modelAndView.addObject("user",userObj);
            System.out.println("i uploaded user on the session");
//

            return "index.htm";

        } else { // store user 

            userName = name;
            userEmail = email;
            imgUrl = img;
            System.out.println("amr");

            return "signupstep.htm";

        }
    }

    /**
     * Amr
     *
     */
    @RequestMapping(method = RequestMethod.POST, value = "/signupgplusstep2")
    public String signupUserFb(Model model, @RequestParam("mobile") String mobil, @RequestParam("governerate") String governerate,
             HttpServletRequest request, HttpServletResponse response) {

        System.out.println("save user func   fb2       google plus---------");
        //    System.out.println("image : "+img);
        User userStore = new User();
        userStore.setGovernerate("Giza");

        if (userEmail == null | userName == null) {

            return "redirect:signup.htm";
        }
        userStore.setMail(userEmail);
        userStore.setFullName(userName);
        userStore.setMobile(mobil);
        userStore.setGovernerate(governerate);
        userStore.setLat(0.0);
        userStore.setLong_(0.0);
        userStore.setLoggedIn(true);
        userStore.setRatesAverage(0);
        userStore.setRegistrationChannel(0);   // web
        userStore.setImageUrl("images/amr.jpg");
        userService.addUser(userStore);
        User user = userService.getUserByEmail(userStore.getMail());

        if (imgUrl != null) {
            String fileName = user.getId() + String.valueOf(new Date().getTime());

            URL url;
            try {
                url = new URL(imgUrl);

                InputStream in = new BufferedInputStream(url.openStream());
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                byte[] buf = new byte[1024];
                int n = 0;
                while (-1 != (n = in.read(buf))) {
                    out.write(buf, 0, n);
                }
                out.close();
                in.close();
                byte[] bytes = out.toByteArray();

                MagicMatch match = Magic.getMagicMatch(bytes);
                final String ext = "." + match.getExtension();

                File parentDir = new File(Constants.IMAGE_PATH + Constants.USER_PATH);
                if (!parentDir.isDirectory()) {
                    parentDir.mkdirs();
                }
                BufferedOutputStream stream
                        = new BufferedOutputStream(new FileOutputStream(new File(Constants.IMAGE_PATH + Constants.USER_PATH + fileName)));
                stream.write(bytes);
                stream.close();
                user.setImageUrl(Constants.IMAGE_PRE_URL + Constants.USER_PATH + fileName + ext);
                userService.updateUser(user);

            } catch (MalformedURLException ex) {
                java.util.logging.Logger.getLogger(SignUpController.class.getName()).log(Level.SEVERE, null, ex);
                userService.deleteUser(user); // delete the category if something goes wrong

                return "signup";

            } catch (IOException ex) {
                java.util.logging.Logger.getLogger(SignUpController.class.getName()).log(Level.SEVERE, null, ex);

                userService.deleteUser(user); // delete the category if something goes wrong

                return "signup";
            } catch (MagicParseException ex) {
                java.util.logging.Logger.getLogger(SignUpController.class.getName()).log(Level.SEVERE, null, ex);
                userService.deleteUser(user); // delete the category if something goes wrong

                return "signup";

            } catch (MagicMatchNotFoundException ex) {
                java.util.logging.Logger.getLogger(SignUpController.class.getName()).log(Level.SEVERE, null, ex);

                userService.deleteUser(user); // delete the category if something goes wrong
                return "signup";
            } catch (MagicException ex) {
                java.util.logging.Logger.getLogger(SignUpController.class.getName()).log(Level.SEVERE, null, ex);
                userService.deleteUser(user); // delete the category if something goes wrong
                return "signup";
            }

        } else {

            user.setImageUrl(Constants.IMAGE_PRE_URL + Constants.USER_PATH + "default_user.jpg");
            userService.updateUser(user);

        }

        model.addAttribute("user", user);
        System.out.println("i Stored user in the DB");
 
        return "redirect:/index.htm";
    }

}
