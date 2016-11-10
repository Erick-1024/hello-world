/**
 * 
 */
package com.cana.yundaex.dao.mapper;

import java.util.List;

import com.cana.vbam.common.yundaex.dto.loanInfo.YundaexLoanFlowListDTO;
import com.cana.vbam.common.yundaex.dto.loanInfo.YundaexLoanFlowQueryDTO;

/**
 * @author guguanggong
 *
 */
public interface YundaexLoanFlowMapper {

	public List<YundaexLoanFlowListDTO> getYundaexLoanFlowList(YundaexLoanFlowQueryDTO loanFlowQueryDTO);
	
	
	public Integer getYundaexLoanFlowCount(YundaexLoanFlowQueryDTO loanFlowQueryDTO);
}
