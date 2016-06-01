/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iti.agrimarket.model.dao;

import java.util.List;
import org.iti.agrimarket.model.pojo.History;

/**
 *
 * @author Israa
 */
public interface HistoryDAOInterface {
    
    public void create(History history);
    
    public void edit(History history);
    
    public void destroy(Integer id);
    
    public List<History> findHistoryEntities() ;

    public History findHistory(Integer id);
    
}
