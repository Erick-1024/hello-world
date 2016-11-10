/**
 * Copyright (c) 2016, Cana and/or its affiliates. All rights reserved.
 */
package com.cana.account.dao.po;

import java.io.Serializable;

public class BankBranchInfo implements Serializable {
    /**
     *主键
     */
    private String id;

    /**
     *
     */
    private String lianHangNo;

    /**
     *
     */
    private String bankName;

    /**
     *
     */
    private String province;

    /**
     *
     */
    private String city;

    /**
     *
     */
    private String branchName;

    private static final long serialVersionUID = 1L;

    /**
     *主键
     */
    public String getId() {
        return id;
    }

    /**
     *主键
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     *
     */
    public String getLianHangNo() {
        return lianHangNo;
    }

    /**
     *
     */
    public void setLianHangNo(String lianHangNo) {
        this.lianHangNo = lianHangNo == null ? null : lianHangNo.trim();
    }

    /**
     *
     */
    public String getBankName() {
        return bankName;
    }

    /**
     *
     */
    public void setBankName(String bankName) {
        this.bankName = bankName == null ? null : bankName.trim();
    }

    /**
     *
     */
    public String getProvince() {
        return province;
    }

    /**
     *
     */
    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    /**
     *
     */
    public String getCity() {
        return city;
    }

    /**
     *
     */
    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    /**
     *
     */
    public String getBranchName() {
        return branchName;
    }

    /**
     *
     */
    public void setBranchName(String branchName) {
        this.branchName = branchName == null ? null : branchName.trim();
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
        BankBranchInfo other = (BankBranchInfo) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getLianHangNo() == null ? other.getLianHangNo() == null : this.getLianHangNo().equals(other.getLianHangNo()))
            && (this.getBankName() == null ? other.getBankName() == null : this.getBankName().equals(other.getBankName()))
            && (this.getProvince() == null ? other.getProvince() == null : this.getProvince().equals(other.getProvince()))
            && (this.getCity() == null ? other.getCity() == null : this.getCity().equals(other.getCity()))
            && (this.getBranchName() == null ? other.getBranchName() == null : this.getBranchName().equals(other.getBranchName()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getLianHangNo() == null) ? 0 : getLianHangNo().hashCode());
        result = prime * result + ((getBankName() == null) ? 0 : getBankName().hashCode());
        result = prime * result + ((getProvince() == null) ? 0 : getProvince().hashCode());
        result = prime * result + ((getCity() == null) ? 0 : getCity().hashCode());
        result = prime * result + ((getBranchName() == null) ? 0 : getBranchName().hashCode());
        return result;
    }
}