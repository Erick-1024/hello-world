package com.travelzen.framework.config.tops.zk;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.data.Stat;

import com.github.zkclient.ZkClient;
import com.github.zkclient.ZkClientUtils;
import com.travelzen.framwork.config.exception.ZkConfReaderException;

public class QuickfailZkClient extends ZkClient {

	//	private final static Logger logger = LoggerFactory.getLogger(QuickfailZkClient.class);

	public QuickfailZkClient(String connectString, int sessionTimeout, int connectionTimeout) {
		super(connectString, sessionTimeout, connectionTimeout);
	}

	/**
	 *  throws TopsZkException , RuntimeException
	 */
	@Override
	protected byte[] readData(final String path, final Stat stat, final boolean watch) {
		try {
			byte[] data = _connection.readData(path, stat, watch);
			return data;
		} catch (KeeperException e) {
			throw new ZkConfReaderException(e);
		} catch (InterruptedException e) {
			throw new ZkConfReaderException(e);
		} catch (Exception e) {
			throw ZkClientUtils.convertToRuntimeException(e);
		}

	}

}
