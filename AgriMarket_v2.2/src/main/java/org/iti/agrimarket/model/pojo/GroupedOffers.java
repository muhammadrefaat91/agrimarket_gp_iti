/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iti.agrimarket.model.pojo;

import com.google.gson.annotations.Expose;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Israa
 */
public class GroupedOffers {
    @Expose
    HashMap<Integer, List<UserOfferProductFixed>> offersGroups;

    public GroupedOffers() {
        offersGroups=new HashMap<>();
    }

    public HashMap<Integer, List<UserOfferProductFixed>> getOffersGroups() {
        return offersGroups;
    }

    public void setOffersGroups(HashMap<Integer, List<UserOfferProductFixed>> offersGroups) {
        this.offersGroups = offersGroups;
    }
    
}
