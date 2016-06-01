/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iti.agrimarket.service;

import java.io.File;
import java.io.FileInputStream;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.iti.agrimarket.constant.Constants;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Israa
 */
@RestController
public class ImageController {

    private final String CONTENT_IMAGES = "image/*";
    private final String CONTENT_HTML = "text/html";
    private final String CONTENT_FILE="application/octet-stream";
    private final String FILE_EXISTS = "File already exists";
    private final String FILE_NOT_EXIST = "File doesn't exist";
    private Logger logger;

    public ImageController() {
        logger = LogManager.getLogger(ImageController.class);

    }

    /**
     * Image getter service
     *
     * @param name image file's name
     * @param response to control its type
     * @return image
     */
    @RequestMapping(value = Constants.IMAGE_PRE_URL+Constants.TYPE_NAME_URL, method = RequestMethod.GET)
    @ResponseBody
    public byte[] getImage(@PathVariable(Constants.TYPE_PARAM) String type, @PathVariable(Constants.NAME_PARAM) String name, HttpServletResponse response) {
       
        response.setContentType(CONTENT_IMAGES);
        File file;
        byte arr[] = {};
        try {
            file = new File(Constants.IMAGE_PATH + type + "/" + name);
            if (file.isFile()) {

                logger.trace(FILE_EXISTS);
            } else {
                logger.trace(FILE_NOT_EXIST);
                response.setContentType(CONTENT_HTML);
                response.setStatus(Constants.PARAM_ERROR);
                return null;
            }
            arr = new byte[(int) file.length()];
            
            FileInputStream fis = new FileInputStream(file);
            fis.read(arr, 0, arr.length);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return arr;
    }
    
    
    
    /**
     * Image getter service
     *
     * @param name image file's name
     * @param response to control its type
     * @return image
     */
    @RequestMapping(value = Constants.SOUND_PRE_URL+Constants.TYPE_NAME_URL, method = RequestMethod.GET)
    @ResponseBody
    public byte[] getSound(@PathVariable(Constants.TYPE_PARAM) String type, @PathVariable(Constants.NAME_PARAM) String name, HttpServletResponse response) {
       
        response.setContentType(CONTENT_FILE);
        File file;
        byte arr[] = {};
        try {
            file = new File(Constants.SOUND_PATH + type + "/" + name);
            if (file.isFile()) {

                logger.trace(FILE_EXISTS);
            } else {
                logger.trace(FILE_NOT_EXIST + file.getAbsolutePath());
                response.setContentType(CONTENT_HTML);
                response.setStatus(Constants.PARAM_ERROR);
                return null;
            }
            arr = new byte[(int) file.length()];
            
            FileInputStream fis = new FileInputStream(file);
            fis.read(arr, 0, arr.length);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return arr;
    }
    

}
