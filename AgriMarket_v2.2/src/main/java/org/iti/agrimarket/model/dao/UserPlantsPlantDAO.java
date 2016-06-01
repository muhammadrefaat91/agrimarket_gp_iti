/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iti.agrimarket.model.dao;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.iti.agrimarket.model.pojo.UserOfferProductFixed;
import org.iti.agrimarket.model.pojo.UserPlantsPlant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionTemplate;

/**
 *
 * @author Israa
 */
//Israa
@Repository
public class UserPlantsPlantDAO implements UserPlantsPlantDAOInterface {

    private TransactionTemplate transactionTemplate;
    private HibernateTemplate hibernateTemplate;

    public TransactionTemplate getTransactionTemplate() {
        return transactionTemplate;
    }

    @Autowired
    public void setTransactionTemplate(TransactionTemplate tt) {
        this.transactionTemplate = tt;
    }

    public HibernateTemplate getHibernateTemplate() {
        return hibernateTemplate;
    }

    @Autowired
    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    @Override
    public boolean create(UserPlantsPlant userPlantsPlant) {
        return (boolean) transactionTemplate.execute((TransactionStatus ts) -> {
            try {
                Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
                if(session.save(userPlantsPlant)==null)
                    return false;

            } catch (Exception e) {
                e.printStackTrace();
                ts.setRollbackOnly();
                return false;
            }
            return true;
        });
    }

    @Override
    public void edit(UserPlantsPlant userPlantsPlant) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void destroy(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<UserPlantsPlant> findUserPlantsPlantEntities() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UserPlantsPlant findUserPlantsPlant(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<UserPlantsPlant> findUserPlants(Integer userId) {
        return (List<UserPlantsPlant>) getHibernateTemplate().execute(new HibernateCallback() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                try {
                    List<UserPlantsPlant> results = session.createQuery("from UserPlantsPlant userPlant where userPlant.user.id = :id").setInteger("id", userId).list();
                    return results;
                } catch (Exception ex) {
                    ex.printStackTrace();
                    return null;
                }
            }
        });
    }

}
