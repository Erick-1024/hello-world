package com.cana.asset.server.api.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import com.cana.asset.api.IAssetArchivesManagementApi;
import com.cana.asset.service.transaction.IABSDataPrivilegeTransactionService;
import com.cana.asset.service.transaction.IAssetArchivesManagementTransactionService;
import com.cana.member.api.IMemberQueryApi;
import com.cana.netdisk.dao.po.NetDisk;
import com.cana.netdisk.service.INetdiskService;
import com.cana.netdisk.service.transaction.INetdiskTransactionService;
import com.cana.vbam.common.member.vo.UserVo;
import com.cana.vbam.common.netdisk.dto.GetMediaListRequest;
import com.cana.vbam.common.netdisk.dto.MediaDTO;
import com.cana.vbam.common.netdisk.dto.MediaDownloadDTO;
import com.cana.vbam.common.netdisk.dto.MediaDownloadRequest;
import com.cana.vbam.common.netdisk.dto.SaveMediaRequest;
import com.cana.vbam.common.netdisk.enums.Type;
import com.travelzen.framework.core.common.ReturnCode;
import com.travelzen.framework.core.exception.WebException;

public class AssetArchivesManagementApiImpl implements IAssetArchivesManagementApi {

	@Resource
	private INetdiskService netdiskServiceImpl;
	
	@Resource
	private INetdiskTransactionService netdiskTransactionServiceImpl;
	
	@Resource
	private IABSDataPrivilegeTransactionService aBSDataPrivilegeTransactionServiceImpl;
	
	@Resource
	private IAssetArchivesManagementTransactionService assetArchivesManagementTransactionServiceImpl;
	
	@Resource
	private IMemberQueryApi memberQueryApi;
	
	@Override
	public MediaDTO saveMedia(SaveMediaRequest saveMediaRequest) {
		checkPrivilege(saveMediaRequest.getCreatorId(), saveMediaRequest.getGroupId());
		return assetArchivesManagementTransactionServiceImpl.save(saveMediaRequest);
	}

	@Override
	public List<MediaDTO> getMediaList(GetMediaListRequest getMediaListRequest, String userId) {
		checkPrivilege(userId, getMediaListRequest.getGroupId());
		List<NetDisk> netdisks = netdiskTransactionServiceImpl.getMediaList(getMediaListRequest);
		List<MediaDTO> returnValue = new ArrayList<>();
		for (NetDisk netDisk : netdisks)
			returnValue.add(convertNetdisk2MediaDTO(netDisk, userId));
		return returnValue;
	}

	@Override
	public void removeMedia(String id, String userId) {
		List<NetDisk> netdisks = netdiskTransactionServiceImpl.getMedias(id);
		if(netdisks == null)
			throw WebException.instance("不存在的文件");
		checkPrivilege(userId, netdisks.get(0).getGroupId());
		List<String> mediaIds = assetArchivesManagementTransactionServiceImpl.remove(id, userId, netdisks.get(0).getGroupId());
		for (String mediaId : mediaIds)
				netdiskServiceImpl.removeMedia(mediaId);
	}

	@Override
	public List<MediaDownloadDTO> download(MediaDownloadRequest mediaDownloadRequest, String userId) {
		checkPrivilege(userId, mediaDownloadRequest.getGroupId());
		List<MediaDownloadDTO> MediaDownloadDTOs = new ArrayList<>();
		List<NetDisk> netdisks = null;
		if(StringUtils.isBlank(mediaDownloadRequest.getId()))
			netdisks = netdiskServiceImpl.getAllMedias(mediaDownloadRequest.getGroupId(), mediaDownloadRequest.getModule());
		else
			netdisks = netdiskTransactionServiceImpl.getMedias(mediaDownloadRequest.getId());
		if(netdisks == null || netdisks.isEmpty())
			throw WebException.instance("没有可下载的文件");
		for (NetDisk netDisk : netdisks) {
			MediaDownloadDTO mediaDownloadDTO = new MediaDownloadDTO();
			BeanUtils.copyProperties(netDisk, mediaDownloadDTO);
			mediaDownloadDTO.setType(Type.valueOf(netDisk.getType()));
			if(netDisk.getMediaId() != null)
				mediaDownloadDTO.setContents(netdiskServiceImpl.getMediaContent(netDisk.getMediaId()));
			MediaDownloadDTOs.add(mediaDownloadDTO);
		}
		return MediaDownloadDTOs;
	}
	
	private MediaDTO convertNetdisk2MediaDTO(NetDisk netdisk, String userId) {
		MediaDTO mediaDTO = new MediaDTO();
		BeanUtils.copyProperties(netdisk, mediaDTO);
		mediaDTO.setType(Type.valueOf(netdisk.getType()));
		mediaDTO.setCanDelete(userId.equals(netdisk.getCreatorId()));
		return mediaDTO;
	}
	
	private void checkPrivilege(String userId, String programId) {
		UserVo userVo = memberQueryApi.findUserById(userId);
		if(!aBSDataPrivilegeTransactionServiceImpl.allow(userVo.getCustomer().getUserType(), userVo.getCustomer().getCustomerName(), programId))
			throw WebException.instance(ReturnCode.NO_PERMISSION);
	}
	
}
