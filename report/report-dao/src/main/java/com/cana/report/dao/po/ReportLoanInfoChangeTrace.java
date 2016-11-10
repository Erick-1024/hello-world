/**
* Copyright (c) 2016, Cana and/or its affiliates. All rights reserved.
*/
package com.cana.report.dao.po;

import java.io.Serializable;

public class ReportLoanInfoChangeTrace implements Serializable {
    /**
    *放款信息快照id
    */
    private String loanInfoId;

    /**
    *上一次跟踪到的版本号
    */
    private String lastTraceVersion;

    private static final long serialVersionUID = 1L;

    /**
    *放款信息快照id
    */
    public String getLoanInfoId() {
        return loanInfoId;
    }

    /**
    *放款信息快照id
    */
    public void setLoanInfoId(String loanInfoId) {
        this.loanInfoId = loanInfoId == null ? null : loanInfoId.trim();
    }

    /**
    *上一次跟踪到的版本号
    */
    public String getLastTraceVersion() {
        return lastTraceVersion;
    }

    /**
    *上一次跟踪到的版本号
    */
    public void setLastTraceVersion(String lastTraceVersion) {
        this.lastTraceVersion = lastTraceVersion == null ? null : lastTraceVersion.trim();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        ReportLoanInfoChangeTrace other = (ReportLoanInfoChangeTrace) that;
        return (this.getLoanInfoId() == null ? other.getLoanInfoId() == null : this.getLoanInfoId().equals(other.getLoanInfoId()))
            && (this.getLastTraceVersion() == null ? other.getLastTraceVersion() == null : this.getLastTraceVersion().equals(other.getLastTraceVersion()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getLoanInfoId() == null) ? 0 : getLoanInfoId().hashCode());
        result = prime * result + ((getLastTraceVersion() == null) ? 0 : getLastTraceVersion().hashCode());
        return result;
    }
}