/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iti.agrimarket.util.requestprocessor.param.extraction;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.iti.agrimarket.request.param.JsonParams;
import org.springframework.stereotype.Component;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.iti.agrimarket.util.requestprocessor.compression.CompressionUtil;


/**
 *
 * @author Israa
 */
@Component
public class ParamExtractor {

    private Logger logger;

    public ParamExtractor() {

        logger = LogManager.getLogger();
    }


    /**
     * Unserializes the json string then decodes , decompresses it and extracts
     * the API's param
     *
     * @param json
     * @return API param in json
     */
    public String getParam(String json) {
        if (json != null && !json.isEmpty()) {
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            JsonParams parsedParam = gson.fromJson(json, JsonParams.class);

            if (parsedParam != null) {
                String rawParam = parsedParam.getJsonObject();
                try {
                    rawParam = CompressionUtil.decompress(rawParam);
//                    rawParam = decompressor.decompress(EncryptionUtil.decodeBASE64(rawParam), 0, rawParam.length());
                } catch (Exception ex) {
                    logger.error(ex);
                }
//                byte[] decoded = decoder.decodeObject(rawParam);
//                System.out.println("length "+ decoded.length);
//                String param = decompressor.decompress(decoded, 0, decoded.length);
//                System.out.println("param : "+param);
//                System.out.println("length : "+param.length());
//                return param;
                return rawParam;
            }
        }
        return null;
    }

    /**
     * Parse Json to Object param
     *
     * @json json string
     * @classOfT Object's class
     * @return Object parameter
     */
    public <T extends Object> T getParam(String json, Class<T> classOfT) {
        String jsonParam = getParam(json);
        if (jsonParam != null) {
            try {
                GsonBuilder builder = new GsonBuilder();
                Gson gson = builder.create();
                return gson.fromJson(jsonParam, classOfT);
            } catch (Exception e) {
                logger.error(getClass().getName() + e.getMessage());
            }
        }
        return null;
    }
}
