package com.cana.asset.dao.mapper;

import java.util.List;

import com.cana.asset.dao.po.UnderlyingAssetLog;
import com.cana.vbam.common.asset.underlyingasset.dto.UnderlyingAssetLogQuery;

public interface UnderlyingAssetLogCustomMapper {
	
	int count(UnderlyingAssetLogQuery query);

    List<UnderlyingAssetLog> find(UnderlyingAssetLogQuery query);
}
