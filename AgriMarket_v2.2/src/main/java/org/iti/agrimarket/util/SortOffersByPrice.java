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
public class SortOffersByPrice implements SortOffers{

//    @Override
//    public int partition(List<UserOfferProductFixed> arr, int left, int right) {
//        int i = left, j = right;
//        UserOfferProductFixed tmp;
//        UserOfferProductFixed pivot = arr.get((left + right) / 2);
//        while (i <= j) {
//            while (arr.get(i).getUnitByUnitId().getId() > pivot.getUnitByUnitId().getId()) {
//                i++;
//            }
//            while (arr.get(j).getUnitByUnitId().getId() < pivot.getUnitByUnitId().getId()) {
//                j--;
//            }
//            if (i <= j) {
//                tmp = arr.get(i);
//                 arr.set(i, arr.get(j));
//                 arr.set(j, tmp);
//                i++;
//                j--;
//            }
//        };
//        return i;
//    }

    @Override
    public int partition(List<UserOfferProductFixed> arr, int left, int right) {
        int i = left, j = right;
        UserOfferProductFixed tmp;
        UserOfferProductFixed pivot = arr.get((left + right) / 2);

        while (i <= j) {
            while (arr.get(i).getUnitByUnitId().getId() < pivot.getUnitByUnitId().getId()) {
//                if (arr.get(i).getUnitByUnitId().getId() == pivot.getUnitByUnitId().getId()) {
//                    while (arr.get(i).getPrice() < pivot.getPrice())
                        i++;
//                }else{
//                    i++;
//                }
            }
            while (arr.get(j).getUnitByUnitId().getId() > pivot.getUnitByUnitId().getId()) {
//                j--;
                
//                 if (arr.get(i).getUnitByUnitId().getId() == pivot.getUnitByUnitId().getId()) {
//                    while (arr.get(i).getPrice() > pivot.getPrice())
//                         j--;
//                }else{
                     j--;
//                }
            }
            if (i <= j) {
                tmp = arr.get(i);
                arr.set(i, arr.get(j));
                arr.set(j, tmp);
                i++;
                j--;
            }
        };
        return i;
    }
    
}
