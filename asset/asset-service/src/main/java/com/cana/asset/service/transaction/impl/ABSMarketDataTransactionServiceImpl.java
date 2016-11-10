package com.cana.asset.service.transaction.impl;

import java.util.ArrayList;
import java.util.Date;
import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.cana.asset.dao.mapper.MarketDataMapper;
import com.cana.asset.dao.mapper.gen.AssetMarketDataProductMapper;
import com.cana.asset.dao.mapper.gen.AssetMarketDataProjectMapper;
import com.cana.asset.dao.mapper.gen.AssetMarketDataReportMapper;
import com.cana.asset.dao.po.AssetMarketDataProduct;
import com.cana.asset.dao.po.AssetMarketDataProject;
import com.cana.asset.dao.po.AssetMarketDataReport;
import com.cana.asset.dao.po.AssetMarketDataReportExample;
import com.cana.asset.service.transaction.IABSMarketDataTransactionService;
import com.cana.asset.service.transaction.util.ValidateRules;
import com.cana.vbam.common.asset.dto.MarketDataProductExcelDTO;
import com.cana.vbam.common.asset.dto.MarketDataProjectExcelDTO;
import com.cana.vbam.common.asset.dto.MarketDataReportDTO;
import com.cana.vbam.common.utils.Constants;
import com.cana.vbam.common.utils.MoneyArithUtil;
import com.travelzen.framework.core.time.DateTimeUtil;
import com.travelzen.framework.dao.rdbms.SequenceGenerator;

@Service
public class ABSMarketDataTransactionServiceImpl implements IABSMarketDataTransactionService {

	@Resource
	private AssetMarketDataProjectMapper assetMarketDataProjectMapper;
	
	@Resource
	private AssetMarketDataProductMapper assetMarketDataProductMapper;
	
	@Resource
	private AssetMarketDataReportMapper assetMarketDataReportMapper;
	
	@Resource 
	private MarketDataMapper marketDataMapper;
	
	@Resource
	private SequenceGenerator seqGen;
	
	@Override
	public void importMarketData(List<MarketDataProjectExcelDTO> projectExcelList) {

		for(MarketDataProjectExcelDTO projectExcelDTO : projectExcelList){
			AssetMarketDataProject project = new AssetMarketDataProject();
			project.setId(generateMarketDataProjectId());
			project.setProjectName(projectExcelDTO.getProjectName());
			project.setValueDate(projectExcelDTO.getValueDateStr());
			project.setOriginator(projectExcelDTO.getOriginator());
			project.setIssueTotalAmount(MoneyArithUtil.convertStringToMoneyBigDecimal(projectExcelDTO.getIssueTotalAmount()));
			project.setSupervisionAgency(projectExcelDTO.getSupervisionAgencyEnum().name());
			project.setUnderlyingAssetType(projectExcelDTO.getUnderlyingAssetType());
			project.setIssuer(projectExcelDTO.getIssuer());
			project.setIssueMonth(projectExcelDTO.getIssueMonthNum());
			if(StringUtils.isNotBlank(projectExcelDTO.getAaaAverageInterestRate()))
				project.setAaaAverageInterestRate(new BigDecimal(projectExcelDTO.getAaaAverageInterestRate()));
			if(StringUtils.isNotBlank(projectExcelDTO.getPriorityAverageInterestRate()))
				project.setPriorityAverageInterestRate(new BigDecimal(projectExcelDTO.getPriorityAverageInterestRate()));
			assetMarketDataProjectMapper.insertSelective(project);
			
			for(MarketDataProductExcelDTO productExcelDTO : projectExcelDTO.getProductExcelList()){
				AssetMarketDataProduct product = new AssetMarketDataProduct();
				product.setId(generateMarketDataProductId());
				product.setProductName(productExcelDTO.getProductName());
				product.setProjectId(project.getId());
				product.setIssueAmount(MoneyArithUtil.convertStringToMoneyBigDecimal(productExcelDTO.getIssueAmount()));
				product.setDebtRating(productExcelDTO.getDebtRating());
				product.setRatingAgency(productExcelDTO.getRatingAgency());
				if(StringUtils.isNotBlank(productExcelDTO.getInterestRate())){
					if(ValidateRules.regexPercentCheck(productExcelDTO.getInterestRate()))
						product.setInterestRate(MoneyArithUtil.convertStringToInterestRate(productExcelDTO.getInterestRate()));
					if(ValidateRules.regexAmountFlaotCheck(productExcelDTO.getInterestRate()))
						product.setInterestRate(new BigDecimal(productExcelDTO.getInterestRate()));
				}
				assetMarketDataProductMapper.insertSelective(product);
			}
		}
		//插入 asset_market_data_report
		List<MarketDataReportDTO> list = marketDataMapper.selectSummaryProjectsByTypeAndMonth();
		insertMarketDataReport(list);
	}


	private void insertMarketDataReport(List<MarketDataReportDTO> list) {
		//report 全删 
		AssetMarketDataReportExample reportExample = new AssetMarketDataReportExample();
		List<AssetMarketDataReport> reports = assetMarketDataReportMapper.selectByExample(reportExample);
		List<String> reportIds = new ArrayList<>();
		if(CollectionUtils.isNotEmpty(reports)){
			for(AssetMarketDataReport assetMarketDataReport : reports){
				reportIds.add(assetMarketDataReport.getId());
			}
		}
		for(String id : reportIds){
			assetMarketDataReportMapper.deleteByPrimaryKey(id);
		}
		//全增
		for(MarketDataReportDTO MarketDataReportDTO : list){
			AssetMarketDataReport assetMarketDataReport = new AssetMarketDataReport();
			assetMarketDataReport.setId(generateMarketDataReportId());
			assetMarketDataReport.setUnderlyingAssetType(MarketDataReportDTO.getUnderlyingAssetType());
			assetMarketDataReport.setIssueAmount(MarketDataReportDTO.getIssueAmount());
			assetMarketDataReport.setIssueNum(MarketDataReportDTO.getIssueNum());
			assetMarketDataReport.setMonth(MarketDataReportDTO.getMonth());
			assetMarketDataReport.setCreateTime(new Date());
			assetMarketDataReport.setUpateTime(new Date());
			assetMarketDataReportMapper.insert(assetMarketDataReport);
		}
	}
	
	private String generateMarketDataProjectId(){
		return DateTimeUtil.datetime12() + seqGen.getNextSeq(Constants.SEQUENCE_NAME_ASSET_MARKET_DATA_PROJECT_ID, 4);
	} 
	private String generateMarketDataProductId(){
		return DateTimeUtil.datetime12() + seqGen.getNextSeq(Constants.SEQUENCE_NAME_ASSET_MARKET_DATA_PRODUCT_ID, 4);
	} 
	private String generateMarketDataReportId(){
		return DateTimeUtil.datetime12() + seqGen.getNextSeq(Constants.SEQUENCE_NAME_ASSET_MARKET_DATA_REPORT_ID, 4);
	} 
}
