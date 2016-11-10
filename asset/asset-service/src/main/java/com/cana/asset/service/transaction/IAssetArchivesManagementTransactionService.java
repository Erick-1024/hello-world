package com.cana.asset.service.transaction;

import java.util.List;

import com.cana.vbam.common.netdisk.dto.MediaDTO;
import com.cana.vbam.common.netdisk.dto.SaveMediaRequest;

public interface IAssetArchivesManagementTransactionService {

	public MediaDTO save(SaveMediaRequest saveMediaRequest);
	
	public List<String> remove(String id, String userId, String specialProgramId);
	
}
