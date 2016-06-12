package org.iti.agrimarket.view;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.BufferedInputStream;
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
import org.iti.agrimarket.util.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.FileOutputStream;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.iti.agrimarket.constant.Constants;
import org.iti.agrimarket.model.pojo.User;
import org.iti.agrimarket.request.param.LogOutParam;
import org.iti.agrimarket.request.param.UserCheckParam;
import org.iti.agrimarket.util.requestprocessor.param.extraction.ParamExtractor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 *
 * @author Amr
 */
@Controller
@SessionAttributes("user")

public class SignUpController extends HttpServlet {

    private Logger logger;

    @Autowired
    UserService userService;

    @Autowired
    OfferService offerService;

    String userName = null;

    String userEmail = null;

    String imgUrl = null;

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String signUp(@ModelAttribute("userForm") @Valid User user, BindingResult br, Model model) {

        if (br.hasErrors()) {
            return "signup";
        }
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
    public ModelAndView drawSignUpForm() {
        User user = new User();
        System.out.println("hello################");
        return new ModelAndView("signup", "userForm", user);
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        // Convert multipart object to byte[]
        binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
    }

    @RequestMapping(method = RequestMethod.POST, value = "/uploadimage")
    public String handleFileUpload(@RequestParam("name") String name,
            @RequestParam("file") MultipartFile file,
            RedirectAttributes redirectAttributes) {
        if (name.contains("/")) {
            redirectAttributes.addFlashAttribute("message", "Folder separators not allowed");
            return "redirect:/";
        }
        if (name.contains("/")) {
            redirectAttributes.addFlashAttribute("message", "Relative pathnames not allowed");
            return "redirect:/";
        }

        if (!file.isEmpty()) {
            try {
                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream(new File("C:\\AgriMarket\\images\\users\\" + name + ".jpg")));
                FileCopyUtils.copy(file.getInputStream(), stream);
                stream.close();
                redirectAttributes.addFlashAttribute("message",
                        "You successfully uploaded " + name + "!");

                System.out.println("succccccccccccc");
            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("message",
                        "You failed to upload " + name + " => " + e.getMessage());
            }
        } else {
            redirectAttributes.addFlashAttribute("message",
                    "You failed to upload " + name + " because the file was empty");
        }

        return "redirect:signup.htm";
    }

    /**
     * upload image and form data
     *
     */
    @RequestMapping(method = RequestMethod.POST, value = "/saveuser")
    public String saveUser(@RequestParam("fullName") String fullName, @RequestParam("password") String password, @RequestParam("mail") String mail, @RequestParam("mobile") String mobil, @RequestParam("governerate") String governerate, @RequestParam("name") String name,
            @RequestParam("file") MultipartFile file,
            RedirectAttributes redirectAttributes) {

        System.out.println("save user func ---------");
        System.out.println("full Name :" + fullName);
        System.out.println("mobile:" + mobil);

        User user = new User();
        user.setFullName(fullName);
//        user.setPassword(password);
        user.setGovernerate(governerate);

        user.setMail(mail);
        user.setMobile(mobil);

        user.setLat(0.0);
        user.setLong_(0.0);
        user.setLoggedIn(true);
        user.setRatesAverage(0);
        user.setRegistrationChannel(0);   // web
        user.setImageUrl("images/amr.jpg");

        if (!Validation.validateUser(user)) {

            return "signup";

        }

        int res = userService.addUser(user);

        if (!file.isEmpty()) {

            String fileName = user.getId() + String.valueOf(new Date().getTime());

            try {
                byte[] bytes = file.getBytes();
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

            } catch (Exception e) {
                //                  logger.error(e.getMessage());
                userService.deleteUser(user); // delete the category if something goes wrong

                redirectAttributes.addFlashAttribute("message",
                        "You failed to upload " + name + " because the file was empty");
                return "signup";
            }

        } else {
            redirectAttributes.addFlashAttribute("message",
                    "You failed to upload " + name + " because the file was empty");
        }

        return "redirect:index.htm";
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

            return "signupl2.htm";

        }
    }

    /**
     * Amr
     *
     */
    @RequestMapping(method = RequestMethod.POST, value = "/signupgplusstep2")
    public String signupUserFb(Model model, @RequestParam("mobile") String mobil, @RequestParam("governerate") String governerate,
            @RequestParam("file") MultipartFile file, HttpServletRequest request, HttpServletResponse response) {

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

        if (!file.isEmpty()) {

            String fileName = user.getId() + String.valueOf(new Date().getTime());

            try {
                byte[] bytes = file.getBytes();
                MagicMatch match = Magic.getMagicMatch(bytes);
                final String ext = "." + match.getExtension();

                File parentDir = new File(Constants.IMAGE_PATH + Constants.USER_PATH);
                if (!parentDir.isDirectory()) {
                    parentDir.mkdirs();
                }
                BufferedOutputStream stream
                        = new BufferedOutputStream(new FileOutputStream(new File(Constants.IMAGE_PATH + Constants.USER_PATH + fileName + ext)));
                stream.write(bytes);
                stream.close();
                user.setImageUrl(Constants.IMAGE_PRE_URL + Constants.USER_PATH + fileName + ext);
                userService.updateUser(user);

            } catch (Exception e) {
                //                  logger.error(e.getMessage());
                userService.deleteUser(user); // delete the category if something goes wrong

                return "signup";
            }

        } else {

        }
//        user=userService.getUserEager(user.getId());

        model.addAttribute("user", user);
        System.out.println("i Stored user in the DB");
 
        return "redirect:/index.htm";
    }

}
