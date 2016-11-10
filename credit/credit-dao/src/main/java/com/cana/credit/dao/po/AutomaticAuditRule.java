/**
* Copyright (c) 2016, Cana and/or its affiliates. All rights reserved.
*/
package com.cana.credit.dao.po;

import java.io.Serializable;

public class AutomaticAuditRule implements Serializable {
    /**
    *批次号
    */
    private Integer batchNo;

    private static final long serialVersionUID = 1L;

    /**
    *批次号
    */
    public Integer getBatchNo() {
        return batchNo;
    }

    /**
    *批次号
    */
    public void setBatchNo(Integer batchNo) {
        this.batchNo = batchNo;
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
        AutomaticAuditRule other = (AutomaticAuditRule) that;
        return (this.getBatchNo() == null ? other.getBatchNo() == null : this.getBatchNo().equals(other.getBatchNo()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getBatchNo() == null) ? 0 : getBatchNo().hashCode());
        return result;
    }
}