/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iti.agrimarket.request.param;

import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author Israa
 */
public class SearchByOfferDateParam {
    private String criteria;
    private Date date;
    private Date maxDate;

    public String getCriteria() {
        return criteria;
    }

    public void setCriteria(String criteria) {
        this.criteria = criteria;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        
        java.sql.Date sDate = java.sql.Date.valueOf(date);
        this.date = new Date(sDate.getTime());
    }

    public Date getMaxDate() {
        return maxDate;
    }

    public void setMaxDate(LocalDate maxDate) {
        java.sql.Date sDate = java.sql.Date.valueOf(maxDate);
        this.maxDate = new Date(sDate.getTime());
    }

            
    
}
