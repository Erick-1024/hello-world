package com.cana.vbam.front.biz.controller.asset;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cana.asset.api.IAssetLoanInfoApi;
import com.cana.member.authorization.common.SecurityContextUtils;
import com.cana.vbam.common.asset.loan.dto.AssetLoanDTO;
import com.cana.vbam.common.asset.loan.dto.AssetPaidPlanRequest;
import com.cana.vbam.common.dto.ObjectResult;
import com.cana.vbam.common.utils.FrontExceptionHandler;
import com.google.gson.Gson;

@Controller
@RequestMapping("/asset/loan/paid")
public class AssetLoanPaidController {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Resource
	private IAssetLoanInfoApi assetLoanInfoApiImpl;
	
	@RequestMapping(method = RequestMethod.GET)
	public String paidView(String loanInfoId, Model model) {
		AssetLoanDTO loanDTO = assetLoanInfoApiImpl.getLoanDetail(loanInfoId, SecurityContextUtils.getOperatorId());
		model.addAttribute("assetLoanDTO", loanDTO);
		return "page/asset/loan/paid";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public ObjectResult<?> postPaid(@RequestBody AssetPaidPlanRequest request) {
		try {
			logger.info("还款冲销请求数据：" + new Gson().toJson(request));
			assetLoanInfoApiImpl.paidAssetLoanPlan(SecurityContextUtils.getOperatorId(), request);
			return ObjectResult.success();
		} catch (Exception e) {
			return FrontExceptionHandler.handleObjectResultException(e);
		}
	}
}
