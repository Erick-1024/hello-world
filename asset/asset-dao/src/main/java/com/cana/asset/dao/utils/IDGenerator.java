package com.cana.asset.dao.utils;

import com.cana.vbam.common.utils.Constants;
import com.travelzen.framework.core.time.DateTimeUtil;
import com.travelzen.framework.dao.rdbms.SequenceGenerator;
import com.travelzen.framework.spring.web.context.SpringApplicationContext;

public class IDGenerator {

	private static SequenceGenerator generator = SpringApplicationContext.getApplicationContext().getBean(SequenceGenerator.class);
	
	public static String generateAssetProjectId() {
		return DateTimeUtil.datetime12() + generator.getNextSeq(Constants.SEQUENCE_NAME_ASSET_PROJECT_ID, 3);
	}
	public static String generateAssetProjectFactorId() {
		return DateTimeUtil.datetime12() + generator.getNextSeq(Constants.SEQUENCE_NAME_ASSET_PROJECT_FACTOR_ID, 3);
	}
	public static String generateAssetProjectDocumentId() {
		return DateTimeUtil.datetime12() + generator.getNextSeq(Constants.SEQUENCE_NAME_ASSET_PROJECT_DOCUMENT_ID, 3);
	}
	public static String generateAssetOperateLogId() {
		return DateTimeUtil.datetime12() + generator.getNextSeq(Constants.SEQUENCE_NAME_ASSET_OPERATE_LOG_ID, 3);
	}
	//客户ｉｄ
	public static String generateAssetCustomerId() {
		return DateTimeUtil.datetime12() + generator.getNextSeq(Constants.SEQUENCE_NAME_ASSET_CUSTOMER_ID, 3);
	}
	//客户高管ｉｄ
	public static String generateAssetCustomerMagId(){
		return DateTimeUtil.datetime12() + generator.getNextSeq(Constants.SEQUENCE_NAME_ASSET_CUSTOMER_MAG_ID,3);
	}
	//客户融资
	public static String generateAssetCustomerMasId(){
		return DateTimeUtil.datetime12() + generator.getNextSeq(Constants.SEQUENCE_NAME_ASSET_CUSTOMER_MAS_ID,3);
	}
	//客户采购
	public static String generateAssetCustomerPurchaseId(){
		return DateTimeUtil.datetime12() + generator.getNextSeq(Constants.SEQUENCE_NAME_ASSET_CUSTOMER_PURCHASE_ID,3);
	}
	//客户销售
	public static String generateAssetCustomerSaleId(){
		return DateTimeUtil.datetime12() + generator.getNextSeq(Constants.SEQUENCE_NAME_ASSET_CUSTOMER_SALE_ID,3);
	}
	//客户股东
	public static String generateAssetCustomerStkId(){
		return DateTimeUtil.datetime12() + generator.getNextSeq(Constants.SEQUENCE_NAME_ASSET_CUSTOMER_STK_ID,3);
	}
	//费用id
	public static String generateAssetExpenseId(){
		return DateTimeUtil.datetime12() + generator.getNextSeq(Constants.SEQUENCE_NAME_ASSET_EXPENSE_ID, 3);
	}
	//应收账款基础信息id
	public static String generateAssetInvoiceBasicInfoId(){
		return DateTimeUtil.datetime12() + generator.getNextSeq(Constants.SEQUENCE_NAME_ASSET_INVOICE_BASIC_INFO_ID, 3);
	}
	//账款信息id
	public static String generateAssetInvoiceInfoId(){
		return DateTimeUtil.datetime12() + generator.getNextSeq(Constants.SEQUENCE_NAME_ASSET_INVOICE_INFO_ID, 3);
	}
	public static String generateAssetLoanPlanId(){
		return DateTimeUtil.datetime12() + generator.getNextSeq(Constants.SEQUENCE_NAME_ASSET_LOAN_PLAN_ID, 3);
	}
	public static String generateAssetLoanPaidId(){
		return DateTimeUtil.datetime12() + generator.getNextSeq(Constants.SEQUENCE_NAME_ASSET_LOAN_PAID_ID, 3);
	}
	//专项计划id
	public static String generateAssetSpecialProgramId(){
		return DateTimeUtil.datetime12() + generator.getNextSeq(Constants.SEQUENCE_NAME_ASSET_SPECIAL_PROGRAM_ID,3);
	}
	//原始权益人id
	public static String generateAssetOriginatorId(){
		return DateTimeUtil.datetime12() + generator.getNextSeq(Constants.SEQUENCE_NAME_ASSET_ORIGINATOR_ID,3);
	}
	//资产服务机构id 
	public static String generateAssetServiceAgencyId(){
		return DateTimeUtil.datetime12() + generator.getNextSeq(Constants.SEQUENCE_NAME_ASSET_SERVICE_AGENCY_ID,3);
	}
	
	
	
	
}
