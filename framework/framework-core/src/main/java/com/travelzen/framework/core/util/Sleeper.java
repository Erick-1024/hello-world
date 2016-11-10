package com.travelzen.framework.core.util;

import java.util.concurrent.TimeUnit;

/**
 * 借鉴自Selenium
 * @author renshui
 *
 */
public interface Sleeper {

	  public static final Sleeper SYSTEM_SLEEPER = new Sleeper() {
	    public void sleep(Duration duration) throws InterruptedException {
	      Thread.sleep(duration.in(TimeUnit.MILLISECONDS));
	    }
	  };

	  /**
	   * Sleeps for the specified duration of time.
	   *
	   * @param duration How long to sleep.
	   * @throws InterruptedException If hte thread is interrupted while sleeping.
	   */
	  void sleep(Duration duration) throws InterruptedException;
	}

