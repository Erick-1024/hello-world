package com.cana.vbam.front.biz.controller.asset;

import java.util.Arrays;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cana.asset.api.IABSSpecialProgramApi;
import com.cana.asset.api.IABSUnderlyingAssetApi;
import com.cana.asset.api.IABSUnderlyingAssetSearchApi;
import com.cana.member.api.IMemberQueryApi;
import com.cana.member.authorization.common.SecurityContextUtils;
import com.cana.vbam.common.asset.dto.SpecialProgramListDTO;
import com.cana.vbam.common.asset.dto.SpecialProgramListRequestDTO;
import com.cana.vbam.common.asset.underlyingasset.dto.ConvertToUnderlyingAssetRequest;
import com.cana.vbam.common.asset.underlyingasset.dto.EditUnderlyingAssetRequest;
import com.cana.vbam.common.asset.underlyingasset.dto.EnterAssetPoolRequest;
import com.cana.vbam.common.asset.underlyingasset.dto.QueryFactorLoanForUnderlyingAssetRequest;
import com.cana.vbam.common.asset.underlyingasset.dto.QueryFactorLoanForUnderlyingAssetResponse;
import com.cana.vbam.common.asset.underlyingasset.dto.UnderlyingAssetDTO;
import com.cana.vbam.common.asset.underlyingasset.dto.UnderlyingAssetQueryDTO;
import com.cana.vbam.common.asset.underlyingasset.dto.UnderlyingAssetSearchCriteria;
import com.cana.vbam.common.asset.underlyingasset.enums.RequestDirection;
import com.cana.vbam.common.dto.ListResult;
import com.cana.vbam.common.dto.ObjectResult;
import com.cana.vbam.common.repayment.enums.InterestRateUnit;
import com.cana.vbam.common.repayment.enums.SettleStatus;
import com.cana.vbam.common.utils.FrontExceptionHandler;
import com.travelzen.framework.core.exception.WebException;

@Controller
@RequestMapping("/asset/underlyingAsset")
public class ABSUnderlyingAssetController {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Resource
	private IABSUnderlyingAssetApi underlyingAssetApi;
	
	@Resource
	private IMemberQueryApi memberQueryApi;
	
	@Resource
	private IABSSpecialProgramApi assetSpecialProgramApi;
	
	@Resource
	private IABSUnderlyingAssetSearchApi underlyingAssetSearchApi;
	
	@RequestMapping(value = "/underlyingAssetList", method = RequestMethod.GET )
	public String underlyingAssetListPage(Model model) {
		return "page/asset/underlyingAsset/underlyingAssetList";
	}

	@RequestMapping(value = "/queryUnderlyingAssetList", method = RequestMethod.POST )
	@ResponseBody
	public ListResult<UnderlyingAssetDTO> queryUnderlyingAssetList(@RequestBody UnderlyingAssetQueryDTO queryDTO) {
		try{
			return underlyingAssetApi.queryUnderlyingAssets(memberQueryApi.findUserById(SecurityContextUtils.getUserDTOFromSession().getId()), queryDTO, RequestDirection.UNDERLYING_ASSET);
		} catch (WebException e) {
			logger.error(e.getMessage(), e);
			return ListResult.fail(e.getMessage());
		} catch(Exception e){
			logger.error("未知异常", e);
			return ListResult.fail("未知异常");
		}
	}
	
	@RequestMapping(value = "/editAsset", method = RequestMethod.GET )
	public String editAssetPage(Model model,String assetId) {
		logger.info("进入【基础资产修改】页面");
		model.addAttribute("dto", underlyingAssetApi.getUnderlyingAssetDetail(memberQueryApi.findUserById(SecurityContextUtils.getUserDTOFromSession().getId()), assetId));
		model.addAttribute("settleStatusList", Arrays.asList(SettleStatus.values()));
		model.addAttribute("interestRateUnitList", Arrays.asList(InterestRateUnit.values()));
		return "page/asset/underlyingAsset/editAsset";
	}
	
	@RequestMapping(value = "/editAsset", method = { RequestMethod.POST })
	@ResponseBody
	public ObjectResult<?> editAsset(@RequestBody EditUnderlyingAssetRequest editAssetLoanRequest) {
		try{
			underlyingAssetApi.updateUnderlyingAsset(SecurityContextUtils.getOperatorId(), editAssetLoanRequest);
			return ObjectResult.success("更新基础资产成功", "更新基础资产成功");
		} catch (Exception e) {
			return FrontExceptionHandler.handleObjectResultException(e);
		}
	}
	
	//获取专项计划列表
	@RequestMapping(value = "/getProgramList", method = { RequestMethod.POST })
	@ResponseBody
	public ListResult<SpecialProgramListDTO> getSpecialProgramList(SpecialProgramListRequestDTO request) {
		try {
			request.setUserId(SecurityContextUtils.getUserDTOFromSession().getId());
			request.setForUnderlyingAssetEnter(true);
			return assetSpecialProgramApi.querySpecialProgramList(request);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return ListResult.fail(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/entering/search", method = RequestMethod.POST)
	@ResponseBody
	public ListResult<UnderlyingAssetDTO> searchEnteringUnderlyingAsset(UnderlyingAssetSearchCriteria criteria){
		try {
			criteria.setUserId(SecurityContextUtils.getOperatorId());
			return underlyingAssetSearchApi.searchUnderlyAsset(criteria);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return ListResult.fail("搜索失败");
		}
	}

	@RequestMapping(value = "/underlyingAssetDetail", method = RequestMethod.GET )
	public String underlyingAssetDetail(Model model,String underlyingAssetId) {
		logger.info("进入【基础资产详情】页面");
		//根据assetId查询数据
		UnderlyingAssetDTO underlyingAssetDTO = underlyingAssetApi.getUnderlyingAssetDetail(memberQueryApi.findUserById(SecurityContextUtils.getUserDTOFromSession().getId()), underlyingAssetId);
		model.addAttribute("underlyingAssetDTO", underlyingAssetDTO);
		return "page/asset/underlyingAsset/underlyingAssetDetail";
	}
	
	@RequestMapping(value = "/bindSpecialProgram", method = RequestMethod.POST)
	@ResponseBody
	public ObjectResult<String> bindSpecialProgram(@RequestBody EnterAssetPoolRequest request){
		try {
			underlyingAssetApi.bindSpecialProgram(memberQueryApi.findUserById(SecurityContextUtils.getUserDTOFromSession().getId()).getCustomerId(), request);
			return ObjectResult.success();
		} catch (WebException e) {
			logger.error(e.getMessage(), e);
			return ObjectResult.fail(e.getMessage());
		} catch(Exception e){
			logger.error("未知异常", e);
			return ObjectResult.fail("未知异常");
		}
	}

	@RequestMapping(value = "/unbindSpecialProgram", method = RequestMethod.POST)
	@ResponseBody
	public ObjectResult<String> unbindSpecialProgram(String underlyingAssetId){
		try {
			underlyingAssetApi.unbindSpecialProgram(memberQueryApi.findUserById(SecurityContextUtils.getUserDTOFromSession().getId()).getCustomerId(), underlyingAssetId);
			return ObjectResult.success();
		} catch (WebException e) {
			logger.error(e.getMessage(), e);
			return ObjectResult.fail(e.getMessage());
		} catch(Exception e){
			logger.error("未知异常", e);
			return ObjectResult.fail("未知异常");
		}
	}

	@RequestMapping(value = "/deleteUnderlyingAsset", method = RequestMethod.POST)
	@ResponseBody
	public ObjectResult<String> deleteUnderlyingAsset(String underlyingAssetId){
		try {
			underlyingAssetApi.deleteUnderlyingAsset(memberQueryApi.findUserById(SecurityContextUtils.getUserDTOFromSession().getId()).getCustomerId(), underlyingAssetId);
			return ObjectResult.success();
		} catch (WebException e) {
			logger.error(e.getMessage(), e);
			return ObjectResult.fail(e.getMessage());
		} catch(Exception e){
			logger.error("未知异常", e);
			return ObjectResult.fail("未知异常");
		}
	}
	
	@RequestMapping(value = "/queryFactorLoanForUnderlyingAsset", method = { RequestMethod.POST })
	@ResponseBody
	public ListResult<QueryFactorLoanForUnderlyingAssetResponse> queryFactorLoanForUnderlyingAsset(QueryFactorLoanForUnderlyingAssetRequest request) {
		try {
			request.setFactorId(SecurityContextUtils.getCustomerId());
			return underlyingAssetApi.queryFactorLoanForUnderlyingAsset(SecurityContextUtils.getCustomerId(),request);
		}  catch (WebException e) {
			logger.error(e.getMessage(), e);
			return ListResult.fail(e.getMessage());
		} catch(Exception e){
			logger.error("未知异常", e);
			return ListResult.fail("未知异常");
		}
	}
	
	@RequestMapping(value = "/createUnderlyingAssetByFactorLoan", method = RequestMethod.POST)
	@ResponseBody
	public ObjectResult<String> createUnderlyingAssetByFactorLoan(@RequestBody ConvertToUnderlyingAssetRequest request){
		try {
			underlyingAssetApi.createUnderlyingAssetByFactorLoan(SecurityContextUtils.getCustomerId(), request);
			return ObjectResult.success();
		} catch (WebException e) {
			logger.error(e.getMessage(), e);
			return ObjectResult.fail(e.getMessage());
		} catch(Exception e){
			logger.error("未知异常", e);
			return ObjectResult.fail("未知异常");
		}
	}
}
