/**
 * Copyright (c) 2016, Cana and/or its affiliates. All rights reserved.
 */
package com.cana.yundaex.dao.po;

import java.io.Serializable;

public class YundaexGradeModel implements Serializable {
    /**
     *主键ID
     */
    private Integer id;

    /**
     *一级指标
     */
    private String firstTarget;

    /**
     *二级指标
     */
    private String secondTarget;

    /**
     *权重×10
     */
    private String weight;

    /**
     *详情
     */
    private String details;

    /**
     *类型，决定该二级指标获取分数的方法
     */
    private String type;

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
     *一级指标
     */
    public String getFirstTarget() {
        return firstTarget;
    }

    /**
     *一级指标
     */
    public void setFirstTarget(String firstTarget) {
        this.firstTarget = firstTarget == null ? null : firstTarget.trim();
    }

    /**
     *二级指标
     */
    public String getSecondTarget() {
        return secondTarget;
    }

    /**
     *二级指标
     */
    public void setSecondTarget(String secondTarget) {
        this.secondTarget = secondTarget == null ? null : secondTarget.trim();
    }

    /**
     *权重×10
     */
    public String getWeight() {
        return weight;
    }

    /**
     *权重×10
     */
    public void setWeight(String weight) {
        this.weight = weight == null ? null : weight.trim();
    }

    /**
     *详情
     */
    public String getDetails() {
        return details;
    }

    /**
     *详情
     */
    public void setDetails(String details) {
        this.details = details == null ? null : details.trim();
    }

    /**
     *类型，决定该二级指标获取分数的方法
     */
    public String getType() {
        return type;
    }

    /**
     *类型，决定该二级指标获取分数的方法
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
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
        YundaexGradeModel other = (YundaexGradeModel) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getFirstTarget() == null ? other.getFirstTarget() == null : this.getFirstTarget().equals(other.getFirstTarget()))
            && (this.getSecondTarget() == null ? other.getSecondTarget() == null : this.getSecondTarget().equals(other.getSecondTarget()))
            && (this.getWeight() == null ? other.getWeight() == null : this.getWeight().equals(other.getWeight()))
            && (this.getDetails() == null ? other.getDetails() == null : this.getDetails().equals(other.getDetails()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getFirstTarget() == null) ? 0 : getFirstTarget().hashCode());
        result = prime * result + ((getSecondTarget() == null) ? 0 : getSecondTarget().hashCode());
        result = prime * result + ((getWeight() == null) ? 0 : getWeight().hashCode());
        result = prime * result + ((getDetails() == null) ? 0 : getDetails().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        return result;
    }
}