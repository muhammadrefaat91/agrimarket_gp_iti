/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iti.agrimarket.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.List;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.iti.agrimarket.business.UserPlantService;
import org.iti.agrimarket.business.UserPlantServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.iti.agrimarket.business.UserRateService;
import org.iti.agrimarket.business.UserService;
import org.iti.agrimarket.constant.Constants;
import org.iti.agrimarket.model.pojo.User;
import org.iti.agrimarket.model.pojo.UserPlantsPlant;
import org.iti.agrimarket.model.pojo.UserRatesUser;
import org.iti.agrimarket.model.pojo.UserRatesUserId;
import org.iti.agrimarket.request.param.GetUserPlantsParam;
import org.iti.agrimarket.request.param.SearchByUserParam;
import org.iti.agrimarket.util.requestprocessor.param.extraction.ParamExtractor;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 * @author Israa
 */
@RestController
@RequestMapping(value = Constants.SERVICES_URL)
public class PlantRestController1 {

    private final String NO_USER = "This user doesn't exist";

    @Autowired
    private UserService userService;
    @Autowired
    private UserPlantService plantService;

    @Autowired
    private ParamExtractor paramExtractor;

    private Logger logger;

    @RequestMapping(value = Constants.ADD_PLANT_URL, method = RequestMethod.POST)
    public Response addPlant(@RequestBody String paramJson) {

        //Parse the parameter
        UserPlantsPlant plant = paramExtractor.getParam(paramJson, UserPlantsPlant.class);
        //Validate
        if (plant == null
                || (plant.getNoUnits() <= 0) || (plant.getYear() <= 1000) || (plant.getProduct() == null)
                || (plant.getProduct().getId() == null) || (plant.getSeason() == null) || (plant.getSeason().getId() == null)
                || (plant.getUnit() == null) || (plant.getUnit().getId() == null) || (plant.getUser() == null)
                || (plant.getUser().getId() == null)) {
            // return missing parameter error 

            logger.trace(Constants.INVALID_PARAM);
            return Response.status(Constants.PARAM_ERROR).entity(Constants.INVALID_PARAM).build();
        }

        //Extract data
        Integer userId = plant.getUser().getId();

        //Check that plants exist
        User user = userService.getUser(userId);
        if (user == null) {
            return Response.status(Constants.PARAM_ERROR).entity(NO_USER + " : " + userId).build();
        }

        //Add user plant
        if (!plantService.addUserPlantsPlant(plant)) {
            return Response.status(Constants.DB_ERROR).build();
        }

        return Response.ok(Constants.SUCCESS_JSON, MediaType.APPLICATION_JSON).build();
    }
    
    
     @RequestMapping(value = Constants.GET_USER_PLANTS_URL, method = RequestMethod.POST)
    public Response getUserPlants(@RequestBody String paramJson) {

         GetUserPlantsParam param = paramExtractor.getParam(paramJson, GetUserPlantsParam.class);
        if (param == null || param.getUserId() == null ) {
            logger.error(Constants.INVALID_PARAM);
            return Response.status(Constants.PARAM_ERROR).entity(Constants.INVALID_PARAM).build();
        }
        User user = userService.getUser(param.getUserId());
        if (user == null) {
            return Response.status(Constants.PARAM_ERROR).entity(NO_USER + " : " + param.getUserId()).build();
        }
        List<UserPlantsPlant> plants = plantService.getUserPlants(param.getUserId());
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

        return Response.ok(gson.toJson(plants), MediaType.APPLICATION_JSON).build();
    }
    

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public PlantRestController1() {
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

    public UserPlantService getPlantService() {
        return plantService;
    }

    public void setPlantService(UserPlantService plantService) {
        this.plantService = plantService;
    }

}
