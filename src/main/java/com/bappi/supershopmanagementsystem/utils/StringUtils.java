package com.bappi.supershopmanagementsystem.utils;

public class StringUtils {
    public static boolean isNullorEmpty(String input)
    {
        if(input == null)
            return true;
        else if(input.trim().isEmpty())
            return true;
        else
            return false;
    }

}
