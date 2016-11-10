package com.cana.asset.service.transaction.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.cana.asset.dao.mapper.gen.SpecialProgramLogMapper;
import com.cana.asset.dao.po.SpecialProgram;
import com.cana.asset.dao.po.SpecialProgramLog;
import com.cana.asset.dao.po.SpecialProgramLogExample;
import com.cana.asset.service.convertors.SpecialProgramCovert;
import com.cana.asset.service.transaction.IABSDataPrivilegeTransactionService;
import com.cana.asset.service.transaction.ISpecialProgramLogTransactionService;
import com.cana.asset.service.transaction.util.SpecialProgramLogIdUtils;
import com.cana.vbam.common.asset.enums.SpecialProgramStatus;
import com.cana.vbam.common.asset.underlyingasset.dto.SpecialProgramLogDTO;
import com.cana.vbam.common.asset.underlyingasset.dto.SpecialProgramLogQuery;
import com.cana.vbam.common.dto.ListResult;
import com.cana.vbam.common.member.vo.UserVo;
import com.google.common.collect.Lists;
import com.travelzen.framework.core.exception.WebException;

/**
 * 资产池管理日志——资产日志
 * @author yihong.tang
 */
@Service
public class SpecialProgramLogTransactionServiceImpl implements ISpecialProgramLogTransactionService {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Resource
	private IABSDataPrivilegeTransactionService absDataPrivilegeTransactionServiceImpl;
	
	@Resource
	private SpecialProgramLogMapper specialProgramLogMapper;
	
	@Override
	public void insertSpecialProgramLog(SpecialProgram specialProgram, Long assetPoolAmount, SpecialProgramStatus operate, UserVo userVo) {
		if(specialProgram == null)
			throw WebException.instance("专项计划不能为空");
		if(operate == null)
			throw WebException.instance("操作不能为空");
		if (userVo == null)
			throw WebException.instance("当前登陆用户不能为空");
		logger.info("插入专项计划日志,专项计划id为{},操作为{},操作人用户名为{}",specialProgram.getId(),operate.desc(),userVo.getUsername());
		SpecialProgramLog specialProgramLog = SpecialProgramCovert.convertSpecialProgram2SpecialProgramLog(specialProgram);
		specialProgramLog.setId(SpecialProgramLogIdUtils.generateSpecialProgramLogId());
		specialProgramLog.setAssetPoolAmount(assetPoolAmount);
		specialProgramLog.setOperateCompanyName(userVo.getCustomer().getCustomerName());
		specialProgramLog.setOperateUsername(userVo.getUsername());
		specialProgramLog.setOperateType(operate.name());
		specialProgramLogMapper.insertSelective(specialProgramLog);
	}

	@Override
	public ListResult<SpecialProgramLogDTO> querySpecialProgramLogs(UserVo userVo, SpecialProgramLogQuery query) {
		if (userVo == null)
			throw WebException.instance("当前登陆用户不能为空");
		
		SpecialProgramLogExample example = convertSpecialProgramLogQuery(userVo,query);
		
		int count = specialProgramLogMapper.countByExample(example);
		List<SpecialProgramLogDTO> dtos = Lists.newArrayList();
		if (count <= (query.getPage() - 1) * query.getPageSize())
			return ListResult.success(dtos, count);
		example.setOrderByClause("create_time desc");
        example.setLimitStart((query.getPage()-1)*query.getPageSize());
        example.setLimitEnd(query.getPageSize());
		List<SpecialProgramLog> specialProgramLogs = specialProgramLogMapper.selectByExample(example);
		dtos = SpecialProgramCovert.convertSpecialProgramLogs2SpecialProgramLogDTOs(specialProgramLogs);
		return ListResult.success(dtos, count);
	}
	
	/**
	 * 转换查询条件
	 * @param userVo
	 * @param query
	 */
	private SpecialProgramLogExample convertSpecialProgramLogQuery(UserVo userVo,SpecialProgramLogQuery query){
		if (query == null) {
			query = new SpecialProgramLogQuery();
			query.setPageSize(5);
		}
		if(query.getPage() <= 0) 
			query.setPage(1);
		if(query.getPageSize() <= 0 || query.getPageSize() > 10) 
			query.setPageSize(5);

		Set<String> programIds = absDataPrivilegeTransactionServiceImpl.allowedProgramIdList(userVo.getCustomer().getUserType(), userVo.getCustomer().getCustomerName());
		List<String> programIdList = new ArrayList<String>(programIds);
		
		SpecialProgramLogExample example = new SpecialProgramLogExample();
		SpecialProgramLogExample.Criteria criteria = example.createCriteria();
		criteria.andSpecialProgramIdIn(programIdList);
		if(query.getOperateType()!=null)
			criteria.andOperateTypeEqualTo(query.getOperateType().name());
		if(StringUtils.isNotBlank(query.getSpecialProgramName()))
			criteria.andSpecialProgramNameLike("%" + query.getSpecialProgramName().trim() + "%");
		if(StringUtils.isNotBlank(query.getOperateCompanyName()))
			criteria.andOperateCompanyNameLike("%" + query.getOperateCompanyName() + "%");
		if(query.getOperateStartDate()!=null)
			criteria.andCreateTimeGreaterThanOrEqualTo(query.getOperateStartDate());
		if(query.getOperateEndDate()!=null)
			criteria.andCreateTimeLessThan(new DateTime(query.getOperateEndDate()).plusDays(1).toDate());
		return example;
	}

}
