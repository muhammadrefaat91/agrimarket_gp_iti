/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iti.agrimarket.request.param;

/**
 *
 * @author Israa
 */
public class GetLimitedOffersParam {
    private int productId;
    private int pageNo;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public GetLimitedOffersParam() {
    }

    public GetLimitedOffersParam(int productId) {
        this.productId = productId;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    
    
}
