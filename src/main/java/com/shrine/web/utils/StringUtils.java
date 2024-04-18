package com.shrine.web.utils;

public class StringUtils {
    public static String removePunctuationAndSpace(String s){
        return s.replaceAll("[\\p{Punct}\\s]+", "");
    }
}
