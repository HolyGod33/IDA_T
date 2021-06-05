package com.zjut.ida.recommend.tutor.utils;

import javax.servlet.http.HttpServletRequest;
import java.security.MessageDigest;
import java.util.Collection;
import java.util.SortedMap;
import java.util.UUID;

/**
 * @author wly
 * @date 2021/4/26 14:05
 */
public class CommonUtils {
    private static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    public static boolean isEmpty(String s) {
        return s == null || s.isEmpty();
    }

    public static String uuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static String uuid16() {
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        int first = uuid.substring(0, 16).hashCode();
        int second = uuid.substring(16).hashCode();
        return String.format("%08x", first) + String.format("%08x", second);
    }

    public static String random(int radix, int digit) {
        int max = (int) Math.pow(radix, digit);
        int rand = (int) (Math.random() * max);
        return Integer.toString(rand, radix);
    }

    public static String getRealIp(HttpServletRequest request) {
        if (request.getHeader("x-forwarded-for") == null) {
            return request.getRemoteAddr();
        }
        return request.getHeader("x-forwarded-for");
    }

    public static String MD5(String text) {
        return getMessageDigestString("MD5", text, null);
    }

    public static String signSHA1(SortedMap<String, String> paramMap) {
        if (paramMap == null) {
            return null;
        }
        StringBuffer sb = new StringBuffer();
        paramMap.forEach((k, v) -> {
            if (v != null && !"sign".equals(k)) {
                if (sb.length() == 0) {
                    sb.append(k + "=" + v);
                } else {
                    sb.append("&" + k + "=" + v);
                }
            }
        });
        return getMessageDigestString("SHA1", sb.toString(), "UTF-8");
    }

    public static String signMD5(SortedMap<String, String> paramMap, String appKey) {
        StringBuffer sb = new StringBuffer();
        paramMap.forEach((k, v) -> {
            if (!isEmpty(v) && !"sign".equals(k) && !"key".equals(k)) {
                sb.append(k + "=" + v + "&");
            }
        });
        sb.append("key=" + appKey);
        return getMessageDigestString("MD5", sb.toString(), "UTF-8").toUpperCase();
    }

    private static String getMessageDigestString(String method, String origin, String charsetname) {
        if (isEmpty(origin)) {
            return null;
        }
        try {
            MessageDigest md = MessageDigest.getInstance(method);
            if (isEmpty(charsetname)) {
                return byteArrayToHexString(md.digest(origin.getBytes()));
            } else {
                return byteArrayToHexString(md.digest(origin.getBytes(charsetname)));
            }
        } catch (Exception exception) {
            return null;
        }
    }

    private static String byteArrayToHexString(byte b[]) {
        char[] buf = new char[b.length * 2];
        for (int i = 0; i < buf.length; ) {
            byte byte0 = b[i / 2];
            buf[i++] = HEX_DIGITS[byte0 >>> 4 & 0xf];
            buf[i++] = HEX_DIGITS[byte0 & 0xf];
        }
        return new String(buf);
    }
}
