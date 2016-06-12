/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iti.agrimarket.model.dao;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.hibernate.Hibernate;
import org.iti.agrimarket.model.pojo.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.iti.agrimarket.model.pojo.UserOfferProductFixed;
import org.iti.agrimarket.model.pojo.UserRatesUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

/**
 *
 * @author Amr
 */
@Repository
public class UserDAO implements UserDAOInterface {

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
    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        UserDAO.hibernateTemplate = hibernateTemplate;
    }

    /**
     * @author Amr
     * @param user
     * @return user Id
     */
    @Override
    public int create(User user) {
        return (int) transactionTemplate.execute(new TransactionCallback() {

            @Override
            public Object doInTransaction(TransactionStatus ts) {

                try {
                    Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
                    System.out.println("user befor persist in create : " + user.toString());

                    session.persist(user);

                    System.out.println("user after persist in create : " + user.toString());
                    return user.getId();

                } catch (HibernateException e) {
                    e.printStackTrace();
                    ts.setRollbackOnly();

                    return -2;  //means DB error 

                } catch (Exception e) {

                    e.printStackTrace();
                    ts.setRollbackOnly();

                    return -1;  //means Server error 
                }

            }
        });

    }

    /**
     * @author Amr
     * @param user
     * @return user Id
     */
    @Override
    public int edit(User user) {
        return (int) transactionTemplate.execute(new TransactionCallback() {

            @Override
            public Object doInTransaction(TransactionStatus ts) {
                try {
                    Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
                    System.out.println("user data are : " + user.toString());

                    if (!session.contains(user)) {

                        session.load(User.class, user.getId());
                    }
                    session.update(user);
                    return user.getId();
                } catch (HibernateException e) {
                    e.printStackTrace();
                    ts.setRollbackOnly();

                    return -2;  //means DB error 

                } catch (Exception e) {

                    e.printStackTrace();
                    ts.setRollbackOnly();

                    return -1;  //means Server error 
                }
            }
        });

    }

    @Override
    public void destroy(Integer id) {
        User user = findUser(id);
        transactionTemplate.execute(new TransactionCallback() {
            @Override
            public Object doInTransaction(TransactionStatus ts) {
                try {
                    Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();

                    session.delete(user);
                    return true;
                } catch (Exception e) {
                    e.printStackTrace();
                    ts.setRollbackOnly();
                }
                return false;
            }
        });
    }

    @Override
    public List<User> findUserEntities() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public User findUser(Integer id) {

        return (User) getHibernateTemplate().execute((Session sn) -> sn.createQuery("from User u where u.id=:id")
                .setInteger("id", id).uniqueResult());
    }

    @Override
    public List<User> searchUser(String userName) {
        return (List<User>) getHibernateTemplate().execute(new HibernateCallback() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                try {
                    List<User> results = session.createQuery("from User user where user.fullName = :name").setString("name", userName).list();
                    return results;
                } catch (Exception ex) {
                    ex.printStackTrace();

                    return null;
                }
            }
        });
    }

    @Override
    public User findUserByEmail(String email) {
        return (User) getHibernateTemplate().execute(new HibernateCallback() {

            @Override
            public Object doInHibernate(Session sn) throws HibernateException {
                User user = (User) sn.createQuery("from User u where u.mail=:mail")
                        .setString("mail", email).uniqueResult();
                if (user != null) {
                    Hibernate.initialize(user.getUserOfferProductFixeds());
                    Set<UserOfferProductFixed> offers = user.getUserOfferProductFixeds();
                    for (Iterator iterator = offers.iterator(); iterator.hasNext();) {
                        UserOfferProductFixed next = (UserOfferProductFixed) iterator.next();
                        Hibernate.initialize(next.getProduct());
                    }
                    Hibernate.initialize(user.getUserRatesUsersForRatedId());
                    UserRatesUser u1;
                    for (Object u : user.getUserRatesUsersForRatedId()) {
                        if (u instanceof UserRatesUser) {
                            u1 = (UserRatesUser) u;
                            Hibernate.initialize(u1.getUserByRaterId());
                        }
                    }
                    Hibernate.initialize(user.getUserRatesUsersForRaterId());
                }
                return user;

            }
        });
    }

    @Override
    public User userLogin(String email, String password) {
        return (User) hibernateTemplate.execute((Session sn) -> sn.createQuery("from User u where u.password = :password and u.mail=:mail")
                .setString("mail", email).setString("password", password).uniqueResult());

    }

    @Override
    public User findUserEager(Integer id) {

        return (User) getHibernateTemplate().execute(new HibernateCallback() {

            @Override
            public Object doInHibernate(Session sn) throws HibernateException {
                User user = (User) sn.createQuery("from User u where u.id=:id").setInteger("id", id).uniqueResult();
                Hibernate.initialize(user.getUserOfferProductFixeds());
                Set<UserOfferProductFixed> offers = user.getUserOfferProductFixeds();
                for (Iterator iterator = offers.iterator(); iterator.hasNext();) {
                    UserOfferProductFixed next = (UserOfferProductFixed) iterator.next();
                    Hibernate.initialize(next.getProduct());
                }
                Hibernate.initialize(user.getUserRatesUsersForRatedId());
                UserRatesUser u1;
                for (Object u : user.getUserRatesUsersForRatedId()) {
                    if (u instanceof UserRatesUser) {
                        u1 = (UserRatesUser) u;
                        Hibernate.initialize(u1.getUserByRaterId());
                    }
                }
                Hibernate.initialize(user.getUserRatesUsersForRaterId());

                return user;
            }
        });

    }

}
