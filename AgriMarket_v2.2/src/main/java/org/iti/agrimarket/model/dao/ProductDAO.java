/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iti.agrimarket.model.dao;

import java.util.List;
import org.hibernate.Hibernate;
import org.iti.agrimarket.model.pojo.Product;
import org.hibernate.HibernateException;
import org.hibernate.Session;
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
public class ProductDAO implements ProductDAOInterface {

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

    //Israa
    @Override
    public int create(Product product) {
        return (int) transactionTemplate.execute(new TransactionCallback() {
            @Override
            public Object doInTransaction(TransactionStatus ts) {
                try {
                    Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();

                    session.persist(product);
                    return product.getId();
                } catch (Exception e) {
                    e.printStackTrace();
                    ts.setRollbackOnly();
                }
                return -1;
            }
        });
    }

    //Israa
    @Override
    public void edit(Product product) {
        transactionTemplate.execute(new TransactionCallback() {
            @Override
            public Object doInTransaction(TransactionStatus ts) {
                try {
                    Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();

                    session.update(product);
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
    public void destroy(Integer id) {
        Product product = findProduct(id);
        transactionTemplate.execute(new TransactionCallback() {
            @Override
            public Object doInTransaction(TransactionStatus ts) {
                try {
                    Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();

                    session.delete(product);
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
    public List<Product> findProductEntities() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    //Refaat
    @Override
    public Product findProduct(Integer id) {
        return (Product) getHibernateTemplate().execute((Session sn) -> sn.createQuery("from Product p where p.id=:id")
                .setInteger("id", id).uniqueResult());
    }

    //Israa
    @Override
    public List<Product> getChildrenOf(Integer categoryId) {
        return (List<Product>) getHibernateTemplate().execute(new HibernateCallback() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                try {
                    List<Product> result = session.createQuery("from Product p where p.category.id = :parent").setInteger("parent", categoryId).list();
                    return result;
                } catch (Exception ex) {
                    ex.printStackTrace();

                    return null;
                }
            }
        });
    }

    //amr
    @Override
    public List<Product> getAllProducts() {
        return (List<Product>) getHibernateTemplate().execute(new HibernateCallback() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                try {
                    List<Product> result = session.createQuery("from Product p").list();
                    return result;
                } catch (Exception ex) {
                    ex.printStackTrace();

                    return null;
                }
            }
        });
    }

    @Override
    public List<Product> searchProduct(String productName) {
        return (List<Product>) getHibernateTemplate().execute(new HibernateCallback() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                try {
                    List<Product> results = session.createQuery("from Product product where product.nameAr = :name or product.nameEn = :name").setString("name", productName).list();
                    return results;
                } catch (Exception ex) {
                    ex.printStackTrace();

                    return null;
                }
            }
        });
    }

    @Override
    public List<Product> getAllProductsEager() {
        return (List<Product>) getHibernateTemplate().execute(new HibernateCallback() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                try {
                    List<Product> result = session.createQuery("from Product p").list();
                    for (int i = 0; i < result.size(); i++) {
                        Product get = result.get(i);
                        Hibernate.initialize(get.getCategory());

//                        Hibernate.initialize(get.getUserOfferProductFixeds());
                    }
                    return result;
                } catch (Exception ex) {
                    ex.printStackTrace();

                    return null;
                }
            }
        });
    }

    @Override
    public Product findProductEager(Integer productId) {
        return (Product) getHibernateTemplate().execute(new HibernateCallback() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                try {
                    Product result = (Product) session.createQuery("from Product p where p.id=:id")
                            .setInteger("id", productId).uniqueResult();
                    Hibernate.initialize(result.getCategory());

                    Hibernate.initialize(result.getUserOfferProductFixeds());
                    return result;
                } catch (Exception ex) {
                    ex.printStackTrace();

                    return null;
                }
            }
        });
    }

}
