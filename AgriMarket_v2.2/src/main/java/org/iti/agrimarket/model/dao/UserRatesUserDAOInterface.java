/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iti.agrimarket.model.dao;

import java.util.List;
import org.iti.agrimarket.model.pojo.UserRatesUser;
import org.iti.agrimarket.model.pojo.UserRatesUserId;

/**
 *
 * @author Israa
 */
public interface UserRatesUserDAOInterface {
    
    public void create(UserRatesUser userRatesUser);
    
    public void createOrEdit(UserRatesUser userRatesUser);
    
    public void edit(UserRatesUser userRatesUser);
    
    public void destroy(UserRatesUserId id);
    
    public List<UserRatesUser> findUserRatesUserEntities() ;

    public UserRatesUser findUserRatesUser(UserRatesUserId id);
    
}
