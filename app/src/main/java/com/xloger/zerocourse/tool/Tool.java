package com.xloger.zerocourse.tool;

import android.util.Log;

import java.math.BigInteger;
import java.security.MessageDigest;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

/**
 * Created by xloger on 2017/2/19.
 */

public class Tool {
    /**
     * 计算md5
     */
    public static String md5(String origin) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(origin.getBytes("UTF-8"));
            BigInteger bi = new BigInteger(1, md.digest());

            return bi.toString(16);
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * Java 连个数组toSting都没有简直了
     */
    public static void arrayToString(Object[] array){
        StringBuffer buffer=new StringBuffer();
        buffer.append("[");
        for (int i = 0; i < array.length; i++) {
            buffer.append(array[i].toString()).append(",");
        }
        buffer.append("]");
        Log.e("xloger",buffer.toString());
    }

    public static void arrayToString(int[] array){
        StringBuffer buffer=new StringBuffer();
        buffer.append("[");
        for (int i = 0; i < array.length; i++) {
            buffer.append(array[i]).append(",");
        }
        buffer.append("]");
        Log.e("xloger",buffer.toString());
    }
}
