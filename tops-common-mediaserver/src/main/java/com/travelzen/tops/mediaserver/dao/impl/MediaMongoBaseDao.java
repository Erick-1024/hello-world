/*
 * Copyright 2012 Yunrang Inc. All Rights Reserved.
 * Author: jianyesun@yunrang.com (Jianye Sun)
 */

package com.travelzen.tops.mediaserver.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSFile;
import com.travelzen.mongo.dao.impl.ImageBasicDaoImpl;
import com.travelzen.tops.mediaserver.dao.IMediaMongoBaseDao;
import com.travelzen.tops.mediaserver.db.projo.Media;

@Repository
public class MediaMongoBaseDao extends ImageBasicDaoImpl implements IMediaMongoBaseDao {

    @SuppressWarnings("unused")
    private static Logger logger = LoggerFactory.getLogger(MediaMongoBaseDao.class);

    @Override
    public Object addMedia(Media media) {
    	if(null != getMedia(media.getMediaId())){
    		logger.debug("进行删除mediaId:"+media.getMediaId()+"的文件");
    		this.removeImageById(media.getMediaId());
    		logger.debug("删除mediaId:"+media.getMediaId()+"的文件成功");
    	}
        GridFSFile mediafile = this.grfs.createFile(media.getInputStream());
        mediafile.put("mediaId", media.getMediaId());
        mediafile.put("filename", media.getFilename());
        mediafile.put("contentType", media.getType());
        mediafile.save();

        media.setCreateTime(mediafile.getUploadDate());

        return media.getMediaId();
    }

    @Override
    public Media getMedia(Object mediaId) {
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
    public Object addContract(Media contract) {
        GridFSFile mediafile = this.grfsContract.createFile(contract.getInputStream());
        mediafile.put("contractId", contract.getMediaId());
        mediafile.put("filename", contract.getFilename());
        mediafile.put("contentType", contract.getType());
        mediafile.save();

        contract.setCreateTime(mediafile.getUploadDate());
        return contract.getMediaId();
    }

    @Override
    public Media getContract(Object contractId) {
        DBObject obj = new BasicDBObject();
        obj.put("contractId", contractId);
        GridFSDBFile file = this.grfsContract.findOne(obj);
        if (file == null) {
            return null;
        }

        Media media = new Media();
        media.setCreateTime(file.getUploadDate());
        media.setInputStream(file.getInputStream());
        media.setFilename(file.getFilename());
        media.setMediaId((String) contractId);
        media.setType(file.getContentType());
        media.setLength(file.getLength());
        return media;
    }

}
