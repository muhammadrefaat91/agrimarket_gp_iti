/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iti.agrimarket.business;

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
public class UserRateServiceImpl implements UserRateService{

    private UserRatesUserDAO rateDAO;

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

   
}
