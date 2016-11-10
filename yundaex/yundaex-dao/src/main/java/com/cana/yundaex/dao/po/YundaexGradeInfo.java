/**
 * Copyright (c) 2016, Cana and/or its affiliates. All rights reserved.
 */
package com.cana.yundaex.dao.po;

import java.io.Serializable;
import java.math.BigDecimal;

public class YundaexGradeInfo implements Serializable {
    /**
     *序号 主键
     */
    private Integer id;

    /**
     *最小分值
     */
    private BigDecimal minPoints;

    /**
     *最大分值
     */
    private BigDecimal maxPoints;

    /**
     *等级
     */
    private String grade;

    /**
     *利率定价
     */
    private BigDecimal beta;

    /**
     *系数值
     */
    private BigDecimal ratio;

    private static final long serialVersionUID = 1L;

    /**
     *序号 主键
     */
    public Integer getId() {
        return id;
    }

    /**
     *序号 主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *最小分值
     */
    public BigDecimal getMinPoints() {
        return minPoints;
    }

    /**
     *最小分值
     */
    public void setMinPoints(BigDecimal minPoints) {
        this.minPoints = minPoints;
    }

    /**
     *最大分值
     */
    public BigDecimal getMaxPoints() {
        return maxPoints;
    }

    /**
     *最大分值
     */
    public void setMaxPoints(BigDecimal maxPoints) {
        this.maxPoints = maxPoints;
    }

    /**
     *等级
     */
    public String getGrade() {
        return grade;
    }

    /**
     *等级
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
    public BigDecimal getRatio() {
        return ratio;
    }

    /**
     *系数值
     */
    public void setRatio(BigDecimal ratio) {
        this.ratio = ratio;
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
        YundaexGradeInfo other = (YundaexGradeInfo) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getMinPoints() == null ? other.getMinPoints() == null : this.getMinPoints().equals(other.getMinPoints()))
            && (this.getMaxPoints() == null ? other.getMaxPoints() == null : this.getMaxPoints().equals(other.getMaxPoints()))
            && (this.getGrade() == null ? other.getGrade() == null : this.getGrade().equals(other.getGrade()))
            && (this.getBeta() == null ? other.getBeta() == null : this.getBeta().equals(other.getBeta()))
            && (this.getRatio() == null ? other.getRatio() == null : this.getRatio().equals(other.getRatio()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getMinPoints() == null) ? 0 : getMinPoints().hashCode());
        result = prime * result + ((getMaxPoints() == null) ? 0 : getMaxPoints().hashCode());
        result = prime * result + ((getGrade() == null) ? 0 : getGrade().hashCode());
        result = prime * result + ((getBeta() == null) ? 0 : getBeta().hashCode());
        result = prime * result + ((getRatio() == null) ? 0 : getRatio().hashCode());
        return result;
    }
}