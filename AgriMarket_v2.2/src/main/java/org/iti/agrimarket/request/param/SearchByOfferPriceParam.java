/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iti.agrimarket.request.param;

/**
 *
 * @author Israa
 */
public class SearchByOfferPriceParam {
    private String criteria;
    private float price;
    private Integer perUnitId;
    private float maxPrice;

    public String getCriteria() {
        return criteria;
    }

    public void setCriteria(String criteria) {
        this.criteria = criteria;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Integer getPerUnitId() {
        return perUnitId;
    }

    public void setPerUnitId(Integer perUnitId) {
        this.perUnitId = perUnitId;
    }

    public float getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(float maxPrice) {
        this.maxPrice = maxPrice;
    }
    
    
            
    
}
