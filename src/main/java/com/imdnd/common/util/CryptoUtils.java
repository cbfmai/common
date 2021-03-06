package com.imdnd.common.util;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * AES Encryption and Decryption
 */
public class CryptoUtils {

    // Default KEY
    public static final String SECRET_KEY = "Qa*EFaj_Fuch7_Ez";

    public static String encrypt(String clearText) {
        return encrypt(clearText, SECRET_KEY);
    }

    public static String decrypt(String encryptedText) {
        return decrypt(encryptedText, SECRET_KEY);
    }

    public static String encrypt(String strClearText, String strKey) {
        String strData = null;

        if (strClearText == null || strKey == null) {
            return null;
        }

        try {
            SecretKeySpec keySpec = new SecretKeySpec(strKey.getBytes(Charset.forName("UTF-8")), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, keySpec);
            byte[] encrypted = cipher.doFinal(strClearText.getBytes(Charset.forName("UTF-8")));
            Base64.Encoder encoder = Base64.getEncoder();
            strData = new String(encoder.encode(encrypted), StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strData;
    }

    public static String decrypt(String strEncrypted, String strKey) {
        if (strEncrypted == null || strKey == null) {
            return null;
        }
        String strData = null;
        try {
            SecretKeySpec keySpec = new SecretKeySpec(strKey.getBytes(Charset.forName("UTF-8")), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, keySpec);
            Base64.Decoder decoder = Base64.getDecoder();
            byte[] decryptedBase64 = decoder.decode(strEncrypted.getBytes(Charset.forName("UTF-8")));
            byte[] decrypted = cipher.doFinal(decryptedBase64);
            strData = new String(decrypted, StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strData;
    }

}