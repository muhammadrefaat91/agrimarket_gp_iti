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
import org.iti.agrimarket.model.pojo.Category;
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
@Repository
public class CategoryDAO implements org.iti.agrimarket.model.dao.CategoryDAOInterface {

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
        return this.hibernateTemplate;
    }

    @Autowired
    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    @Override
    public int create(Category category) {
        return (int) transactionTemplate.execute(new TransactionCallback() {
            @Override
            public Object doInTransaction(TransactionStatus ts) {
                try {
                    Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();

                    session.persist(category);
                    return category.getId();
                } catch (Exception e) {
                    e.printStackTrace();
                    ts.setRollbackOnly();
                }
                return -1;
            }
        });
    }

    @Override
    public void edit(Category category) {
        transactionTemplate.execute(new TransactionCallback() {
            @Override
            public Object doInTransaction(TransactionStatus ts) {
                try {
                    Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();

                    session.update(category);
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
        Category category = findCategory(id);
        transactionTemplate.execute(new TransactionCallback() {
            @Override
            public Object doInTransaction(TransactionStatus ts) {
                try {
                    Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();

                    session.delete(category);
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
    public List<Category> findCategoryEntities() {
        return (List<Category>) getHibernateTemplate().execute(new HibernateCallback() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                try {
                    List<Category> result = session.createQuery("from Category c where c.id != 1").list();
                    for (int i = 0; i < result.size(); i++) {
                        Category get = result.get(i);
                        Hibernate.initialize(get.getCategory());
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
    public Category findCategory(Integer id) {
        return (Category) getHibernateTemplate().execute(new HibernateCallback() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                try {
                    Category result = (Category) session.get(Category.class, id);
                    return result;
                } catch (Exception ex) {
                    ex.printStackTrace();

                    return null;
                }
            }
        });
    }

    //Israa
    @Override
    public List<Category> getChildrenOf(Integer categoryId) {
        Category category = findCategory(categoryId);
        return (List<Category>) getHibernateTemplate().execute(new HibernateCallback() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                try {
                    List<Category> result = session.createQuery("from Category c where c.category = :parent and c.id != :id").setEntity("parent", category).setInteger("id", categoryId).list();
                    return result;
                } catch (Exception ex) {
                    ex.printStackTrace();

                    return null;
                }
            }
        });
    }

    @Override
    public List<Category> searchCategory(String categoryName) {
        return (List<Category>) getHibernateTemplate().execute(new HibernateCallback() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                try {
                    List<Category> results = session.createQuery("from Category category where category.nameAr = :name or category.nameEn = :name").setString("name", categoryName).list();
                    return results;
                } catch (Exception ex) {
                    ex.printStackTrace();

                    return null;
                }
            }
        });
    }

    @Override
    public Category findCategoryEager(Integer categoryId) {
        return (Category) getHibernateTemplate().execute(new HibernateCallback() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                try {
                    Category result = (Category) session.get(Category.class, categoryId);
                    Hibernate.initialize(result.getCategories());
                    Hibernate.initialize(result.getCategory());

                    return result;
                } catch (Exception ex) {
                    ex.printStackTrace();

                    return null;
                }
            }
        });
    }

    @Override
    public List<Category> findCategoryWithNoProducts() {
        return (List<Category>) getHibernateTemplate().execute(new HibernateCallback() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                try {
                    List<Category> result = session.createQuery("from Category c where c.id != 1  AND c.products IS EMPTY").list();
                    for (int i = 0; i < result.size(); i++) {
                        Category get = result.get(i);
                        Hibernate.initialize(get.getCategory());
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
    public List<Category> findCategoryWithNoChildCategories() {
        return (List<Category>) getHibernateTemplate().execute(new HibernateCallback() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                try {
                    List<Category> result = session.createQuery("from Category c where c.id != 1 AND c.categories IS EMPTY").list();
                    for (int i = 0; i < result.size(); i++) {
                        Category get = result.get(i);
                        Hibernate.initialize(get.getCategory());
                    }
                    return result;
                } catch (Exception ex) {
                    ex.printStackTrace();

                    return null;
                }
            }
        });
    }

}
