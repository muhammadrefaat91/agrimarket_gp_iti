/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iti.agrimarket.util.requestprocessor.encoding;

import sun.misc.*;

/**
 *
 * @author Israa
 */
public class EncodingUtil {

    public static String encodeBASE64(byte[] bytes) {

        BASE64Encoder b64 = new BASE64Encoder();

        return b64.encode(bytes);

    }

    public static byte[] decodeBASE64(String text) throws Exception {

        BASE64Decoder b64 = new BASE64Decoder();

        return b64.decodeBuffer(text);

    }
}
