package com.goose.tools.mybatis.utils;

import java.util.ResourceBundle;

public class MyUtils
{
    private static ResourceBundle rb = null;
    private static ResourceBundle jdbc_rb = null;
    private static ResourceBundle full_rb = null;

    static
    {
        rb = ResourceBundle.getBundle("type");
        jdbc_rb = ResourceBundle.getBundle("jdbcType");
        full_rb = ResourceBundle.getBundle("fullType");
    }

    public static String formatToClassName(String name)
    {
        if (name == null)
        {
            return name;
        }
        else if (name.indexOf("_") == -1)
        {
            return upperFirstLetter(name);
        }
        else
        {
            String[] temp = name.split("_");
            String result = "";
            for (String string : temp)
            {
                result += upperFirstLetter(string);
            }
            return result;
        }
    }

    public static String formatToVarName(String name)
    {
        if (name == null)
        {
            return name;
        }
        else if (name.indexOf("_") == -1)
        {
            return lowerFirstLetter(name);
        }
        else
        {
            String[] temp = name.split("_");
            String result = "";
            for (String string : temp)
            {
                result += upperFirstLetter(string);
            }
            return lowerFirstLetter(result);
        }
    }

    public static String formatDataType(String name)
    {
        if (name == null || "".equals(name))
        {
            return name;
        }
        else
        {
            try
            {
                return rb.getString(name);
            }
            catch (Exception e)
            {
                return name;
            }
        }
    }
    
    public static String formatJdbcType(String name)
    {
        if (name == null || "".equals(name))
        {
            return name;
        }
        else
        {
            try
            {
                return jdbc_rb.getString(name);
            }
            catch (Exception e)
            {
                return name;
            }
        }
    }
    
    public static String formatFullType(String name)
    {
        if (name == null || "".equals(name))
        {
            return name;
        }
        else
        {
            try
            {
                return full_rb.getString(name);
            }
            catch (Exception e)
            {
                return name;
            }
        }
    }

    /**
     * Ê××ÖÄ¸´óÐ´»¯
     * @param src   ×Ö·û´®
     * @return      Ê××ÖÄ¸´óÐ´ºóµÄ×Ö·û´®
     */
    public static String upperFirstLetter(String src)
    {
        if (src == null || "".equals(src))
        {
            return src;
        }
        return src.substring(0, 1).toUpperCase() + src.substring(1);
    }

    /**
     * Ê××ÖÄ¸Ð¡Ð´
     * @param src   ×Ö·û´®
     * @return      Ê××ÖÄ¸Ð¡Ð´ºóµÄ×Ö·û´®
     */
    public static String lowerFirstLetter(String src)
    {
        if (src == null || "".equals(src))
        {
            return src;
        }
        return src.substring(0, 1).toLowerCase() + src.substring(1);
    }
}
