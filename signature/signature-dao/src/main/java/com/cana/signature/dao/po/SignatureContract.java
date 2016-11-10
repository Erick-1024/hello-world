/**
 * Copyright (c) 2016, Cana and/or its affiliates. All rights reserved.
 */
package com.cana.signature.dao.po;

import java.io.Serializable;

public class SignatureContract implements Serializable {
    /**
     *id主键
     */
    private String id;

    /**
     *签名合同数据
     */
    private byte[] contractSignedData;

    /**
     *合同原数据
     */
    private byte[] contractSourceData;

    private static final long serialVersionUID = 1L;

    /**
     *id主键
     */
    public String getId() {
        return id;
    }

    /**
     *id主键
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     *签名合同数据
     */
    public byte[] getContractSignedData() {
        return contractSignedData;
    }

    /**
     *签名合同数据
     */
    public void setContractSignedData(byte[] contractSignedData) {
        this.contractSignedData = contractSignedData;
    }

    /**
     *合同原数据
     */
    public byte[] getContractSourceData() {
        return contractSourceData;
    }

    /**
     *合同原数据
     */
    public void setContractSourceData(byte[] contractSourceData) {
        this.contractSourceData = contractSourceData;
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
        SignatureContract other = (SignatureContract) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getContractSignedData() == null ? other.getContractSignedData() == null : this.getContractSignedData().equals(other.getContractSignedData()))
            && (this.getContractSourceData() == null ? other.getContractSourceData() == null : this.getContractSourceData().equals(other.getContractSourceData()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getContractSignedData() == null) ? 0 : getContractSignedData().hashCode());
        result = prime * result + ((getContractSourceData() == null) ? 0 : getContractSourceData().hashCode());
        return result;
    }
}