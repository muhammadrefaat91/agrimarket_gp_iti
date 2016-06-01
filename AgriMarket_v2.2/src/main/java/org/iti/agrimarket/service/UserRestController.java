/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iti.agrimarket.service;

import java.io.File;
import java.io.FileOutputStream;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import org.iti.agrimarket.business.UserService;
import java.io.BufferedOutputStream;
import java.util.Date;
import net.sf.jmimemagic.Magic;
import net.sf.jmimemagic.MagicMatch;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.iti.agrimarket.constant.Constants;
import org.iti.agrimarket.model.pojo.User;
import org.iti.agrimarket.request.param.LogOutParam;
import org.iti.agrimarket.request.param.UserCheckParam;
import org.iti.agrimarket.util.requestprocessor.param.extraction.ParamExtractor;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 * @author Amr
 *
 */
@RestController
@RequestMapping(value = "/service")
public class UserRestController {


    @Autowired
    private UserService userServiceInterface;

    @Autowired
    private ParamExtractor paramExtractor;

    private Logger logger;

    /**
     * @author Amr
     * @param json
     * @return user Id
     */
    @RequestMapping(value = Constants.ADD_USER_URL, method = RequestMethod.POST)
    public Response addUser(@RequestBody String param) {

        User user = paramExtractor.getParam(param, User.class);
        
        if (!validateUser(user)) {
            // return missing parameter error 

            logger.trace(Constants.INVALID_PARAM);
            return Response.status(Constants.PARAM_ERROR).entity(Constants.INVALID_PARAM).build();

        }
        user.setLoggedIn(true);
        int res = userServiceInterface.addUser(user);

        if (user.getId() == null) {
            logger.trace(Constants.DB_ERROR);
            return Response.status(Constants.DB_ERROR).build();
        }

        //Use the generated id to form the image name
        String name = user.getId() + String.valueOf(new Date().getTime());

        if (user.getImage() != null) {
            try {
                byte[] bytes = user.getImage();
                MagicMatch match = Magic.getMagicMatch(bytes);
                final String ext = "." + match.getExtension();

                File parentDir = new File(Constants.IMAGE_PATH + Constants.USER_PATH);
                if (!parentDir.isDirectory()) {
                    parentDir.mkdirs();
                }
                BufferedOutputStream stream
                        = new BufferedOutputStream(new FileOutputStream(new File(Constants.IMAGE_PATH + Constants.USER_PATH + name)));
                stream.write(bytes);
                stream.close();
                user.setImageUrl(Constants.IMAGE_PRE_URL + Constants.USER_PATH + name + ext);
                userServiceInterface.updateUser(user);
            } catch (Exception e) {
                logger.error(e.getMessage());
                userServiceInterface.deleteUser(user); // delete the category if something goes wrong
                return Response.status(Constants.SERVER_ERROR).entity(Constants.IMAGE_UPLOAD_ERROR).build();

            }
        } else {
        }
        return Response.ok("{\"" + Constants.ID_PARAM + "\":" + user.getId() + "}", MediaType.APPLICATION_JSON).build();

    }

    /**
     * @author Amr
     * @param json
     * @return success
     */
    @RequestMapping(value = Constants.UPDATE_USER_URL, method = RequestMethod.POST)
    public Response updateUser(@RequestBody String param) {

        User user = paramExtractor.getParam(param, User.class);

        if (user.getId() == null || (userServiceInterface.getUser(user.getId())) == null) {
            // return missing parameter error 

            logger.trace(Constants.INVALID_PARAM);
            return Response.status(Constants.PARAM_ERROR).entity(Constants.INVALID_PARAM).build();

        }

        //Use the generated id to form the image name
        String name = user.getId() + String.valueOf(new Date().getTime());

        if (user.getImage() != null) {
            try {
                byte[] bytes = user.getImage();
                MagicMatch match = Magic.getMagicMatch(bytes);
                final String ext = "." + match.getExtension();

                File parentDir = new File(Constants.IMAGE_PATH + Constants.USER_PATH);
                if (!parentDir.isDirectory()) {
                    parentDir.mkdirs();
                }
                BufferedOutputStream stream
                        = new BufferedOutputStream(new FileOutputStream(new File(Constants.IMAGE_PATH + Constants.USER_PATH + name)));
                stream.write(bytes);
                stream.close();
                user.setImageUrl(Constants.IMAGE_PRE_URL + Constants.USER_PATH + name + ext);
                userServiceInterface.updateUser(user);
            } catch (Exception e) {
                logger.error(e.getMessage());
                return Response.status(Constants.SERVER_ERROR).entity(Constants.IMAGE_UPLOAD_ERROR).build();

            }
        } else {

            userServiceInterface.updateUser(user);

        }
        return Response.ok(Constants.SUCCESS_JSON, MediaType.APPLICATION_JSON).build();

    }

    //this Web Service  will be responsible for checking if user exists in case of signing in again 
    @RequestMapping(value = Constants.USER_CHECK_URL, method = RequestMethod.POST)
    public Response userCheck(@RequestBody String paramJson) {

        UserCheckParam param = paramExtractor.getParam(paramJson, UserCheckParam.class);

        if (param == null || param.getEmail() == null || param.getEmail().isEmpty() || param.getRegistrationChannel() == null) {
            // return missing parameter error 

            logger.trace(Constants.INVALID_PARAM);
            return Response.status(Constants.PARAM_ERROR).entity(Constants.INVALID_PARAM).build();
        }

        String email = param.getEmail();
        Integer registrationChannel = param.getRegistrationChannel();

        //get from the DB all the list of users with this email
        User userObj = userServiceInterface.getUserByEmail(email);

        //check if this email is correct or not
        if (userObj == null) {
            return Response.ok("{\""+Constants.USER_ID_PARAM+"\":" + -1 + "}", MediaType.APPLICATION_JSON).build();
        } //if email matched return the value of logged_in
        else {
            userObj.setLoggedIn(true);
            userServiceInterface.updateUser(userObj);
            return Response.ok("{\""+Constants.USER_ID_PARAM+"\":" + userObj.getId() + "}", MediaType.APPLICATION_JSON).build();
        }
    }

    @RequestMapping(value = Constants.LOGOUT_URL, method = RequestMethod.POST)
    public Response logOut(@RequestBody String paramJson) {

        //get the id of user that wants to logout
        LogOutParam param = paramExtractor.getParam(paramJson, LogOutParam.class);

        if (param == null || param.getId() == null) {
            // return missing parameter error 

            logger.trace(Constants.INVALID_PARAM);
            return Response.status(Constants.PARAM_ERROR).entity(Constants.INVALID_PARAM).build();
        }

        //get that user from DB
        User userReturned = userServiceInterface.getUser(param.getId());

        if (userReturned == null) {
            // return missing parameter error 

            logger.trace(Constants.INVALID_PARAM);
            return Response.status(Constants.PARAM_ERROR).entity(Constants.INVALID_PARAM).build();
        } else {
            //set the LoggedIn statues of this user by false
            userReturned.setLoggedIn(false);
            userServiceInterface.updateUser(userReturned);
            return Response.ok(Constants.SUCCESS_JSON, MediaType.APPLICATION_JSON).build();
        }

    }

    public boolean validateUser(User user) {

        if (user == null
                || user.getFullName() == null || user.getFullName().isEmpty() || user.getFullName().length() > 99
                || user.getMail() == null || user.getMail().isEmpty() || user.getMail().length() > 99) {

            return false;

        } else {

            return true;
        }

    }

    

    public UserService getUserServiceInterface() {
        return userServiceInterface;
    }

    public void setUserServiceInterface(UserService userServiceInterface) {
        this.userServiceInterface = userServiceInterface;
    }

    public UserRestController() {
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

//Questions 

// update user means that android could bring me opject without image  ???  
