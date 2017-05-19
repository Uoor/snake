package com.lyy.snake.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by fanshuai on 17/5/19.
 */
public class PatternUtil {
    public static boolean isNotMatchs(String str, String regex){
        return !isMatchs(str,regex);
    }
    public static boolean isMatchs(String str, String regex){
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    public static void main(String[] args){
        System.out.println(isNotMatchs("demo", "^[a-zA-Z]{1}[a-zA-Z0-9\\-]*$"));
    }
}
