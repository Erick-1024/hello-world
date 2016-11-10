package com.cana.asset.api;

import java.util.List;

import com.cana.vbam.common.netdisk.dto.GetMediaListRequest;
import com.cana.vbam.common.netdisk.dto.MediaDTO;
import com.cana.vbam.common.netdisk.dto.MediaDownloadDTO;
import com.cana.vbam.common.netdisk.dto.MediaDownloadRequest;
import com.cana.vbam.common.netdisk.dto.SaveMediaRequest;

public interface IAssetArchivesManagementApi {

	public MediaDTO saveMedia(SaveMediaRequest saveMediaRequest);
	
	public List<MediaDTO> getMediaList(GetMediaListRequest getMediaListRequest, String userId);
	
	public void removeMedia(String id, String userId);
	
	public List<MediaDownloadDTO> download(MediaDownloadRequest mediaDownloadRequest, String userId);
	
}
