/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iti.agrimarket.model.dao;

import java.util.List;
import org.iti.agrimarket.model.pojo.Unit;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.support.TransactionTemplate;
import org.hibernate.HibernateException;

/**
 *
 * @author Israa
 */
@Repository
public class UnitDAO implements UnitDAOInterface {

    private TransactionTemplate transactionTemplate;
    private static HibernateTemplate hibernateTemplate;

    public TransactionTemplate getTransactionTemplate() {
        return transactionTemplate;
    }

    @Autowired
    public void setTransactionTemplate(TransactionTemplate tt) {
        this.transactionTemplate = tt;
    }

    public static HibernateTemplate getHibernateTemplate() {
        return hibernateTemplate;
    }

    @Autowired
    public   void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        UnitDAO.hibernateTemplate = hibernateTemplate;
    }

    @Override
    public void create(Unit unit) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void edit(Unit unit) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void destroy(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Unit> findUnitEntities() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Refaat using detached criteria because otherwise is deprecated
     *
     * @param id
     * @return unit object
     */
    @Override
    public Unit findUnit(Integer id) {

        return (Unit) getHibernateTemplate().execute((Session sn) -> sn.createQuery("from Unit u where u.id=:id")
                .setInteger("id", id).uniqueResult());
    }

    /**
     * @Author Amr get all the units of Certain type
     * @param type its the type of units
     * @return json array of opjects of units
     * @throws Database (Hibernate) error if there is server error
     * @throws server error if there is server error
     */
    @Override

    public List<Unit> getUnitsOf(String type) throws HibernateException, Exception {

        System.out.println("type " + type);
        return (List<Unit>) getHibernateTemplate().execute((Session session) -> session.createQuery("from Unit u where u.type=:t").setString("t", type).list());

    }

    /**
     * @Author Amr get all the units of Certain type
     * @param type its the type of units
     * @return json array of opjects of units
     * @throws Database (Hibernate) error if there is server error
     * @throws server error if there is server error
     */
    @Override

    public List<Unit> getAllUnits() {

        return (List<Unit>) getHibernateTemplate().execute((Session session) -> session.createQuery("from Unit u").list());

    }

}
