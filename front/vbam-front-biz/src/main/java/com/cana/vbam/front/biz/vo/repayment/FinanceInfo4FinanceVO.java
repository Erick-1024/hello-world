package com.cana.vbam.front.biz.vo.repayment;

public class FinanceInfo4FinanceVO {
	
		// 融资余额, 单位分
		private String financeBalance;
		// 融资笔数
		private int loanInfoNum;
		// 逾期金额, 单位分
		private String totalOverdueAmount;
		// 逾期笔数
		private int overdueNum;
		// 近7日待还款金额, 单位分
		private String toPayAmount;
		public String getFinanceBalance() {
			return financeBalance;
		}
		public void setFinanceBalance(String financeBalance) {
			this.financeBalance = financeBalance;
		}
		public int getLoanInfoNum() {
			return loanInfoNum;
		}
		public void setLoanInfoNum(int loanInfoNum) {
			this.loanInfoNum = loanInfoNum;
		}
		public String getTotalOverdueAmount() {
			return totalOverdueAmount;
		}
		public void setTotalOverdueAmount(String totalOverdueAmount) {
			this.totalOverdueAmount = totalOverdueAmount;
		}
		public int getOverdueNum() {
			return overdueNum;
		}
		public void setOverdueNum(int overdueNum) {
			this.overdueNum = overdueNum;
		}
		public String getToPayAmount() {
			return toPayAmount;
		}
		public void setToPayAmount(String toPayAmount) {
			this.toPayAmount = toPayAmount;
		}
		
		

}
