/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iti.agrimarket.business;

import java.util.List;
import org.iti.agrimarket.model.dao.UserDAOInterface;
import org.iti.agrimarket.model.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author muhammad
 */
@Service
public class UserServiceImpl implements UserService {

    private UserDAOInterface userDAO;

    @Override
    public User getUser(Integer id) {
        return userDAO.findUser(id);
    }

    public UserDAOInterface getUserDAO() {
        return userDAO;
    }

    @Autowired
    public void setUserDAO(UserDAOInterface userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public int addUser(User user) {
        return userDAO.create(user);
    }

    @Override
    public int updateUser(User user) {

        return userDAO.edit(user);

    }

    @Override
    public List<User> searchUser(String name) {
        return userDAO.searchUser(name);
    }

    @Override
    public User getUserByEmail(String email) {
        return userDAO.findUserByEmail(email);
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteUser(User user) {
        userDAO.destroy(user.getId());
    }

    @Override
    public User userLogin(String emil, String password) {
        return userDAO.userLogin(emil, password);
    }

    @Override
    public User getUserEager(Integer id) {
        return userDAO.findUserEager(id);
    }
}
