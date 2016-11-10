package com.cana.asset.api;

import com.cana.vbam.common.asset.underlyingasset.dto.UnderlyingAssetDTO;
import com.cana.vbam.common.asset.underlyingasset.dto.UnderlyingAssetSearchCriteria;
import com.cana.vbam.common.dto.ListResult;

/**
 * @author hu
 *
 */
public interface IABSUnderlyingAssetSearchApi {

	/**
	 * 搜索备选基础资产
	 * @param criteria
	 * @return
	 */
	public ListResult<UnderlyingAssetDTO> searchUnderlyAsset(UnderlyingAssetSearchCriteria criteria) throws Exception;
}
