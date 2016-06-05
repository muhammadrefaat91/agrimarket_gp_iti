/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iti.agrimarket.util;

import org.iti.agrimarket.model.pojo.User;

/**
 *
 * @author Amr
 */
public class Validation {
    
    
      public static boolean validateUser(User user) {

        if (user == null
                || user.getFullName() == null || user.getFullName().isEmpty() || user.getFullName().length() > 99
                || user.getMail() == null || user.getMail().isEmpty() || user.getMail().length() > 99) {

            return false;

        } else {

            return true;
        }

    }

    
    
    
}
