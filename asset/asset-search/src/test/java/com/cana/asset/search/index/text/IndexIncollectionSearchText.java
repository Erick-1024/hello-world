package com.cana.asset.search.index.text;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cana.asset.search.index.bean.UnderlyingAssetIndexBean;
import com.cana.asset.search.index.bean.UnderlyingAssetIndexBean.UnderlyingAssetFieldEnum;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.travelzen.framework.lucene.index.IndexBuilder;
import com.travelzen.framework.lucene.index.query.bean.BooleanQueryExample;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:META-INF/spring/*.xml", "classpath*:spring/asset-search-*.xml"})
public class IndexIncollectionSearchText {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Test
	public void testInCollectionSeach() throws Exception{
		IndexBuilder indexBuilder = IndexBuilder.getIndexBuilder();
		BooleanQueryExample stuexa = new BooleanQueryExample();
		
		List<String> assetIds = Lists.newArrayList("20160914002-1","20160913001-S1");
//		stuexa.and(UnderlyingAssetFieldEnum.loanId.name(), "20160913001-S1");
		stuexa.inCollection(UnderlyingAssetFieldEnum.loanId.name(), assetIds);
		List<UnderlyingAssetIndexBean> beans = indexBuilder.doSearch(stuexa, 0, 10, UnderlyingAssetIndexBean.class);
		System.out.println("test==lucene,queryIndex,["+ new Gson().toJson(beans)+"]");
		
	}
}
