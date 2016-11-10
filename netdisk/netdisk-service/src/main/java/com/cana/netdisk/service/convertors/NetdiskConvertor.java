package com.cana.netdisk.service.convertors;

import org.springframework.beans.BeanUtils;

import com.cana.netdisk.dao.po.NetDisk;
import com.cana.vbam.common.netdisk.dto.MediaDTO;
import com.cana.vbam.common.netdisk.enums.Type;

public class NetdiskConvertor {

	public static MediaDTO convertNetdisk2MediaDTO(NetDisk netdisk, String userId) {
		MediaDTO mediaDTO = new MediaDTO();
		BeanUtils.copyProperties(netdisk, mediaDTO);
		mediaDTO.setType(Type.valueOf(netdisk.getType()));
		mediaDTO.setCanDelete(userId.equals(netdisk.getCreatorId()));
		return mediaDTO;
	}
	
}
