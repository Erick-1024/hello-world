package com.cana.vbam.common.report.consts;

public class ReportConsts {

	/*--------------------------------------------报表表头------------------------------------------------------------------*/
	public static final String[] repaymentDailyReportHeaders = { "序号", "日期", "项目","融资余额", "当日放款金额", "应还本金", "应还利息","应还服务费"
		,"应还展期费","应还固定费用","应还逾期本金","应还逾期利息","应还逾期服务费","应还逾期展期费","应还逾期本金罚息","应还逾期利息罚息","应还逾期服务费罚息","应还其他罚息"
		,"应还总费用","应还逾期总金额","已还本金","已还利息","已还服务费","已还展期费","已还固定费用","已还提前还款手续费","已还逾期本金","已还逾期利息","已还逾期服务费"
		,"已还逾期展期费","已还逾期本金罚息","已还逾期利息罚息","已还逾期服务费罚息","已还其他罚息","已还总费用","已还逾期总金额","当日调账本金","当日调账利息","当日调账服务费"
		,"当日调账展期","当日调账逾期利息","当日调账逾期服务费","当日调账逾期管理费","当日调账固定费用","当日调账总费用" };
	
	public static final String[] repaymentAnnualReportHeaders = { "序号", "年份", "项目", "融资余额", "当年放款金额", "应还本金","应还利息","应还服务费"
		,"应还展期费","应还固定费用","应还逾期本金","应还逾期利息","应还逾期服务费","应还逾期展期费","应还逾期本金罚息","应还逾期利息罚息","应还逾期服务费罚息"
		,"应还其他罚息","应还总费用","应还逾期总金额","已还本金","已还利息","已还服务费","已还展期费","已还固定费用","已还提前还款手续费","已还逾期本金","已还逾期利息"
		,"已还逾期服务费","已还逾期展期费","已还逾期本金罚息","已还逾期利息罚息","已还逾期服务费罚息","已还其他罚息","已还总费用","已还逾期总金额","累计逾期本金"
		,"累计展期本金","当年调账本金","当年调账利息","当年调账服务费","当年调账展期","当年调账逾期利息","当年调账逾期服务费","当年调账逾期管理费","当年调账固定费用","当年调账总费用" };
	
	public static final String[] repaymentCountReportHeaders = { "序号", "日期", "项目", "放款笔数", "逾期笔数", "展期笔数", "还款笔数", "调账笔数", "新增逾期客户数" };

	/*--------------------------------------------报表表单名------------------------------------------------------------------*/

	public static final String repaymentDailyReportSheetTitle = "融资日报表";

	public static final String repaymentAnnualReportSheetTitle = "融资年报表";

	public static final String repaymentCountReportSheetTitle = "融资计数报表";

	/*--------------------------------------------报表文件名------------------------------------------------------------------*/

	public static final String repaymentDailyReportFileName = "融资日报表.xls";

	public static final String repaymentAnnualReportFileName = "融资年报表.xls";

	public static final String repaymentCountReportFileName = "融资计数报表.xls";

	// --------------- 资金报表导出文件名称

	public static final String fund_daily_report = "资金日报表.xls";

	public static final String fund_year_report = "资金年报表.xls";

	public static final String fund_count_report = "资金计数报表.xls";

	public static final String fund_monthly_report = "资金月报表.xls";

	// ----------------资金报表标题

	public static final String fund_daily_report_title = "资金日报表";

	public static final String fund_year_report_title = "资金年报表";

	public static final String fund_count_report_title = "资金计数报表";

	public static final String fund_monthly_report_title = "资金月报表";

	// ----------------资金报表表头

	public static final String[] cana_fund_daily_report_header = { "序号", "日期", "上一日账户余额", "入金金额", "转账金额", "提现金额",
			"提现费用", "当日账户余额" };

	public static final String[] cana_fund_year_report_header = { "序号", "年份", "上年账户余额", "累计入金金额", "累计转账金额", "累计提现金额",
			"累计提现费用", "本年账户余额" };
	
	public static final String[] finance_fund_daily_report_header = { "序号", "日期", "上一日账户余额", "上一日监管户（自有）余额",
			"上一日监管户(资金方)余额", "入金金额", "转账金额", "提现金额", "提现费用", "当日账户余额", "当日监管户（自有）余额", "当日监管户(资金方)余额" };

	public static final String[] finance_fund_year_report_header = { "序号", "年份", "上年账户余额", "上年监管户（自有）余额",
			"上年监管户(资金方)余额", "累计入金金额", "累计转账金额", "累计提现金额", "累计提现费用", "本年账户余额", "本年监管户（自有）余额", "本年监管户(资金方)余额" };

	public static final String[] factor_fund_daily_report_header = { "序号", "日期", "上一日账户余额", "上一日监管户（自有）余额", "入金金额",
			"转账金额", "提现金额", "提现费用", "当日账户余额", "当日监管户（自有）余额" };

	public static final String[] factor_fund_year_report_header = { "序号", "年份", "上年账户余额", "上年监管户（自有）余额", "累计入金金额",
			"累计转账金额", "累计提现金额", "累计提现费用", "本年账户余额", "本年监管户（自有）余额" };

	public static final String[] fund_count_report_header = { "序号", "日期", "入金笔数", "转账转入笔数", "转账转出笔数", "提现笔数", "退款笔数" };

	public static final String[] fund_monthly_report_header = { "序号", "日期", "保理商", "融资客户", "账户名称", "银行账号", "账户类型",
			"监管类型", "回款账户", "账户状态", "当月账户余额", "获取结果", "时间"};
	
}
