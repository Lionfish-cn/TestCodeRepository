package com.code.repository.util;

public class EntityUtil {

    public  static String parseNameByModelName(String bean){
        String clzname = bean.substring(bean.lastIndexOf("\\."));
        return clzname.toUpperCase().charAt(0)+clzname.substring(1);
    }
}
