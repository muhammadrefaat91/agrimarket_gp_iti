/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iti.agrimarket.model.dao;

import java.util.List;
import org.iti.agrimarket.model.pojo.UserPlantsPlant;

/**
 *
 * @author Israa
 */
public interface UserPlantsPlantDAOInterface {
    
    public boolean create(UserPlantsPlant userPlantsPlant);
    
    public void edit(UserPlantsPlant userPlantsPlant);
    
    public void destroy(Integer id);
    
    public List<UserPlantsPlant> findUserPlantsPlantEntities() ;

    public UserPlantsPlant findUserPlantsPlant(Integer id);
    
    public List<UserPlantsPlant> findUserPlants(Integer userId);
}
