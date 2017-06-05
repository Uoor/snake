package com.lyy.snake.common.env;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by fanshuai on 17/5/9.
 */
public class EnvManager {
    private static Properties properties = new Properties();
    static {
        InputStream appPropertyStream = EnvManager.class.getResourceAsStream("/app");
        if (appPropertyStream==null){
            throw new ExceptionInInitializerError("app config file ["+EnvManager.class.getResourceAsStream("/")+"/app"+"] not found");
        }
        try {
            properties.load(appPropertyStream);
        } catch (IOException e) {
            throw new ExceptionInInitializerError("app config file ["+EnvManager.class.getResourceAsStream("/")+"/app"+"] load error :"+e.getMessage());
        } finally {
            if (appPropertyStream!=null){
                try {
                    appPropertyStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        String appName = properties.getProperty("appname");
        if (appName==null||appName.trim().length()<1){
            throw new ExceptionInInitializerError("app config file ["+EnvManager.class.getResourceAsStream("/")+"/app"+"] not appname item ");
        }
        File file = new File("/data/app/"+appName+"/env");
        InputStream inputStream  = null;
        try {
            if (!file.exists()){
                throw new ExceptionInInitializerError("env config file ["+file.getAbsolutePath()+"] not found");
            }
            inputStream =  new FileInputStream(file);
            properties.load(inputStream);
        } catch (IOException e) {
            throw new ExceptionInInitializerError("env config file ["+file.getAbsolutePath()+"] load error :"+e.getMessage());
        }finally {
            if (inputStream!=null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static String getCurEnvZKHost(){
        return properties.getProperty("zkAddress");
    }


    public static void main(String [] args){
        System.out.println(getCurEnvZKHost());
    }
}
