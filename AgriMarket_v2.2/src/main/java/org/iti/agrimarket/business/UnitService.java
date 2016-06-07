/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iti.agrimarket.business;

import java.util.List;
import org.hibernate.HibernateException;
import org.iti.agrimarket.model.pojo.Unit;
import org.iti.agrimarket.model.pojo.UserOfferProductFixed;

/**
 *
 * @author muhammad
 */
public interface UnitService {
    int addUnit(UserOfferProductFixed offerProduct);
    Unit getUnit(int id);
    
         public List<Unit> getUnitsOf(String type) throws HibernateException,Exception ;
  
         
         public List<Unit> getAllUnits();
}


