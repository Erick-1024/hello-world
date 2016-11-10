package com.cana.asset.service.transaction.impl;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.cana.asset.dao.mapper.AssetLoanCustomMapper;
import com.cana.asset.dao.mapper.UnderlyingAssetSearchMapper;
import com.cana.asset.dao.mapper.gen.LoanPlanMapper;
import com.cana.asset.dao.mapper.gen.SpecialProgramMapper;
import com.cana.asset.dao.mapper.gen.UnderlyingAssetMapper;
import com.cana.asset.dao.po.LoanPlan;
import com.cana.asset.dao.po.LoanPlanExample;
import com.cana.asset.dao.po.SpecialProgram;
import com.cana.asset.dao.po.SpecialProgramExample;
import com.cana.asset.dao.po.UnderlyingAsset;
import com.cana.asset.dao.po.UnderlyingAssetExample;
import com.cana.asset.dao.po.UnderlyingAssetExample.Criteria;
import com.cana.asset.service.convertors.LoanInfoConvertor;
import com.cana.asset.service.convertors.UnderlyingAssetConvertor;
import com.cana.asset.service.transaction.IABSDataPrivilegeTransactionService;
import com.cana.asset.service.transaction.IUnderlyingAssetQueryTransactionService;
import com.cana.vbam.common.asset.enums.UnderlyingAssetPoolStatus;
import com.cana.vbam.common.asset.enums.UnderlyingAssetSource;
import com.cana.vbam.common.asset.loan.dto.AssetLoanPaidListRequest;
import com.cana.vbam.common.asset.loan.dto.LoanPaidDTO;
import com.cana.vbam.common.asset.loan.dto.LoanPlanDTO;
import com.cana.vbam.common.asset.underlyingasset.dto.UnderlyingAssetDTO;
import com.cana.vbam.common.asset.underlyingasset.dto.UnderlyingAssetQueryDTO;
import com.cana.vbam.common.asset.underlyingasset.enums.RequestDirection;
import com.cana.vbam.common.dto.ListResult;
import com.cana.vbam.common.enums.AjaxResponseStatus;
import com.cana.vbam.common.member.enums.user.UserType;
import com.cana.vbam.common.member.vo.UserVo;
import com.google.common.collect.Lists;
import com.travelzen.framework.core.exception.WebException;

@Service
public class UnderlyingAssetQueryTransactionServiceImpl implements IUnderlyingAssetQueryTransactionService{

	@Resource
	private UnderlyingAssetMapper underlyingAssetMapper;
	
	@Resource
	private IABSDataPrivilegeTransactionService absDataPrivilegeTransactionServiceImpl;
	
	@Resource
	private UnderlyingAssetConvertor underlyingAssetConvertor;

	@Resource
	private LoanInfoConvertor loanInforConvertor;
	
	@Resource
	private LoanPlanMapper loanPlanMapper;
	
	@Resource
	private AssetLoanCustomMapper assetLoanCustomMapper;
	
	@Resource
	private SpecialProgramMapper specialProgramMapper;

	@Resource
	private UnderlyingAssetSearchMapper underlyingAssetSearchMapper;
	
	@Override
	public ListResult<UnderlyingAssetDTO> queryUnderlyingAssets(UserVo userVo, UnderlyingAssetQueryDTO queryDTO, RequestDirection requestDirection) {
		ListResult<UnderlyingAssetDTO> result = new ListResult<UnderlyingAssetDTO>();
		UnderlyingAssetExample example = assembleSearchCondition(userVo, queryDTO, Boolean.TRUE, requestDirection);
		example.setLimitStart((queryDTO.getPage() -1) * queryDTO.getPageSize());
		example.setLimitEnd(queryDTO.getPageSize());
		example.setOrderByClause("update_time desc");
		List<UnderlyingAsset> underlyingAssetList = underlyingAssetMapper.selectByExample(example);
		List<UnderlyingAssetDTO> underlyingAssetData = underlyingAssetConvertor.
				convertToUnderlyingAssetDTO(underlyingAssetConvertor.queryUnderlyingAssetData(underlyingAssetList), queryDTO.getLoadExtraData());
		result.setData(underlyingAssetData);
		result.setTotalNum(underlyingAssetMapper.countByExample(example));
		result.setStatus(AjaxResponseStatus.SUCCESS);
		return result;
	}

	@Override
	public List<UnderlyingAssetDTO> queryUnderlyingAssetData(UnderlyingAssetQueryDTO queryDTO){
		UnderlyingAssetExample example = assembleSearchCondition(null, queryDTO, Boolean.FALSE, RequestDirection.UNDERLYING_ASSET);
		example.setLimitStart(0);
		example.setLimitEnd(queryDTO.getPageSize());
		example.setOrderByClause("create_time ASC");
		List<UnderlyingAsset> underlyingAssetList = underlyingAssetMapper.selectByExample(example);
		return underlyingAssetConvertor.convertToUnderlyingAssetDTO(underlyingAssetConvertor.queryUnderlyingAssetData(underlyingAssetList), queryDTO.getLoadExtraData());
//		return underlyingAssetConvertor.convertToUnderlyingAssetDTO(underlyingAssetConvertor.queryUnderlyingAssetData(
//				underlyingAssetSearchMapper.getUnderlyingAssetForIndexUpdateAll(queryDTO.getAndOperationNum(), queryDTO.getResultNum(), 
//				queryDTO.getAssetPoolStatus().name(), queryDTO.getPageSize())), queryDTO.getLoadExtraData());
	}
	
	
	@Override
	public UnderlyingAssetDTO getUnderlyingAssetDetail(UserVo userVo, String underlyingAssetId) {
		UnderlyingAsset underlyingAsset = underlyingAssetMapper.selectByPrimaryKey(underlyingAssetId);
		return underlyingAssetConvertor.convertToUnderlyingAssetDTO(underlyingAssetConvertor.
				queryUnderlyingAssetData(Lists.newArrayList(underlyingAsset)), Boolean.FALSE).get(0);
	}
	
	@Override
	public List<UnderlyingAssetDTO> getUnderlyingAssetDetailList(List<String> underlyingAssetIdList, UnderlyingAssetPoolStatus status){
		UnderlyingAssetExample example = new UnderlyingAssetExample();
		example.createCriteria().andIdIn(underlyingAssetIdList).andAssetPoolStatusEqualTo(status.name());
		List<UnderlyingAsset> underlyingAssetList = underlyingAssetMapper.selectByExample(example);
		return underlyingAssetConvertor.convertToUnderlyingAssetDTO(underlyingAssetConvertor.
				queryUnderlyingAssetData(underlyingAssetList), Boolean.TRUE);
	}

	private UnderlyingAssetExample assembleSearchCondition(UserVo userVo, UnderlyingAssetQueryDTO queryDTO, boolean isNeedPrivilege, RequestDirection requestDirection){
		UnderlyingAssetExample example = new UnderlyingAssetExample();
		Criteria criteria = example.createCriteria();
		if(StringUtils.equals(requestDirection.name(), RequestDirection.ASSET_POOL.name())){
			if(StringUtils.isEmpty(queryDTO.getSpecialProgramId())){
				throw WebException.instance("专项计划Id不能为空");
			}
			String companyName = userVo.getCustomer().getCustomerName();
			SpecialProgramExample spExample = new SpecialProgramExample();
			spExample.createCriteria().andIdEqualTo(queryDTO.getSpecialProgramId());
			spExample.or(spExample.createCriteria().andLawFirmNameEqualTo(companyName));
			spExample.or(spExample.createCriteria().andAccountingFirmNameEqualTo(companyName));
			spExample.or(spExample.createCriteria().andSupervisionBankEqualTo(companyName));
			spExample.or(spExample.createCriteria().andCustodianBankEqualTo(companyName));
			spExample.or(spExample.createCriteria().andRatingAgencyEqualTo(companyName));
			spExample.or(spExample.createCriteria().andAssetEvaluationAgencyEqualTo(companyName));
			List<SpecialProgram> specialProgramList = specialProgramMapper.selectByExample(spExample);
			if(CollectionUtils.isEmpty(specialProgramList)){
				throw WebException.instance("您无权访问该专项计划的基础资产");
			}
		}else{
			if(isNeedPrivilege){
				if(StringUtils.equals(userVo.getCustomer().getUserType().name(), UserType.FACTOR.name())){
					criteria.andFactorIdEqualTo(userVo.getCustomerId());
				}else{
					Set<String> allowedProgramIdList = absDataPrivilegeTransactionServiceImpl.allowedProgramIdList(userVo.getCustomer().getUserType(), userVo.getCustomer().getCustomerName());
					criteria.andSpecialProgramIdIn(Lists.newArrayList(allowedProgramIdList));
				}
			}
		}
		if(StringUtils.isNotBlank(queryDTO.getLoanNo())){
			criteria.andIdLike("%" + queryDTO.getLoanNo() + "%");
		}
		if(StringUtils.isNotBlank(queryDTO.getBusinessContractNo())){
			criteria.andBusinessContractNoLike("%" + queryDTO.getBusinessContractNo() + "%");
		}
		if(StringUtils.isNotBlank(queryDTO.getCustomerName())){
			criteria.andCustomerNameLike("%" + queryDTO.getCustomerName() + "%");
		}
		if(StringUtils.isNotBlank(queryDTO.getLoanDateStart())){
			criteria.andLoanDateGreaterThanOrEqualTo(queryDTO.getLoanDateStart());
		}
		if(StringUtils.isNotBlank(queryDTO.getLoanDateEnd())){
			criteria.andLoanDateLessThan(queryDTO.getLoanDateEnd());
		}
		if(StringUtils.isNotBlank(queryDTO.getDueDateStart())){
			criteria.andDueDateGreaterThanOrEqualTo(queryDTO.getDueDateStart());
		}
		if(StringUtils.isNotBlank(queryDTO.getDueDateEnd())){
			criteria.andDueDateLessThanOrEqualTo(queryDTO.getDueDateEnd());
		}
		if(StringUtils.isNotBlank(queryDTO.getSpecialProgramId())){
			criteria.andSpecialProgramIdEqualTo(queryDTO.getSpecialProgramId());
		}
		if(null != queryDTO.getAssetPoolStatus()){
			criteria.andAssetPoolStatusEqualTo(queryDTO.getAssetPoolStatus().name());
		}
		if(null != queryDTO.getCreateTime()){
			criteria.andCreateTimeGreaterThanOrEqualTo(queryDTO.getCreateTime());
		}
		return example;
	}

	@Override
	public List<LoanPlanDTO> getLoanPlans(UserVo userVo, String underlyingAssetId) {
		checkParamIsValidForAssetPool(userVo, underlyingAssetId);

		LoanPlanExample example = new LoanPlanExample();
		example.createCriteria().andLoanInfoIdEqualTo(underlyingAssetId);
		List<LoanPlan> plans = loanPlanMapper.selectByExample(example);
		return loanInforConvertor.convertLoanPlan2LoanPlanDTO(plans);
	}

	@Override
	public ListResult<LoanPaidDTO> getLoanPaids(UserVo userVo, String underlyingAssetId, int page, int pageSize) {
		checkParamIsValidForAssetPool(userVo, underlyingAssetId);

		AssetLoanPaidListRequest assetLoanPaidListRequest = new AssetLoanPaidListRequest();
		assetLoanPaidListRequest.setLoanId(underlyingAssetId);
		assetLoanPaidListRequest.setPage(page <= 0 ? 1 : page);
		assetLoanPaidListRequest.setPageSize(pageSize <= 0 || pageSize > 10 ? 10 : pageSize);
		List<LoanPaidDTO> loanPaidDTOs = assetLoanCustomMapper.getLoanPaidDTOList(assetLoanPaidListRequest);
		for (LoanPaidDTO loanPaidDTO : loanPaidDTOs) {
			if(StringUtils.isNotBlank(loanPaidDTO.getRepaymentDate())) {
				String diffInDayStr = String.valueOf(loanInforConvertor.diffInDay(loanPaidDTO.getRepaymentDate(), loanPaidDTO.getPaidDate()));
				if(loanPaidDTO.getRepaymentDate().compareTo(loanPaidDTO.getPaidDate()) > 0)
					loanPaidDTO.setForwardDays(diffInDayStr);
				else if(loanPaidDTO.getRepaymentDate().compareTo(loanPaidDTO.getPaidDate()) < 0)
					loanPaidDTO.setOverdueDays(diffInDayStr);
			}
		}
		return ListResult.success(loanPaidDTOs, assetLoanCustomMapper.countLoanPaidDTOList(assetLoanPaidListRequest));
	}

	private void checkParamIsValidForAssetPool(UserVo userVo, String underlyingAssetId) {
		if (userVo == null || StringUtils.isBlank(underlyingAssetId))
			throw WebException.instance("参数不能为空");

		UnderlyingAsset asset = underlyingAssetMapper.selectByPrimaryKey(underlyingAssetId);
		if (asset == null)
			throw WebException.instance("不存在该基础资产");
		if (StringUtils.isBlank(asset.getSpecialProgramId()))
			throw WebException.instance("该基础资产还未入池");
		if (false == absDataPrivilegeTransactionServiceImpl.allow(userVo.getCustomer().getUserType(),
				userVo.getCustomer().getCustomerName(), asset.getSpecialProgramId()))
			throw WebException.instance("无权限访问专项计划[" + asset.getSpecialProgramId() + "]");
		if (!UnderlyingAssetSource.FACTOR.name().equals(asset.getAssetSource()))
			throw WebException.instance("该基础资产不是保理业务放款");
	}
}
