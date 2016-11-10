package com.travelzen.framework.config.tops;

import static org.apache.commons.lang.StringUtils.isEmpty;
import static org.apache.commons.lang.StringUtils.trim;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooKeeper;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.zkclient.IZkStateListener;
import com.github.zkclient.exception.ZkException;
import com.google.common.collect.Maps;
import com.travelzen.framework.config.tops.TopsConfEnum.ConfLocation;
import com.travelzen.framework.config.tops.TopsConfEnum.ConfScope;
import com.travelzen.framework.config.tops.util.LocalPropertiesUtil;
import com.travelzen.framework.config.tops.util.TopsConfigPathResolver;
import com.travelzen.framework.config.tops.util.TopsConfigReaderProps;
import com.travelzen.framework.config.tops.zk.QuickfailZkClient;
import com.travelzen.framework.config.tops.zk.ZkData;
import com.travelzen.framework.core.config.PropertiesUtil;
import com.travelzen.framework.core.util.TzkClient;
import com.travelzen.framework.logger.core.TZMarkers;
import com.travelzen.framwork.config.exception.ZkConfReaderException;

public class FastConfigReader {

	private static Logger logger = LoggerFactory.getLogger(FastConfigReader.class);

	private static boolean onlyLocal = false;

	private static boolean localInfoInitialized = false;

	private QuickfailZkClient zkClient = null;

	public ZooKeeper getZookeeper() {
		return getInstance().zkClient.getZooKeeper();
	}

	//各类zk操作：connnect， read，write 操作的时候，等待的毫秒数
	int CONF_READER_CONNECTION_TIMEOUT = 10000;

	int CONF_READER_SESSION_TIMEOUT = 30000;

	DateTime lastTryConnectTime = new DateTime();

	//上次丢失连接, 尝试连接之后， 重新尝试从zk读最新文件的间隔时间（秒）
	int retryZkConnectIntervalSec = 30;

	Map<String, byte[]> cache = Maps.newConcurrentMap();

	private static class InstanceHolder {
		private static FastConfigReader INSTANCE = new FastConfigReader();
		static {
			INSTANCE.init();
		}
	}

	private FastConfigReader() {
	}

	public static FastConfigReader getInstance() {
		return InstanceHolder.INSTANCE;
	}

	String connectionString;

	KeeperState keeperState;

	private boolean needReconnect() {
		return keeperState == KeeperState.Disconnected || keeperState == KeeperState.Expired;
	}

	synchronized private void connectZkIfNeed() {

		if (zkClient != null) {
			return;
		}

		try {
			//  ZkTimeoutException InterruptedException
			lastTryConnectTime = new DateTime();
			zkClient = new QuickfailZkClient(connectionString, CONF_READER_SESSION_TIMEOUT, CONF_READER_CONNECTION_TIMEOUT);

			keeperState = KeeperState.SyncConnected;

			zkClient.subscribeStateChanges(new IZkStateListener() {

				@Override
				public void handleStateChanged(KeeperState state) throws Exception {

					if (state == KeeperState.AuthFailed) {
						logger.error(TZMarkers.p2, "KeeperState.AuthFailed");
					}

					if (state != KeeperState.SaslAuthenticated) {
						keeperState = state;
					}

				}

				@Override
				public void handleNewSession() throws Exception {
					// do nothing for  confRead purpose
				}

			});

			//may not  be call for zk down on startup
			TzkClient.setZkAndSignal(zkClient.getZooKeeper());

		} catch (Exception e) {
			//ZkTimeoutException will be catched here 
			// just   regard ZkTimeoutException as  KeeperState.Disconnected
			keeperState = KeeperState.Disconnected;
			processZkException(e);
		}
	}

	//	@SuppressWarnings("static-access")
	private void init() {

		//tag the zkSrc
		TzkClient.setZkSrc(TzkClient.ZkSrc.FastConfigReader);

		try {

			connectionString = trim(TopsConfigReaderProps.getZookeeperService());
			if (isEmpty(connectionString)) {
				logger.error(TZMarkers.p3, "connectionString should no be empty");
				return;
			}

			onlyLocal = TopsConfigReaderProps.isOnlyLocal();
			localInfoInitialized = true;

			connectZkIfNeed();

		} catch (Exception e) {
			//do nothing on any exception on startup procedure
			logger.error(TZMarkers.p1, e.getLocalizedMessage(), e);
		}

	}

	/**
	 * 检查client
	 * 
	 * throws   ZkTimeoutException 
	 */
	private void ensureZkClient() {

		/**
		 *  since the 'init' subrountine is only called once,     
		 *   if it failed , the 'ensureZkClient' subroutine will be abnormal ( specially,  localInfo reading error), 
		 *  thus, I add this  trace code here to  illustrate  the scenario 
		 */
		if (!localInfoInitialized) {
			logger.error(TZMarkers.p2, "localInfoInitialized=false");
			return;
		}

		if (zkClient == null || needReconnect()) {
			// enough sec is pass away, then retry  zkconnect
			if (lastTryConnectTime.plusSeconds(retryZkConnectIntervalSec).isBeforeNow()) {
				connectZkIfNeed();
			}
		}
	}

	/**
	 * translate all Exception into ZkConfReaderException
	 * @param e
	 */
	private void processZkException(Exception e) throws ZkConfReaderException {
		if (e instanceof ZkConfReaderException) {
			ZkConfReaderException te = (ZkConfReaderException) e;
			KeeperException ke = te.getKeeperException();

			switch (ke.code()) {

			case NONODE:
			case NODEEXISTS:
			case INVALIDACL:
			case AUTHFAILED:
			case NOTREADONLY:
			case NOAUTH:
			case OPERATIONTIMEOUT:
			case NOCHILDRENFOREPHEMERALS:
			case NOTEMPTY:
			case INVALIDCALLBACK:
				logger.warn("logic err:{}", ke.getLocalizedMessage());
				break;

			case SESSIONMOVED:
			case CONNECTIONLOSS:
			case SESSIONEXPIRED:
				logger.error(TZMarkers.p2, "network err:{}", ke.getLocalizedMessage());
				break;

			case SYSTEMERROR:
			case RUNTIMEINCONSISTENCY:
			case DATAINCONSISTENCY:
			case MARSHALLINGERROR:
			case UNIMPLEMENTED:
				logger.error(TZMarkers.p2, "zk server-side  err:{}", ke.getLocalizedMessage());
				break;

			case BADARGUMENTS:
			case APIERROR:
			case BADVERSION:
				logger.error(TZMarkers.p2, "code version   err:{}", ke.getLocalizedMessage());
				break;
			default:
				logger.warn("unknow zk  err:{}", ke.getLocalizedMessage());
				break;

			}
		}

		else if (e instanceof IOException) {
			logger.error(TZMarkers.p2, " io  err:{}", e.getLocalizedMessage());
		} else if (e instanceof ZkException) {
			logger.error(TZMarkers.p2, " zkclient  err:{}", e.getLocalizedMessage());
		} else {
			logger.error(TZMarkers.p2, " unkown  err:{}", e.getLocalizedMessage());
		}

		throw new ZkConfReaderException(e);

	}

	private ZkData getZkDataFromCacheAndLocalfile(String nodepath, ConfScope scope) {

		ZkData zkdata = new ZkData();

		if (cache.containsKey(nodepath)) {
			zkdata.data = cache.get(nodepath);
			zkdata.confLocation = TopsConfEnum.ConfLocation.CACHE;
			return zkdata;
		} else {
			// will throw ZkConfReaderException on IOException here
			return getZkDateFromLocal(nodepath, scope);
		}
	}

	/*
	 * throws :  ZkConfReaderException
	 */
	private ZkData getZkDateFromLocal(String nodepath, ConfScope scope) {
		ZkData zkdata = new ZkData();

		String localpath = TopsConfigPathResolver.getAppConfigPath(nodepath, scope, ConfLocation.LOCALHOST);

		try {
			zkdata.data = FileUtils.readFileToByteArray(new File(localpath));
			zkdata.confLocation = TopsConfEnum.ConfLocation.LOCALHOST;
			return zkdata;
		} catch (Exception e) {
			processZkException(e);
		}
		return zkdata;

	}

	/**
	 * 获得数据
	 * @throws IOException 
	 *  throws TopsZkException ,IllegalArgumentException , RuntimeException
	 */
	private ZkData getData(String nodepath, ConfScope scope) {

		ZkData zkdata = new ZkData();

		if (isEmpty(nodepath)) {
			throw new IllegalArgumentException("nodepath is null");
		}

		if (onlyLocal) {
			return getZkDateFromLocal(nodepath, scope);
		}

		try {

			//确保zkclient有效， 可能抛出连接超时
			ensureZkClient();

			String zkpath = TopsConfigPathResolver.getAppConfigPath(nodepath, scope, ConfLocation.ZK);

			if (keeperState == KeeperState.ConnectedReadOnly || keeperState == KeeperState.SyncConnected) {

				try {
					zkdata.data = zkClient.readData(zkpath);
					zkdata.confLocation = TopsConfEnum.ConfLocation.ZK;
					cache.put(nodepath, zkdata.data);
					return zkdata;
				} catch (Exception e) {
					processZkException(e);
				}
			} else {
				//已经知道zkClient不可用，不用再做尝试，直接读cache和本地文件
				return getZkDataFromCacheAndLocalfile(nodepath, scope);
			}
		} catch (ZkConfReaderException e) {
			//try to read data from cache or localfile on any ZkConfReaderException
			return getZkDataFromCacheAndLocalfile(nodepath, scope);
		}

		throw new ZkConfReaderException("unknown err");

	}

	//////

	/**
	 * 从本地进行读取
	 * 
	 * @param fileName
	 * @param key
	 * @param scope
	 * @return
	 */
	public static String getValueFromLocalFS(String fileName, String key, ConfScope scope) throws ZkConfReaderException {
		String appConfigPath = TopsConfigPathResolver.getAppConfigPath(fileName, scope, ConfLocation.LOCALHOST);
		String value = LocalPropertiesUtil.getPropertyFromLocalFile(appConfigPath, key, true, null);
		logger.info("getValueFromLocalFS, key : {} ,value : {}", key, value);
		return value;
	}

	public static String getPropertyValue(String fileName, String key, ConfScope scope) throws ZkConfReaderException {

		return getPropertyValue(fileName, key, scope, null);

	}

	/**
	 * 
	/**
	 *   try to get value from zk,  
	 *   if failed,   try to get from cache
	 *   if  no cache value, read value from local disk
	 *   
	 *   not  use cache if zk is alive
	 *   
	 * @param resourcePath
	 * @param key
	 * @param defaultValue
	 * @return
	 * @throws ZkConfReaderException 
	 */
	public static String getPropertyValue(String fileName, String key, ConfScope scope, String defaultValue) throws ZkConfReaderException {

		byte[] data = getInstance().getData(fileName, scope).data;
		return PropertiesUtil.getProperty(data).getProperty(key, defaultValue);

	}

	/**
	 * 
	 * 获得文件的变量
	 * 
	 * @param path
	 * @param scope
	 * @return
	 * @throws ZkConfReaderException 
	 */
	public static ZkData getContent(String fileName, ConfScope scope) throws ZkConfReaderException {
		return getInstance().getData(fileName, scope);
	}

}
