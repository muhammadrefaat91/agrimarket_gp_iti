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
public class GetUserPlantsParam {
    private Integer userId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public GetUserPlantsParam() {
    }

    public GetUserPlantsParam(Integer userId) {
        this.userId = userId;
    }
    
    
}
