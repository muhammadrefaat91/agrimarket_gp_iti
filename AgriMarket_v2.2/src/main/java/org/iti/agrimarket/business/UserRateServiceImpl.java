/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iti.agrimarket.business;

import java.util.Iterator;
import java.util.List;
import org.iti.agrimarket.model.dao.UserDAO;
import org.iti.agrimarket.model.dao.UserDAOInterface;
import org.iti.agrimarket.model.dao.UserRatesUserDAO;
import org.iti.agrimarket.model.pojo.User;
import org.iti.agrimarket.model.pojo.UserRatesUser;
import org.iti.agrimarket.model.pojo.UserRatesUserId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author muhammad
 */
@Service
public class UserRateServiceImpl implements UserRateService {

    private UserRatesUserDAO rateDAO;
    private UserDAO userDAO;

    public UserDAO getUserDAO() {
        return userDAO;
    }

    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public UserRatesUserDAO getRateDAO() {
        return rateDAO;
    }

    @Autowired
    public void setRateDAO(UserRatesUserDAO rateDAO) {
        this.rateDAO = rateDAO;
    }

    @Override
    public UserRatesUser getUserRate(UserRatesUserId id) {
        return rateDAO.findUserRatesUser(id);
    }

    @Override
    public void addUserRate(UserRatesUser rate) {
        rateDAO.createOrEdit(rate);
    }

    public void calculateAverageRate(Integer rate) {

    }

    @Override
    public List calUserWeights(int raterId, int ratedID, int rate, String reviewText) {
        List userRates = null;
        UserRatesUser userRatesUser;

        //check if these users in DB
        User rater = userDAO.findUser(raterId);
        User rated = userDAO.findUser(ratedID);
        if (rater != null && rated != null) {

            int hasRate = rateDAO.userHasRate(ratedID);
            if (hasRate == 1) {//check if user has rates
                UserRatesUser result = rateDAO.findUserRateUser(raterId, ratedID);//if raterId rate to ratedID
                userRates = rateDAO.calUserWeights(ratedID);//get sum and count
//                if (result == 1) {
                    
                    if (!userRates.isEmpty()) {
                        for (Object list1 : userRates) {
                            Object total[] = (Object[]) list1;//calculate number of rater and sum of thier rates

                            Long count = (Long) total[0];
                            Long sum = (Long) total[1];

                            userRatesUser = new UserRatesUser();
                            userRatesUser.setId(new UserRatesUserId(raterId, ratedID));
                            userRatesUser.setReview(reviewText);
                            userRatesUser.setRate(rate);
                            
                            int averge=0;
                            
                            if (result != null){
                            rateDAO.edit(userRatesUser);
                            averge = (int) (((sum-result.getRate()) + rate) / (count));
                        }else{
                            rateDAO.create(userRatesUser);
                             averge = (int) ((sum + rate) / (count + 1));
                            }
                             
                             
                            rated.setRatesAverage(averge);
                            userDAO.edit(rated);
//                        }
                        }
                    }
//                }
//            else {//if there is no rating 
//                    userRatesUser = new UserRatesUser();
//                    userRatesUser.setId(new UserRatesUserId(raterId, ratedID));
//                    userRatesUser.setRate(rate);
//                    userRatesUser.setReview(reviewText);
//                    rateDAO.create(userRatesUser);
//                }
                } else {//if user doesn't have rates 
                userRatesUser = new UserRatesUser();
                userRatesUser.setId(new UserRatesUserId(raterId, ratedID));
                userRatesUser.setRate(rate);
                userRatesUser.setReview(reviewText);
                rateDAO.create(userRatesUser);
                
                rated.setRatesAverage(rate);
                userDAO.edit(rated);
            }
        }
        return userRates;
    }

}
