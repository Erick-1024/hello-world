package com.cana.netdisk.mongo.dao;

import com.cana.netdisk.dao.projo.Media;

public interface MongoBaseDao {

	public void addMedia(Media media);
	
	public Media getMedia(String mediaId);
	
	public void removeMedia(String mediaId);

}
