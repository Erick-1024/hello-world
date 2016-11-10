/**
 * Copyright © 2015 Cana. All rights reserved.
 */
package com.cana.bankgate.server.service;

import javax.annotation.Resource;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import com.cana.bankgate.server.enums.TransferFundType;
import com.cana.vbam.common.bankgate.dto.request.BankAccountBalanceQueryDTO;
import com.cana.vbam.common.bankgate.dto.request.BankAccountBaseDTO;
import com.cana.vbam.common.bankgate.dto.request.TradeStatusQueryDTO;
import com.cana.vbam.common.bankgate.dto.request.TransferFundDTO;
import com.cana.vbam.common.bankgate.dto.response.BankAccountBalanceDataDTO;
import com.cana.vbam.common.bankgate.dto.response.BankAccountBalanceResultDTO;
import com.cana.vbam.common.bankgate.dto.response.BankBaseResultDTO;
import com.cana.vbam.common.bankgate.dto.response.TradeStatusResultDTO;
import com.cana.vbam.common.bankgate.enums.BankTranStatus;
import com.cana.vbam.common.service.IVbamCommonService;
import com.cana.vbam.common.testcenter.dto.WithdrawStateDTO;
import com.google.common.collect.Lists;

@Service("bankgateTestCenterService")
public class BankgateTestCenterServiceImpl implements IBankgateTestCenterService, InitializingBean {

	private Boolean isProdEnv;

	@Resource
	private IVbamCommonService commonService;

	@Override
	public BankAccountBalanceResultDTO queryAccountBalance(BankAccountBalanceQueryDTO queryDTO) {
		if (!isProdEnv) {
			synchronized (isProdEnv) {
				Long balance = commonService.getVirtualBalance(queryDTO.getAccountNo());
				if (balance != null) {
					BankAccountBalanceResultDTO result = new BankAccountBalanceResultDTO();
					result.setStatus(BankTranStatus.success);
				    result.setStatusText("请求成功");
				    BankAccountBalanceDataDTO data = new BankAccountBalanceDataDTO();
				    data.setAccountNo(queryDTO.getAccountNo());
				    data.setAvailableBalance(balance);
				    data.setAvailableFund(balance);
				    data.setActualFund(balance);
				    data.setFreezeFund(0L);
				    data.setOverdraft(0L);
				    result.setBankAccountBalanceDatas(Lists.newArrayList(data));
				    return result;
				}
			}
		}
		return null;
	}

	@Override
	public <T extends BankAccountBaseDTO> BankBaseResultDTO transferFundForce(TransferFundType type, String gateSeq,
			T dto) {
		if (!isProdEnv) {
			if (dto instanceof TransferFundDTO) {
				synchronized (isProdEnv) {
					Long balance = commonService.getVirtualBalance(dto.getAccountNo());
					if (balance != null) {
						Long amount = ((TransferFundDTO) dto).getAmount();
						BankBaseResultDTO result = new BankBaseResultDTO();
						if (balance < amount) {
						    result.setStatus(BankTranStatus.fail);
						    result.setStatusText("虚拟余额不足");
						} else {
							commonService.saveVirtualBalance(dto.getAccountNo(), balance - amount, 12);
							result.setStatus(BankTranStatus.success);
							result.setStatusText("交易成功");
						}
						return result;
					}
				}
			}
		}
		return null;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		isProdEnv = commonService.isProdEnv();
	}

	@Override
	public boolean isProdEnv() {
		return isProdEnv;
	}

	@Override
	public TradeStatusResultDTO queryTradeStatusResult(TradeStatusQueryDTO queryDTO) {
		if (!isProdEnv) {
			synchronized (isProdEnv) {
				WithdrawStateDTO withdrawStateDTO = commonService.getWithdrawState(queryDTO.getBusinessSeq());
				if (withdrawStateDTO != null) {
				    return withdrawStateDTO.getTradeStatusResultDTO();
				}
			}
		}
		return null;
	}

}
