/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iti.agrimarket.business;

import java.util.List;
import org.hibernate.HibernateException;
import org.iti.agrimarket.model.dao.UnitDAOInterface;
import org.iti.agrimarket.model.dao.UserOfferProductFixedDAOInterface;
import org.iti.agrimarket.model.pojo.Unit;
import org.iti.agrimarket.model.pojo.UserOfferProductFixed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author muhammad
 */
@Service
public class UnitServiceImpl implements UnitService{
    
    private UnitDAOInterface unitDAOInterface;

    public UnitDAOInterface getUnitDAOInterface() {
        return unitDAOInterface;
    }
    @Autowired
    public void setUnitDAOInterface(UnitDAOInterface unitDAOInterface) {
        this.unitDAOInterface = unitDAOInterface;
    }

    

    @Override
    public int addUnit(UserOfferProductFixed offerProduct) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * @Author Refaat
     * @param id  unit id
     */
    @Override
    public Unit getUnit(int id) {
        return unitDAOInterface.findUnit(id);
    }

        
/**
 * @Author Amr
 * get all the units of Certain type 
 * @param type its the type of units 
 * @return json array of opjects of units 
 * @throws   Database (Hibernate) error if there is server error 
 * @throws   server error if there is server error 
 */   
    
    
    
@Override
    public List<Unit> getUnitsOf(String type) throws HibernateException,Exception {
    
      return  unitDAOInterface.getUnitsOf(type);
    }
       
    
    
     
    
    
    
}
