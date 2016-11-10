package com.cana.netdisk.service.transaction;

import java.util.List;

import com.cana.netdisk.dao.po.NetDisk;
import com.cana.vbam.common.netdisk.dto.GetMediaListRequest;
import com.cana.vbam.common.netdisk.dto.SaveMediaRequest;

public interface INetdiskTransactionService {

	/**
	 * 保存一个文件
	 * @param saveMediaRequest
	 * @return
	 */
	public NetDisk saveMedia(SaveMediaRequest saveMediaRequest);
	
	/**
	 * 获取某个目录的文件（夹）列表
	 * @param getMediaListRequest
	 * @return
	 */
	public List<NetDisk> getMediaList(GetMediaListRequest getMediaListRequest);
	
	/**
	 * 根据ID获取某个文件或则文件夹以及其中的所有文件
	 * @param id
	 * @return
	 */
	public List<NetDisk> getMedias(String id);

	/**
	 * 根据ID删除一条文件（夹）记录
	 * @param id
	 */
	public void removeMediaRecords(String id);
	
}
