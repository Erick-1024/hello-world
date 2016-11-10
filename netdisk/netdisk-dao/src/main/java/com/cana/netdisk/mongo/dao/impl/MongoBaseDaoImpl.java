package com.cana.netdisk.mongo.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.cana.netdisk.dao.projo.Media;
import com.cana.netdisk.mongo.dao.MongoBaseDao;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSFile;
import com.travelzen.mongo.dao.impl.ImageBasicDaoImpl;

@Repository
public class MongoBaseDaoImpl extends ImageBasicDaoImpl implements MongoBaseDao {

	 private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	public void addMedia(Media media) {
		if(null != getMedia(media.getMediaId())){
    		logger.debug("进行删除mediaId:"+media.getMediaId()+"的文件");
    		this.removeImageById(media.getMediaId());
    		logger.debug("删除mediaId:"+media.getMediaId()+"的文件成功");
    	}
        GridFSFile mediafile = this.grfs.createFile(media.getContent());
        mediafile.put("mediaId", media.getMediaId());
        mediafile.put("filename", media.getFilename());
        mediafile.put("contentType", media.getType());
        mediafile.save();
	}

	@Override
	public Media getMedia(String mediaId) {
		DBObject obj = new BasicDBObject();
        obj.put("mediaId", mediaId);
        GridFSDBFile file = this.grfs.findOne(obj);
        if (file == null) {
        	logger.debug("没获取到mediaId:"+mediaId+"的文件");
            return null;
        }
        logger.debug("获取到mediaId:"+mediaId+"的文件");
        Media media = new Media();
        media.setCreateTime(file.getUploadDate());
        media.setInputStream(file.getInputStream());
        media.setFilename(file.getFilename());
        media.setMediaId((String) mediaId);
        media.setType(file.getContentType());
        media.setLength(file.getLength());
        return media;
	}

	@Override
	public void removeMedia(String mediaId) {
		removeImageById(mediaId);
	}

}
