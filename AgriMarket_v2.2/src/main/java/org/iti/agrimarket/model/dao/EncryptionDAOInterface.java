/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iti.agrimarket.model.dao;

import java.util.List;
import org.iti.agrimarket.model.pojo.Encryption;

/**
 *
 * @author Israa
 */
public interface EncryptionDAOInterface {
    
    public void create(Encryption encryption);
    
    public void edit(Encryption encryption);
    
    public void destroy(Integer id);
    
    public List<Encryption> findEncryptionEntities() ;

    public Encryption findEncryption(Integer id);
    
}
