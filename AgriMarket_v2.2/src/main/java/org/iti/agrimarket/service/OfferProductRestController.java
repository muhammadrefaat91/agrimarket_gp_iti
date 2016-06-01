/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iti.agrimarket.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.iti.agrimarket.business.OfferService;
import org.iti.agrimarket.business.UnitService;
import org.iti.agrimarket.business.UserService;
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
import org.iti.agrimarket.model.pojo.Product;
import org.iti.agrimarket.model.pojo.Unit;
import org.iti.agrimarket.model.pojo.User;
import org.iti.agrimarket.model.pojo.UserOfferProductFixed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.iti.agrimarket.business.ProductService;
import org.iti.agrimarket.constant.Constants;
import static org.iti.agrimarket.constant.Constants.*;
import org.iti.agrimarket.request.param.GetMainCategoriesParam;
import org.iti.agrimarket.request.param.GetOffersParam;
import org.iti.agrimarket.request.param.GetUserOffersParam;
import org.iti.agrimarket.request.param.JsonParams;
import org.iti.agrimarket.util.requestprocessor.param.extraction.ParamExtractor;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 * @author muhammad
 */
@RestController
public class OfferProductRestController {

    private OfferService offerService;
    private UserService userService;
    private ProductService productServiceInterface;
    private UnitService unitService;
    @Autowired
    private ParamExtractor paramExtractor;

    private Logger logger;

    /**
     * is responsible to Get add offer
     *
     * @author Muhammad
     * @param offer it's belongs to UserOfferProduct class mapping all its
     * properties
     * @return JSON Success word if added successfully or some of error codes
     */
    @RequestMapping(value = ADD_OFFER_URL, method = RequestMethod.POST)
    public Response addOffer(@RequestBody String offer) {

        //convert JSON parameter to Object
        UserOfferProductFixed offerProductFixed = paramExtractor.getParam(offer, UserOfferProductFixed.class);

        if (offerProductFixed != null && offerProductFixed.getProduct() != null
                && offerProductFixed.getUser() != null && offerProductFixed.getUser().getId() != 0
                && offerProductFixed.getUnitByUnitId() != null
                && offerProductFixed.getUnitByUnitId().getId() != 0 && offerProductFixed.getStartDate() != null) {
            //check if product & user & unit are already exists! 
            User userObject = userService.getUser(offerProductFixed.getUser().getId());
            Product product = productServiceInterface.getProduct(offerProductFixed.getProduct().getId());
            Unit unit = unitService.getUnit(offerProductFixed.getUnitByUnitId().getId());

            if (userObject == null || product == null || unit == null) {
                logger.error(Constants.INVALID_PARAM);
                return Response.status(Constants.PARAM_ERROR).entity(Constants.INVALID_PARAM).build();
            }
//            offerProductFixed.setImageUrl("images" + file.getName());
            int check = offerService.addOffer(offerProductFixed);
            if (check == -1) // if the object doesn't added
            {
                logger.error(Constants.DB_ERROR);
                return Response.status(Constants.DB_ERROR).build();
            }
            String name = offerProductFixed.getId() + String.valueOf(new Date().getTime());
            if (offerProductFixed.getImage() != null) {
                try {
                    byte[] bytes = offerProductFixed.getImage();
                    MagicMatch match = Magic.getMagicMatch(bytes);
                    final String ext = "." + match.getExtension();

                    File parentDir = new File(Constants.IMAGE_PATH + Constants.OFFER_PATH);
                    if (!parentDir.isDirectory()) {
                        parentDir.mkdirs();
                    }

                    BufferedOutputStream stream
                            = new BufferedOutputStream(new FileOutputStream(new File(Constants.IMAGE_PATH + Constants.OFFER_PATH + name)));
                    stream.write(bytes);

                    stream.close();
                    offerProductFixed.setImageUrl(Constants.IMAGE_PRE_URL + Constants.OFFER_PATH + name + ext);
                    offerService.updateOffer(offerProductFixed);
                } catch (Exception e) {
                    logger.error(e.getMessage());
                    offerService.deleteOffer(check); // delete the offer if something goes wrong
                    return Response.status(Constants.SERVER_ERROR).entity(Constants.IMAGE_UPLOAD_ERROR).build();
                }
            } else {
                logger.error(Constants.IMAGE_UPLOAD_ERROR);
                offerService.deleteOffer(check); // delete the offer if something goes wrong
                return Response.status(Constants.SERVER_ERROR).entity(Constants.IMAGE_UPLOAD_ERROR).build();
            }
            //if request happened successfully.
            return Response.ok(Constants.SUCCESS_JSON, MediaType.APPLICATION_JSON).build();
        } else {
            // if there are invalid or missing parameters
            logger.error(Constants.INVALID_PARAM);
            return Response.status(Constants.PARAM_ERROR).entity(Constants.INVALID_PARAM).build();
        }

    }

    /**
     * is responsible to Get all offers
     *
     * @author Muhammad
     * @param param to get all offers associated with an id
     * @return JSON list of all offers on a product
     */
    @RequestMapping(value = GET_OFFERS_URL, method = RequestMethod.POST)
    public Response getOffers(@RequestBody String param) {

        GetOffersParam parsedParam = paramExtractor.getParam(param, GetOffersParam.class);

        if (parsedParam == null || parsedParam.getProductId() == 0) {
            logger.trace(Constants.INVALID_PARAM);
            return Response.status(Constants.PARAM_ERROR).entity(Constants.INVALID_PARAM).build();
        }

        int productId = parsedParam.getProductId();

        GsonBuilder builder = new GsonBuilder();
        builder.excludeFieldsWithoutExposeAnnotation();
        Gson gson = builder.create();

        Product product = null;
        if (productId != 0) {
            //check if product existed 
            product = productServiceInterface.getProduct(productId);
            List<UserOfferProductFixed> offers = offerService.getOffers(product);
            if (!offers.isEmpty()) {
                return Response.ok("{\"" + Constants.OFFERS + "\":" + gson.toJson(offers) + "}", MediaType.APPLICATION_JSON).build();
            } else {
                return Response.status(Response.Status.NO_CONTENT).build();
            }
        } else {
            logger.trace(Constants.INVALID_PARAM);
            return Response.status(Constants.PARAM_ERROR).entity(Constants.INVALID_PARAM).build();

        }

    }

    /**
     * is responsible to Get all offers associated with an user
     *
     * @author Muhammad
     * @param userId to get all offers associated with an user
     * @param user is user name for the service
     * @param key is the encrypted key
     * @return JSON list of all user's offers
     */
    @RequestMapping(value = GET_USER_OFFERS_URL, method = RequestMethod.POST)
    public Response getUserOffers(@RequestBody String param) {

        GetUserOffersParam parsedParam = paramExtractor.getParam(param, GetUserOffersParam.class);

        if (parsedParam == null || parsedParam.getUserId() == 0) {
            logger.trace(Constants.INVALID_PARAM);
            return Response.status(Constants.PARAM_ERROR).entity(Constants.INVALID_PARAM).build();
        }

        int userId = parsedParam.getUserId();
        GsonBuilder builder = new GsonBuilder();
        builder.excludeFieldsWithoutExposeAnnotation();
        Gson gson = builder.create();
        User userObj;
        if (userId != 0) {
            userObj = userService.getUser(userId);
            List<UserOfferProductFixed> offers = offerService.getUserOffers(userObj);
            if (!offers.isEmpty()) {
                return Response.ok("{\"" + Constants.OFFERS + "\":" + gson.toJson(offers) + "}", MediaType.APPLICATION_JSON).build();
            } else {
                return Response.status(Response.Status.NO_CONTENT).build();
            }
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

    }

    public OfferService getOfferService() {
        return offerService;
    }

    @Autowired
    public void setOfferService(OfferService offerService) {
        this.offerService = offerService;
    }

    @Autowired
    private String path;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public UserService getUserService() {
        return userService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public ProductService getProductServiceInterface() {
        return productServiceInterface;
    }

    @Autowired
    public void setProductServiceInterface(ProductService productServiceInterface) {
        this.productServiceInterface = productServiceInterface;
    }

    public UnitService getUnitService() {
        return unitService;
    }

    @Autowired
    public void setUnitService(UnitService unitService) {
        this.unitService = unitService;
    }

    public OfferProductRestController() {
        logger = LogManager.getLogger();
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

}
