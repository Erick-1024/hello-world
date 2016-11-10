package com.cana.asset.api;

import com.cana.vbam.common.asset.dto.QueryCompany4AddUserPrivilegeListItem;
import com.cana.vbam.common.asset.dto.QueryCompanyListRequest;
import com.cana.vbam.common.asset.dto.SpecialProgramDTO;
import com.cana.vbam.common.asset.dto.SpecialProgramIssueListDTO;
import com.cana.vbam.common.asset.dto.SpecialProgramListDTO;
import com.cana.vbam.common.asset.dto.SpecialProgramListRequestDTO;
import com.cana.vbam.common.asset.dto.SpecialProgramListRequestIssueDTO;
import com.cana.vbam.common.asset.dto.SpecialProgramRequestDTO;
import com.cana.vbam.common.dto.ListResult;
import com.cana.vbam.common.member.vo.UserVo;

/**
 * 资产证券化发行管理接口
 * 
 * @author jiangzhou.Ren
 * @time 2016年8月29日上午10:07:42
 */
public interface IABSSpecialProgramApi {

	/**
	 * @param request
	 * 专项计划列表接口
	 */
	public ListResult<SpecialProgramListDTO> querySpecialProgramList(SpecialProgramListRequestDTO request);

	/**
	 * @param id
	 * 专项计划详情接口
	 */
	public SpecialProgramDTO getSpecialProgramById(String specialProgramId, String userId);

	/**
	 * @param request
	 * 专项计划新增接口
	 */
	public void addSpecialProgram(SpecialProgramRequestDTO request);

	/**
	 * @param request
	 * 修改专项计划接口
	 */
	public void updateSpecialProgram(SpecialProgramRequestDTO request);

	/**
	 * @param id
	 * 专项计划删除接口
	 */
	public void deleteSpecialProgramById(String specialProgramId, String userId);
	
	/**
	 * 专项计划查询企业名称和客户类型
	 * @param currentLoginUserVO
	 * @param request
	 * @return
	 */
	public ListResult<QueryCompany4AddUserPrivilegeListItem> queryCompanyList(UserVo currentLoginUserVO, QueryCompanyListRequest request);

	/**
	 * 生成专项计划编号
	 * @return
	 */
	public String getSpecialProgramId();
	
	
	
	/**
	 * 校验专项计划编号是否存在
	 * @param specialProgramId
	 * @return
	 */
	public boolean checkSpecialProgramIdExist(String specialProgramId,String oldId);
	
	
	
	/*****发行后专项计划接口 *****/
	
	
	/**
	 * 成立后专项计划管理列表
	 * @param SpecialProgramListRequestIssueDTO
	 * @return
	 */
	public ListResult<SpecialProgramIssueListDTO> querySpecialProgramIssueList(SpecialProgramListRequestIssueDTO request,String userId);
	 
	/**
	 * 成立后新增后专项计划
	 * @param request
	 */
	public void addSpecialProgramIssue(SpecialProgramRequestDTO request);
	
	
	/**
	 * 成立后修改专项计划
	 * @param request
	 */
	public void updateSpeicalProgramIssue(SpecialProgramRequestDTO request);
	
}






