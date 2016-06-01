/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iti.agrimarket.model.dao;

import java.util.List;
import org.iti.agrimarket.model.pojo.User;

/**
 *
 * @author Israa
 */
public interface UserDAOInterface {

    public int create(User user);

    public int edit(User user);

    public void destroy(Integer id);

    public List<User> findUserEntities();

    public User findUser(Integer id);
    
    public List<User> searchUser(String userName);

    
    
    public User findUserByEmail(String email);
    public User userLogin(String emil,String password);
    public User findUserEager(Integer id);
}
