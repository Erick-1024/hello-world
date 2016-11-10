package com.cana.vbam.front.biz.controller.asset;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.cana.asset.api.IAssetProjectManageApi;
import com.cana.member.api.IMemberQueryApi;
import com.cana.member.authorization.common.SecurityContextUtils;
import com.cana.vbam.common.asset.dto.ProjectDTO;
import com.cana.vbam.common.asset.dto.ProjectFactorDTO;
import com.cana.vbam.common.asset.dto.ProjectListRequestDTO;
import com.cana.vbam.common.asset.dto.ProjectListResponseDTO;
import com.cana.vbam.common.asset.dto.ProjectRequestDTO;
import com.cana.vbam.common.asset.dto.ProjectStatusResponseDTO;
import com.cana.vbam.common.asset.enums.ProjectErrorFieldEnum;
import com.cana.vbam.common.asset.enums.ProjectFactorStatusEnum;
import com.cana.vbam.common.dto.ListResult;
import com.cana.vbam.common.dto.PageResult;
import com.cana.vbam.common.member.enums.user.UserType;
import com.cana.vbam.common.member.vo.UserVo;
import com.travelzen.framework.core.exception.WebException;
import com.travelzen.tops.mediaserver.client.MediaClientUtil;
import com.travelzen.tops.mediaserver.client.MediaClientUtil.MediaType;


/**
 * @author jiangzhou.Ren
 * @time 2016年5月25日上午10:27:19
 */
@Controller
@RequestMapping("/asset/project")
public class AssetProjectController {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Resource
	private IAssetProjectManageApi assetProjectManageApi;
	@Resource
	private IMemberQueryApi memberQueryApi;
	
	// 返回项目管理页面
	@RequestMapping(value = "/projectList", method = { RequestMethod.GET })
	public String listApply(Model model) {
		String userId = SecurityContextUtils.getUserDTOFromSession().getId();
		UserVo userVo = memberQueryApi.findUserById(userId);
		model.addAttribute("userVo",userVo );
		logger.info("进入项目管理页面");
		return "page/asset/project/projectList";
	}
	
	
	
	@RequestMapping(value = "/searchList", method = { RequestMethod.POST })
	@ResponseBody
	public ListResult<ProjectListResponseDTO> searchProjectList(ProjectListRequestDTO request) {
		// 获取session中userId
		String userId = SecurityContextUtils.getUserDTOFromSession().getId();
		try {
			// 查询项目管理列表信息
			ListResult<ProjectListResponseDTO> projectLists = assetProjectManageApi.getProjectList(userId, request);
			logger.info("项目管理列表查询页！");
			return projectLists;
		} catch (Exception e) {
			logger.error("获取项目管理列表错误", e);
			e.printStackTrace();
			return ListResult.fail(e.getMessage());
		}
	}
	
	
	
	//项目详情页面
	@RequestMapping(value = "/projectDetail", method = { RequestMethod.GET })
	public String detail(@RequestParam String projectId, Model model) {
		// 获取session中userId
		String userId = SecurityContextUtils.getUserDTOFromSession().getId();
		// 查询项目详情
		ProjectDTO projectDTO = assetProjectManageApi.getProjectDetail(userId, projectId);
		model.addAttribute("projectDTO", projectDTO);
		logger.info("进入项目查询详情页面");
		return "page/asset/project/projectDetail";
	}
	
	
	//资金方list
	@RequestMapping(value = "/projectDetails")
	@ResponseBody
	public PageResult<ProjectFactorDTO> detailList(@RequestParam String projectId) {
		try {
			String userId = SecurityContextUtils.getUserDTOFromSession().getId();
			// 查询项目详情
			ProjectDTO projectDTO = assetProjectManageApi.getProjectDetail(userId, projectId);
			List<ProjectFactorDTO> projectFactors = projectDTO.getProjectFactors();
			return new PageResult<ProjectFactorDTO>(projectFactors, projectFactors.size());
		} catch (Exception e) {
			logger.error("获取项目资金方详情错误", e);
			e.printStackTrace();
		}
		return null;
	}
	//项目管理返回新增项目页面
	@RequestMapping(value="/projectCreate")
	public String projectCreate(Model model) throws Exception{
		UserType userType = SecurityContextUtils.getUserDTOFromSession().getUserType();
		//只有资金方可以跳转到新建页面
		if (userType != UserType.FACTOR) {
			throw WebException.instance("只有资金方才有权限新建");
		} 
		//资金方信息
		String userId = SecurityContextUtils.getUserDTOFromSession().getId();
		UserVo userVo = memberQueryApi.findUserById(userId);
		model.addAttribute("projectFactorStatusEnums",ProjectFactorStatusEnum.projectFactorStatusEnums() );
		model.addAttribute("userVo",userVo );
		logger.info("进入项目管理新增项目页面");
		return "page/asset/project/projectCreate";
	}
	
	//项目新增form数据提交
	@RequestMapping(value="projectCreateFormDate", method = { RequestMethod.POST })
	@ResponseBody
	public ProjectStatusResponseDTO projectCreateFormDate(@RequestBody ProjectRequestDTO projectRequest) {
		try {
			String userId = SecurityContextUtils.getUserDTOFromSession().getId();
			//设置合同权限havePermissionModifyDocument
			projectRequest.setHavePermissionModifyDocument(true);
			ProjectStatusResponseDTO projectStatusResponseDTO = assetProjectManageApi.addProject(userId,projectRequest);
			return projectStatusResponseDTO;
		} catch(WebException e){
			Map<ProjectErrorFieldEnum,String> errorInfos = new HashMap<>();
			errorInfos.put(ProjectErrorFieldEnum.error,e.getMessage());
			return new ProjectStatusResponseDTO(errorInfos);
		}catch (Exception e) {
			logger.info("项目新增异常");
			e.printStackTrace();
		}
		return null;
	}
	
	
	//合同文件上传
	@RequestMapping(value="saveFile",method=RequestMethod.POST)
	public void uploadFile(MultipartFile file, HttpServletResponse httpServletResponse) throws IOException{
		String mediaId = "";
		try {
			mediaId = MediaClientUtil.upload(file.getBytes(), MediaType.IMAGE, file.getOriginalFilename());
		} catch (Exception e) {
			logger.error("文件上传失败", e);
			mediaId = "FAILED";
		}
		httpServletResponse.setContentType("text/html");
		httpServletResponse.getWriter().write(mediaId);
	}
	
	//项目管理修改项目数据回填页面
	@RequestMapping(value = "/projectUpdate", method = { RequestMethod.GET })
	public String projectUpdate(@RequestParam String projectId, Model model) {
		// 获取session中userId
		UserType userType = SecurityContextUtils.getUserDTOFromSession().getUserType();
		//只有资金方可以跳转到新建页面
		if (userType != UserType.FACTOR) {
			throw WebException.instance("只有资金方才有权限修改");
		} 
		String userId = SecurityContextUtils.getUserDTOFromSession().getId();
		ProjectDTO projectDTO = assetProjectManageApi.getProjectDetail(userId, projectId);
		model.addAttribute("projectDTO", projectDTO);
		logger.info("进入项目查询修改页面");
		return "page/asset/project/projectUpdate";
	}
	
	// 项目修改form页面
	@RequestMapping(value = "/projectUpdateForm",method ={ RequestMethod.POST })
	@ResponseBody
	public ProjectStatusResponseDTO projectUpdateForm(@RequestBody ProjectRequestDTO projectRequest) {
		try {
			String userId = SecurityContextUtils.getUserDTOFromSession().getId();
			//设置合同权限havePermissionModifyDocument
			projectRequest.setHavePermissionModifyDocument(true);
			ProjectStatusResponseDTO projectStatusResponseDTO = assetProjectManageApi.updateProject(userId,projectRequest);
			return projectStatusResponseDTO;
		} catch(WebException e){
			Map<ProjectErrorFieldEnum,String> errorInfos = new HashMap<>();
			errorInfos.put(ProjectErrorFieldEnum.error,e.getMessage());
			return new ProjectStatusResponseDTO(errorInfos);
		} catch (Exception e) {
			logger.info("项目修改异常");
			e.printStackTrace();
		}
		return null;
	}
	
}
