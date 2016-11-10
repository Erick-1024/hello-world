package com.cana.message.mongo.core;

import java.util.List;

import org.springframework.beans.factory.FactoryBean;

import com.github.jmkgreen.morphia.Datastore;
import com.github.jmkgreen.morphia.EntityInterceptor;
import com.travelzen.framework.core.util.TZUtil;
import com.travelzen.mongo.TZMongoClientOptionsBuilder;
import com.travelzen.mongo.morphia.DataStoreFactory;

public class MessagingDatastoreFactory implements FactoryBean<Datastore> {

	private List<EntityInterceptor> interceptors;
	private String mongoUri;
	private String entityPackage;
	private TZMongoClientOptionsBuilder optionsBuilder;

	public TZMongoClientOptionsBuilder getOptionsBuilder() {
		return optionsBuilder;
	}

	public void setOptionsBuilder(TZMongoClientOptionsBuilder optionsBuilder) {
		this.optionsBuilder = optionsBuilder;
	}

	@Override
	public Datastore getObject() throws Exception {
		DataStoreFactory dsFactory = new DataStoreFactory();
		if (optionsBuilder != null) {
			dsFactory.setOptionsBuilder(optionsBuilder);
		}
		dsFactory.setPackagePaths(new String[]{ entityPackage });
		dsFactory.init();
		Datastore ds = dsFactory.createDatastore(mongoUri);
		if (!TZUtil.isEmpty(interceptors)) {
			for (EntityInterceptor ei : interceptors) {
				ds.getMapper().addInterceptor(ei);
			}
		}
		return ds;
	}

	@Override
	public Class<?> getObjectType() {
		return Datastore.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

	public void setInterceptors(List<EntityInterceptor> interceptors) {
		this.interceptors = interceptors;
	}

	public void setMongoUri(String mongoUri) {
		this.mongoUri = mongoUri;
	}

	public void setEntityPackage(String entityPackage) {
		this.entityPackage = entityPackage;
	}

}
