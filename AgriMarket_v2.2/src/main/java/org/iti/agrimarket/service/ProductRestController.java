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
import net.sf.jmimemagic.Magic;
import net.sf.jmimemagic.MagicMatch;
import org.iti.agrimarket.business.CategoryService;
import org.iti.agrimarket.business.ProductService;
import org.iti.agrimarket.constant.Constants;
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

    /**
     * Parse Json to AddProductParam
     *
     * @param json
     * @return AddProductParam parameter object
     */
    private Product getAddProductParam(String json) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        Product parsedParam = null;
        try {
            parsedParam = gson.fromJson(json, Product.class);

        } catch (Exception exception) {
            return null;
        }
        return parsedParam;
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

}
