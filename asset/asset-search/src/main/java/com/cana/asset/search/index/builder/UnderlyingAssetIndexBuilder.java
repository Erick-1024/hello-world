package com.cana.asset.search.index.builder;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.lucene.index.IndexWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.cana.asset.search.index.bean.UnderlyingAssetIndexBean;
import com.cana.asset.search.index.bean.UnderlyingAssetIndexBean.UnderlyingAssetFieldEnum;
import com.cana.asset.service.transaction.IUnderlyingAssetQueryTransactionService;
import com.cana.asset.service.transaction.IUnderlyingAssetTransactionService;
import com.cana.vbam.common.asset.enums.UnderlyingAssetPoolStatus;
import com.cana.vbam.common.asset.underlyingasset.dto.UnderlyingAssetDTO;
import com.cana.vbam.common.asset.underlyingasset.dto.UnderlyingAssetQueryDTO;
import com.cana.vbam.common.asset.underlyingasset.dto.UnderlyingAssetUpdateDTO;
import com.cana.vbam.common.utils.Constants;
import com.cana.vbam.common.utils.InterestRateConverter;
import com.esotericsoftware.minlog.Log;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.travelzen.framework.core.exception.WebException;
import com.travelzen.framework.lucene.index.IndexBuilder;
import com.travelzen.framework.lucene.index.document.DocumentBuilder;
import com.travelzen.framework.lucene.index.init.InitIndexUtil;
import com.travelzen.framework.lucene.index.query.bean.BooleanQueryExample;

@Service
public class UnderlyingAssetIndexBuilder implements IUnderlyingAssetIndexBuilder{

	private static final int DATA_MAX_NUM = 12000;
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private DocumentBuilder documentBuilder = DocumentBuilder.getDocumentBuilder();
	
	@Resource
	private IUnderlyingAssetQueryTransactionService underlyingAssetQueryTransactionService;
	
	@Resource
	private IUnderlyingAssetTransactionService underlyingAssetTransactionService;
	
	@PostConstruct
	private void initIndexConfig(){
		InitIndexUtil.initIndex(Constants.INDEX_FILE_PATH, true, null, false, UnderlyingAssetIndexBean.class);
	}
	
	@Override
	public void createInedx() throws Exception {
		IndexBuilder indexBuilder = IndexBuilder.getIndexBuilder();
		
		IndexWriter	indexWriter = indexBuilder.initIndexSystem(UnderlyingAssetIndexBean.class);
		
//		//重置标记位
//		updateUnderlyingAssetIndexFlag(null, 0, true);
//		//删除所以索引
//		indexWriter.deleteAll();
//		
//		List<UnderlyingAssetDTO> assetDTOList  = Lists.newArrayList();
//		do{
//			logger.info("获取数据开始");
//			assetDTOList.clear();
//			UnderlyingAssetQueryDTO queryDTO = new UnderlyingAssetQueryDTO();
//			queryDTO.setAssetPoolStatus(UnderlyingAssetPoolStatus.ENTERING);
//			queryDTO.setLoadExtraData(true);
//			queryDTO.setPageSize(1000);
//			assetDTOList = underlyingAssetQueryTransactionService.queryUnderlyingAssetData(queryDTO);
//			logger.info("获取数据条数[{}]", assetDTOList.size());
//			List<UnderlyingAssetIndexBean> assetIndexBeanList = convertDTO2IndexBean(assetDTOList);
//			indexWriter.addDocuments(documentBuilder.generateDocument(assetIndexBeanList));
//			//建立索引后更新标志位
//			updateUnderlyingAssetIndexFlag(assetDTOList, 1, true);
//			logger.info("更新标志结束");
//		}while(CollectionUtils.isNotEmpty(assetDTOList));
//	
//		indexWriter.commit();
//		//更新reader
//		indexBuilder.updateIndexSearch(UnderlyingAssetIndexBean.class);
		
		//删除所以索引
		indexWriter.deleteAll();
		
		List<UnderlyingAssetDTO> assetDTOList  = Lists.newArrayList();
		List<UnderlyingAssetDTO> assetDTOWriteList  = Lists.newArrayList();
		Set<String> preIdList = new HashSet<>();
		Date createTimeTmp = null;
		UnderlyingAssetQueryDTO queryDTO = new UnderlyingAssetQueryDTO();
		queryDTO.setAssetPoolStatus(UnderlyingAssetPoolStatus.ENTERING);
		queryDTO.setLoadExtraData(true);
		queryDTO.setPageSize(DATA_MAX_NUM);
		do{
			logger.info("全量获取数据开始");
			
			queryDTO.setCreateTime(createTimeTmp);
			Log.info("全量获取数据start_time[" + createTimeTmp + "]");
			
			assetDTOList = underlyingAssetQueryTransactionService.queryUnderlyingAssetData(queryDTO);
			logger.info("获取数据条数[{}]", assetDTOList.size());
			//删除重复
			assetDTOWriteList.clear();
			for(UnderlyingAssetDTO assetDTO : assetDTOList){
				if(preIdList.contains(assetDTO.getLoanNo()))
					continue;
				assetDTOWriteList.add(assetDTO);
			}
			
			//添加
			List<UnderlyingAssetIndexBean> assetIndexBeanList = convertDTO2IndexBean(assetDTOWriteList);
			indexWriter.addDocuments(documentBuilder.generateDocument(assetIndexBeanList));
			
			Date createTime = assetDTOList.get(assetDTOList.size() -1).getCreateTime();
			if(null != createTimeTmp && createTime.compareTo(createTimeTmp) == 0){
				throw WebException.instance("");
			}
			createTimeTmp = createTime;
			
			preIdList.clear();
			for(UnderlyingAssetDTO assetDTO :  assetDTOWriteList){
				preIdList.add(assetDTO.getLoanNo());
			}
		}while(CollectionUtils.isNotEmpty(assetDTOList) && assetDTOList.size() == DATA_MAX_NUM);
	
		indexWriter.commit();
		//更新reader
		indexBuilder.updateIndexSearch(UnderlyingAssetIndexBean.class);
	}

	@Override
	public void updateIndex(List<String> assetIds) throws Exception{
		if(CollectionUtils.isEmpty(assetIds))
			throw WebException.instance("基础资产id List为空");
		logger.info("underlyingAsset更新索引开始：更新条数[{}]", assetIds.size());
		InitIndexUtil.initIndex(Constants.INDEX_FILE_PATH, true, null, false, UnderlyingAssetIndexBean.class);
		IndexBuilder indexBuilder = IndexBuilder.getIndexBuilder();
		
		for(int i=0; i<assetIds.size();){
			int j = (i + 1000) > assetIds.size() ? assetIds.size() : (i + 1000);
			List<String> assetIdSubs = assetIds.subList(i, j);
			logger.info("underlyingAsset：更新条数[{}]~[{}]：[{}]", i, j, assetIdSubs);
			List<UnderlyingAssetDTO> assetDTOList = underlyingAssetQueryTransactionService.getUnderlyingAssetDetailList(assetIdSubs, UnderlyingAssetPoolStatus.ENTERING);
			BooleanQueryExample stuexa = new BooleanQueryExample();
			stuexa.inCollection(UnderlyingAssetFieldEnum.loanId.name(), assetIdSubs);
			if(CollectionUtils.isNotEmpty(assetDTOList)){
				List<UnderlyingAssetIndexBean> assetIndexBeanList = convertDTO2IndexBean(assetDTOList);
				indexBuilder.updateIndexs(stuexa.getBuildedQuery(), assetIndexBeanList, UnderlyingAssetIndexBean.class);
			}else{
				indexBuilder.deleteIndex(stuexa.getBuildedQuery(), UnderlyingAssetIndexBean.class);
			}
			i = j;
		}
		
		logger.info("underlyingAsset更新索引结束");
	}

	/**
	 * 更新基础资产索引标志位（未建立索引为０，相反为１）
	 * @param assetDTOList
	 * @param num
	 * @param isAndOperat
	 */
	private void updateUnderlyingAssetIndexFlag(List<UnderlyingAssetDTO> assetDTOList, int num, boolean isAndOperat){
		UnderlyingAssetUpdateDTO updateDTO = new UnderlyingAssetUpdateDTO();
		updateDTO.setAndOperation(isAndOperat);
		Map<Integer, Integer> positionAndNumMap = Maps.newHashMap();
		positionAndNumMap.put(0, num);
		updateDTO.setOperatePositionAndNum(positionAndNumMap);
		if(CollectionUtils.isNotEmpty(assetDTOList)){
			List<String> idList = Lists.newArrayList();
			for(UnderlyingAssetDTO assetDTO : assetDTOList){
				idList.add(assetDTO.getLoanNo());
			}
			updateDTO.setUnderlyingAssetIdList(idList);
		}
		underlyingAssetTransactionService.updateUnderlyingAssetInCondition(updateDTO);
	}
	
	private List<UnderlyingAssetIndexBean> convertDTO2IndexBean(List<UnderlyingAssetDTO> assetDTOList){
		List<UnderlyingAssetIndexBean> indexBeanList = Lists.newArrayList();
		for(UnderlyingAssetDTO assetDTO : assetDTOList){
			UnderlyingAssetIndexBean indexBean = new UnderlyingAssetIndexBean();
			indexBean.setLoanId(assetDTO.getLoanNo());
			indexBean.setFactorId(assetDTO.getFactorId());
			if(StringUtils.isNotBlank(assetDTO.getBusinessContractNo()))
				indexBean.setBusinessContractNo(assetDTO.getBusinessContractNo());
			if(null == indexBean.getBusinessProduct())
				indexBean.setBusinessProduct(assetDTO.getBusinessProduct());
			if(StringUtils.isNotBlank(assetDTO.getCustomerName()))
				indexBean.setCustomerName(assetDTO.getCustomerName());
			if(StringUtils.isNotBlank(assetDTO.getCustomerEconomicCategory()))
				indexBean.setCustomerEconomicCategory(assetDTO.getCustomerEconomicCategory());
			if(StringUtils.isNotBlank(assetDTO.getCustomerCity()))
				indexBean.setCustomerCity(assetDTO.getCustomerCity());
			if(StringUtils.isNotBlank(assetDTO.getCustomerIndustry()))
				indexBean.setCustomerIndustry(assetDTO.getCustomerIndustry());
			if(StringUtils.isNotBlank(assetDTO.getCounterparty()))
				indexBean.setCounterparty(assetDTO.getCounterparty());
			if(StringUtils.isNotBlank(assetDTO.getCounterpartyEconomicCategory()))
				indexBean.setCounterpartyEconomicCategory(assetDTO.getCounterpartyEconomicCategory());
			if(StringUtils.isNotBlank(assetDTO.getCounterpartyCity()))
				indexBean.setCounterpartyCity(assetDTO.getCounterpartyCity());
			if(StringUtils.isNotBlank(assetDTO.getCounterpartyIndustry()))
				indexBean.setCounterpartyIndustry(assetDTO.getCounterpartyIndustry());
			if(StringUtils.isNotBlank(assetDTO.getCounterpartyRating()))
				indexBean.setCounterpartyRating(assetDTO.getCounterpartyRating());
			if(StringUtils.isNotBlank(assetDTO.getLoanDate()))
				indexBean.setLoanDate(assetDTO.getLoanDate());
			if(StringUtils.isNotBlank(assetDTO.getSettleInterestDate()))
				indexBean.setSettlementDate(assetDTO.getSettleInterestDate());
			if(StringUtils.isNotBlank(assetDTO.getDueDate()))
				indexBean.setDueDate(assetDTO.getDueDate());
			if(StringUtils.isNotBlank(assetDTO.getRepaymentMethod()))
				indexBean.setRepaymentMethod(assetDTO.getRepaymentMethod());
			if(StringUtils.isNotBlank(assetDTO.getGuaranteeInfo()))
				indexBean.setGuaranteeInfo(assetDTO.getGuaranteeInfo());
			if(StringUtils.isNotBlank(assetDTO.getGuaranteeCompanyInfo()))
				indexBean.setGuaranteeCompanyInfo(assetDTO.getGuaranteeCompanyInfo());
			if(StringUtils.isNotBlank(assetDTO.getGuaranteeGoodsNo()))
				indexBean.setGuaranteeGoodsNo(assetDTO.getGuaranteeGoodsNo());
			indexBean.setInvoiceAmount(assetDTO.getInvoiceAmountNum());
			indexBean.setInvoiceBalance(assetDTO.getInvoiceBalanceNum());
			indexBean.setFinanceAmount(assetDTO.getFinanceAmountNum());
			indexBean.setFinanceBalance(assetDTO.getFinanceBalanceNum());
			indexBean.setInterestRate(InterestRateConverter.getAnnualRate(assetDTO.getInterestRateUnit(),
					assetDTO.getInterestRateNum(), assetDTO.getDayCountConvention()).doubleValue());
			indexBean.setAccountInterest(assetDTO.getTotalInterestAmount());
			if(null != assetDTO.getPenaltyRate())
				indexBean.setPenaltyRate(assetDTO.getPenaltyRate().doubleValue());
			indexBean.setAccountTotalAmount(assetDTO.getTotalAmount());
			indexBean.setCreditLimit(assetDTO.getCreditLimitNum());
			indexBean.setCreditBalance(assetDTO.getCreditBalanceNum());
			indexBean.setCounterpartyLimit(assetDTO.getCounterpartyLimitNum());
			indexBean.setCounterpartyBalance(assetDTO.getCounterpartyBalanceNum());
			indexBean.setLoanPeriod(assetDTO.getLoanPeriod());
			indexBean.setSpecialProgramId(assetDTO.getSpecialProgramId());
			indexBean.setSpecialProgramName(assetDTO.getSpecialProgramName());
			indexBean.setUpdateTime(assetDTO.getUpdateTime());
			indexBeanList.add(indexBean);
		}
		return indexBeanList;
	}
}
