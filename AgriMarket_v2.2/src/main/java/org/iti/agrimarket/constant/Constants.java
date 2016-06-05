/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iti.agrimarket.constant;

/**
 *
 * @author Israa
 */
public class Constants {

    public static final boolean DEBUG_MODE = true;
    public static final int PAGE_SIZE = 10;

    public static final String PARAM = "param";
    public static final String USER_PARAM = "user";
    public static final String USER_ID_PARAM = "userId";
    public static final String ID_PARAM = "id";
    public static final String KEY_PARAM = "key";
    public static final String TYPE_PARAM = "type";
    public static final String NAME_PARAM = "name";
    public static final String OFFERS = "offers";
    public static final String UNITS = "units";
    public static final String IMAGE_PARAM = "image";
//    public static final String IMAGE_EXT = ".png";

   
    public static final String ROOT_PATH = "C:/AgriMarket/";
//    public static final String ROOT_PATH = "/home/zipline/agrimarket/";
//    public static final String ROOT_PATH = "/home/muhammad/agrimarket/" ;
    public static final String IMAGE_PATH = ROOT_PATH+"images/";

    public static final String SOUND_PATH = ROOT_PATH+"sound/";
    public static final String KEY_PATH = ROOT_PATH+"keys/";

    
    public static final String OFFER_PATH = "offers/";
    public static final String PRODUCT_PATH = "products/";
    public static final String CATEGORY_PATH = "categories/";
    public static final String USER_PATH = "users/";

    public static final String IMAGE_PRE_URL = "/images/";
    public static final String SOUND_PRE_URL = "/sound/";

    public static final String GET_CHILDREN_URL = "/getchildren";
    public static final String GET_PLANTS_URL = "/getplants";
    public static final String ADD_PRODUCT_URL = "/addproduct";
    public static final String ADD_CATEGORY_URL = "/addcategory";
    public static final String ADD_USER_URL = "/adduser";
    public static final String UPDATE_USER_URL = "/updateuser";
    public static final String GET_USER_URL = "/getuser";
    public static final String GET_UNIT_URL = "/getunit";
    public static final String GET_UNITS_OF_URL = "/getunitsof";
    public static final String GET_MAIN_CATEGORIES_URL = "/getmaincategories";
    public static final String ADD_RATE_URL = "/addrate";
    public static final String ADD_PLANT_URL = "/adduserplant";
    public static final String GET_USER_PLANTS_URL = "/getuserplants";
    public static final String SERVICES_URL = "/service";
    public static final String SEARCH_URL = "/search";
    public static final String OFFER_URL = "/offer";
    public static final String PRODUCT_URL = "/product";
    public static final String USER_URL = "/user";
    public static final String CATEGORY_URL = "/category";
    public static final String USER_CHECK_URL = "/usercheck";
    public static final String LOGOUT_URL = "/logout";
    public static final String TYPE_NAME_URL = "{type}/{name}";

    public static final String PRODUCT_NAME_URL = "/productname";
    public static final String PARENT_CATEGORY_URL = "/parentcategory";
    public static final String LOCATION_URL = "/location";
    public static final String PRICE_URL = "/price";
    public static final String DATE_URL = "/date";
    public static final String QUANTITY_URL = "/quantity";
    
    
    public static final String IMAGE_UPLOAD_ERROR = "Failed to upload image";
    public static final String SOUND_UPLOAD_ERROR = "Failed to upload sound";
    public static final String INVALID_PARAM = "Invalid parameter";
    public static final String NO_DATA = "No data";

    public static final String SUCCESS_JSON = "{\"success\":1}";

    public static final int SERVER_ERROR = 530;
    public static final int DB_ERROR = 501;
    public static final int PARAM_ERROR = 400;

    /**
     *
     *
     */
    public static final String REMOVE_OFFER_URL = "/removeoffer";
    
        //offer Rest class
    public static final String POST_OFFER_NAME = "Image-offer";
    public static final String ADD_OFFER_URL = "/service/addoffer";
    public static final String GET_OFFERS_URL = "/service/getoffers";
    public static final String GET_LIMITED_OFFERS_URL = "/service/getofferslimited";
    public static final String GET_USER_OFFERS_URL = "/service/getuseroffers";

}
