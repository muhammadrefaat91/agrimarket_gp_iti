/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iti.agrimarket.model.dao;

import java.util.List;
import org.iti.agrimarket.model.pojo.UserData;

/**
 *
 * @author Israa
 */
public interface UserDataDAOInterface {
    
    public void create(UserData userData);
    
    public void edit(UserData userData);
    
    public void destroy(Integer id);
    
    public List<UserData> findUserDataEntities() ;

    public UserData findUserData(Integer id);
    
}
