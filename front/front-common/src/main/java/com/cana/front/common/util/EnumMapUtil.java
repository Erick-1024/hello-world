package com.cana.front.common.util;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public abstract class EnumMapUtil {

   private static final Logger LOGGER = LoggerFactory.getLogger(EnumMapUtil.class);

   private static Map<String, Map<String, Object>> enumMap = new HashMap<String, Map<String, Object>>();

   private static final String DEFAUTL_DESC_METHOD = "getDesc";

   /**
    * 将一个ENUM的所有对象都放入MAP，KEY为其类名，VALUE为（name,desc）的键值对MAP
    * 
    * @param enumClass
    */
   public <E extends Enum<E>> void addEnum(Class<E> enumClass) {
      addEnum(enumClass, enumClass.getSimpleName());
   }

   /**
    * 将一个ENUM的所有对象都放入MAP，KEY为其类名，VALUE为（name,desc）的键值对MAP
    * 
    * @param enumClass
    */
   public <E extends Enum<E>> void addEnum(Class<E> enumClass, String alias) {
      addEnum(enumClass, alias, DEFAUTL_DESC_METHOD);
   }

   public <E extends Enum<E>> void addEnum(Class<E> enumClass, String alias, String methodName) {
      try {
         Map<String, Object> itemMap = new LinkedHashMap<String, Object>();
         E[] enums = enumClass.getEnumConstants();
         for (E item : enums) {
            final Method getDesc = item.getClass().getMethod(methodName);
            itemMap.put(item.name(), (String) getDesc.invoke(item));
         }
         enumMap.put(alias, itemMap);
      } catch (Exception e) {
         LOGGER.error(e.getMessage(), e);
      }
   }

   /**
    * 以特定的名来定义ENUM，并且将一些ENUM中的对象过滤掉
    * 
    * @param enumName
    * @param ignoreItems
    * @param enumClass
    */
   public <E extends Enum<E>> void addEnum(Class<E> enumClass, String alias, List<E> ignoreItems) {
      try {
         // if (enumClass.getSimpleName().equals(alias)) {
         // throw JnBizException.instance("自定义的枚举名不能是本身的对象");
         // }
         Map<String, Object> itemMap = new LinkedHashMap<String, Object>();
         E[] enums = enumClass.getEnumConstants();
         for (E item : enums) {
            final Method getDesc = item.getClass().getMethod(DEFAUTL_DESC_METHOD);
            String descItem = (String) getDesc.invoke(item);
            if (!ignoreItems.contains(item)) {
               itemMap.put(item.name(), descItem);
            }
         }
         enumMap.put(alias, itemMap);
      } catch (Exception e) {
         LOGGER.error(e.getMessage(), e);
      }
   }

   public void addEnum(Map<String, Object> outEnumMap, String alias) {
      enumMap.put(alias, outEnumMap);
   }

   public <E extends Enum<E>> Map<String, Object> getEnum(Class<E> enumClass) {
      String enumName = enumClass.getName();
      return getEnum(enumName);
   }

   public <E extends Enum<E>> Map<String, Object> getEnum(String enumName) {
      if (!enumMap.containsKey(enumName)) {
         return null;
      } else {
         return enumMap.get(enumName);
      }
   }

   public Map<String, Map<String, Object>> getEnumMap() {
      return enumMap;
   }

   public abstract void init();
}
