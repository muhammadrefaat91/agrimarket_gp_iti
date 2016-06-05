/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iti.agrimarket.model.dao;

import java.util.Date;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.iti.agrimarket.model.pojo.UserOfferProductFixed;
import org.hibernate.Session;
import org.iti.agrimarket.constant.Constants;
import org.iti.agrimarket.model.pojo.Product;
import org.iti.agrimarket.model.pojo.User;
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
public class UserOfferProductFixedDAO implements UserOfferProductFixedDAOInterface {

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

    //Refaat
    @Override
    public int create(UserOfferProductFixed userOfferProductFixed) {
        return (int) transactionTemplate.execute((TransactionStatus ts) -> {
            try {
                Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
                int count = (int) session.save(userOfferProductFixed);
                return count;

            } catch (Exception e) {
                e.printStackTrace();
                ts.setRollbackOnly();
            }
            return -1;
        });
    }

    
    
    
    
     @Override
    public void update(UserOfferProductFixed userOfferProductFixed) {
        transactionTemplate.execute((TransactionStatus ts) -> {
            try {
                Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
               session.update(userOfferProductFixed);
               

            } catch (Exception e) {
                e.printStackTrace();
                ts.setRollbackOnly();
            }
            return -1;
        });
    }

    
    @Override
    public void edit(UserOfferProductFixed userOfferProductFixed) {
        transactionTemplate.execute((TransactionStatus ts) -> {
            Session session = hibernateTemplate.getSessionFactory().getCurrentSession();

            if (userOfferProductFixed != null) {
                session.merge(userOfferProductFixed);
                return true;
            } else {
                ts.setRollbackOnly();
                return false;
            }
        });
    }
    //Refaat

    @Override
    public boolean destroy(Integer id) {
        final UserOfferProductFixed productFixed = findUserOfferProductFixed(id);
        return (boolean) transactionTemplate.execute((TransactionStatus ts) -> {
            Session session = hibernateTemplate.getSessionFactory().getCurrentSession();

            if (productFixed != null) {
                session.delete(productFixed);
                return true;
            } else {
                ts.setRollbackOnly();
                return false;
            }
        });
    }

    //Refaat
    @Override
    public List<UserOfferProductFixed> findOffers(Product product) {

        return (List<UserOfferProductFixed>) getHibernateTemplate().execute(new HibernateCallback() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                try {
                    List<UserOfferProductFixed> results = session.createQuery("from UserOfferProductFixed userOffer where userOffer.product = :product").setEntity("product", product).list();
                    return results;
                } catch (Exception ex) {
                    ex.printStackTrace();

                    return null;
                }
            }
        });
    }
    
    
    @Override
    public List<UserOfferProductFixed> findLimitedOffers(Product product, int pageNo) {

        return (List<UserOfferProductFixed>) getHibernateTemplate().execute(new HibernateCallback() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                try {
                    List<UserOfferProductFixed> results = session.createQuery("from UserOfferProductFixed userOffer where userOffer.product = :product").setEntity("product", product).setFirstResult((pageNo-1)*Constants.PAGE_SIZE).setMaxResults(Constants.PAGE_SIZE).list();
                    return results;
                } catch (Exception ex) {
                    ex.printStackTrace();

                    return null;
                }
            }
        });
    }
    
    
//Refaat

    @Override
    public List<UserOfferProductFixed> findUserOffers(User user) {

        return (List<UserOfferProductFixed>) getHibernateTemplate().execute(new HibernateCallback() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                try {
                    List<UserOfferProductFixed> results = session.createQuery("from UserOfferProductFixed userOffer where userOffer.user = :user").setEntity("user", user).list();
                    return results;
                } catch (Exception ex) {
                    ex.printStackTrace();

                    return null;
                }
            }
        });
    }

    //Refaat
    @Override
    public UserOfferProductFixed findUserOfferProductFixed(Integer id) {
        return (UserOfferProductFixed) getHibernateTemplate().execute((Session sn) -> sn.createQuery("from UserOfferProductFixed offer where offer.id=:id")
                .setInteger("id", id).uniqueResult());
    }

    @Override
    public List<UserOfferProductFixed> findUserOfferProductByProduct(String productName) {
        return (List<UserOfferProductFixed>) getHibernateTemplate().execute(new HibernateCallback() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                try {
//                "productName", "%" + productName + "%"
                    List<UserOfferProductFixed> results = session.createQuery("from UserOfferProductFixed userOffer where userOffer.product.nameAr LIKE :productName or userOffer.product.nameEn LIKE :productName").setString("productName", "%" + productName + "%").list();
                    return results;
                } catch (Exception ex) {
                    ex.printStackTrace();

                    return null;
                }
            }
        });
    }

    @Override
    public List<UserOfferProductFixed> findUserOfferProductByParent(String parentCategoryName) {
        return (List<UserOfferProductFixed>) getHibernateTemplate().execute(new HibernateCallback() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                try {
                    List<UserOfferProductFixed> results = session.createQuery("from UserOfferProductFixed userOffer where userOffer.product.category.nameAr = :categoryName or userOffer.product.category.nameEn = :categoryName").setString("categoryName", parentCategoryName).list();
                    return results;
                } catch (Exception ex) {
                    ex.printStackTrace();

                    return null;
                }
            }
        });
    }

    @Override
    public List<UserOfferProductFixed> findUserOfferProductByLocation(String location) {
        return (List<UserOfferProductFixed>) getHibernateTemplate().execute(new HibernateCallback() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                try {
                    List<UserOfferProductFixed> results = session.createQuery("from UserOfferProductFixed userOffer where userOffer.userLocation = :location").setString("location", location).list();
                    return results;
                } catch (Exception ex) {
                    ex.printStackTrace();

                    return null;
                }
            }
        });
    }

    @Override
    public List<UserOfferProductFixed> findUserOfferProductByPrice(String criteria) {
        return (List<UserOfferProductFixed>) getHibernateTemplate().execute(new HibernateCallback() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                try {
                    Logger logger = LogManager.getLogger(UserOfferProductFixed.class);
                    logger.debug(criteria);
                    List<UserOfferProductFixed> results = session.createQuery("from UserOfferProductFixed userOffer " + criteria).list();
                    return results;
                } catch (Exception ex) {
                    ex.printStackTrace();

                    return null;
                }
            }
        });
    }

    @Override
    public List<UserOfferProductFixed> findUserOfferProductByQuantity(float quantity, Integer unitId) {
        return (List<UserOfferProductFixed>) getHibernateTemplate().execute(new HibernateCallback() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                try {
                    List<UserOfferProductFixed> results = session.createQuery("from UserOfferProductFixed userOffer where userOffer.quantity = :quantity and userOffer.unitByUnitId.id = :unitId").setFloat("quantity", quantity).setInteger("unitId", unitId).list();
                    return results;
                } catch (Exception ex) {
                    ex.printStackTrace();

                    return null;
                }
            }
        });
    }

    @Override
    public List<UserOfferProductFixed> findUserOfferProductByDate(Date date, Date maxDate, String criteria) {
        return (List<UserOfferProductFixed>) getHibernateTemplate().execute(new HibernateCallback() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                try {
                    Logger logger = LogManager.getLogger(UserOfferProductFixed.class);
                    logger.debug(criteria);
                    Query newCriteria=session.createQuery("from UserOfferProductFixed userOffer where date(userOffer.startDate) "+criteria+" :date "+
                            (criteria.equals("between")?" and :maxDate ":"")).setDate("date", date);
                    if (criteria.equals("between")) {
                        newCriteria=newCriteria.setDate("maxDate", maxDate);
                    }
                    System.out.println(newCriteria.toString());

                    List<UserOfferProductFixed> results = newCriteria.list();
                    return results;
                } catch (Exception ex) {
                    ex.printStackTrace();

                    return null;
                }
            }
        });
    }
    @Override
    public List<UserOfferProductFixed> findAllOfferProducts() {
        return (List<UserOfferProductFixed>) getHibernateTemplate().execute(new HibernateCallback() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                try {
                    List<UserOfferProductFixed> results = session.createQuery("from UserOfferProductFixed ").list();
                    return results;
                } catch (Exception ex) {
                    ex.printStackTrace();

                    return null;
                }
            }
        });
    }
    @Override
    public List<UserOfferProductFixed> findUserOfferProductByProductAndCategory(String productName, String categoryName) {
        return (List<UserOfferProductFixed>) getHibernateTemplate().execute((Session session) -> {
            try {       
                List<UserOfferProductFixed> results = session.createQuery("from UserOfferProductFixed userOffer where userOffer.product.nameEn LIKE :productName and userOffer.product.category.nameEn LIKE :categoryName").setString("categoryName", "%" + categoryName + "%").setString("productName",  "%" + productName + "%").list();
                return results;
            } catch (Exception ex) {
                ex.printStackTrace();
                return null;
            }
        });
    }
    //Refaat eager while retrive offer from DB
    @Override
    public UserOfferProductFixed findUserOfferProductFixedEager(Integer id) {
        return (UserOfferProductFixed) getHibernateTemplate().execute(new HibernateCallback() {

            @Override
            public Object doInHibernate(Session sn) throws HibernateException {
               UserOfferProductFixed productFixed= (UserOfferProductFixed) sn.createQuery("from UserOfferProductFixed offer where offer.id=:id").setInteger("id", id).uniqueResult();
                Hibernate.initialize(productFixed.getUser());
                Hibernate.initialize(productFixed.getProduct());
                Hibernate.initialize(productFixed.getUnitByUnitId());
                Hibernate.initialize(productFixed.getUnitByPricePerUnitId());
                return productFixed;
            }
        });          
    }
}
