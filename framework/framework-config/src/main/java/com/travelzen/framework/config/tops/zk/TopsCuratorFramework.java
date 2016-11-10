package com.travelzen.framework.config.tops.zk;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.CuratorWatcher;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.framework.recipes.locks.InterProcessReadWriteLock;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.framework.state.ConnectionStateListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.travelzen.framework.config.tops.util.TopsConfigReaderProps;
import com.travelzen.framework.core.dict.DataDict;
import com.travelzen.framework.core.util.RPIDLogger;
import com.travelzen.framework.core.util.StringUtil;
import com.travelzen.framework.core.util.TZUtil;
import com.travelzen.framework.core.util.TzkClient;
import com.travelzen.framework.logger.core.TZMarkers;

/**
 * 对curator做的标准化定制。该类实现了单例模式，保证一个进程只有一个zookeeper连接。
 * 
 * @author Jiangning
 * 
 */
public class TopsCuratorFramework {
	private static Logger logger = LoggerFactory.getLogger(TopsCuratorFramework.class);

	private CuratorFramework client = null;
	private CountDownLatch connectedLatch = new CountDownLatch(1);
	private static final String CHARACTER_SET_ENCODING_UTF8 = "UTF-8";
	
	public class Node{
		private String path;
		private String data;
		private List<Node> childNodes;
		
		public Node(String path) throws Exception{
			this.path = path;
			data = getDataForString(path);
		}
		
		public String getPath() {
			return path;
		}
		
		public String getData() {
			return data;
		}
		
		/**
		 * 获取所有的直接子节点
		 * @return
		 * @throws Exception
		 */
		public List<Node> getChildNodes() throws Exception{
			if(childNodes != null)
				return childNodes;
			childNodes = new ArrayList<>();
			for(String childNodePath : listPaths(path)){
				childNodes.add(new Node(path + "/" + childNodePath));
			}
			return childNodes;
		}
		
		/**
		 * 当前节点是否是子节点
		 * @return
		 * @throws Exception
		 */
		public boolean isLeafNode() throws Exception{
			return getChildNodes().size() == 0;
		}
		
	}

	private static class InstanceHolder {
		private static TopsCuratorFramework INSTANCE = new TopsCuratorFramework();
		static {
			INSTANCE.init();
		}
	}

	/**
	 * user this method instead of "YRCuratorFramework.InstanceHolder.INSTANCE"
	 * 
	 * @return
	 */
	public static TopsCuratorFramework getInstance() {
		return InstanceHolder.INSTANCE;
	}

	private void init() {
		//tag the zkSrc
		TzkClient.setZkSrc(TzkClient.ZkSrc.TopsCuratorFramework);

		// Thread.sleep(2000);
		RPIDLogger.info("start ....");
		try {
			waitUntilConnected();
		} catch (Exception e) {
			RPIDLogger.error("waitUntilConnected", e);
		}
		RPIDLogger.info("end ....");
	}

	public ZooKeeper getZookeeper() {

		if (client == null) {
			logger.warn("zookeeper obj null");
		}

		try {
			return getInstance().client.getZookeeperClient().getZooKeeper();
		} catch (Exception e) {
			logger.error(TZMarkers.p2, e.getLocalizedMessage(), e);
			return null;
		}
	}

	private TopsCuratorFramework() {
		String connectionString = TopsConfigReaderProps.getZookeeperService();
		if (StringUtil.isEmpty(connectionString))
			return;
		connectionString = connectionString.trim();
		try {
			RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
			//			retryPolicy = new RetryOneTime(1);

			//make canReadOnly to improve available
			client = CuratorFrameworkFactory.builder().canBeReadOnly(true)//
					.connectString(connectionString)//
					//					.sessionTimeoutMs(10 * 1000)//
					//					.connectionTimeoutMs(10 * 1000)//

					.sessionTimeoutMs(10 * 1000)//
					.connectionTimeoutMs(10 * 1000)//

					.retryPolicy(retryPolicy) //
					.build();//

			//			client = CuratorFrameworkFactory.newClient(connectionString, 10 * 1000, 10 * 1000, retryPolicy);

			try {
				addConnectionStateListener(new ConnectionStateListener() {

					@Override
					public void stateChanged(CuratorFramework client, ConnectionState newState) {
						if (newState == ConnectionState.CONNECTED) {
							RPIDLogger.info("zookeeper is connected");
							if (connectedLatch != null) {
								RPIDLogger.info("connectedLatch  is countDown");
								connectedLatch.countDown();
							}
						}
						if (newState == ConnectionState.LOST) {
							RPIDLogger.info("zookeeper is lost");
						}
						if (newState == ConnectionState.RECONNECTED) {
							RPIDLogger.info("zookeeper is reconnected");
						}
						if (newState == ConnectionState.SUSPENDED) {
							RPIDLogger.info("zookeeper is suspended");
						}

					}

				});
			} catch (Throwable thr) {
				RPIDLogger.error("", thr);
			}
			
			client.start();

			//may not  be call for zk down on startup
			TzkClient.setZkAndSignal(client.getZookeeperClient().getZooKeeper());

		} catch (Exception e) {
			RPIDLogger.error("创建CuratorFramework时出错", e);
			client = null;
		}
	}

	private void waitUntilConnected() {
		try {
			RPIDLogger.info("connectedLatch  is start ....");
			connectedLatch.await();
			RPIDLogger.info("connectedLatch  is end ....");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		connectedLatch = null;
	}

	/**
	 * 获取curatorFramework
	 * 
	 * @return
	 * @throws Exception
	 */
	public CuratorFramework getCuratorFramework() throws Exception {
		if (client == null)
			throw new IllegalStateException("没有可用的curatorFramework");
		else
			return InstanceHolder.INSTANCE.client;
	}

	/**
	 * 检查client
	 */
	public void assertClient() {
		if (TZUtil.isEmpty(client))
			throw new IllegalStateException("没有可用的curatorFramework");
	}

	/**
	 * 增加连接状态监听器
	 * 
	 * @param listener
	 * @throws Exception
	 */
	public void addConnectionStateListener(ConnectionStateListener listener) throws Exception {
		assertClient();
		if (TZUtil.isEmpty(listener))
			throw new IllegalStateException("listener is null");
		client.getConnectionStateListenable().addListener(listener);
		RPIDLogger.info("addConnectionStateListener");
	}

	public PathChildrenCache addPathChildrenCache(String nodePath) {
		assertClient();
		if (TZUtil.isEmpty(nodePath))
			throw new IllegalStateException("nodePath is null");
		PathChildrenCache pathChildrenCache = new PathChildrenCache(client, nodePath, true);
		return pathChildrenCache;
	}

	/**
	 * 删除某个节点
	 * 
	 * @param monitorNodePath
	 * @throws Exception
	 */
	public void deleteNode(String monitorNodePath) throws Exception {
		assertClient();
		if (TZUtil.isEmpty(monitorNodePath))
			throw new IllegalStateException("monitorNodePath is null");
		Stat stat = checkNodePath(monitorNodePath, null);
		if (stat != null) {
			logger.info("delete -- > {}", monitorNodePath);
			client.delete().forPath(monitorNodePath);
		}
	}

	/**
	 * 删除所有文件
	 */
	public void deleteNodesFromPath(String monitorNodePath) throws Exception {
		assertClient();
		if (TZUtil.isEmpty(monitorNodePath))
			throw new IllegalStateException("monitorNodePath is null");
		Stat stat = checkNodePath(monitorNodePath, null);
		if (stat != null) {
			List<String> listPaths = listPaths(monitorNodePath);
			if (!TZUtil.isEmpty(listPaths)) {
				for (String path : listPaths) {
					deleteNodesFromPath(monitorNodePath + File.separator + path);
				}
			}
			deleteNode(monitorNodePath);
		}
	}

	/**
	 * 创建某个节点 path: 路径 data: 数据 mode: 创建模式 PERSISTENT：创建后只要不删就永久存在
	 * 
	 * EPHEMERAL：会话结束年结点自动被删除
	 * 
	 * SEQUENTIAL：节点名末尾会自动追加一个10位数的单调递增的序号，同一个节点的所有子节点序号是单调递增的
	 * 
	 * PERSISTENT_SEQUENTIAL：结合PERSISTENT和SEQUENTIAL
	 * 
	 * EPHEMERAL_SEQUENTIAL：结合EPHEMERAL和SEQUENTIAL
	 * 
	 * @param monitorNodePath
	 * @param data
	 * @param mode
	 * @throws Exception
	 */
	public void createNode(String monitorNodePath, byte[] data, CreateMode mode) throws Exception {
		assertClient();
		if (TZUtil.isEmpty(monitorNodePath))
			throw new IllegalStateException("monitorNodePath is null");
		if (TZUtil.isEmpty(mode))
			throw new IllegalStateException("mode is null");
		client.create().creatingParentsIfNeeded().withMode(mode).forPath(monitorNodePath, data);
	}

	/**
	 * 创建一个临时路径的数据
	 * 
	 * @param nodepath
	 * @param dataStr
	 */
	public void createEphemeralDataNode(String nodepath, String dataStr) throws Exception {
		if (TZUtil.isEmpty(dataStr))
			throw new IllegalStateException("dataStr is null");
		createNode(nodepath, dataStr.getBytes(DataDict.CHARACTER_SET_ENCODING_UTF8), CreateMode.EPHEMERAL);
	}

	/**
	 * 创建一个永久路径的数据
	 * 
	 * @param nodepath
	 * @param dataStr
	 */
	public void createPersistentDataNode(String nodepath, String dataStr) throws Exception {
		if (null == dataStr)
			throw new IllegalStateException("dataStr is null");
		createNode(nodepath, dataStr.getBytes(DataDict.CHARACTER_SET_ENCODING_UTF8), CreateMode.PERSISTENT);
	}

	/**
	 * 获得数据
	 * 
	 * @throws Exception
	 */
	public byte[] getData(String nodepath) throws Exception {
		assertClient();
		if (TZUtil.isEmpty(nodepath))
			throw new IllegalStateException("nodepath is null");
		return client.getData().forPath(nodepath);
	}

	/**
	 * 获得字符串类型的数据
	 * 
	 * @throws Exception
	 */
	public String getDataForString(String nodepath) throws Exception {
		byte[] data = getData(nodepath);
		if (!TZUtil.isEmpty(data)) {
			return new String(data, DataDict.CHARACTER_SET_ENCODING_UTF8);
		}
		return null;
	}

	/**
	 * 检查节点 采用一个watcher
	 * 
	 * @param zkPath
	 * @param dataWatcher
	 * @return
	 * @throws Exception
	 */
	public Stat checkNodePath(String zkPath, CuratorWatcher curatorWatcher) throws Exception {
		assertClient();
		if (TZUtil.isEmpty(zkPath))
			throw new IllegalStateException("zkPath is null");
		if (TZUtil.isEmpty(curatorWatcher)) {
			return client.checkExists().forPath(zkPath);

		} else {
			return client.checkExists().usingWatcher(curatorWatcher).forPath(zkPath);
		}
	}

	/**
	 * 返回一个读写锁
	 * 
	 * @throws Exception
	 */
	public InterProcessMutex getInterProcessMutex(String readWriteLockPath) throws Exception {
		assertClient();
		if (TZUtil.isEmpty(readWriteLockPath))
			throw new IllegalStateException("readWriteLockPath is null");
		InterProcessReadWriteLock readWriteLock = new InterProcessReadWriteLock(client, readWriteLockPath);
		InterProcessMutex writeLock = readWriteLock.writeLock();

		if (writeLock.acquire(2, TimeUnit.SECONDS)) {
			return writeLock;
		}
		return null;
	}

	/**
	 * 检查节点 采用一个watcher
	 * 
	 * @param zkPath
	 * @param dataWatcher
	 * @return
	 * @throws Exception
	 */
	public List<String> listPaths(String zkPath) throws Exception {
		assertClient();
		if (TZUtil.isEmpty(zkPath))
			throw new IllegalStateException("zkPath is null");
		if (zkPath.length() > 2 && zkPath.endsWith("/")) {
			zkPath = zkPath.substring(0, zkPath.length() - 1);
		}
		List<String> list = client.getChildren().forPath(zkPath);
		if (!TZUtil.isEmpty(list)) {
			Collections.sort(list, new Comparator<String>() {

				@Override
				public int compare(String str1, String str2) {
					return str1.compareTo(str2);
				}

			});
		}
		return list;
	}
	
	/**
	 * 获取所有的叶子节点
	 * @param zkPath
	 * @return
	 * @throws Exception
	 */
	public List<Node> getAllLeafNodes(String zkPath) throws Exception{
		List<Node> allNodes = new ArrayList<>();
		Node parentNode = new Node(zkPath); 
		for(Node childNode : parentNode.getChildNodes()){
			if(childNode.isLeafNode())
				allNodes.add(childNode);
			else allNodes.addAll(getAllLeafNodes(childNode.getPath()));
		}
		return allNodes;
	}
	
	/**
	 * 检测路径是否存在
	 */
	public boolean exists(String zkPath) throws Exception{
		return client.checkExists().forPath(zkPath) != null; 	
	}
}
