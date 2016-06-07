/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iti.agrimarket.business;

import java.util.List;
import org.iti.agrimarket.model.pojo.UserRatesUser;
import org.iti.agrimarket.model.pojo.UserRatesUserId;

/**
 *
 * @author muhammad
 */
public interface UserRateService {
    UserRatesUser getUserRate(UserRatesUserId id);
    public void addUserRate(UserRatesUser rate);
    public List calUserWeights(int raterId,int ratedID,int rate,String reviewText);
}
