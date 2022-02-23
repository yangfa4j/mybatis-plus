package com.yf.utils;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class MD5Utils {

    /**
     * 对字符串进行md5加密
     *
     * @param str 需要加密的字符串
     * @return
     */
    public static String getMD5(String str) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(str.getBytes());
            return new BigInteger(1, messageDigest.digest()).toString(16);
        } catch (Exception e) {
            return "";
        }
    }


    /**
     * MD5加密，UTF-8编码，并转成16进制
     *
     * @param encryptStr 需要加密的字符串
     * @return
     */
    public static String getMD5HexUtf8(String encryptStr) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(encryptStr.getBytes(StandardCharsets.UTF_8));
            byte[] digest = md.digest();
            StringBuffer md5 = new StringBuffer();
            for (byte b : digest) {
                md5.append(Character.forDigit((b & 0xF0) >> 4, 16));
                md5.append(Character.forDigit((b & 0xF), 16));
            }
            return md5.toString();
        } catch (Exception ex) {
            return null;
        }
    }

    private static final char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    public static String md5(String s) {
        try {
            byte[] btInput = s.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char[] str = new char[j * 2];
            int k = 0;
            for (byte byte0 : md) {
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            throw new IllegalArgumentException("md5 operation error");
        }
    }
}
