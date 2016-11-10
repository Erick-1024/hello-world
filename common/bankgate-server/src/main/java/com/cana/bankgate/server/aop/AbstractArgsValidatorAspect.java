/**
 * Copyright © 2015 Cana. All rights reserved.
 */
package com.cana.bankgate.server.aop;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cana.bankgate.server.throwables.ValidateException;
import com.cana.vbam.common.annotations.NotBlank;

/**
 * The annotation must add on method in interface.
 * <p>
 * If add in implements,it can not be effective.
 * 
 * @author ducer
 */
@Aspect
public abstract class AbstractArgsValidatorAspect {

  private static Logger logger = LoggerFactory.getLogger(AbstractArgsValidatorAspect.class);

  @Pointcut
  public abstract void fieldValidate();

  @Before("fieldValidate()")
  public void validate(final JoinPoint pjp) throws Throwable {
    if (!Modifier.isPublic(pjp.getSignature().getModifiers())) // &Modifier.PUBLIC
      return;
    Object[] objs = pjp.getArgs();
    Method method = ((MethodSignature) pjp.getSignature()).getMethod();
    for (int index = 0; index < objs.length; index++) {
      validateMethodParam(objs[index], method, index);
      if (objs[index] != null && ! isBasicType(objs[index].getClass())) {
        String paramObjName = method.getParameterTypes()[index].getSimpleName();
        validateMethodParamObject(objs[index], paramObjName, true);
      }
    }
  }

  // 不递归只验证二级父类。预防爆栈
  private void validateMethodParamObject(Object obj, String paramObjName, boolean validateSuper)
      throws Throwable {
    validateObjectField(obj, obj.getClass(), paramObjName);
    if (validateSuper && superClassIsCanaCreate(obj.getClass())) {
      validateObjectField(obj, obj.getClass().getSuperclass(), paramObjName);
    }
    if (validateSuper && superClassIsCanaCreate(obj.getClass().getSuperclass())) {
      validateObjectField(obj, obj.getClass().getSuperclass().getSuperclass(), paramObjName);
    }
  }

  private void validateObjectField(Object obj, Class<?> clazz, String paramObjName)
      throws Throwable {
    Field[] fields = clazz.getDeclaredFields();
    for (Field field : fields) {
      field.setAccessible(true);
      NotBlank an = field.getAnnotation(NotBlank.class);
      if (an != null && isBlank(field.get(obj))) {
        propagate(paramObjName + "." + field.getName(), an.message());
      } else if (an != null && !isBasicType(field.getType())) {
        validateMethodParamObject(field.get(obj), paramObjName + "." + field.getName(), true);
      }
    }
  }
  
  private void validateMethodParam(Object obj, Method method, int index) {
    Annotation[][] ans = method.getParameterAnnotations();
    Class<?>[] paramTypes = method.getParameterTypes();
    if (ans[index].length >= 1) {
      for (int i = 0; i < ans[index].length; i++) {
        if ((ans[index][i] instanceof NotBlank) && isBlank(obj)) {
          NotBlank not = ((NotBlank) ans[index][i]);
          String name = StringUtils.isBlank(not.name()) ? paramTypes[index].getSimpleName() : not.name();
          propagate(name, not.message());
        }
      }
    }
  }

  private void propagate(String argName, String message) {// ((Throwable)an.exception().newInstance())
    StringBuilder bu = new StringBuilder();
    bu.append(StringUtils.isBlank(argName) ? "Argument " : argName).append(" ").append(message);
    logger.info(bu.toString());
    throw new ValidateException(bu.toString());
  }

  private boolean isBlank(Object obj) {
    return obj == null || StringUtils.isBlank(String.valueOf(obj));
  }
  
  private boolean isBasicType(Class<?> clazz) {
    return clazz.isPrimitive() || clazz.isAssignableFrom(String.class);
  }
  
  private boolean superClassIsCanaCreate(Class<?> clazz) {
    return clazz.getSuperclass() != null && clazz.getSuperclass().getPackage().getName().contains("com.cana");
  }
}
