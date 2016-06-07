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
import org.iti.agrimarket.business.CategoryService;
import org.iti.agrimarket.business.OfferService;
import org.iti.agrimarket.business.ProductService;
import org.iti.agrimarket.business.UnitService;
import org.iti.agrimarket.business.UserService;
import org.iti.agrimarket.constant.Constants;
import static org.iti.agrimarket.constant.Constants.GET_LIMITED_OFFERS_URL;
import static org.iti.agrimarket.constant.Constants.SEARCH_LIMITED_OFFERS_URL;
import org.iti.agrimarket.model.pojo.Category;
import org.iti.agrimarket.model.pojo.GroupedOffers;
import org.iti.agrimarket.model.pojo.Product;
import org.iti.agrimarket.model.pojo.User;
import org.iti.agrimarket.model.pojo.UserOfferProductFixed;
import org.iti.agrimarket.request.param.GetLimitedOffersParam;
import org.iti.agrimarket.request.param.SearchByCategoryParam;
import org.iti.agrimarket.request.param.SearchByOfferDateParam;
import org.iti.agrimarket.request.param.SearchByOfferLocationParam;
import org.iti.agrimarket.request.param.SearchByOfferParentCategoryParam;
import org.iti.agrimarket.request.param.SearchByOfferPriceParam;
import org.iti.agrimarket.request.param.SearchByOfferProductNameParam;
import org.iti.agrimarket.request.param.SearchByOfferQuantityParam;
import org.iti.agrimarket.request.param.SearchByProductParam;
import org.iti.agrimarket.request.param.SearchByUserParam;
import org.iti.agrimarket.request.param.SearchLimitedOffersByProductNameParam;
import org.iti.agrimarket.util.requestprocessor.param.extraction.ParamExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Israa
 */
@RestController
@RequestMapping(value = Constants.SERVICES_URL + Constants.SEARCH_URL)
public class SearchRestController {

    private final String UNIT_NOT_EXIST = "This unit doesn't exist";
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private OfferService offerService;
    @Autowired
    private UnitService unitService;
    @Autowired
    private ParamExtractor paramExtractor;

    private Logger logger;

    @RequestMapping(value = Constants.USER_URL, method = RequestMethod.POST)
    public Response searchUser(@RequestBody String paramJson) {

        SearchByUserParam param = paramExtractor.getParam(paramJson, SearchByUserParam.class);
        if (param == null || param.getUserName() == null || param.getUserName().isEmpty()) {
            logger.error(Constants.INVALID_PARAM);
            return Response.status(Constants.PARAM_ERROR).entity(Constants.INVALID_PARAM).build();
        }
        List<User> users = userService.searchUser(param.getUserName());
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

        return Response.ok(gson.toJson(users), MediaType.APPLICATION_JSON).build();
    }

    @RequestMapping(value = Constants.CATEGORY_URL, method = RequestMethod.POST)
    public Response searchCategory(@RequestBody String paramJson) {

        SearchByCategoryParam param = paramExtractor.getParam(paramJson, SearchByCategoryParam.class);
        if (param == null || param.getCategoryName() == null || param.getCategoryName().isEmpty()) {
            logger.error(Constants.INVALID_PARAM);
            return Response.status(Constants.PARAM_ERROR).entity(Constants.INVALID_PARAM).build();
        }

        List<Category> categories = categoryService.searchCategory(param.getCategoryName());

        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

        return Response.ok(gson.toJson(categories), MediaType.APPLICATION_JSON).build();
    }

    @RequestMapping(value = Constants.PRODUCT_URL, method = RequestMethod.POST)
    public Response searchProduct(@RequestBody String paramJson) {

        SearchByProductParam param = paramExtractor.getParam(paramJson, SearchByProductParam.class);
        if (param == null || param.getProductName() == null || param.getProductName().isEmpty()) {
            logger.error(Constants.INVALID_PARAM);
            return Response.status(Constants.PARAM_ERROR).entity(Constants.INVALID_PARAM).build();
        }

        List<Product> products = productService.searchProduct(param.getProductName());
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

        return Response.ok(gson.toJson(products), MediaType.APPLICATION_JSON).build();
    }

    @RequestMapping(value = Constants.OFFER_URL + Constants.PRODUCT_NAME_URL, method = RequestMethod.POST)
    public Response searchOfferByProductName(@RequestBody String paramJson) {

        SearchByOfferProductNameParam param = paramExtractor.getParam(paramJson, SearchByOfferProductNameParam.class);
        if (param == null || param.getProductName() == null || param.getProductName().isEmpty()) {
            logger.error(Constants.INVALID_PARAM);
            return Response.status(Constants.PARAM_ERROR).entity(Constants.INVALID_PARAM).build();
        }

        List<UserOfferProductFixed> offers = offerService.getOffersByProduct(param.getProductName());
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

        return Response.ok(gson.toJson(offers), MediaType.APPLICATION_JSON).build();
    }

    @RequestMapping(value = Constants.OFFER_URL + Constants.PARENT_CATEGORY_URL, method = RequestMethod.POST)
    public Response searchOfferByParentCategory(@RequestBody String paramJson) {

        SearchByOfferParentCategoryParam param = paramExtractor.getParam(paramJson, SearchByOfferParentCategoryParam.class);
        if (param == null || param.getCategoryName() == null || param.getCategoryName().isEmpty()) {
            logger.error(Constants.INVALID_PARAM);
            return Response.status(Constants.PARAM_ERROR).entity(Constants.INVALID_PARAM).build();
        }

        List<UserOfferProductFixed> offers = offerService.getOffersByParent(param.getCategoryName());
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

        return Response.ok(gson.toJson(offers), MediaType.APPLICATION_JSON).build();
    }

    @RequestMapping(value = Constants.OFFER_URL + Constants.LOCATION_URL, method = RequestMethod.POST)
    public Response searchOfferByLocation(@RequestBody String paramJson) {

        SearchByOfferLocationParam param = paramExtractor.getParam(paramJson, SearchByOfferLocationParam.class);
        if (param == null || param.getLocation() == null || param.getLocation().isEmpty()) {
            logger.error(Constants.INVALID_PARAM);
            return Response.status(Constants.PARAM_ERROR).entity(Constants.INVALID_PARAM).build();
        }

        List<UserOfferProductFixed> offers = offerService.getOffersByLocation(param.getLocation());
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        return Response.ok(gson.toJson(offers), MediaType.APPLICATION_JSON).build();
    }

    @RequestMapping(value = Constants.OFFER_URL + Constants.PRICE_URL, method = RequestMethod.POST)
    public Response searchOfferByPrice(@RequestBody String paramJson) {

        SearchByOfferPriceParam param = paramExtractor.getParam(paramJson, SearchByOfferPriceParam.class);
        if (param == null || param.getCriteria() == null || param.getPerUnitId() == null || param.getPrice() < 0 || (param.getCriteria().equals("between") && param.getMaxPrice() < param.getPrice())) {
            logger.error(Constants.INVALID_PARAM);
            return Response.status(Constants.PARAM_ERROR).entity(Constants.INVALID_PARAM).build();
        }
        if (unitService.getUnit(param.getPerUnitId()) == null) {

            return Response.status(Constants.PARAM_ERROR).entity(UNIT_NOT_EXIST).build();
        }

        List<UserOfferProductFixed> offers = offerService.getOffersByPrice(param.getPrice(), param.getMaxPrice(), param.getCriteria(), param.getPerUnitId());
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        return Response.ok(gson.toJson(offers), MediaType.APPLICATION_JSON).build();
    }

    @RequestMapping(value = Constants.OFFER_URL + Constants.DATE_URL, method = RequestMethod.POST)
    public Response searchOfferByDate(@RequestBody String paramJson) {

        SearchByOfferDateParam param = paramExtractor.getParam(paramJson, SearchByOfferDateParam.class);
        if (param == null || param.getCriteria() == null || param.getDate() == null || (param.getCriteria().equals("between") && param.getMaxDate() == null)) {
            logger.error(Constants.INVALID_PARAM);
            return Response.status(Constants.PARAM_ERROR).entity(Constants.INVALID_PARAM).build();
        }
        List<UserOfferProductFixed> offers = offerService.getOffersByDate(param.getDate(), param.getMaxDate(), param.getCriteria());
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        return Response.ok(gson.toJson(offers), MediaType.APPLICATION_JSON).build();
    }

    @RequestMapping(value = Constants.OFFER_URL + Constants.QUANTITY_URL, method = RequestMethod.POST)
    public Response searchOfferByQuantity(@RequestBody String paramJson) {

        SearchByOfferQuantityParam param = paramExtractor.getParam(paramJson, SearchByOfferQuantityParam.class);
        if (param == null || param.getUnitId() == null || param.getQuantity() <= 0) {
            logger.error(Constants.INVALID_PARAM);
            return Response.status(Constants.PARAM_ERROR).entity(Constants.INVALID_PARAM).build();
        }
        if (unitService.getUnit(param.getUnitId()) == null) {

            return Response.status(Constants.PARAM_ERROR).entity(UNIT_NOT_EXIST).build();
        }

        List<UserOfferProductFixed> offers = offerService.getOffersByQuantity(param.getQuantity(), param.getUnitId());
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

        return Response.ok(gson.toJson(offers), MediaType.APPLICATION_JSON).build();
    }

    @RequestMapping(value = SEARCH_LIMITED_OFFERS_URL, method = RequestMethod.POST)
    public Response getLimitedOffersByProductName(@RequestBody String param) {

        SearchLimitedOffersByProductNameParam parsedParam = paramExtractor.getParam(param, SearchLimitedOffersByProductNameParam.class);

        if (parsedParam == null || parsedParam.getProductName() == null || parsedParam.getProductName().isEmpty() || parsedParam.getPageNo() <= 0) {
            logger.trace(Constants.INVALID_PARAM);
            return Response.status(Constants.PARAM_ERROR).entity(Constants.INVALID_PARAM).build();
        }

        String productName = parsedParam.getProductName();

        GsonBuilder builder = new GsonBuilder();
        builder.excludeFieldsWithoutExposeAnnotation();
        Gson gson = builder.create();

        GroupedOffers offers = offerService.searchGroupedLimitedOffers(productName, parsedParam.getPageNo(), parsedParam.getSortType());
//        List<UserOfferProductFixed> offers = offerService.searchLimitedOffers(productName, parsedParam.getPageNo(), parsedParam.getSortType());
        if (offers != null) {
            return Response.ok(gson.toJson(offers), MediaType.APPLICATION_JSON).build();
        } else {
            return Response.status(Constants.DB_ERROR).build();
        }

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

    public SearchRestController() {

        logger = LogManager.getLogger();
    }

    public UnitService getUnitService() {
        return unitService;
    }

    public void setUnitService(UnitService unitService) {
        this.unitService = unitService;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public ProductService getProductService() {
        return productService;
    }

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    public CategoryService getCategoryService() {
        return categoryService;
    }

    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public OfferService getOfferService() {
        return offerService;
    }

    public void setOfferService(OfferService offerService) {
        this.offerService = offerService;
    }

}
