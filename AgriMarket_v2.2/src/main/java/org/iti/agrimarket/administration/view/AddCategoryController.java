package org.iti.agrimarket.administration.view;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import org.iti.agrimarket.view.*;
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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jmimemagic.Magic;
import net.sf.jmimemagic.MagicMatch;
import org.apache.logging.log4j.Logger;
import org.iti.agrimarket.business.CategoryService;
import org.iti.agrimarket.business.ProductService;
import org.iti.agrimarket.business.UnitService;
import org.iti.agrimarket.constant.Constants;
import org.iti.agrimarket.model.pojo.Category;
import org.iti.agrimarket.model.pojo.Product;
import org.iti.agrimarket.model.pojo.Unit;
import org.iti.agrimarket.model.pojo.User;
import org.iti.agrimarket.model.pojo.UserOfferProductFixed;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 *
 * @author Amr
 */
@Controller

@SessionAttributes("categories")

public class AddCategoryController extends HttpServlet {

    private Logger logger;

    @Autowired
    CategoryService categoryService;

    @RequestMapping(value = "/admin/addcategory.htm", method = RequestMethod.GET)
    public ModelAndView drawAddCategoryPage(Model model) {

        List<Category> categories;
        categories = categoryService.getAllCategories();
        System.out.println(categories.get(0).getNameEn());
        model.addAttribute("categories", categories);
        System.out.println("hello################  new product");
        return new ModelAndView("admin/addcategory");
    }

    /**
     * Amr upload image and form data
     *
     */
    @RequestMapping(method = RequestMethod.POST, value = "/admin/addcategory")
    public String addCategory(@RequestParam("nameAr") String nameAr,
            @RequestParam("nameEn") String nameEn,
            @RequestParam("parentCategoryId") int parentCategoryId,
            @RequestParam("file") MultipartFile file
    ) {

        System.out.println("save user func ---------");
        System.out.println("full Name :" + nameAr);

        Category category = new Category();
        category.setNameAr(nameAr);
        category.setNameEn(nameEn);
        Category parentCategory = categoryService.getCategory(parentCategoryId);

        category.setCategory(parentCategory);

        category.setSoundUrl("/to be continue");
        int res = categoryService.addCategory(category);

        if (!file.isEmpty()) {
            String fileName = category.getId() + String.valueOf(new Date().getTime());

            try {

                System.out.println("fileName   :" + fileName);
                byte[] bytes = file.getBytes();
                MagicMatch match = Magic.getMagicMatch(bytes);
                final String ext = "." + match.getExtension();

                File parentDir = new File(Constants.IMAGE_PATH + Constants.CATEGORY_PATH);
                if (!parentDir.isDirectory()) {
                    parentDir.mkdirs();
                }

                BufferedOutputStream stream
                        = new BufferedOutputStream(new FileOutputStream(new File(Constants.IMAGE_PATH + Constants.OFFER_PATH + fileName)));
                stream.write(bytes);

                stream.close();
                category.setImageUrl(Constants.IMAGE_PRE_URL + Constants.OFFER_PATH + fileName + ext);
                categoryService.updateCategory(category);
            } catch (Exception e) {
                //                  logger.error(e.getMessage());
                categoryService.deleteCategory(category.getId()); // delete the category if something goes wrong
                return "redirect:index.htm";
            }

        } else {

            category.setImageUrl(Constants.IMAGE_PRE_URL + Constants.OFFER_PATH + "default_category.jpg");
            categoryService.updateCategory(category);

        }

        return "redirect:/index.htm";
    }

}
