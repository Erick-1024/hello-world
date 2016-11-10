package com.cana.asset.service.transaction.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.cana.asset.dao.mapper.UnderlyingAssetLogCustomMapper;
import com.cana.asset.dao.mapper.gen.UnderlyingAssetLogMapper;
import com.cana.asset.dao.po.UnderlyingAsset;
import com.cana.asset.dao.po.UnderlyingAssetLog;
import com.cana.asset.service.convertors.UnderlyingAssetConvertor;
import com.cana.asset.service.transaction.IABSDataPrivilegeTransactionService;
import com.cana.asset.service.transaction.IUnderlyingAssetLogTransactionService;
import com.cana.asset.service.transaction.util.LoanAndUnderlyingAssetIdUtils;
import com.cana.vbam.common.asset.enums.UnderlyingAssetOperateTypeEnum;
import com.cana.vbam.common.asset.enums.UnderlyingAssetSource;
import com.cana.vbam.common.asset.underlyingasset.dto.UnderlyingAssetLogDTO;
import com.cana.vbam.common.asset.underlyingasset.dto.UnderlyingAssetLogQuery;
import com.cana.vbam.common.dto.ListResult;
import com.cana.vbam.common.member.enums.user.UserType;
import com.cana.vbam.common.member.vo.UserVo;
import com.google.common.collect.Lists;
import com.travelzen.framework.core.exception.WebException;

/**
 * 资产池管理日志——资产日志
 * @author yihong.tang
 *
 */
@Service
public class UnderlyingAssetLogTransactionServiceImpl implements IUnderlyingAssetLogTransactionService {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Resource
	private UnderlyingAssetLogMapper underlyingAssetLogMapper;
	
	@Resource
	private IABSDataPrivilegeTransactionService absDataPrivilegeTransactionServiceImpl;
	
	@Resource
	private UnderlyingAssetLogCustomMapper underlyingAssetLogCustomMapper;

	@Resource
	private UnderlyingAssetConvertor underlyingAssetConvertor;
	@Override
	public void insertUnderlyingAssetLog(UnderlyingAsset underlyingAsset, UnderlyingAssetOperateTypeEnum operate, UserVo userVo) {
		if(underlyingAsset == null)
			throw WebException.instance("基础资产不能为空");
		if(operate == null)
			throw WebException.instance("操作不能为空");
		if (userVo == null)
			throw WebException.instance("当前登陆用户不能为空");
		logger.info("插入基础资产日志,基础资产id为{},操作为{},操作人用户名为{}",underlyingAsset.getId(),operate.desc(),userVo.getUsername());
		if(UnderlyingAssetSource.FACTOR.name().equals(underlyingAsset.getAssetSource())){
			//补全信息
			List<UnderlyingAsset> underlyingAssets = new ArrayList<>();
			underlyingAssets.add(underlyingAsset);
			underlyingAsset = underlyingAssetConvertor.queryUnderlyingAssetData(underlyingAssets).get(0);
		}
		UnderlyingAssetLog underlyingAssetLog = underlyingAssetConvertor.convertUnderlyingAsset2UnderlyingAssetLog(underlyingAsset);
		underlyingAssetLog.setId(LoanAndUnderlyingAssetIdUtils.generateUnderlyingAssetLogId());
		underlyingAssetLog.setOperateCompanyName(userVo.getCustomer().getCustomerName());
		underlyingAssetLog.setOperateUsername(userVo.getUsername());
		underlyingAssetLog.setOperateType(operate.name());
		underlyingAssetLogMapper.insertSelective(underlyingAssetLog);
	}

	@Override
	public void insertUnderlyingAssetLog(List<UnderlyingAsset> underlyingAssets, UnderlyingAssetOperateTypeEnum operate, UserVo userVo) {
		if(CollectionUtils.isEmpty(underlyingAssets))
			throw WebException.instance("基础资产不能为空");
		for(UnderlyingAsset underlyingAsset : underlyingAssets)
			insertUnderlyingAssetLog(underlyingAsset,operate,userVo);
	}
	
	@Override
	public ListResult<UnderlyingAssetLogDTO> queryUnderlyingAssetLogs(UserVo userVo, UnderlyingAssetLogQuery query) {
		if (userVo == null)
			throw WebException.instance("当前登陆用户不能为空");
		
		convertUnderlyingAssetLogQuery(userVo,query);

		int count = underlyingAssetLogCustomMapper.count(query);
		List<UnderlyingAssetLogDTO> dtos = Lists.newArrayList();
		if (count <= (query.getPage() - 1) * query.getPageSize())
			return ListResult.success(dtos, count);
		List<UnderlyingAssetLog> underlyingAssetLogs = underlyingAssetLogCustomMapper.find(query);
		dtos = underlyingAssetConvertor.convertUnderlyingAssetLogs2UnderlyingAssetLogDTOs(underlyingAssetLogs);
		return ListResult.success(dtos, count);
	}
	
	/**
	 * 转换查询条件
	 * @param userVo
	 * @param query
	 */
	private void convertUnderlyingAssetLogQuery(UserVo userVo,UnderlyingAssetLogQuery query){
		if (query == null) {
			query = new UnderlyingAssetLogQuery();
			query.setPageSize(5);
		}
		if(query.getPage() <= 0) 
			query.setPage(1);
		if(query.getPageSize() <= 0 || query.getPageSize() > 10) 
			query.setPageSize(5);

		if(UserType.FACTOR.equals(userVo.getCustomer().getUserType()))
			query.setFactorId(userVo.getCustomerId());
		else{
			Set<String> programIds = absDataPrivilegeTransactionServiceImpl.allowedProgramIdList(userVo.getCustomer().getUserType(), userVo.getCustomer().getCustomerName());
			List<String> programIdList = new ArrayList<String>(programIds);
			query.setSpecialProgramIds(programIdList);
		}
		if(StringUtils.isBlank(query.getBusinessContractNo()))
			query.setBusinessContractNo(null);
		if(StringUtils.isBlank(query.getLoanInfoId()))
			query.setLoanInfoId(null);
		if(StringUtils.isNotBlank(query.getSpecialProgramName()))
			query.setSpecialProgramName("%" + query.getSpecialProgramName().trim() + "%");
		else
			query.setSpecialProgramName(null);
		if(StringUtils.isNotBlank(query.getOperateCompanyName()))
			query.setOperateCompanyName("%" + query.getOperateCompanyName() + "%");
		else
			query.setOperateCompanyName(null);
		if(query.getOperateType()!=null)
			query.setOperateTypeStr(query.getOperateType().name());
		if(query.getOperateEndDate()!=null)
			query.setOperateEndDate(new DateTime(query.getOperateEndDate()).plusDays(1).toDate());
	}

	
}
