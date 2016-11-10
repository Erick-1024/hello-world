package com.cana.asset.service.impl;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import com.cana.asset.dao.po.AssetMarketDataProductExample;
import com.cana.asset.dao.po.AssetMarketDataProject;
import com.cana.asset.dao.po.AssetMarketDataProjectExample;
import com.cana.asset.dao.po.AssetMarketDataProjectExample.Criteria;
import com.cana.asset.dao.po.AssetMarketDataReport;
import com.cana.asset.dao.po.AssetMarketDataReportExample;
import com.cana.asset.service.IABSMarketResearchService;
import com.cana.asset.service.convertors.MarketDataConvertor;
import com.cana.vbam.common.asset.dto.EchartsQuery;
import com.cana.vbam.common.asset.dto.EchartsResponseDTO;
import com.cana.vbam.common.asset.dto.MarketDataListDTO;
import com.cana.vbam.common.asset.dto.MarketDataProductDTO;
import com.cana.vbam.common.asset.dto.MarketDataQueryDTO;
import com.cana.vbam.common.asset.dto.MarketDataSummaryDTO;
import com.cana.vbam.common.asset.enums.SupervisionAgencyEnum;
import com.cana.vbam.common.utils.MoneyArithUtil;
import com.travelzen.framework.common.PageList;
import com.travelzen.framework.core.exception.WebException;
import com.travelzen.framework.core.time.DateTimeUtil;
import com.travelzen.framework.core.util.MoneyUtil;
import com.travelzen.framework.util.DateUtils;

@Service
public class ABSMarketResearchServiceImpl implements IABSMarketResearchService {

	@Resource
	private AssetMarketDataReportMapper assetMarketDataReportMapper;
	
	@Resource
	private AssetMarketDataProductMapper assetMarketDataProductMapper;
	
	@Resource
	private AssetMarketDataProjectMapper assetMarketDataProjectMapper;
	
	@Resource
	private MarketDataMapper marketDataMapper;
	
	@Override
	public List<String> getUnderlyingAssetType() {
		List<String> underlyingAssetTypes = new ArrayList<>();
		underlyingAssetTypes = marketDataMapper.selectUnderlyingAssetType();
		return underlyingAssetTypes;
	}
	
	@Override
	public EchartsResponseDTO getMarketDataReport(EchartsQuery queryDTO) {
		EchartsResponseDTO echartsResponseDTO = new EchartsResponseDTO();
		String startDate = queryDTO.getStartDate(); 
		String endDate = queryDTO.getEndDate();     
		if(compareDate(startDate, endDate)>0)
			throw WebException.instance("开始日期不能大于结束日期");
		List<String> dateList = new ArrayList<>();
		List<Object[]> resultList = new ArrayList<>();
		while(compareDate(startDate, endDate)<=0){
			dateList.add(startDate);
			startDate = DateTimeUtil.format(DateTimeUtil.addMonth(DateTimeUtil.getDate(startDate, "yyyy-MM"),1), "yyyy-MM");
		}
//		MarketResearchSummary[] values = MarketResearchSummary.values(); 
		List<String> underlyingAssetTypes = marketDataMapper.selectUnderlyingAssetType();
		if(CollectionUtils.isEmpty(underlyingAssetTypes)){
			echartsResponseDTO.setArray(dateList.toArray());
			echartsResponseDTO.setEnumArray(new Object[0]);
			echartsResponseDTO.setList(resultList);
			return echartsResponseDTO;
		}
		//计算 发行产品金额
//		for(int i =0 ;i<values.length-1;i++){
		for(String type : underlyingAssetTypes){
			List<BigDecimal> amountList = new ArrayList<>();
			//根据dateList 和category 计算数据个数
			AssetMarketDataReportExample example = new AssetMarketDataReportExample();
			example.createCriteria().andUnderlyingAssetTypeEqualTo(type).andMonthIn(dateList);
			List<AssetMarketDataReport> assetMarketDataReports = assetMarketDataReportMapper.selectByExample(example);
			for(int j=0;j<dateList.size();j++){
				BigDecimal sum = BigDecimal.ZERO;
				if(CollectionUtils.isNotEmpty(assetMarketDataReports))
					for(AssetMarketDataReport assetMarketDataReport : assetMarketDataReports){
						if(dateList.get(j).equals(assetMarketDataReport.getMonth()))
							sum = MoneyArithUtil.divide(assetMarketDataReport.getIssueAmount(), new BigDecimal(100), 2);
				}
				amountList.add(sum);
			}
			resultList.add(amountList.toArray());
		}
		//计算 发行产品数量
		List<Double> numList = new ArrayList<>();
		AssetMarketDataReportExample example = new AssetMarketDataReportExample();
		example.createCriteria().andMonthIn(dateList);
		List<AssetMarketDataReport> assetMarketDataReports = assetMarketDataReportMapper.selectByExample(example);
		for(int j=0;j<dateList.size();j++){
			Double sum = 0d;
			if(CollectionUtils.isNotEmpty(assetMarketDataReports))
				for(AssetMarketDataReport assetMarketDataReport : assetMarketDataReports){
					if(dateList.get(j).equals(assetMarketDataReport.getMonth()))
						sum += assetMarketDataReport.getIssueNum().doubleValue();
			}
			numList.add(sum);
		}
		resultList.add(numList.toArray());
		
		echartsResponseDTO.setArray(dateList.toArray());
		underlyingAssetTypes.add("发行产品数量");
		echartsResponseDTO.setEnumArray(underlyingAssetTypes.toArray());
		echartsResponseDTO.setList(resultList);
		return echartsResponseDTO;
	}
	
	private int compareDate(String startDate, String endDate) {
		DateFormat df = new SimpleDateFormat("yyyy-MM");
		try {
			Date dt1 = df.parse(startDate);
			Date dt2 = df.parse(endDate);
			if (dt1.getTime() > dt2.getTime()) {
				return 1;  // dt1在dt2前
			} else if (dt1.getTime() < dt2.getTime()) {
				return -1;  // dt1在dt2后
			} else {
				return 0;
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return 0;
	}

	/**
	 * 
	 */
	@Override
	public MarketDataSummaryDTO getMarketDataSummary() {
		//全部数据   最近30天数据
		MarketDataSummaryDTO marketDataSummaryDTO = getMarketDataSummaryDTO();
		
		//最小、最大日期
		AssetMarketDataReportExample reportExample = new AssetMarketDataReportExample();
		reportExample.setOrderByClause("month asc");
		List<AssetMarketDataReport> reports = assetMarketDataReportMapper.selectByExample(reportExample);
		if(CollectionUtils.isNotEmpty(reports)){
			marketDataSummaryDTO.setMinDate(reports.get(0).getMonth());
			marketDataSummaryDTO.setMaxDate(reports.get(reports.size()-1).getMonth());
		}else{
			//数据库为空  默认页面查询当前日期
			marketDataSummaryDTO.setMinDate(new SimpleDateFormat("yyyy-MM").format(new Date()));
			marketDataSummaryDTO.setMaxDate(new SimpleDateFormat("yyyy-MM").format(new Date()));
		}
		//最新发行企业产品
		AssetMarketDataProjectExample projectExample = new AssetMarketDataProjectExample();
		projectExample.setOrderByClause("value_date desc");
		projectExample.setLimitStart(0);
		projectExample.setLimitEnd(10);
		List<AssetMarketDataProject> projects = assetMarketDataProjectMapper.selectByExample(projectExample);
		List<MarketDataListDTO> marketDataListDTOs = MarketDataConvertor.convertorProjects2ListDTO(projects);
		marketDataSummaryDTO.setRecentIssueProducts(marketDataListDTOs);
		return marketDataSummaryDTO;
	}
	
	private MarketDataSummaryDTO getMarketDataSummaryDTO() {
		MarketDataSummaryDTO marketDataSummaryDTO = new MarketDataSummaryDTO();
		AssetMarketDataProjectExample projectExample = new AssetMarketDataProjectExample();
		int issueTotalNum = getIssueTotalNum(projectExample);
		marketDataSummaryDTO.setIssueTotalNum(issueTotalNum);
		String issueTotalAmount = getIssueTotalAmount(projectExample);
		marketDataSummaryDTO.setIssueTotalAmount(issueTotalAmount);
		
		AssetMarketDataProjectExample projectExample2 = new AssetMarketDataProjectExample();
		String now = DateUtils.format(new Date(), 19);
		String start = DateTimeUtil.addDay10(now, -30);
		projectExample2.createCriteria().andValueDateGreaterThanOrEqualTo(start).andValueDateLessThanOrEqualTo(now);
	
		int recentThirtyNum = getIssueTotalNum(projectExample2);
		marketDataSummaryDTO.setRecentThirtyNum(recentThirtyNum);
		String recentThirtyAmount = getIssueTotalAmount(projectExample2);
		marketDataSummaryDTO.setRecentThirtyAmount(recentThirtyAmount);
		
		return marketDataSummaryDTO;
	}

	/**
	 * 发行产品总额
	 * @param projectExample
	 * @return
	 */
	private String getIssueTotalAmount(AssetMarketDataProjectExample projectExample) {
		Long issueTotalAmount = 0l;
		List<AssetMarketDataProject> projects = assetMarketDataProjectMapper.selectByExample(projectExample);
		if(CollectionUtils.isEmpty(projects))
			issueTotalAmount = 0l; 
		else{
			for(AssetMarketDataProject assetMarketDataProject : projects){
				issueTotalAmount += assetMarketDataProject.getIssueTotalAmount().longValue();
			}
		}
		return MoneyArithUtil.convertMoneyToString(issueTotalAmount);
	}

	/**
	 * 发行产品总数
	 * @param projectExample
	 * @return
	 */
	private int getIssueTotalNum(AssetMarketDataProjectExample projectExample) {
		return assetMarketDataProjectMapper.countByExample(projectExample);
	}

	/**
	 * 
	 */
	@Override
	public PageList<MarketDataListDTO> getMarketDataSearchList(MarketDataQueryDTO queryDTO) {
		PageList<MarketDataListDTO> result = new PageList<>();
		//project
		AssetMarketDataProjectExample projectExample = new AssetMarketDataProjectExample();
		Criteria projectCriteria = projectExample.createCriteria(); 
		if(StringUtils.isNotBlank(queryDTO.getSupervisionAgency()))
			projectCriteria.andSupervisionAgencyEqualTo(queryDTO.getSupervisionAgency());
		if(StringUtils.isNotBlank(queryDTO.getUnderlyingAssetType()))
			projectCriteria.andUnderlyingAssetTypeEqualTo(queryDTO.getUnderlyingAssetType());
		if(StringUtils.isNotBlank(queryDTO.getValueDate()))
			projectCriteria.andValueDateLike(queryDTO.getValueDate()+"%");
		if(StringUtils.isNotBlank(queryDTO.getOriginator()))
			projectCriteria.andOriginatorEqualTo(queryDTO.getOriginator());
		if(StringUtils.isNotBlank(queryDTO.getIssuer()))
			projectCriteria.andIssuerEqualTo(queryDTO.getIssuer());
		projectExample.setOrderByClause("create_time desc");
		projectExample.setLimitStart((queryDTO.getPage() - 1) * queryDTO.getPageSize());
		projectExample.setLimitEnd(queryDTO.getPageSize());
		List<AssetMarketDataProject> projects = assetMarketDataProjectMapper.selectByExample(projectExample); 
		if(CollectionUtils.isEmpty(projects))
			return result;
		
		//封装页面数据
		List<MarketDataListDTO> marketDataListDTOs = MarketDataConvertor.convertorProjects2ListDTO(projects);
		
		result.setRecords(marketDataListDTOs);
		result.setTotalRecords(assetMarketDataProjectMapper.countByExample(projectExample));
		return result;
	}

	/**
	 * 封装详情页面数据
	 * @param projects
	 * @param products
	 * @return
	 */
	private MarketDataListDTO getMarketDataListDTOs(AssetMarketDataProject project,List<AssetMarketDataProduct> products) {
		MarketDataListDTO marketDataDTO = new MarketDataListDTO();
		marketDataDTO.setId(project.getId());
		marketDataDTO.setProjectName(project.getProjectName());
		marketDataDTO.setIssueTotalAmount(MoneyUtil.cent2Yuan(project.getIssueTotalAmount()));
		marketDataDTO.setSupervisionAgency(SupervisionAgencyEnum.valueOf(project.getSupervisionAgency()).name());
		marketDataDTO.setSupervisionAgencyDesc(SupervisionAgencyEnum.valueOf(project.getSupervisionAgency()).desc());
		marketDataDTO.setUnderlyingAssetType(project.getUnderlyingAssetType());
		marketDataDTO.setIssueDate(project.getValueDate());
		if(null != project.getAaaAverageInterestRate())
			marketDataDTO.setAAAAverageInterestRate(MoneyArithUtil.convertInterestRateToString(project.getAaaAverageInterestRate()));
		if(null != project.getPriorityAverageInterestRate())
			marketDataDTO.setPriorityAverageInterestRate(MoneyArithUtil.convertInterestRateToString(project.getPriorityAverageInterestRate()));
		marketDataDTO.setOriginator(project.getOriginator());
		marketDataDTO.setIssuer(project.getIssuer());
		
		List<MarketDataProductDTO> productDTOs = new ArrayList<>();
		for(AssetMarketDataProduct assetMarketDataProduct : products){
			MarketDataProductDTO marketDataProductDTO = new MarketDataProductDTO();
			marketDataProductDTO.setId(assetMarketDataProduct.getId());
			marketDataProductDTO.setProductName(assetMarketDataProduct.getProductName());
			marketDataProductDTO.setIssueAmount(MoneyUtil.cent2Yuan(assetMarketDataProduct.getIssueAmount()));
			marketDataProductDTO.setDebtRating(assetMarketDataProduct.getDebtRating());
			if(null !=assetMarketDataProduct.getInterestRate() && assetMarketDataProduct.getInterestRate().compareTo(BigDecimal.ZERO) !=0)
				marketDataProductDTO.setInterestRate(MoneyArithUtil.convertInterestRateToString(assetMarketDataProduct.getInterestRate()));
			marketDataProductDTO.setRatingAgency(assetMarketDataProduct.getRatingAgency());
			marketDataProductDTO.setProjectId(assetMarketDataProduct.getProjectId());
			productDTOs.add(marketDataProductDTO);
		}
		if(CollectionUtils.isNotEmpty(productDTOs)){
			if(null != project.getAaaAverageInterestRate())
				productDTOs.get(0).setAaaAverageInterestRate(MoneyArithUtil.convertInterestRateToString(project.getAaaAverageInterestRate()));
			if(null != project.getPriorityAverageInterestRate())
				productDTOs.get(0).setPriorityAverageInterestRate(MoneyArithUtil.convertInterestRateToString(project.getPriorityAverageInterestRate()));
		}
		marketDataDTO.setProductDTOs(productDTOs);

		return marketDataDTO;
	}

	@Override
	public MarketDataSummaryDTO getMarketProduct() {
		//全部数据   最近30天数据
		MarketDataSummaryDTO marketDataSummaryDTO = getMarketDataSummaryDTO();
		return marketDataSummaryDTO;
	}

	@Override
	public MarketDataListDTO getMarketDataDetail(String id) {
		if(StringUtils.isBlank(id))
			throw WebException.instance("产品id为空");
		AssetMarketDataProject project = assetMarketDataProjectMapper.selectByPrimaryKey(id); 
		if(project == null)
			throw WebException.instance("该产品不存在");
		AssetMarketDataProductExample productExample = new AssetMarketDataProductExample();
		productExample.createCriteria().andProjectIdEqualTo(id);
		productExample.setOrderByClause("id asc");
		List<AssetMarketDataProduct> products = assetMarketDataProductMapper.selectByExample(productExample); 
		MarketDataListDTO marketDataListDTO = getMarketDataListDTOs(project, products);
		return marketDataListDTO;
	}

}
