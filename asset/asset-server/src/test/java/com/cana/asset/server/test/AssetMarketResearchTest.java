package com.cana.asset.server.test;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cana.asset.dao.mapper.MarketDataMapper;
import com.cana.asset.dao.mapper.gen.AssetMarketDataReportMapper;
import com.cana.asset.dao.po.AssetMarketDataReport;
import com.cana.vbam.common.asset.dto.MarketDataReportDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:META-INF/spring/*.xml")
public class AssetMarketResearchTest {

	@Resource
	private AssetMarketDataReportMapper assetMarketDataReportMapper;
	
	@Resource
	private MarketDataMapper marketDataMapper;
	
	/**
	 * 插入自测数据
	 */
	@Test
	public void insertData(){
		AssetMarketDataReport assetMarketDataReport = new AssetMarketDataReport();
		for(int i=0; i<24;i++){
			assetMarketDataReport.setId("210000"+i);
			assetMarketDataReport.setUnderlyingAssetType("");
			assetMarketDataReport.setIssueAmount(new BigDecimal(i*10));
			assetMarketDataReport.setIssueNum(i*32);
			assetMarketDataReport.setMonth("2016-09");
			assetMarketDataReportMapper.insert(assetMarketDataReport);
		}
	}
	
	@Test
	public void testSmmaryProjects(){
		List<MarketDataReportDTO> reports = marketDataMapper.selectSummaryProjectsByTypeAndMonth();
		for(MarketDataReportDTO marketDataReportDTO : reports){
			System.out.println(marketDataReportDTO.getIssueAmount());
			System.out.println(marketDataReportDTO.getIssueNum());
			System.out.println(marketDataReportDTO.getMonth());
			System.out.println(marketDataReportDTO.getUnderlyingAssetType());
		}
	}
	
}
