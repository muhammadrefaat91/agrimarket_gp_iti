/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iti.agrimarket.business;

import java.util.Date;
import java.util.List;
import org.iti.agrimarket.model.pojo.GroupedOffers;
import org.iti.agrimarket.model.pojo.Product;
import org.iti.agrimarket.model.pojo.User;
import org.iti.agrimarket.model.pojo.UserOfferProductFixed;

/**
 *
 * @author muhammad
 */
public interface OfferService {

    int addOffer(UserOfferProductFixed offerProduct);

    boolean deleteOffer(Integer id);

    List<UserOfferProductFixed> getOffers(Product productId);
    

    List<UserOfferProductFixed> getUserOffers(User user);

    public List<UserOfferProductFixed> getOffersByProduct(String productName);

    public List<UserOfferProductFixed> getOffersByParent(String parentCategoryName);

    public List<UserOfferProductFixed> getOffersByLocation(String location);

    public List<UserOfferProductFixed> getOffersByPrice(float price, float maxPrice, String criteria, Integer unitId);

    public List<UserOfferProductFixed> getOffersByQuantity(float quantity, Integer unitId);

    public void updateOffer(UserOfferProductFixed offerProductFixed);

    public List<UserOfferProductFixed> getOffersByDate(Date date, Date maxDate, String criteria);

    public List<UserOfferProductFixed> getAllOfferProducts();

    public List<UserOfferProductFixed> getOffersByProductAndCategory(String productName, String categoryName);
    public UserOfferProductFixed getOfferProductEager(Integer id);

    public List<UserOfferProductFixed> getLimitedOffers(Product product, int pageNo, int sortType);
    
    public GroupedOffers getGroupedLimitedOffers(Product productId, int pageNo, int sortType);
    
    public GroupedOffers searchGroupedLimitedOffers(String productName, int pageNo, int sortType);
    public List<UserOfferProductFixed> searchLimitedOffers(String productName, int pageNo, int sortType);
    public List<UserOfferProductFixed> getLatestOffers();

    public List<UserOfferProductFixed> getOffersByCategory(Integer categoryId);
}
