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
import org.iti.agrimarket.model.pojo.User;

/**
 *
 * @author Israa
 *
 */
public class LazySerializerForUser implements JsonSerializer<User> {

    /**
     * This method is responsible for the serialization operation for the unit
     * objects depending on the language requested it ignores the other
     * language's fields
     *
     * @param t
     * @param type
     * @param jsc
     * @return
     */
    @Override
    public JsonElement serialize(User t, Type type, JsonSerializationContext jsc) {
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        JsonObject jObj = (JsonObject) gson.toJsonTree(t);

        jObj.remove("mail");
        jObj.remove("mobile");
        jObj.remove("registrationChannel");
        jObj.remove("lat");
        jObj.remove("long_");
        jObj.remove("governerate");
        jObj.remove("imageUrl");
        jObj.remove("ratesAverage");
        jObj.remove("loggedIn");
        jObj.remove("image");
        jObj.remove("userDatas");
        jObj.remove("products");
        jObj.remove("userPlantsPlants");
        jObj.remove("userOfferProductFixeds");
        jObj.remove("histories");
        jObj.remove("userRatesUsersForRatedId");
        jObj.remove("userRatesUsersForRaterId");

        return jObj;
    }
}
