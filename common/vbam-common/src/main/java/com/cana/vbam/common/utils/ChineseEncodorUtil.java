package com.cana.vbam.common.utils;

import java.io.UnsupportedEncodingException;

public class ChineseEncodorUtil {
	public static String encodeStr(String str) {  
        try {  
            return new String(str.getBytes("ISO-8859-1"), "UTF-8");  
        } catch (UnsupportedEncodingException e) {  
            e.printStackTrace();  
            return null;  
        }  
    }
	
	public static String decodeStr(String str) {  
        try {  
            return new String(str.getBytes("UTF-8"), "ISO-8859-1");  
        } catch (UnsupportedEncodingException e) {  
            e.printStackTrace();  
            return null;  
        }  
    }

}
