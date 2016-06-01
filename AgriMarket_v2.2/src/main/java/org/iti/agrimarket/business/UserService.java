/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iti.agrimarket.business;

import java.util.List;
import org.iti.agrimarket.model.pojo.User;

/**
 *
 * @author muhammad
 */
public interface UserService {
     User getUser(Integer id);
    public User userLogin(String emil, String password);
    public int addUser(User user);
    public int updateUser(User user);
    public List<User> searchUser(String name);

    public User getUserByEmail(String email);

    public void deleteUser(User user);
    public User getUserEager(Integer id);
}
