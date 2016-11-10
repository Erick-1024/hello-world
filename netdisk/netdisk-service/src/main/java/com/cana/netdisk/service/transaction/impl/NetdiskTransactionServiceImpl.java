package com.cana.netdisk.service.transaction.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.cana.netdisk.dao.mapper.gen.NetDiskMapper;
import com.cana.netdisk.dao.po.NetDisk;
import com.cana.netdisk.dao.po.NetDiskExample;
import com.cana.netdisk.dao.po.NetDiskExample.Criteria;
import com.cana.netdisk.dao.projo.Media;
import com.cana.netdisk.mongo.dao.MongoBaseDao;
import com.cana.netdisk.service.transaction.INetdiskTransactionService;
import com.cana.vbam.common.netdisk.dto.GetMediaListRequest;
import com.cana.vbam.common.netdisk.dto.SaveMediaRequest;
import com.cana.vbam.common.netdisk.enums.Type;
import com.cana.vbam.common.utils.Constants;
import com.travelzen.framework.core.exception.WebException;
import com.travelzen.framework.core.time.DateTimeUtil;
import com.travelzen.framework.dao.rdbms.SequenceGenerator;
import com.travelzen.framework.media.MediaId;

@Service
public class NetdiskTransactionServiceImpl implements INetdiskTransactionService {

	@Resource
	private SequenceGenerator seqGen;
	
	@Resource
	private NetDiskMapper netDiskMapper;
	
	@Resource
	private MongoBaseDao mongoBaseDaoImpl;
	
	@Override
	public NetDisk saveMedia(SaveMediaRequest saveMediaRequest) {
		if(saveMediaRequest.getType() == null)
			throw WebException.instance("type不能为空");
		String fileName = saveMediaRequest.getFileName();
		if(StringUtils.isBlank(fileName))
			throw WebException.instance("fileName不能为空");
		if(fileName.length() > 255)
			throw WebException.instance("文件名不能超过255个字符");
		if(saveMediaRequest.getType() == Type.FILE && !fileName.matches("[^\\s\\\\/:\\*\\?\\\"<>\\|](\\x20|[^\\s\\\\/:\\*\\?\\\"<>\\|])*[^\\s\\\\/:\\*\\?\\\"<>\\|\\.]$"))
			throw WebException.instance("非法文件名");
		if(saveMediaRequest.getType() == Type.DIRECTORY && !fileName.matches("^[^\\/:*?\"<>|]+$"))
			throw WebException.instance("非法文件夹名");
		if(saveMediaRequest.getModule() == null)
			throw WebException.instance("module不能为空");
		if(StringUtils.isBlank(saveMediaRequest.getPath()))
			throw WebException.instance("path不能为空");
		if(StringUtils.isBlank(saveMediaRequest.getPath()))
			throw WebException.instance("文件路径不能超过200个字符");
		if(StringUtils.isBlank(saveMediaRequest.getGroupId()))
			throw WebException.instance("groupId不能为空");
		if(StringUtils.isBlank(saveMediaRequest.getCreatorId()))
			throw WebException.instance("creatorId不能为空");
		GetMediaListRequest getMediaListRequest = new GetMediaListRequest();
		getMediaListRequest.setGroupId(saveMediaRequest.getGroupId());
		getMediaListRequest.setModule(saveMediaRequest.getModule());
		getMediaListRequest.setPath(saveMediaRequest.getPath());
		getMediaListRequest.setType(saveMediaRequest.getType());
		List<NetDisk> netDisks = getMediaList(getMediaListRequest);
		fileName = rename(netDisks, fileName, null, saveMediaRequest.getType());
		String id = DateTimeUtil.datetime12() + seqGen.getNextSeq(Constants.SEQUENCE_NAME_NETDISK_ID, 4);
		NetDisk netdisk = new NetDisk();
		netdisk.setId(id);
		netdisk.setCreateTime(new Date());
		netdisk.setGroupId(saveMediaRequest.getGroupId());
		netdisk.setModule(saveMediaRequest.getModule().name());
		netdisk.setName(fileName);
		netdisk.setPath(saveMediaRequest.getPath());
		netdisk.setType(saveMediaRequest.getType().name());
		netdisk.setCreatorId(saveMediaRequest.getCreatorId());
		if(Type.FILE == saveMediaRequest.getType()) {
			if(saveMediaRequest.getContents().length == 0)
				throw WebException.instance("contents不能为空");
			String mediaId = MediaId.createMediaId();
			netdisk.setMediaId(mediaId);
			netdisk.setSize((long)saveMediaRequest.getContents().length);
			Media media = new Media();
			media.setContent(saveMediaRequest.getContents());
			media.setFilename(fileName);
			media.setMediaId(mediaId);
			media.setType(fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()));
			mongoBaseDaoImpl.addMedia(media);
		}
		netDiskMapper.insertSelective(netdisk);
		return netdisk;
	}

	@Override
	public List<NetDisk> getMediaList(GetMediaListRequest getMediaListRequest) {
		if(StringUtils.isBlank(getMediaListRequest.getGroupId()))
			throw WebException.instance("groupId不能为空");
		if(StringUtils.isBlank(getMediaListRequest.getPath()))
			throw WebException.instance("path不能为空");
		if(!getMediaListRequest.getPath().endsWith("/"))
			throw WebException.instance("path必须以'/'结尾");
		if(getMediaListRequest.getModule() == null)
			throw WebException.instance("module不能为空");
		NetDiskExample netDiskExample = new NetDiskExample();
		Criteria criteria = netDiskExample.createCriteria();
		criteria.andModuleEqualTo(getMediaListRequest.getModule().name()).andGroupIdEqualTo(getMediaListRequest.getGroupId()).andPathEqualTo(getMediaListRequest.getPath());
		if(getMediaListRequest.getType() != null)
			criteria.andTypeEqualTo(getMediaListRequest.getType().name());
		netDiskExample.setOrderByClause("type, create_time desc");
		return netDiskMapper.selectByExample(netDiskExample);
	}

	@Override
	public List<NetDisk> getMedias(String id) {
		NetDisk netDisk = netDiskMapper.selectByPrimaryKey(id);
		if(netDisk == null)
			return null;
		List<NetDisk> netdisks = new ArrayList<>();
		if(Type.DIRECTORY == Type.valueOf(netDisk.getType())){
			NetDiskExample netdiskExample = new NetDiskExample();
			netdiskExample.createCriteria().andPathLike(netDisk.getPath() + netDisk.getName() + "/%").andGroupIdEqualTo(netDisk.getGroupId()).andModuleEqualTo(netDisk.getModule());
			netdiskExample.setOrderByClause("type, name");
			netdisks.addAll(netDiskMapper.selectByExample(netdiskExample));
		}
		netdisks.add(netDisk);
		return netdisks;
	}
	

	@Override
	public void removeMediaRecords(String id) {
		NetDisk netDisk = netDiskMapper.selectByPrimaryKey(id);
		if(netDisk == null)
			throw WebException.instance("不存在该文件");
		if(Type.DIRECTORY == Type.valueOf(netDisk.getType())){
			NetDiskExample netdiskExample = new NetDiskExample();
			netdiskExample.createCriteria().andPathLike(netDisk.getPath() + netDisk.getName() + "/%").andGroupIdEqualTo(netDisk.getGroupId()).andModuleEqualTo(netDisk.getModule());
			netDiskMapper.deleteByExample(netdiskExample);
		}
		netDiskMapper.deleteByPrimaryKey(id);
	}
	
	private String rename(List<NetDisk> netDisks, String fileName, String suffix, Type type) {
		String newFileName = fileName;
		if(suffix != null)
			if(type == Type.DIRECTORY)
				newFileName += suffix;
			else
				newFileName = fileName.substring(0, fileName.lastIndexOf(".")) + suffix + fileName.substring(fileName.lastIndexOf("."), fileName.length());
		for (NetDisk netDisk : netDisks) {
			if(netDisk.getName().equals(newFileName)) {
				if(suffix != null)
					suffix = (Integer.parseInt(suffix) + 1) + "";
				else
					suffix = "1";
				newFileName = rename(netDisks, fileName, suffix, type);
				break;
			}
		}
		return newFileName;
	}

}
