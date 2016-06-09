/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iti.agrimarket.util;

import java.util.List;
import org.iti.agrimarket.model.pojo.UserOfferProductFixed;

/**
 *
 * @author muhammad
 */
public interface SortOffers {
    
    int partition(List<UserOfferProductFixed> arr,int left,int right);
    
    default void sort(List<UserOfferProductFixed> arr, int left, int right) {
        
        int index = partition(arr, left, right);
        if (left < index - 1) {
            sort(arr, left, index - 1);
        }
        if (index < right) {
            sort(arr, index, right);
        }
    }
}
