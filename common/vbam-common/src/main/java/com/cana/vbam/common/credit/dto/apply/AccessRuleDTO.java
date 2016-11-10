package com.cana.vbam.common.credit.dto.apply;

import java.io.Serializable;

public class AccessRuleDTO implements Serializable{

	private static final long serialVersionUID = 6187740649758114898L;

	/**
     *是否检查是否是白名单客户
     */
    private String isCheckWhiteCustomer;

    @Deprecated
    /**
     *是否检查真旅网评审结果是否通过
     */
    private String isCheckProposalAuditResult;

    /**
     *与真旅网合作月份的最小值
     */
    private String cooperationPeriod;
    
    @Deprecated
    /**
     *下游回款账期
     */
    private String downstreamRepaymentAccountPeriod;

	/**
     *最近一段时间内的逾期率的最大值（真旅网平台）
     */
    private String overdueRateTz;

    /**
     *最近一段时间内的逾期次数的最大值（真旅网平台）
     */
    private String overdueTimesTz;

    /**
     *最近一段时间内的逾期记录的逾期天数的最大值（真旅网平台）
     */
    private String overdueDaysTz;
    
    /**
     *最近一段时间内的逾期率的最大值（cana平台）
     */
    private String overdueRateCana;

    /**
     *最近一段时间内的逾期次数的最大值（cana平台）
     */
    private String overdueTimesCana;

    /**
     *订单采购的增长率的最小值
     */
    private String purchaseOrderGrowthRate;

    /**
     *法院被执行（企业）总金额（元）
     */
    private String courtExecuteCompanyAmount;

    /**
     *法院被执行（企业）总次数
     */
    private String courtExecuteCompanyTimes;

    /**
     *法院被执行（个人）总金额（元）
     */
    private String courtExecuteIndividualAmount;

    /**
     *法院被执行（个人）总次数
     */
    private String courtExecuteIndividualTimes;

    @Deprecated
    /**
     *是否检查工商信息是否是真实
     */
    private String isCheckBusinessInfomation;

    @Deprecated
    /**
     *是否检查存在负面信息
     */
    private String isCheckNegativeInfomation;

    public String getDownstreamRepaymentAccountPeriod() {
		return downstreamRepaymentAccountPeriod;
	}

	public void setDownstreamRepaymentAccountPeriod(String downstreamRepaymentAccountPeriod) {
		this.downstreamRepaymentAccountPeriod = downstreamRepaymentAccountPeriod;
	}
    
    public String getIsCheckWhiteCustomer() {
		return isCheckWhiteCustomer;
	}

	public void setIsCheckWhiteCustomer(String isCheckWhiteCustomer) {
		this.isCheckWhiteCustomer = isCheckWhiteCustomer;
	}

	public String getIsCheckProposalAuditResult() {
		return isCheckProposalAuditResult;
	}

	public void setIsCheckProposalAuditResult(String isCheckProposalAuditResult) {
		this.isCheckProposalAuditResult = isCheckProposalAuditResult;
	}

	public String getIsCheckBusinessInfomation() {
		return isCheckBusinessInfomation;
	}

	public void setIsCheckBusinessInfomation(String isCheckBusinessInfomation) {
		this.isCheckBusinessInfomation = isCheckBusinessInfomation;
	}

	public String getIsCheckNegativeInfomation() {
		return isCheckNegativeInfomation;
	}

	public void setIsCheckNegativeInfomation(String isCheckNegativeInfomation) {
		this.isCheckNegativeInfomation = isCheckNegativeInfomation;
	}

	/**
     *与真旅网合作月份的最小值
     */
    public String getCooperationPeriod() {
        return cooperationPeriod;
    }

    /**
     *与真旅网合作月份的最小值
     */
    public void setCooperationPeriod(String cooperationPeriod) {
        this.cooperationPeriod = cooperationPeriod;
    }

    /**
     *最近三个月的逾期率的最大值（真旅网平台）
     */
    public String getOverdueRateTz() {
        return overdueRateTz;
    }

    /**
     *最近三个月的逾期率的最大值（真旅网平台）
     */
    public void setOverdueRateTz(String overdueRateTz) {
        this.overdueRateTz = overdueRateTz;
    }

    /**
     *最近一个月的逾期次数的最大值（真旅网平台）
     */
    public String getOverdueTimesTz() {
        return overdueTimesTz;
    }

    /**
     *最近一个月的逾期次数的最大值（真旅网平台）
     */
    public void setOverdueTimesTz(String overdueTimesTz) {
        this.overdueTimesTz = overdueTimesTz;
    }

    /**
     *最近三个月的逾期率的最大值（cana平台）
     */
    public String getOverdueRateCana() {
        return overdueRateCana;
    }

    /**
     *最近三个月的逾期率的最大值（cana平台）
     */
    public void setOverdueRateCana(String overdueRateCana) {
        this.overdueRateCana = overdueRateCana;
    }

    /**
     *最近一个月的逾期次数的最大值（cana平台）
     */
    public String getOverdueTimesCana() {
        return overdueTimesCana;
    }

    /**
     *最近一个月的逾期次数的最大值（cana平台）
     */
    public void setOverdueTimesCana(String overdueTimesCana) {
        this.overdueTimesCana = overdueTimesCana;
    }

    /**
     *订单采购的增长率的最小值
     */
    public String getPurchaseOrderGrowthRate() {
        return purchaseOrderGrowthRate;
    }

    /**
     *订单采购的增长率的最小值
     */
    public void setPurchaseOrderGrowthRate(String purchaseOrderGrowthRate) {
        this.purchaseOrderGrowthRate = purchaseOrderGrowthRate;
    }

    /**
     *法院被执行（企业）总金额（元）
     */
    public String getCourtExecuteCompanyAmount() {
        return courtExecuteCompanyAmount;
    }

    /**
     *法院被执行（企业）总金额（元）
     */
    public void setCourtExecuteCompanyAmount(String courtExecuteCompanyAmount) {
        this.courtExecuteCompanyAmount = courtExecuteCompanyAmount;
    }

    /**
     *法院被执行（企业）总次数
     */
    public String getCourtExecuteCompanyTimes() {
        return courtExecuteCompanyTimes;
    }

    /**
     *法院被执行（企业）总次数
     */
    public void setCourtExecuteCompanyTimes(String courtExecuteCompanyTimes) {
        this.courtExecuteCompanyTimes = courtExecuteCompanyTimes;
    }

    public String getCourtExecuteIndividualAmount() {
        return courtExecuteIndividualAmount;
    }

    public void setCourtExecuteIndividualAmount(String courtExecuteIndividualAmount) {
        this.courtExecuteIndividualAmount = courtExecuteIndividualAmount;
    }

    public String getCourtExecuteIndividualTimes() {
        return courtExecuteIndividualTimes;
    }

    public void setCourtExecuteIndividualTimes(String courtExecuteIndividualTimes) {
        this.courtExecuteIndividualTimes = courtExecuteIndividualTimes;
    }

	public String getOverdueDaysTz() {
		return overdueDaysTz;
	}

	public void setOverdueDaysTz(String overdueDaysTz) {
		this.overdueDaysTz = overdueDaysTz;
	}
}
