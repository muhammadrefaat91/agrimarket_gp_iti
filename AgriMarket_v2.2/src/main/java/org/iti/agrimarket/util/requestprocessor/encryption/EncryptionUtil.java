/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iti.agrimarket.util.requestprocessor.encryption;

/**
 *
 * @author muhammad
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.Cipher;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.iti.agrimarket.constant.Constants;
import org.iti.agrimarket.util.requestprocessor.encoding.EncodingUtil;

/**
 * @author JavaDigest
 *
 */
public class EncryptionUtil {

    /**
     * String to hold name of the encryption algorithm.
     */
    public static final String ALGORITHM = "RSA/ECB/PKCS1Padding";

    /**
     * String to hold the name of the private key file.
     */
    public static final String PRIVATE_KEY_FILE = Constants.KEY_PATH + "private.key";
//    public static final String PRIVATE_KEY_FILE = "E:\\GP\\iti-agrimarket\\AgriMarket_v2.1\\src\\java\\org\\iti\\agrimarket\\util\\requestprocessor\\encryption\\keys\\private.key";
    /**
     * String to hold name of the public key file.
     */
//    public static final String PUBLIC_KEY_FILE = "E:\\GP\\iti-agrimarket\\AgriMarket_v2.1\\src\\java\\org\\iti\\agrimarket\\util\\requestprocessor\\encryption\\keys\\public.key";
    public static final String PUBLIC_KEY_FILE = Constants.KEY_PATH + "public.key";

    /**
     * Generate key which contains a pair of private and public key using 1024
     * bytes. Store the set of keys in Prvate.key and Public.key files.
     *
     * @throws NoSuchAlgorithmException
     * @throws IOException
     * @throws FileNotFoundException
     */
    public static void generateKey() {
        try {
            final KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
            keyGen.initialize(2048);
            final KeyPair key = keyGen.generateKeyPair();

            File privateKeyFile = new File(PRIVATE_KEY_FILE);
            File publicKeyFile = new File(PUBLIC_KEY_FILE);

// Create files to store public and private key
            if (privateKeyFile.getParentFile() != null) {
                privateKeyFile.getParentFile().mkdirs();
            }
            privateKeyFile.createNewFile();

            if (publicKeyFile.getParentFile() != null) {
                publicKeyFile.getParentFile().mkdirs();
            }
            publicKeyFile.createNewFile();

// Saving the Public key in a file
            ObjectOutputStream publicKeyOS = new ObjectOutputStream(
                    new FileOutputStream(publicKeyFile));
            publicKeyOS.writeObject(key.getPublic());
            publicKeyOS.close();

// Saving the Private key in a file
            ObjectOutputStream privateKeyOS = new ObjectOutputStream(
                    new FileOutputStream(privateKeyFile));
            privateKeyOS.writeObject(key.getPrivate());
            privateKeyOS.close();
        } catch (Exception e) {
            Logger logger = LogManager.getLogger();
            logger.error(e.getMessage());
        }

    }

    /**
     * The method checks if the pair of public and private key has been
     * generated.
     *
     * @return flag indicating if the pair of keys were generated.
     */
    public static boolean areKeysPresent() {

        File privateKey = new File(PRIVATE_KEY_FILE);
        File publicKey = new File(PUBLIC_KEY_FILE);

        if (privateKey.exists() && publicKey.exists()) {
            return true;
        }
        return false;
    }

    /**
     * Encrypt the plain text using public key.
     *
     * @param text : original plain text
     * @param key :The public key
     * @return Encrypted text
     * @throws java.lang.Exception
     */
    public static String encrypt(String text, PublicKey key) {
        byte[] cipherText = null;
        try {
// get an RSA cipher object and print the provider
            final Cipher cipher = Cipher.getInstance(ALGORITHM);
// encrypt the plain text using the public key
            cipher.init(Cipher.ENCRYPT_MODE, key);

            cipherText = cipher.doFinal(text.getBytes("UTF-8"));
        } catch (Exception e) {
            Logger logger = LogManager.getLogger();
            logger.error(e.getMessage());
        }
        return EncodingUtil.encodeBASE64(cipherText);
    }

    /**
     * Decrypt text using private key.
     *
     * @param text :encrypted text
     * @param key :The private key
     * @return plain text
     * @throws java.lang.Exception
     */
    public static String decrypt(String text, PrivateKey key) throws UnsupportedEncodingException {
        byte[] plaintext = null;
        try {
// get an RSA cipher object and print the provider
            final Cipher cipher = Cipher.getInstance(ALGORITHM);

// decrypt the text using the private key
            cipher.init(Cipher.DECRYPT_MODE, key);

//            dectyptedText = cipher.doFinal(text);
            plaintext = cipher.doFinal(EncodingUtil.decodeBASE64(text));
            return new String(plaintext, "UTF8");

        } catch (Exception ex) {
            Logger logger = LogManager.getLogger();
            logger.error(ex.getMessage());
        }

        return new String(plaintext, "UTF8");
    }

    /**
     * Test the EncryptionUtil
     */
    public static void main(String[] args) {

        try {

// Check if the pair of keys are present else generate those.
            if (!areKeysPresent()) {
// Method generates a pair of keys using the RSA algorithm and stores it
// in their respective files
                generateKey();
            }

            final String originalText = "veerpal";
            ObjectInputStream inputStream = null;

// Encrypt the string using the public key
            inputStream = new ObjectInputStream(new FileInputStream(PUBLIC_KEY_FILE));
            final PublicKey publicKey = (PublicKey) inputStream.readObject();
            String cipherText;
            cipherText = encrypt(originalText, publicKey);
//            String s = new String(cipherText, Charset.forName("UTF-8"));

// Decrypt the cipher text using the private key.
            inputStream = new ObjectInputStream(new FileInputStream(PRIVATE_KEY_FILE));
            final PrivateKey privateKey = (PrivateKey) inputStream.readObject();
//            byte[] gg = new byte[1000000];
//            gg = s.getBytes();
//             gg = DatatypeConverter.parseBase64Binary(s);

            final String plainText = decrypt(cipherText, privateKey);

// Printing the Original, Encrypted and Decrypted Text
//            System.out.println(
//                    "Original: " + originalText
//            );
//            System.out.println(
//                    "Encrypted: " + cipherText.toString()
//            );
//            System.out.println(
//                    "Decrypted: " + plainText
//            );
        } catch (Exception e) {
            Logger logger = LogManager.getLogger();
            logger.error(e.getMessage());
        }
    }
}
