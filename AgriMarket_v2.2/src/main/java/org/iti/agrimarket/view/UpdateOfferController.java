package org.iti.agrimarket.view;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import javax.servlet.http.HttpServlet;
import org.iti.agrimarket.business.OfferService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.iti.agrimarket.business.UserService;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.logging.Level;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Amr
 */
@Controller

@SessionAttributes("user")

@RequestMapping("web")
public class UpdateOfferController extends HttpServlet {

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

    @RequestMapping(value = {"/updateoffer.htm"}, method = RequestMethod.GET)
    public String drawAddOfferPage(@RequestParam("offerId") int offerId, Locale locale, Model model) {
        locale = LocaleContextHolder.getLocale();
        if (!model.containsAttribute("user")) {
            //model.addAttribute("user", user);
            System.out.println("------------------------");
            System.out.println("-----!model view ----------");
            // return new ModelAndView("signup");
            return "redirect:/web/signup.htm";
        }

//        if (!model.containsAttribute("offerId")) {
//
//            //return new ModelAndView("offers_page");
//            return "redirect:/offers.htm";
//
//        }
        List<Unit> units;
        units = unitService.getAllUnits();
        System.out.println(units.get(1).getNameEn());

        model.addAttribute("units", units);

        String[] countryArr = {"Ad Daqahliyah", "Al Bahr al Ahmar", "Al Buhayrah", "Al Fayyum", "Al Gharbiyah", "Al Iskandariyah", "Al Isma'iliyah", "Al Jizah", "Al Minufiyah", "Al Minya", "Al Qahirah", "Al Qalyubiyah", "Al Wadi al Jadid", "As Suways", "Ash Sharqiyah", "Aswan", "Asyut", "Bani Suwayf", "Bur Sa'id", "Dumyat", "Janub Sina", "Kafr ash Shaykh", "Matruh", "Qina", "Shamal Sina", "Suhaj"};
        String[] countryArrAr = {"القاهره", "الاسكندريه", "البحيره", "الفيوم", "الغربيه", "الاسكندريه", "الإسماعيلية", "الجيزة", "المنوفية", "المنيا", "القليوبية", "الوادي الجديد", "السويس", "الشرقية", "أسوان", "أسيوط", "بني سويف", "بورسعيد", "دمياط", "جنوب سيناء", "كفر الشيخ", "مطروح", "قنا", "شمال سيناء", "سوهاج"};

        if (offerId == 0) {

            return "profile";
        }
        model.addAttribute("states_ar", countryArrAr);
        model.addAttribute("states_us", countryArr);

        UserOfferProductFixed userOfferProductFixed = offerService.findUserOfferProductFixed(offerId);

        // offerIdVal=offerKey;
        List<Product> products = productService.getAllProducts();
        System.out.println(products.get(1).getNameEn());

        model.addAttribute("products", products);
        model.addAttribute("offer", userOfferProductFixed);
        System.out.println("hello################  new offer");
        model.addAttribute("lang", locale);
        return "updateoffer";
    }

    /**
     * upload image and form data
     *
     */
    @RequestMapping(method = RequestMethod.POST, value = {"/updateoffer.htm"})
    public String updateOffer(@RequestParam("description") String description,
            @RequestParam("quantity") float quantity,
            @RequestParam("quantityunit") int quantityunit,
            @RequestParam("unitprice") int unitprice,
            @RequestParam("price") float price,
            @RequestParam("mobile") String mobile,
            @RequestParam("governerate") String governerate,
            @RequestParam("product") int product,
            @RequestParam("file") MultipartFile file,
            @RequestParam("offerId") int offerId,
            @ModelAttribute("user") User userFromSession,
            RedirectAttributes redirectAttributes,
            Model model, HttpServletRequest request) {

        System.out.println("product id PPPPPPPPPPPPPPPPPPPPPP:" + product);

        UserOfferProductFixed userOfferProductFixed = offerService.findUserOfferProductFixed(offerId);
        if (userOfferProductFixed != null) {
            userOfferProductFixed.setDescription(description);
            userOfferProductFixed.setPrice(price);
            userOfferProductFixed.setQuantity(quantity);
            userOfferProductFixed.setProduct(productService.getProduct(product));
            userOfferProductFixed.setUnitByUnitId(unitService.getUnit(quantityunit));
            userOfferProductFixed.setUnitByPricePerUnitId(unitService.getUnit(unitprice));
            userOfferProductFixed.setUser(userFromSession);
            userOfferProductFixed.setUserLocation(governerate);
            userOfferProductFixed.setUserPhone(mobile);

            offerService.updateOffer(userOfferProductFixed);

        }
        if (!file.isEmpty()) {

            String fileName = userOfferProductFixed.getId() + String.valueOf(new Date().getTime());

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
                return "redirect:/web/updateoffer.htm";
            }

        } else {
            redirectAttributes.addFlashAttribute("message",
                    "You failed to upload  because the file was empty");
        }

//User user =  userService.getUserByEmail(userFromSession.getMail());
//           model.addAttribute("user", user);
        User oldUser = (User) request.getSession().getAttribute("user");
        if (oldUser != null) {
            User user = userService.getUserEager(oldUser.getId());
            request.getSession().setAttribute("user", user);
            model.addAttribute("user", user);
        }

        return "profile";
    }

    /**
     * @Author Amr delete one offer from the Fixed offers table
     * @param offerid its the id of the offer
     * @return json opject {"success":1} if success
     * @return json opject {"success":0} if deletion error
     *
     */
    @RequestMapping(method = RequestMethod.GET, value = "/removeoffer.htm")
    public String removeOffer(@RequestParam("offerid") Integer offerId, HttpServletRequest request, HttpServletResponse response, Model model) {

        System.out.println("in delete offer");

        offerService.deleteOffer(offerId);
        try {
            response.sendRedirect(request.getContextPath() + "/web/profile.htm");
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
        User oldUser = (User) request.getSession().getAttribute("user");
        if (oldUser != null) {
            User user = userService.getUserEager(oldUser.getId());
            request.getSession().setAttribute("user", user);
            model.addAttribute("user", user);
        }

        return "profile";

    }

}
