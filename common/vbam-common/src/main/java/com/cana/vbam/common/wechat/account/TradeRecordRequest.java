package com.cana.vbam.common.wechat.account;

import java.io.Serializable;
import java.util.List;

import com.cana.vbam.common.account.enums.AccountTradeType;

/**
 * 微信平台——流水明细查询条件
 * @author yihong.tang
 */
public class TradeRecordRequest implements Serializable{

	private static final long serialVersionUID = 7954298036497783163L;
	
	private int pageSize;  //每页显示行数
	private String currentTradeRecordId;//当前展示的信息id,分页查询会导致数据重复展示
	private String customerId;//当前登录用户id
	
    // 后台需要，调用api者不需要传
	private int offset = 0;  //偏移量
    private String factorId;  //保理商
    private String finaceId;  //融资商
    private String coreCompanyId; //核心企业
    private List<AccountTradeType> tradeTypes;  //交易类型
    
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getFactorId() {
		return factorId;
	}
	public void setFactorId(String factorId) {
		this.factorId = factorId;
	}
	public String getFinaceId() {
		return finaceId;
	}
	public void setFinaceId(String finaceId) {
		this.finaceId = finaceId;
	}
	public String getCoreCompanyId() {
		return coreCompanyId;
	}
	public void setCoreCompanyId(String coreCompanyId) {
		this.coreCompanyId = coreCompanyId;
	}
	public List<AccountTradeType> getTradeTypes() {
		return tradeTypes;
	}
	public void setTradeTypes(List<AccountTradeType> tradeTypes) {
		this.tradeTypes = tradeTypes;
	}
	public int getOffset() {
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	public String getCurrentTradeRecordId() {
		return currentTradeRecordId;
	}
	public void setCurrentTradeRecordId(String currentTradeRecordId) {
		this.currentTradeRecordId = currentTradeRecordId;
	}
}

