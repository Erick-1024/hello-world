package com.cana.asset.search.proxy;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.cana.asset.search.index.builder.IUnderlyingAssetIndexBuilder;

@Component
public class IndexBuildProxy {

	@Resource
	private IUnderlyingAssetIndexBuilder assetIndexBuilder;
	
	public synchronized void underlyingAssetIndexCreateOrUpdate(boolean isCreate, List<String> assetIds) throws Exception {
		if(isCreate)
			assetIndexBuilder.createInedx();
		else
			assetIndexBuilder.updateIndex(assetIds);
	}
}
