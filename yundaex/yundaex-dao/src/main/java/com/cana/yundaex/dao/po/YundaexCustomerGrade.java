/**
 * Copyright (c) 2016, Cana and/or its affiliates. All rights reserved.
 */
package com.cana.yundaex.dao.po;

import java.io.Serializable;
import java.math.BigDecimal;

public class YundaexCustomerGrade implements Serializable {
    /**
     *
     */
    private Integer id;

    /**
     *网点编号
     */
    private String stationNo;

    /**
     *评级分数
     */
    private BigDecimal points;

    /**
     *评级等级 
     */
    private String grade;

    /**
     *利率定价
     */
    private BigDecimal beta;

    /**
     *系数值
     */
    private BigDecimal raito;

    private static final long serialVersionUID = 1L;

    /**
     *
     */
    public Integer getId() {
        return id;
    }

    /**
     *
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *网点编号
     */
    public String getStationNo() {
        return stationNo;
    }

    /**
     *网点编号
     */
    public void setStationNo(String stationNo) {
        this.stationNo = stationNo == null ? null : stationNo.trim();
    }

    /**
     *评级分数
     */
    public BigDecimal getPoints() {
        return points;
    }

    /**
     *评级分数
     */
    public void setPoints(BigDecimal points) {
        this.points = points;
    }

    /**
     *评级等级 
     */
    public String getGrade() {
        return grade;
    }

    /**
     *评级等级 
     */
    public void setGrade(String grade) {
        this.grade = grade == null ? null : grade.trim();
    }

    /**
     *利率定价
     */
    public BigDecimal getBeta() {
        return beta;
    }

    /**
     *利率定价
     */
    public void setBeta(BigDecimal beta) {
        this.beta = beta;
    }

    /**
     *系数值
     */
    public BigDecimal getRaito() {
        return raito;
    }

    /**
     *系数值
     */
    public void setRaito(BigDecimal raito) {
        this.raito = raito;
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
        YundaexCustomerGrade other = (YundaexCustomerGrade) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getStationNo() == null ? other.getStationNo() == null : this.getStationNo().equals(other.getStationNo()))
            && (this.getPoints() == null ? other.getPoints() == null : this.getPoints().equals(other.getPoints()))
            && (this.getGrade() == null ? other.getGrade() == null : this.getGrade().equals(other.getGrade()))
            && (this.getBeta() == null ? other.getBeta() == null : this.getBeta().equals(other.getBeta()))
            && (this.getRaito() == null ? other.getRaito() == null : this.getRaito().equals(other.getRaito()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getStationNo() == null) ? 0 : getStationNo().hashCode());
        result = prime * result + ((getPoints() == null) ? 0 : getPoints().hashCode());
        result = prime * result + ((getGrade() == null) ? 0 : getGrade().hashCode());
        result = prime * result + ((getBeta() == null) ? 0 : getBeta().hashCode());
        result = prime * result + ((getRaito() == null) ? 0 : getRaito().hashCode());
        return result;
    }
}