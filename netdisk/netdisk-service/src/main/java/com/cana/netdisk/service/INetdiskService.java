package com.cana.netdisk.service;

import java.util.List;

import com.cana.netdisk.dao.po.NetDisk;
import com.cana.vbam.common.netdisk.enums.Module;

public interface INetdiskService {
	
	/**
	 * 根据文件（夹）ID获取文件（夹）
	 * @param id
	 * @return
	 */
	public NetDisk getMedia(String id);
	
	/**
	 * 获取满足条件的所有文件（夹）
	 * @param groupId
	 * @param module
	 * @return
	 */
	public List<NetDisk> getAllMedias(String groupId, Module module);
	
	/**
	 * 根据mediaId删除媒体服务器中的文件
	 * @param mediaId
	 */
	public void removeMedia(String mediaId);
	
	/**
	 * 根据mediaId获取文件的字节数组
	 * @param mediaId
	 * @return
	 */
	public byte[] getMediaContent(String mediaId);
	
}
