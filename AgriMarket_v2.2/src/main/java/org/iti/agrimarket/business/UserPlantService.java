/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iti.agrimarket.business;

import java.util.List;
import org.iti.agrimarket.model.pojo.UserPlantsPlant;

/**
 *
 * @author muhammad
 */
public interface UserPlantService {
    public boolean addUserPlantsPlant(UserPlantsPlant userPlant);
    
    public List<UserPlantsPlant> getUserPlants(Integer userId);
}
