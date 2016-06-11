package org.iti.agrimarket.view;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import net.sf.jmimemagic.Magic;
import net.sf.jmimemagic.MagicMatch;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.iti.agrimarket.business.ProductService;
import org.iti.agrimarket.business.UnitService;
import org.iti.agrimarket.constant.Constants;
import org.iti.agrimarket.model.pojo.Product;
import org.iti.agrimarket.model.pojo.Unit;
import org.iti.agrimarket.model.pojo.User;
import org.iti.agrimarket.model.pojo.UserOfferProductFixed;
import org.iti.agrimarket.request.param.LogOutParam;
import org.iti.agrimarket.request.param.UserCheckParam;
import org.iti.agrimarket.util.requestprocessor.param.extraction.ParamExtractor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 *
 * @author Amr
 */
@Controller

@SessionAttributes("offerId")

public class UpdateOfferController1 extends HttpServlet {

    private Logger logger;

    @Autowired
    UserService userService;

    @Autowired
    ProductService productService;

    @Autowired
    UnitService unitService;

    @Autowired
    OfferService offerService;

int offerIdVal;

    //  @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String signUp2(@ModelAttribute("userForm") @Valid User user, BindingResult br, Model model) {

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

    @RequestMapping(value = "/updateoffer", method = RequestMethod.GET)
    public ModelAndView drawAddOfferPage(Model model) {
//
//        int[] productsArr = {1, 2, 3, 4, 5};
//
//        int[] unitsArr = {1, 2, 3, 4, 5};

        //  List<Product>
        List<Unit> units;

        units = unitService.getAllUnits();

        System.out.println(units.get(1).getNameEn());

        model.addAttribute("units", units);
      

        if (!model.containsAttribute("offerId")) {
            model.addAttribute("offerId", 1);
        }

        
        UserOfferProductFixed userOfferProductFixed = offerService.findUserOfferProductFixed(1);
        
        
       // offerIdVal=offerKey;
        
        
        List<Product> products = productService.getAllProducts();
        System.out.println(products.get(1).getNameEn());

        model.addAttribute("products", products);

        System.out.println("hello################  new offer");
        return new ModelAndView("updateoffer","offer",userOfferProductFixed);
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        // Convert multipart object to byte[]
        binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
    }

    //  @RequestMapping(method = RequestMethod.POST, value = "/uploadimage")
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
    @RequestMapping(method = RequestMethod.POST, value = "/updateoffer")
    public String addOffer(@RequestParam("description") String description,
            @RequestParam("quantity") float quantity,
            @RequestParam("quantityunit") int quantityunit,
            @RequestParam("unitprice") int unitprice,
            @RequestParam("price") float price,
            @RequestParam("mobile") String mobile,
            @RequestParam("governerate") String governerate,
            @RequestParam("product") int product,
            @RequestParam("file") MultipartFile file,
            RedirectAttributes redirectAttributes) {

        System.out.println("save user func ---------");
        System.out.println("full Name :" + description);
        System.out.println("mobile:" + description);

        UserOfferProductFixed userOfferProductFixed = new UserOfferProductFixed();

        userOfferProductFixed.setDescription(description);
        userOfferProductFixed.setPrice(price);
        userOfferProductFixed.setRecommended(Boolean.FALSE);
        userOfferProductFixed.setQuantity(quantity);
        userOfferProductFixed.setProduct(productService.getProduct(product));
        userOfferProductFixed.setUnitByUnitId(unitService.getUnit(quantityunit));
        userOfferProductFixed.setUnitByPricePerUnitId(unitService.getUnit(unitprice));
        userOfferProductFixed.setUser(userService.getUser(1));
        userOfferProductFixed.setUserLocation(governerate);
        userOfferProductFixed.setUserPhone(mobile);
        userOfferProductFixed.setStartDate(new Date());

        offerService.updateOffer(userOfferProductFixed);

//
//        if (!Validation.validateUser(user)) {
//
//            return "signup";
//
//        }
//
//        int res = userService.addUser(user);
//
////          if (user.getId() == null) {
////         //   logger.trace(Constants.DB_ERROR);
////           return "signup";
////        }
////            if (name.contains("/")) {
////                redirectAttributes.addFlashAttribute("message", "Folder separators not allowed");
////                return "redirect:/";
////            }
////            if (name.contains("/")) {
////                redirectAttributes.addFlashAttribute("message", "Relative pathnames not allowed");
////                return "redirect:/";
////            }
        if (!file.isEmpty()) {
//        
//
////                    
////                    BufferedOutputStream stream = new BufferedOutputStream(
////                            new FileOutputStream(new File("C:\\AgriMarket\\images\\users\\" + name)));
////                    FileCopyUtils.copy(file.getInputStream(), stream);
////                    stream.close();
////                    redirectAttributes.addFlashAttribute("message",
////                            "You successfully uploaded " + name + "!");
////                    
////
////                    System.out.println("succccccccccccc");
            String fileName = userOfferProductFixed.getId() + String.valueOf(new Date().getTime());
//
//             
            try {

                System.out.println("fileName   :" + fileName);
                byte[] bytes = file.getBytes();

                System.out.println(new String(bytes));
                MagicMatch match = Magic.getMagicMatch(bytes);
                final String ext = "." + match.getExtension();

                File parentDir = new File(Constants.IMAGE_PATH + Constants.OFFER_PATH);
                if (!parentDir.isDirectory()) {
                    parentDir.mkdirs();
                }
                BufferedOutputStream stream
                        = new BufferedOutputStream(new FileOutputStream(new File(Constants.IMAGE_PATH + Constants.USER_PATH + fileName + ext)));
                stream.write(bytes);
                stream.close();
                userOfferProductFixed.setImageUrl(Constants.IMAGE_PRE_URL + Constants.USER_PATH + fileName + ext);
                System.out.println("image url" + userOfferProductFixed.getImageUrl());

                offerService.updateOffer(userOfferProductFixed);

            } catch (Exception e) {
                //                  logger.error(e.getMessage());
                offerService.deleteOffer(userOfferProductFixed.getId()); // delete the category if something goes wrong

                redirectAttributes.addFlashAttribute("message",
                        "You failed to upload  because the file was empty");
                return "signup";
            }

        } else {
            redirectAttributes.addFlashAttribute("message",
                    "You failed to upload  because the file was empty");
        }
        return "redirect:index.htm";
    }

    /**
     * @Author Amr delete one offer from the Fixed offers table
     * @param offerid its the id of the offer
     * @return json opject {"success":1} if success
     * @return json opject {"success":0} if deletion error
     *
     */
    @RequestMapping(method = RequestMethod.POST, value = "/removeoffer")
    public String removeOffer(@RequestParam("offerid") Integer offerId) {

        GsonBuilder builder = new GsonBuilder();

        Gson gson = builder.create();
        boolean b = false;
        System.out.println("in delete offer");

        b = offerService.deleteOffer(offerId);
        if (b) {
            return "index";
        } else {

            return "updateoffer";

        }

    }

}
