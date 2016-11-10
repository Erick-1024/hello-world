package com.cana.yundaex.dao.po;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class YundaexStationOperationExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int limitStart = -1;

    protected int limitEnd = -1;

    public YundaexStationOperationExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    public void setLimitStart(int limitStart) {
        this.limitStart=limitStart;
    }

    public int getLimitStart() {
        return limitStart;
    }

    public void setLimitEnd(int limitEnd) {
        this.limitEnd=limitEnd;
    }

    public int getLimitEnd() {
        return limitEnd;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andCostManualIsNull() {
            addCriterion("cost_manual is null");
            return (Criteria) this;
        }

        public Criteria andCostManualIsNotNull() {
            addCriterion("cost_manual is not null");
            return (Criteria) this;
        }

        public Criteria andCostManualEqualTo(BigDecimal value) {
            addCriterion("cost_manual =", value, "costManual");
            return (Criteria) this;
        }

        public Criteria andCostManualNotEqualTo(BigDecimal value) {
            addCriterion("cost_manual <>", value, "costManual");
            return (Criteria) this;
        }

        public Criteria andCostManualGreaterThan(BigDecimal value) {
            addCriterion("cost_manual >", value, "costManual");
            return (Criteria) this;
        }

        public Criteria andCostManualGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("cost_manual >=", value, "costManual");
            return (Criteria) this;
        }

        public Criteria andCostManualLessThan(BigDecimal value) {
            addCriterion("cost_manual <", value, "costManual");
            return (Criteria) this;
        }

        public Criteria andCostManualLessThanOrEqualTo(BigDecimal value) {
            addCriterion("cost_manual <=", value, "costManual");
            return (Criteria) this;
        }

        public Criteria andCostManualIn(List<BigDecimal> values) {
            addCriterion("cost_manual in", values, "costManual");
            return (Criteria) this;
        }

        public Criteria andCostManualNotIn(List<BigDecimal> values) {
            addCriterion("cost_manual not in", values, "costManual");
            return (Criteria) this;
        }

        public Criteria andCostManualBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("cost_manual between", value1, value2, "costManual");
            return (Criteria) this;
        }

        public Criteria andCostManualNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("cost_manual not between", value1, value2, "costManual");
            return (Criteria) this;
        }

        public Criteria andCostReceiptIsNull() {
            addCriterion("cost_receipt is null");
            return (Criteria) this;
        }

        public Criteria andCostReceiptIsNotNull() {
            addCriterion("cost_receipt is not null");
            return (Criteria) this;
        }

        public Criteria andCostReceiptEqualTo(BigDecimal value) {
            addCriterion("cost_receipt =", value, "costReceipt");
            return (Criteria) this;
        }

        public Criteria andCostReceiptNotEqualTo(BigDecimal value) {
            addCriterion("cost_receipt <>", value, "costReceipt");
            return (Criteria) this;
        }

        public Criteria andCostReceiptGreaterThan(BigDecimal value) {
            addCriterion("cost_receipt >", value, "costReceipt");
            return (Criteria) this;
        }

        public Criteria andCostReceiptGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("cost_receipt >=", value, "costReceipt");
            return (Criteria) this;
        }

        public Criteria andCostReceiptLessThan(BigDecimal value) {
            addCriterion("cost_receipt <", value, "costReceipt");
            return (Criteria) this;
        }

        public Criteria andCostReceiptLessThanOrEqualTo(BigDecimal value) {
            addCriterion("cost_receipt <=", value, "costReceipt");
            return (Criteria) this;
        }

        public Criteria andCostReceiptIn(List<BigDecimal> values) {
            addCriterion("cost_receipt in", values, "costReceipt");
            return (Criteria) this;
        }

        public Criteria andCostReceiptNotIn(List<BigDecimal> values) {
            addCriterion("cost_receipt not in", values, "costReceipt");
            return (Criteria) this;
        }

        public Criteria andCostReceiptBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("cost_receipt between", value1, value2, "costReceipt");
            return (Criteria) this;
        }

        public Criteria andCostReceiptNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("cost_receipt not between", value1, value2, "costReceipt");
            return (Criteria) this;
        }

        public Criteria andCostPackageIsNull() {
            addCriterion("cost_package is null");
            return (Criteria) this;
        }

        public Criteria andCostPackageIsNotNull() {
            addCriterion("cost_package is not null");
            return (Criteria) this;
        }

        public Criteria andCostPackageEqualTo(BigDecimal value) {
            addCriterion("cost_package =", value, "costPackage");
            return (Criteria) this;
        }

        public Criteria andCostPackageNotEqualTo(BigDecimal value) {
            addCriterion("cost_package <>", value, "costPackage");
            return (Criteria) this;
        }

        public Criteria andCostPackageGreaterThan(BigDecimal value) {
            addCriterion("cost_package >", value, "costPackage");
            return (Criteria) this;
        }

        public Criteria andCostPackageGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("cost_package >=", value, "costPackage");
            return (Criteria) this;
        }

        public Criteria andCostPackageLessThan(BigDecimal value) {
            addCriterion("cost_package <", value, "costPackage");
            return (Criteria) this;
        }

        public Criteria andCostPackageLessThanOrEqualTo(BigDecimal value) {
            addCriterion("cost_package <=", value, "costPackage");
            return (Criteria) this;
        }

        public Criteria andCostPackageIn(List<BigDecimal> values) {
            addCriterion("cost_package in", values, "costPackage");
            return (Criteria) this;
        }

        public Criteria andCostPackageNotIn(List<BigDecimal> values) {
            addCriterion("cost_package not in", values, "costPackage");
            return (Criteria) this;
        }

        public Criteria andCostPackageBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("cost_package between", value1, value2, "costPackage");
            return (Criteria) this;
        }

        public Criteria andCostPackageNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("cost_package not between", value1, value2, "costPackage");
            return (Criteria) this;
        }

        public Criteria andTransitFeeIsNull() {
            addCriterion("transit_fee is null");
            return (Criteria) this;
        }

        public Criteria andTransitFeeIsNotNull() {
            addCriterion("transit_fee is not null");
            return (Criteria) this;
        }

        public Criteria andTransitFeeEqualTo(BigDecimal value) {
            addCriterion("transit_fee =", value, "transitFee");
            return (Criteria) this;
        }

        public Criteria andTransitFeeNotEqualTo(BigDecimal value) {
            addCriterion("transit_fee <>", value, "transitFee");
            return (Criteria) this;
        }

        public Criteria andTransitFeeGreaterThan(BigDecimal value) {
            addCriterion("transit_fee >", value, "transitFee");
            return (Criteria) this;
        }

        public Criteria andTransitFeeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("transit_fee >=", value, "transitFee");
            return (Criteria) this;
        }

        public Criteria andTransitFeeLessThan(BigDecimal value) {
            addCriterion("transit_fee <", value, "transitFee");
            return (Criteria) this;
        }

        public Criteria andTransitFeeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("transit_fee <=", value, "transitFee");
            return (Criteria) this;
        }

        public Criteria andTransitFeeIn(List<BigDecimal> values) {
            addCriterion("transit_fee in", values, "transitFee");
            return (Criteria) this;
        }

        public Criteria andTransitFeeNotIn(List<BigDecimal> values) {
            addCriterion("transit_fee not in", values, "transitFee");
            return (Criteria) this;
        }

        public Criteria andTransitFeeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("transit_fee between", value1, value2, "transitFee");
            return (Criteria) this;
        }

        public Criteria andTransitFeeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("transit_fee not between", value1, value2, "transitFee");
            return (Criteria) this;
        }

        public Criteria andOtherMaterialsCostRateIsNull() {
            addCriterion("other_materials_cost_rate is null");
            return (Criteria) this;
        }

        public Criteria andOtherMaterialsCostRateIsNotNull() {
            addCriterion("other_materials_cost_rate is not null");
            return (Criteria) this;
        }

        public Criteria andOtherMaterialsCostRateEqualTo(BigDecimal value) {
            addCriterion("other_materials_cost_rate =", value, "otherMaterialsCostRate");
            return (Criteria) this;
        }

        public Criteria andOtherMaterialsCostRateNotEqualTo(BigDecimal value) {
            addCriterion("other_materials_cost_rate <>", value, "otherMaterialsCostRate");
            return (Criteria) this;
        }

        public Criteria andOtherMaterialsCostRateGreaterThan(BigDecimal value) {
            addCriterion("other_materials_cost_rate >", value, "otherMaterialsCostRate");
            return (Criteria) this;
        }

        public Criteria andOtherMaterialsCostRateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("other_materials_cost_rate >=", value, "otherMaterialsCostRate");
            return (Criteria) this;
        }

        public Criteria andOtherMaterialsCostRateLessThan(BigDecimal value) {
            addCriterion("other_materials_cost_rate <", value, "otherMaterialsCostRate");
            return (Criteria) this;
        }

        public Criteria andOtherMaterialsCostRateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("other_materials_cost_rate <=", value, "otherMaterialsCostRate");
            return (Criteria) this;
        }

        public Criteria andOtherMaterialsCostRateIn(List<BigDecimal> values) {
            addCriterion("other_materials_cost_rate in", values, "otherMaterialsCostRate");
            return (Criteria) this;
        }

        public Criteria andOtherMaterialsCostRateNotIn(List<BigDecimal> values) {
            addCriterion("other_materials_cost_rate not in", values, "otherMaterialsCostRate");
            return (Criteria) this;
        }

        public Criteria andOtherMaterialsCostRateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("other_materials_cost_rate between", value1, value2, "otherMaterialsCostRate");
            return (Criteria) this;
        }

        public Criteria andOtherMaterialsCostRateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("other_materials_cost_rate not between", value1, value2, "otherMaterialsCostRate");
            return (Criteria) this;
        }

        public Criteria andOppositeStationSendCostIsNull() {
            addCriterion("opposite_station_send_cost is null");
            return (Criteria) this;
        }

        public Criteria andOppositeStationSendCostIsNotNull() {
            addCriterion("opposite_station_send_cost is not null");
            return (Criteria) this;
        }

        public Criteria andOppositeStationSendCostEqualTo(BigDecimal value) {
            addCriterion("opposite_station_send_cost =", value, "oppositeStationSendCost");
            return (Criteria) this;
        }

        public Criteria andOppositeStationSendCostNotEqualTo(BigDecimal value) {
            addCriterion("opposite_station_send_cost <>", value, "oppositeStationSendCost");
            return (Criteria) this;
        }

        public Criteria andOppositeStationSendCostGreaterThan(BigDecimal value) {
            addCriterion("opposite_station_send_cost >", value, "oppositeStationSendCost");
            return (Criteria) this;
        }

        public Criteria andOppositeStationSendCostGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("opposite_station_send_cost >=", value, "oppositeStationSendCost");
            return (Criteria) this;
        }

        public Criteria andOppositeStationSendCostLessThan(BigDecimal value) {
            addCriterion("opposite_station_send_cost <", value, "oppositeStationSendCost");
            return (Criteria) this;
        }

        public Criteria andOppositeStationSendCostLessThanOrEqualTo(BigDecimal value) {
            addCriterion("opposite_station_send_cost <=", value, "oppositeStationSendCost");
            return (Criteria) this;
        }

        public Criteria andOppositeStationSendCostIn(List<BigDecimal> values) {
            addCriterion("opposite_station_send_cost in", values, "oppositeStationSendCost");
            return (Criteria) this;
        }

        public Criteria andOppositeStationSendCostNotIn(List<BigDecimal> values) {
            addCriterion("opposite_station_send_cost not in", values, "oppositeStationSendCost");
            return (Criteria) this;
        }

        public Criteria andOppositeStationSendCostBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("opposite_station_send_cost between", value1, value2, "oppositeStationSendCost");
            return (Criteria) this;
        }

        public Criteria andOppositeStationSendCostNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("opposite_station_send_cost not between", value1, value2, "oppositeStationSendCost");
            return (Criteria) this;
        }

        public Criteria andAverageProfitIsNull() {
            addCriterion("average_profit is null");
            return (Criteria) this;
        }

        public Criteria andAverageProfitIsNotNull() {
            addCriterion("average_profit is not null");
            return (Criteria) this;
        }

        public Criteria andAverageProfitEqualTo(BigDecimal value) {
            addCriterion("average_profit =", value, "averageProfit");
            return (Criteria) this;
        }

        public Criteria andAverageProfitNotEqualTo(BigDecimal value) {
            addCriterion("average_profit <>", value, "averageProfit");
            return (Criteria) this;
        }

        public Criteria andAverageProfitGreaterThan(BigDecimal value) {
            addCriterion("average_profit >", value, "averageProfit");
            return (Criteria) this;
        }

        public Criteria andAverageProfitGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("average_profit >=", value, "averageProfit");
            return (Criteria) this;
        }

        public Criteria andAverageProfitLessThan(BigDecimal value) {
            addCriterion("average_profit <", value, "averageProfit");
            return (Criteria) this;
        }

        public Criteria andAverageProfitLessThanOrEqualTo(BigDecimal value) {
            addCriterion("average_profit <=", value, "averageProfit");
            return (Criteria) this;
        }

        public Criteria andAverageProfitIn(List<BigDecimal> values) {
            addCriterion("average_profit in", values, "averageProfit");
            return (Criteria) this;
        }

        public Criteria andAverageProfitNotIn(List<BigDecimal> values) {
            addCriterion("average_profit not in", values, "averageProfit");
            return (Criteria) this;
        }

        public Criteria andAverageProfitBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("average_profit between", value1, value2, "averageProfit");
            return (Criteria) this;
        }

        public Criteria andAverageProfitNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("average_profit not between", value1, value2, "averageProfit");
            return (Criteria) this;
        }

        public Criteria andSendIncomeIsNull() {
            addCriterion("send_income is null");
            return (Criteria) this;
        }

        public Criteria andSendIncomeIsNotNull() {
            addCriterion("send_income is not null");
            return (Criteria) this;
        }

        public Criteria andSendIncomeEqualTo(BigDecimal value) {
            addCriterion("send_income =", value, "sendIncome");
            return (Criteria) this;
        }

        public Criteria andSendIncomeNotEqualTo(BigDecimal value) {
            addCriterion("send_income <>", value, "sendIncome");
            return (Criteria) this;
        }

        public Criteria andSendIncomeGreaterThan(BigDecimal value) {
            addCriterion("send_income >", value, "sendIncome");
            return (Criteria) this;
        }

        public Criteria andSendIncomeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("send_income >=", value, "sendIncome");
            return (Criteria) this;
        }

        public Criteria andSendIncomeLessThan(BigDecimal value) {
            addCriterion("send_income <", value, "sendIncome");
            return (Criteria) this;
        }

        public Criteria andSendIncomeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("send_income <=", value, "sendIncome");
            return (Criteria) this;
        }

        public Criteria andSendIncomeIn(List<BigDecimal> values) {
            addCriterion("send_income in", values, "sendIncome");
            return (Criteria) this;
        }

        public Criteria andSendIncomeNotIn(List<BigDecimal> values) {
            addCriterion("send_income not in", values, "sendIncome");
            return (Criteria) this;
        }

        public Criteria andSendIncomeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("send_income between", value1, value2, "sendIncome");
            return (Criteria) this;
        }

        public Criteria andSendIncomeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("send_income not between", value1, value2, "sendIncome");
            return (Criteria) this;
        }

        public Criteria andCourierFeeIsNull() {
            addCriterion("courier_fee is null");
            return (Criteria) this;
        }

        public Criteria andCourierFeeIsNotNull() {
            addCriterion("courier_fee is not null");
            return (Criteria) this;
        }

        public Criteria andCourierFeeEqualTo(BigDecimal value) {
            addCriterion("courier_fee =", value, "courierFee");
            return (Criteria) this;
        }

        public Criteria andCourierFeeNotEqualTo(BigDecimal value) {
            addCriterion("courier_fee <>", value, "courierFee");
            return (Criteria) this;
        }

        public Criteria andCourierFeeGreaterThan(BigDecimal value) {
            addCriterion("courier_fee >", value, "courierFee");
            return (Criteria) this;
        }

        public Criteria andCourierFeeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("courier_fee >=", value, "courierFee");
            return (Criteria) this;
        }

        public Criteria andCourierFeeLessThan(BigDecimal value) {
            addCriterion("courier_fee <", value, "courierFee");
            return (Criteria) this;
        }

        public Criteria andCourierFeeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("courier_fee <=", value, "courierFee");
            return (Criteria) this;
        }

        public Criteria andCourierFeeIn(List<BigDecimal> values) {
            addCriterion("courier_fee in", values, "courierFee");
            return (Criteria) this;
        }

        public Criteria andCourierFeeNotIn(List<BigDecimal> values) {
            addCriterion("courier_fee not in", values, "courierFee");
            return (Criteria) this;
        }

        public Criteria andCourierFeeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("courier_fee between", value1, value2, "courierFee");
            return (Criteria) this;
        }

        public Criteria andCourierFeeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("courier_fee not between", value1, value2, "courierFee");
            return (Criteria) this;
        }

        public Criteria andOtherMaterialsCostRateSendIsNull() {
            addCriterion("other_materials_cost_rate_send is null");
            return (Criteria) this;
        }

        public Criteria andOtherMaterialsCostRateSendIsNotNull() {
            addCriterion("other_materials_cost_rate_send is not null");
            return (Criteria) this;
        }

        public Criteria andOtherMaterialsCostRateSendEqualTo(BigDecimal value) {
            addCriterion("other_materials_cost_rate_send =", value, "otherMaterialsCostRateSend");
            return (Criteria) this;
        }

        public Criteria andOtherMaterialsCostRateSendNotEqualTo(BigDecimal value) {
            addCriterion("other_materials_cost_rate_send <>", value, "otherMaterialsCostRateSend");
            return (Criteria) this;
        }

        public Criteria andOtherMaterialsCostRateSendGreaterThan(BigDecimal value) {
            addCriterion("other_materials_cost_rate_send >", value, "otherMaterialsCostRateSend");
            return (Criteria) this;
        }

        public Criteria andOtherMaterialsCostRateSendGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("other_materials_cost_rate_send >=", value, "otherMaterialsCostRateSend");
            return (Criteria) this;
        }

        public Criteria andOtherMaterialsCostRateSendLessThan(BigDecimal value) {
            addCriterion("other_materials_cost_rate_send <", value, "otherMaterialsCostRateSend");
            return (Criteria) this;
        }

        public Criteria andOtherMaterialsCostRateSendLessThanOrEqualTo(BigDecimal value) {
            addCriterion("other_materials_cost_rate_send <=", value, "otherMaterialsCostRateSend");
            return (Criteria) this;
        }

        public Criteria andOtherMaterialsCostRateSendIn(List<BigDecimal> values) {
            addCriterion("other_materials_cost_rate_send in", values, "otherMaterialsCostRateSend");
            return (Criteria) this;
        }

        public Criteria andOtherMaterialsCostRateSendNotIn(List<BigDecimal> values) {
            addCriterion("other_materials_cost_rate_send not in", values, "otherMaterialsCostRateSend");
            return (Criteria) this;
        }

        public Criteria andOtherMaterialsCostRateSendBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("other_materials_cost_rate_send between", value1, value2, "otherMaterialsCostRateSend");
            return (Criteria) this;
        }

        public Criteria andOtherMaterialsCostRateSendNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("other_materials_cost_rate_send not between", value1, value2, "otherMaterialsCostRateSend");
            return (Criteria) this;
        }

        public Criteria andTransitFeeBailIsNull() {
            addCriterion("transit_fee_bail is null");
            return (Criteria) this;
        }

        public Criteria andTransitFeeBailIsNotNull() {
            addCriterion("transit_fee_bail is not null");
            return (Criteria) this;
        }

        public Criteria andTransitFeeBailEqualTo(BigDecimal value) {
            addCriterion("transit_fee_bail =", value, "transitFeeBail");
            return (Criteria) this;
        }

        public Criteria andTransitFeeBailNotEqualTo(BigDecimal value) {
            addCriterion("transit_fee_bail <>", value, "transitFeeBail");
            return (Criteria) this;
        }

        public Criteria andTransitFeeBailGreaterThan(BigDecimal value) {
            addCriterion("transit_fee_bail >", value, "transitFeeBail");
            return (Criteria) this;
        }

        public Criteria andTransitFeeBailGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("transit_fee_bail >=", value, "transitFeeBail");
            return (Criteria) this;
        }

        public Criteria andTransitFeeBailLessThan(BigDecimal value) {
            addCriterion("transit_fee_bail <", value, "transitFeeBail");
            return (Criteria) this;
        }

        public Criteria andTransitFeeBailLessThanOrEqualTo(BigDecimal value) {
            addCriterion("transit_fee_bail <=", value, "transitFeeBail");
            return (Criteria) this;
        }

        public Criteria andTransitFeeBailIn(List<BigDecimal> values) {
            addCriterion("transit_fee_bail in", values, "transitFeeBail");
            return (Criteria) this;
        }

        public Criteria andTransitFeeBailNotIn(List<BigDecimal> values) {
            addCriterion("transit_fee_bail not in", values, "transitFeeBail");
            return (Criteria) this;
        }

        public Criteria andTransitFeeBailBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("transit_fee_bail between", value1, value2, "transitFeeBail");
            return (Criteria) this;
        }

        public Criteria andTransitFeeBailNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("transit_fee_bail not between", value1, value2, "transitFeeBail");
            return (Criteria) this;
        }

        public Criteria andOtherFeeBailIsNull() {
            addCriterion("other_fee_bail is null");
            return (Criteria) this;
        }

        public Criteria andOtherFeeBailIsNotNull() {
            addCriterion("other_fee_bail is not null");
            return (Criteria) this;
        }

        public Criteria andOtherFeeBailEqualTo(BigDecimal value) {
            addCriterion("other_fee_bail =", value, "otherFeeBail");
            return (Criteria) this;
        }

        public Criteria andOtherFeeBailNotEqualTo(BigDecimal value) {
            addCriterion("other_fee_bail <>", value, "otherFeeBail");
            return (Criteria) this;
        }

        public Criteria andOtherFeeBailGreaterThan(BigDecimal value) {
            addCriterion("other_fee_bail >", value, "otherFeeBail");
            return (Criteria) this;
        }

        public Criteria andOtherFeeBailGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("other_fee_bail >=", value, "otherFeeBail");
            return (Criteria) this;
        }

        public Criteria andOtherFeeBailLessThan(BigDecimal value) {
            addCriterion("other_fee_bail <", value, "otherFeeBail");
            return (Criteria) this;
        }

        public Criteria andOtherFeeBailLessThanOrEqualTo(BigDecimal value) {
            addCriterion("other_fee_bail <=", value, "otherFeeBail");
            return (Criteria) this;
        }

        public Criteria andOtherFeeBailIn(List<BigDecimal> values) {
            addCriterion("other_fee_bail in", values, "otherFeeBail");
            return (Criteria) this;
        }

        public Criteria andOtherFeeBailNotIn(List<BigDecimal> values) {
            addCriterion("other_fee_bail not in", values, "otherFeeBail");
            return (Criteria) this;
        }

        public Criteria andOtherFeeBailBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("other_fee_bail between", value1, value2, "otherFeeBail");
            return (Criteria) this;
        }

        public Criteria andOtherFeeBailNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("other_fee_bail not between", value1, value2, "otherFeeBail");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}