package com.owm.biubiuboom.net.retrofit;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * api帮助类
 * Created by ouweiming on 2016/11/2.
 */

public class ApiHelper {

    public static final String APP_ID = "20161028000030973";
    public static final String KEY = "pgskoVGyRHixyyUj_49u";

    public static String getSign(String query, String salt) {
        String temp = new String();
        temp.append(APP_ID).append(query).append(salt).append(KEY);
        return md5(temp.toString());
    }

    private static String md5(String string) {
        byte[] hash;
        try {
            hash = MessageDigest.getInstance("MD5").digest(string.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Huh, MD5 should be supported?", e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Huh, UTF-8 should be supported?", e);
        }

        StringBuilder hex = new StringBuilder(hash.length * 2);
        for (byte b : hash) {
            if ((b & 0xFF) < 0x10) hex.append("0");
            hex.append(Integer.toHexString(b & 0xFF));
        }
        return hex.toString();
    }
}
