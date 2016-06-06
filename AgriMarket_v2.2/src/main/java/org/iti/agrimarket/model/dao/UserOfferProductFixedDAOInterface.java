/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iti.agrimarket.model.dao;

import java.util.Date;
import java.util.List;
import org.iti.agrimarket.model.pojo.Product;
import org.iti.agrimarket.model.pojo.User;
import org.iti.agrimarket.model.pojo.UserOfferProductFixed;

/**
 *
 * @author Israa
 */
public interface UserOfferProductFixedDAOInterface {

    public int create(UserOfferProductFixed userOfferProductFixed);

    public void edit(UserOfferProductFixed userOfferProductFixed);

    public boolean destroy(Integer id);

    public List<UserOfferProductFixed> findOffers(Product product);
    

    public List<UserOfferProductFixed> findUserOffers(User user);

    public UserOfferProductFixed findUserOfferProductFixed(Integer id);

    public List<UserOfferProductFixed> findUserOfferProductByProduct(String productName);

    public List<UserOfferProductFixed> findUserOfferProductByParent(String parentCategoryName);


    public List<UserOfferProductFixed> findUserOfferProductByLocation(String location);
    public UserOfferProductFixed findUserOfferProductFixedEager(Integer id);

    

    public List<UserOfferProductFixed> findUserOfferProductByPrice(String criteria);

    public List<UserOfferProductFixed> findUserOfferProductByQuantity(float quantity, Integer unitId);

    public List<UserOfferProductFixed> findUserOfferProductByDate(Date date, Date maxDate, String criteria);
    public List<UserOfferProductFixed> findAllOfferProducts();
        public List<UserOfferProductFixed> findUserOfferProductByProductAndCategory(String productName,String categoryName);

         public void update(UserOfferProductFixed userOfferProductFixed);

    public List<UserOfferProductFixed> findLimitedOffers(Product productId, int pageNo, int sortType);
        
        
}
