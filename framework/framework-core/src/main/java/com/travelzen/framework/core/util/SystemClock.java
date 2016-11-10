package com.travelzen.framework.core.util;

/**
 * 借鉴自Selenium
 * @author renshui
 *
 */
public class SystemClock implements Clock {

  public long laterBy(long durationInMillis) {
    return System.currentTimeMillis() + durationInMillis;
  }

  public boolean isNowBefore(long endInMillis) {
    return System.currentTimeMillis() < endInMillis;
  }

  public long now() {
    return System.currentTimeMillis();
  }
}

