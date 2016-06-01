package org.iti.agrimarket.service;

import com.google.gson.Gson;

import org.iti.agrimarket.request.param.GetUnitParam;
import org.iti.agrimarket.util.SerializerForUnit;
import com.google.gson.GsonBuilder;
import java.util.List;
import org.hibernate.HibernateException;
import org.iti.agrimarket.business.UnitService;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.iti.agrimarket.constant.Constants;
import org.iti.agrimarket.model.pojo.Unit;
import org.iti.agrimarket.request.param.GetUnitsOfParam;
import org.iti.agrimarket.util.requestprocessor.param.extraction.ParamExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Amr
 */
@RestController
@RequestMapping(value = Constants.SERVICES_URL)
public class UnitRestController {

    private Logger logger;

    @Autowired
    private UnitService unitService;

    @Autowired
    private ParamExtractor paramExtractor;

    /**
     * @Author Amr get all the units of Certain type
     * @param unittype its the type of units
     * @param language its the language of returned list of units
     * @return json array of objects of units
     * @throws Database (Hibernate) error if there is server error
     * @throws server error if there is server error
     */
    @RequestMapping(value = Constants.GET_UNITS_OF_URL, method = RequestMethod.POST)
    public Response getUnitsOf(@RequestBody String paramJson) {

        GetUnitsOfParam param = paramExtractor.getParam(paramJson, GetUnitsOfParam.class);

        if (param == null || param.getLanguage() == null || param.getLanguage().isEmpty() || param.getUnitType() == null || param.getUnitType().isEmpty()) {
            // return missing parameter error 

            logger.trace(Constants.INVALID_PARAM);
            return Response.status(Constants.PARAM_ERROR).entity(Constants.INVALID_PARAM).build();
        }

        String language = param.getLanguage();
        String unitType = param.getUnitType();

        GsonBuilder builder = new GsonBuilder();

        SerializerForUnit serializerForUnit = new SerializerForUnit();
        serializerForUnit.setLanguage(language);
        builder.registerTypeAdapter(Unit.class, serializerForUnit);

        Gson gson = builder.create();
        List<Unit> units;
        try {
            units = unitService.getUnitsOf(unitType);
        } catch (HibernateException ex) {
            logger.error(ex.getMessage());
            return Response.status(Constants.DB_ERROR).build();

        } catch (Exception ex) {
            logger.error(ex.getMessage());
            return Response.status(Constants.SERVER_ERROR).build();
        }

        return Response.ok("{\"" + Constants.UNITS + "\":" + gson.toJson(units) + "}", MediaType.APPLICATION_JSON).build();
    }

    /**
     * is responsible to Get unit by unit id
     *
     * @author Muhammad
     * @param getUnit it's belongs to GetUnitParam class mapped to two values
     * language and unit id
     * @param user is user name for the service
     * @param key is the encrypted key
     * @return JSON object of unit class
     */
    @RequestMapping(value = Constants.GET_UNIT_URL, method = RequestMethod.POST)
    public Response getUnit(@RequestBody String paramJson) {

        //parse getUnit String to Json object
        GetUnitParam unitParam = paramExtractor.getParam(paramJson, GetUnitParam.class);
        Unit unit = null;
        // validate  propertise of unitParam object 
        if (unitParam != null && unitParam.getUnitId() != 0
                && null != unitParam.getLanguage() && !unitParam.getLanguage().trim().equals("")) {
            //eleminate name property according to langaue either Arabic or English
            Gson gson = getCustomAdaptedGson(unitParam.getLanguage());
            // Get unit object from DB by id
            unit = unitService.getUnit(unitParam.getUnitId());
            if (unit != null) {
                return Response.ok("{\""+Constants.UNITS+"\":" + gson.toJson(unit) + "}", MediaType.APPLICATION_JSON).build();
            } else {
            // return missing parameter error 

            logger.trace(Constants.INVALID_PARAM);
            return Response.status(Constants.PARAM_ERROR).entity(Constants.INVALID_PARAM).build();
            }
        } else {
            // return missing parameter error 

            logger.trace(Constants.INVALID_PARAM);
            return Response.status(Constants.PARAM_ERROR).entity(Constants.INVALID_PARAM).build();
        }
    }

    private Gson getCustomAdaptedGson(String language) {
        GsonBuilder builder = new GsonBuilder();

        SerializerForUnit serializerForUnit = new SerializerForUnit();
        serializerForUnit.setLanguage(language);
        builder.registerTypeAdapter(Unit.class, serializerForUnit);

        return builder.create();
    }

    public UnitRestController() {
        this.logger = LogManager.getLogger();

    }

    public UnitService getUnitService() {
        return unitService;
    }

    public void setUnitService(UnitService unitService) {
        this.unitService = unitService;
    }

    public ParamExtractor getParamExtractor() {
        return paramExtractor;
    }

    public void setParamExtractor(ParamExtractor paramExtractor) {
        this.paramExtractor = paramExtractor;
    }

}
