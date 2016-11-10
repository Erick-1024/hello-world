package com.travelzen.tops.mediaserver.service;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.travelzen.framework.util.HashUtils;
import com.travelzen.tops.mediaserver.dao.impl.ImageRelationMongoDao;
import com.travelzen.tops.mediaserver.dao.impl.MediaMongoBaseDao;
import com.travelzen.tops.mediaserver.db.projo.Image;
import com.travelzen.tops.mediaserver.db.projo.ImageRelation;
import com.travelzen.tops.mediaserver.db.projo.Media;
import com.travelzen.tops.mediaserver.exception.MediaServerException;

@Service
public class ImageLoadService {
	private static Logger LOG = LoggerFactory.getLogger(ImageLoadService.class);

	@Resource
	private MediaMongoBaseDao mediaMongoBaseDao;

	@Resource
	private ImageRelationMongoDao imageRelationMongoDao;

	public boolean setImageIntoMongo(String mediaImageId) {
		ImageRelation imageRelation = null;
		try {
			imageRelation = imageRelationMongoDao.getImageUrl("mediaId",
					mediaImageId);
		} catch (MediaServerException e) {
			LOG.warn(e.getMessage());
		}
		if (imageRelation == null) {
			return false;
		}
		mediaMongoBaseDao.removeImageById(mediaImageId);
		String imageUrl = imageRelation.getImageUrl();
		String fileName = getFileName(imageUrl);
		try {
			Media media = new Image();
			URLConnection connection = new URL(imageUrl).openConnection();
			media.setInputStream(connection.getInputStream());
			media.setFilename(fileName);
			media.setMediaId(getMediaId(fileName));
			if (fileName != null) {
				String contentType = fileName.substring(
						fileName.lastIndexOf(".") + 1, fileName.length());
				media.setType(contentType.toUpperCase());
			} else {
				media.setType(null);
			}
			media.setCreateTime(new Date());
			media.setLength(connection.getContentLength());

			mediaMongoBaseDao.addMedia(media);
			return true;
		} catch (IOException e) {
			LOG.error(e.getMessage(), e);
			return false;
		}
	}

	public String putImageRelations(String imageUrl) {
		try {
			String fileName = getFileName(imageUrl);
			String mediaId = getMediaId(fileName);
			ImageRelation relation = new ImageRelation();
			relation.setMediaId(mediaId);
			relation.setImageUrl(imageUrl);
			imageRelationMongoDao.updateOrCreate(relation);
			return mediaId;
		} catch (MediaServerException e) {
			LOG.error(e.getMessage());
			return "";
		}
	}

	private static String getFileName(String url) {
		String GenerateName = new Long(System.currentTimeMillis()).toString()
				+ ".X";
		int index = url.lastIndexOf("/");
		int indexTo = url.lastIndexOf("?");
		if (indexTo <= 0) {
			indexTo = url.length();
		}
		if (index <= 0) {
			return GenerateName;
		}
		String name = url.substring(index + 1, indexTo).trim();
		if (name.length() > 0) {
			return name;
		} else {
			return GenerateName;
		}
	}

	private String getMediaId(String fileName) {
		String mediaStringId = fileName.replaceAll("/", "");
		if (mediaStringId.length() <= 0) {
			return null;
		}
		// mediaStringId = mediaStringId.substring(mediaStringId.length() - 7,
		// mediaStringId.length());
		String mediaId = String.valueOf(HashUtils.murmurHash2(mediaStringId));
		return mediaId;

	}
}
