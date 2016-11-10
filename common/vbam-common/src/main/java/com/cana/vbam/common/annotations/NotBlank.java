/**
 * Copyright © 2015 Cana. All rights reserved.
 */
package com.cana.vbam.common.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Can not be blank,if blank throws default RuntimeException.
 * <p>
 * Attention: Only support using on interface now.
 * <p>
 * You can use this annotation on method parameters and the field of the parameter Object.
 * <p>
 * Such as:
 * 
 * <pre>
 * public void Test(@NotBlank String arg1,@NotBlank VO arg2)
 * public class VO{
 * 	&#64;NotBlank
 * 	public String v;
 * }
 * </pre>
 * 
 * @author ducer
 */
@Inherited
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface NotBlank {

  /**
   * Method parameter can not be got,you can edit it this.
   */
  public String name() default "";

  /**
   * Using this value to writing log and throw Exception when parameter is blank.
   */
  public String message() default "can not be blank.";

  /**
   * Type of Exception.
   */
  public Class<?> exception() default RuntimeException.class;
}
