package com.cana.asset.server.api.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cana.asset.api.IAssetInvApi;
import com.cana.asset.dao.mapper.gen.AssetInvoiceBasicInfoMapper;
import com.cana.asset.dao.mapper.gen.AssetInvoiceInfoMapper;
import com.cana.asset.dao.po.AssetInvoiceBasicInfo;
import com.cana.asset.dao.po.AssetInvoiceBasicInfoExample;
import com.cana.asset.service.IAssetFactorBusinessService;
import com.cana.asset.service.IAssetInvoiceService;
import com.cana.asset.service.convertors.InvoiceConvertor;
import com.cana.asset.service.transaction.IAssetFactorBusinessTransactionService;
import com.cana.asset.service.transaction.IAssetInvoiceTransactionService;
import com.cana.asset.service.transaction.util.AssetInvoicePersistenceValidator;
import com.cana.asset.service.transaction.util.DataPermissionValidator;
import com.cana.member.api.IMemberQueryApi;
import com.cana.vbam.common.asset.dto.BusinessCounterpartyDTO;
import com.cana.vbam.common.asset.dto.CounterpartySearchDTO;
import com.cana.vbam.common.asset.dto.FactorBusinessDTO;
import com.cana.vbam.common.asset.dto.InvoiceExcelInfoDTO;
import com.cana.vbam.common.asset.dto.InvoiceFilter;
import com.cana.vbam.common.asset.dto.InvoiceInfoDTO;
import com.cana.vbam.common.asset.dto.InvoiceInfoQueryDTO;
import com.cana.vbam.common.asset.dto.InvoiceListDTO;
import com.cana.vbam.common.asset.dto.InvoiceQueryDTO;
import com.cana.vbam.common.asset.dto.InvoiceRedisDTO;
import com.cana.vbam.common.asset.enums.BusinessProduct;
import com.cana.vbam.common.dto.PageResult;
import com.cana.vbam.common.member.dto.user.UserSessionDTO;
import com.cana.vbam.common.utils.Constants;
import com.cana.vbam.common.utils.RedisUtils;
import com.travelzen.framework.common.PageList;
import com.travelzen.framework.config.tops.TopsConfEnum.ConfScope;
import com.travelzen.framework.config.tops.TopsConfReader;
import com.travelzen.framework.core.exception.WebException;
import com.travelzen.framework.core.time.DateTimeUtil;
import com.travelzen.framework.dao.rdbms.SequenceGenerator;
import com.travelzen.framework.redis.client.SpringRedisClient;

/**
 * 应收账款 api 实现类
 * 
 * @author guguanggong
 *
 */
public class AssetInvApiImpl implements IAssetInvApi {
	
	@Resource
	private SequenceGenerator seqGen;
	
	private final SpringRedisClient redisCache = SpringRedisClient.getInstance();
	
	private static final Logger LGR = LoggerFactory.getLogger(AssetInvApiImpl.class);

	@Resource
	private IAssetInvoiceService assetInvoiceService;
	
	@Resource
	private IAssetInvoiceTransactionService assetInvoiceTransactionService;
	
	@Resource
	private IAssetFactorBusinessTransactionService assetFactorBusinessTransactionService;

	@Resource
	private IAssetFactorBusinessService assetFactorBusinessService;
	
	@Resource
	private AssetInvoiceBasicInfoMapper assetInvoiceBasicInfoMapper;
	
	@Resource
	private AssetInvoiceInfoMapper assetInvoiceInfoMapper;
	
	@Resource
	private DataPermissionValidator datapermissionValidator;
	
	@Resource
	private IMemberQueryApi memberQueryApi;
	
	/**
	 * 查询应收账款列表信息
	 * 
	 * @param queryDTO
	 * @param userSessionDTO
	 * @return
	 */
	@Override
	public PageList<InvoiceListDTO> getInvoiceList(InvoiceQueryDTO queryDTO, UserSessionDTO userSessionDTO) {
		return assetInvoiceService.getInvoiceList(queryDTO,userSessionDTO);
	}

	/**
	 * 根据应收账款id查询应收账款信息
	 */
	@Override
	public InvoiceListDTO getInvoiceManage(String id, UserSessionDTO userSessionDTO) {
		return assetInvoiceService.getInvoiceManage(id,userSessionDTO);
	}

	/**
	 * 根据应收账款编号 新增
	 */
	@Override
	public void createInvManage(InvoiceListDTO invoiceListDTO, UserSessionDTO user) {
		assetInvoiceTransactionService.updateInvManage(invoiceListDTO,user);
	}
	
	/**
	 * 根据应收账款编号 修改
	 */
	@Override
	public void updateInvManage(InvoiceListDTO invoiceListDTO,UserSessionDTO user) {
		assetInvoiceTransactionService.updateInvManage(invoiceListDTO,user);
	}

	/**
	 * 将redis中合法数据保存到数据库
	 */
	@Override
	public void importExcelInvList(String key,String customerId,String operatorId) {
		if(redisCache.read(RedisUtils.generateInvoiceInfoRedisKeyByOperator(key,operatorId))!=null){
			LGR.info("把key="+key+"的redis中的数据存到sql中。");
			// 获取redis数据
			InvoiceExcelInfoDTO invExcelInfoDTO = (InvoiceExcelInfoDTO)redisCache.read(key);
			InvoiceFilter invFilter = (InvoiceFilter)redisCache.read(RedisUtils.generateInvoiceInfoRedisKeyByOperator(key,operatorId));
			List<InvoiceListDTO> invoiceListDTO = invFilter.getPassInvoiceList();
			for(InvoiceListDTO invListDTO : invoiceListDTO){
				// 将redis中数据转到mysql中
				assetInvoiceTransactionService.importExcelInvList(invListDTO,invExcelInfoDTO,customerId);
			}
		}else{
			LGR.info("key="+key+",redis中没有该key值的数据。");
			throw WebException.instance("数据不存在");
		}
	}

	/**
	 * 根据应收账款基本信息Id删除（删除应收账款基本信息、删除应收账款）
	 * @param invoiceListDTO
	 */
	@Override
	public void delBusManage(String id,UserSessionDTO userSessionDTO) {
		// 根据应收账款基本信息Id删除（删除应收账款基本信息、删除应收账款），注意这两步要在一个事务
		assetInvoiceTransactionService.delBusManage(id,userSessionDTO);
	}

	/**
	 * 应收账款excel导入页面，rediskey获取方法
	 */
	@Override
	public String generateRediskey() {
		return generateInvoiceRedisId();
	}

	private String generateInvoiceRedisId() {
		return DateTimeUtil.datetime12()+ seqGen.getNextSeq(Constants.SEQUENCE_NAME_REDIS_ASSET_INVOICE_INFO_ID, 4);
	}

	/**
	 * 获取redis失效时间  86400秒
	 * @return
	 */
	private int getRedisExpireTime(){
		return Integer.parseInt(TopsConfReader.getConfContent("properties/asset-common.properties", "redis.temp.timeout", ConfScope.G));
	}

	/**
	 * 保存excel数据到redis
	 * @throws ParseException 
	 */
	@Override
	public void batchSaveToRedis(String customerId, String key,List<InvoiceRedisDTO> list) throws Exception {
		List<InvoiceRedisDTO> passInvoiceRedis = new ArrayList<>();
		List<InvoiceRedisDTO> NotPassInvoiceRedis = new ArrayList<>();
		Object excelRedis = redisCache.read(key); 
        if(excelRedis!=null){
        	InvoiceExcelInfoDTO invoiceExcelInfoDTO = (InvoiceExcelInfoDTO)redisCache.read(key);
        	passInvoiceRedis = invoiceExcelInfoDTO.getPassInvoiceRedisDTO();
        	NotPassInvoiceRedis = invoiceExcelInfoDTO.getNotPassInvoiceRedisDTO();
		}
		Iterator<InvoiceRedisDTO> invIterator = list.iterator(); 
		while(invIterator.hasNext()){
			InvoiceRedisDTO invoiceRedisDTO = invIterator.next();
			//校验 invoiceRedisDTO
			Boolean flag = AssetInvoicePersistenceValidator.checkInvoiceExcelDTO(invoiceRedisDTO);
			//数据校验
			if(flag){
				Boolean flag2 = checkInvoiceRedisDTO(customerId,invoiceRedisDTO);
				if(flag2){
					//再判断excelRedis是否是重复数据
					Boolean flag3 = checkPassInvoiceRedis(invoiceRedisDTO,passInvoiceRedis);
					if(!flag3){
						//保存
						passInvoiceRedis.add(invoiceRedisDTO);
					}
				}else{
					NotPassInvoiceRedis.add(invoiceRedisDTO);
				}
			}else{
				NotPassInvoiceRedis.add(invoiceRedisDTO);
			}
		}
		//同一个业务合同号、交易对手的融资比例必须相同
		List<String> invoiceNoList = getInvoiceNoList(passInvoiceRedis);
		
		Iterator<InvoiceRedisDTO> passInvIterator = passInvoiceRedis.iterator();
		while(passInvIterator.hasNext()){
			InvoiceRedisDTO passInvRedisDTO = passInvIterator.next();
			if(invoiceNoList.contains(passInvRedisDTO.getInvoiceNo())){
				passInvIterator.remove();
				NotPassInvoiceRedis.add(passInvRedisDTO);
			}
		}
		
		InvoiceExcelInfoDTO invoiceExcelInfoDTO = new InvoiceExcelInfoDTO();
		invoiceExcelInfoDTO.setNotPassInvoiceRedisDTO(NotPassInvoiceRedis);
		invoiceExcelInfoDTO.setPassInvoiceRedisDTO(passInvoiceRedis);
		redisCache.save(key, invoiceExcelInfoDTO);
	}
	
	/**
	 * 获取不符合 同一个业务合同号、交易对手的融资比例必须相同的单证号码
	 * @param passInvoiceRedis
	 * @return
	 */
	private List<String> getInvoiceNoList(List<InvoiceRedisDTO> passInvoiceRedis) {
		List<String> invoiceNoList = new ArrayList<>();
		if(CollectionUtils.isEmpty(passInvoiceRedis))
			return invoiceNoList;
		for(int i=0;i<passInvoiceRedis.size()-1;i++){
			for(int j=i+1;j<passInvoiceRedis.size();j++){
				InvoiceRedisDTO now = passInvoiceRedis.get(i);
				InvoiceRedisDTO next = passInvoiceRedis.get(j);
				if(now.getBusinessContractNo().equals(next.getBusinessContractNo())&&now.getCounterparty().equals(next.getCounterparty())&&!now.getFinancingRatio().equals(next.getFinancingRatio())){
					invoiceNoList.add(now.getInvoiceNo());
					invoiceNoList.add(next.getInvoiceNo());
				}
			}
		}
		return invoiceNoList;
	}

	private Boolean checkInvoiceRedisDTO(String customerId,InvoiceRedisDTO invoiceRedisDTO) {
		String businessContractNo = invoiceRedisDTO.getBusinessContractNo(); 
		String businessProductDesc = invoiceRedisDTO.getBusinessProductDesc(); 
		String currencyDesc = invoiceRedisDTO.getCurrencyDesc(); 
		String memberName = invoiceRedisDTO.getMemberName();
		String counterparty = invoiceRedisDTO.getCounterparty(); 

		//业务合同号唯一
		AssetInvoiceBasicInfoExample basicExample = new AssetInvoiceBasicInfoExample();
		basicExample.createCriteria().andBusinessContractNoEqualTo(businessContractNo);
		List<AssetInvoiceBasicInfo> basicInvoiceInfos = assetInvoiceBasicInfoMapper.selectByExample(basicExample);
		if (CollectionUtils.isNotEmpty(basicInvoiceInfos)) {
			return false;
		}
		
		// 根据业务合同号查询业务合同信息
		FactorBusinessDTO businessDTO = assetFactorBusinessTransactionService.queryFactorBusinessInfoByBusinessContractNo(businessContractNo,customerId);
		if(businessDTO == null)
			return false;
		else{
			//业务产品必须是该业务合同号指定的业务产品
			if(!businessDTO.getBusinessProduct().equals(BusinessProduct.getEnum(businessProductDesc)))
				return false;
			//币种必须是该业务合同号指定的币种
			if(!businessDTO.getCurrency().name().equals(currencyDesc))
				return false;
			//交易对手必须是该业务合同号指定的交易对手
			//客户名称必须是该业务合同号指定的客户名称
			String customerName = businessDTO.getCustomerName();
			Boolean counterpartyFlag = false;
			List<BusinessCounterpartyDTO> counterpartyList = businessDTO.getCounterpartyList(); 
			for(BusinessCounterpartyDTO businessCounterpartyDTO : counterpartyList){
				if(memberName.equals(customerName)&&counterparty.equals(businessCounterpartyDTO.getCounterparty()))
					counterpartyFlag = true;
				else
					continue;
			}
			if(!counterpartyFlag)
				return counterpartyFlag;
			return true;
		}
	}

	private Boolean checkPassInvoiceRedis(InvoiceRedisDTO invoiceRedisDTONow, List<InvoiceRedisDTO> passInvoiceRedis) {
		for(InvoiceRedisDTO invoiceRedisDTO : passInvoiceRedis){
			//业务合同号、交易对手、单证号码都存在 视为重复数据
			if(invoiceRedisDTO.getBusinessContractNo().equals(invoiceRedisDTONow.getBusinessContractNo())&&invoiceRedisDTO.getCounterparty().equals(invoiceRedisDTONow.getCounterparty())&&invoiceRedisDTO.getInvoiceNo().equals(invoiceRedisDTONow.getInvoiceNo())){
				return true;
			}
		}
		return false;
	}


	/**
	 * 把相同合同号的invoice放在一个map中
	 * @param invRedisList
	 * @return
	 */
	private Map<String,List<InvoiceRedisDTO>> getSameBusinessInvoice(List<InvoiceRedisDTO> invRedisList) {
		Map<String,List<InvoiceRedisDTO>> map = new HashMap<>();
		List<String> business = new ArrayList<>();
		for(InvoiceRedisDTO invRedisDTO : invRedisList){
			if(business.contains(invRedisDTO.getBusinessContractNo())){
			   	map.get(invRedisDTO.getBusinessContractNo()).add(invRedisDTO);
			}else{
				business.add(invRedisDTO.getBusinessContractNo());
				List<InvoiceRedisDTO> invoiceList = new ArrayList<>();
				invoiceList.add(invRedisDTO);
				map.put(invRedisDTO.getBusinessContractNo(), invoiceList);
			}
		}
		return map;
	}


	/**
	 * 查询redis中的应收账款信息
	 */
	@Override
	public PageList<InvoiceListDTO> queryInvoiceInfoFromRedis(String key,String operatorId, String status, int page, int pageSize) {
		//是否存在该key
		if(redisCache.read(key) == null)
			return new PageList<InvoiceListDTO>();
		InvoiceExcelInfoDTO invoiceExcelInfoDTO = (InvoiceExcelInfoDTO)redisCache.read(key);
		
		//处理相同业务合同号的应收账款信息
		Map<String,List<InvoiceRedisDTO>> passInvoiceMap = getSameBusinessInvoice(invoiceExcelInfoDTO.getPassInvoiceRedisDTO());
		Map<String,List<InvoiceRedisDTO>> NotPassInvoiceMap = getSameBusinessInvoice(invoiceExcelInfoDTO.getNotPassInvoiceRedisDTO());
		
		List<InvoiceListDTO> passInvoiceList = InvoiceConvertor.convertorRedisToListDTO(passInvoiceMap);
		List<InvoiceListDTO> NotPassInvoiceList = InvoiceConvertor.convertorRedisToListDTO(NotPassInvoiceMap);
		//保存应收账款列表信息，先查询是否已经存在key值
		InvoiceFilter invFilter = new InvoiceFilter();
		invFilter.setPassInvoiceList(passInvoiceList);
		invFilter.setNotPassInvoiceList(NotPassInvoiceList);
		redisCache.save(RedisUtils.generateInvoiceInfoRedisKeyByOperator(key, operatorId),invFilter,getRedisExpireTime());
		//页面展示数据封装
		return queryInvoiceInfo(invFilter,status,page,pageSize);
		
	}
	
	/**
	 * 页面展示
	 * @param invFilter
	 * @param result
	 * @param status
	 * @param page
	 * @param pageSize
	 * @return
	 */
	private PageList<InvoiceListDTO> queryInvoiceInfo(InvoiceFilter invFilter,String status,int page, int pageSize) {
		PageList<InvoiceListDTO> result = new PageList<InvoiceListDTO>();
		if("success".equals(status)){
		    try {
				List<InvoiceListDTO> invoiceListDTOs = new ArrayList<>();
				if(CollectionUtils.isNotEmpty(invFilter.getPassInvoiceList())){
					invoiceListDTOs = invFilter.getPassInvoiceList().subList(getStartIndex(page, pageSize),getEndIndex(page, pageSize, invFilter.getPassInvoiceList()));
				}
				result.setRecords(invoiceListDTOs);
				result.setTotalRecords(invFilter.getPassInvoiceList().size());
				LGR.info("查询通过的应收账款redis成功");
			} catch (Exception e) {
				LGR.error("查询通过的应收账款redis失败", e);
			}
			
		}else{
			try {
				List<InvoiceListDTO> invoiceListDTOs = new ArrayList<>();
				if(CollectionUtils.isNotEmpty(invFilter.getNotPassInvoiceList())){
					invoiceListDTOs = invFilter.getNotPassInvoiceList().subList(getStartIndex(page, pageSize), getEndIndex(page, pageSize, invFilter.getNotPassInvoiceList()));
				}
				result.setRecords(invoiceListDTOs);
				result.setTotalRecords(invFilter.getNotPassInvoiceList().size());
				LGR.info("查询未通过的应收账款redis成功");
			} catch (Exception e) {
				LGR.error("查询未通过的应收账款redis失败", e);
			}
		}
		return result;
	}

	/**
	 * 根据业务合同号查询业务合同信息
	 * @param queryDTO
	 * @return
	 */
	@Override
	public InvoiceListDTO getBusinessInfo(InvoiceQueryDTO queryDTO, String customerId) {
		if (queryDTO == null || StringUtils.isBlank(queryDTO.getBusinessContractNo())) {
			throw WebException.instance("业务合同号不能为空");
		}
		// 检查业务合同号已存在
		validAssetInvoice(queryDTO);
		// 根据业务合同号查询业务合同信息
		InvoiceListDTO invoiceListDTO = getFactorBusiness(queryDTO, customerId);
		return invoiceListDTO;
	}
	
	/**
	 * 检查业务合同号已存在
	 * @param queryDTO
	 * @param customerId
	 * @return
	 */
	private InvoiceListDTO getFactorBusiness(InvoiceQueryDTO queryDTO,String customerId) {
		FactorBusinessDTO businessDTO = assetFactorBusinessTransactionService.queryFactorBusinessInfoByBusinessContractNo(queryDTO.getBusinessContractNo(),customerId);
		if (businessDTO != null) {
			InvoiceListDTO invoiceListDTO = new InvoiceListDTO();
			invoiceListDTO.setCustomerId(businessDTO.getCustomerId());
			invoiceListDTO.setCustomerName(businessDTO.getCustomerName());
			invoiceListDTO.setCurrency(businessDTO.getCurrency().name());
			invoiceListDTO.setBusinessProductDesc(businessDTO.getBusinessProduct().desc());
			invoiceListDTO.setBusinessProduct(businessDTO.getBusinessProduct().name());

			return invoiceListDTO;
		} else {
			throw WebException.instance("该业务合同号不存在");
		}
	}

	/**
	 * 根据业务合同号查询业务合同信息
	 * @param queryDTO
	 */
	private void validAssetInvoice(InvoiceQueryDTO queryDTO) {
		AssetInvoiceBasicInfoExample basicExample = new AssetInvoiceBasicInfoExample();
		basicExample.createCriteria().andBusinessContractNoEqualTo(queryDTO.getBusinessContractNo());
		List<AssetInvoiceBasicInfo> basicInvoiceInfos = assetInvoiceBasicInfoMapper.selectByExample(basicExample);
		if (CollectionUtils.isNotEmpty(basicInvoiceInfos)) {
			throw WebException.instance("该业务合同号已存在");
		}
	}
	
	/**
	 * 查询业务合同中交易对手信息(业务合同号、交易对手名称)
	 */
	@Override
	public PageResult<BusinessCounterpartyDTO> queryBusinessCounterpartyDTO(InvoiceQueryDTO queryDTO) {
		CounterpartySearchDTO searchDTO = new CounterpartySearchDTO();
		searchDTO.setBusinessContractNo(queryDTO.getBusinessContractNo());
		searchDTO.setCustomerName(queryDTO.getCustomerName());
		searchDTO.setPage(queryDTO.getPage());
		searchDTO.setPageSize(queryDTO.getPageSize());
		PageResult<BusinessCounterpartyDTO> pageResult = assetFactorBusinessService.queryBusinessCounterpartyDTO(searchDTO);
		return pageResult;
	}

	
	/**
	 * 根据业务合同号（必输）、交易对手获取应收账款信息
	 */
	@Override
	public InvoiceListDTO getInvByExample(InvoiceQueryDTO queryDTO) {
		return assetInvoiceTransactionService.getInvByExample(queryDTO);
	}
	
	private int getStartIndex(int page, int pageSize){
		return (page - 1) * pageSize;
	}
	
	private int getEndIndex(int page, int pageSize, List<?> curList) {
		return page * pageSize < curList.size() ? page * pageSize : curList.size();
	}

	/**
	 * 测试用
	 */
	@Override
	public PageList<InvoiceRedisDTO> queryPassRedis(String key,int page, int pageSize) {
		PageList<InvoiceRedisDTO> result = new PageList<InvoiceRedisDTO>();
		List<InvoiceRedisDTO> list = new ArrayList<>();
		InvoiceExcelInfoDTO invoiceExcelInfoDTO = (InvoiceExcelInfoDTO)redisCache.read(key);
		list.addAll(invoiceExcelInfoDTO.getPassInvoiceRedisDTO());
		list.addAll(invoiceExcelInfoDTO.getNotPassInvoiceRedisDTO());
		result.setRecords(list.subList(getStartIndex(page, pageSize),getEndIndex(page, pageSize, list)));
		result.setTotalRecords(list.size());
		return result;
	}

	/**
	 * 获取未放款的应收账款
	 */
	@Override
	public List<InvoiceInfoDTO> getUnloanInvoiceInfoDTOs(InvoiceInfoQueryDTO invoiceInfoQueryDTO) {
		AssetInvoiceBasicInfo assetInvoiceBasicInfo = assetInvoiceService.getAssetInvoiceBasicInfoByBusinessContractNo(invoiceInfoQueryDTO.getBusinessContractNo());
		if(assetInvoiceBasicInfo != null) {
			datapermissionValidator.checkDataPermissions(memberQueryApi.findUserById(invoiceInfoQueryDTO.getUserId()), assetInvoiceBasicInfo.getCustomerId(), assetInvoiceBasicInfo.getFactorId());
			invoiceInfoQueryDTO.setInvoiceBaseId(assetInvoiceBasicInfo.getId());
			return assetInvoiceService.getUnloanInvoiceInfoDTOs(invoiceInfoQueryDTO);
		}
		return null;
	}
	
}
