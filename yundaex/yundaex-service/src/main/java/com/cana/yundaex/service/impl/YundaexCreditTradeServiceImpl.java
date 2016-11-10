/**
 * 
 */
package com.cana.yundaex.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cana.vbam.common.credit.enums.CreditTransferStatus;
import com.cana.yundaex.common.enums.YundaexCreditTransferStatus;
import com.cana.yundaex.dao.mapper.gen.YundaexCreditTransferMapper;
import com.cana.yundaex.dao.po.YundaexCreditTransfer;
import com.cana.yundaex.dao.po.YundaexCreditTransferExample;
import com.cana.yundaex.service.IYundaexCreditTradeService;

/**
 * 韵达项目- 放款实体卡记录实现类
 * 
 * @author guguanggong
 *
 */
@Service
public class YundaexCreditTradeServiceImpl implements IYundaexCreditTradeService {

	@Resource
	private YundaexCreditTransferMapper yundaexCreditTransferMapper;
	
	/**
	 * 获得所有状态处于提现中的授信交易记录
	 * 
	 * @return
	 */
	@Override
	public List<YundaexCreditTransfer> getHandlingCreditTrade() {
		YundaexCreditTransferExample example = new YundaexCreditTransferExample();
		YundaexCreditTransferExample.Criteria criteria = example.createCriteria();
		criteria.andTransferStatusEqualTo(YundaexCreditTransferStatus.HANDING.name());
		List<YundaexCreditTransfer> YundaexCreditTransfers = yundaexCreditTransferMapper.selectByExample(example);
		return YundaexCreditTransfers;
	}

	/**
	 * 更新授信转账状态
	 * 
	 * @param yundaexCreditTransfer
	 * @param creditTransferStatus
	 *            最新的放款状态
	 */
	@Override
	public void updateCreditTransferStatus(YundaexCreditTransfer yundaexCreditTransfer,
			CreditTransferStatus creditTransferStatus) {
		yundaexCreditTransfer.setTransferStatus(creditTransferStatus.name());
		yundaexCreditTransfer.setTransferEndTime(new Date());
		yundaexCreditTransferMapper.updateByPrimaryKey(yundaexCreditTransfer);
	}

}
