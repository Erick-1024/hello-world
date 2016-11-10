package com.cana.asset.service.transaction;

import com.cana.asset.dao.po.SpecialProgram;
import com.cana.vbam.common.asset.enums.SpecialProgramStatus;
import com.cana.vbam.common.asset.underlyingasset.dto.SpecialProgramLogDTO;
import com.cana.vbam.common.asset.underlyingasset.dto.SpecialProgramLogQuery;
import com.cana.vbam.common.dto.ListResult;
import com.cana.vbam.common.member.vo.UserVo;

/**
 * 资产池管理日志——专项计划日志
 * @author yihong.tang
 *
 */
public interface ISpecialProgramLogTransactionService {

	/**
	 * 插入专项计划操作日志
	 * <p> 插入操作日志需要保存当前操作用户的企业名称和用户名。
	 * <p> 需要调用方传入操作类型，包括 立项，封包，发行，成立，结束
	 * <p> 日志内容为上述操作后的最新的专项计划内容
	 * <p> assetPoolAmount 为最新的资产池金额（最新的融资余额累加），立项的时候为0l
	 */
	public void insertSpecialProgramLog(SpecialProgram specialProgram, Long assetPoolAmount, SpecialProgramStatus operate, UserVo userVo);
	
	/**
	 * 查询当前客户能访问到的所有的专项计划操作日志
	 * <p>需要到ABS数据权限模块获取当前客户能访问的所有的专项计划编号列表，通过该编号列表查询所有的操作日志。
	 */
	public ListResult<SpecialProgramLogDTO> querySpecialProgramLogs(UserVo userVo,SpecialProgramLogQuery query);
}
