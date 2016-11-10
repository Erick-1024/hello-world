package com.travelzen.framework.logger.core;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.annotation.PostConstruct;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;
import ch.qos.logback.core.util.StatusPrinter;

import com.travelzen.framework.core.util.TzkClient;

public class LogbackLoader {
	//节点路径
	//private String zkNodePath = "/opt/conf/tz-data/springMVC/192.168.163.184/"; 
	//zookeeper的URL和端口号
	//	private String zookeeperUrl = "localhost:2181";
	private static Logger logger = LoggerFactory.getLogger(LogbackLoader.class);

	public LogbackLoader() {
		super();
	}

	/**
	 * 从zookeeper上加载logbank.xml文件
	 * 
	 * @throws ServletException
	 */
	@PostConstruct
	public void loaderLogback() {

		LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
		JoranConfigurator configurator = new JoranConfigurator();
		configurator.setContext(lc);
		lc.reset();
		InputStream inputStream = null;
		try {
			inputStream = getInputStreamFromZK("logback.xml");
			configurator.doConfigure(inputStream);
		} catch (JoranException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (KeeperException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		StatusPrinter.printInCaseOfErrorsOrWarnings(lc);
		System.out.println("................loading logback..................");
	}

	/**
	 * 从zookeeper上获取节点输入流
	 * 
	 * @param fileName
	 *            节点名称
	 * @return InputStream
	 * @throws IOException
	 * @throws KeeperException
	 * @throws InterruptedException
	 */
	public InputStream getInputStreamFromZK(String fileName) throws IOException, KeeperException, InterruptedException {

		// 创建监听器 监听zookeeper节点上的数据的变动
		new Watcher() {
			@Override
			public void process(WatchedEvent event) {
				logger.info("ZK节点{}发生改变。 改变类型{}", event.getPath(), event.getType());
				if (event.getType().equals(EventType.NodeDataChanged)) {
					loaderLogback();

					System.out.println("节点数据改变了。。");
				}
			}
		};
		// 创建zookeeper的连接
		//	ZooKeeper zk = new ZooKeeper(zookeeperUrl, 2000, watcher);

		// 读取zookeeper节点上的数据，并将数据转化成输入流。
		InputStream is = null;
		try {
			is = new ByteArrayInputStream(TzkClient.readData("logback.xml"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return is;
	}
}
