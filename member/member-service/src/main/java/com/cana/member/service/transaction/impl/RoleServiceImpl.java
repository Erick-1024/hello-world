/**
 * 
 */
package com.cana.member.service.transaction.impl;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.cana.member.dao.mapper.MemberTableLockMapper;
import com.cana.member.dao.mapper.gen.PermissionMapper;
import com.cana.member.dao.mapper.gen.RoleMapper;
import com.cana.member.dao.mapper.gen.UserMapper;
import com.cana.member.dao.po.Permission;
import com.cana.member.dao.po.PermissionExample;
import com.cana.member.dao.po.Role;
import com.cana.member.dao.po.RoleExample;
import com.cana.member.dao.po.RoleExample.Criteria;
import com.cana.member.dao.po.User;
import com.cana.member.service.transaction.IPermissionTransactionService;
import com.cana.member.service.transaction.IRoleService;
import com.cana.vbam.common.dto.ListResult;
import com.cana.vbam.common.enums.RoleType;
import com.cana.vbam.common.member.dto.permission.PermissionDTO;
import com.cana.vbam.common.member.dto.role.RoleAddDTO;
import com.cana.vbam.common.member.dto.role.RoleSearchCriterionDTO;
import com.cana.vbam.common.member.dto.role.RoleSearchResultDTO;
import com.cana.vbam.common.member.dto.role.RoleUpdateDTO;
import com.cana.vbam.common.member.dto.user.RoleDTO;
import com.cana.vbam.common.member.enums.user.UserType;
import com.cana.vbam.common.utils.Constants;
import com.dianping.cat.Cat;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.travelzen.framework.core.exception.WebException;
import com.travelzen.framework.core.time.DateTimeUtil;
import com.travelzen.framework.core.util.StringUtil;
import com.travelzen.framework.dao.rdbms.SequenceGenerator;

/**
 * @author dev3
 *
 */
@Service
public class RoleServiceImpl implements IRoleService
{
	@Resource
	private RoleMapper roleMapper;
	
	@Resource
	private SequenceGenerator seqGen;
	
	@Resource
	private MemberTableLockMapper tablelockMapper;
	
	@Resource
	private UserMapper userMapper;
	
	@Resource
	private IPermissionTransactionService permissionTransactionService;
	
	@Resource
	private PermissionMapper permissionMapper;
	
	private List<Role> searchRoles(RoleExample roleExample)
	{
		List<Role> roleList = roleMapper.selectByExampleWithBLOBs(roleExample);
		return roleList;
		
	}
	
	private String generateRoleId() throws Exception {
		return DateTimeUtil.datetime14() + seqGen.getNextSeq(Constants.SEQUENCE_NAME_ROLE_ID, 4);
	}
	
	private List<PermissionDTO> getPermissionByRole(String roleId) throws Exception{
		List<Permission> permissionList = permissionTransactionService.getPermissionByRole(roleId);
		List<PermissionDTO> permissionDTOList = convert2PermissionDTO(permissionList);
		return permissionDTOList;
	}
	private List<PermissionDTO> getAllPermissions() throws Exception
	{
		List<Permission> permissions = permissionMapper.selectByExample(new PermissionExample());
		List<PermissionDTO> permissionDTOList = convert2PermissionDTO(permissions);	
		return permissionDTOList;
	}
	/**
	 * DTO转化
	 * @param permissionList
	 * @return
	 */
	private List<PermissionDTO> convert2PermissionDTO(List<Permission> permissionList){
		List<PermissionDTO> permissionDTOList = new ArrayList<PermissionDTO>();
		if(!CollectionUtils.isEmpty(permissionList)){
			for(Permission permission : permissionList){
				PermissionDTO permissionDTO = new PermissionDTO();
				BeanUtils.copyProperties(permission, permissionDTO);
				permissionDTOList.add(permissionDTO);
			}
		}
		return permissionDTOList;
	}
	/**
	 * 转换查询角色id和name的条件
	 * @param currentOperatorId
	 * @param roleType
	 * @param userType
	 * @return
	 */
	private RoleExample convertQueryRolesIdAndNameExample(String currentOperatorId, RoleType roleType, UserType userType) {
		RoleExample roleExample = new RoleExample();
		if(null != currentOperatorId){
			User currentOperator = userMapper.selectByPrimaryKey(currentOperatorId);
			String masterId;
			if(null == currentOperator.getMasterId()){
				masterId = currentOperatorId;
			} else {
				masterId = currentOperator.getMasterId();
			}
			User masterUser = userMapper.selectByPrimaryKey(masterId);
			if(RoleType.LEVEL1 == roleType && UserType.CANA == UserType.valueOf(masterUser.getUserType())){
				roleExample.createCriteria().andTypeEqualTo(roleType.name()).andRoleNameNotEqualTo("cana").andRoleTypeEqualTo(userType.name());
			} else {
				roleExample.createCriteria().andMasterIdEqualTo(masterId).andTypeEqualTo(roleType.name());
			}
		} else {
			throw WebException.instance("当前操作用户不存在");
		}
		return roleExample;
	}
	
	/**
	 * 转换查询到的角色结果
	 * @param roles
	 * @return
	 */
	private Map<String, String> convertQueryRolesIdAndNameResult(List<Role> roles) {
		Map<String, String> roleNames = new HashMap<String, String>(roles.size());
		for (int i = 0; i < roles.size(); i++) {
			roleNames.put(roles.get(i).getId(), roles.get(i).getRoleName());
		}
		return roleNames;
	}
	
	private void setCriteria(Criteria criteria,RoleSearchCriterionDTO roleSearchCriterionDTO) throws ParseException
	{
		StringUtil.trimObjectFields(roleSearchCriterionDTO);
		//加入查询条件：角色名，起始时间，终止时间，和查询的角色类型，企业类型为LEVEL1，员工类型为LEVEL2
		if(!StringUtils.isBlank(roleSearchCriterionDTO.getRoleName()))
			criteria.andRoleNameLike("%"+roleSearchCriterionDTO.getRoleName()+"%");
		if(!StringUtils.isBlank(roleSearchCriterionDTO.getBeginTime()))
			criteria.andCreatetimeGreaterThanOrEqualTo(new SimpleDateFormat("yyyy-MM-dd").parse(roleSearchCriterionDTO.getBeginTime()));
		if(!StringUtils.isBlank(roleSearchCriterionDTO.getEndTime()))
		{
			Date dateFormat  = new SimpleDateFormat("yyyy-MM-dd").parse(roleSearchCriterionDTO.getEndTime());
			criteria.andCreatetimeLessThan(new DateTime(dateFormat).plusDays(1).toDate());//终止时间加一天
		}
		if(!StringUtils.isBlank(roleSearchCriterionDTO.getType()))
			criteria.andTypeEqualTo(roleSearchCriterionDTO.getType());
		if(roleSearchCriterionDTO.getUserType() != null)
			criteria.andRoleTypeEqualTo(roleSearchCriterionDTO.getUserType().name());
		if(RoleType.LEVEL1.name().equals(roleSearchCriterionDTO.getType()))//查询企业类型的
		{	
			criteria.andIdNotEqualTo(Constants.CANA_ROLE_ID);//排除cana的管理员的角色帐号
		}
		else if (RoleType.LEVEL2.name().equals(roleSearchCriterionDTO.getType()))//查询员工类型的
		{
			criteria.andMasterIdEqualTo(roleSearchCriterionDTO.getMasterId());
		}
		else 
		{
			throw WebException.instance("非法查询！");
		}
	}
	
	/**
	 * 转换角色搜索结果
	 * @param users
	 * @return
	 */
	private List<RoleSearchResultDTO> convertRoleToRoleSearchResultDTO(List<Role> roles) 
	{
		List<RoleSearchResultDTO> roleSearchResultDTOs = new ArrayList<RoleSearchResultDTO>();
		for(Role role : roles)
		{
			RoleSearchResultDTO roleSearchResultDTO = new RoleSearchResultDTO();
			BeanUtils.copyProperties(role, roleSearchResultDTO);
			if(StringUtils.isNotBlank(role.getRoleType()))
				roleSearchResultDTO.setRoleType(UserType.valueOf(role.getRoleType()).desc());
			roleSearchResultDTOs.add(roleSearchResultDTO);
		}
		return roleSearchResultDTOs;
	}

	@Override
	public boolean checkEmployeeRoleNameExist(RoleSearchCriterionDTO roleSearchCriterionDTO) throws Exception
	{
		StringUtil.trimObjectFields(roleSearchCriterionDTO);//去空格
		if(StringUtils.isBlank(roleSearchCriterionDTO.getMasterId()) || StringUtils.isBlank(roleSearchCriterionDTO.getRoleName()))
		{
			throw WebException.instance("新建角色参数不能为空！");
		}
		RoleExample roleExample = new RoleExample();
		roleExample.createCriteria().andTypeEqualTo(RoleType.LEVEL2.name()).andMasterIdEqualTo(roleSearchCriterionDTO.getMasterId()).andRoleNameEqualTo(roleSearchCriterionDTO.getRoleName());
		List<Role> roleList = searchRoles(roleExample);
		if(!CollectionUtils.isEmpty(roleList))
			return true;
		return false;
	}
	
	@Override
	public boolean checkCompanyRoleNameExit(RoleSearchCriterionDTO roleSearchCriterionDTO) throws Exception{
		StringUtil.trimObjectFields(roleSearchCriterionDTO);//去空格
		if(roleSearchCriterionDTO.getUserType() == null || StringUtils.isBlank(roleSearchCriterionDTO.getRoleName())){
			throw WebException.instance("新建角色参数不能为空！");
		}
		RoleExample roleExample = new RoleExample();
		roleExample.createCriteria().andTypeEqualTo(RoleType.LEVEL1.name()).andRoleNameEqualTo(roleSearchCriterionDTO.getRoleName()).andRoleTypeEqualTo(roleSearchCriterionDTO.getUserType().name());
		List<Role> roleList = searchRoles(roleExample);
		if(!CollectionUtils.isEmpty(roleList))
			return true;
		return false;
	}

	@Override
	public String addRole(RoleAddDTO roleAddDTO) throws Exception
	{
		if(StringUtils.isNotBlank(roleAddDTO.getPermissions()))
			roleAddDTO.setPermissions(roleAddDTO.getPermissions().substring(0,roleAddDTO.getPermissions().length()-1));
		Role role = new Role();
		StringUtil.trimObjectFields(roleAddDTO);//去空格
		BeanUtils.copyProperties(roleAddDTO, role);	
		if(roleAddDTO.getUserType() != null)
			role.setRoleType(roleAddDTO.getUserType().name());//添加角色对应的用户类型
		//role 添加必要字段，角色创建日期，角色Id
		role.setCreatetime(new Date());
		role.setId(generateRoleId());
		roleMapper.insertSelective(role);
		return role.getId();//返回新建的角色Id
	}
	
	@Override
	public ListResult<RoleSearchResultDTO> queryRoleList(RoleSearchCriterionDTO roleSearchCriterionDTO) throws Exception
	{
		Cat.logMetricForCount("Query_Role_List");
		long begainTime = System.currentTimeMillis();
		ListResult<RoleSearchResultDTO> result = new ListResult<>();
		RoleExample roleExample = new RoleExample();
		Criteria criteria = roleExample.createCriteria();
		
		//设置查询条件
		setCriteria(criteria, roleSearchCriterionDTO);
		//计算根据查询条件计算查询结果共有多少条
		int totalNum = roleMapper.countByExample(roleExample);
		result.setTotalNum(totalNum);
		if(roleSearchCriterionDTO.getPageSize() <= 0)
			roleSearchCriterionDTO.setPageSize(totalNum);
		
		//设置分页查询; 设置起始页begainRowNo页码； 
		int begainRowNo = (roleSearchCriterionDTO.getPage() - 1) * roleSearchCriterionDTO.getPageSize();
		if(begainRowNo < totalNum || begainRowNo == 0)
		{
			roleExample.setLimitStart(begainRowNo);
			roleExample.setLimitEnd(roleSearchCriterionDTO.getPageSize());//设置每页显示多少条
			roleExample.setOrderByClause("-id");//根据id进行排序
		}
		else
		{
			Cat.logMetricForCount("Query_Role_List_Fail");
			throw WebException.instance("查询页码超出总页数");
		}
		
		List<Role> roles = roleMapper.selectByExample(roleExample);
		if(CollectionUtils.isEmpty(roles))
		{
			Cat.logMetricForCount("Query_Role_List_Fail");
			result.setData(null);
		}	
		result.setData(convertRoleToRoleSearchResultDTO(roles));
		Cat.logMetricForDuration("Query_Role_List_Time", System.currentTimeMillis()-begainTime);
		Cat.logMetricForCount("Query_Role_List_Success");
		return result;
	}
	
	@Override
	public Map<String, String> queryRolesIdAndName(String currentOperatorId, RoleType roleType, UserType userType) throws Exception {
		List<Role> roles = roleMapper.selectByExampleWithBLOBs(convertQueryRolesIdAndNameExample(currentOperatorId, roleType, userType));
		return convertQueryRolesIdAndNameResult(roles);
	}
	


	@Override
	public RoleSearchResultDTO getRoleById(String roleId) throws Exception{
		StringUtil.trim(roleId);
		if(StringUtils.isBlank(roleId)){
			throw WebException.instance("查询的角色Id不能为空");
		}
		RoleSearchResultDTO roleSearchResultDTO = new RoleSearchResultDTO();
		Role role = roleMapper.selectByPrimaryKey(roleId);
		BeanUtils.copyProperties(role, roleSearchResultDTO);
		return roleSearchResultDTO;
	}

	@Override
	public void updateRole(RoleUpdateDTO roleUpdateDTO) throws Exception{
		StringUtil.trimObjectFields(roleUpdateDTO);//去空格
		if(StringUtils.isBlank(roleUpdateDTO.getRoleId())){
			throw WebException.instance("查询的角色Id不能为空");
		}
		Role role = tablelockMapper.lockMemberRoleById(roleUpdateDTO.getRoleId());
		if(role == null){
			throw WebException.instance("未查询到该角色Id对应的角色");
		}
		//去掉权限字符串最后一个分号。
		if(StringUtils.isNotBlank(roleUpdateDTO.getPermissions()))
			roleUpdateDTO.setPermissions(roleUpdateDTO.getPermissions().substring(0,roleUpdateDTO.getPermissions().length()-1));
		String newPermissions = "";
		List<PermissionDTO> oldPermissionList = getPermissionByRole(roleUpdateDTO.getRoleId());//被修改的角色的权限list
		//修改者的权限list
		List<PermissionDTO> changerPermissionList = Lists.newArrayList();
		Set<PermissionDTO> changerPermissionSet = Sets.newHashSet();
		for(String changeRoleId:roleUpdateDTO.getChangeRoleIdList()){
			changerPermissionSet.addAll(getPermissionByRole(changeRoleId));
		}
		changerPermissionList.addAll(changerPermissionSet);
		//遍历被修改的角色的权限list，比对修改者的权限list，将修改者没有的权限拿出来保存起来。
		for(PermissionDTO permissionDTO : oldPermissionList){
			if(!changerPermissionList.contains(permissionDTO))
				newPermissions += ";" + permissionDTO.getId();
		}
		//将之前保存的权限加入到新修改的权限后面。
		if(StringUtils.isNotBlank(newPermissions)){
			roleUpdateDTO.setPermissions(roleUpdateDTO.getPermissions()+newPermissions);
		}
		BeanUtils.copyProperties(roleUpdateDTO, role);
		role.setId(roleUpdateDTO.getRoleId());
		role.setUpdatetime(new Date());
		if(roleUpdateDTO.getUserType() != null)
			role.setRoleType(roleUpdateDTO.getUserType().name());//添加角色对应的用户类型
		roleMapper.updateByPrimaryKeySelective(role);
	}

	@Override
	public Map<String, String> queryRolesByRoleId(List<RoleDTO> roleDTOList) throws Exception{
		long begainTime = System.currentTimeMillis();
		List<Role> roles = Lists.newArrayList();
		Set<Role> roleSet = new HashSet<>();
		for(RoleDTO roleDTO : roleDTOList){
			StringUtil.trim(roleDTO.getRoleId());
			if(StringUtils.isBlank(roleDTO.getRoleId())){
				Cat.logMetricForCount("Query_Roles_By_RoleId_Fail");
				throw WebException.instance("查询的角色Id不能为空");
			}
			Role role = roleMapper.selectByPrimaryKey(roleDTO.getRoleId());
			RoleExample roleExample = new RoleExample();
			Criteria criteria = roleExample.createCriteria();
			criteria.andRoleTypeEqualTo(role.getRoleType());//设置企业角色的角色类型
//			criteria.andIdNotEqualTo(roleDTO.getRoleId());//去除该角色
			roleSet.addAll(roleMapper.selectByExampleWithBLOBs(roleExample));
			Cat.logMetricForDuration("Query_Roles_By_RoleId", System.currentTimeMillis()-begainTime);
			Cat.logMetricForCount("Query_Roles_By_RoleId_Success");
		}
		roles.addAll(roleSet);
		return convertQueryRolesIdAndNameResult(roles);
	}

	@Override
	public void updateCompanyRoleByCana(RoleUpdateDTO roleUpdateDTO) throws Exception
	{
		StringUtil.trimObjectFields(roleUpdateDTO);//去空格
		if(StringUtils.isBlank(roleUpdateDTO.getRoleId()))
		{
			throw WebException.instance("查询的角色Id不能为空");
		}
		Role role = tablelockMapper.lockMemberRoleById(roleUpdateDTO.getRoleId());
		if(role == null)
		{
			throw WebException.instance("未查询到该角色Id对应的角色");
		}
		//去掉权限字符串最后一个分号。
		if(StringUtils.isNotBlank(roleUpdateDTO.getPermissions()))
			roleUpdateDTO.setPermissions(roleUpdateDTO.getPermissions().substring(0,roleUpdateDTO.getPermissions().length()-1));
		String newPermissions = "";
		List<PermissionDTO> oldPermissionList = getPermissionByRole(roleUpdateDTO.getRoleId());//被修改的角色的权限list
		List<PermissionDTO> changerPermissionList = getAllPermissions();//修改者的权限list
		//遍历被修改的角色的权限list，比对修改者的权限list，将修改者没有的权限拿出来保存起来。
		for(PermissionDTO permissionDTO : oldPermissionList)
		{
			if(!changerPermissionList.contains(permissionDTO))
				newPermissions += ";" + permissionDTO.getId();
		}
		//将之前保存的权限加入到新修改的权限后面。
		if(StringUtils.isNotBlank(newPermissions))
		{
			roleUpdateDTO.setPermissions(roleUpdateDTO.getPermissions()+newPermissions);
		}
		BeanUtils.copyProperties(roleUpdateDTO, role);
		role.setId(roleUpdateDTO.getRoleId());
		role.setUpdatetime(new Date());
		if(roleUpdateDTO.getUserType() != null)
			role.setRoleType(roleUpdateDTO.getUserType().name());//添加角色对应的用户类型
		roleMapper.updateByPrimaryKeySelective(role);
		
	}
	
}
