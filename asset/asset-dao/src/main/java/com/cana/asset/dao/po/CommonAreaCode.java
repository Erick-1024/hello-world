/**
 * Copyright (c) 2016, Cana and/or its affiliates. All rights reserved.
 */
package com.cana.asset.dao.po;

import java.io.Serializable;

public class CommonAreaCode implements Serializable {
    /**
     *
     */
    private String areaCode;

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
    private String address;

    private static final long serialVersionUID = 1L;

    /**
     *
     */
    public String getAreaCode() {
        return areaCode;
    }

    /**
     *
     */
    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode == null ? null : areaCode.trim();
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
    public String getAddress() {
        return address;
    }

    /**
     *
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
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
        CommonAreaCode other = (CommonAreaCode) that;
        return (this.getAreaCode() == null ? other.getAreaCode() == null : this.getAreaCode().equals(other.getAreaCode()))
            && (this.getProvince() == null ? other.getProvince() == null : this.getProvince().equals(other.getProvince()))
            && (this.getCity() == null ? other.getCity() == null : this.getCity().equals(other.getCity()))
            && (this.getAddress() == null ? other.getAddress() == null : this.getAddress().equals(other.getAddress()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getAreaCode() == null) ? 0 : getAreaCode().hashCode());
        result = prime * result + ((getProvince() == null) ? 0 : getProvince().hashCode());
        result = prime * result + ((getCity() == null) ? 0 : getCity().hashCode());
        result = prime * result + ((getAddress() == null) ? 0 : getAddress().hashCode());
        return result;
    }
}