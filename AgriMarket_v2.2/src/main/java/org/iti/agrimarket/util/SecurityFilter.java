/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iti.agrimarket.util;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.security.PrivateKey;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.FilterChain;
import javax.servlet.ReadListener;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import org.iti.agrimarket.request.param.JsonParams;
import org.iti.agrimarket.util.requestprocessor.encryption.EncryptionUtil;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 *
 * @author muhammad
 */
public class SecurityFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest hsr, HttpServletResponse hsr1, FilterChain fc) throws ServletException {

        try {
//            System.out.println("Hi filter");
            MultiReadHttpServletRequest multiReadRequest = new MultiReadHttpServletRequest(hsr);
            if (autheniticate(multiReadRequest)) {

                fc.doFilter(multiReadRequest, hsr1);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SecurityFilter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SecurityFilter.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private String readRequestBody(HttpServletRequest hsr) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = hsr.getReader();

        try {
            String line1;
            while ((line1 = br.readLine()) != null) {
                sb.append(line1);
            }
        } finally {
            br.close();
        }
        return sb.toString();
    }

    private boolean autheniticate(HttpServletRequest hsr) throws ClassNotFoundException, IOException {

//        String jsonBody = readRequestBody(hsr);
//
//        Gson gson = new Gson();
//        JsonParams jsonParams = gson.fromJson(jsonBody, JsonParams.class);
//        String encryptedKey = jsonParams.getKeyService();
//
//        ObjectInputStream os = new ObjectInputStream(new FileInputStream(EncryptionUtil.PRIVATE_KEY_FILE));
//        final PrivateKey privateKey = (PrivateKey) os.readObject();
//        String decryptedTimeStamp = EncryptionUtil.decrypt(encryptedKey, privateKey);
//
//        decryptedTimeStamp=decryptedTimeStamp.substring(0, decryptedTimeStamp.length() - 5);
//
//        long ts = 0;
//        try {
//            ts = Long.valueOf(decryptedTimeStamp);
//        } catch (Exception e) {
//
//            return false;
//        }
//
//        long currTs = new Date().getTime();
//        
//        if (Math.abs(currTs - ts) <= (30 * 60 * 1000)) {
//            return true;
//        } else {
//            return false;
//        }
        
        return true;
    }

    public class MultiReadHttpServletRequest extends HttpServletRequestWrapper {

        private ByteArrayOutputStream cachedBytes;

        public MultiReadHttpServletRequest(HttpServletRequest request) {
            super(request);
        }

        @Override
        public ServletInputStream getInputStream() throws IOException {
            if (cachedBytes == null) {
                cacheInputStream();
            }

            return new CachedServletInputStream();
        }

        @Override
        public BufferedReader getReader() throws IOException {
            return new BufferedReader(new InputStreamReader(getInputStream()));
        }

        private void cacheInputStream() throws IOException {
            /* Cache the inputstream in order to read it multiple times. For
     * convenience, I use apache.commons IOUtils
             */
            cachedBytes = new ByteArrayOutputStream();
            IOUtils.copy(super.getInputStream(), cachedBytes);
        }

        /* An inputstream which reads the cached request body */
        public class CachedServletInputStream extends ServletInputStream {

            private ByteArrayInputStream input;

            public CachedServletInputStream() {
                /* create a new input stream from the cached request body */
                input = new ByteArrayInputStream(cachedBytes.toByteArray());
            }

            @Override
            public int read() throws IOException {
                return input.read();
            }

            @Override
            public boolean isFinished() {
                return true;
            }

            @Override
            public boolean isReady() {
                return true;
            }

            @Override
            public void setReadListener(ReadListener readListener) {
            }
        }
    }
}
