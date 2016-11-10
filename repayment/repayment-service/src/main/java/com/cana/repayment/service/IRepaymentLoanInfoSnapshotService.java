package com.cana.repayment.service;

import com.cana.repayment.dao.po.RepaymentLoanInfoSnapshot;

public interface IRepaymentLoanInfoSnapshotService {
    
    /**
     * 根据 loanInfoId 和 currentVeriosn 查询一条 放款信息快照
     * @param loanInfoId
     * @param currentVersion
     * @return
     */
    public RepaymentLoanInfoSnapshot getRepaymentLoanInfoSnapshotByLoanInfoIdAndCurrentVersion(String loanInfoId,String currentVersion);
}
