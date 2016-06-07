/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iti.agrimarket.model.dao;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.iti.agrimarket.model.pojo.UserOfferProductFixed;
import org.iti.agrimarket.model.pojo.UserRatesUser;
import org.iti.agrimarket.model.pojo.UserRatesUserId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

/**
 *
 * @author Israa
 */
//Israa
@Repository
public class UserRatesUserDAO implements UserRatesUserDAOInterface {

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
    public void create(UserRatesUser userRatesUser) {
        transactionTemplate.execute(new TransactionCallback() {
            @Override
            public Object doInTransaction(TransactionStatus ts) {
                try {
                    Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();

                    session.persist(userRatesUser);
                } catch (Exception e) {
                    e.printStackTrace();
                    ts.setRollbackOnly();
                }
                return null;
            }
        });
    }

    @Override
    public void edit(UserRatesUser userRatesUser) {
        transactionTemplate.execute(new TransactionCallback() {
            @Override
            public Object doInTransaction(TransactionStatus ts) {
                try {
                    Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();

                    session.update(userRatesUser);
                } catch (Exception e) {
                    e.printStackTrace();
                    ts.setRollbackOnly();
                }
                return null;
            }
        });
    }

    @Override
    public void destroy(UserRatesUserId id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<UserRatesUser> findUserRatesUserEntities() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UserRatesUser findUserRatesUser(UserRatesUserId id) {
        return (UserRatesUser) getHibernateTemplate().execute(new HibernateCallback() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                try {
                    UserRatesUser result = (UserRatesUser) session.get(UserRatesUser.class, id);
                    return result;
                } catch (Exception ex) {
                    ex.printStackTrace();

                    return null;
                }
            }
        });
    }

    @Override
    public void createOrEdit(UserRatesUser userRatesUser) {
        try {
            create(userRatesUser);
        } catch (Exception e) {
            edit(userRatesUser);
        }

    }

    /**
     * get count and sum for number of users and sum of user rates
     * @param
     */
    public List calUserWeights(int id) {
        return (List) getHibernateTemplate().execute(new HibernateCallback() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                String hql = "SELECT count(userRatesUser.id.ratedId),sum(userRatesUser.rate) FROM UserRatesUser userRatesUser where userRatesUser.id.ratedId = :id";
                Query query = session.createQuery(hql).setInteger("id", id);
                List results = query.list();
              
                return results;
            }

        });
    }

    @Override
    public UserRatesUser findUserRateUser(int raterId, int ratedId) {
        return (UserRatesUser)getHibernateTemplate().execute(new HibernateCallback() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                try {
                    UserRatesUser result = (UserRatesUser) session.createQuery("FROM UserRatesUser userRatesUser where userRatesUser.id.ratedId = :ratedId and userRatesUser.id.raterId = :raterId").setInteger("raterId",raterId).setInteger("ratedId", ratedId).uniqueResult();
                    if(result != null)
                        
                    return result;
                } catch (Exception ex) {
                    ex.printStackTrace();
                    return null;
                }
               return null; 
            }
        });
          
    }
    @Override
    public int userHasRate( int ratedId) {
        return (int)getHibernateTemplate().execute(new HibernateCallback() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                try {
                    UserRatesUser result = (UserRatesUser) session.createQuery("FROM UserRatesUser userRatesUser where userRatesUser.id.ratedId = :ratedId ").setInteger("ratedId", ratedId).uniqueResult();
                    if(result != null)
                    return 1;
                } catch (Exception ex) {
                    ex.printStackTrace();
                    return 0;
                }
               return 0; 
            }
        });
           
    }

}
