package com.cana.asset.service.transaction.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.cana.asset.dao.mapper.gen.AssetInvoiceBasicInfoMapper;
import com.cana.asset.dao.mapper.gen.AssetInvoiceInfoMapper;
import com.cana.asset.dao.mapper.gen.ExpenseMapper;
import com.cana.asset.dao.po.AssetInvoiceBasicInfo;
import com.cana.asset.dao.po.AssetInvoiceBasicInfoExample;
import com.cana.asset.dao.po.AssetInvoiceInfo;
import com.cana.asset.dao.po.AssetInvoiceInfoExample;
import com.cana.asset.dao.po.Expense;
import com.cana.asset.dao.po.ExpenseExample;
import com.cana.asset.dao.utils.IDGenerator;
import com.cana.asset.service.IAssetInvoiceService;
import com.cana.asset.service.convertors.InvoiceConvertor;
import com.cana.asset.service.transaction.IAssetCreditTransactionService;
import com.cana.asset.service.transaction.IAssetFactorBusinessTransactionService;
import com.cana.asset.service.transaction.IAssetInvoiceTransactionService;
import com.cana.asset.service.transaction.IAssetLoanInfoTransactionService;
import com.cana.asset.service.transaction.IAssetUserPrivilegeTransactionService;
import com.cana.asset.service.transaction.util.AssetInvoicePersistenceValidator;
import com.cana.vbam.common.asset.dto.BusinessCounterpartyDTO;
import com.cana.vbam.common.asset.dto.ExpenseDTO;
import com.cana.vbam.common.asset.dto.FactorBusinessDTO;
import com.cana.vbam.common.asset.dto.InvoiceExcelInfoDTO;
import com.cana.vbam.common.asset.dto.InvoiceInfoDTO;
import com.cana.vbam.common.asset.dto.InvoiceListDTO;
import com.cana.vbam.common.asset.dto.InvoiceQueryDTO;
import com.cana.vbam.common.asset.dto.InvoiceRedisDTO;
import com.cana.vbam.common.asset.enums.BusinessProduct;
import com.cana.vbam.common.asset.enums.ExpenseType;
import com.cana.vbam.common.member.dto.user.UserSessionDTO;
import com.cana.vbam.common.member.enums.user.UserType;
import com.travelzen.framework.core.common.ReturnCode;
import com.travelzen.framework.core.exception.WebException;
import com.travelzen.framework.core.time.DateTimeUtil;
import com.travelzen.framework.core.util.MoneyUtil;
import com.travelzen.framework.core.util.NumberUtils;

@Service
public class AssetInvoiceTransactionServiceImpl implements IAssetInvoiceTransactionService {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Resource
	private IAssetCreditTransactionService assetCreditTransactionService;
	
	@Resource
	private IAssetLoanInfoTransactionService assetLoanInfoTransactionService;
	
	@Resource
	private IAssetFactorBusinessTransactionService assetFactorBusinessTransactionService;
	
	@Resource
	private IAssetUserPrivilegeTransactionService assetUserPrivilegeTransactionService;
	
	@Resource
	private IAssetInvoiceService assetInvoiceService;
	
	@Resource
	private AssetInvoiceBasicInfoMapper assetInvoiceBasicInfoMapper;
	
	@Resource
	private AssetInvoiceInfoMapper assetInvoiceInfoMapper;
	
	@Resource
	private ExpenseMapper expenseMapper;
	
	@Override
	public void updateInvManage(InvoiceListDTO invoiceListDTO,UserSessionDTO user) {
		String basicId = invoiceListDTO.getId();
		String businessContractNo = invoiceListDTO.getBusinessContractNo();
		assetCreditTransactionService.lockByBussinessContractNo(businessContractNo);
		boolean flag = assetLoanInfoTransactionService.checkContractNoHasLoan(businessContractNo);
		List<InvoiceInfoDTO> invoiceInfoDTOs = invoiceListDTO.getInvoiceInfoDTOs();
		List<ExpenseDTO> expenseDTOs = invoiceListDTO.getExpenseDTOs();
		AssetInvoicePersistenceValidator.checkInvoice(invoiceListDTO);
		//数据唯一性检验
		uniqueCheck(invoiceInfoDTOs);
		//同一个业务合同、交易对手的融资比例要相同
		checkRatio(invoiceInfoDTOs);
		if(StringUtils.isBlank(basicId)){
			//业务合同号 唯一性检验
			AssetInvoiceBasicInfoExample basicExample = new AssetInvoiceBasicInfoExample();
			basicExample.createCriteria().andBusinessContractNoEqualTo(businessContractNo);
			List<AssetInvoiceBasicInfo> basicInvoiceInfos = assetInvoiceBasicInfoMapper.selectByExample(basicExample);
			if (CollectionUtils.isNotEmpty(basicInvoiceInfos)) {
				throw WebException.instance("该业务合同号已存在");
			}
			//新增应收账款数据
			insert(invoiceListDTO,user);
		}else{
			//修改 应收账款基础信息  账款数据信息  费用信息
			updateInvoice(basicId,invoiceInfoDTOs,expenseDTOs,flag);
		}
	}


	/**
	 * 应收账款修改
	 * 
	 *  只能修改未放款的应收账款，后台已放款的应收账款数据不变
	 *  未放款的数据，根据前台接受的数据做修改。
	 * 
	 * @param basicId
	 * @param invoiceInfoDTOs
	 * @param expenseDTOs
	 * @param flag
	 */
	private void updateInvoice(String basicId,List<InvoiceInfoDTO> invoiceInfoDTOs,List<ExpenseDTO> expenseDTOs,Boolean flag) {
		AssetInvoiceInfoExample invExample = new AssetInvoiceInfoExample();
		invExample.createCriteria().andInvoiceBaseIdEqualTo(basicId);
		List<AssetInvoiceInfo> invList = assetInvoiceInfoMapper.selectByExample(invExample);
		List<AssetInvoiceInfo> loanedInvList = new ArrayList<>();
		List<AssetInvoiceInfo> unloanInvList = new ArrayList<>();
		if(CollectionUtils.isNotEmpty(invList)){
			for(AssetInvoiceInfo assetInvoiceInfo : invList){
				if(StringUtils.isBlank(assetInvoiceInfo.getLoanInfoId()))
					unloanInvList.add(assetInvoiceInfo);
				else
					loanedInvList.add(assetInvoiceInfo);			
			}
		}
		List<InvoiceInfoDTO> loanedInvDTOs = new ArrayList<>();
		List<InvoiceInfoDTO> unloanInvDTOs = new ArrayList<>();
		if(CollectionUtils.isNotEmpty(invoiceInfoDTOs)){
			for(InvoiceInfoDTO invoiceInfoDTO : invoiceInfoDTOs){
				if(StringUtils.isBlank(invoiceInfoDTO.getLoanInfoId()))
					unloanInvDTOs.add(invoiceInfoDTO);
				else
					loanedInvDTOs.add(invoiceInfoDTO);
			}
		}
		if(loanedInvList.size() != loanedInvDTOs.size())
			throw WebException.instance("应收账款信息变更，提交失败");
		
		updateAssetInvoiceInfo(basicId,unloanInvList,unloanInvDTOs);
		updateAssetExpense(basicId,expenseDTOs,flag);
		updateAssetInvoiceBasicInfo(basicId,loanedInvList,unloanInvDTOs);
	}

	/**
	 * 同一个业务合同号下，交易对手和单证号码不能相同
	 * @param invoiceInfoDTOs
	 */
	private void uniqueCheck(List<InvoiceInfoDTO> invoiceInfoDTOs) {
		if(CollectionUtils.isNotEmpty(invoiceInfoDTOs)){
			for(int i=0;i<invoiceInfoDTOs.size()-1;i++){
				for(int j=i+1;j<invoiceInfoDTOs.size();j++){
					InvoiceInfoDTO now = invoiceInfoDTOs.get(i);
					InvoiceInfoDTO next = invoiceInfoDTOs.get(j);
					if(now.getCounterpartyId().equals(next.getCounterpartyId())&&now.getInvoiceNo().equals(next.getInvoiceNo())){
						throw WebException.instance("账款数据不合法,有重复数据");
					}
				}
			}
		}
	}

	/**
	 * 同一个业务合同、交易对手的融资比例要相同
	 * @param invoiceInfoDTOs
	 */
	private void checkRatio(List<InvoiceInfoDTO> invoiceInfoDTOs) {
		if(CollectionUtils.isNotEmpty(invoiceInfoDTOs)){
			for(int i=0;i<invoiceInfoDTOs.size()-1;i++){
				for(int j=i+1;j<invoiceInfoDTOs.size();j++){
					InvoiceInfoDTO now = invoiceInfoDTOs.get(i);
					InvoiceInfoDTO next = invoiceInfoDTOs.get(j);
					if(now.getCounterpartyId().equals(next.getCounterpartyId())&&now.getFinancingRatio().compareTo(next.getFinancingRatio())!=0){
						throw WebException.instance("账款数据融资比例不合法");
					}
				}
			}
		}
	}
	
	/**
	 * 修改 应收账款基础信息
	 * @param basicId
	 * @param loanedInvList
	 * @param unloanInvDTOs
	 */
	private void updateAssetInvoiceBasicInfo(String basicId,List<AssetInvoiceInfo>loanedInvList, List<InvoiceInfoDTO> unloanInvDTOs) {
		AssetInvoiceBasicInfo basicInfo = new AssetInvoiceBasicInfo();
		basicInfo.setId(basicId);
		basicInfo.setCountInvoice(loanedInvList.size()+unloanInvDTOs.size());
		basicInfo.setSumInvoiceAmt(updateSumInvoiceAmt(loanedInvList,unloanInvDTOs));
		assetInvoiceBasicInfoMapper.updateByPrimaryKeySelective(basicInfo);
	}
	
	private Long updateSumInvoiceAmt(List<AssetInvoiceInfo> loanedInvList, List<InvoiceInfoDTO> unloanInvDTOs) {
		BigDecimal invAmt = new BigDecimal(0);
		for(AssetInvoiceInfo AssetInvoiceInfo : loanedInvList){
			invAmt = invAmt.add(new BigDecimal(MoneyUtil.cent2Yuan(AssetInvoiceInfo.getInvoiceAmt())));
		}
		for(InvoiceInfoDTO invoiceInfoDTO : unloanInvDTOs){
			String parseMoney = MoneyUtil.parseMoney(invoiceInfoDTO.getInvoiceAmt()==null?"0":invoiceInfoDTO.getInvoiceAmt()); 
			invAmt = invAmt.add(new BigDecimal(parseMoney));
		}
		return Long.valueOf(MoneyUtil.yuan2Cent(invAmt));
	}

	
	/**
	 * 修改 应收账款中的费用信息
	 * @param expenseDTOs
	 */
	private void updateAssetExpense(String basicId,List<ExpenseDTO> expenseDTOs,Boolean flag) {
		if(!flag){
			ExpenseExample expenseExample = new ExpenseExample();
			expenseExample.createCriteria().andRefidEqualTo(basicId).andReftypeEqualTo(ExpenseType.INVOICE.name());
			List<Expense> expenses = expenseMapper.selectByExample(expenseExample);
			for(Expense expense : expenses){
				expenseMapper.deleteByPrimaryKey(expense.getId());
			}
			if(CollectionUtils.isNotEmpty(expenseDTOs)){
				for(ExpenseDTO expenseDTO : expenseDTOs){
					Expense expense = new Expense();
					expense.setId(IDGenerator.generateAssetExpenseId());
					expense.setRefid(basicId);
					expense.setReftype(ExpenseType.INVOICE.name());
					expense.setExpenseSubject(expenseDTO.getExpenseSubject());
					expense.setAmount(MoneyUtil.yuan2Cent(MoneyUtil.parseMoney(expenseDTO.getAmountStr()==null?"0":expenseDTO.getAmountStr())));
					expense.setCreateTime(new Date());
					expense.setUpdateTime(new Date());
					//expense.setSequence(expenseDTO.getSequence());
					expenseMapper.insert(expense);
				}
			}
		}
	}
	
	/**
	 * 修改 账款信息
	 * @param invoiceInfoDTOs
	 */
	private void updateAssetInvoiceInfo(String basicId,List<AssetInvoiceInfo> unloanInvList,List<InvoiceInfoDTO> unloanInvDTOs) {
	    //删除数据库中未放款
		for(AssetInvoiceInfo assetInvInfo : unloanInvList){
			if(StringUtils.isNotBlank(assetInvInfo.getId()))
				assetInvoiceInfoMapper.deleteByPrimaryKey(assetInvInfo.getId());
		}
		//添加 页面未放款的数据
		for(InvoiceInfoDTO invInfoDTO : unloanInvDTOs){
			AssetInvoiceInfo assetInvoiceInfo = new AssetInvoiceInfo();
			assetInvoiceInfo.setId(IDGenerator.generateAssetInvoiceInfoId());
			assetInvoiceInfo.setInvoiceBaseId(basicId);
			assetInvoiceInfo.setCounterpartyId(invInfoDTO.getCounterpartyId());
			assetInvoiceInfo.setCounterparty(invInfoDTO.getCounterparty());
			assetInvoiceInfo.setInvoiceNo(invInfoDTO.getInvoiceNo());
			assetInvoiceInfo.setNominvoiceAmt(MoneyUtil.yuan2Cent(MoneyUtil.parseMoney(invInfoDTO.getNominvoiceAmt())));
			assetInvoiceInfo.setInvoiceAmt(MoneyUtil.yuan2Cent(MoneyUtil.parseMoney(invInfoDTO.getInvoiceAmt())));
			assetInvoiceInfo.setFinancingRatio(invInfoDTO.getFinancingRatio().divide(new BigDecimal(100), 5, BigDecimal.ROUND_HALF_UP));
			assetInvoiceInfo.setInvoiceDate(invInfoDTO.getInvoiceDate());
			assetInvoiceInfo.setDueDate(invInfoDTO.getDueDate());
			assetInvoiceInfo.setUpdateTime(new Date());
			assetInvoiceInfo.setCreateTime(new Date());
			assetInvoiceInfoMapper.insert(assetInvoiceInfo);
		}
	}
	

	/**
	 * 新建应收账款
	 * @param invoiceListDTO
	 */
	private void insert(InvoiceListDTO invoiceListDTO,UserSessionDTO user) {
		String id= IDGenerator.generateAssetInvoiceBasicInfoId();
		List<InvoiceInfoDTO> invoiceInfoDTOs = invoiceListDTO.getInvoiceInfoDTOs(); 
		List<ExpenseDTO> expenseDTOs = invoiceListDTO.getExpenseDTOs();
		if (CollectionUtils.isNotEmpty(expenseDTOs)) {
			insertAssetExpense(id,expenseDTOs);
		}
		if (CollectionUtils.isNotEmpty(invoiceInfoDTOs)) {
			insertAssetInvoiceInfo(id,invoiceInfoDTOs);
		}
		
		String factorId = user.getId();
		String factorName = user.getUsername();
		String customerId = invoiceListDTO.getCustomerId();
		String customerName = invoiceListDTO.getCustomerName(); 
		String businessProduct = invoiceListDTO.getBusinessProduct(); 
		String businessContractNo = invoiceListDTO.getBusinessContractNo(); 
		String currency = invoiceListDTO.getCurrency(); 
		AssetInvoiceBasicInfo invBasicInfo = new AssetInvoiceBasicInfo();
		invBasicInfo.setId(id);
		invBasicInfo.setFactorId(factorId);
		invBasicInfo.setFactorName(factorName);
		invBasicInfo.setCustomerId(customerId);
		invBasicInfo.setCustomerName(customerName);
		invBasicInfo.setBusinessProduct(businessProduct);
		invBasicInfo.setBusinessContractNo(businessContractNo);
		invBasicInfo.setCurrency(currency);
		invBasicInfo.setCountInvoice(invoiceInfoDTOs == null ? 0 : invoiceInfoDTOs.size());
		invBasicInfo.setSumInvoiceAmt(invoiceInfoDTOs == null ? 0L : getSumInvoiceAmt(invoiceInfoDTOs));
		invBasicInfo.setCreateTime(new Date());
		invBasicInfo.setUpdateTime(new Date());
		assetInvoiceBasicInfoMapper.insert(invBasicInfo);
	}

	private Long getSumInvoiceAmt(List<InvoiceInfoDTO> invoiceInfoDTOs) {
		BigDecimal invAmt = new BigDecimal(0);
		for(InvoiceInfoDTO invDTO : invoiceInfoDTOs){
			String parseMoney = MoneyUtil.parseMoney(invDTO.getInvoiceAmt()==null?"0":invDTO.getInvoiceAmt()); 
			invAmt = invAmt.add(new BigDecimal(parseMoney));
		}
		return Long.valueOf(MoneyUtil.yuan2Cent(invAmt));
	}
	
	/**
	 * 新增  应收账款信息
	 * @param id
	 * @param invoiceInfoDTOs
	 */
	private void insertAssetInvoiceInfo(String id, List<InvoiceInfoDTO> invoiceInfoDTOs) {
		
		//同一个业务合同号下，交易对手和单证号码不能相同
		uniqueCheck(invoiceInfoDTOs);
		
		for(InvoiceInfoDTO invInfoDTO : invoiceInfoDTOs){
			AssetInvoiceInfo assetInvoiceInfo = new AssetInvoiceInfo();
			assetInvoiceInfo.setId(IDGenerator.generateAssetInvoiceInfoId());
			assetInvoiceInfo.setInvoiceBaseId(id);
			assetInvoiceInfo.setCounterpartyId(invInfoDTO.getCounterpartyId());
			assetInvoiceInfo.setCounterparty(invInfoDTO.getCounterparty());
			assetInvoiceInfo.setInvoiceNo(invInfoDTO.getInvoiceNo());
			assetInvoiceInfo.setNominvoiceAmt(MoneyUtil.yuan2Cent(MoneyUtil.parseMoney(invInfoDTO.getNominvoiceAmt())));
			assetInvoiceInfo.setInvoiceAmt(MoneyUtil.yuan2Cent(MoneyUtil.parseMoney(invInfoDTO.getInvoiceAmt())));
			assetInvoiceInfo.setFinancingRatio(invInfoDTO.getFinancingRatio().divide(new BigDecimal(100), 5, BigDecimal.ROUND_HALF_UP));
			assetInvoiceInfo.setInvoiceDate(invInfoDTO.getInvoiceDate());
			assetInvoiceInfo.setDueDate(invInfoDTO.getDueDate());
			assetInvoiceInfo.setUpdateTime(new Date());
			assetInvoiceInfo.setCreateTime(new Date());
			assetInvoiceInfoMapper.insert(assetInvoiceInfo);
		}
	}

	/**
	 * 新增 费用信息
	 * @param id
	 * @param expenseDTOs
	 */
	private void insertAssetExpense(String id, List<ExpenseDTO> expenseDTOs) {
		for(ExpenseDTO expenseDTO : expenseDTOs){
			Expense expense = new Expense();
			expense.setId(IDGenerator.generateAssetExpenseId());
			expense.setRefid(id);
			expense.setReftype(ExpenseType.INVOICE.name());
			expense.setExpenseSubject(expenseDTO.getExpenseSubject());
			expense.setAmount(MoneyUtil.yuan2Cent(MoneyUtil.parseMoney(expenseDTO.getAmountStr()==null?"0":expenseDTO.getAmountStr())));
			expense.setCreateTime(new Date());
			expense.setUpdateTime(new Date());
			//expense.setSequence(expenseDTO.getSequence());
			expenseMapper.insert(expense);
		}
	}


	@Override
	public void delBusManage(String basicId,UserSessionDTO userSessionDTO) {
		if(StringUtils.isBlank(basicId))
			throw WebException.instance("账款信息id为空");
		AssetInvoiceBasicInfo invBasicInfo = assetInvoiceBasicInfoMapper.selectByPrimaryKey(basicId);
		UserType userType = userSessionDTO.getUserType();
		String masterId = userSessionDTO.getMasterId()==null?userSessionDTO.getId():userSessionDTO.getMasterId();
		//数据权限
		switch (userType) {
		case FINACE:
			if(!assetUserPrivilegeTransactionService.allow(masterId, invBasicInfo.getCustomerId()))
				throw WebException.instance("没有删除权限");
			break;
		case FACTOR:
			if(!invBasicInfo.getFactorId().equals(userSessionDTO.getMasterId()==null?userSessionDTO.getId():userSessionDTO.getMasterId()))
				throw WebException.instance("没有删除权限");
			break;
		default:
			throw WebException.instance(ReturnCode.NO_PERMISSION);
		}
		
		String businessContractNo = invBasicInfo.getBusinessContractNo();
		boolean flag = assetLoanInfoTransactionService.checkContractNoHasLoan(businessContractNo);
		if(!flag){
			assetCreditTransactionService.lockByBussinessContractNo(businessContractNo);
			//删除baseInfo
			delBasicInfo(basicId);
			//删除Info
			delInfo(basicId);
			//删除expense
			delExpense(basicId);
		}else
			throw WebException.instance("已放款，不能删除该应收账款信息");
	}

	private void delExpense(String basicId) {
		ExpenseExample expenseExample = new ExpenseExample();
		expenseExample.createCriteria().andRefidEqualTo(basicId).andReftypeEqualTo(ExpenseType.INVOICE.name());
		List<Expense> expenses = expenseMapper.selectByExample(expenseExample);
		for(Expense expense : expenses){
			expenseMapper.deleteByPrimaryKey(expense.getId());
		}
	}

	private void delInfo(String basicId) {
		AssetInvoiceInfoExample invExample = new AssetInvoiceInfoExample();
		invExample.createCriteria().andInvoiceBaseIdEqualTo(basicId);
		List<AssetInvoiceInfo> invInfos = assetInvoiceInfoMapper.selectByExample(invExample);
		for(AssetInvoiceInfo assetInvInfo : invInfos){
			assetInvoiceInfoMapper.deleteByPrimaryKey(assetInvInfo.getId());
		}
	}

	private void delBasicInfo(String basicId) {
		assetInvoiceBasicInfoMapper.deleteByPrimaryKey(basicId);
	}

	/**
	 * excel导入数据保存到sql
	 * 
	 * 根据businessContractNo 判断是否是重复数据
	 * 若重复，则该条businessContractNo在redis中保存的数据不进行数据库保存操作
	 * 
	 */
	@Override
	public void importExcelInvList(InvoiceListDTO invListDTO, InvoiceExcelInfoDTO invExcelInfoDTO,String customerId) {
		String basicId= IDGenerator.generateAssetInvoiceBasicInfoId();
		String businessContractNo = invListDTO.getBusinessContractNo(); 
		if(StringUtils.isBlank(businessContractNo))
			throw WebException.instance("业务合同号为空");
		//查询业务合同号的用户id及保理商id
		FactorBusinessDTO businessDTO = assetFactorBusinessTransactionService.queryFactorBusinessInfoByBusinessContractNo(businessContractNo,customerId);
		
		if(businessDTO ==null){
			logger.error("业务信息为空，businessContractNo："+businessContractNo+",customerId:"+customerId);
			throw WebException.instance("业务信息为空");
		}
		//检验数据合法性，数据库中若已有相同的业务合同号，不保存
		AssetInvoiceBasicInfoExample basicExample = new AssetInvoiceBasicInfoExample();
		basicExample.createCriteria().andBusinessContractNoEqualTo(businessContractNo);
		List<AssetInvoiceBasicInfo> basics = assetInvoiceBasicInfoMapper.selectByExample(basicExample);
		if(CollectionUtils.isEmpty(basics)){
			List<InvoiceRedisDTO> passInvoiceRedisList = getSameContractNoInvList(businessContractNo,invExcelInfoDTO.getPassInvoiceRedisDTO());
			//保存应收账款信息
			insertInvInfo(basicId,passInvoiceRedisList,businessDTO);
			//保存费用信息
			insertExpense(basicId,passInvoiceRedisList);
			//保存应收账款基础信息
			insertInvBasicInfo(basicId,invListDTO,businessDTO,passInvoiceRedisList);
		}
	}
	
	private List<InvoiceRedisDTO> getSameContractNoInvList(String businessContractNo,List<InvoiceRedisDTO> passInvoiceRedisDTO) {
		List<InvoiceRedisDTO> passInvoiceRedisList = new ArrayList<>();
		for(InvoiceRedisDTO invoiceRedisDTO : passInvoiceRedisDTO){
			if(businessContractNo.equals(invoiceRedisDTO.getBusinessContractNo()))
				passInvoiceRedisList.add(invoiceRedisDTO);
		}
		return passInvoiceRedisList;
	}


	/**
	 * 保存应收账款基础信息
	 * @param basicId
	 * @param invListDTO
	 * @param businessDTO
	 * @param passInvoiceRedisList
	 */
	private void insertInvBasicInfo(String basicId, InvoiceListDTO invListDTO, FactorBusinessDTO businessDTO,List<InvoiceRedisDTO> passInvoiceRedisList) {
		AssetInvoiceBasicInfo invBasicInfo = new AssetInvoiceBasicInfo();
		invBasicInfo.setId(basicId);
		invBasicInfo.setFactorId(businessDTO.getFactorId());
		invBasicInfo.setFactorName(businessDTO.getFactorName());
		invBasicInfo.setCustomerId(businessDTO.getCustomerId());
		invBasicInfo.setCustomerName(businessDTO.getCustomerName());
		invBasicInfo.setBusinessProduct(BusinessProduct.getEnum(invListDTO.getBusinessProductDesc()).name());
		invBasicInfo.setBusinessContractNo(invListDTO.getBusinessContractNo());
		invBasicInfo.setCurrency(invListDTO.getCurrencyDesc());
		invBasicInfo.setCountInvoice(passInvoiceRedisList.size());
		invBasicInfo.setSumInvoiceAmt(getSumInvRedisAmt(passInvoiceRedisList));
		invBasicInfo.setCreateTime(new Date());
		invBasicInfo.setUpdateTime(new Date());
		assetInvoiceBasicInfoMapper.insert(invBasicInfo);
	}

	private Long getSumInvRedisAmt(List<InvoiceRedisDTO> passInvoiceRedisList) {
		BigDecimal invAmt = new BigDecimal(0);
		for(InvoiceRedisDTO invRedisDTO : passInvoiceRedisList){
			String parseMoney = MoneyUtil.parseMoney(invRedisDTO.getInvoiceAmt()); 
			if(StringUtils.isNotBlank(parseMoney))
				invAmt = invAmt.add(new BigDecimal(parseMoney));
		}
		return MoneyUtil.yuan2Cent(invAmt);
	}

	/**
	 * 保存费用信息
	 * @param basicId
	 * @param passInvoiceRedisList
	 */
	private void insertExpense(String basicId, List<InvoiceRedisDTO> passInvoiceRedisList) {
		for(InvoiceRedisDTO invoiceRedisDTO : passInvoiceRedisList){
			Expense expense = new Expense();
			expense.setId(IDGenerator.generateAssetExpenseId());
			expense.setRefid(basicId);
			expense.setReftype(ExpenseType.INVOICE.name());
			expense.setExpenseSubject(invoiceRedisDTO.getExpenseSubject());
			expense.setAmount(MoneyUtil.yuan2Cent(invoiceRedisDTO.getAmount()));
			expense.setCreateTime(new Date());
			expense.setUpdateTime(new Date());
			expenseMapper.insert(expense);
		}
	}

	
	/**
	 * 保存应收账款信息
	 * @param basicId
	 * @param passInvoiceRedisList
	 * @param businessDTO
	 */
	private void insertInvInfo(String basicId, List<InvoiceRedisDTO> passInvoiceRedisList,FactorBusinessDTO businessDTO) {
		for(InvoiceRedisDTO invRedisDTO : passInvoiceRedisList){
			AssetInvoiceInfo invInfo = new AssetInvoiceInfo();
			invInfo.setId(IDGenerator.generateAssetInvoiceInfoId());
			invInfo.setInvoiceBaseId(basicId);
			invInfo.setCounterparty(invRedisDTO.getCounterparty());
			invInfo.setCounterpartyId(getCounterpartyId(invRedisDTO.getCounterparty(),businessDTO));
			invInfo.setInvoiceNo(invRedisDTO.getInvoiceNo());
			invInfo.setNominvoiceAmt(MoneyUtil.yuan2Cent(invRedisDTO.getNominvoiceAmt()));
			invInfo.setInvoiceAmt(MoneyUtil.yuan2Cent(invRedisDTO.getInvoiceAmt()));
			invInfo.setFinancingRatio(new BigDecimal(invRedisDTO.getFinancingRatio().replace("%", "")).divide(new BigDecimal(100), 5, BigDecimal.ROUND_HALF_UP));
			invInfo.setInvoiceDate(invRedisDTO.getInvoiceDate());
			invInfo.setDueDate(invRedisDTO.getDueDate());
			invInfo.setCreateTime(new Date());
			invInfo.setUpdateTime(new Date());
			assetInvoiceInfoMapper.insert(invInfo);
		}
	}

	private String getCounterpartyId(String counterparty, FactorBusinessDTO businessDTO) {
		List<BusinessCounterpartyDTO> counterpartyList = businessDTO.getCounterpartyList(); 
		if(CollectionUtils.isNotEmpty(counterpartyList)){
			for(BusinessCounterpartyDTO businessCounterpartyDTO : counterpartyList){
				if(businessCounterpartyDTO.getCounterparty().equals(counterparty))
					return businessCounterpartyDTO.getCounterpartyId();
			}
		}
		throw WebException.instance("业务基础信息中没有此交易对手");
	}

	/**
	 * 根据业务合同号、交易对手获取应收账款信息
	 */
	@Override
	public InvoiceListDTO getInvByExample(InvoiceQueryDTO queryDTO) {
		// 获取应收账款基本信息
		List<AssetInvoiceBasicInfo> basicInfos = getInvBaseInfo(queryDTO);
		if(CollectionUtils.isEmpty(basicInfos))
			return null;
		
		AssetInvoiceBasicInfo basicInfo = basicInfos.get(0);
		String id = basicInfo.getId();
		
		// 获取应收账款信息
		List<AssetInvoiceInfo> assetInvoiceInfos = getInvInfo(queryDTO, id);
		
		// 转换
		InvoiceListDTO invoiceListDTO = InvoiceConvertor.convertorInvBasicInfoToInvDTO(basicInfo);
		List<InvoiceInfoDTO> infoDTOs = InvoiceConvertor.convertorAssetInvInfoToInvInfoDTO(assetInvoiceInfos, invoiceListDTO.getBusinessContractNo());
		invoiceListDTO.setInvoiceInfoDTOs(infoDTOs);
		return invoiceListDTO;
	}

	/**
	 * 获取应收账款信息
	 * @param queryDTO
	 * @param id
	 * @return
	 */
	private List<AssetInvoiceInfo> getInvInfo(InvoiceQueryDTO queryDTO, String id) {
		AssetInvoiceInfoExample assetInvoiceInfoExample = new AssetInvoiceInfoExample();
		AssetInvoiceInfoExample.Criteria criteria = assetInvoiceInfoExample.createCriteria();
		criteria.andInvoiceBaseIdEqualTo(id);
		if (StringUtils.isNotBlank(queryDTO.getCounterpartyId())) {
			criteria.andCounterpartyIdEqualTo(queryDTO.getCounterpartyId());
		}
		List<AssetInvoiceInfo> assetInvoiceInfos = assetInvoiceInfoMapper.selectByExample(assetInvoiceInfoExample);
		return assetInvoiceInfos;
	}

	/**
	 * 获取应收账款基本信息
	 * @param queryDTO
	 * @return
	 */
	private List<AssetInvoiceBasicInfo> getInvBaseInfo(InvoiceQueryDTO queryDTO) {
		if (StringUtils.isBlank(queryDTO.getBusinessContractNo())) {
			return null;
		}
		AssetInvoiceBasicInfoExample invBasicInfoExample = new AssetInvoiceBasicInfoExample();
		AssetInvoiceBasicInfoExample.Criteria invBasicInfoCriteria = invBasicInfoExample.createCriteria();
		invBasicInfoCriteria.andBusinessContractNoEqualTo(queryDTO.getBusinessContractNo());
		List<AssetInvoiceBasicInfo> assetInvoiceList = assetInvoiceBasicInfoMapper.selectByExample(invBasicInfoExample); 
		return assetInvoiceList;
	}

	@Override
	public void addInvoiceInfoByContractNo(String contractNo, List<AssetInvoiceInfo> invoices) {

		checkAddInvoiceByContractParam(contractNo, invoices);
		assetCreditTransactionService.lockByBussinessContractNo(contractNo);

		AssetInvoiceBasicInfoExample basicInfoExample = new AssetInvoiceBasicInfoExample();
		basicInfoExample.createCriteria().andBusinessContractNoEqualTo(contractNo);
		List<AssetInvoiceBasicInfo> basicInfos = assetInvoiceBasicInfoMapper.selectByExample(basicInfoExample);
		if(CollectionUtils.isEmpty(basicInfos))
			throw WebException.instance("该合同号不存在应收账款基本信息");
		if (basicInfos.size() > 1)
			throw WebException.instance("该合同号存在多条应收账款基本信息");

		AssetInvoiceBasicInfo basicInfo = basicInfos.get(0);

		long addInvoiceAmt = 0l;
		for (AssetInvoiceInfo invoice : invoices) {
			invoice.setInvoiceBaseId(basicInfo.getId());
			if (StringUtils.isBlank(invoice.getId()))
				invoice.setId(IDGenerator.generateAssetInvoiceInfoId());
			invoice.setLoanInfoId(null);
			invoice.setCreateTime(new Date());
			invoice.setUpdateTime(new Date());
			assetInvoiceInfoMapper.insertSelective(invoice);

			addInvoiceAmt += invoice.getInvoiceAmt();
		}

		basicInfo.setSumInvoiceAmt(basicInfo.getSumInvoiceAmt() + addInvoiceAmt);
		basicInfo.setCountInvoice(basicInfo.getCountInvoice() + invoices.size());
		basicInfo.setUpdateTime(new Date());
		assetInvoiceBasicInfoMapper.updateByPrimaryKeySelective(basicInfo);
	}

	private void checkAddInvoiceByContractParam(String contractNo, List<AssetInvoiceInfo> invoices) {
		if (StringUtils.isBlank(contractNo) || CollectionUtils.isEmpty(invoices))
			throw WebException.instance("参数不能为空");
		for (AssetInvoiceInfo invoice : invoices) {
			if (StringUtils.isBlank(invoice.getCounterpartyId()))
				throw WebException.instance("交易对手ID不能为空");
			if (StringUtils.isBlank(invoice.getCounterparty()))
				throw WebException.instance("交易对手不能为空");
			if (StringUtils.isBlank(invoice.getInvoiceNo()))
				throw WebException.instance("单证号码不能为空");
			if (NumberUtils.getValue(invoice.getInvoiceAmt()) <= 0)
				throw WebException.instance("应收金额必须大于0");
			if (NumberUtils.getValue(invoice.getNominvoiceAmt()) <= 0)
				throw WebException.instance("单证面额必须大于0");
			if (!DateTimeUtil.validateDate10(invoice.getInvoiceDate()))
				throw WebException.instance("开票日不合法");
			if (!DateTimeUtil.validateDate10(invoice.getDueDate()))
				throw WebException.instance("到期日不合法");
			if (invoice.getFinancingRatio() == null
					|| invoice.getFinancingRatio().compareTo(BigDecimal.ZERO) <= 0
					|| invoice.getFinancingRatio().compareTo(BigDecimal.ONE) > 0)
				throw WebException.instance("融资比例必须在(0,1]之间");
		}
	}
}
