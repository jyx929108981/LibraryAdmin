package com.library.libraryoccupyseat.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author jyx
 * Created by weisi on 2017/7/20.
 */

public class Md5Utils {
    public static String md5(String strDisplay) {
        return md5Ser(strDisplay.getBytes());
    }

    public static String md5Ser(byte[] value) {
        try {
            MessageDigest ex = MessageDigest.getInstance("MD5");
            ex.update(value);
            return new String(ex.digest());
        } catch (NoSuchAlgorithmException var2) {
            return null;
        }
    }
}
