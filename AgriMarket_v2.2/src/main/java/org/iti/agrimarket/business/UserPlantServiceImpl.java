/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iti.agrimarket.business;

import java.util.List;
import org.iti.agrimarket.model.dao.UserPlantsPlantDAO;
import org.iti.agrimarket.model.pojo.UserPlantsPlant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author muhammad
 */
@Service
public class UserPlantServiceImpl implements UserPlantService {

    private UserPlantsPlantDAO plantDAO;

    public UserPlantsPlantDAO getPlantDAO() {
        return plantDAO;
    }

    @Autowired
    public void setPlantDAO(UserPlantsPlantDAO plantDAO) {
        this.plantDAO = plantDAO;
    }

    
    
    @Override
    public boolean addUserPlantsPlant(UserPlantsPlant userPlant) {
        return plantDAO.create(userPlant);
    }

    @Override
    public List<UserPlantsPlant> getUserPlants(Integer userId) {
        return plantDAO.findUserPlants(userId);
    }

}
