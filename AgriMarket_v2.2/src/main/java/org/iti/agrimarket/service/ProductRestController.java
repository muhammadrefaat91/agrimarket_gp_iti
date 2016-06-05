/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iti.agrimarket.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.File;
import java.io.FileOutputStream;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.iti.agrimarket.model.pojo.Category;
import org.iti.agrimarket.model.pojo.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.io.BufferedOutputStream;
import java.util.Date;
import java.util.List;
import net.sf.jmimemagic.Magic;
import net.sf.jmimemagic.MagicMatch;
import org.iti.agrimarket.business.CategoryService;
import org.iti.agrimarket.business.ProductService;
import org.iti.agrimarket.constant.Constants;
import org.iti.agrimarket.request.param.GetChildrenParam;
import org.iti.agrimarket.request.param.GetPlantsParam;
import org.iti.agrimarket.util.SerializerForCategory;
import org.iti.agrimarket.util.SerializerForProduct;
import org.iti.agrimarket.util.requestprocessor.param.extraction.ParamExtractor;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 * @author Israa
 */
@RestController
@RequestMapping(value = Constants.SERVICES_URL)
public class ProductRestController {

    private Logger logger;

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categorySrevice;
    @Autowired
    private ParamExtractor paramExtractor;
    @Autowired
    private CategoryService categoryService;
    private static final Integer ID_OF_CROPS = 7;

    /**
     * Responsible for adding new product
     *
     * @param paramJsoncontains Json object of the product's data, and parent
     * category id
     * @param user Name of the user sending the request
     * @param key Encrypted key using the user's key
     * @param file The image of the product
     * @return Json {"success":1} if added successfully or status code with the
     * error
     */
    @RequestMapping(value = Constants.ADD_PRODUCT_URL, method = RequestMethod.POST)
    public Response addProduct(@RequestBody String paramJson) {

        //Parse the parameter
        Product product = paramExtractor.getParam(paramJson, Product.class);

        //Validate product
        if (product == null
                || ((product.getNameAr() == null || product.getNameAr().isEmpty())
                && (product.getNameEn() == null || product.getNameEn().isEmpty()))
                || ((product.getNameAr() != null && product.getNameAr().length() > 44)
                || (product.getNameEn() != null && product.getNameEn().length() > 44))) {
            logger.error(Constants.INVALID_PARAM);
            return Response.status(Constants.PARAM_ERROR).entity(Constants.INVALID_PARAM).build();
        }

        Integer parentCategory = product.getCategory().getId();

        //Check that parent category exists
        Category category = categorySrevice.getCategory(parentCategory);
        if (category == null) {
            logger.error(Constants.INVALID_PARAM);
            return Response.status(Constants.PARAM_ERROR).entity(Constants.INVALID_PARAM).build();
        }
        product.setCategory(category);

        //Add product
        productService.addProduct(product);

        //Use the generated id to form the image name
        String name = product.getId() + String.valueOf(new Date().getTime());

        //Save image
        if (product.getImage() != null) {
            try {
                byte[] bytes = product.getImage();
                MagicMatch match = Magic.getMagicMatch(bytes);
                final String ext = "." + match.getExtension();

                File parentDir = new File(Constants.IMAGE_PATH + Constants.PRODUCT_PATH);
                if (!parentDir.isDirectory()) {
                    parentDir.mkdirs();
                }
                BufferedOutputStream stream
                        = new BufferedOutputStream(new FileOutputStream(new File(Constants.IMAGE_PATH + Constants.PRODUCT_PATH + name)));
                stream.write(bytes);
                stream.close();

                //Set the image url in the db
                product.setImageUrl(Constants.IMAGE_PRE_URL + Constants.PRODUCT_PATH + name + ext);
                productService.updateProduct(product);
            } catch (Exception e) {
                logger.error(e.getMessage());

                productService.deleteProduct(product.getId()); // delete the offer if something goes wrong
                return Response.status(Constants.SERVER_ERROR).entity(Constants.IMAGE_UPLOAD_ERROR).build();
            }
        } else {
            logger.error(Constants.IMAGE_UPLOAD_ERROR);

            productService.deleteProduct(product.getId()); //also here should delete the offer  
            return Response.status(Constants.SERVER_ERROR).entity(Constants.IMAGE_UPLOAD_ERROR).build();
        }
        return Response.ok(Constants.SUCCESS_JSON, MediaType.APPLICATION_JSON).build();
    }

    @RequestMapping(value = Constants.GET_PLANTS_URL, method = RequestMethod.POST)
    public Response getPlants(@RequestBody String param) {

        //Parse json
        GetPlantsParam parsedParam = paramExtractor.getParam(param, GetPlantsParam.class);
        if (parsedParam == null || parsedParam.getLanguage() == null || parsedParam.getLanguage().isEmpty()) {

            logger.trace(Constants.INVALID_PARAM);
            return Response.status(Constants.PARAM_ERROR).entity(Constants.INVALID_PARAM).build();
        }

        //Extract params
        String language = parsedParam.getLanguage();

        //Get gson object using language customized serialzers
        Gson gson = getCustomAdaptedGson(language);

        List<Product> products = null;
        //Get children categories
        List<Category> categories = categoryService.getChildrenOf(ID_OF_CROPS);
        for (int i = 0; i < categories.size(); i++) {
            Category get = categories.get(i);
            List<Category> children = categoryService.getChildrenOf(get.getId());
            if (children != null && children.size() > 0) {
                categories.addAll(children);
            } else {
                List<Product> temp = productService.getChildrenOf(get.getId());
                if (temp != null && temp.size() > 0) {
                    if (products == null) {
                        products = temp;
                    } else {
                        products.addAll(temp);
                    }
                }
            }

        }

        if (products == null) {
            return Response.status(Constants.DB_ERROR).build();
        }
        return Response.ok(gson.toJson(products), MediaType.APPLICATION_JSON).build();
    }

    public ProductRestController() {
        logger = LogManager.getLogger();
    }

    public CategoryService getCategorySrevice() {
        return categorySrevice;
    }

    public void setCategorySrevice(CategoryService categorySrevice) {
        this.categorySrevice = categorySrevice;
    }

    public ProductService getProductService() {
        return productService;
    }

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    public ParamExtractor getParamExtractor() {
        return paramExtractor;
    }

    public void setParamExtractor(ParamExtractor paramExtractor) {
        this.paramExtractor = paramExtractor;
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

    /**
     * User custom language serializers in building Gson object
     *
     * @param language
     * @return Gson object using language custom serializers
     */
    private Gson getCustomAdaptedGson(String language) {
        GsonBuilder builder = new GsonBuilder();

        SerializerForProduct serializerForProduct = new SerializerForProduct();
        serializerForProduct.setLanguage(language);
        builder.registerTypeAdapter(Product.class, serializerForProduct);

        return builder.create();
    }
}
