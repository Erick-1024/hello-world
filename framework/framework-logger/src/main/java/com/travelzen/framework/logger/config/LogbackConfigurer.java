package com.travelzen.framework.logger.config;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.impl.StaticLoggerBinder;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.classic.selector.ContextSelector;
import ch.qos.logback.classic.util.ContextInitializer;
import ch.qos.logback.classic.util.ContextSelectorStaticBinder;
import ch.qos.logback.core.joran.spi.JoranException;
import ch.qos.logback.core.status.InfoStatus;
import ch.qos.logback.core.status.StatusListener;
import ch.qos.logback.core.status.StatusListenerAsList;
import ch.qos.logback.core.status.StatusManager;

/**
 * logback configigurer
 *
 * @author dylan
 */
@Deprecated
public class LogbackConfigurer {
	private static String EMPTY = "";
	private static LogbackConfigurer instance;

	private LogbackConfigurer() {
	}

	public static LogbackConfigurer getInstance() {
		if (instance == null) {
			instance = new LogbackConfigurer();
		}
		return instance;
	}

	/**
	 * 通过系统属性配置设置logback配置文件位置
	 *
	 * @param url
	 */
	public static void setSystemLoggerURL(String url) {
		System.setProperty(ContextInitializer.CONFIG_FILE_PROPERTY, url);
	}

	public static LoggerContext getLoggerContext() {
		return (LoggerContext) StaticLoggerBinder.getSingleton()
				.getLoggerFactory();
	}

	/**
	 * Shut down Logback.
	 * <p/>
	 * This isn't strictly necessary, but recommended for shutting down
	 * logback in a scenario where the host VM stays alive (for example, when
	 * shutting down an application in a J2EE environment).
	 */
	public static void shutdownLogging() {
		ContextSelector selector = ContextSelectorStaticBinder.getSingleton().getContextSelector();
		LoggerContext loggerContext = selector.getLoggerContext();
		String loggerContextName = loggerContext.getName();
		LoggerContext context = selector.detachLoggerContext(loggerContextName);
		context.reset();
	}

	/**
	 * 加载默认的logback配置文件
	 *
	 * @throws ch.qos.logback.core.joran.spi.JoranException
	 */
	public void reloadDefaultConfiguration() throws JoranException {
		ContextInitializer ci = new ContextInitializer(getLoggerContext());
		URL url = ci.findURLOfDefaultConfigurationFile(true);
		reloadByURL(url);
	}

	/**
	 * 加载默认的logback配置文件
	 *
	 * @param removeSystemConfig
	 * @throws ch.qos.logback.core.joran.spi.JoranException
	 */
	public void reloadDefaultConfiguration(boolean removeSystemConfig) throws JoranException {
		setSystemLoggerURL(null);
		ContextInitializer ci = new ContextInitializer(getLoggerContext());
		URL url = ci.findURLOfDefaultConfigurationFile(true);
		reloadByURL(url);
	}

	public void reloadByFileName(String fileName) throws JoranException,
			FileNotFoundException {
		File f = new File(fileName);
		if (f.exists() && f.isFile()) {
			URL url;
			try {
				url = f.toURI().toURL();
				reloadByURL(url);
			} catch (MalformedURLException e) {
				throw new RuntimeException(
						"Unexpected MalformedURLException occured. See nexted cause.",
						e);
			}

		} else {
			String errMsg = "Could not find [" + fileName + "]";
			addInfo(null, errMsg);
			throw new FileNotFoundException(errMsg);
		}
	}

	public void reloadByURL(URL url) throws JoranException {
		LoggerContext loggerContext = getLoggerContext();
		StatusListenerAsList statusListenerAsList = new StatusListenerAsList();

		addStatusListener(loggerContext, statusListenerAsList);
		addInfo(loggerContext, "Resetting context: " + loggerContext.getName());
		loggerContext.reset();
		// after a reset the statusListenerAsList gets removed as a listener
		addStatusListener(loggerContext, statusListenerAsList);

		try {
			JoranConfigurator configurator = new JoranConfigurator();
			configurator.setContext(loggerContext);
			configurator.doConfigure(url);
			addInfo(loggerContext, "Context: " + loggerContext.getName() + " reloaded.");
		} finally {
			removeStatusListener(loggerContext, statusListenerAsList);
		}
	}

	public void reloadByInputStream(InputStream inputStream) throws JoranException {
		LoggerContext loggerContext = getLoggerContext();
		StatusListenerAsList statusListenerAsList = new StatusListenerAsList();

		addStatusListener(loggerContext, statusListenerAsList);
		addInfo(loggerContext, "Resetting context: " + loggerContext.getName());
		loggerContext.reset();
		// after a reset the statusListenerAsList gets removed as a listener
		addStatusListener(loggerContext, statusListenerAsList);

		try {
			JoranConfigurator configurator = new JoranConfigurator();
			configurator.setContext(loggerContext);
			configurator.doConfigure(inputStream);
			addInfo(loggerContext, "Context: " + loggerContext.getName() + " reloaded.");
		} finally {
			removeStatusListener(loggerContext, statusListenerAsList);
		}
	}

	private void addInfo(LoggerContext loggerContext, String info) {
		StatusManager sm = loggerContext.getStatusManager();
		if (sm != null) {
			sm.add(new InfoStatus(info, this));
		}
	}

	static void addStatusListener(LoggerContext loggerContext,
	                              StatusListener statusListener) {
		StatusManager sm = loggerContext.getStatusManager();
		sm.add(statusListener);
	}

	static void removeStatusListener(LoggerContext loggerContext,
	                                 StatusListener statusListener) {
		StatusManager sm = loggerContext.getStatusManager();
		sm.add(statusListener);
	}

	/**
	 * 设置日志级别
	 *
	 * @param loggerName
	 * @param levelStr
	 */
	public void setLoggerLevel(String loggerName, String levelStr) {
		if (loggerName == null) {
			return;
		}
		if (levelStr == null) {
			return;
		}
		loggerName = loggerName.trim();
		levelStr = levelStr.trim();

		LoggerContext lc = getLoggerContext();
		addInfo(lc, "Trying to set level " + levelStr + " to logger " + loggerName);

		Logger logger = lc.getLogger(loggerName);
		if ("null".equalsIgnoreCase(levelStr)) {
			logger.setLevel(null);
		} else {
			Level level = Level.toLevel(levelStr, null);
			if (level != null) {
				logger.setLevel(level);
			}
		}
	}

	/**
	 * 获取日志级别
	 *
	 * @param loggerName
	 * @return
	 */
	public String getLoggerLevel(String loggerName) {
		if (loggerName == null) {
			return EMPTY;
		}

		loggerName = loggerName.trim();

		LoggerContext lc = getLoggerContext();
		Logger logger = lc.exists(loggerName);
		if (logger != null && logger.getLevel() != null) {
			return logger.getLevel().toString();
		} else {
			return EMPTY;
		}
	}

	/**
	 * 获取日志级别
	 *
	 * @param loggerName
	 * @return
	 */
	public String getLoggerEffectiveLevel(String loggerName) {
		if (loggerName == null) {
			return EMPTY;
		}

		loggerName = loggerName.trim();

		LoggerContext lc = getLoggerContext();
		Logger logger = lc.exists(loggerName);
		if (logger != null) {
			return logger.getEffectiveLevel().toString();
		} else {
			return EMPTY;
		}
	}

	public List<String> getLoggerList() {
		List<String> strList = new ArrayList<String>();
		Iterator<Logger> it = getLoggerContext().getLoggerList().iterator();
		while (it.hasNext()) {
			Logger log = it.next();
			strList.add(log.getName());
		}
		return strList;
	}
}
