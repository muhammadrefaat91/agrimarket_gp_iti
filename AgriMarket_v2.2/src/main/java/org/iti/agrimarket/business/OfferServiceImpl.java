/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iti.agrimarket.business;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.iti.agrimarket.model.dao.UserOfferProductFixedDAOInterface;
import org.iti.agrimarket.model.pojo.Product;
import org.iti.agrimarket.model.pojo.User;
import org.iti.agrimarket.model.pojo.UserOfferProductFixed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author muhammad
 */
@Service
public class OfferServiceImpl implements OfferService {

    @Autowired
    private UserOfferProductFixedDAOInterface dAOInterface;

    @Override
    public int addOffer(UserOfferProductFixed offerProduct) {
        return dAOInterface.create(offerProduct);
    }

    @Override
    public List<UserOfferProductFixed> getOffers(Product product) {
        return dAOInterface.findOffers(product);
    }

    @Override
    public boolean deleteOffer(Integer id) {
        return dAOInterface.destroy(id);
    }

    public UserOfferProductFixedDAOInterface getdAOInterface() {
        return dAOInterface;
    }

    public void setdAOInterface(UserOfferProductFixedDAOInterface dAOInterface) {
        this.dAOInterface = dAOInterface;
    }

    /**
     * @Author Refaat
     * @param user
     */
    @Override
    public List<UserOfferProductFixed> getUserOffers(User user) {
        return dAOInterface.findUserOffers(user);
    }

    @Override
    public List<UserOfferProductFixed> getOffersByProduct(String productName) {
        return dAOInterface.findUserOfferProductByProduct(productName);
    }

    @Override
    public List<UserOfferProductFixed> getOffersByParent(String parentCategoryName) {
        return dAOInterface.findUserOfferProductByParent(parentCategoryName);
    }

    @Override
    public List<UserOfferProductFixed> getOffersByLocation(String location) {
        return dAOInterface.findUserOfferProductByLocation(location);
    }

    @Override
    public List<UserOfferProductFixed> getOffersByPrice(float price, float maxPrice, String criteria, Integer unitId) {
        String newCriteria = "where userOffer.unitByPricePerUnitId.id = " + unitId + " and FORMAT(userOffer.price,0) " + criteria + " " + (int) price;
        if (criteria.equals("between")) {
            newCriteria += " and " + (int) maxPrice;
        }

        return dAOInterface.findUserOfferProductByPrice(newCriteria);
    }

    @Override
    public List<UserOfferProductFixed> getOffersByQuantity(float quantity, Integer unitId) {
        return dAOInterface.findUserOfferProductByQuantity(quantity, unitId);
    }

    @Override
    public void updateOffer(UserOfferProductFixed offerProductFixed) {
        dAOInterface.edit(offerProductFixed);
    }

    @Override
    public List<UserOfferProductFixed> getOffersByDate(Date date, Date maxDate, String criteria) {

        return dAOInterface.findUserOfferProductByDate(date, maxDate, criteria);
    }

    @Override
    public List<UserOfferProductFixed> getAllOfferProducts() {
        List<UserOfferProductFixed> result = dAOInterface.findAllOfferProducts();
        for (UserOfferProductFixed current : result) {
            current.getProduct();
        }
        return result;
    }

    @Override
    public List<UserOfferProductFixed> getOffersByProductAndCategory(String productName, String categoryName) {
        List<UserOfferProductFixed> result = dAOInterface.findUserOfferProductByProductAndCategory(productName, categoryName);
        if (result.isEmpty()) {
            return new ArrayList<>();
        }
        return result;
    }

    @Override
    public UserOfferProductFixed getOfferProductEager(Integer id) {
        return dAOInterface.findUserOfferProductFixedEager(id);
    }

    @Override
    public List<UserOfferProductFixed> getLimitedOffers(Product productId, int pageNo) {

        return dAOInterface.findLimitedOffers(productId, pageNo);
    }
}
