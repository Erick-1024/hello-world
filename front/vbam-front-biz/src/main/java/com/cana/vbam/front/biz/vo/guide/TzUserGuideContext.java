package com.cana.vbam.front.biz.vo.guide;

import java.io.Serializable;

import com.cana.member.authorization.common.SecurityContextUtils;

/**
 * 真旅客户引导页面上下文信息
 * 
 * @author XuMeng
 *
 */
public class TzUserGuideContext implements Serializable {
	private static final long serialVersionUID = 1L;

	private String contractId;
	private String supervisionAccountNo;
	private boolean individual; // 是否是个人客户

	public static String getRedisKey() {
		return "tz-user-guide-context-redis-key" + SecurityContextUtils.getCustomerId();
	}

	public String getContractId() {
		return contractId;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}

	public String getSupervisionAccountNo() {
		return supervisionAccountNo;
	}

	public void setSupervisionAccountNo(String supervisionAccountNo) {
		this.supervisionAccountNo = supervisionAccountNo;
	}

	public boolean isIndividual() {
		return individual;
	}

	public void setIndividual(boolean individual) {
		this.individual = individual;
	}

}
