package com.cana.asset.service.transaction.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cana.asset.dao.mapper.gen.SpecialProgramMapper;
import com.cana.asset.dao.po.SpecialProgram;
import com.cana.asset.service.transaction.IAssetArchivesManagementTransactionService;
import com.cana.netdisk.dao.po.NetDisk;
import com.cana.netdisk.service.convertors.NetdiskConvertor;
import com.cana.netdisk.service.transaction.INetdiskTransactionService;
import com.cana.vbam.common.asset.enums.SpecialProgramStatus;
import com.cana.vbam.common.netdisk.dto.MediaDTO;
import com.cana.vbam.common.netdisk.dto.SaveMediaRequest;
import com.travelzen.framework.core.exception.WebException;

@Service
public class AssetArchivesManagementTransactionServiceImpl implements IAssetArchivesManagementTransactionService {

	@Resource
	private INetdiskTransactionService netdiskTransactionServiceImpl;
	
	@Resource
	private SpecialProgramMapper specialProgramMapper;
	
	@Override
	public MediaDTO save(SaveMediaRequest saveMediaRequest) {
		checkStatus(specialProgramMapper.lockByPrimaryKey(saveMediaRequest.getGroupId()));
		return NetdiskConvertor.convertNetdisk2MediaDTO(netdiskTransactionServiceImpl.saveMedia(saveMediaRequest), saveMediaRequest.getCreatorId());
	}

	@Override
	public List<String> remove(String id, String userId, String specialProgramId) {
		checkStatus(specialProgramMapper.lockByPrimaryKey(specialProgramId));
		List<NetDisk> netdisks = netdiskTransactionServiceImpl.getMedias(id);
		if(netdisks == null)
			throw WebException.instance("不存在的文件");
		List<String> mediaIds = new ArrayList<String>();
		for (NetDisk netDisk : netdisks) {
			if(!netDisk.getCreatorId().equals(userId))
				throw WebException.instance("不能删除包含不是您创建的文件");
			if(netDisk.getMediaId() != null)
				mediaIds.add(netDisk.getMediaId());
		}
		netdiskTransactionServiceImpl.removeMediaRecords(id);
		return mediaIds;
	}
	
	private void checkStatus(SpecialProgram specialProgram) {
		if(specialProgram == null)
			throw WebException.instance("没有该专项计划");
		if(!SpecialProgramStatus.CREATE.name().equals(specialProgram.getStatus()))
			throw WebException.instance("当前状态不可对档案进行修改");
	}

}
