package com.cana.asset.server.api.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cana.asset.api.IABSSpecialProgramApi;
import com.cana.asset.api.utils.IDGenerator;
import com.cana.asset.dao.mapper.gen.SpecialProgramMapper;
import com.cana.asset.dao.po.SpecialProgram;
import com.cana.asset.dao.po.SpecialProgramExample;
import com.cana.asset.service.transaction.IABSSpecialProgramTransactionService;
import com.cana.member.api.IMemberQueryApi;
import com.cana.member.api.IUserApi;
import com.cana.vbam.common.asset.dto.QueryCompany4AddUserPrivilegeListItem;
import com.cana.vbam.common.asset.dto.QueryCompanyListRequest;
import com.cana.vbam.common.asset.dto.SpecialProgramDTO;
import com.cana.vbam.common.asset.dto.SpecialProgramIssueListDTO;
import com.cana.vbam.common.asset.dto.SpecialProgramListDTO;
import com.cana.vbam.common.asset.dto.SpecialProgramListRequestDTO;
import com.cana.vbam.common.asset.dto.SpecialProgramListRequestIssueDTO;
import com.cana.vbam.common.asset.dto.SpecialProgramRequestDTO;
import com.cana.vbam.common.dto.ListResult;
import com.cana.vbam.common.enums.AjaxResponseStatus;
import com.cana.vbam.common.member.dto.user.CustomerSearchCriteriaDTO;
import com.cana.vbam.common.member.dto.user.CustomerSearchResultDTO;
import com.cana.vbam.common.member.enums.user.UserStatus;
import com.cana.vbam.common.member.vo.UserVo;
import com.travelzen.framework.core.common.ReturnCode;
import com.travelzen.framework.core.exception.WebException;

/**
 * @author jiangzhou.Ren
 * @time 2016年9月1日上午10:25:32
 */
public class ABSSpecialProgramApiImpl implements IABSSpecialProgramApi {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Resource
	private IMemberQueryApi memberQueryApi;
	
	@Resource
	private IUserApi userApi;
	
	@Resource
	private SpecialProgramMapper specialProgramMapper;

	@Resource
	private IABSSpecialProgramTransactionService specialProgramTransactionService;

	@Override
	public ListResult<SpecialProgramListDTO> querySpecialProgramList(SpecialProgramListRequestDTO request) {
		UserVo userDetail = getUserDetail(request.getUserId());
		return specialProgramTransactionService.querySpecialProgramList(request,userDetail);
	}

	@Override
	public SpecialProgramDTO getSpecialProgramById(String specialProgramId, String userId) {
		UserVo userDetail = getUserDetail(userId);
		return specialProgramTransactionService.getSpecialProgramById(specialProgramId, userDetail);
	}

	@Override
	public void addSpecialProgram(SpecialProgramRequestDTO request) {
		UserVo userDetail = getUserDetail(request.getUserId());
		specialProgramTransactionService.addSpecialProgram(request,userDetail);
	}

	@Override
	public void updateSpecialProgram(SpecialProgramRequestDTO request) {
		UserVo userDetail = getUserDetail(request.getUserId());
		specialProgramTransactionService.updateSpecialProgram(request,userDetail);
	}

	@Override
	public void deleteSpecialProgramById(String specialProgramId, String userId) {
		UserVo userDetail = getUserDetail(userId);
		specialProgramTransactionService.deleteSpecialProgramById(specialProgramId, userDetail);
	}

	@Override
	public ListResult<QueryCompany4AddUserPrivilegeListItem> queryCompanyList(UserVo currentLoginUserVO,
			QueryCompanyListRequest request) {
		CustomerSearchCriteriaDTO customerSearchCriteriaDTO = new CustomerSearchCriteriaDTO();
		customerSearchCriteriaDTO.setUserType(request.getUserType());
		customerSearchCriteriaDTO.setUserStatusWithInList(Arrays.asList(UserStatus.PENDINGACTIVATE.name(), UserStatus.ACTIVATED.name()));
		customerSearchCriteriaDTO.setCompanyName(request.getCompanyName());
		customerSearchCriteriaDTO.setPage(request.getPage());
		customerSearchCriteriaDTO.setPageSize(request.getPageSize());
		ListResult<CustomerSearchResultDTO> resultFromUser = null;
		try{
			resultFromUser = userApi.queryCustomerList(customerSearchCriteriaDTO);
		}catch(Exception e){
			logger.error("", e);
			throw WebException.instance(ReturnCode.ERROR, "查询企业信息失败"); 
		}
		if(resultFromUser == null || resultFromUser.getStatus() != AjaxResponseStatus.SUCCESS)
			throw WebException.instance(ReturnCode.ERROR, "查询企业信息失败");
		
		List<QueryCompany4AddUserPrivilegeListItem> itemList = new ArrayList<>();
		for(CustomerSearchResultDTO rawData : resultFromUser.getData()){
			QueryCompany4AddUserPrivilegeListItem item = new QueryCompany4AddUserPrivilegeListItem();
			item.setMasterId(rawData.getId());
			item.setCompanyName(rawData.getCompanyName());
			item.setUserTypeDesc(rawData.getUserType().desc());
			itemList.add(item);
		}
		
		return ListResult.success("查询成功", itemList, resultFromUser.getTotalNum());
	}
	
	/**
	 * 查询userDetail
	 * @param userId
	 * @return
	 */
	public UserVo getUserDetail(String userId) {
		if(StringUtils.isBlank(userId)){
			WebException.instance("userId为空");
		}
		//根据userId查询用户信息
		UserVo userDetail = memberQueryApi.findUserById(userId);
		if (userDetail == null) {
			throw WebException.instance("用户不存在");
		}
		userDetail.getCustomer().getUserType();
		return userDetail;
	}
	
	
	/**
	 * 获取随机生成的专项计划编号
	 */
	@Override
	public String getSpecialProgramId() {
		return IDGenerator.generateAssetSpecialProgramId();
	}

	/**
//	 * 校验专项计划编号是否存在
	 */
	@Override
	public boolean checkSpecialProgramIdExist(String specialProgramId,String oldId) {
		SpecialProgramExample programExample = new SpecialProgramExample();
		SpecialProgramExample.Criteria criteria = programExample.createCriteria();
			criteria.andIdEqualTo(specialProgramId);
		if (StringUtils.isNotBlank(oldId)) 
			criteria.andIdNotEqualTo(oldId);
		List<SpecialProgram> specialProgram = specialProgramMapper.selectByExample(programExample);
		if (CollectionUtils.isNotEmpty(specialProgram))
			return true;
		return false;
	}

	
	
	
	
	
	@Override
	public ListResult<SpecialProgramIssueListDTO> querySpecialProgramIssueList(SpecialProgramListRequestIssueDTO request,String userId) {
		UserVo userDetail = getUserDetail(userId);
		return specialProgramTransactionService.querySpecialProgramIssueList(request,userDetail);
	}

	@Override
	public void addSpecialProgramIssue(SpecialProgramRequestDTO request) {
		UserVo userDetail = getUserDetail(request.getUserId());
		specialProgramTransactionService.addSpecialProgramIssue(request,userDetail);
		
	}

	@Override
	public void updateSpeicalProgramIssue(SpecialProgramRequestDTO request) {
		UserVo userDetail = getUserDetail(request.getUserId());
		specialProgramTransactionService.updateSpeicalProgramIssue(request,userDetail);
	}
	
}
