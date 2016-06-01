/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iti.agrimarket.model.dao;

import java.util.List;
import org.iti.agrimarket.model.pojo.Unit;
import org.hibernate.HibernateException;
/**
 *
 * @author Israa
 */
public interface UnitDAOInterface {
    
    public void create(Unit unit);
    
    public void edit(Unit unit);
    
    public void destroy(Integer id);
    
    public List<Unit> findUnitEntities() ;

    public Unit findUnit(Integer id);
    
    public List<Unit> getUnitsOf(String type) throws HibernateException , Exception;

}
