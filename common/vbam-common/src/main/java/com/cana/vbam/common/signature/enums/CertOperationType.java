package com.cana.vbam.common.signature.enums;

/**
 * @author hu
 *
 */
public enum CertOperationType {

	APPLY_AND_DOWNLOAD("1101", "证书申请并下载"),//申请证书, 激活并下载
	UPDATE_AND_DOWNLOAD("1201", "证书更新并下载"),//更新证书, 激活并下载
	APPLY("2101", "证书申请"),//申请证书, 未下载状态
	APPLY_WITH_DN("2102", "定制DN证书申请"),//申请证书, DN由客户传入, 未下载状态
	REMOVE("2201", "证书删除"),//未下载的证书删除
	REAWARD_SERIAL_NO("2301", "两码重发"),//未下载证书重发序列号授权码
	DOWNLOAD("2401", "制证"),//未下载证书激活并下载
	FREEZE("2501", "证书冻结"),//已下载证书冻结
	UNFREEZE("2601", "证书解冻"),//已冻结证书解冻
	UPDATE("2701", "证书更新"),//已激活证书重新签发，证书有效期截止时间为指定时间或更新有效期
	REISSUE("2702", "证书补发"),//已激活证书重新签发，证书有效期截止时间不变
	REPLACE("2703", "证书换发"),//已激活证书重新签发，证书有效期截止时间为指定时间或延长有效期
	SYNCHRONISE("2801", "状态同步"),//差错处理功能，确保证书状态与CA一致
	REVOKE("2901", "证书吊销"),//已激活证书吊销，放入吊销列表(CRL)
	SEARCH_BY_CONDITION("7101", "证书查询"),//如果指定了条件对应的值，该查询条件有效
	SEARCH_UNIQUE("7102", "唯一证书查询"),//根据DN或序列号查询证书信息
	SEARCH_PUBLICKEY("7103", "证书公钥查询"),//根据序列号查询证书公钥信息
	STATISTIC("7201", "证书统计");//如果指定了条件对应的值，该统计条件有效
	
	private String number;
	
	private String desc;
	
	private CertOperationType(String number, String desc){
		this.number = number;
		this.desc = desc;
	}
	
	public String number(){
		return number;
	}
	
	public String desc(){
		return desc;
	}
}
