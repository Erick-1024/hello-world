package com.cana.credit.limit.dao.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.cana.credit.limit.dao.po.CreditLimit;

public interface ICommonCreditMapper {
	
	/**
	 * 获取额度信息
	 * @param date
	 * @return
	 */
	@Select("select * from vbam.credit_limit where member_id=#{memberId} and project_id=#{projectId}")
	public CreditLimit getCreditLimitByMemberIdAndProjectId(@Param("memberId") String memberId, @Param("projectId") String projectId);
	
}
