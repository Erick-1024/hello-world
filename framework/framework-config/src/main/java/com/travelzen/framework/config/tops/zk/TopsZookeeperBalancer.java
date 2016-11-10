package com.travelzen.framework.config.tops.zk;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

import org.apache.commons.lang3.StringUtils;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.framework.state.ConnectionStateListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.travelzen.framework.core.dict.DataDict;
import com.travelzen.framework.core.util.RPIDLogger;
import com.travelzen.framework.core.util.StringUtil;

public class TopsZookeeperBalancer {
	private static Logger log = LoggerFactory.getLogger(TopsZookeeperBalancer.class);
	private static Map<String, Map<String, String>> rpcUrls = new HashMap<String, Map<String, String>>();
	private static CountDownLatch waitingForAsyncGetRpcAddressCompleteLatch = new CountDownLatch(1);

	private static List<String> getRpcAdressFromZk(final String rpcServiceName, String YRNS_PREFIX, boolean needCache) {
		if (needCache) {
			return getRpcAdressFromZk(rpcServiceName, YRNS_PREFIX);
		} else {
			return getRpcAdressFromZkNoCache(rpcServiceName, YRNS_PREFIX);
		}
	}

	public static List<String> getRpcAddress(final String rpcServiceName, String YRNS_PREFIX) {
		return getRpcAddress(rpcServiceName, YRNS_PREFIX, true);
	}

	/**
	 * 返回服务的rpc地址，若没有取到则返回null
	 * 
	 * @param rpcServiceName
	 * @return
	 */
	public static List<String> getRpcAddress(final String rpcServiceName, String YRNS_PREFIX, boolean needCache) {

		log.info("获取rpc地址， serviceName=" + rpcServiceName + ", YRNS_PREFIX=" + YRNS_PREFIX);

		if (StringUtils.isBlank(YRNS_PREFIX) || StringUtils.isBlank(rpcServiceName)) {
			return null;
		}

		List<String> availableServiceNodes = getRpcAdressFromZk(rpcServiceName, YRNS_PREFIX, needCache);
		if (availableServiceNodes == null) {
			return null;
		}
		String OFFLINE_YRNS_PREFIX = "/OFFLINE" + YRNS_PREFIX;
		String offline_rpcSErviceName = rpcServiceName + "_offline";

		List<String> excludeServiceNodes = getRpcAdressFromZk(offline_rpcSErviceName, OFFLINE_YRNS_PREFIX, needCache);
		if (excludeServiceNodes != null) {
			availableServiceNodes.removeAll(excludeServiceNodes);
		}
		return availableServiceNodes;
	}

	/**
	 * 
	 * @param rpcServiceName
	 * @param YRNS_PREFIX
	 * @return
	 */
	private static List<String> getRpcAdressFromZkNoCache(final String rpcServiceName, String YRNS_PREFIX) {
		if (StringUtils.isBlank(YRNS_PREFIX)) {
			return null;
		}
		YRNS_PREFIX = YRNS_PREFIX.toUpperCase();
		if (TopsCuratorFramework.getInstance() == null)
			return null;

		try {

			Map<String, String> rpcAddressKeyedByZkPath = new ConcurrentHashMap<>();

			String rootZKPath = getRpcServiceRootZKPath(YRNS_PREFIX, rpcServiceName);
			for (TopsCuratorFramework.Node node : TopsCuratorFramework.getInstance().getAllLeafNodes(rootZKPath)) {
				if (isRpcAddressNode(node) && StringUtils.isNotBlank(node.getPath()) && StringUtils.isNotBlank(node.getData())) {
					rpcAddressKeyedByZkPath.put(node.getPath(), node.getData());
				}
			}

			return new ArrayList<String>(rpcAddressKeyedByZkPath.values());
		} catch (Throwable thr) {
			RPIDLogger.error("", thr);
			return null;
		}

	}

	/**
	 * 
	 * @param rpcServiceName
	 * @param YRNS_PREFIX
	 * @return
	 */
	private static List<String> getRpcAdressFromZk(final String rpcServiceName, String YRNS_PREFIX) {
		if (StringUtils.isBlank(YRNS_PREFIX)) {
			return null;
		}
		YRNS_PREFIX = YRNS_PREFIX.toUpperCase();
		if (TopsCuratorFramework.getInstance() == null)
			return null;
		if (rpcUrls.containsKey(rpcServiceName)) {
			if (rpcUrls.get(rpcServiceName) != null)
				return new ArrayList<String>(rpcUrls.get(rpcServiceName).values());
			else
				return null;
		}
		synchronized (TopsCuratorFramework.class) {
			if (rpcUrls.containsKey(rpcServiceName)) {
				if (rpcUrls.get(rpcServiceName) != null)
					return new ArrayList<String>(rpcUrls.get(rpcServiceName).values());
				else
					return null;
			}
			try {
				Map<String, String> rpcAddressKeyedByZkPath = new ConcurrentHashMap<>();
				rpcUrls.put(rpcServiceName, rpcAddressKeyedByZkPath);
				//startAsyncUpdateRpcAddressFromZK(rpcServiceName, YRNS_PREFIX);
				//waitingForAsyncGetRpcAddressCompleteLatch.await(3, TimeUnit.SECONDS);
				syncGetRpcAddressFromZK(rpcServiceName, YRNS_PREFIX);
				rpcUrls.put(rpcServiceName, rpcAddressKeyedByZkPath);
				return new ArrayList<String>(rpcAddressKeyedByZkPath.values());
			} catch (Throwable thr) {
				RPIDLogger.error("", thr);
				rpcUrls.put(rpcServiceName, null);
				return null;
			}

		}
	}

	private static Map<String, String> syncGetRpcAddressFromZK(final String rpcServiceName, String YRNS_PREFIX) throws Exception {
		Map<String, String> rpcAddressKeyedByZkPath = rpcUrls.get(rpcServiceName);
		String rootZKPath = getRpcServiceRootZKPath(YRNS_PREFIX, rpcServiceName);
		for (TopsCuratorFramework.Node node : TopsCuratorFramework.getInstance().getAllLeafNodes(rootZKPath)) {
			if (isRpcAddressNode(node) && StringUtils.isNotBlank(node.getPath()) && StringUtils.isNotBlank(node.getData())) {
				rpcAddressKeyedByZkPath.put(node.getPath(), node.getData());
			}
		}
		return rpcAddressKeyedByZkPath;
	}

	public static String getRpcServiceRootZKPath(String YRNS_PREFIX, String rpcServiceName) {
		return YRNS_PREFIX + "/" + rpcServiceName;
	}

	private static boolean isRpcAddressNode(TopsCuratorFramework.Node node) {
		String path = StringUtils.trimToEmpty(node.getPath());
		return path.contains("rpc/");
	}

	@SuppressWarnings("unused")
	private static void startAsyncUpdateRpcAddressFromZK(final String rpcServiceName, String YRNS_PREFIX) throws Exception {
		PathChildrenCache shardListener = TopsCuratorFramework.getInstance().addPathChildrenCache(getRpcServiceRootZKPath(YRNS_PREFIX, rpcServiceName));
		shardListener.getListenable().addListener(new PathChildrenCacheListener() {
			@Override
			public void childEvent(CuratorFramework client, PathChildrenCacheEvent event) throws Exception {
				if (event.getType() == PathChildrenCacheEvent.Type.CHILD_ADDED) {
					String shardNodePath = StringUtil.trim(event.getData().getPath());

					PathChildrenCache replicaListener = TopsCuratorFramework.getInstance().addPathChildrenCache(shardNodePath + "/rpc");
					replicaListener.getListenable().addListener(new PathChildrenCacheListener() {
						@Override
						public void childEvent(CuratorFramework client, PathChildrenCacheEvent event) throws Exception {
							if (event.getType() == PathChildrenCacheEvent.Type.CHILD_ADDED || event.getType() == PathChildrenCacheEvent.Type.CHILD_UPDATED) {
								String replicaNodePath = StringUtil.trim(event.getData().getPath());
								String replicaRpcUrl = StringUtil.trim(new String(event.getData().getData(), DataDict.CHARACTER_SET_ENCODING_UTF8));
								rpcUrls.get(rpcServiceName).put(replicaNodePath, replicaRpcUrl);
								waitingForAsyncGetRpcAddressCompleteLatch.countDown();
							} else if (event.getType() == PathChildrenCacheEvent.Type.CHILD_REMOVED) {
								String replicaNodePath = StringUtil.trim(event.getData().getPath());
								rpcUrls.get(rpcServiceName).remove(replicaNodePath);
							}
						}
					});
					replicaListener.start();
				}
			}

		});

		shardListener.start();
	}

	/**
	 * 注册rpc服务的url
	 * 
	 * @param rpcUrl
	 */
	public static void registerRpc(final String rpcUrl, final String YRNS_PREFIX, final String serviceName, final String shardId, final String replicaId) throws Exception {
		try {
			if (StringUtils.isBlank(YRNS_PREFIX) || StringUtils.isBlank(serviceName) || StringUtils.isBlank(shardId) || StringUtils.isBlank(replicaId))
				throw new Exception("cannot identify (serviceName, shardId, replicaId)");
			log.info("regist {} to {}/{}/{}/{}", rpcUrl, YRNS_PREFIX, serviceName, shardId, replicaId);
			createRpcNodeAtZK(rpcUrl, YRNS_PREFIX.toUpperCase(), serviceName, shardId, replicaId);
			TopsCuratorFramework.getInstance().addConnectionStateListener(new ConnectionStateListener() {

				@Override
				public void stateChanged(CuratorFramework client, ConnectionState newState) {
					if (newState == ConnectionState.RECONNECTED) {
						try {
							log.info("stateChanged");
							createRpcNodeAtZK(rpcUrl, YRNS_PREFIX.toUpperCase(), serviceName, shardId, replicaId);
						} catch (Exception e) {
							log.error("", e);
						}
					}

				}

			});
		} catch (Exception e) {
			throw e;
		}

	}

	private static void createRpcNodeAtZK(String rpcUrl, final String YRNS_PREFIX, final String serviceName, final String shardId, final String replicaId) throws Exception {
		String rpcNodePath = YRNS_PREFIX + "/" + serviceName + "/" + shardId + "/rpc/" + replicaId;
		// 在前一个session超时前，创建一个临时节点可能会失败，因为前一个session创建的同一个节点可能还存在。保险起见，先删除，后创建。
		try {
			TopsCuratorFramework.getInstance().deleteNode(rpcNodePath);
		} catch (Throwable thr) {

		}
		TopsCuratorFramework.getInstance().createEphemeralDataNode(rpcNodePath, rpcUrl);
		RPIDLogger.info("注册rpc地址:" + rpcUrl + "到zookeeper路径:" + rpcNodePath + "成功");

		String offlineNodePath = "/OFFLINE" + YRNS_PREFIX + "/"+ serviceName + "_offline";
		if(!TopsCuratorFramework.getInstance().exists(offlineNodePath)) {
			TopsCuratorFramework.getInstance().createPersistentDataNode(offlineNodePath, "");
			RPIDLogger.info("为服务：" + serviceName + "添加offline目录成功");
		}
	}

	/**
	 * 注册监控服务的url
	 * 
	 * @param monitorUrl
	 */
	public static void registerMonitor(final String monitorUrl, final String YRNS_PREFIX, final String serviceName, final String shardId, final String replicaId) throws Exception {
		try {
			if (StringUtils.isBlank(YRNS_PREFIX) || StringUtils.isBlank(serviceName) || StringUtils.isBlank(shardId) || StringUtils.isBlank(replicaId))
				throw new Exception("cannot identify (serviceName, shardId, replicaId)");
			createMonitorNodeAtZK(monitorUrl, YRNS_PREFIX, serviceName, shardId, replicaId);
			TopsCuratorFramework.getInstance().addConnectionStateListener(new ConnectionStateListener() {

				@Override
				public void stateChanged(CuratorFramework client, ConnectionState newState) {
					if (newState == ConnectionState.RECONNECTED) {
						try {
							createMonitorNodeAtZK(monitorUrl, YRNS_PREFIX, serviceName, shardId, replicaId);
						} catch (Exception e) {
							RPIDLogger.error("", e);
						}
					}

				}

			});
		} catch (Exception e) {
			throw e;
		}

	}

	private static void createMonitorNodeAtZK(String monitorUrl, String YRNS_PREFIX, String serviceName, String shardId, String replicaId) throws Exception {
		if (StringUtils.isBlank(YRNS_PREFIX) || StringUtils.isBlank(serviceName) || StringUtils.isBlank(shardId) || StringUtils.isBlank(replicaId))
			throw new Exception("cannot identify (serviceName, shardId, replicaId)");
		String monitorNodePath = YRNS_PREFIX + "/" + serviceName + "/" + shardId + "/monitor/" + replicaId;
		// 在前一个session超时前，创建一个临时节点可能会失败，因为前一个session创建的同一个节点可能还存在。保险起见，先删除，后创建。
		try {
			TopsCuratorFramework.getInstance().deleteNode(monitorNodePath);
		} catch (Throwable thr) {

		}
		TopsCuratorFramework.getInstance().createEphemeralDataNode(monitorNodePath, monitorUrl);
		log.info("注册monitor地址:" + monitorUrl + "到zookeeper路径:" + monitorNodePath + "成功");
	}
}
