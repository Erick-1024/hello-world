package com.cana.crawler.dao.mongo.entity;

import java.io.Serializable;

import org.bson.types.ObjectId;
import org.joda.time.DateTime;

import com.github.jmkgreen.morphia.annotations.Id;
import com.travelzen.mongo.entity.MorphiaEntity;

public class BaseMorphiaEntity implements MorphiaEntity<ObjectId>, Serializable{
	private static final long serialVersionUID = 7986935728144596883L;
	@Id
	private ObjectId id;
	protected DateTime createDate;
	protected DateTime updateDate;

	@Override
	public ObjectId getId() {
		return id;
	}
	
	@Override
	public void setId(ObjectId id) {
		this.id = id;
	}

	@Override
	public void setId(String id) {
		this.id = new ObjectId(id);
	}

	public DateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate(DateTime createDate) {
		this.createDate = createDate;
	}

	public DateTime getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(DateTime updateDate) {
		this.updateDate = updateDate;
	}
}
