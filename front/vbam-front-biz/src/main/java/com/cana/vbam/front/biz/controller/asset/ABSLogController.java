package com.cana.vbam.front.biz.controller.asset;

import java.util.Arrays;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cana.asset.api.IABSLogApi;
import com.cana.member.api.IMemberQueryApi;
import com.cana.member.authorization.common.SecurityContextUtils;
import com.cana.vbam.common.asset.enums.SpecialProgramStatus;
import com.cana.vbam.common.asset.enums.UnderlyingAssetOperateTypeEnum;
import com.cana.vbam.common.asset.underlyingasset.dto.SpecialProgramLogQuery;
import com.cana.vbam.common.asset.underlyingasset.dto.UnderlyingAssetLogQuery;
import com.cana.vbam.common.dto.ListResult;
import com.travelzen.framework.core.exception.WebException;

/**
 * 管理日志
 * @author yihong.tang
 *
 */
@Controller
@RequestMapping("/asset/log")
public class ABSLogController {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Resource
	private IMemberQueryApi memberQueryApi;
	
	@Resource
	private IABSLogApi absLogApi;
	
	
	@RequestMapping(value = "/list", method = RequestMethod.GET )
	public String logListPage(Model model) {
		logger.info("进入【管理日志】页面");
		boolean permSpecialProgram = SecurityContextUtils.authorizePermKey("SIM_LOG_SPECIAL_PROGRAM");
		boolean permAsset = SecurityContextUtils.authorizePermKey("SIM_LOG_ASSET");
		model.addAttribute("permSpecialProgram", permSpecialProgram);
		model.addAttribute("permAsset", permAsset);
		model.addAttribute("specialProgramStatusList", Arrays.asList(SpecialProgramStatus.values()));
		model.addAttribute("underlyingAssetOperateTypeList", Arrays.asList(UnderlyingAssetOperateTypeEnum.values()));
		return "page/asset/log/list";
	}
	
	@RequestMapping(value = "/searchSpecialProgramLog", method = { RequestMethod.POST })
	@ResponseBody
	public ListResult<?> searchSpecialProgramLog(SpecialProgramLogQuery query) {
		try{
			return absLogApi.querySpecialProgramLogs(memberQueryApi.findUserById(SecurityContextUtils.getUserDTOFromSession().getId()), query);
		} catch (WebException e) {
			logger.error(e.getMessage(), e);
			return ListResult.fail(e.getMessage());
		} catch(Exception e){
			logger.error("未知异常", e);
			return ListResult.fail("未知异常");
		}
	}
	
	@RequestMapping(value = "/searchUnderlyingAssetLog", method = { RequestMethod.POST })
	@ResponseBody
	public ListResult<?> searchUnderlyingAssetLog(UnderlyingAssetLogQuery query) {
		try{
			return absLogApi.queryUnderlyingAssetLogs(memberQueryApi.findUserById(SecurityContextUtils.getUserDTOFromSession().getId()), query);
		} catch (WebException e) {
			logger.error(e.getMessage(), e);
			return ListResult.fail(e.getMessage());
		} catch(Exception e){
			logger.error("未知异常", e);
			return ListResult.fail("未知异常");
		}
	}
	
}
