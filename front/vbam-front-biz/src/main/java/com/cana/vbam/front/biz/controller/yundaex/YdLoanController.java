/**
 * 
 */
package com.cana.vbam.front.biz.controller.yundaex;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.cana.member.authorization.common.SecurityContextUtils;
import com.cana.vbam.common.dto.ObjectResult;
import com.cana.vbam.common.dto.PageResult;
import com.cana.vbam.common.member.dto.user.UserSessionDTO;
import com.cana.vbam.common.member.enums.user.UserType;
import com.cana.vbam.common.yundaex.dto.loanApply.YundaexLoanApplyDTO;
import com.cana.vbam.common.yundaex.dto.loanApply.YundaexLoanApplyQueryDTO;
import com.cana.vbam.common.yundaex.dto.loanInfo.YundaexLoanFlowListDTO;
import com.cana.vbam.common.yundaex.dto.loanInfo.YundaexLoanFlowQueryDTO;
import com.cana.yundaex.api.IYundaexLoanApplyApi;
import com.travelzen.framework.core.exception.WebException;

/**
 * 韵达项目－用款申请
 * 
 * @author guguanggong
 *
 */
@Controller
@RequestMapping(value = "/yundaex/loan")
public class YdLoanController {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Resource
	private IYundaexLoanApplyApi yundaexloanApplyApi;
	
	/**
	 * 放款申请页面数据
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/loanDetail", method = RequestMethod.GET)
	public String getLoanDetail(Model model) {
		String memberId = getCurrentUser();
		YundaexLoanApplyDTO yundaexLoanApplyDTO = yundaexloanApplyApi.getLoanApplyDetails(memberId);
		model.addAttribute("yundaexLoanApplyDTO", yundaexLoanApplyDTO);
		logger.info("进入放款申请页面");
		return "page/yundaex/loan/loanDetail";
	}

	/**
	 * 提交放款信息
	 * @param applyQueryDTO
	 * @return
	 */
	@RequestMapping(value = "/creditLoanApply", method = RequestMethod.POST)
	@ResponseBody
	public ObjectResult<String> creditLoanApply(@RequestBody YundaexLoanApplyQueryDTO applyQueryDTO) {
		try {
			UserSessionDTO userSessionDTO = SecurityContextUtils.getUserDTOFromSession();
			yundaexloanApplyApi.creditLoanApply(applyQueryDTO, userSessionDTO);
			return ObjectResult.success("成功");
		} catch (Exception e) {
			logger.error("参数校验失败", e);
			return ObjectResult.fail(e.getMessage());
		}
	}

	/**
	 * 申请用款流水列表
	 * @return
	 */
	@RequestMapping(value="/loanFlow",method=RequestMethod.GET)
	public String gotoLoanFlow(){
		logger.info("进入申请用款流水列表");
		return "page/yundaex/loan/loanFlow";
	}
	
	/**
	 * 获取申请用款流水列表
	 */
	@RequestMapping(value = "/loanFlow", method = RequestMethod.POST)
	@ResponseBody
	public PageResult<YundaexLoanFlowListDTO> searchLoanFlowList(YundaexLoanFlowQueryDTO loanFlowQueryDTO) {
		PageResult<YundaexLoanFlowListDTO> pageResult = new PageResult<>();
		try {
			UserSessionDTO userSessionDTO = SecurityContextUtils.getUserDTOFromSession();
			String memberId = null;
			if (userSessionDTO.getUserType() == UserType.FINACE) {
				memberId = StringUtils.isBlank(userSessionDTO.getMasterId()) ? userSessionDTO.getId()
						: userSessionDTO.getMasterId();
			}
			logger.info("查询申请用款流水列表");
			pageResult = yundaexloanApplyApi.queryLoanFlowList(loanFlowQueryDTO, memberId);
		} catch (Exception e) {
			logger.error("查询申请用款流水列表，{}", e);
		}
		return pageResult;
	}
	
	/**
	 * 获取当前用户信息
	 * @return
	 */
	private String getCurrentUser() {
		UserSessionDTO userSessionDTO = SecurityContextUtils.getUserDTOFromSession();
		String masterId = "";
		if (userSessionDTO == null) {
			throw WebException.instance("系统错误");
		}
		if (userSessionDTO.getUserType() != UserType.FINACE) {
			throw WebException.instance("该用户不能操作用款申请");
		} else {
			masterId = StringUtils.isBlank(userSessionDTO.getMasterId()) ? userSessionDTO.getId()
					: userSessionDTO.getMasterId();
		}
		return masterId;
	}

}
