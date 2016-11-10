/**
 * 
 */
package com.cana.member.service.transaction;

import java.util.List;
import java.util.Map;

import com.cana.vbam.common.dto.ListResult;
import com.cana.vbam.common.enums.RoleType;
import com.cana.vbam.common.member.dto.role.RoleAddDTO;
import com.cana.vbam.common.member.dto.role.RoleSearchCriterionDTO;
import com.cana.vbam.common.member.dto.role.RoleSearchResultDTO;
import com.cana.vbam.common.member.dto.role.RoleUpdateDTO;
import com.cana.vbam.common.member.dto.user.RoleDTO;
import com.cana.vbam.common.member.enums.user.UserType;
/**
 * 角色管理 service
 * @author dev3
 *
 */
public interface IRoleService {
	/**
	 * 新建角色时检查员工类型的角色名称是否存在
	 * @param roleSearchCriterionDTO 角色查询标准DTO
	 * @return 存在返回 true,不存在返回false.
	 * @throws Exception
	 */
	public boolean checkEmployeeRoleNameExist(RoleSearchCriterionDTO roleSearchCriterionDTO)throws Exception;
	
	/**
	 * 新建角色时检查公司类型的角色名称是否存在
	 * @param 用户输入的用户名
	 * @return 存在返回 true,不存在返回false.
	 * @throws Exception
	 */
	public boolean checkCompanyRoleNameExit(RoleSearchCriterionDTO roleSearchCriterionDTO)throws Exception;
	
	/**
	 * 向数据库中加入新角色
	 * @param roleAddDTO
	 * @return roleId返回新建的角色的Id
	 * @throws Exception
	 */
	public String addRole(RoleAddDTO roleAddDTO)throws Exception;
	
	/**
	 * 从数据库中查询出role list
	 * @param roleSearchCriterionDTO
	 * @return
	 * @throws Exception
	 */
	public ListResult<RoleSearchResultDTO> queryRoleList(RoleSearchCriterionDTO roleSearchCriterionDTO)throws Exception;
	
	/**
	 * 查询当前用户可用的角色id和角色名
	 * @param currentOperatorId
	 * @return
	 * @throws Exception
	 */
	public Map<String, String> queryRolesIdAndName(String currentOperatorId, RoleType roleType, UserType userType)throws Exception;
	
	/**
	 * 根据角色Id获取相应角色
	 * @param roleId
	 * @return
	 * @throws Exception
	 */
	public RoleSearchResultDTO getRoleById(String roleId)throws Exception;
	
	/**
	 * 更新角色信息
	 * @param roleUpdateDTO
	 * @throws Exception
	 */
	public void updateRole(RoleUpdateDTO roleUpdateDTO)throws Exception;
	
	/**
	 * cana 更新企业类型角色
	 * @param roleUpdateDTO
	 * @throws Exception
	 */
	public void updateCompanyRoleByCana(RoleUpdateDTO roleUpdateDTO)throws Exception;
	
	/**
	 * 根据角色id查询与之roleType相同的<roleId,roleName> map
	 * Map需要排除自己本身
	 * @param roleId
	 * @return
	 * @throws Exception
	 */
	public Map<String, String> queryRolesByRoleId(List<RoleDTO> roleDTOList)throws Exception;
}
