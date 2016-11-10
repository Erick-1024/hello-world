package com.travelzen.mongo.morphia;

import java.net.UnknownHostException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;

import com.github.jmkgreen.morphia.Datastore;
import com.github.jmkgreen.morphia.EntityInterceptor;
import com.github.jmkgreen.morphia.Morphia;
import com.github.jmkgreen.morphia.mapping.DefaultMapper;
import com.github.jmkgreen.morphia.mapping.Mapper;
import com.github.jmkgreen.morphia.mapping.MapperOptions;
import com.github.jmkgreen.morphia.validation.ValidationExtension;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientURI;
import com.mongodb.ServerAddress;
import com.travelzen.mongo.TZMongoClientOptionsBuilder;
import com.travelzen.mongo.morphia.fixed.FixedDatastoreFactory;

/**
 * 用于生成DataStore的Factory类
 *
 * @author meng.wang
 */
public class DataStoreFactory {

	private Morphia morphia;
	// morphia bean到包路径
	private String[] packagePaths;
	private List<EntityInterceptor> interceptors;
	private TZDatastoreProvider tzDatastoreProvider;

	private TZMongoClientOptionsBuilder optionsBuilder;

	private static Map<String, MongoClient> mongoClientMap = new ConcurrentHashMap<>();

	public void setPackagePaths(String[] packagePaths) {
		this.packagePaths = packagePaths;
	}
	
	public void setInterceptors(List<EntityInterceptor> interceptors) {
		this.interceptors = interceptors;
	}

	public void setOptionsBuilder(TZMongoClientOptionsBuilder optionsBuilder) {
		this.optionsBuilder = optionsBuilder;
	}

	// 初始化,扫描mongo entity 路径泛型
	@PostConstruct
	public void init() {
		tzDatastoreProvider = new TZDatastoreProvider();
		MapperOptions mo = new MapperOptions();
		mo.datastoreProvider = tzDatastoreProvider;
		Mapper mp = new DefaultMapper(mo);
		morphia = new Morphia(mp);
		morphia.setDatastoreFactory(new FixedDatastoreFactory());
		if (packagePaths != null && packagePaths.length > 0) {
			for (String entityPath : packagePaths) {
				morphia.mapPackage(entityPath, true);
			}
		}
		if (interceptors != null && interceptors.size() > 0) {
			for (EntityInterceptor ei : interceptors) {
				morphia.getMapper().addInterceptor(ei);
			}
		}
		// 验证
		new ValidationExtension(morphia);
	}

	/**
	 * ensure index and caps
	 *
	 * @param mon
	 * @param dbName
	 * @param user
	 * @param pw
	 * @return
	 */
	public Datastore createDatastore(Mongo mon, String dbName, String user, char[] pw) {
		Datastore datastore = null;
		if (user == null || user.length() == 0 || pw == null || pw.length == 0) {
			datastore = morphia.createDatastore(mon, dbName);
		} else {
			datastore = morphia.createDatastore(mon, dbName, user, pw);
		}
		return postProcessDatastoreCreation(datastore);
	}

	public Datastore createDatastore(String host, int port, String dbName,
			String user, char[] pw, TZMongoClientOptionsBuilder builder) throws UnknownHostException {
		MongoClientOptions options = builder.build();
		ServerAddress svAddr = new ServerAddress(host, port);
		Mongo mongo = new MongoClient(svAddr, options);
		return createDatastore(mongo, dbName, user, pw);
	}

	public Datastore createDatastore(String host, int port, String dbName,
			String user, char[] password) throws UnknownHostException {
		ServerAddress svAddr = new ServerAddress(host, port);
		Mongo mongo = null;
		if (optionsBuilder != null) {
			MongoClientOptions options = optionsBuilder.build();
			mongo = new MongoClient(svAddr, options);
		} else {
			mongo = new MongoClient(svAddr);
		}
		return createDatastore(mongo, dbName, user, password);
	}

	private Datastore postProcessDatastoreCreation(Datastore datastore) {
		datastore.getMapper().getConverters().addConverter(JodaTimeConverter.class);
		datastore.ensureIndexes();
		datastore.ensureCaps();
		tzDatastoreProvider.set(datastore);
		return datastore;
	}

	public Datastore createDatastore(String uri) throws UnknownHostException {
		MongoClientURI mcUri = null;
		if (optionsBuilder != null) {
			mcUri = new MongoClientURI(uri, optionsBuilder.getOptionsBuilder());
		} else {
			mcUri = new MongoClientURI(uri);
		}
		MongoClient mc = mongoClientMap.get(uri);
		if (mc == null) {
			mc = new MongoClient(mcUri);
		}
		Datastore datastore = morphia.createDatastore(mc, mcUri.getDatabase());
		return postProcessDatastoreCreation(datastore);
	}

}
