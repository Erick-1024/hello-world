package com.travelzen.tops.mediaserver.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.github.jmkgreen.morphia.Key;
import com.github.jmkgreen.morphia.query.Query;
import com.mongodb.WriteResult;
import com.travelzen.mongo.dao.impl.MorphiaBasicDao;
import com.travelzen.mongo.entity.MorphiaEntity;
import com.travelzen.tops.mediaserver.exception.MediaServerException;

@Repository
public class RelationMongoBaseDao<E extends MorphiaEntity<I>, I> extends MorphiaBasicDao<E, I> {

	private Logger LOG = LoggerFactory.getLogger(RelationMongoBaseDao.class);

	public String addImageUrl(E relation) {
		Key<E> result = this.getDatastore().save(relation);
		return result.getId().toString();
	}

	public E getImageUrl(String key, String value) throws MediaServerException {
		Query<E> query = (Query<E>) this.getDatastore().createQuery(getEntityClass());
		query.field(key).equal(value);
		List<E> result = query.asList();
		if (result.size() <= 0) {
			LOG.debug("The Key \"" + value + "\" does not exist");
			return null;
		}
		if (result.size() > 1) {
			throw new MediaServerException("The Key \"" + value + "\" More than one!");
		}
		return result.get(0);
	}

	public WriteResult deleteById(I id) {
		return this.getDatastore().delete(getEntityClass(), id);
	}

}
