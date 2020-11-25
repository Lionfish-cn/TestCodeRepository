package com.code.repository.spider;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Enumeration;
import java.util.Properties;

public class SpiderUtil {

    public static HttpURLConnection getHttpURLConnection(String url,String method, Properties properties) throws Exception{
        URL u = new URL(url);
        HttpURLConnection httpURLConnection = (HttpURLConnection)u.openConnection();
        httpURLConnection.setRequestMethod(method);
        Enumeration<?> prop = properties.propertyNames();
        while(prop.hasMoreElements()){
            String name = prop.nextElement().toString();
            httpURLConnection.setRequestProperty(name,properties.getProperty(name));
        }
        httpURLConnection.connect();
        return httpURLConnection;
    }

    @Autowired
    public static void httpPost(String url,Properties properties,JSONObject formdata) throws  Exception{
        HttpURLConnection connection = getHttpURLConnection(url,"post",properties);

        DataOutputStream dos = new DataOutputStream(connection.getOutputStream());
//        from: zh
//        to: en
//        query: 星期一
//        transtype: translang
//        simple_means_flag: 3
//        sign: 429336.159273
//        token: 934c6a2a7c3304aea6b16cdf930c2317
//        domain: common

        dos.writeUTF(formdata.toJSONString());

        if(connection.getResponseCode()==HttpURLConnection.HTTP_OK){
            DataInputStream dis = new DataInputStream(connection.getInputStream());
            String line = dis.readUTF();
            System.out.println(line);
        }
    }
}
