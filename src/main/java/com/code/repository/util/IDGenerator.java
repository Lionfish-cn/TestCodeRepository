package com.code.repository.util;

import org.apache.tomcat.util.security.MD5Encoder;

public class IDGenerator {
    public static String generateID(){
        Long t = System.currentTimeMillis();
        String v = MD5Encoder.encode(t.toString().getBytes()).substring(10,20);
        return MD5Encoder.encode(v.getBytes());
    }
}
