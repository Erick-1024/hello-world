package com.cana.account.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.cana.account.dao.po.AccountTradeRecord;

public interface IQueryUnupdateWithdrawFeeMapper {
	
	@Select("select * from vbam.account_trade_record where business_seq not in "
			+ "(select business_seq from vbam.account_trade_record where trade_type='WITHDRAW_FUND' and operate_type='手续费' "
			+ "and status = 'TRADE_SUCCESS' ) and trade_type='WITHDRAW_FUND' and operate_type='手工转出' and status='TRADE_SUCCESS' ")
	public List<AccountTradeRecord> queryUnpdateWithdrawRecord();
}
