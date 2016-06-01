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
public class GetUnitsOfParam {
    
    
    private String unitType;
    private String language;

    public GetUnitsOfParam() {
    }

    public GetUnitsOfParam(String unitType, String language) {
        this.unitType = unitType;
        this.language = language;
    }

    
    public String getUnitType() {
        return unitType;
    }

    public void setUnitType(String unitType) {
        this.unitType = unitType;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
    
    
    
}
