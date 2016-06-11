/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iti.agrimarket.view;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.iti.agrimarket.constant.Constants;

/**
 *
 * @author muhammad
 */
//@WebServlet("/image/*")
public class ResourceController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("resource controller");

        //your image servlet code here
        String imageName = request.getPathInfo().substring(1); // Returns "foo.png".
        // Set content size
        if(imageName != null && !imageName.equals("")){
        File file = new File(Constants.IMAGE_PATH+imageName);
        response.setContentLength((int) file.length());

        // Open the file and output streams
        FileInputStream in = new FileInputStream(file);
        OutputStream out = response.getOutputStream();

        // Copy the contents of the file to the output stream
        byte[] buf = new byte[1024];
        int count = 0;
        while ((count = in.read(buf)) >= 0) {
            out.write(buf, 0, count);
        }
        in.close();
        out.close();
        }else{
            response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
        }

//        List<Product> products = (List<Product>) request.getServletContext().getAttribute("products");
////        ProductDao productDao = new ProductDaoImp();
//        for (Product product : products) {
//            if (product.getName().equals(proName)) {
//                byte[] image = product.getImage();
//                if (image != null) {
//                    response.setContentType(getServletContext().getMimeType(proName));
//                    response.setContentLength(image.length);
//                    response.getOutputStream().write(image);
//                } else {
//                    response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
//                }
//            }
//        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

}
