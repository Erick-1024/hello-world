package com.cana.vbam.front.biz.controller.user;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cana.member.api.IPermissionApi;
import com.cana.member.api.IRoleApi;
import com.cana.member.api.IUserApi;
import com.cana.member.authorization.common.SecurityContextUtils;
import com.cana.vbam.common.dto.ListResult;
import com.cana.vbam.common.enums.RoleType;
import com.cana.vbam.common.member.dto.permission.PermissionDTO;
import com.cana.vbam.common.member.dto.role.RoleAddDTO;
import com.cana.vbam.common.member.dto.role.RoleSearchCriterionDTO;
import com.cana.vbam.common.member.dto.role.RoleSearchDTO;
import com.cana.vbam.common.member.dto.role.RoleSearchResultDTO;
import com.cana.vbam.common.member.dto.role.RoleUpdateDTO;
import com.cana.vbam.common.member.dto.user.CustomerDetailDTO;
import com.cana.vbam.common.member.dto.user.RoleDTO;
import com.cana.vbam.common.member.dto.user.UserRoleListUpdateDTO;
import com.cana.vbam.common.member.dto.user.UserSessionDTO;
import com.cana.vbam.common.member.enums.user.UserType;
import com.cana.vbam.common.utils.TreeNode;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.travelzen.framework.core.exception.WebException;
import com.travelzen.framework.core.util.StringUtil;

/**
 * 角色管理页面的controller
 * @author dev3
 *
 */

@Controller
@RequestMapping("/role")
public class RoleController
{
	@Resource
	private IRoleApi roleApi;    //角色管理服务的Api
	
	@Resource
	private IUserApi userApi;    //用户管理的Api
	
	@Resource
	private IPermissionApi permissionApi;  //权限服务Api
	
	private static final Logger LGR = LoggerFactory.getLogger(RoleController.class);  

	private String error_prompt_msg = "新建角色失败";
//	private String success_prompt_msg = "新建角色成功";
	
	/**
	 * 跳转到角色分配页面
	 * @return 
	 */
	@RequestMapping(value="gotoRoleAssignment")
	public String gotoRoleAssignment(@RequestParam String userId,Model model)
	{
		if(UserType.CANA != SecurityContextUtils.getUserDTOFromSession().getUserType())
			throw WebException.instance("您没有权限访问该页面！");
		StringUtils.trim(userId);
		if(StringUtils.isBlank(userId))
		{
			throw WebException.instance("不存在该企业用户！");//错误页面
		}
		Map<String, String> roles = new HashMap<>();
		List<RoleSearchResultDTO> roleSearchResultDTOList = Lists.newArrayList();
		try
		{
			CustomerDetailDTO customerDetailDTO = userApi.queryCustomerDetail(userId);
			for(RoleDTO roleDTO:customerDetailDTO.getRoleDTOList()){
				RoleSearchResultDTO roleSearchResultDTO = new RoleSearchResultDTO();
				roleSearchResultDTO = getRoleById(roleDTO.getRoleId());
				roleSearchResultDTOList.add(roleSearchResultDTO);
			}
			roles = roleApi.queryRolesByRoleId(customerDetailDTO.getRoleDTOList());
			LGR.info("查询相同roleType的角色Map成功！");
		}
		catch(Exception e)
		{
			LGR.error("查询相同roleType的角色Map失败！",e);
			throw WebException.instance("跳转角色分配页面出错！");
		}
		model.addAttribute("userId",userId);
		model.addAttribute("originRoles",roleSearchResultDTOList);
		model.addAttribute("roles",roles);
		LGR.info("跳转到添加角色页面。");
		return "page/role/companyRoleAssignment";
	}
	
	/**
	 * 更新用户所分配的角色。
	 * @param updateDTO
	 * @return
	 */
	@RequestMapping("updateRoleOfUser")
	@ResponseBody
	public String updateRoleOfUser(@RequestBody UserRoleListUpdateDTO userUpdateDTO)
	{
		String SCCESS_UPDATE_USER = "角色分配成功";
		String ERROR_UPDATE_USER = "角色分配失败！";
		StringUtil.trimObjectFields(userUpdateDTO);
		if(StringUtils.isBlank(userUpdateDTO.getId()) || CollectionUtils.isEmpty(userUpdateDTO.getRoleIdList()))
			return ERROR_UPDATE_USER;
		try
		{
			userApi.updateRoleListOfUser(userUpdateDTO);
			LGR.info("角色分配成功！");
		} catch (Exception e)
		{
			LGR.error("角色分配失败！",e);
		}
		
		return SCCESS_UPDATE_USER;
	}
	/**
	 * 跳转到企业类型角色列表页面
	 * @return
	 */
	@RequestMapping("gotoCompanyRoleList")
	public String goToCompanyRoleList(Model model)
	{
		model.addAttribute("userTypes", UserType.nonIndividualUserTypes());
		return "page/role/companyRoleList";
	}
	/**
	 * 跳转到添加企业类型角色页面
	 * @return 
	 */
	@RequestMapping(value="gotoAddCompanyRole")
	public String goToAddCompnayRole(Model model)
	{
		UserSessionDTO userSessionDTO = SecurityContextUtils.getUserDTOFromSession();
		if(UserType.CANA != userSessionDTO.getUserType())
			throw WebException.instance("您没有权限访问该页面！");
		model.addAttribute("userTypes", UserType.nonIndividualUserTypes());
		LGR.info("跳转到添加企业类型角色页面。");
		return "page/role/addCompanyRole";
	}
	/**
	 * 跳转到企业类型角色详情页面
	 * @return 
	 */
	@RequestMapping(value="gotoCompanyRoleDetails")
	public String gotoCompanyRoleDetails(@RequestParam String roleId, Model model)
	{
		UserSessionDTO userSessionDTO = SecurityContextUtils.getUserDTOFromSession();
		if(UserType.CANA != userSessionDTO.getUserType())
			throw WebException.instance("您没有权限访问该页面！");
		RoleSearchResultDTO roleSearchResultDTO  = getRoleById(roleId);
		model.addAttribute("role",roleSearchResultDTO);
		model.addAttribute("userTypes", UserType.nonIndividualUserTypes());
		return "page/role/companyRoleDetails";
	}
	/**
	 * 跳转到编辑企业类型角色页面
	 * @return 
	 */
	@RequestMapping(value="gotoEditCompanyRole")
	public String goToEditCompnayRole(@RequestParam String roleId,Model model)
	{
		UserSessionDTO userSessionDTO = SecurityContextUtils.getUserDTOFromSession();
		if(UserType.CANA != userSessionDTO.getUserType())
			throw WebException.instance("您没有权限访问该页面！");
		RoleSearchResultDTO roleSearchResultDTO  = getRoleById(roleId);
		model.addAttribute("role",roleSearchResultDTO);
		model.addAttribute("userTypes", UserType.nonIndividualUserTypes());
		return "page/role/editCompanyRole";
	}
	
	/**
	 * 获取企业类型的角色list
	 * @param roleSearchCriterionDTO
	 * @return
	 */
	@RequestMapping("getCompanyRoleList")
	@ResponseBody
	public ListResult<RoleSearchResultDTO> getCompayRoleList(RoleSearchCriterionDTO roleSearchCriterionDTO)
	{
		ListResult<RoleSearchResultDTO> result = new ListResult<RoleSearchResultDTO>();
		StringUtil.trimObjectFields(roleSearchCriterionDTO);
		roleSearchCriterionDTO.setType(RoleType.LEVEL1.name());//设置为一级，查询为企业类型
		try
		{
			result = roleApi.queryRoleList(roleSearchCriterionDTO);
			LGR.info("获取企业角色列表成功！");
		} catch (Exception e)
		{
			LGR.error("获取企业角色列表成功！",e);
		}
		return result;
	}
	/**
	 * 检查企业类型角色名称是否合法。
	 * @param roleName 用户输入的角色名称
	 * @return true表示角色名已存在 false 角色名唯一
	 */
	@RequestMapping(value="/checkCompanyRoleName",method=RequestMethod.POST)
	@ResponseBody
	public boolean checkCompanyRoleNameResult(RoleSearchCriterionDTO roleSearchCriterionDTO)
	{
		if(checkCompanyRoleName(roleSearchCriterionDTO))
		{
			return false;
		}
		else
		{
			return true;
		}
	}	
	/**
	 * 新建企业类型角色时提交角色表单的controller
	 * @param roleAddDTO
	 * @return
	 */
	@RequestMapping("/addCompanyRole")
	@ResponseBody
	public String addCompanyRole(RoleAddDTO roleAddDTO)
	{	
		String roleId = "";
		StringUtil.trimObjectFields(roleAddDTO);
		if(StringUtils.isBlank(roleAddDTO.getRoleName()))
			return "新建角色名称不能为空";
		if(StringUtils.isBlank(roleAddDTO.getPermissions()))
			return "新建的角色权限不能为空";
		if(checkAddCompanyRoleRequest(roleAddDTO))
		{
			return error_prompt_msg;
		}
		//新建的角色为企业类型角色，roleType设为一级，masterId设为空
		roleAddDTO.setType(RoleType.LEVEL1.name());
		try
		{
			RoleSearchCriterionDTO roleSearchCriterionDTO = new RoleSearchCriterionDTO();
			BeanUtils.copyProperties(roleAddDTO, roleSearchCriterionDTO);
			if(checkCompanyRoleName(roleSearchCriterionDTO))
			{
				return error_prompt_msg;
			}
			else 
			{
				roleId = roleApi.addRole(roleAddDTO);	
				LGR.info("新建企业类型角色成功！");
			}
			
		} catch (Exception e)
		{
			// TODO: handle exception
			LGR.error("新建企业类型角色失败！",e);
		}
		if(!StringUtils.isBlank(roleId))
			return roleId;
		else
			return error_prompt_msg;
	}
	
	/**
	 * 更新角色信息
	 * @param roleUpdateDTO
	 * @return
	 */
	@RequestMapping("updateCompanyRole")
	@ResponseBody
	public String updateCompanyRole(RoleUpdateDTO roleUpdateDTO){	
		String error_prompt_msg = "编辑角色失败";
		String success_prompt_msg = "编辑角色成功";
		StringUtil.trimObjectFields(roleUpdateDTO);
		UserSessionDTO userSessionDTO = SecurityContextUtils.getUserDTOFromSession();
		roleUpdateDTO.setChangeRoleIdList(userSessionDTO.getRoleIdList());
		if(checkUpdateCompanyRoleRequest(roleUpdateDTO)){
			return error_prompt_msg;
		}
		try{
			RoleSearchCriterionDTO roleSearchCriterionDTO = new RoleSearchCriterionDTO();
			BeanUtils.copyProperties(roleUpdateDTO, roleSearchCriterionDTO);
			roleSearchCriterionDTO.setId(roleUpdateDTO.getRoleId());
			if(checkCompanyRoleName(roleSearchCriterionDTO)){
				return error_prompt_msg;
			}else{
				if(UserType.CANA == userSessionDTO.getUserType())
					roleApi.updateCompanyRoleByCana(roleUpdateDTO);
				else 
					roleApi.updateRole(roleUpdateDTO);
				LGR.info("编辑企业类型角色成功！");
			}
			
		} catch (Exception e){
			// TODO: handle exception
			LGR.error("编辑企业类型角色失败！",e);
		}
		return success_prompt_msg;
	}
	
	/**
	 * 以下是员工角色的所有 Controller
	 */
	
	/**
	 * 跳转到员工类型角色列表页面
	 * @return
	 */
	@RequestMapping("gotoEmployeeRoleList")
	public String goToEmployeeRoleList()
	{
		return "page/role/employeeRoleList";
	}
	
	/**
	 * 跳转到添加员工类型角色页面
	 * @return 
	 */
	@RequestMapping(value="gotoAddEmployeeRole")
	public String goToAddEmployeeRole()
	{
		LGR.info("跳转到添加企业类型角色页面。");
		UserSessionDTO userSessionDTO = SecurityContextUtils.getUserDTOFromSession();
		if(UserType.CANA != userSessionDTO.getUserType()){
			if(StringUtils.isNotBlank(userSessionDTO.getMasterId()))
				throw WebException.instance("您没有权限访问该页面！");
		}
		return "page/role/addEmployeeRole";
	}
	
	/**
	 * 跳转到编辑员工类型角色页面
	 * @return 
	 */
	@RequestMapping(value="gotoEditEmployeeRole")
	public String goToEditEmployeeRole(@RequestParam String roleId,Model model){
		RoleSearchResultDTO roleSearchResultDTO  = getRoleById(roleId);
		UserSessionDTO userSessionDTO = SecurityContextUtils.getUserDTOFromSession();
		if(UserType.CANA != userSessionDTO.getUserType()){
			if(!userSessionDTO.getId().equals(roleSearchResultDTO.getMasterId()))
				throw WebException.instance("您没有权限访问该页面！");
		}
		model.addAttribute("role",roleSearchResultDTO);
		return "page/role/editEmployeeRole";
	}
	
	/**
	 * 跳转到员工类型角色详情页面
	 * @return 
	 */
	@RequestMapping(value="gotoEmployeeRoleDetails")
	public String gotoEmployeeRoleDetails(@RequestParam String roleId, Model model){
		RoleSearchResultDTO roleSearchResultDTO  = getRoleById(roleId);
		UserSessionDTO userSessionDTO = SecurityContextUtils.getUserDTOFromSession();
		if(UserType.CANA != userSessionDTO.getUserType()){
			if(!userSessionDTO.getId().equals(roleSearchResultDTO.getMasterId()))
				throw WebException.instance("您没有权限访问该页面！");
		}
		model.addAttribute("role",roleSearchResultDTO);
		return "page/role/employeeRoleDetails";
	}
	
	/**
	 * 获取员工类型的角色list
	 * @param roleSearchCriterionDTO
	 * @param model
	 * @return
	 */
	@RequestMapping("getEmployeeRoleList")
	@ResponseBody
	public ListResult<RoleSearchResultDTO> getEmployeeRoleList(RoleSearchCriterionDTO roleSearchCriterionDTO){
		ListResult<RoleSearchResultDTO> result = new ListResult<>();
		StringUtil.trimObjectFields(roleSearchCriterionDTO);
		roleSearchCriterionDTO.setType(RoleType.LEVEL2.name());//设置为二级，查询为员工类型
		UserSessionDTO userSessionDTO = SecurityContextUtils.getUserDTOFromSession();
		//设置查询的员工的所属的masterId
		roleSearchCriterionDTO.setMasterId(StringUtils.isBlank(userSessionDTO.getMasterId())?userSessionDTO.getId():userSessionDTO.getMasterId());
		try{
			result = roleApi.queryRoleList(roleSearchCriterionDTO);
			LGR.info("获取员工角色列表成功！");
		} catch (Exception e){
			LGR.error("获取员工角色列表成功！",e);
		}
		return result;
	}	
	/**
	 * 检查角色名称是否合法。
	 * @param roleName 用户输入的角色名称
	 * @return true表示角色名已存在 false 角色名唯一
	 */
	@RequestMapping(value="checkEmployeeRoleName")
	@ResponseBody
	public boolean checkEmployeeRoleNameResult(RoleSearchCriterionDTO roleSearchCriterionDTO)
	{
		if(checkEmployeeRoleName(roleSearchCriterionDTO))
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	
	/**
	 * 新建员工类型角色时提交角色表单的controller
	 * @param roleAddDTO
	 * @param roleName
	 * @param permissions
	 * @return
	 */
	@RequestMapping("/addEmployeeRole")
	@ResponseBody
	public String addEmployeeRole(RoleAddDTO roleAddDTO)
	{	
		String roleId = "";
		UserSessionDTO userSessionDTO = SecurityContextUtils.getUserDTOFromSession();
		
		StringUtil.trimObjectFields(roleAddDTO);
		if(StringUtils.isBlank(roleAddDTO.getPermissions()))
			return "新建的角色权限不能为空";
		//新建的角色为员工类型角色，roleType设为二级，masterId设为userId或者userMasterId
		if(checkAddEmployeeRoleRequest(roleAddDTO))
		{
			return error_prompt_msg;
		}
		roleAddDTO.setType(RoleType.LEVEL2.name());//roleType设为二级
		roleAddDTO.setMasterId(StringUtils.isBlank(userSessionDTO.getMasterId())?userSessionDTO.getId():userSessionDTO.getMasterId());
		try
		{
			RoleSearchCriterionDTO roleSearchCriterionDTO = new RoleSearchCriterionDTO();
			BeanUtils.copyProperties(roleAddDTO, roleSearchCriterionDTO);
			if(checkEmployeeRoleName(roleSearchCriterionDTO))
			{
				return error_prompt_msg;
			}
			else 
			{
				roleId = roleApi.addRole(roleAddDTO);
				LGR.info("新建员工角色成功！");
			}
		} catch (Exception e)
		{
			// TODO: handle exception
			LGR.error("新建员工角色失败！",e);
		}
		if(!StringUtils.isBlank(roleId))
			return roleId;
		else
			return error_prompt_msg;
	}
	
	/**
	 * 更新员工类型角色信息
	 * @param roleUpdateDTO
	 * @return
	 */
	@RequestMapping("updateEmployeeRole")
	@ResponseBody
	public String updateEmployeeRole(RoleUpdateDTO roleUpdateDTO){	
		String error_prompt_msg = "编辑角色失败";
		String success_prompt_msg = "编辑角色成功";
		
		UserSessionDTO userSessionDTO = SecurityContextUtils.getUserDTOFromSession();
		roleUpdateDTO.setChangeRoleIdList(userSessionDTO.getRoleIdList());
		
		if(checkUpdateEmployeeRoleRequest(roleUpdateDTO)){
			return error_prompt_msg;
		}
		try{
			RoleSearchCriterionDTO roleSearchCriterionDTO = new RoleSearchCriterionDTO();
			BeanUtils.copyProperties(roleUpdateDTO, roleSearchCriterionDTO);
			roleSearchCriterionDTO.setId(roleUpdateDTO.getRoleId());
			if(checkEmployeeRoleName(roleSearchCriterionDTO)){
				return error_prompt_msg;
			}else{
				roleApi.updateRole(roleUpdateDTO);
				LGR.info("编辑企业类型角色成功！");
			}
			
		} catch (Exception e){
			// TODO: handle exception
			LGR.error("编辑企业类型角色失败！",e);
		}
		return success_prompt_msg;
	}
	
	
	/**
	 * 获取相应角色的的权限树
	 * @return
	 */
	@RequestMapping(value="/getPermissionsTree")
	@ResponseBody
	public String getPermissionsTree(){
		UserSessionDTO userSessionDTO = SecurityContextUtils.getUserDTOFromSession();
		StringBuilder json = new StringBuilder("[");
		try{
			List<PermissionDTO> permissionDTOList = new ArrayList<PermissionDTO>();
			Set<PermissionDTO> permissionSet = Sets.newHashSet();
			for(String userRoleId:userSessionDTO.getRoleIdList()){
				permissionSet.addAll(permissionApi.getPermissionByRole(userRoleId));
			}
			permissionDTOList.addAll(permissionSet) ;
			LGR.info("获取相应roleId的所有权限list成功!");
			List<TreeNode<PermissionDTO>> permissionsTree = buildPermissionTree(permissionDTOList);
			if(!CollectionUtils.isEmpty(permissionsTree)){
				LGR.info("权限树生成成功!");
				for(TreeNode<PermissionDTO> node : permissionsTree){
					//json.append(getJson(node));
					json.append(getJsonTree(node));
					json.append(",");
				}
				json.deleteCharAt(json.length() - 1);
			}else{
				LGR.error("权限树生成失败！");
			}
			json.append("]");
			
		} catch (Exception e){
			// TODO: handle exception
			LGR.error("获取权限树异常！",e);
		}
		return json.toString();
	}
	
	/**
	 * 获取相应角色的的权限树
	 * @return
	 */
	@RequestMapping(value="/getPermissionsTreeForCompany")
	@ResponseBody
	public String getPermissionsTreeForCompany()
	{
		UserSessionDTO userSessionDTO = SecurityContextUtils.getUserDTOFromSession();
		StringBuilder json = new StringBuilder("[");
		try
		{
			List<PermissionDTO> permissionDTOList = new ArrayList<PermissionDTO>();
			if(UserType.CANA == userSessionDTO.getUserType())
			{
				permissionDTOList = permissionApi.getAllPermissions();
			}
			else{
				Set<PermissionDTO> permissionSet = Sets.newHashSet();
				for(String userRoleId:userSessionDTO.getRoleIdList()){
					permissionSet.addAll(permissionApi.getPermissionByRole(userRoleId));
				}
				permissionDTOList.addAll(permissionSet) ;
			}
			LGR.info("获取相应roleId的所有权限list成功!");
			List<TreeNode<PermissionDTO>> permissionsTree = buildPermissionTree(permissionDTOList);
			if(!CollectionUtils.isEmpty(permissionsTree))
			{
				LGR.info("权限树生成成功!");
				for(TreeNode<PermissionDTO> node : permissionsTree)
				{
					//json.append(getJson(node));
					json.append(getJsonTree(node));
					json.append(",");
				}
				json.deleteCharAt(json.length() - 1);
			}
			else
			{
				LGR.error("权限树生成失败！");
			}
			json.append("]");
			
		} catch (Exception e)
		{
			// TODO: handle exception
			LGR.error("获取权限树异常！",e);
		}
		return json.toString();
	}
	
	/**
	 * 编辑角色时
	 * 获取相应角色的的权限树
	 * @return
	 */
	@RequestMapping(value="/getEditPermissionsTreeForCompany")
	@ResponseBody
	public String getEditPermissionsTreeForCompany(String roleId)
	{
		UserSessionDTO userSessionDTO = SecurityContextUtils.getUserDTOFromSession();
		StringBuilder json = new StringBuilder("[");
		try
		{
			List<PermissionDTO> permissionDTOList = new ArrayList<PermissionDTO>();
			if(UserType.CANA == userSessionDTO.getUserType()){
				permissionDTOList = permissionApi.getAllPermissions();
			}
			else{
				Set<PermissionDTO> permissionSet = Sets.newHashSet();
				for(String userRoleId:userSessionDTO.getRoleIdList()){
					permissionSet.addAll(permissionApi.getPermissionByRole(userRoleId));
				}
				permissionDTOList.addAll(permissionSet) ;
			}
			//角色原有权限list
			List<PermissionDTO> originPermissionList = permissionApi.getPermissionByRole(roleId);
			LGR.info("获取相应roleId的所有权限list成功!");
			List<TreeNode<PermissionDTO>> permissionsTree = buildPermissionTree(permissionDTOList);
			if(!CollectionUtils.isEmpty(permissionsTree))
			{
				LGR.info("权限树生成成功!");
				for(TreeNode<PermissionDTO> node : permissionsTree)
				{
					//json.append(getJson(node));
					json.append(getEditJsonTree(node,originPermissionList));
					json.append(",");
				}
				json.deleteCharAt(json.length() - 1);
			}
			else
			{
				LGR.error("权限树生成失败！");
			}
			json.append("]");
			
		} catch (Exception e)
		{
			// TODO: handle exception
			LGR.error("获取权限树异常！",e);
		}
		return json.toString();
	}

	/**
	 * 编辑角色时
	 * 获取相应角色的的权限树
	 * @return
	 */
	@RequestMapping(value="/getEditPermissionsTree")
	@ResponseBody
	public String getEditPermissionsTree(String roleId)
	{
		UserSessionDTO userSessionDTO = SecurityContextUtils.getUserDTOFromSession();
//		String permissions = getRoleById(roleId).getPermissions();
		StringBuilder json = new StringBuilder("[");
		try
		{
			List<PermissionDTO> permissionDTOList = Lists.newArrayList();
			Set<PermissionDTO> permissionSet = Sets.newHashSet();
			for(String userRoleId:userSessionDTO.getRoleIdList()){
				permissionSet.addAll(permissionApi.getPermissionByRole(userRoleId));
			}
			permissionDTOList.addAll(permissionSet) ;
			//角色原有权限list
			List<PermissionDTO> originPermissionList = permissionApi.getPermissionByRole(roleId);
			LGR.info("获取相应roleId的所有权限list成功!");
			List<TreeNode<PermissionDTO>> permissionsTree = buildPermissionTree(permissionDTOList);
			if(!CollectionUtils.isEmpty(permissionsTree))
			{
				LGR.info("权限树生成成功!");
				for(TreeNode<PermissionDTO> node : permissionsTree)
				{
					//json.append(getJson(node));
					json.append(getEditJsonTree(node,originPermissionList));
					json.append(",");
				}
				json.deleteCharAt(json.length() - 1);
			}
			else
			{
				LGR.error("权限树生成失败！");
			}
			json.append("]");
			
		} catch (Exception e)
		{
			// TODO: handle exception
			LGR.error("获取权限树异常！",e);
		}
		return json.toString();
	}
	/**
	 * 查询角色详情时
	 * 获取相应角色的的权限树
	 * @return
	 */
	@RequestMapping(value="getDetailsPermissionsTree")
	@ResponseBody
	public String getDetailsPermissionsTree(@RequestBody RoleSearchDTO roleSearchDTO){
//		UserSessionDTO userSessionDTO = SecurityContextUtils.getUserDTOFromSession();
		StringBuilder json = new StringBuilder("[");
		try{
//			List<PermissionDTO> permissionDTOList = permissionApi.getPermissionByRole(userSessionDTO.getRoleId());
			//角色原有权限list
			List<PermissionDTO> originPermissionList = permissionApi.getPermissionByRoleList(roleSearchDTO.getRoleIdList());
			LGR.info("获取相应roleId的所有权限list成功!");
			List<TreeNode<PermissionDTO>> permissionsTree = buildPermissionTree(originPermissionList);
			if(!CollectionUtils.isEmpty(permissionsTree)){
				LGR.info("权限树生成成功!");
				for(TreeNode<PermissionDTO> node : permissionsTree){
					//json.append(getJson(node));
					json.append(getDetailsJsonTree(node,originPermissionList));
					json.append(",");
				}
				json.deleteCharAt(json.length() - 1);
			}else{
				LGR.error("权限树生成失败！");
			}
			json.append("]");
		} catch (Exception e){
			// TODO: handle exception
			LGR.error("获取权限树异常！",e);
		}
		return json.toString();
	}

	/**
	 * 员工账户激活成功时
	 * 获取相应角色的的权限树
	 * @return
	 */
	@RequestMapping(value="/facade/getActivateSuccessPermissionsTree")
	@ResponseBody
	public String getActivateSuccessPermissionsTree(String roleId)
	{

		StringBuilder json = new StringBuilder("[");
		try
		{
			//角色原有权限list
			List<PermissionDTO> originPermissionList = permissionApi.getPermissionByRole(roleId);
			LGR.info("获取相应roleId的所有权限list成功!");
			List<TreeNode<PermissionDTO>> permissionsTree = buildPermissionTree(originPermissionList);
			if(!CollectionUtils.isEmpty(permissionsTree))
			{
				LGR.info("权限树生成成功!");
				for(TreeNode<PermissionDTO> node : permissionsTree)
				{
					//json.append(getJson(node));
					json.append(getDetailsJsonTree(node,originPermissionList));
					json.append(",");
				}
				json.deleteCharAt(json.length() - 1);
			}
			else
			{
				LGR.error("权限树生成失败！");
			}
			json.append("]");
			
		} catch (Exception e)
		{
			// TODO: handle exception
			LGR.error("获取权限树异常！",e);
		}
		return json.toString();
	}
	
	/**
	 * 编辑企业个性权限时
	 * 获取相应企业的的权限树
	 * @return
	 */
	@RequestMapping(value="/customer/getEditPermissionsTree")
	@ResponseBody
	public String getEditPermissionsTreeForCompanyUser(@RequestParam String userId)
	{
		UserSessionDTO userSessionDTO = SecurityContextUtils.getUserDTOFromSession();
		StringBuilder json = new StringBuilder("[");
		try
		{
			List<PermissionDTO> permissionDTOList = new ArrayList<PermissionDTO>();
			if(UserType.CANA == userSessionDTO.getUserType()){
				permissionDTOList = permissionApi.getAllPermissions();
			}
			else{
				Set<PermissionDTO> permissionSet = Sets.newHashSet();
				for(String userRoleId:userSessionDTO.getRoleIdList()){
					permissionSet.addAll(permissionApi.getPermissionByRole(userRoleId));
				}
				permissionDTOList.addAll(permissionSet) ;
			}
			//用户原有权限list
			List<PermissionDTO> originPermissionList = permissionApi.getPermissionByUserId(userId);
			
			LGR.info("获取相应roleId的所有权限list成功!");
			List<TreeNode<PermissionDTO>> permissionsTree = buildPermissionTree(permissionDTOList);
			if(!CollectionUtils.isEmpty(permissionsTree))
			{
				LGR.info("权限树生成成功!");
				for(TreeNode<PermissionDTO> node : permissionsTree)
				{
					//json.append(getJson(node));
					json.append(getEditJsonTree(node,originPermissionList));
					json.append(",");
				}
				json.deleteCharAt(json.length() - 1);
			}
			else
			{
				LGR.error("权限树生成失败！");
			}
			json.append("]");
			
		} catch (Exception e)
		{
			// TODO: handle exception
			LGR.error("获取权限树异常！",e);
		}
		return json.toString();
	}

	/**
	 * 编辑员工个性权限时
	 * 获取相应员工的的权限树
	 * @return
	 */
	@RequestMapping(value="/employee/getEditPermissionsTree")
	@ResponseBody
	public String getEditPermissionsTreeForEmployee(@RequestParam String userId)
	{
		UserSessionDTO userSessionDTO = SecurityContextUtils.getUserDTOFromSession();
//		String permissions = getRoleById(roleId).getPermissions();
		StringBuilder json = new StringBuilder("[");
		try
		{
			List<PermissionDTO> permissionDTOList = Lists.newArrayList();
			Set<PermissionDTO> permissionSet = Sets.newHashSet();
			for(String userRoleId:userSessionDTO.getRoleIdList()){
				permissionSet.addAll(permissionApi.getPermissionByRole(userRoleId));
			}
			permissionDTOList.addAll(permissionSet) ;
			//员工原有权限list
			List<PermissionDTO> originPermissionList = permissionApi.getPermissionByUserId(userId);
			LGR.info("获取相应roleId的所有权限list成功!");
			List<TreeNode<PermissionDTO>> permissionsTree = buildPermissionTree(permissionDTOList);
			if(!CollectionUtils.isEmpty(permissionsTree))
			{
				LGR.info("权限树生成成功!");
				for(TreeNode<PermissionDTO> node : permissionsTree)
				{
					//json.append(getJson(node));
					json.append(getEditJsonTree(node,originPermissionList));
					json.append(",");
				}
				json.deleteCharAt(json.length() - 1);
			}
			else
			{
				LGR.error("权限树生成失败！");
			}
			json.append("]");
			
		} catch (Exception e)
		{
			// TODO: handle exception
			LGR.error("获取权限树异常！",e);
		}
		return json.toString();
	}
	
	/**
	 *以下是Controller里的私有方法。
	 * @throws Exception 
	 */
	/**
	 * 判断新建角色提交的信息
	 * @param roleAddDTO
	 * @return
	 */
	private boolean checkAddCompanyRoleRequest(RoleAddDTO roleAddDTO)
	{
		if(StringUtils.isBlank(roleAddDTO.getRoleName()) || StringUtils.isBlank(roleAddDTO.getPermissions()) || roleAddDTO.getUserType() == null)
			return true;
		return false;
	}
	
	private boolean checkUpdateCompanyRoleRequest(RoleUpdateDTO roleUpdateDTO)
	{
		if(StringUtils.isBlank(roleUpdateDTO.getRoleId()) ||StringUtils.isBlank(roleUpdateDTO.getRoleName()) || StringUtils.isBlank(roleUpdateDTO.getPermissions()) || roleUpdateDTO.getUserType() == null)
		{
			return true;
		}
		return false;
	}
	
	private boolean checkAddEmployeeRoleRequest(RoleAddDTO roleAddDTO)
	{
		if(StringUtils.isBlank(roleAddDTO.getRoleName()) || StringUtils.isBlank(roleAddDTO.getPermissions()))
			return true;
		return false;
	}
	
	private boolean checkUpdateEmployeeRoleRequest(RoleUpdateDTO roleUpdateDTO)
	{
		if(StringUtils.isBlank(roleUpdateDTO.getRoleId()) ||StringUtils.isBlank(roleUpdateDTO.getRoleName()) || StringUtils.isBlank(roleUpdateDTO.getPermissions()))
		{
			return true;
		}
		return false;
	}

	/**
	 * 检查企业类型的角色名称
	 * @param roleName
	 * @return
	 */
	private boolean checkCompanyRoleName(RoleSearchCriterionDTO roleSearchCriterionDTO)
	{
		StringUtil.trimObjectFields(roleSearchCriterionDTO);
		boolean roleNameExist = true;
		if(StringUtils.isBlank(roleSearchCriterionDTO.getRoleName()) || roleSearchCriterionDTO.getUserType() == null)
		{
			return roleNameExist;//如果用户输入的角色名为空或者角色类型为空，直接返回true；
		}
		//判断roleId是否为空，为空时属于添加角色的情况
		if(StringUtils.isNotBlank(roleSearchCriterionDTO.getId()))
		{
			//如果roleId不为空，则为编辑角色时的情况；如果编辑后的角色名和角色类型与数据库中的相同，则直接返回false；
			RoleSearchResultDTO roleSearchResultDTO = getRoleById(roleSearchCriterionDTO.getId());
			if(roleSearchResultDTO.getRoleName().equals(roleSearchCriterionDTO.getRoleName()) && roleSearchResultDTO.getRoleType().equals(roleSearchCriterionDTO.getUserType().name()))
				return false;
		}
		try
		{
			roleNameExist = roleApi.checkCompanyRoleNameExist(roleSearchCriterionDTO);
			LGR.info("检查企业角色名称唯一性成功！");
		} catch (Exception e)
		{
			// TODO: handle exception
			LGR.error("检查角色名称唯一性异常！",e);
		}
		return roleNameExist;
	}
	
	/**
	 * 检查企业类型的角色名称
	 * @param roleName
	 * @return
	 */
	private boolean checkEmployeeRoleName(RoleSearchCriterionDTO roleSearchCriterionDTO)
	{
		StringUtil.trimObjectFields(roleSearchCriterionDTO);
		boolean roleNameExist = true;
		UserSessionDTO userSessionDTO = SecurityContextUtils.getUserDTOFromSession();
		
		if(StringUtils.isBlank(roleSearchCriterionDTO.getRoleName()))
		{
			return roleNameExist;//如果用户输入的角色名为空，直接返回true；
		}
		//判断roleId是否为空，为空时属于添加角色的情况
		if(StringUtils.isNotBlank(roleSearchCriterionDTO.getId()))
		{
			//如果roleId不为空，则为编辑角色时的情况；如果编辑后的角色名和角色类型与数据库中的相同，则直接返回false；
			RoleSearchResultDTO roleSearchResultDTO = getRoleById(roleSearchCriterionDTO.getId());
			if(roleSearchResultDTO.getRoleName().equals(roleSearchCriterionDTO.getRoleName()))
				return false;
		}
		roleSearchCriterionDTO.setMasterId(StringUtils.isBlank(userSessionDTO.getMasterId())?userSessionDTO.getId():userSessionDTO.getMasterId());
		try
		{
			roleNameExist = roleApi.checkEmployeeRoleNameExist(roleSearchCriterionDTO);
			LGR.info("检查员工角色名称唯一性成功！");
		}
		catch (WebException webException)
		{
			throw webException;
		}
		catch (Exception e)
		{
			// TODO: handle exception
			LGR.error("检查员工角色名称唯一性异常！",e);
		}
		return roleNameExist;
	}
	/**
	 * 生成权限树。
	 * @param permissionDTOList
	 * @return
	 * @throws Exception
	 */
	private  List<TreeNode<PermissionDTO>> buildPermissionTree(List<PermissionDTO> permissionDTOList) throws Exception
	{
		if(permissionDTOList == null)
		{
			return null;
		}
		List<TreeNode<PermissionDTO>> permissionsTree = new LinkedList<>();
		
		Set<PermissionDTO> permissionsList = new TreeSet<PermissionDTO>();
//		Map<String, TreeNode<PermissionDTO>> permissionDTOMap  = new HashMap<>();//所有权限的map对象
		
		List<PermissionDTO> allPermissions = permissionApi.getAllPermissions();
		//遍历list中所有权限将其父权限加入list中
		for(PermissionDTO permissonDTo : permissionDTOList)
		{
			Set<PermissionDTO> parentsList = new TreeSet<PermissionDTO>();//父权限list
			setParentList(permissonDTo, parentsList,permissionDTOList, allPermissions);//获取其所有父权限
			parentsList.add(permissonDTo);
			permissionsList.addAll(parentsList);

		}
		
//		Collections.sort(permissionsList);//根据ord对list进行排序。
		// HashMap.values()不会按照存入顺序取出数据，需使用LinkedHashMap
		Map<String, TreeNode<PermissionDTO>> permissionsMap  = new LinkedHashMap<>();
		for(PermissionDTO permissonDTO : permissionsList)
		{
			//遍历将 权限id 和 权限对象 加入map中
			permissionsMap.put(permissonDTO.getId(), new TreeNode<PermissionDTO>(permissonDTO.getName(),permissonDTO));//

		}
		
		//从map中取出权限对象进行遍历
		for(TreeNode<PermissionDTO> node : permissionsMap.values())
		{
			//System.out.println(node.getData().getOrd());
			if(StringUtils.isEmpty(node.getData().getParentId()))//判断parentId父级权限是否为空，为空的话作为根结点加入权限树。
			{
				permissionsTree.add(node);
			}
			else
			{
				//根据node的parentId对比map中保存的id,获取其的父权限对象
				TreeNode<PermissionDTO> parent = permissionsMap.get(node.getData().getParentId());
				parent.addChild(node);
				node.setParent(parent);
			}
		}
		return permissionsTree;//返回构建好的权限树。
	}
	
	
	/**
	 * 递归获取 父权限List
	 * @param child
	 * @param parentList
	 * @throws Exception
	 */
	private void setParentList(PermissionDTO child,Set<PermissionDTO> parentList,List<PermissionDTO> permissionDTOList, List<PermissionDTO> allPermissions)throws Exception
	{
		if(!StringUtils.isEmpty(child.getParentId()))
		{	PermissionDTO perTmp = new PermissionDTO();
			perTmp.setId(child.getParentId());
			int index = allPermissions.indexOf(perTmp);
//			PermissionDTO parent = permissionApi.getPermissionById(child.getParentId());
			if(index < 0){
				return;
			}
			PermissionDTO parent = allPermissions.get(index);
			if(!permissionDTOList.contains(parent))
			{
				parent.setGranted(false);
				parentList.add(parent);
			}
			setParentList(parent, parentList,permissionDTOList, allPermissions);
		}
	}
	
	/**
	 * 编辑角色时
	 * 生成一个节点的json串
	 * @param node
	 * @return
	 */
	private String getEditJsonTree(TreeNode<PermissionDTO> node,List<PermissionDTO> originPermissionList)
	{
		StringBuilder json = new StringBuilder("{");
		//json加入 权限 id，name,parentId,avaliable 。
		json.append(String.format("\"id\":\"%s\", \"name\":\"%s\", \"chkDisabled\":\"%s\"",node.getData().getId(),node.getData().getName(),!(node.getData().isGranted())));
		json.append(",\"open\":\"true\"");
		if(originPermissionList.contains(node.getData()))
		{
			json.append(",\"checked\":\"true\"");
		}
		//如果子节点不为空,json加入子节点
		if(!CollectionUtils.isEmpty(node.getChildren()))
		{
			json.append(", \"children\":[");
			for(TreeNode<PermissionDTO> child : node.getChildren() )
			{
				json.append(getEditJsonTree(child, originPermissionList));//递归加入子节点
				json.append(",");
			}
			json.deleteCharAt(json.length() - 1);//删除最后一个逗号。
			json.append("]");
		}
		return json.append("}").toString();
	}
	
	/**
	 * 编辑角色时
	 * 生成一个节点的json串
	 * @param node
	 * @return
	 */
	private String getDetailsJsonTree(TreeNode<PermissionDTO> node,List<PermissionDTO> originPermissionList)
	{
		StringBuilder json = new StringBuilder("{");
		//json加入 权限 id，name,parentId,avaliable 。
		json.append(String.format("\"id\":\"%s\", \"name\":\"%s\", \"chkDisabled\":\"%s\"",node.getData().getId(),node.getData().getName(),true));
		json.append(",\"open\":\"true\"");
		if(originPermissionList.contains(node.getData()))
		{
			json.append(",\"checked\":\"true\"");
		}
		//如果子节点不为空,json加入子节点
		if(!CollectionUtils.isEmpty(node.getChildren()))
		{
			json.append(", \"children\":[");
			for(TreeNode<PermissionDTO> child : node.getChildren() )
			{
				json.append(getDetailsJsonTree(child, originPermissionList));//递归加入子节点
				json.append(",");
			}
			json.deleteCharAt(json.length() - 1);//删除最后一个逗号。
			json.append("]");
		}
		return json.append("}").toString();
	}
	/**
	 * 生成一个节点的json串
	 * @param node
	 * @return
	 */
	private String getJsonTree(TreeNode<PermissionDTO> node)
	{
		StringBuilder json = new StringBuilder("{");
		//json加入 权限 id，name,parentId,avaliable 。
		json.append(String.format("\"id\":\"%s\", \"name\":\"%s\", \"chkDisabled\":\"%s\"",node.getData().getId(),node.getData().getName(),!(node.getData().isGranted())));
		json.append(",\"open\":\"false\"");
		//如果子节点不为空,json加入子节点
		if(!CollectionUtils.isEmpty(node.getChildren()))
		{
			json.append(", \"children\":[");
			for(TreeNode<PermissionDTO> child : node.getChildren() )
			{
				json.append(getJsonTree(child));//递归加入子节点
				json.append(",");
			}
			json.deleteCharAt(json.length() - 1);//删除最后一个逗号。
			json.append("]");
		}
		return json.append("}").toString();
	}
	
	/**
	 * /**
	 * 根据角色Id获取相应角色
	 * @param roleId
	 * @return roleSearchResultDTO
	 */
	private RoleSearchResultDTO getRoleById(String roleId)
	{
		RoleSearchResultDTO roleSearchResultDTO  = new RoleSearchResultDTO();
		StringUtil.trim(roleId);
		if(StringUtils.isBlank(roleId))
		{
			throw WebException.instance("角色Id不能为空！");
		}
		try
		{		
			roleSearchResultDTO = roleApi.getRoleById(roleId);
			LGR.info("获取角色信息成功！");
		} catch (Exception e)
		{
			// TODO: handle exception
			LGR.error("获取角色信息失败！",e);
			throw WebException.instance("获取角色信息失败！");
		}
		return roleSearchResultDTO;
	}
}
