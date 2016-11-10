/**
 * Copyright (c) 2016, Cana and/or its affiliates. All rights reserved.
 */
package com.cana.yundaex.dao.po;

import java.io.Serializable;
import java.math.BigDecimal;

public class YundaexStationOperation implements Serializable {
    /**
     *主键

     */
    private Integer id;

    /**
     *人工成本（每单）
     */
    private BigDecimal costManual;

    /**
     *面单成本（每单）
     */
    private BigDecimal costReceipt;

    /**
     *包装平均成本（每单）
     */
    private BigDecimal costPackage;

    /**
     *中转费（每单）
     */
    private BigDecimal transitFee;

    /**
     *其他物料成本占比（揽件）
     */
    private BigDecimal otherMaterialsCostRate;

    /**
     *对方网点派件成本（每单）
     */
    private BigDecimal oppositeStationSendCost;

    /**
     *平均单笔揽件利润
     */
    private BigDecimal averageProfit;

    /**
     *派件收入（每单）
     */
    private BigDecimal sendIncome;

    /**
     *快递员费用（每单）
     */
    private BigDecimal courierFee;

    /**
     *其他物料成本占比（派件）
     */
    private BigDecimal otherMaterialsCostRateSend;

    /**
     *保证金 中转费（每单）
     */
    private BigDecimal transitFeeBail;

    /**
     *保证金 其他费用（每单）
     */
    private BigDecimal otherFeeBail;

    private static final long serialVersionUID = 1L;

    /**
     *主键

     */
    public Integer getId() {
        return id;
    }

    /**
     *主键

     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *人工成本（每单）
     */
    public BigDecimal getCostManual() {
        return costManual;
    }

    /**
     *人工成本（每单）
     */
    public void setCostManual(BigDecimal costManual) {
        this.costManual = costManual;
    }

    /**
     *面单成本（每单）
     */
    public BigDecimal getCostReceipt() {
        return costReceipt;
    }

    /**
     *面单成本（每单）
     */
    public void setCostReceipt(BigDecimal costReceipt) {
        this.costReceipt = costReceipt;
    }

    /**
     *包装平均成本（每单）
     */
    public BigDecimal getCostPackage() {
        return costPackage;
    }

    /**
     *包装平均成本（每单）
     */
    public void setCostPackage(BigDecimal costPackage) {
        this.costPackage = costPackage;
    }

    /**
     *中转费（每单）
     */
    public BigDecimal getTransitFee() {
        return transitFee;
    }

    /**
     *中转费（每单）
     */
    public void setTransitFee(BigDecimal transitFee) {
        this.transitFee = transitFee;
    }

    /**
     *其他物料成本占比（揽件）
     */
    public BigDecimal getOtherMaterialsCostRate() {
        return otherMaterialsCostRate;
    }

    /**
     *其他物料成本占比（揽件）
     */
    public void setOtherMaterialsCostRate(BigDecimal otherMaterialsCostRate) {
        this.otherMaterialsCostRate = otherMaterialsCostRate;
    }

    /**
     *对方网点派件成本（每单）
     */
    public BigDecimal getOppositeStationSendCost() {
        return oppositeStationSendCost;
    }

    /**
     *对方网点派件成本（每单）
     */
    public void setOppositeStationSendCost(BigDecimal oppositeStationSendCost) {
        this.oppositeStationSendCost = oppositeStationSendCost;
    }

    /**
     *平均单笔揽件利润
     */
    public BigDecimal getAverageProfit() {
        return averageProfit;
    }

    /**
     *平均单笔揽件利润
     */
    public void setAverageProfit(BigDecimal averageProfit) {
        this.averageProfit = averageProfit;
    }

    /**
     *派件收入（每单）
     */
    public BigDecimal getSendIncome() {
        return sendIncome;
    }

    /**
     *派件收入（每单）
     */
    public void setSendIncome(BigDecimal sendIncome) {
        this.sendIncome = sendIncome;
    }

    /**
     *快递员费用（每单）
     */
    public BigDecimal getCourierFee() {
        return courierFee;
    }

    /**
     *快递员费用（每单）
     */
    public void setCourierFee(BigDecimal courierFee) {
        this.courierFee = courierFee;
    }

    /**
     *其他物料成本占比（派件）
     */
    public BigDecimal getOtherMaterialsCostRateSend() {
        return otherMaterialsCostRateSend;
    }

    /**
     *其他物料成本占比（派件）
     */
    public void setOtherMaterialsCostRateSend(BigDecimal otherMaterialsCostRateSend) {
        this.otherMaterialsCostRateSend = otherMaterialsCostRateSend;
    }

    /**
     *保证金 中转费（每单）
     */
    public BigDecimal getTransitFeeBail() {
        return transitFeeBail;
    }

    /**
     *保证金 中转费（每单）
     */
    public void setTransitFeeBail(BigDecimal transitFeeBail) {
        this.transitFeeBail = transitFeeBail;
    }

    /**
     *保证金 其他费用（每单）
     */
    public BigDecimal getOtherFeeBail() {
        return otherFeeBail;
    }

    /**
     *保证金 其他费用（每单）
     */
    public void setOtherFeeBail(BigDecimal otherFeeBail) {
        this.otherFeeBail = otherFeeBail;
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
        YundaexStationOperation other = (YundaexStationOperation) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCostManual() == null ? other.getCostManual() == null : this.getCostManual().equals(other.getCostManual()))
            && (this.getCostReceipt() == null ? other.getCostReceipt() == null : this.getCostReceipt().equals(other.getCostReceipt()))
            && (this.getCostPackage() == null ? other.getCostPackage() == null : this.getCostPackage().equals(other.getCostPackage()))
            && (this.getTransitFee() == null ? other.getTransitFee() == null : this.getTransitFee().equals(other.getTransitFee()))
            && (this.getOtherMaterialsCostRate() == null ? other.getOtherMaterialsCostRate() == null : this.getOtherMaterialsCostRate().equals(other.getOtherMaterialsCostRate()))
            && (this.getOppositeStationSendCost() == null ? other.getOppositeStationSendCost() == null : this.getOppositeStationSendCost().equals(other.getOppositeStationSendCost()))
            && (this.getAverageProfit() == null ? other.getAverageProfit() == null : this.getAverageProfit().equals(other.getAverageProfit()))
            && (this.getSendIncome() == null ? other.getSendIncome() == null : this.getSendIncome().equals(other.getSendIncome()))
            && (this.getCourierFee() == null ? other.getCourierFee() == null : this.getCourierFee().equals(other.getCourierFee()))
            && (this.getOtherMaterialsCostRateSend() == null ? other.getOtherMaterialsCostRateSend() == null : this.getOtherMaterialsCostRateSend().equals(other.getOtherMaterialsCostRateSend()))
            && (this.getTransitFeeBail() == null ? other.getTransitFeeBail() == null : this.getTransitFeeBail().equals(other.getTransitFeeBail()))
            && (this.getOtherFeeBail() == null ? other.getOtherFeeBail() == null : this.getOtherFeeBail().equals(other.getOtherFeeBail()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCostManual() == null) ? 0 : getCostManual().hashCode());
        result = prime * result + ((getCostReceipt() == null) ? 0 : getCostReceipt().hashCode());
        result = prime * result + ((getCostPackage() == null) ? 0 : getCostPackage().hashCode());
        result = prime * result + ((getTransitFee() == null) ? 0 : getTransitFee().hashCode());
        result = prime * result + ((getOtherMaterialsCostRate() == null) ? 0 : getOtherMaterialsCostRate().hashCode());
        result = prime * result + ((getOppositeStationSendCost() == null) ? 0 : getOppositeStationSendCost().hashCode());
        result = prime * result + ((getAverageProfit() == null) ? 0 : getAverageProfit().hashCode());
        result = prime * result + ((getSendIncome() == null) ? 0 : getSendIncome().hashCode());
        result = prime * result + ((getCourierFee() == null) ? 0 : getCourierFee().hashCode());
        result = prime * result + ((getOtherMaterialsCostRateSend() == null) ? 0 : getOtherMaterialsCostRateSend().hashCode());
        result = prime * result + ((getTransitFeeBail() == null) ? 0 : getTransitFeeBail().hashCode());
        result = prime * result + ((getOtherFeeBail() == null) ? 0 : getOtherFeeBail().hashCode());
        return result;
    }
}