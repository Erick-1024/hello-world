package com.cana.asset.service.transaction;

import java.util.List;

import com.cana.homsom.dao.po.HomsomDailyReportTransferDetail;
import com.cana.homsom.dao.po.HomsomTicket;
import com.cana.vbam.common.dto.ObjectResult;
import com.cana.vbam.common.homsom.dto.CounterpartyConfigDTO;
import com.cana.vbam.common.homsom.dto.SubmitSettlementRequestDTO;
import com.cana.vbam.common.homsom.enums.Channel;
import com.cana.vbam.common.member.vo.UserVo;

public interface IHomsomTransactionService {
	
	/**
	 * 导入客票
	 * @param channel
	 * @param ticketList
	 * @throws Exception
	 */
	public void ticketImport(Channel channel, List<HomsomTicket> ticketList);
	
	/**
	 * 准备生成放款
	 * @param channel
	 * @param reportDate 报表日期
	 * @param issueDate 出票日期
	 */
	public void prepareGenerateLoan(Channel channel, String reportDate, String issueDate);
	
	/**
	 * 生成放款
	 * @param channel
	 * @param reportDate 报表日期
	 * @param issueDate 出票日期
	 */
	public void generateLoan(UserVo factorUser, Channel channel, String reportDate, String issueDate);

	/**
	 * 保存交易对手配置
	 * @param counterpartyConfigDTO
	 */
	public ObjectResult<String> modifyCounterpartyConfig(CounterpartyConfigDTO counterpartyConfigDTO);
	
	/**
	 * 查询已有配置的交易对手信息
	 * @param requestDTO
	 * @return
	 */
	public ObjectResult<CounterpartyConfigDTO> getCounterpartyConfigDTO(CounterpartyConfigDTO queryDTO);
	
	/**
	 * 对某个业务合同下的某天的所有交易对手产生的放款申请进行放款
	 * @param date
	 * @param channel
	 * @return 资金转移记录，如不为空，则需要调用方进行银行转账操作
	 */
	public HomsomDailyReportTransferDetail confirmLoan(String date, Channel channel);
	
	/**
	 * 核销/回购确认
	 * @param userVo 操作人
	 * @param requestDTO
	 * @return
	 */
	public ObjectResult<String> selttlementConfirm(UserVo userVo, SubmitSettlementRequestDTO requestDTO);
	
	public void importSettlement2DB(String userId, String rediskey);
	
	public void importBuyBack2DB(String userId, String rediskey);
}
