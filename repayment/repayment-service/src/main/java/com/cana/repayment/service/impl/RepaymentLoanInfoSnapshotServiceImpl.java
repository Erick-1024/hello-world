package com.cana.repayment.service.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.cana.repayment.dao.mapper.IRepaymentLoanInfoSnapshotMapper;
import com.cana.repayment.dao.po.RepaymentLoanInfoSnapshot;
import com.cana.repayment.service.IRepaymentLoanInfoSnapshotService;

@Service
public class RepaymentLoanInfoSnapshotServiceImpl implements IRepaymentLoanInfoSnapshotService {

    @Resource
    private IRepaymentLoanInfoSnapshotMapper loanInfoSnapshotMapper;
    
    @Override
    public RepaymentLoanInfoSnapshot getRepaymentLoanInfoSnapshotByLoanInfoIdAndCurrentVersion(String loanInfoId,
	    String currentVersion) {
	
	return loanInfoSnapshotMapper.getByLoanInfoIdAndCurrentVersion(loanInfoId, currentVersion);
    }

}
