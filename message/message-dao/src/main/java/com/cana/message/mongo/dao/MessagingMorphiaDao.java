package com.cana.message.mongo.dao;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import com.github.jmkgreen.morphia.Datastore;
import com.travelzen.mongo.dao.MemberBasicDao;
import com.travelzen.mongo.dao.impl.MorphiaBasicDao;
import com.travelzen.mongo.entity.MorphiaEntity;

public abstract class MessagingMorphiaDao<E extends MorphiaEntity<I>, I>
		extends MorphiaBasicDao<E, I> implements MemberBasicDao<E, I>{

	@Resource(name = "msgDatastore")
	private Datastore messageDatastore;

	@PostConstruct
	protected void setCustomerDatastore() {
		super.setDatastore(this.messageDatastore);
	}

	@Override
	public void batchSave(List<E> data) {
		getDatastore().save(data);
	}

	@Override
	public void clear() {
		getDatastore().delete(createQuery());
	}
}