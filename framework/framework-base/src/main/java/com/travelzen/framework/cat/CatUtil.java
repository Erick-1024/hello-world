package com.travelzen.framework.cat;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class CatUtil {
	
	private static final Set<Class<?>> WRAPPER_TYPES = getWrapperTypes();

	public static String getArgsAsString(Object[] args){
		List<String> argStrings = new ArrayList<>();
		if(args != null){
			int argNum = args.length;
			for(int i = 0; i < argNum; i++){
				argStrings.add("arg" + i + "=" + getArgAsString(args[i]));
			}
		}
		String allArgStrings = StringUtils.join(argStrings, ",");
		if(allArgStrings.length() > 10000)
			allArgStrings = allArgStrings.substring(0, 10000);
		return "[" + allArgStrings + "]";
	}
	
	private static String getArgAsString(Object arg){
		if(arg == null)
			return "NULL";
		if(arg.getClass() == String.class || isWrapperType(arg.getClass()))
			return arg.toString();
		return ReflectionToStringBuilder.toString(arg);
	}
	
	public static boolean isWrapperType(Class<?> clazz){
        return WRAPPER_TYPES.contains(clazz);
    }

    private static Set<Class<?>> getWrapperTypes(){
        Set<Class<?>> ret = new HashSet<Class<?>>();
        ret.add(Boolean.class);
        ret.add(Character.class);
        ret.add(Byte.class);
        ret.add(Short.class);
        ret.add(Integer.class);
        ret.add(Long.class);
        ret.add(Float.class);
        ret.add(Double.class);
        ret.add(Void.class);
        return ret;
    }
    
}
