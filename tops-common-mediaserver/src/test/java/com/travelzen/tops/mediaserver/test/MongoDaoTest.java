package com.travelzen.tops.mediaserver.test;

import java.util.List;

import javax.annotation.Resource;

import org.bson.types.ObjectId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mongodb.BasicDBObject;
import com.mongodb.WriteResult;
import com.mongodb.gridfs.GridFSDBFile;
import com.travelzen.tops.mediaserver.dao.impl.ImageRelationMongoDao;
import com.travelzen.tops.mediaserver.dao.impl.MediaMongoBaseDao;
import com.travelzen.tops.mediaserver.db.projo.ImageRelation;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext-mediaserver.xml")
public class MongoDaoTest {

	private static Logger LOG = LoggerFactory.getLogger(MongoDaoTest.class);

	@Resource
	ImageRelationMongoDao imageRelationMongoDao;

	@Resource
	MediaMongoBaseDao mediaMongoBaseDao;

	@Test
	public void MongoConnectTest() throws Exception {

		ImageRelation relation = new ImageRelation();

		relation.setMediaId("9094257476850576466");
		relation.setImageUrl("http://www.elongstatic.com/gp1/M00/4B/87/pIYBAFIl_MaAJ4VxAAK9gHSFFg8890.png?v=20120307193144");
		String resule = imageRelationMongoDao.updateOrCreate(relation);

		LOG.info("<<- {}", resule);

	}

	@Test
	public void MongoGetUrlsTest() throws Exception {

		ImageRelation image = imageRelationMongoDao.getImageUrl("mediaId", "");
		if (image != null) {
			LOG.info("\n{}--{}", image.getMediaId(), image.getImageUrl());
		}
	}

	@Test
	public void MongoDeleteTest() {
		ObjectId id = new ObjectId("528ef2b9e4b04e00e965b6e9");
		WriteResult result = imageRelationMongoDao.deleteById(id);
//		LOG.error(result.getError());
	}

	@Test
	public void BaseDaoTest() {

		List<GridFSDBFile> result = mediaMongoBaseDao.grfs.find(new BasicDBObject("mediaId", "9094257476850576466"));
		System.out.println(result.size());
		for (GridFSDBFile file : result) {
			System.out.println(file.getFilename());
		}
	}

	@Test
	public void removeImageTest() {

		mediaMongoBaseDao.removeImageById("9094257476850576466");
	}
}
