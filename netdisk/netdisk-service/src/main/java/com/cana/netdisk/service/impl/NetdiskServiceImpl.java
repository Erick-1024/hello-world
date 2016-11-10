package com.cana.netdisk.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.cana.netdisk.dao.mapper.gen.NetDiskMapper;
import com.cana.netdisk.dao.po.NetDisk;
import com.cana.netdisk.dao.po.NetDiskExample;
import com.cana.netdisk.dao.projo.Media;
import com.cana.netdisk.mongo.dao.MongoBaseDao;
import com.cana.netdisk.service.INetdiskService;
import com.cana.vbam.common.netdisk.enums.Module;
import com.travelzen.framework.core.exception.WebException;

@Service
public class NetdiskServiceImpl implements INetdiskService {

	@Resource
	private NetDiskMapper netDiskMapper;
	
	@Resource
	private MongoBaseDao mongoBaseDaoImpl;
	
	@Override
	public NetDisk getMedia(String id) {
		return netDiskMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<NetDisk> getAllMedias(String groupId, Module module) {
		if(StringUtils.isBlank(groupId))
			throw WebException.instance("groupId不能为空");
		if(module == null)
			throw WebException.instance("module不能为空");
		NetDiskExample netDiskExample = new NetDiskExample();
		netDiskExample.createCriteria().andGroupIdEqualTo(groupId).andModuleEqualTo(module.name());
		netDiskExample.setOrderByClause("type, name");
		return netDiskMapper.selectByExample(netDiskExample);
	}

	@Override
	public void removeMedia(String mediaId) {
		mongoBaseDaoImpl.removeMedia(mediaId);
	}

	@Override
	public byte[] getMediaContent(String mediaId) {
		Media media = mongoBaseDaoImpl.getMedia(mediaId);
		if(media == null)
			throw WebException.instance("不存在该文件");
		return media.getContent();
	}
	
}
