package com.cana.asset.search.index.builder;

import java.util.List;

/**
 * @author hu
 *
 */
public interface IUnderlyingAssetIndexBuilder {

	/**
	 * 全量创建索引
	 */
	public void createInedx() throws Exception ;
	
	/**
	 * 更新索引
	 * @param assetIds
	 */
	public void updateIndex(List<String> assetIds) throws Exception ;
}
