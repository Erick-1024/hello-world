/**
 * Copyright (c) 2016, Cana and/or its affiliates. All rights reserved.
 */
package com.cana.yundaex.dao.po;

import java.io.Serializable;
import java.math.BigDecimal;

public class YundaexCompositeCost implements Serializable {
    /**
     *主键ID
     */
    private Integer id;

    /**
     *
     */
    private String cityLevel;

    /**
     *站点所在城市，多个城市有相同的成本，用中文逗号分割
     */
    private String stationCity;

    /**
     *门店租金成本
     */
    private BigDecimal rentalCost;

    /**
     *运输成本
     */
    private BigDecimal transportCost;

    /**
     *缺货损货成本
     */
    private BigDecimal defectCost;

    private static final long serialVersionUID = 1L;

    /**
     *主键ID
     */
    public Integer getId() {
        return id;
    }

    /**
     *主键ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *
     */
    public String getCityLevel() {
        return cityLevel;
    }

    /**
     *
     */
    public void setCityLevel(String cityLevel) {
        this.cityLevel = cityLevel == null ? null : cityLevel.trim();
    }

    /**
     *站点所在城市，多个城市有相同的成本，用中文逗号分割
     */
    public String getStationCity() {
        return stationCity;
    }

    /**
     *站点所在城市，多个城市有相同的成本，用中文逗号分割
     */
    public void setStationCity(String stationCity) {
        this.stationCity = stationCity == null ? null : stationCity.trim();
    }

    /**
     *门店租金成本
     */
    public BigDecimal getRentalCost() {
        return rentalCost;
    }

    /**
     *门店租金成本
     */
    public void setRentalCost(BigDecimal rentalCost) {
        this.rentalCost = rentalCost;
    }

    /**
     *运输成本
     */
    public BigDecimal getTransportCost() {
        return transportCost;
    }

    /**
     *运输成本
     */
    public void setTransportCost(BigDecimal transportCost) {
        this.transportCost = transportCost;
    }

    /**
     *缺货损货成本
     */
    public BigDecimal getDefectCost() {
        return defectCost;
    }

    /**
     *缺货损货成本
     */
    public void setDefectCost(BigDecimal defectCost) {
        this.defectCost = defectCost;
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
        YundaexCompositeCost other = (YundaexCompositeCost) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCityLevel() == null ? other.getCityLevel() == null : this.getCityLevel().equals(other.getCityLevel()))
            && (this.getStationCity() == null ? other.getStationCity() == null : this.getStationCity().equals(other.getStationCity()))
            && (this.getRentalCost() == null ? other.getRentalCost() == null : this.getRentalCost().equals(other.getRentalCost()))
            && (this.getTransportCost() == null ? other.getTransportCost() == null : this.getTransportCost().equals(other.getTransportCost()))
            && (this.getDefectCost() == null ? other.getDefectCost() == null : this.getDefectCost().equals(other.getDefectCost()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCityLevel() == null) ? 0 : getCityLevel().hashCode());
        result = prime * result + ((getStationCity() == null) ? 0 : getStationCity().hashCode());
        result = prime * result + ((getRentalCost() == null) ? 0 : getRentalCost().hashCode());
        result = prime * result + ((getTransportCost() == null) ? 0 : getTransportCost().hashCode());
        result = prime * result + ((getDefectCost() == null) ? 0 : getDefectCost().hashCode());
        return result;
    }
}