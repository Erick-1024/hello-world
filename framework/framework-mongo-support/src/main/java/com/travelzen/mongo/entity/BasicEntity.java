package com.travelzen.mongo.entity;

import org.bson.types.ObjectId;

import com.travelzen.mongo.entity.MorphiaEntity;

public interface BasicEntity extends MorphiaEntity<ObjectId> {

	public String getKey();

	public void setKey(String key);

}
