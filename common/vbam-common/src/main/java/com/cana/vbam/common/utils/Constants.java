package com.cana.vbam.common.utils;

import com.travelzen.framework.config.tops.TopsConfReader;
import com.travelzen.framework.config.tops.TopsConfEnum.ConfScope;

public class Constants {
	
	// 用户编号序列数名称
	public static final String SEQUENCE_NAME_USER_ID = "member_user_id";
	
	public static final String SEQUENCE_NAME_AUDIT_ID = "member_audit_id";
	
	public static final String SEQUENCE_NAME_ROLE_ID = "member_role_id";

	public static final String ROLE_SPILT_SIMBOL = ",";

	public static final String SEQUENCE_NAME_TASK_ITEM_ID = "task_item_id";
	
	// credit
	public static final String SEQUENCE_NAME_CREDIT_CUSTOMER_ID="credit_customer_apply_id";
	public static final String SEQUENCE_NAME_CREDIT_LIMIT_ID = "credit_limit_id";
	public static final String SEQUENCE_NAME_CREDIT_SUPERVISION_ACCOUNT_ID = "credit_supervision_account_id";
	public static final String SEQUENCE_NAME_CREDIT_TRADE_ID = "credit_trade_id";
	public static final String SEQUENCE_NAME_CREDIT_TRANSFER_ID = "credit_transfer_id";
	public static final String SEQUENCE_NAME_CREDIT_TRANSFER_BUSINESS_SEQ = "credit_transfer_business_seq";
	public static final String SEQUENCE_NAME_CREDIT_LIMIT_AUDIT_ID = "credit_limit_audit_id";
	
	public static final String CANA_ROLE_ID = TopsConfReader.getConfContent("properties/member-common-global.properties", "cana.role.id", ConfScope.R);
	public static final String CANA_ROLE_NAME = TopsConfReader.getConfContent("properties/member-common-global.properties", "cana.role.name", ConfScope.R);
	public static final String CANA_ROLE_DESC = TopsConfReader.getConfContent("properties/member-common-global.properties", "cana.role.desc", ConfScope.R);
	public static final String CANA_CUSTOMER_ID = TopsConfReader.getConfContent("properties/member-common-global.properties", "company.cana.customer.id", ConfScope.R);
	
	public static final String SEQUENCE_NAME_BANKGATE_TRANS_ID = "bankgate_trans_id";

	
	
	// 账户Id
	public static final String BUSINESS_SEQ = "business_seq";
	public static final String SEQUENCE_NAME_ACCOUNT_ID = "account_id";
	public static final String SEQUENCE_NAME_ACCOUNT_APPLY_ID = "account_apply_id";
	public static final String SEQUENCE_NAME_TRADE_RECORD_ID = "account_trade_record_id";
    public static final String SEQUENCE_NAME_TRADE_APPLY_ID = "account_trade_apply_id";
	public static final String ACCOUNT_SUPERVISION_ID = "account_supervision_id";
    public static final String ACCOUNT_SUPERVISION_APPLY_ID = "account_supervision_apply_id";
	
	public static final String REDIS_LOAN_INFO_ID = "redis_loan_info_id";
	
	public static final String LOAN_INFO_ID = "loan_info_id";
	
	public static final String REDIS_REPAYMENT_PLAN_INFO_ID = "redis_repayment_plan_info_id";

	public static final String REDIS_REPAYMENT_EXPENSE_ID = "redis_repayment_expense_id";
	
	public static final String REPAYMENT_LOAN_INFO_ID = "repayment_loan_info_id";
	
	// 还款计划&费用版本号
	public static final String REPAYMENT_PLAN_VERSION = "repayment_plan_version";
	public static final String REPAYMENT_EXPENSE_VERSION = "repayment_expense_version";
	
	public static final String REPAYMENT_RULE_ID = "repayment_rule_id";
	
	public static final String REPAYMENT_ADJUSTMENT_ID = "repayment_adjustment_id";
	
	public static final String ACTIVE_REPAYMENT_ADJUSTMENT_ID = "active_repayment_adjustment_id";

	public static final String SEQUENCE_NAME_SINGLE_COLLECT_ID = "single_collect_id";

	public static final String SEQUENCE_NAME_SINGLE_DISTRIBUTE_DETAIL_ID = "single_distribute_detail_id";

	//消息
	public static final String MESSAGE_NOTIFICATION_ID = "message_notification_id";

	public static final String SEQUENCE_NAME_LOAN_INFO_VERSION = "loan_info_version";

	public static final String SEQUENCE_NAME_EXTENSION_PRODUCT_ID = "extension_product_id";
	
	public static final String SEQUENCE_NAME_EXTENSION_PRODUCT_SUMMARY_ID = "extension_product_summary_id";

	public static final String SEQUENCE_NAME_OVERDUE_PRODUCT_ID = "overdue_product_id";
	
	public static final String SEQUENCE_NAME_OVERDUE_PRODUCT_SUMMARY_ID = "overdue_product_summary_id";
	
	public static final String SEQUENCE_NAME_PENALTY_PRODUCT_ID = "overdue_product_id";
	
	public static final String SEQUENCE_NAME_PENALTY_PRODUCT_SUMMARY_ID = "overdue_product_summary_id";
	
	public static final String SEQUENCE_NAME_LOAN_INFO_SNAPSHOT_ID = "loan_info_snapshot_id";
	
	public static final String SEQUENCE_NAME_PLAN_SNAPSHOT_ID = "plan_snapshot_id";
	
	public static final String SEQUENCE_NAME_EXPENSE_SNAPSHOT_ID = "expense_snapshot_id";
	
	public static final String TRACE_ID = "rpid";
	
	public static final String SEQUENCE_REPORT_BATCH_TASK_ID = "report_batch_task_id";
	
	public static final String SEQUENCE_ACCOUNT_FUND_REPORT_TASK_ID = "account_fund_report_task_id";
	
	public static final String SEQUENCE_NAME_REPORT_MONITOR_DATA = "report_monitor_data";
	
	public static final String SEQUENCE_NAME_REPORT_MONITOR_METRIC = "report_monitor_metric";
	
	//报表
	public static final String REPORT_FACTOR_FINANCE_DAY_ID = "report_factor_finance_day_id";
	public static final String REPORT_FACTOR_FINANCE_YEAR_ID = "report_factor_finance_year_id";
	public static final String REPORT_FACTOR_FINANCE_COUNT_ID = "report_factor_finance_count_id";
	public static final String REPORT_FUND_MONTHLY_ID = "report_fund_monthly_id";
	public static final String REPORT_FUND_MONTHLY = "report_fund_monthly";
	//报表初始化任务
	public static final String TASK_PREFIX_INIT_FINANCE_REPORT = "init_report_task_";
	//真旅产品id
	public static final String TRAVELZEN_FINANCE_PRODUCT_ID = "travelzen_finance";
	//韵达项目id
	public static final String YUNDAEX_FINANCE_PRODUCT_ID = "yundaex_project_id";
	//韵达线下数据redisKey
	public static final String SEQUENCE_NAME_REDIS_LINE_IMPORT_INFO_ID = "redis_line_import_id";
	public static final String SEQUENCE_NAME_REDIS_YUNDAEX_MONITOR_ID = "redis_yundaex_monitor_id";
	// task item extra key
	public static final String TASK_ITEM_EXTRA_KEY_DEFAULT_DEDUCT = "defaultDeduct";
	//放款信息extra key
	@Deprecated
	public static final String REPAYMENT_LOAN_INFO_EXTRA_KEY_PRODUCT = "product";
	public static final String REPAYMENT_LOAN_INFO_EXTRA_KEY_ACTIVE = "active";
	public static final String REPAYMENT_LOAN_INFO_EXTRA_KEY_ACTIVE_TYPE = "type";
	public static final String REPAYMENT_LOAN_INFO_EXTRA_KEY_INSTITUTION_NAME = "institution_name";
	//授信
	public static final String CREDIT_TRAVELZEN_USER_REFUND_RESULT = "credit_travelzen_user_refund_result";
	public static final String CREDIT_LIMIT_RECOVERY_REQUEST = "credit_limit_recovery_request";
	public static final String CREDIT_LIMIT_RECOVERY_RETRY_TASK = "credit_limit_recovery_retry_task";

	public static final String REPAYMENT_SUMMARY_RECORD = "repayment_summary_record";

	public static final String REPAYMENT_SUCESS_MESSAGE = "repayment_success_message";

	public static final String REPAYMENT_SUCESS_MESSAGE_NOTIFY_RETRY_TASK = "repayment_sucess_message_notify_retry_task";

	public static final String SEQUENCE_NAME_LOAN_INFO_LOAN_NO = "loan_info_loan_no";

	//系统维护
	public static final String MAINTENANCE_PATH = "properties/system-maintenance.properties";
	public static final String IS_MAINTAINING = "isMaintaining";
	public static final String MAINTENANCE_WHITE_IP = "maintenance.white.ip";

	//预警
	public static final String SEQUENCE_NAME_EARLYWARNING_CUSTOMER_ID = "earlywarning_customer_id";
	public static final String SEQUENCE_NAME_EARLYWARNING_SYSTEM_EVENT_GENERATE_RECORD_ID = "early_warning_system_event_generate_record_id";
	public static final String SEQUENCE_NAME_EARLYWARNING_LEVEL_CHANGE_ID = "earlywarning_level_change_id";
	public static final String SEQUENCE_NAME_EARLYWARNING_EVENT_ID = "earlywarning_event_id";
	public static final String SWQUENCE_NAME_EARLYWARNING_EVENT_REVIEW_ID = "early_warning_event_review_id";
	
	//VJ
	public static final String SEQUENCE_NAME_VJ_CUSTOMER_APPLY_ID = "vj_customer_apply_id";
	public static final String SEQUENCE_NAME_VJ_TRAN = "vj_tran";
	public static final String SEQUENCE_NAME_CANA_TRAN_SEQ = "cana_tran_seq";
	public static final String VJ_PRODUCT_ID = "vj";
	public static final String SEQUENCE_NAME_VJ_BANK_BILL = "vj_bank_bill_id";
	public static final String SEQUENCE_NAME_VJ_CUSTOMER_APPLY_VERSION = "vj_customer_apply_version";
	public static final String SEQUENCE_NAME_VJ_CUSTOMER_APPLY_SNAPSHOT_ID = "vj_customer_apply_snapshot_id";
	
	//主动还款
	public static final String ACCOUNT_TRADE_STATUS = "accountTradeStatus";
	
	//asset
	public static final String SEQUENCE_NAME_ASSET_EXPENSE_ID = "asset_expense_id";
	public static final String SEQUENCE_NAME_ASSET_CONTRACT_ID = "asset_contract_id";
	public static final String SEQUENCE_NAME_ASSET_PROJECT_ID = "asset_project_id";
	public static final String SEQUENCE_NAME_ASSET_PROJECT_FACTOR_ID = "asset_project_factor_id";
	public static final String SEQUENCE_NAME_ASSET_PROJECT_DOCUMENT_ID = "asset_project_document_id";
	public static final String SEQUENCE_NAME_ASSET_OPERATE_LOG_ID = "asset_operate_log_id";
	public static final String SEQUENCE_NAME_ASSET_CUSTOMER_ID = "asset_customer_id";
	public static final String SEQUENCE_NAME_ASSET_CUSTOMER_MAG_ID = "asset_customer_mag_id";
	public static final String SEQUENCE_NAME_ASSET_CUSTOMER_MAS_ID = "asset_customer_mas_id";
	public static final String SEQUENCE_NAME_ASSET_CUSTOMER_PURCHASE_ID = "asset_customer_purchase_id";
	public static final String SEQUENCE_NAME_ASSET_CUSTOMER_STK_ID = "asset_customer_stk_id";
	public static final String SEQUENCE_NAME_ASSET_CUSTOMER_SALE_ID = "asset_customer_sale_id";
	public static final String SEQUENCE_NAME_ASSET_ENTERPRISE_INFO_ID = "asset_enterprise_info_id";
	public static final String SEQUENCE_NAME_ASSET_INVOICE_INFO_ID = "asset_invoice_info_id";
	public static final String SEQUENCE_NAME_ASSET_INVOICE_BASIC_INFO_ID = "asset_invoice_basic_info_id";
	public static final String SEQUENCE_NAME_ASSET_LOAN_INFO_REDIS_KEY = "asset_loan_info_redis_key";
	public static final String SEQUENCE_NAME_ASSET_LOAN_PLAN_REDIS_KEY = "asset_loan_plan_redis_key";
	public static final String SEQUENCE_NAME_UNDERLYING_ASSET_REDIS_KEY = "asset_underlying_asset_redis_key";
	public static final String SEQUENCE_NAME_ASSET_LOAN_INFO_ID = "asset_loan_info_id";
	public static final String SEQUENCE_NAME_ASSET_LOAN_PLAN_ID = "asset_loan_plan_id";
	public static final String SEQUENCE_NAME_ASSET_LOAN_PAID_ID = "asset_loan_paid_id";
	public static final String SEQUENCE_NAME_REDIS_ASSET_INVOICE_INFO_ID = "redis_invoice_info_id";
	public static final String SEQUENCE_NAME_BUSINESS_BASICE_INFO_ID = "redis_business_basic_info_id";
	public static final String SEQUENCE_NAME_BUSINESS_COUNTERPARTY_ID = "redis_business_counterparty_id";
	public static final String SEQUENCE_NAME_BUSINESS_GUARANTEE_INFO_ID = "redis_business_guarantee_info_id";
	public static final String ASSET_PRIVILEGE_ALL = "ALL";
	public static final String SEQUENCE_NAME_ASSET_CREDIT_ID = "asset_credit_id";
	public static final String SEQUENCE_NAME_ASSET_CREDIT_AUDIT_ID = "asset_credit_audit_id";
	public static final String SEQUENCE_NAME_ASSET_USER_PRIVILEGE_ID = "asset_user_privilege_id";
	public static final String SEQUENCE_NAME_UNDERLYING_ASSET_LOG_ID = "underlying_log_id";
	public static final String SEQUENCE_NAME_SPECIAL_PROGRAM_LOG_ID = "special_program_log_id";
	public static final String SEQUENCE_NAME_ASSET_MARKET_DATA_REDIS_KEY = "asset_market_data_redis_key";
	public static final String SEQUENCE_NAME_ASSET_MARKET_DATA_PROJECT_ID = "asset_market_data_project_id";
	public static final String SEQUENCE_NAME_ASSET_MARKET_DATA_PRODUCT_ID = "asset_market_data_product_id";
	public static final String SEQUENCE_NAME_ASSET_MARKET_DATA_REPORT_ID = "asset_market_data_report_id";
	public static final String UPDATE_INDEX_FLAGS_STR = "1111111111111111";
	
	// 短信发送
	public static final String DINGDONG_SUCCESS_CODE = "1"; 
	public static final String YUNPIAN_SUCCESS_CODE = "0"; 
	public static final String SPLIT_SYMBOL = ",";
	
	// 云片短信发送参数名
	public static final String YUNPIAN_API_KEY = "apikey";
	public static final String YUNPIAN_MOBILE = "mobile";
	public static final String YUNPIAN_CONTENT = "text";

	// 叮咚短信发送参数名
	public static final String DINGDING_API_KEY = "apikey";
	public static final String DINGDING_MOBILE = "mobile";
	public static final String DINGDING_CONTENT = "content";

	//资产管理
	public static final String TASK_SET_CREDIT_EXPIRE = "set_credit_expire_task_";
	public static final String TASK_SET_CREDIT_NORMAL = "set_credit_normal_task_";

	//专项计划
	public static final String SEQUENCE_NAME_ASSET_SPECIAL_PROGRAM_ID = "asset_special_program_id";
	public static final String SEQUENCE_NAME_ASSET_ORIGINATOR_ID = "asset_originator_id";
	public static final String SEQUENCE_NAME_ASSET_SERVICE_AGENCY_ID = "asset_serviceAgency_id";
	//专项计划状态定时任务
	public static final String TASK_SET_SPECIALPROGRAM_STATUS_TO_CLOSE = "task_set_specialprogram_status_to_close";
	public static final String TASK_SET_SPECIALPROGRAM_STATUS_TO_ESTABLISH = "task_set_specialprogram_status_to_establish";
	
	
	
	//Netdisk
	public static final String SEQUENCE_NAME_NETDISK_ID = "common_netdisk_id";

	//lucene
	public static final String INDEX_FILE_PATH= TopsConfReader.getConfContent("properties/lucene-index.properties", "index.file.path", ConfScope.R);

	//homsom
	public static final String SEQUENCE_NAME_HOMSOM_TICKET_ID = "homsom_ticket_id";
	public static final String HOMSOM_TICKET_IMPORT_PREFIX = "homsom_ticket_import_";
	public static final String SEQUENCE_NAME_HOMSOM_SETTLEMENT_REDIS_KEY = "sequence_name_homsom_settlement_redis_key";
	public static final String SEQUENCE_NAME_HOMSOM_SETTLEMENT_TICKET_ID = "homsom_settlement_ticket_id";
	public static final String SEQUENCE_NAME_HOMSOM_SETTLEMENT_TRACK_ID = "homsom_settlement_track_id";
	public static final String SEQUENCE_NAME_HOMSOM_SETTLEMENT_COUNTERPARTY_ID = "homsom_settlement_counterparty_id";
	public static final String SEQUENCE_NAME_HOMSOM_DAILY_REPORT_TRANSFER_DETAIL_ID = "homsom_daily_report_transfer_detail_id";
	
	// 网关
	public static final int WAITING_LOCK_TIMEOUT = 50;
	public static final String QUERY_LIMIT_FLAG = "交易行为异常,请联系技术人员.";
	
	// 恒顺配置文件
	public static final String HOMSOM_PARAM_PATH = "properties/homsom_param.properties";
	
	// 微信 redis key值
	public static final String  SEQUENCE_NAME_ACCOUNT_BALANCE_WECHAT= "redis_account_balance_result_id";
}
