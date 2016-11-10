package com.travelzen.framework.core.util;

import com.google.common.base.Function;

/**
 * 借鉴自Selenium
 * A generic interface for waiting until a condition is true or not null. The condition may take a
 * single argument of type .
 *
 * @param <F> the argument to pass to any function called
 */
public interface Wait<F> {

  /**
   * Implementations should wait until the condition evaluates to a value that is neither null nor
   * false. Because of this contract, the return type must not be Void.
   *
   * <p>
   * If the condition does not become true within a certain time (as defined by the implementing
   * class), this method will throw a non-specified {@link Throwable}. This is so that an
   * implementor may throw whatever is idiomatic for a given test infrastructure (e.g. JUnit4 would
   * throw {@link AssertionError}.
   *
   * @param <T> the return type of the method, which must not be Void
   * @param isTrue the parameter to pass to the {@link ExpectedCondition}
   */
  <T> T until(Function<? super F, T> isTrue);
}


