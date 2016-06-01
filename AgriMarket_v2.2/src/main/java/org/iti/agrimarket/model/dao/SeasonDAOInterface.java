/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iti.agrimarket.model.dao;

import java.util.List;
import org.iti.agrimarket.model.pojo.Season;

/**
 *
 * @author Israa
 */
public interface SeasonDAOInterface {
    
    public void create(Season season);
    
    public void edit(Season season);
    
    public void destroy(Integer id);
    
    public List<Season> findSeasonEntities() ;

    public Season findSeason(Integer id);
    
}
