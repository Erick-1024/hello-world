package com.cana.asset.service.transaction.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.cana.asset.dao.mapper.ABSUnderlyingAssetMapper;
import com.cana.asset.dao.mapper.UnderlyingAssetUpdateMapper;
import com.cana.asset.dao.mapper.gen.LoanInfoMapper;
import com.cana.asset.dao.mapper.gen.SpecialProgramMapper;
import com.cana.asset.dao.mapper.gen.UnderlyingAssetMapper;
import com.cana.asset.dao.po.LoanInfo;
import com.cana.asset.dao.po.LoanInfoExample;
import com.cana.asset.dao.po.SpecialProgram;
import com.cana.asset.dao.po.UnderlyingAsset;
import com.cana.asset.dao.po.UnderlyingAssetExample;
import com.cana.asset.service.transaction.IABSDataPrivilegeTransactionService;
import com.cana.asset.service.transaction.IUnderlyingAssetLogTransactionService;
import com.cana.asset.service.transaction.IUnderlyingAssetTransactionService;
import com.cana.asset.service.transaction.util.LoanAndUnderlyingAssetIdUtils;
import com.cana.asset.service.transaction.util.ValidateRules;
import com.cana.vbam.common.asset.enums.SpecialProgramStatus;
import com.cana.vbam.common.asset.enums.UnderlyingAssetOperateTypeEnum;
import com.cana.vbam.common.asset.enums.UnderlyingAssetPoolStatus;
import com.cana.vbam.common.asset.enums.UnderlyingAssetSource;
import com.cana.vbam.common.asset.underlyingasset.dto.EditUnderlyingAssetRequest;
import com.cana.vbam.common.asset.underlyingasset.dto.EnterAssetPoolRequest;
import com.cana.vbam.common.asset.underlyingasset.dto.QueryFactorLoanForUnderlyingAssetRequest;
import com.cana.vbam.common.asset.underlyingasset.dto.QueryFactorLoanForUnderlyingAssetResponse;
import com.cana.vbam.common.asset.underlyingasset.dto.UnderlyingAssetUpdateDTO;
import com.cana.vbam.common.dto.ListResult;
import com.cana.vbam.common.member.enums.user.UserType;
import com.cana.vbam.common.member.vo.UserVo;
import com.cana.vbam.common.repayment.enums.InterestRateUnit;
import com.cana.vbam.common.repayment.enums.SettleStatus;
import com.cana.vbam.common.utils.Constants;
import com.cana.vbam.common.utils.MoneyArithUtil;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.travelzen.framework.core.exception.WebException;
import com.travelzen.framework.core.time.DateTimeUtil;
import com.travelzen.framework.core.util.MoneyUtil;
import com.travelzen.framework.core.util.StringUtil;

/**
 * 基础资产
 * @author XuMeng
 *
 */
@Service
public class UnderlyingAssetTransactionServiceImpl implements IUnderlyingAssetTransactionService {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Resource
	private LoanInfoMapper loanInfoMapper;
	@Resource
	private UnderlyingAssetMapper underlyingAssetMapper;
	@Resource
	private ABSUnderlyingAssetMapper aBSUnderlyingAssetMapper;
	@Resource
	private IABSDataPrivilegeTransactionService privilegeTransactionService;
	@Resource
	private IUnderlyingAssetLogTransactionService underlyingAssetLogTransactionService;
	@Resource
	private SpecialProgramMapper specialProgramMapper;
	@Resource
	private UnderlyingAssetUpdateMapper underlyingAssetUpdateMapper;

	@Override
	public ListResult<QueryFactorLoanForUnderlyingAssetResponse> queryFactorLoanForUnderlyingAsset(
			UserVo userVo, QueryFactorLoanForUnderlyingAssetRequest request) {
		checkUserIsFactor(userVo);
		if (request == null) {
			request = new QueryFactorLoanForUnderlyingAssetRequest();
			request.setPageSize(5);
		}
		if (request.getPage() <= 0) request.setPage(1);
		if (request.getPageSize() <= 0) request.setPageSize(10);
		request.setFactorId(userVo.getCustomerId());
		if (StringUtils.isNotBlank(request.getLoanInfoId()))
			request.setLoanInfoId("%" + request.getLoanInfoId().trim() + "%");
		else 
			request.setLoanInfoId(null);
		if (StringUtils.isNotBlank(request.getFinanceBalanceLower()))
			request.setFinanceBalanceCentLower(MoneyUtil.yuan2Cent(request.getFinanceBalanceLower()));
		else
			request.setFinanceBalanceCentLower(null);
		if (StringUtils.isNotBlank(request.getFinanceBalanceUpper()))
			request.setFinanceBalanceCentUpper(MoneyUtil.yuan2Cent(request.getFinanceBalanceUpper()));
		else
			request.setFinanceBalanceCentUpper(null);

		int count = aBSUnderlyingAssetMapper.countFactorLoanForUnderlyingAsset(request);
		List<QueryFactorLoanForUnderlyingAssetResponse> datas = Lists.newArrayList();
		if (count <= (request.getPage() - 1) * request.getPageSize())
			return ListResult.success(datas, count);
		List<LoanInfo> loanInfos = aBSUnderlyingAssetMapper.queryFactorLoanForUnderlyingAsset(request);
		for (LoanInfo loan : loanInfos) {
			QueryFactorLoanForUnderlyingAssetResponse data = new QueryFactorLoanForUnderlyingAssetResponse();
			data.setLoanInfoId(loan.getId());
			data.setCustomerName(loan.getCustomerName());
			data.setFinanceAmount(MoneyUtil.cent2Yuan(loan.getFinanceAmount()));
			data.setFinanceBalance(MoneyUtil.cent2Yuan(loan.getFinanceBalance()));
			data.setDueDate(loan.getDueDate());
			data.setSettleStatusDesc(SettleStatus.valueOf(loan.getSettleStatus()).desc());
			datas.add(data);
		}
		return ListResult.success(datas, count);
	}

	@Override
	public void createUnderlyingAssetByFactorLoan(UserVo userVo, List<String> loanInfoIds) {
		checkUserIsFactor(userVo);
		List<LoanInfo> loanInfos = lockLoanInfoByIds(loanInfoIds);
		logger.info("用户[{}]正在进行将保理放款{}转为基础资产操作", userVo.getUserId(), loanInfoIds);
		checkLoanInfosForCreate(userVo, loanInfoIds, loanInfos);

		List<UnderlyingAsset> assets = Lists.newArrayList();
		for (LoanInfo loan : loanInfos) {
			UnderlyingAsset asset = new UnderlyingAsset();
			asset.setId(loan.getId());
			asset.setBusinessContractNo(loan.getBusinessContractNo());
			asset.setAssetPoolStatus(UnderlyingAssetPoolStatus.NOT_ENTER.name());
			asset.setAssetSource(UnderlyingAssetSource.FACTOR.name());
			asset.setFactorId(userVo.getCustomerId());
			asset.setCustomerId(loan.getCustomerId());
			asset.setCustomerName(loan.getCustomerName());
			asset.setCounterpartyId(loan.getCounterpartyId());
			asset.setLoanDate(loan.getLoanDate());
			asset.setDueDate(loan.getDueDate());
			asset.setCreateTime(new Date());
			asset.setUpdateTime(new Date());
			assets.add(asset);
			underlyingAssetMapper.insertSelective(asset);
		}

		// 添加新增日志
		underlyingAssetLogTransactionService.insertUnderlyingAssetLog(assets, UnderlyingAssetOperateTypeEnum.CREATE, userVo);
	}

	private void checkLoanInfosForCreate(UserVo userVo, List<String> loanInfoIds, List<LoanInfo> loanInfos) {
		if (CollectionUtils.isEmpty(loanInfos) || loanInfos.size() != loanInfoIds.size())
			throw WebException.instance("放款编号列表有误");
		for (LoanInfo loan : loanInfos) {
			if (!StringUtils.equals(userVo.getCustomerId(), loan.getFactorId()))
				throw WebException.instance("当前客户对放款编号为[" + loan.getId() + "]没有操作权限");
		}

		UnderlyingAssetExample example = new UnderlyingAssetExample();
		example.createCriteria().andIdIn(loanInfoIds);
		List<UnderlyingAsset> assets = underlyingAssetMapper.selectByExample(example);
		if (!CollectionUtils.isEmpty(assets))
			throw WebException.instance("放款编号[" + assets.get(0).getId() + "]已经是基础资产了，不能进行当前操作");
	}

	private List<LoanInfo> lockLoanInfoByIds(List<String> loanInfoIds) {
		if (CollectionUtils.isEmpty(loanInfoIds))
			throw WebException.instance("放款编号不能为空");
		for (String loanInfoId : loanInfoIds) {
			if (StringUtils.isBlank(loanInfoId))
				throw WebException.instance("放款编号列表存在为空的编号");
		}
		LoanInfoExample example = new LoanInfoExample();
		example.createCriteria().andIdIn(loanInfoIds);
		List<LoanInfo> loanInfos = loanInfoMapper.lockByExample(example);
		return loanInfos;
	}

	private void checkUserIsFactor(UserVo userVo) {
		if (userVo == null)
			throw WebException.instance("当前登陆用户不能为空");
		if (userVo.getCustomer().getUserType() != UserType.FACTOR)
			throw WebException.instance("当前登陆用户必须是保理商");
	}

	@Override
	public void deleteUnderlyingAsset(UserVo userVo, String underlyingAssetId) {
		checkUserIsFactor(userVo);
		UnderlyingAsset asset = lockAndCheckForAsset(underlyingAssetId);

		if (!StringUtils.equals(userVo.getCustomerId(), asset.getFactorId()))
			throw WebException.instance("当前客户对该基础资产无操作权限");
		if (!UnderlyingAssetPoolStatus.NOT_ENTER.name().equals(asset.getAssetPoolStatus()))
			throw WebException.instance("该基础资产已经入池，不能删除");

		logger.info("用户[{}]执行删除基础资产[{}]操作", userVo.getUsername(), underlyingAssetId);
		underlyingAssetMapper.deleteByPrimaryKey(asset.getId());

		// 添加删除日志
		underlyingAssetLogTransactionService.insertUnderlyingAssetLog(asset, UnderlyingAssetOperateTypeEnum.DELETE, userVo);
	}

	private UnderlyingAsset lockAndCheckForAsset(String underlyingAssetId) {
		if (StringUtils.isBlank(underlyingAssetId))
			throw WebException.instance("放款编号不能为空");
		UnderlyingAsset asset = underlyingAssetMapper.lockByPrimaryKey(underlyingAssetId);
		if (asset == null)
			throw WebException.instance("基础资产[" + underlyingAssetId + "]不存在");
		return asset;
	}

	private List<UnderlyingAsset> lockAndCheckForAssets(List<String> underlyingAssetIds) {
		if (CollectionUtils.isEmpty(underlyingAssetIds))
			throw WebException.instance("基础资产编号不能为空");
		for (String underlyingAssetId : underlyingAssetIds) {
			if (StringUtils.isBlank(underlyingAssetId))
				throw WebException.instance("基础资产编号不能为空");
		}

		UnderlyingAssetExample example = new UnderlyingAssetExample();
		example.createCriteria().andIdIn(underlyingAssetIds);
		List<UnderlyingAsset> assets = underlyingAssetMapper.lockByExample(example);
		if (CollectionUtils.isEmpty(assets) || assets.size() != underlyingAssetIds.size())
			throw WebException.instance("基础资产编号有误，请重新选择");

		return assets;
	}

	@Override
	public void bindSpecialProgram(UserVo userVo, EnterAssetPoolRequest request) {
		checkUserIsFactor(userVo);
		if (request == null || CollectionUtils.isEmpty(request.getUnderlyingAssetIds()) || StringUtils.isBlank(request.getSpecialProgramId()))
			throw WebException.instance("请求参数不能为空");

		List<UnderlyingAsset> assets = lockAndCheckForAssets(request.getUnderlyingAssetIds());

		if (false == privilegeTransactionService.allow(userVo.getCustomer().getUserType(),
				userVo.getCustomer().getCustomerName(), request.getSpecialProgramId()))
			throw WebException.instance("对该专项计划无数据操作权限");

		checkProgramCanBindPoolOrEnterPool(request.getSpecialProgramId());

		logger.info("用户[{}]进行入备选池操作，请求：[{}]", userVo.getUserId(), new Gson().toJson(request));

		for (UnderlyingAsset asset : assets) {
			if (!StringUtils.equals(userVo.getCustomerId(), asset.getFactorId()))
				throw WebException.instance("当前客户对基础资产[" + asset.getId() + "]无操作权限");
			if (!UnderlyingAssetPoolStatus.NOT_ENTER.name().equals(asset.getAssetPoolStatus()))
				throw WebException.instance("基础资产[" + asset.getId() + "]已入池，不能再做入池操作");
			asset.setAssetPoolStatus(UnderlyingAssetPoolStatus.ENTERING.name());
			asset.setSpecialProgramId(StringUtils.trim(request.getSpecialProgramId()));
		}

		for (UnderlyingAsset asset : assets) {
			asset.setUpdateTime(new Date());
			underlyingAssetMapper.updateByPrimaryKey(asset);

			//添加入备选池日志
			underlyingAssetLogTransactionService.insertUnderlyingAssetLog(asset, UnderlyingAssetOperateTypeEnum.ENTER_ALTERNATE_POOL, userVo);
		}
	}

	@Override
	public void unbindSpecialProgram(UserVo userVo, String underlyingAssetId) {
		checkUserIsFactor(userVo);
		UnderlyingAsset asset = lockAndCheckForAsset(underlyingAssetId);

		if (!StringUtils.equals(userVo.getCustomerId(), asset.getFactorId()))
			throw WebException.instance("当前客户对该基础资产无操作权限");
		if (!UnderlyingAssetPoolStatus.ENTERING.name().equals(asset.getAssetPoolStatus()))
			throw WebException.instance("基础资产[" + asset.getId() + "]已入池，不能做出池操作");

//		checkProgramExistAndNotClosed(asset.getSpecialProgramId());

		logger.info("用户[{}]进行出备选池操作，基础资产为：[{}]", userVo.getUserId(), underlyingAssetId);

		asset.setAssetPoolStatus(UnderlyingAssetPoolStatus.NOT_ENTER.name());
		asset.setSpecialProgramId(null);
		asset.setUpdateTime(new Date());
		underlyingAssetMapper.updateByPrimaryKey(asset);

		//添加出备选池日志
		underlyingAssetLogTransactionService.insertUnderlyingAssetLog(asset, UnderlyingAssetOperateTypeEnum.OUT_ALTERNATE_POOL, userVo);
	}

	@Override
	public boolean checkUnbindForDeleteProgram(String programId) {
		if (StringUtils.isBlank(programId))
			throw WebException.instance("专项计划ID不能为空");
		UnderlyingAssetExample example = new UnderlyingAssetExample();
		example.createCriteria()
			.andAssetPoolStatusEqualTo(UnderlyingAssetPoolStatus.ENTERED.name())
			.andSpecialProgramIdEqualTo(StringUtils.trim(programId));
		return CollectionUtils.isEmpty(underlyingAssetMapper.selectByExample(example));
	}

	@Override
	public void checkAndUnbindForDeleteProgram(UserVo userVo, String programId) {
		if (StringUtils.isBlank(programId))
			throw WebException.instance("专项计划ID不能为空");
		UnderlyingAssetExample example = new UnderlyingAssetExample();
		example.createCriteria()
			.andSpecialProgramIdEqualTo(StringUtils.trim(programId));
		List<UnderlyingAsset> assets = underlyingAssetMapper.lockByExample(example);

		logger.info("用户[{}]删除专项计划时自动执行基础资产出备选池操作，专项计划为：{}，影响基础资产条数为：{}",
				userVo.getUserId(), programId, assets == null ? 0 : assets.size());

		if (CollectionUtils.isEmpty(assets))
			return;

		for (UnderlyingAsset asset : assets) {
			if (!UnderlyingAssetPoolStatus.ENTERING.name().equals(asset.getAssetPoolStatus()))
				throw WebException.instance("基础资产[" + asset.getId() + "]已入池，不能删除专项计划");

			asset.setAssetPoolStatus(UnderlyingAssetPoolStatus.NOT_ENTER.name());
			asset.setSpecialProgramId(null);
		}

		for (UnderlyingAsset asset : assets) {
			logger.info("用户[{}]删除专项计划时自动执行基础资产[{}]出备选池操作", userVo.getUserId(), asset.getId());

			asset.setUpdateTime(new Date());
			underlyingAssetMapper.updateByPrimaryKey(asset);

			//  添加出备选池日志
			underlyingAssetLogTransactionService.insertUnderlyingAssetLog(asset, UnderlyingAssetOperateTypeEnum.OUT_ALTERNATE_POOL, userVo);
		}
	}

	@Override
	public void enterAssetPool(UserVo userVo, List<String> underlyingAssetIds) {
		checkUserIsBrokerTrustOrg(userVo);
		List<UnderlyingAsset> assets = lockAndCheckForAssets(underlyingAssetIds);

		String programId = assets.get(0).getSpecialProgramId();
		if (StringUtils.isBlank(programId))
			throw WebException.instance("基础资产[" + assets.get(0).getId() + "]不在备选池当中，不能进行入池操作");
		if (false == privilegeTransactionService.allow(userVo.getCustomer().getUserType(),
				userVo.getCustomer().getCustomerName(), programId))
			throw WebException.instance("当前客户对专项计划[" + programId + "]无操作权限");

		for (UnderlyingAsset asset : assets) {
			if (!StringUtils.equals(programId, asset.getSpecialProgramId()))
				throw WebException.instance("基础资产[" + asset.getId() + "]没有关联专项计划[" + programId + "]，不能进行入池操作");
			if (!UnderlyingAssetPoolStatus.ENTERING.name().equals(asset.getAssetPoolStatus()))
				throw WebException.instance("基础资产[" + asset.getId() + "]不在备选池当中，不能进行入池操作");

			asset.setAssetPoolStatus(UnderlyingAssetPoolStatus.ENTERED.name());
		}

		checkProgramCanBindPoolOrEnterPool(programId);

		logger.info("用户[{}]进行基础资产入正式池操作，基础资产为：{}", userVo.getUserId(), underlyingAssetIds);
		for (UnderlyingAsset asset : assets) {
			asset.setUpdateTime(new Date());
			underlyingAssetMapper.updateByPrimaryKey(asset);

			//  添加入正式池日志
			underlyingAssetLogTransactionService.insertUnderlyingAssetLog(asset, UnderlyingAssetOperateTypeEnum.ENTER_POOL, userVo);
		}
	}

	/**
	 * 判断一个专项计划不能是结束状态
	 * @param programId
	 */
	private SpecialProgram checkProgramExistAndNotClosed(String programId) {
		SpecialProgram program = specialProgramMapper.lockByPrimaryKey(programId);
		if (program == null)
			throw WebException.instance("专项计划[" + programId + "]不存在");
		if (SpecialProgramStatus.CLOSE.name().equals(program.getStatus()))
			throw WebException.instance("专项计划[" + programId + "]已结束，不能进行当前操作");
		return program;
	}

	/**
	 * 判断一个专项计划是否允许入正式池或者入备选池
	 * @param programId
	 */
	private void checkProgramCanBindPoolOrEnterPool(String programId) {
		SpecialProgram program = checkProgramExistAndNotClosed(programId);
		if (SpecialProgramStatus.ESTABLISH.name().equals(program.getStatus())
				&& BooleanUtils.isNotTrue(program.getCyclePurchaseStructure()))
			throw WebException.instance("专项计划[" + programId + "]已成立且不为循环购买结构，不能进行当前操作");
	}

	@Override
	public void outAssetPoolAndDelete(UserVo userVo, String underlyingAssetId) {
		checkUserIsBrokerTrustOrg(userVo);
		UnderlyingAsset asset = lockAndCheckForAsset(underlyingAssetId);
		checkBrokerTrustOrgCanOperateProgram(userVo, asset.getSpecialProgramId());

		if (!UnderlyingAssetPoolStatus.ENTERED.name().equals(asset.getAssetPoolStatus()))
			throw WebException.instance("基础资产[" + asset.getId() + "]没有入池，不能进行出池操作");

		checkProgramExistAndNotClosed(asset.getSpecialProgramId());

		logger.info("用户[{}]进行基础资产出正式池并删除操作，基础资产为：{}", userVo.getUserId(), underlyingAssetId);
		underlyingAssetMapper.deleteByPrimaryKey(asset.getId());

		// 添加出池日志
		underlyingAssetLogTransactionService.insertUnderlyingAssetLog(asset, UnderlyingAssetOperateTypeEnum.OUT_POOL, userVo);
	}

	@Override
	public void outAssetPoolAndKeepBind(UserVo userVo, String underlyingAssetId) {
		checkUserIsBrokerTrustOrg(userVo);
		UnderlyingAsset asset = lockAndCheckForAsset(underlyingAssetId);
		checkBrokerTrustOrgCanOperateProgram(userVo, asset.getSpecialProgramId());

		if (!UnderlyingAssetPoolStatus.ENTERED.name().equals(asset.getAssetPoolStatus()))
			throw WebException.instance("基础资产[" + asset.getId() + "]没有入池，不能进行待入池操作");

		checkProgramExistAndNotClosed(asset.getSpecialProgramId());

		logger.info("用户[{}]进行基础资产待入池操作，基础资产为：{}", userVo.getUserId(), underlyingAssetId);
		asset.setAssetPoolStatus(UnderlyingAssetPoolStatus.ENTERING.name());
		asset.setUpdateTime(new Date());
		underlyingAssetMapper.updateByPrimaryKey(asset);

		// 添加待入池日志
		underlyingAssetLogTransactionService.insertUnderlyingAssetLog(asset, UnderlyingAssetOperateTypeEnum.PENDING_ENTER_POOL, userVo);
	}

	@Override
	public void redeemAssetPool(UserVo userVo, String underlyingAssetId) {
		checkUserIsBrokerTrustOrg(userVo);
		UnderlyingAsset asset = lockAndCheckForAsset(underlyingAssetId);
		checkBrokerTrustOrgCanOperateProgram(userVo, asset.getSpecialProgramId());

		if (!UnderlyingAssetPoolStatus.ENTERED.name().equals(asset.getAssetPoolStatus()))
			throw WebException.instance("基础资产[" + asset.getId() + "]没有入池，不能进行赎回操作");

		checkProgramExistAndNotClosed(asset.getSpecialProgramId());

		logger.info("用户[{}]进行基础资产赎回操作，基础资产为：{}", userVo.getUserId(), underlyingAssetId);
		underlyingAssetMapper.deleteByPrimaryKey(asset.getId());

		// 添加赎回日志
		underlyingAssetLogTransactionService.insertUnderlyingAssetLog(asset, UnderlyingAssetOperateTypeEnum.REDEMPTION, userVo);
	}

	private void checkBrokerTrustOrgCanOperateProgram(UserVo userVo, String programId) {
		if (false == privilegeTransactionService.allow(userVo.getCustomer().getUserType(),
				userVo.getCustomer().getCustomerName(), programId))
			throw WebException.instance("当前客户对专项计划[" + programId + "]无操作权限");
	}

	private void checkUserIsBrokerTrustOrg(UserVo userVo){
		if (userVo == null)
			throw WebException.instance("当前登陆用户不能为空");
		if (userVo.getCustomer().getUserType() != UserType.BROKERTRUSTORG)
			throw WebException.instance("当前登陆用户必须是券商/信托");
	}

	@Override
	public void checkImportUnderlyingAssetRequest(UserVo userVo, EditUnderlyingAssetRequest request) {
		checkUserIsFactor(userVo);
		checkEditUnderlyingAssetRequestIsValid(userVo, request);
		LoanAndUnderlyingAssetIdUtils.checkUnderlyingAssetIdIsValid(request.getContractNo(), request.getUnderlyingAssetId());
		if (null != underlyingAssetMapper.selectByPrimaryKey(request.getUnderlyingAssetId()))
			throw WebException.instance("放款编号已存在，不能导入");
	}

	@Override
	public void importUnderlyingAsset(UserVo userVo, List<EditUnderlyingAssetRequest> requests) {
		checkUserIsFactor(userVo);

		List<String> underlyingAssetIds = Lists.newArrayList();
		List<UnderlyingAsset> underlyingAssets = Lists.newArrayList();
		for (EditUnderlyingAssetRequest request : requests) {
			checkEditUnderlyingAssetRequestIsValid(userVo, request);
			LoanAndUnderlyingAssetIdUtils.checkUnderlyingAssetIdIsValid(request.getContractNo(), request.getUnderlyingAssetId());

			if (underlyingAssetIds.contains(request.getUnderlyingAssetId()))
				throw WebException.instance("本次导入存在重复的基础资产[]");
			underlyingAssetIds.add(request.getUnderlyingAssetId());

			UnderlyingAsset asset = new UnderlyingAsset();

			asset.setAssetPoolStatus(UnderlyingAssetPoolStatus.NOT_ENTER.name());
			asset.setAssetSource(UnderlyingAssetSource.MANUAL.name());
			asset.setFactorId(userVo.getCustomerId());
			convertUnderlyingAsset(request, asset);

			underlyingAssets.add(asset);
		}

		UnderlyingAssetExample example = new UnderlyingAssetExample();
		example.createCriteria().andIdIn(underlyingAssetIds);
		List<UnderlyingAsset> existAssets = underlyingAssetMapper.selectByExample(example);
		if (!CollectionUtils.isEmpty(existAssets))
			throw WebException.instance("基础资产编号[" + existAssets.get(0).getId() + "]已存在，不能导入");

		for (UnderlyingAsset asset : underlyingAssets) {
			logger.info("用户[{}]进行基础资产批量导入操作，当前基础资产为：{}", userVo.getUserId(), asset.getId());
			asset.setCreateTime(new Date());
			asset.setUpdateTime(new Date());
			underlyingAssetMapper.insert(asset);

			// 添加新建日志
			underlyingAssetLogTransactionService.insertUnderlyingAssetLog(asset, UnderlyingAssetOperateTypeEnum.CREATE, userVo);
		}
	}

	private UnderlyingAsset convertUnderlyingAsset(EditUnderlyingAssetRequest request, UnderlyingAsset asset) {
		asset.setId(request.getUnderlyingAssetId());
		asset.setBusinessContractNo(request.getContractNo());
		asset.setCustomerName(request.getCustomerName());
		asset.setCustomerEconomicCategory(request.getCustomerEconomicCategory());
		asset.setCustomerCity(request.getCustomerCity());
		asset.setCustomerIndustry(request.getCustomerIndustry());
		asset.setCustomerRating(request.getCustomerRating());
		asset.setCounterparty(request.getCounterparty());
		asset.setCounterpartyEconomicCategory(request.getCounterpartyEconomicCategory());
		asset.setCounterpartyCity(request.getCounterpartyCity());
		asset.setCounterpartyIndustry(request.getCounterpartyIndustry());
		asset.setCounterpartyRating(request.getCounterpartyRating());
		asset.setGuaranteeInfo(request.getGuaranteeInfo());
		asset.setGuaranteeType(request.getGuaranteeType());
		asset.setGuaranteeCompanyInfo(request.getGuaranteeCompanyInfo());
		asset.setGuaranteeCompanyType(request.getGuaranteeCompanyType());
		asset.setGuaranteeGoodsNo(request.getGuaranteeGoodsNo());
		asset.setCreditLimit(MoneyUtil.yuan2Cent(request.getCreditLimit()));
		asset.setCreditBalance(MoneyUtil.yuan2Cent(request.getCreditBalance()));
		asset.setCounterpartyLimit(MoneyUtil.yuan2Cent(request.getCounterpartyLimit()));
		asset.setCounterpartyBalance(MoneyUtil.yuan2Cent(request.getCounterpartyBalance()));
		asset.setInvoiceAmount(MoneyUtil.yuan2Cent(request.getInvoiceAmount()));
		asset.setInvoiceBalance(MoneyUtil.yuan2Cent(request.getInvoiceBalance()));
		asset.setFinanceAmount(MoneyUtil.yuan2Cent(request.getFinanceAmount()));
		asset.setFinanceBalance(MoneyUtil.yuan2Cent(request.getFinanceBalance()));
		asset.setLoanDate(request.getLoanDate());
		asset.setDueDate(request.getDueDate());
		asset.setRepaymentMethod(request.getRepaymentMethod());
		asset.setInterestRateUnit(request.getInterestRateUnit());
		asset.setInterestRate(MoneyArithUtil.convertStringToInterestRate(request.getInterestRate()));
		asset.setLoanPeriod(request.getLoanPeriod());
		asset.setFiveLevelCategory(request.getFiveLevelCategory());
		asset.setSettleStatus(request.getSettleStatus());
		asset.setForwardDays(request.getForwardDays());
		asset.setOverdueDays(request.getOverdueDays());
		asset.setExtensionDays(request.getExtensionDays());
		return asset;
	}

	@Override
	public void updateUnderlyingAsset(UserVo userVo, EditUnderlyingAssetRequest request) {
		checkUserIsFactor(userVo);
		checkEditUnderlyingAssetRequestIsValid(userVo, request);

		UnderlyingAsset dbAsset = underlyingAssetMapper.lockByPrimaryKey(request.getUnderlyingAssetId());
		if (dbAsset == null)
			throw WebException.instance("基础资产不存在");
		if (!StringUtils.equals(userVo.getCustomerId(), dbAsset.getFactorId()))
			throw WebException.instance("当前客户无权限操作该基础资产");
		if (!UnderlyingAssetSource.MANUAL.name().equals(dbAsset.getAssetSource()))
			throw WebException.instance("非手动导入的基础资产不能修改");
		String dbProgramId = StringUtils.trimToNull(dbAsset.getSpecialProgramId());
		String reqProgramId = StringUtils.trimToNull(request.getProgramId());
		if (!StringUtils.equals(dbProgramId, reqProgramId)
				&& UnderlyingAssetPoolStatus.ENTERED.name().equals(dbAsset.getAssetPoolStatus()))
			throw WebException.instance("已入正式池的基础资产不能修改专项计划编号");

		if (reqProgramId == null) {
			dbAsset.setAssetPoolStatus(UnderlyingAssetPoolStatus.NOT_ENTER.name());
		} else {
			if (false == privilegeTransactionService.allow(userVo.getCustomer().getUserType(),
					userVo.getCustomer().getCustomerName(), reqProgramId))
				throw WebException.instance("对该专项计划[" + reqProgramId + "]无数据操作权限");
			dbAsset.setAssetPoolStatus(UnderlyingAssetPoolStatus.ENTERING.name());
		}
		dbAsset.setSpecialProgramId(reqProgramId);
		convertUnderlyingAsset(request, dbAsset);

		logger.info("用户[{}]进行基础资修改操作，基础资产为：{}", userVo.getUserId(), dbAsset.getId());
		dbAsset.setUpdateTime(new Date());
		underlyingAssetMapper.updateByPrimaryKey(dbAsset);

		// 添加修改日志
		underlyingAssetLogTransactionService.insertUnderlyingAssetLog(dbAsset, UnderlyingAssetOperateTypeEnum.UPDATE, userVo);
	}

	@Override
	public void updateUnderlyingAssetInCondition(UnderlyingAssetUpdateDTO updateDTO) {
		updateDTO.setOperationNum(getOperationNum(updateDTO.getOperatePositionAndNum()));
		underlyingAssetUpdateMapper.updateUnderlyingAssetInCondition(updateDTO);
	}

	public int getOperationNum(Map<Integer,Integer> operatePositionAndNum){
		StringBuilder sb = new StringBuilder(Constants.UPDATE_INDEX_FLAGS_STR);
		for(int position:operatePositionAndNum.keySet()){
			sb.replace(sb.length() - position - 1, sb.length() - position - 1, operatePositionAndNum.get(position).toString());
		}
        int operationNum = 0;
        for(char c: sb.toString().toCharArray())
        	operationNum = operationNum * 2 + (c == '1' ? 1 : 0);
		return operationNum;
	}
	
	private void checkEditUnderlyingAssetRequestIsValid(UserVo userVo, EditUnderlyingAssetRequest request) {
		if (request == null)
			throw new IllegalArgumentException("请求参数不能为空");
		StringUtil.trimObjectFields(request);
		if (StringUtils.isBlank(request.getContractNo()))
			throw WebException.instance("业务合同号不能为空");
		if (request.getContractNo().length() > 24)
			throw WebException.instance("业务合同号过长");
		if (StringUtils.isBlank(request.getUnderlyingAssetId()))
			throw WebException.instance("放款编号不能为空");
		if (StringUtils.isBlank(request.getCustomerName()))
			throw WebException.instance("借款人名称不能为空");
		if (StringUtils.isBlank(request.getCustomerEconomicCategory()))
			throw WebException.instance("借款人类型不能为空");
		if (request.getCustomerEconomicCategory().length() > 30)
			throw WebException.instance("借款人类型过长");
		if (StringUtils.isBlank(request.getCounterparty()))
			throw WebException.instance("交易对手名称不能为空");
		if (StringUtils.isBlank(request.getCounterpartyEconomicCategory()))
			throw WebException.instance("交易对手类型不能为空");
		if (request.getCounterpartyEconomicCategory().length() > 30)
			throw WebException.instance("交易对手类型过长");
		if (StringUtils.isBlank(request.getCustomerCity()))
			throw WebException.instance("借款人所在地区不能为空");
		if (request.getCustomerCity().length() > 30)
			throw WebException.instance("借款人所在地区过长");
		if (StringUtils.isBlank(request.getCustomerIndustry()))
			throw WebException.instance("借款人所属行业不能为空");
		if (request.getCustomerIndustry().length() > 30)
			throw WebException.instance("借款人所属行业过长");
		if (StringUtils.isNotBlank(request.getCustomerRating()) && request.getCustomerRating().length() > 30)
			throw WebException.instance("借款人评级过长");
		if (StringUtils.isBlank(request.getCounterpartyCity()))
			throw WebException.instance("交易对手所在地区不能为空");
		if (request.getCounterpartyCity().length() > 30)
			throw WebException.instance("交易对手所在地区过长");
		if (StringUtils.isBlank(request.getCounterpartyIndustry()))
			throw WebException.instance("交易对手所属行业不能为空");
		if (request.getCounterpartyIndustry().length() > 30)
			throw WebException.instance("交易对手所属行业过长");
		if (StringUtils.isNotBlank(request.getCounterpartyRating()) && request.getCounterpartyRating().length() > 30)
			throw WebException.instance("交易对手评级过长");

		if (StringUtils.isNotBlank(request.getGuaranteeInfo()) && request.getGuaranteeInfo().length() > 30)
			throw WebException.instance("基础资产担保人信息过长");
		if (StringUtils.isNotBlank(request.getGuaranteeType()) && request.getGuaranteeType().length() > 30)
			throw WebException.instance("基础资产担保人类型过长");
		if (StringUtils.isNotBlank(request.getGuaranteeCompanyInfo()) && request.getGuaranteeCompanyInfo().length() > 30)
			throw WebException.instance("基础资产担保企业信息过长");
		if (StringUtils.isNotBlank(request.getGuaranteeCompanyType()) && request.getGuaranteeCompanyType().length() > 30)
			throw WebException.instance("基础资产担保企业类型过长");
		if (StringUtils.isNotBlank(request.getGuaranteeGoodsNo()) && request.getGuaranteeGoodsNo().length() > 30)
			throw WebException.instance("担保品编号过长");

		if (StringUtils.isBlank(request.getCreditLimit())
				|| !ValidateRules.regexAmountCheck(request.getCreditLimit())
				|| MoneyUtil.yuan2Cent(request.getCreditLimit()) <= 0)
			throw WebException.instance("授信额度不正确");

		if (StringUtils.isBlank(request.getCreditBalance())
				|| !ValidateRules.regexAmountCheck(request.getCreditBalance())
				|| MoneyUtil.yuan2Cent(request.getCreditBalance()) < 0)
			throw WebException.instance("授信可用余额不正确");

		if (MoneyUtil.yuan2Cent(request.getCreditBalance()) > MoneyUtil.yuan2Cent(request.getCreditLimit()))
			throw WebException.instance("授信余额不能大于授信额度");

		if (StringUtils.isBlank(request.getCounterpartyLimit())
				|| !ValidateRules.regexAmountCheck(request.getCounterpartyLimit())
				|| MoneyUtil.yuan2Cent(request.getCounterpartyLimit()) < 0)
			throw WebException.instance("交易对手非承保额度不正确");

		if (StringUtils.isBlank(request.getCounterpartyBalance())
				|| !ValidateRules.regexAmountCheck(request.getCounterpartyBalance())
				|| MoneyUtil.yuan2Cent(request.getCounterpartyBalance()) < 0)
			throw WebException.instance("交易对手非承保额度余额不正确");

		if (MoneyUtil.yuan2Cent(request.getCounterpartyBalance()) > MoneyUtil.yuan2Cent(request.getCounterpartyLimit()))
			throw WebException.instance("交易对手非承保额度余额不能大于交易对手非承保额度");

		if (StringUtils.isBlank(request.getInvoiceAmount())
				|| !ValidateRules.regexAmountCheck(request.getInvoiceAmount())
				|| MoneyUtil.yuan2Cent(request.getInvoiceAmount()) <= 0)
			throw WebException.instance("应收账款金额不正确");

		if (StringUtils.isBlank(request.getInvoiceBalance())
				|| !ValidateRules.regexAmountCheck(request.getInvoiceBalance())
				|| MoneyUtil.yuan2Cent(request.getInvoiceBalance()) < 0)
			throw WebException.instance("应收账款余额不正确");

		if (MoneyUtil.yuan2Cent(request.getInvoiceBalance()) > MoneyUtil.yuan2Cent(request.getInvoiceAmount()))
			throw WebException.instance("应收账款余额不能大于应收账款金额");

		if (StringUtils.isBlank(request.getFinanceAmount())
				|| !ValidateRules.regexAmountCheck(request.getFinanceAmount())
				|| MoneyUtil.yuan2Cent(request.getFinanceAmount()) <= 0)
			throw WebException.instance("融资金额不正确");

		if (StringUtils.isBlank(request.getFinanceBalance())
				|| !ValidateRules.regexAmountCheck(request.getFinanceBalance())
				|| MoneyUtil.yuan2Cent(request.getFinanceBalance()) < 0)
			throw WebException.instance("融资余额不正确");

		if (MoneyUtil.yuan2Cent(request.getFinanceBalance()) > MoneyUtil.yuan2Cent(request.getFinanceAmount()))
			throw WebException.instance("融资余额不能大于融资金额");

		if (!DateTimeUtil.validateDate10(request.getLoanDate()))
			throw WebException.instance("不是合法的起息日");
		if (!DateTimeUtil.validateDate10(request.getDueDate()))
			throw WebException.instance("不是合法的到期日");
		if (request.getDueDate().compareTo(request.getLoanDate()) <= 0)
			throw WebException.instance("到期日必须大于放款日");

		if (StringUtils.isBlank(request.getRepaymentMethod()))
			throw WebException.instance("还款方式不能为空");
		if (request.getRepaymentMethod().length() > 30)
			throw WebException.instance("还款方式过长");

		if (!EnumUtils.isValidEnum(InterestRateUnit.class, request.getInterestRateUnit()))
			throw WebException.instance("利率类型必须是日/月/年");
		if (StringUtils.isBlank(request.getInterestRate())
				|| !ValidateRules.regexPercentCheck(request.getInterestRate())
				|| MoneyArithUtil.convertStringToInterestRate(request.getInterestRate()).compareTo(BigDecimal.ZERO) <= 0)
			throw WebException.instance("利率不合法");
		if (StringUtils.isBlank(request.getLoanPeriod()))
			throw WebException.instance("期限不能为空");
		if (request.getLoanPeriod().length() > 30)
			throw WebException.instance("期限过长");
		if (StringUtils.isNotBlank(request.getFiveLevelCategory()) && request.getFiveLevelCategory().length() > 30)
			throw WebException.instance("五级分类过长");
		if (StringUtils.isBlank(request.getSettleStatus()))
			request.setSettleStatus(SettleStatus.UNSETTLE.name());
		else if (!EnumUtils.isValidEnum(SettleStatus.class, request.getSettleStatus()))
			throw WebException.instance("结清标示不合法");

		if (request.getForwardDays() < 0)
			throw WebException.instance("提前还款天数必需大于等于0");
		if (request.getExtensionDays() < 0)
			throw WebException.instance("展期天数必需大于等于0");
		if (request.getOverdueDays() < 0)
			throw WebException.instance("违约天数必需大于等于0");

	}
}
