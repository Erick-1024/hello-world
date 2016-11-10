package com.travelzen.framework.config.tops.zk;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.curator.utils.CloseableUtils;
import org.apache.curator.x.discovery.ServiceDiscovery;
import org.apache.curator.x.discovery.ServiceDiscoveryBuilder;
import org.apache.curator.x.discovery.ServiceInstance;
import org.apache.curator.x.discovery.UriSpec;
import org.apache.curator.x.discovery.details.JsonInstanceSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Preconditions;


/**
 * 服务的结构如下：
 * base path
       |_______ service A name
                    |__________ instance 1 id --> (serialized ServiceInstance)
                    |__________ instance 2 id --> (serialized ServiceInstance)
                    |__________ ...
       |_______ service B name
                    |__________ instance 1 id --> (serialized ServiceInstance)
                    |__________ instance 2 id --> (serialized ServiceInstance)
                    |__________ ...
       |_______ ...
 * @author renshui
 *
 */
public class TopsServiceDiscovery {
	
	private Logger logger = LoggerFactory.getLogger(TopsServiceDiscovery.class); 
	
	private static Map<String, ServiceDiscovery<InstanceDetails>> serviceDiscoveries = new HashMap<>();
	
	/**
	 * 注册RPC服务,
	 * @param basePath
	 * @param serviceName
	 * @param rpcUrl
	 * @throws Exception
	 */
	public static synchronized void registerRpc(final String basePath, final String serviceName, final String rpcUrl) throws Exception {
		Preconditions.checkArgument(StringUtils.isNotEmpty(basePath), "basePath不能为空");
		Preconditions.checkArgument(StringUtils.isNotEmpty(serviceName), "serviceName不能为空");
		Preconditions.checkArgument(StringUtils.isNotEmpty(rpcUrl), "rpcUrl不能为空");
		UriSpec uriSpec = new UriSpec();
		ServiceInstance<InstanceDetails> serviceInstance = ServiceInstance.<InstanceDetails>builder()
				.name(serviceName)
				.payload(new InstanceDetails(rpcUrl))
				.uriSpec(uriSpec)
				.build();
		JsonInstanceSerializer<InstanceDetails> serializer = new JsonInstanceSerializer<InstanceDetails>(InstanceDetails.class);
		ServiceDiscovery<InstanceDetails> serviceDiscovery = ServiceDiscoveryBuilder.builder(InstanceDetails.class)
				.client(TopsCuratorFramework.getInstance().getCuratorFramework())
				.basePath(basePath)
				.serializer(serializer)
				.thisInstance(serviceInstance)
				.build();
		serviceDiscovery.start();
		serviceDiscoveries.put(serviceName, serviceDiscovery);
	}
	
	/**
	 * 
	 * @param basePath
	 * @param serviceName
	 * @return
	 * @throws Exception
	 */
	public static synchronized List<String> getRpcAddress(final String basePath, final String serviceName) throws Exception{
		Preconditions.checkArgument(StringUtils.isNotEmpty(basePath), "basePath不能为空");
		Preconditions.checkArgument(StringUtils.isNotEmpty(serviceName), "serviceName不能为空");
		ServiceDiscovery<InstanceDetails> serviceDiscovery = serviceDiscoveries.get(serviceName);
		if(serviceDiscovery == null){
			JsonInstanceSerializer<InstanceDetails> serializer = new JsonInstanceSerializer<InstanceDetails>(InstanceDetails.class);
			serviceDiscovery = ServiceDiscoveryBuilder.builder(InstanceDetails.class)
					.client(TopsCuratorFramework.getInstance().getCuratorFramework())
					.basePath(basePath)
					.serializer(serializer)
					.build();
			serviceDiscovery.start();
			serviceDiscoveries.put(serviceName, serviceDiscovery);
		}
		List<String> rpcAddresses = new ArrayList<>();
		Collection<ServiceInstance<InstanceDetails>> instances = serviceDiscovery.queryForInstances(serviceName);
		for ( ServiceInstance<InstanceDetails> instance : instances ) {
			rpcAddresses.add(instance.getPayload().getRpcUrl());
		}
		return rpcAddresses;
	}
	
	/**
	 * 关闭服务
	 */
	public static void close(){
		for(ServiceDiscovery<InstanceDetails> serviceDiscovery : serviceDiscoveries.values())
			CloseableUtils.closeQuietly(serviceDiscovery);
	}
}
