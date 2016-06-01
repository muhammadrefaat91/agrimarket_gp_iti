/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iti.agrimarket.service;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.iti.agrimarket.business.UserRateService;
import org.iti.agrimarket.business.UserService;
import org.iti.agrimarket.constant.Constants;
import org.iti.agrimarket.model.pojo.User;
import org.iti.agrimarket.model.pojo.UserRatesUser;
import org.iti.agrimarket.model.pojo.UserRatesUserId;
import org.iti.agrimarket.util.requestprocessor.param.extraction.ParamExtractor;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 * @author Israa
 */
@RestController
@RequestMapping(value = Constants.SERVICES_URL)
public class RateRestController {

    private final String NO_USER = "This user doesn't exist";

    @Autowired
    private UserService userService;
    @Autowired
    private UserRateService rateService;

    @Autowired
    private ParamExtractor paramExtractor;

    private Logger logger;

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
    @RequestMapping(value = Constants.ADD_RATE_URL, method = RequestMethod.POST)
    public Response addRate(@RequestBody String paramJson) {

        //Parse the parameter
        UserRatesUser rate = paramExtractor.getParam(paramJson, UserRatesUser.class);
        //Validate
        if (rate == null 
                || (rate.getReview() == null || rate.getReview().isEmpty())
                || (rate.getReview().length() > 16777213)
                || rate.getUserByRatedId() == null || rate.getUserByRaterId() == null
                || rate.getUserByRatedId().getId() == null || rate.getUserByRaterId().getId() == null
                || rate.getUserByRatedId().getId() < 0 || rate.getUserByRaterId().getId() < 0) {
            // return missing parameter error 

            logger.trace(Constants.INVALID_PARAM);
            return Response.status(Constants.PARAM_ERROR).entity(Constants.INVALID_PARAM).build();
        }

        //Extract data
        Integer raterId = rate.getUserByRaterId().getId();
        Integer ratedId = rate.getUserByRatedId().getId();

        
        //Check that users exist
        User rater = userService.getUser(raterId);
        User rated = userService.getUser(ratedId);
        if (rater == null || rated == null) {
            return Response.status(Constants.PARAM_ERROR).entity(NO_USER + " : " + (rater == null ? raterId : ratedId)).build();
        }
        rate.setId(new UserRatesUserId(raterId, ratedId));

        //Add product
        rateService.addUserRate(rate);

        return Response.ok(Constants.SUCCESS_JSON, MediaType.APPLICATION_JSON).build();
    }

 
    
    
    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public UserRateService getRateService() {
        return rateService;
    }

    public void setRateService(UserRateService rateService) {
        this.rateService = rateService;
    }

    public RateRestController() {
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



