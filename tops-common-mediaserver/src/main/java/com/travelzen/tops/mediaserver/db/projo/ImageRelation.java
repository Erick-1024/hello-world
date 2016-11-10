package com.travelzen.tops.mediaserver.db.projo;

import org.bson.types.ObjectId;

import com.github.jmkgreen.morphia.annotations.Entity;
import com.github.jmkgreen.morphia.annotations.Id;
import com.travelzen.mongo.entity.MorphiaEntity;

@Entity(value = "relation.elongImage", noClassnameStored = true)
public class ImageRelation implements MorphiaEntity<ObjectId> {

	@Id
	private ObjectId id;

	// @Indexed
	private String mediaId;

	private String imageUrl;

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

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

}
