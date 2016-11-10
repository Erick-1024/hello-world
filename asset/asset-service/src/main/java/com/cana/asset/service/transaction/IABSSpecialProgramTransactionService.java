package com.cana.asset.service.transaction;

import com.cana.asset.dao.po.SpecialProgram;
import com.cana.vbam.common.asset.dto.SpecialProgramDTO;
import com.cana.vbam.common.asset.dto.SpecialProgramIssueListDTO;
import com.cana.vbam.common.asset.dto.SpecialProgramListDTO;
import com.cana.vbam.common.asset.dto.SpecialProgramListRequestDTO;
import com.cana.vbam.common.asset.dto.SpecialProgramListRequestIssueDTO;
import com.cana.vbam.common.asset.dto.SpecialProgramQueryDTO;
import com.cana.vbam.common.asset.dto.SpecialProgramRequestDTO;
import com.cana.vbam.common.dto.ListResult;
import com.cana.vbam.common.member.vo.UserVo;
import com.travelzen.framework.common.PageList;

/**
 * @author jiangzhou.Ren
 * @time 2016年8月29日上午10:52:39
 */
public interface IABSSpecialProgramTransactionService {
	/**
	 * @param request
	 * @param userDetail
	 * 专项计划列表接口
	 */
	public ListResult<SpecialProgramListDTO> querySpecialProgramList(SpecialProgramListRequestDTO request,UserVo userDetail);
		

	/**
	 * @param specialProgramId
	 * @param userDetail
	 * 专项计划详情接口
	 */
	public SpecialProgramDTO getSpecialProgramById(String specialProgramId, UserVo userDetail);

	/**
	 * @param request
	 * @param userDetail
	 * 专项计划新增接口
	 */
	public void addSpecialProgram(SpecialProgramRequestDTO request,UserVo userDetail);

	/**
	 * @param request
	 * @param userDetail
	 * 修改专项计划接口
	 */
	public void updateSpecialProgram(SpecialProgramRequestDTO request,UserVo userDetail);

	/**
	 * @param SpecialPlanNum
	 * @param userDetail
	 * 专项计划删除接口
	 */
	public void deleteSpecialProgramById(String specialProgramId,UserVo userDetail);

	/**
	 * 搜索专项计划
	 * @param specialProgramQueryDTO
	 * @param userVo
	 * @return
	 */
	public PageList<SpecialProgram> getSpecialProgramList(SpecialProgramQueryDTO specialProgramQueryDTO,UserVo userVo);
	
	
	/*******发行后专项计划管理********/
	
	
	
	/**
	 * 发行后专项计划列表查询
	 * @param request
	 * @param userDetail
	 * @return
	 */
	public ListResult<SpecialProgramIssueListDTO> querySpecialProgramIssueList(SpecialProgramListRequestIssueDTO request,UserVo userDetail);
	
	
	/**
	 * 发行后新增专项计划
	 * @param request
	 * @param userDetail
	 */
	public void addSpecialProgramIssue(SpecialProgramRequestDTO request,UserVo userDetail);
	
	/**
	 * 发行后修改专项计划
	 * @param request
	 * @param userDetail
	 */
	public void updateSpeicalProgramIssue(SpecialProgramRequestDTO request,UserVo userDetail);
	
	/**
	 * 根据当前时间到法定日期之后修改为结束状态
	 * @param currentDate
	 */
	public void updateSpecialProgramStatusByStatutoryDueDate(String currentDate);
	
}
