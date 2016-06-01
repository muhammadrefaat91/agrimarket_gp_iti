/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iti.agrimarket.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import org.iti.agrimarket.model.pojo.Category;
import org.iti.agrimarket.model.pojo.Product;

/**
 *
 * @author Israa
 */
public class SerializerForProduct implements JsonSerializer<Product> {

    private  String language;

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    
    /**
     * This method is responsible for the serialization operation for the product objects depending on the language requested
     * it ignores the other language's fields
     * @param t
     * @param type
     * @param jsc
     * @return 
     */
    @Override
    public JsonElement serialize(Product t, Type type, JsonSerializationContext jsc) {
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        JsonObject jObj = (JsonObject) gson.toJsonTree(t);
        
        if (language.equals("ar")) {
            jObj.remove("nameEn");
        } else if (language.equals("en")) {

            jObj.remove("nameAr");
        }

        return jObj;
    }
}
