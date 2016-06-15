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
import org.iti.agrimarket.request.param.GetLimitedOffersParam;
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

    public  HibernateTemplate getHibernateTemplate() {
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
                    for (int i = 0; i < results.size(); i++) {
                        UserOfferProductFixed get = results.get(i);
                        Hibernate.initialize(get.getProduct());
                    }
                    return results;
                } catch (Exception ex) {
                    ex.printStackTrace();

                    return null;
                }
            }
        });
    }

    @Override
    public List<UserOfferProductFixed> findLimitedOffers(Product product, int pageNo, int sortType) {

        return (List<UserOfferProductFixed>) getHibernateTemplate().execute(new HibernateCallback() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                try {
                    String sortField = null;
                    switch (sortType) {
                        case GetLimitedOffersParam.DATE_SORT:
                            sortField = "userOffer.startDate desc";
                            break;
                        case GetLimitedOffersParam.PRICE_SORT:
                            sortField = "userOffer.price";
                            break;
                        case GetLimitedOffersParam.QUANTITY_SORT:
                            sortField = "userOffer.quantity";
                            break;
                    }
                    String queryString = "from UserOfferProductFixed userOffer where userOffer.product = :product";

                    queryString += " ORDER BY userOffer.recommended desc";
                    if (sortField != null) {
                        queryString += " , " + sortField;
                    }
                    Query query = session.createQuery(queryString)
                            .setEntity("product", product)
                            .setFirstResult((pageNo - 1) * Constants.PAGE_SIZE)
                            .setMaxResults(Constants.PAGE_SIZE);
                    List<UserOfferProductFixed> results = query.list();
//                    if (sortType != GetLimitedOffersParam.DATE_SORT) {
                    for (int i = 0; i < results.size(); i++) {
                        UserOfferProductFixed get = results.get(i);
                        Hibernate.initialize(get.getUnitByPricePerUnitId());
                        Hibernate.initialize(get.getUnitByUnitId());
                        Hibernate.initialize(get.getUser());
                        Hibernate.initialize(get.getProduct());
                        
//                        System.out.println(get.getUnitByUnitId().getNameAr());
//                        System.out.println(get.getUnitByUnitId().getNameEn());
//                        System.out.println(get.getUnitByPricePerUnitId().getNameAr());
//                        System.out.println(get.getUnitByPricePerUnitId().getNameEn());
//                        System.out.println(get.getProduct().getNameAr());
//                        System.out.println(get.getProduct().getNameEn());
//                        System.out.println(get.getUser().getFullName());
                    }
//                    }
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
        return (UserOfferProductFixed) getHibernateTemplate().execute(new HibernateCallback() {

            @Override
            public Object doInHibernate(Session sn) throws HibernateException {
         UserOfferProductFixed offerProductFixed = (UserOfferProductFixed) sn.createQuery("from UserOfferProductFixed offer where offer.id=:id").setInteger("id", id).uniqueResult();
         Hibernate.initialize(offerProductFixed.getProduct());
         return offerProductFixed;
            }
        });
                
    }

    @Override
    public List<UserOfferProductFixed> findUserOfferProductByProduct(String productName) {
        return (List<UserOfferProductFixed>) getHibernateTemplate().execute(new HibernateCallback() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                try {
                    List<UserOfferProductFixed> results = session.createQuery("from UserOfferProductFixed userOffer where userOffer.product.nameAr LIKE :productName or userOffer.product.nameEn LIKE :productName").setString("productName", "%" + productName + "%").list();
                    for (int i = 0; i < results.size(); i++) {
                        UserOfferProductFixed get = results.get(i);
                        Hibernate.initialize(get.getProduct());
                    }
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
                    Query newCriteria = session.createQuery("from UserOfferProductFixed userOffer where date(userOffer.startDate) " + criteria + " :date "
                            + (criteria.equals("between") ? " and :maxDate " : "")).setDate("date", date);
                    if (criteria.equals("between")) {
                        newCriteria = newCriteria.setDate("maxDate", maxDate);
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
                    for (int i = 0; i < results.size(); i++) {
                        UserOfferProductFixed get = results.get(i);
                        Hibernate.initialize(get.getProduct());
                    }
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
                List<UserOfferProductFixed> results = session.createQuery("from UserOfferProductFixed userOffer where userOffer.product.nameEn LIKE :productName and userOffer.product.category.nameEn LIKE :categoryName").setString("categoryName", "%" + categoryName + "%").setString("productName", "%" + productName + "%").list();
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
                UserOfferProductFixed productFixed = (UserOfferProductFixed) sn.createQuery("from UserOfferProductFixed offer where offer.id=:id").setInteger("id", id).uniqueResult();
                Hibernate.initialize(productFixed.getUser());
                Hibernate.initialize(productFixed.getProduct());
                Hibernate.initialize(productFixed.getUnitByUnitId());
                Hibernate.initialize(productFixed.getUnitByPricePerUnitId());
                return productFixed;
            }
        });
    }

    @Override
    public List<UserOfferProductFixed> findLimitedOffersByProductName(String productName, int pageNo, int sortType) {

        return (List<UserOfferProductFixed>) getHibernateTemplate().execute(new HibernateCallback() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                try {
                    String sortField = null;
                    switch (sortType) {
                        case GetLimitedOffersParam.DATE_SORT:
                            sortField = "userOffer.startDate desc";
                            break;
                        case GetLimitedOffersParam.PRICE_SORT:
                            sortField = "userOffer.unitByPricePerUnitId.id , userOffer.price";
                            break;
                        case GetLimitedOffersParam.QUANTITY_SORT:
                            sortField = "userOffer.unitByUnitId.id , userOffer.quantity";
                            break;
                    }
                    String queryString = "from UserOfferProductFixed userOffer where userOffer.product.nameEn like :product or  userOffer.product.nameAr like :product";

                    queryString += " ORDER BY userOffer.recommended desc";
                    if (sortField != null) {
                        queryString += " , " + sortField;
                    }
                    Query query = session.createQuery(queryString)
                            .setString("product", "%" + productName + "%")
                            .setFirstResult((pageNo - 1) * Constants.PAGE_SIZE)
                            .setMaxResults(Constants.PAGE_SIZE);
                    List<UserOfferProductFixed> results = query.list();
                    for (int i = 0; i < results.size(); i++) {
                        UserOfferProductFixed get = results.get(i);
                        Hibernate.initialize(get.getUnitByPricePerUnitId());
                        Hibernate.initialize(get.getUnitByUnitId());
                        Hibernate.initialize(get.getUser());
                        Hibernate.initialize(get.getProduct());
                    }
                    session.clear();
                    return results;
                } catch (Exception ex) {
                    ex.printStackTrace();

                    return null;
                }
            }
        });
    }

    @Override
    public List<UserOfferProductFixed> findLatestOffers() {
        return (List<UserOfferProductFixed>) getHibernateTemplate().execute((Session session) -> {
            try {
                List<UserOfferProductFixed> results = session.createQuery("from UserOfferProductFixed userOffer ORDER BY userOffer.startDate desc ,userOffer.id desc").setMaxResults(Constants.PAGE_SIZE).setFirstResult(0).list();
                for (int i = 0; i < results.size(); i++) {
                    UserOfferProductFixed get = results.get(i);
                    Hibernate.initialize(get.getProduct());
                }
                return results;
            } catch (Exception ex) {
                ex.printStackTrace();
                return null;
            }
        });
    }

}
