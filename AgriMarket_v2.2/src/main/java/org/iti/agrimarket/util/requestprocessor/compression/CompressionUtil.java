/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iti.agrimarket.util.requestprocessor.compression;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterOutputStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.iti.agrimarket.util.requestprocessor.encoding.EncodingUtil;

/**
 *
 * @author Israa
 */
public class CompressionUtil {

    public static String compress(String input) {
        String result = null;
        try {
            byte[] testdata = input.getBytes("UTF-8");

            byte[] compressed = null;
            try (ByteArrayOutputStream buffer = new ByteArrayOutputStream()) {
                try (DeflaterOutputStream stream = new DeflaterOutputStream(buffer)) {
                    stream.write(testdata);
                }
                compressed = buffer.toByteArray();
                result = EncodingUtil.encodeBASE64(compressed);
            } catch (IOException e) {

                Logger logger = LogManager.getLogger();
                logger.error(e.getMessage());
            }
        } catch (UnsupportedEncodingException ex) {
            Logger logger = LogManager.getLogger();
            logger.error(ex.getMessage());
        }
        return result;
    }

    public static String decompress(String input) {

        String result = null;
        try {
            byte[] compressed = EncodingUtil.decodeBASE64(input);

            byte[] decompressed = null;
            try (ByteArrayOutputStream buffer = new ByteArrayOutputStream()) {
                try (InflaterOutputStream stream = new InflaterOutputStream(buffer)) {
                    stream.write(compressed);
                }
                decompressed = buffer.toByteArray();
                result = new String(decompressed, "UTF-8");

            } catch (IOException e) {

                Logger logger = LogManager.getLogger();
                logger.error(e.getMessage());
            }

        } catch (Exception ex) {
            Logger logger = LogManager.getLogger();
            logger.error(ex.getMessage());
        }
        return result;
    }

}
