package com.pingpongx.smb.fee.server.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author chenbz@pingpongx.com
 * @version 1.0.0
 * @description 描述
 * @time 2019/04/23 14:57
 */
public class SHAUtil {

    static char[] HEX_DIGIT = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public static String sha512(String strSrc) {
        return doSha(strSrc, "UTF-8", "SHA-512");
    }

    public static boolean verifySha512(String strSrc, String sign) {
        return sha512(strSrc).equalsIgnoreCase(sign);
    }

    private static String doSha(String data, String charsetName, String algorithm) {
        MessageDigest messageDigest;
        String strDst = null;

        try {
            byte[] bt = data.getBytes(charsetName);
            messageDigest = MessageDigest.getInstance(algorithm);
            messageDigest.update(bt);
            byte[] digestData = messageDigest.digest();
            strDst = binToHex(digestData);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return strDst;
    }

    public static String binToHex(byte[] bin) {
        if (bin == null) {
            throw new IllegalArgumentException("Parameter bin shouldn't be null");
        }
        int len = bin.length;
        char str[] = new char[len * 2];
        int k = 0;
        // 移位 输出字符串
        for (int i = 0; i < len; i++) {
            byte byte0 = bin[i];
            str[k++] = HEX_DIGIT[byte0 >>> 4 & 0x0f];
            str[k++] = HEX_DIGIT[byte0 & 0x0f];
        }
        return new String(str);
    }

}
