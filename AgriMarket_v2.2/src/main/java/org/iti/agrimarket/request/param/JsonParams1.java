/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iti.agrimarket.request.param;

/**
 *
 * @author muhammad
 */
public class JsonParams1 {

    private String jsonParam;
    private String user;
    private byte[] key;
    private int compressLength;

    public String getJsonParam() {
        return jsonParam;
    }

    public void setJsonParam(String jsonParam) {
        this.jsonParam = jsonParam;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public byte[] getKey() {
        return key;
    }

    public void setKey(byte[] key) {
        this.key = key;
    }

    
    public int getCompressLength() {
        return compressLength;
    }

    public void setCompressLength(int compressLength) {
        this.compressLength = compressLength;
    }

}
