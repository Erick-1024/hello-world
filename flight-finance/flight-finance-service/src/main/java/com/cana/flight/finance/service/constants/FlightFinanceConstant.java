package com.cana.flight.finance.service.constants;

/**
 * @author ducer
 *
 */
public class FlightFinanceConstant {

	//上一次订单号ID的key
	public static final String travelzen_ticket_last_record_id = "travelzen_ticket_last_record_id";
	//单次拉取订单数据的条数
	public static final int travelzen_ticket_record_size_once = 1000;
	//上一次结算数据ID的key
	public static final String travelzen_repayment_last_record_id = "travelzen_repayment_last_record_id";
	//单次拉取结算数据的条数
	public static final int travelzen_repayment_record_size_once = 1000;
	//最近一次日账单同步结束位置
	public static final String travelzen_bill_last_record_id = "travelzen_bill_last_record_id";
	//每次更新日账单最大可处理的订单数
	public static final int travelzen_bill_record_size_once = 600000;
	// 最老的状态可改的客票的recordId（未起飞航班>0）
	public static final String alterable_last_record_id="alterable_last_record_id";
	
	public static final String trace_id = "rpid";
	
	public static final int update_flag_size = 70000; 
	
}
