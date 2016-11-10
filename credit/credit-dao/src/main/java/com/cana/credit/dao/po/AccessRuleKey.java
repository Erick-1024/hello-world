package com.cana.credit.dao.po;

import java.io.Serializable;

public class AccessRuleKey implements Serializable {
    /**
     *批次号
     */
    private Integer batchNo;

    /**
     *适用对象（白名单客户，非白名单客户）
     */
    private String fitObject;

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

    /**
     *适用对象（白名单客户，非白名单客户）
     */
    public String getFitObject() {
        return fitObject;
    }

    /**
     *适用对象（白名单客户，非白名单客户）
     */
    public void setFitObject(String fitObject) {
        this.fitObject = fitObject == null ? null : fitObject.trim();
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
        AccessRuleKey other = (AccessRuleKey) that;
        return (this.getBatchNo() == null ? other.getBatchNo() == null : this.getBatchNo().equals(other.getBatchNo()))
            && (this.getFitObject() == null ? other.getFitObject() == null : this.getFitObject().equals(other.getFitObject()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getBatchNo() == null) ? 0 : getBatchNo().hashCode());
        result = prime * result + ((getFitObject() == null) ? 0 : getFitObject().hashCode());
        return result;
    }
}