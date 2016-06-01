/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iti.agrimarket.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import net.sf.jmimemagic.Magic;
import net.sf.jmimemagic.MagicMatch;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.iti.agrimarket.model.pojo.Category;
import org.iti.agrimarket.model.pojo.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.iti.agrimarket.util.SerializerForCategory;
import org.iti.agrimarket.util.SerializerForProduct;
import org.iti.agrimarket.business.CategoryService;
import org.iti.agrimarket.business.ProductService;
import org.iti.agrimarket.constant.Constants;
import org.iti.agrimarket.request.param.GetChildrenParam;
import org.iti.agrimarket.request.param.GetMainCategoriesParam;
import org.iti.agrimarket.util.requestprocessor.param.extraction.ParamExtractor;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 * @author Israa
 */
@RestController
@RequestMapping(value = Constants.SERVICES_URL)
public class CategoryRestController {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductService productService;
    @Autowired
    private ParamExtractor paramExtractor;

    private final String NO_CATEGORY_CHILDREN = "This category doesn't exist or doesn't have children";

    private final String NO_CATEGORY = "This category doesn't exist";

    private final String CATEGORIES_RETURN = "categories";

    private final String PRODUCTS_RETURN = "products";

    private Logger logger;

    /**
     *
     * @param param Encoded and Inflated Json String carrying the needed
     * parameters : category id and requested language
     * @param Request body
     * @return Encoded and inflated Json string carrying the children categories
     * or products with a status code 200 - Success 204 - Category doesn't exist
     * or has no children
     */
    @RequestMapping(value = Constants.GET_CHILDREN_URL, method = RequestMethod.POST)
    public Response getChildren(@RequestBody String param) {

        System.out.println("1234567890");
        //Parse json
        GetChildrenParam parsedParam = paramExtractor.getParam(param, GetChildrenParam.class);
        if (parsedParam == null || parsedParam.getLanguage() == null || parsedParam.getLanguage().isEmpty() || parsedParam.getCategoryId() == null) {

            logger.trace(Constants.INVALID_PARAM);
            return Response.status(Constants.PARAM_ERROR).entity(Constants.INVALID_PARAM).build();
        }

        //Extract params
        String language = parsedParam.getLanguage();
        Integer id = parsedParam.getCategoryId();

        //Get gson object using language customized serialzers
        Gson gson = getCustomAdaptedGson(language);

        //Get children categories
        List<Category> categories = categoryService.getChildrenOf(id);

        //In case of no children
        if (categories == null || categories.isEmpty()) {
            //Get products under category
            List<Product> products = productService.getChildrenOf(id);

            if (products != null && !products.isEmpty()) {

                if (Constants.DEBUG_MODE) {
                    logger.debug("Found products");
                }
                return Response.ok("{\"" + PRODUCTS_RETURN + "\":" + gson.toJson(products) + "}", MediaType.APPLICATION_JSON).build();
            } else {
                logger.error(Constants.NO_DATA);
                return Response.status(Constants.PARAM_ERROR).entity(NO_CATEGORY_CHILDREN).build();
            }
        }

        if (Constants.DEBUG_MODE) {
            logger.debug("Found categories");
        }
        return Response.ok("{\"" + CATEGORIES_RETURN + "\":" + gson.toJson(categories) + "}", MediaType.APPLICATION_JSON).build();
    }

    /**
     * Responsible for adding new category
     *
     * @param paramJsoncontains Json object of the category's data, and parent
     * category id
     * @param user Name of the user sending the request
     * @param key Encrypted key using the user's key
     * @param file The image of the product
     * @return Json {"success":1} if added successfully or status code with the
     * error
     */
    @RequestMapping(value = Constants.ADD_CATEGORY_URL, method = RequestMethod.POST)
    public Response addCategory(@RequestBody String paramJson) {

        
        //Parse the parameter
        Category category = paramExtractor.getParam(paramJson, Category.class);

        //Validate category
        if (category == null
                || ((category.getNameAr() == null || category.getNameAr().isEmpty())
                && (category.getNameEn() == null || category.getNameEn().isEmpty()))
                || ((category.getNameAr() != null && category.getNameAr().length() > 44)
                || (category.getNameEn() != null && category.getNameEn().length() > 44))) {
            logger.trace(Constants.INVALID_PARAM);
            return Response.status(Constants.PARAM_ERROR).entity(Constants.INVALID_PARAM).build();
        }

        //Check parent category
        if (category.getCategory() == null) {
            category.setCategory(category);
        } else {
            Category parent = categoryService.getCategory(category.getCategory().getId());
            if (parent == null) {
                logger.trace(NO_CATEGORY);
                return Response.status(Constants.PARAM_ERROR).entity(NO_CATEGORY).build();
            }

            category.setCategory(parent);
        }

        //Add category
        categoryService.addCategory(category);

        if (category==null || category.getId() == -1) {

            logger.trace(Constants.DB_ERROR);
            return Response.status(Constants.DB_ERROR).build();
        }

        //Use the generated id to form the image name
        String name = category.getId() + String.valueOf(new Date().getTime());

        //Save image if not empty file
        if (category.getImage() != null) {
            try {
                byte[] bytes = category.getImage();
                MagicMatch match = Magic.getMagicMatch(bytes);
                final String ext = "." + match.getExtension();

                File parentDir = new File(Constants.IMAGE_PATH + Constants.CATEGORY_PATH);
                if (!parentDir.isDirectory()) {
                    parentDir.mkdirs();
                }
                BufferedOutputStream stream
                        = new BufferedOutputStream(new FileOutputStream(new File(Constants.IMAGE_PATH + Constants.CATEGORY_PATH + name)));
                stream.write(bytes);
                stream.close();

                //Set the image url in the db
                category.setImageUrl(Constants.IMAGE_PRE_URL + Constants.CATEGORY_PATH + name + ext);

                categoryService.updateCategory(category);
            } catch (Exception e) {
                logger.error(e.getMessage());

                categoryService.deleteCategory(category.getId()); // delete the category if something goes wrong
                return Response.status(Constants.SERVER_ERROR).entity(Constants.IMAGE_UPLOAD_ERROR).build();
            }
        } else {
            categoryService.deleteCategory(category.getId()); // delete the category if something goes wrong
            return Response.status(Constants.SERVER_ERROR).entity(Constants.IMAGE_UPLOAD_ERROR).build();
        }

        return Response.ok(Constants.SUCCESS_JSON, MediaType.APPLICATION_JSON).build();
    }

    /**
     * @author Amr
     * @param paramJson
     * @return list of categories
     */
    @RequestMapping(value = Constants.GET_MAIN_CATEGORIES_URL, method = RequestMethod.POST)
    public Response getMainCategories(@RequestBody String paramJson) {

        GetMainCategoriesParam param = paramExtractor.getParam(paramJson, GetMainCategoriesParam.class);

        if (param == null || param.getLanguage() == null || param.getLanguage().isEmpty()) {
            logger.error(Constants.INVALID_PARAM);
            return Response.status(Constants.PARAM_ERROR).entity(Constants.INVALID_PARAM).build();
        }

        String language = param.getLanguage();

        Gson gson = getCustomAdaptedGson(language);

        List<Category> categories = categoryService.getChildrenOf(1);

        if (categories == null || categories.isEmpty()) {
            return Response.status(Constants.DB_ERROR).build();
        }

        return Response.ok("{\"" + CATEGORIES_RETURN + "\":" + gson.toJson(categories) + "}", MediaType.APPLICATION_JSON).build();
    }

    public ParamExtractor getParamExtractor() {
        return paramExtractor;
    }

    public void setParamExtractor(ParamExtractor paramExtractor) {
        this.paramExtractor = paramExtractor;
    }

    public CategoryRestController() {
        logger = LogManager.getLogger(CategoryRestController.class);

    }

    public Logger getLogger() {
        return logger;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    public CategoryService getCategoryService() {
        return categoryService;
    }

    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public ProductService getProductService() {
        return productService;
    }

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    /**
     * User custom language serializers in building Gson object
     *
     * @param language
     * @return Gson object using language custom serializers
     */
    private Gson getCustomAdaptedGson(String language) {
        GsonBuilder builder = new GsonBuilder();

        SerializerForCategory serializerForCategory = new SerializerForCategory();
        serializerForCategory.setLanguage(language);
        builder.registerTypeAdapter(Category.class, serializerForCategory);

        SerializerForProduct serializerForProduct = new SerializerForProduct();
        serializerForProduct.setLanguage(language);
        builder.registerTypeAdapter(Product.class, serializerForProduct);

        return builder.create();
    }

}
