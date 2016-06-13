package org.iti.agrimarket.view;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import javax.servlet.http.HttpServlet;

import javax.validation.Valid;
import org.iti.agrimarket.business.OfferService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.FileOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.iti.agrimarket.business.UserService;
import java.io.BufferedOutputStream;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jmimemagic.Magic;
import net.sf.jmimemagic.MagicMatch;
import org.apache.logging.log4j.Logger;
import org.iti.agrimarket.business.ProductService;
import org.iti.agrimarket.business.UnitService;
import org.iti.agrimarket.constant.Constants;
import org.iti.agrimarket.model.pojo.Product;
import org.iti.agrimarket.model.pojo.Unit;
import org.iti.agrimarket.model.pojo.User;
import org.iti.agrimarket.model.pojo.UserOfferProductFixed;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 *
 * @author Amr
 */
@Controller
@RequestMapping("web")
public class AddOfferController extends HttpServlet {

    private Logger logger;

    @Autowired
    UserService userService;

    @Autowired
    ProductService productService;

    @Autowired
    UnitService unitService;

    @Autowired
    OfferService offerService;

    User user;

    @RequestMapping(value = {"/addoffer.htm"}, method = RequestMethod.GET)
    public ModelAndView drawAddOfferPage(Locale locale,Model model) {
locale = LocaleContextHolder.getLocale();
        List<Unit> units;
        units = unitService.getAllUnits();
        System.out.println(units.get(1).getNameEn());
        model.addAttribute("units", units);

        User user = new User();
        user.setId(1);

        if (!model.containsAttribute("user")) {
            //model.addAttribute("user", user);
            System.out.println("------------------------");
            System.out.println("-----!model view ----------");
            return new ModelAndView("signup");

        }
        // model.addAttribute("user",user);
        List<Product> products = productService.getAllProducts();
        System.out.println(products.get(1).getNameEn());

        model.addAttribute("products", products);
        model.addAttribute("lang", locale);
        System.out.println("hello################  new offer");
        return new ModelAndView("addoffer");
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        // Convert multipart object to byte[]
        binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
    }

    /**
     * Amr upload image and form data
     *
     */
    @RequestMapping(method = RequestMethod.POST, value = "/addoffer")
    public String addOffer(@RequestParam("description") String description,
            @RequestParam("quantity") float quantity,
            @RequestParam("quantityunit") int quantityunit,
            @RequestParam("unitprice") int unitprice,
            @RequestParam("price") float price,
            @RequestParam("mobile") String mobile,
            @RequestParam("governerate") String governerate,
            @RequestParam("product") int product,
            @ModelAttribute("user") User userFromSession,
            @RequestParam("file") MultipartFile file,
            RedirectAttributes redirectAttributes,HttpServletRequest request, HttpServletResponse response) {

        if (userFromSession == null) {
            return "login";
        } else {

            user = userFromSession;
        }

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
        userOfferProductFixed.setUser(userService.getUser(user.getId()));
        userOfferProductFixed.setUserLocation(governerate);
        userOfferProductFixed.setUserPhone(mobile);
        userOfferProductFixed.setStartDate(new Date());

        int res = offerService.addOffer(userOfferProductFixed);

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
                MagicMatch match = Magic.getMagicMatch(bytes);
                final String ext = "." + match.getExtension();

                File parentDir = new File(Constants.IMAGE_PATH + Constants.OFFER_PATH);
                if (!parentDir.isDirectory()) {
                    parentDir.mkdirs();
                }

                BufferedOutputStream stream
                        = new BufferedOutputStream(new FileOutputStream(new File(Constants.IMAGE_PATH + Constants.OFFER_PATH + fileName)));
                stream.write(bytes);

                stream.close();
                userOfferProductFixed.setImageUrl(Constants.IMAGE_PRE_URL + Constants.OFFER_PATH + fileName + ext);
                offerService.updateOffer(userOfferProductFixed);
            } catch (Exception e) {
                //                  logger.error(e.getMessage());
                offerService.deleteOffer(userOfferProductFixed.getId()); // delete the category if something goes wrong

                redirectAttributes.addFlashAttribute("message",
                        "You failed to upload  because the file was empty");
                return "redirect:index.htm";
            }

        } else {

            userOfferProductFixed.setImageUrl(Constants.IMAGE_PRE_URL + Constants.OFFER_PATH + "default_offer.jpg");

            offerService.updateOffer(userOfferProductFixed);

        }
        
        User oldUser = (User) request.getSession().getAttribute("user");
        if (oldUser != null) {
            User user = userService.getUserEager(oldUser.getId());
            request.getSession().setAttribute("user", user);
        }
        return "redirect:index.htm";
    }

}
