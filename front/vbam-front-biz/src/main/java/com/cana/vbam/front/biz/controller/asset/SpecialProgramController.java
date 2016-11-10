package com.cana.vbam.front.biz.controller.asset;

import java.io.IOException;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cana.asset.api.IABSSpecialProgramApi;
import com.cana.member.api.IMemberQueryApi;
import com.cana.member.api.IUserApi;
import com.cana.member.authorization.common.SecurityContextUtils;
import com.cana.vbam.common.asset.dto.QueryCompany4AddUserPrivilegeListItem;
import com.cana.vbam.common.asset.dto.QueryCompanyListRequest;
import com.cana.vbam.common.asset.dto.SpecialProgramDTO;
import com.cana.vbam.common.asset.dto.SpecialProgramIssueListDTO;
import com.cana.vbam.common.asset.dto.SpecialProgramListDTO;
import com.cana.vbam.common.asset.dto.SpecialProgramListRequestDTO;
import com.cana.vbam.common.asset.dto.SpecialProgramListRequestIssueDTO;
import com.cana.vbam.common.asset.dto.SpecialProgramRequestDTO;
import com.cana.vbam.common.asset.enums.BasicAssetType;
import com.cana.vbam.common.asset.enums.SpecialProgramStatus;
import com.cana.vbam.common.dto.ListResult;
import com.cana.vbam.common.dto.ObjectResult;
import com.cana.vbam.common.member.vo.UserVo;

/**
 * @author jiangzhou.Ren
 * @time 2016年8月31日下午3:36:25
 * 专项计划
 */


@Controller
@RequestMapping("/asset/specialprogram")
public class SpecialProgramController {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Resource
	private IMemberQueryApi memberQueryApi;
	

	@Resource
	private IUserApi userApi;
	
	
	@Resource
	private IABSSpecialProgramApi assetSpecialProgramApi;
	
	/**
	 * 专项初始化页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/specialProgram/list", method = { RequestMethod.GET })
	public String specialProgramListPage(Model model) {
		model.addAttribute("specialProgramStatuss", SpecialProgramStatus.values());
		model.addAttribute("basicAssetTypes", BasicAssetType.values());
		return "page/asset/specialprogram/specialProgramList";

	}
	
	/**
	 * 专项计划列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/querySpecialProgram", method = { RequestMethod.POST })
	@ResponseBody
	public ListResult<SpecialProgramListDTO> querySpecialProgramList(SpecialProgramListRequestDTO request) {
		try {
			request.setUserId(SecurityContextUtils.getUserDTOFromSession().getId());
			return assetSpecialProgramApi.querySpecialProgramList(request);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return ListResult.fail(e.getMessage());
		}
	}
	
	
	/**
	 * 专项计划详情
	 * @param specialProgramId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/specialProgramDetail", method = { RequestMethod.GET })
	public String specialProgramDetail(@RequestParam String specialProgramId, Model model) {
		String userId = SecurityContextUtils.getUserDTOFromSession().getId();
		SpecialProgramDTO specialProgramDTO = assetSpecialProgramApi.getSpecialProgramById(specialProgramId, userId);
		model.addAttribute("specialProgramDTO", specialProgramDTO);
		logger.info("进入专项计划查询详情页面");
		return "page/asset/specialprogram/specialProgramDetail";

	}
	
	
	/**
	 * 专项计划新增
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/addSpeciaProgram")
	public String addSpecialProgram(Model model) throws Exception {
		String companyName = SecurityContextUtils.getUserDTOFromSession().getCompanyName();
		String specialProgramId = assetSpecialProgramApi.getSpecialProgramId();
		model.addAttribute("specialProgramId", specialProgramId);
		model.addAttribute("companyName", companyName);
		model.addAttribute("basicAssetTypes", BasicAssetType.values());
		logger.info("进入专项计划新增页面");
		
		return "page/asset/specialprogram/addSpecialProgram";
	}
	
	
	/**
	 * 专项计划新增提交数据
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/addSpecialProgramFormData", method = { RequestMethod.POST })
	@ResponseBody
	public ObjectResult<String> addSpecialProgramFormData(@RequestBody SpecialProgramRequestDTO request)
			throws IOException {
		try {
			request.setUserId(SecurityContextUtils.getUserDTOFromSession().getId());
			assetSpecialProgramApi.addSpecialProgram(request);
			return ObjectResult.success("专项计划新增成功");
		} catch (Exception e) {
			logger.error("专项计划新增失败", e.getMessage());
			e.printStackTrace();
			return ObjectResult.fail(e.getMessage());
		}
	}
	
	
	/**
	 * 修改专项计划
	 * @param specialProgramId
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateSpecialProgram", method = { RequestMethod.GET })
	public String updateSpecialProgram(@RequestParam String specialProgramId, Model model) throws Exception {
		model.addAttribute("specialProgramStatuss", SpecialProgramStatus.values());
		model.addAttribute("basicAssetTypes", BasicAssetType.values());
		String userId = SecurityContextUtils.getUserDTOFromSession().getId();
		SpecialProgramDTO specialProgramDTO = assetSpecialProgramApi.getSpecialProgramById(specialProgramId, userId);
		model.addAttribute("specialProgramDTO", specialProgramDTO);
		logger.info("进入专项计划修改页面");
		return "page/asset/specialprogram/updateSpecialProgram";
	}
	
	
	/**
	 * 修改专项计划提交数据
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "updataSpecialProgramFormData", method = { RequestMethod.POST })
	@ResponseBody
	public ObjectResult<String> updateSpecialProgramFormData(@RequestBody SpecialProgramRequestDTO request) {
		try {
			request.setUserId(SecurityContextUtils.getUserDTOFromSession().getId());
			assetSpecialProgramApi.updateSpecialProgram(request);
			return ObjectResult.success("修改专项计划成功");
		} catch (Exception e) {
			logger.error("专项计划修改失败", e.getMessage());
			e.printStackTrace();
			return ObjectResult.fail(e.getMessage());
		}
	}
	
	
	/**
	 * 删除专项计划
	 * @param specialProgramId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/delete", method = { RequestMethod.POST })
	@ResponseBody
	public ObjectResult<String> deleteSprecialProgram(@RequestParam String specialProgramId) throws Exception {
		try {
			String userId = SecurityContextUtils.getUserDTOFromSession().getId();
			assetSpecialProgramApi.deleteSpecialProgramById(specialProgramId, userId);
			return ObjectResult.success("删除专项计划成功");
		} catch (Exception e) {
			logger.error("删除专项计划错误",e.getMessage());
			e.printStackTrace();
			return ObjectResult.fail(e.getMessage());
		}
		
	}
	
	/**
	 * 查询专项计划原始权益人和资产服务机构企业信息列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryCompanyList", method = RequestMethod.POST)
	@ResponseBody
	public ListResult<QueryCompany4AddUserPrivilegeListItem> queryCompanyList(QueryCompanyListRequest request) {
		try {
			String userId = SecurityContextUtils.getUserDTOFromSession().getId();
			UserVo currentLoginUserVO = memberQueryApi.findUserById(userId);
			return assetSpecialProgramApi.queryCompanyList(currentLoginUserVO, request);
		} catch (Exception e) {
			logger.error("专项计划企业信息查询错误",e.getMessage());
			return ListResult.fail(e.getMessage());
		}
	}
	
	
	/**
	 * 校验专项计划编号
	 * @param specialProgramId
	 * @param oldId
	 * @return
	 */
	@RequestMapping(value = "/checkSpecialProgramId", method = RequestMethod.POST)
	@ResponseBody
	public boolean checkSpecialProgramIdExist(@RequestParam String specialProgramId, String oldId) {
		try {
			return assetSpecialProgramApi.checkSpecialProgramIdExist(specialProgramId,oldId);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return false;
		}
	}
	
	
	/**
	 * 校验专项计划管理人名称是否存在
	 * @param companyName
	 * @return
	 */
	@RequestMapping(value = "/checkManageName", method = RequestMethod.POST)
	@ResponseBody
	public boolean checkManageNameExist(@RequestParam String companyName) {
		try {
			boolean checkCompanyNameExist = userApi.checkCompanyNameExist(companyName, SecurityContextUtils.getUserDTOFromSession().getUserType());
			if(checkCompanyNameExist == true){
				return false;
			}
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return true;
		}
	}
	
	
	/********************************发行后专项计划管理************************/
	
	/**
	 * 成立后专项初始化页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/specialProgramIssue/list", method = { RequestMethod.GET })
	public String specialProgramIssueListPage(Model model) {
		model.addAttribute("specialProgramStatuss", SpecialProgramStatus.values());
		model.addAttribute("basicAssetTypes", BasicAssetType.values());
		return "page/asset/specialprogram/issueList";

	}
	
	
	/**
	 * 成立后专项计划列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryList", method = { RequestMethod.POST })
	@ResponseBody
	public ListResult<SpecialProgramIssueListDTO> querySpecialProgramIssueList(SpecialProgramListRequestIssueDTO request) {
		try {
			String userId = SecurityContextUtils.getUserDTOFromSession().getId();
			return assetSpecialProgramApi.querySpecialProgramIssueList(request, userId);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return ListResult.fail(e.getMessage());
		}
	}
	
	
	/**
	 * 专项计划成立
	 * @param specialProgramId
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/issueAdd")
	public String SpecialProgramIssueAdd(@RequestParam String specialProgramId,Model model) throws Exception {
		String userId = SecurityContextUtils.getUserDTOFromSession().getId();
		SpecialProgramDTO specialProgramDTO = assetSpecialProgramApi.getSpecialProgramById(specialProgramId, userId);
		model.addAttribute("specialProgramDTO", specialProgramDTO);
		logger.info("进入专项计划成立页面");
		return "page/asset/specialprogram/issueAdd";
	}
	
	
	/**
	 * 专项计划成立提交数据
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/issueAddFormData", method = { RequestMethod.POST })
	@ResponseBody
	public ObjectResult<String> SpecialProgramIssueAddFormData(@RequestBody SpecialProgramRequestDTO request)throws IOException {
		try {
			request.setUserId(SecurityContextUtils.getUserDTOFromSession().getId());
			assetSpecialProgramApi.addSpecialProgramIssue(request);
			return ObjectResult.success("专项计划成立新增成功");
		} catch (Exception e) {
			logger.error("专项计划成立新增失败", e.getMessage());
			e.printStackTrace();
			return ObjectResult.fail(e.getMessage());
		}
	}
	
	
	/**
	 * 进入专项计划成立管理页面
	 * @param specialProgramId
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/issueUpdate", method = { RequestMethod.GET })
	public String SpecialProgramIssueUpdate(@RequestParam String specialProgramId, Model model) throws Exception {
		model.addAttribute("specialProgramStatuss", SpecialProgramStatus.values());
		model.addAttribute("basicAssetTypes", BasicAssetType.values());
		String userId = SecurityContextUtils.getUserDTOFromSession().getId();
		SpecialProgramDTO specialProgramDTO = assetSpecialProgramApi.getSpecialProgramById(specialProgramId, userId);
		model.addAttribute("specialProgramDTO", specialProgramDTO);
		logger.info("进入专项计划成立管理页面");
		return "page/asset/specialprogram/issueUpdate";
	}
	
	
	/**
	 * 发行后专项计划管理成立修改
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/issueUpdateFormData", method = { RequestMethod.POST })
	@ResponseBody
	public ObjectResult<String> SpecialPragramIssueUpdateFormData(@RequestBody SpecialProgramRequestDTO request)throws IOException{
		try {
			request.setUserId(SecurityContextUtils.getUserDTOFromSession().getId());
			assetSpecialProgramApi.updateSpeicalProgramIssue(request);
			return ObjectResult.success("发行后专项计划成立修改成功");
		} catch (Exception e) {
			logger.error("发行后专项计划成立修改失败",e.getMessage());
			e.printStackTrace();
			return ObjectResult.fail(e.getMessage());
		}
	}
	
	
}
