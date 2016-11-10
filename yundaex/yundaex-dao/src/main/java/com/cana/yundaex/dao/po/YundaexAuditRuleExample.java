package com.cana.yundaex.dao.po;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class YundaexAuditRuleExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int limitStart = -1;

    protected int limitEnd = -1;

    public YundaexAuditRuleExample() {
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

        public Criteria andBatchNoIsNull() {
            addCriterion("batch_no is null");
            return (Criteria) this;
        }

        public Criteria andBatchNoIsNotNull() {
            addCriterion("batch_no is not null");
            return (Criteria) this;
        }

        public Criteria andBatchNoEqualTo(Integer value) {
            addCriterion("batch_no =", value, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoNotEqualTo(Integer value) {
            addCriterion("batch_no <>", value, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoGreaterThan(Integer value) {
            addCriterion("batch_no >", value, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoGreaterThanOrEqualTo(Integer value) {
            addCriterion("batch_no >=", value, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoLessThan(Integer value) {
            addCriterion("batch_no <", value, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoLessThanOrEqualTo(Integer value) {
            addCriterion("batch_no <=", value, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoIn(List<Integer> values) {
            addCriterion("batch_no in", values, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoNotIn(List<Integer> values) {
            addCriterion("batch_no not in", values, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoBetween(Integer value1, Integer value2) {
            addCriterion("batch_no between", value1, value2, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoNotBetween(Integer value1, Integer value2) {
            addCriterion("batch_no not between", value1, value2, "batchNo");
            return (Criteria) this;
        }

        public Criteria andApplycustomerAddressIsNull() {
            addCriterion("applyCustomer_address is null");
            return (Criteria) this;
        }

        public Criteria andApplycustomerAddressIsNotNull() {
            addCriterion("applyCustomer_address is not null");
            return (Criteria) this;
        }

        public Criteria andApplycustomerAddressEqualTo(String value) {
            addCriterion("applyCustomer_address =", value, "applycustomerAddress");
            return (Criteria) this;
        }

        public Criteria andApplycustomerAddressNotEqualTo(String value) {
            addCriterion("applyCustomer_address <>", value, "applycustomerAddress");
            return (Criteria) this;
        }

        public Criteria andApplycustomerAddressGreaterThan(String value) {
            addCriterion("applyCustomer_address >", value, "applycustomerAddress");
            return (Criteria) this;
        }

        public Criteria andApplycustomerAddressGreaterThanOrEqualTo(String value) {
            addCriterion("applyCustomer_address >=", value, "applycustomerAddress");
            return (Criteria) this;
        }

        public Criteria andApplycustomerAddressLessThan(String value) {
            addCriterion("applyCustomer_address <", value, "applycustomerAddress");
            return (Criteria) this;
        }

        public Criteria andApplycustomerAddressLessThanOrEqualTo(String value) {
            addCriterion("applyCustomer_address <=", value, "applycustomerAddress");
            return (Criteria) this;
        }

        public Criteria andApplycustomerAddressLike(String value) {
            addCriterion("applyCustomer_address like", value, "applycustomerAddress");
            return (Criteria) this;
        }

        public Criteria andApplycustomerAddressNotLike(String value) {
            addCriterion("applyCustomer_address not like", value, "applycustomerAddress");
            return (Criteria) this;
        }

        public Criteria andApplycustomerAddressIn(List<String> values) {
            addCriterion("applyCustomer_address in", values, "applycustomerAddress");
            return (Criteria) this;
        }

        public Criteria andApplycustomerAddressNotIn(List<String> values) {
            addCriterion("applyCustomer_address not in", values, "applycustomerAddress");
            return (Criteria) this;
        }

        public Criteria andApplycustomerAddressBetween(String value1, String value2) {
            addCriterion("applyCustomer_address between", value1, value2, "applycustomerAddress");
            return (Criteria) this;
        }

        public Criteria andApplycustomerAddressNotBetween(String value1, String value2) {
            addCriterion("applyCustomer_address not between", value1, value2, "applycustomerAddress");
            return (Criteria) this;
        }

        public Criteria andCooperationPeriodIsNull() {
            addCriterion("cooperation_period is null");
            return (Criteria) this;
        }

        public Criteria andCooperationPeriodIsNotNull() {
            addCriterion("cooperation_period is not null");
            return (Criteria) this;
        }

        public Criteria andCooperationPeriodEqualTo(Integer value) {
            addCriterion("cooperation_period =", value, "cooperationPeriod");
            return (Criteria) this;
        }

        public Criteria andCooperationPeriodNotEqualTo(Integer value) {
            addCriterion("cooperation_period <>", value, "cooperationPeriod");
            return (Criteria) this;
        }

        public Criteria andCooperationPeriodGreaterThan(Integer value) {
            addCriterion("cooperation_period >", value, "cooperationPeriod");
            return (Criteria) this;
        }

        public Criteria andCooperationPeriodGreaterThanOrEqualTo(Integer value) {
            addCriterion("cooperation_period >=", value, "cooperationPeriod");
            return (Criteria) this;
        }

        public Criteria andCooperationPeriodLessThan(Integer value) {
            addCriterion("cooperation_period <", value, "cooperationPeriod");
            return (Criteria) this;
        }

        public Criteria andCooperationPeriodLessThanOrEqualTo(Integer value) {
            addCriterion("cooperation_period <=", value, "cooperationPeriod");
            return (Criteria) this;
        }

        public Criteria andCooperationPeriodIn(List<Integer> values) {
            addCriterion("cooperation_period in", values, "cooperationPeriod");
            return (Criteria) this;
        }

        public Criteria andCooperationPeriodNotIn(List<Integer> values) {
            addCriterion("cooperation_period not in", values, "cooperationPeriod");
            return (Criteria) this;
        }

        public Criteria andCooperationPeriodBetween(Integer value1, Integer value2) {
            addCriterion("cooperation_period between", value1, value2, "cooperationPeriod");
            return (Criteria) this;
        }

        public Criteria andCooperationPeriodNotBetween(Integer value1, Integer value2) {
            addCriterion("cooperation_period not between", value1, value2, "cooperationPeriod");
            return (Criteria) this;
        }

        public Criteria andReceiveSendGrowthRateIsNull() {
            addCriterion("receive_send_growth_rate is null");
            return (Criteria) this;
        }

        public Criteria andReceiveSendGrowthRateIsNotNull() {
            addCriterion("receive_send_growth_rate is not null");
            return (Criteria) this;
        }

        public Criteria andReceiveSendGrowthRateEqualTo(BigDecimal value) {
            addCriterion("receive_send_growth_rate =", value, "receiveSendGrowthRate");
            return (Criteria) this;
        }

        public Criteria andReceiveSendGrowthRateNotEqualTo(BigDecimal value) {
            addCriterion("receive_send_growth_rate <>", value, "receiveSendGrowthRate");
            return (Criteria) this;
        }

        public Criteria andReceiveSendGrowthRateGreaterThan(BigDecimal value) {
            addCriterion("receive_send_growth_rate >", value, "receiveSendGrowthRate");
            return (Criteria) this;
        }

        public Criteria andReceiveSendGrowthRateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("receive_send_growth_rate >=", value, "receiveSendGrowthRate");
            return (Criteria) this;
        }

        public Criteria andReceiveSendGrowthRateLessThan(BigDecimal value) {
            addCriterion("receive_send_growth_rate <", value, "receiveSendGrowthRate");
            return (Criteria) this;
        }

        public Criteria andReceiveSendGrowthRateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("receive_send_growth_rate <=", value, "receiveSendGrowthRate");
            return (Criteria) this;
        }

        public Criteria andReceiveSendGrowthRateIn(List<BigDecimal> values) {
            addCriterion("receive_send_growth_rate in", values, "receiveSendGrowthRate");
            return (Criteria) this;
        }

        public Criteria andReceiveSendGrowthRateNotIn(List<BigDecimal> values) {
            addCriterion("receive_send_growth_rate not in", values, "receiveSendGrowthRate");
            return (Criteria) this;
        }

        public Criteria andReceiveSendGrowthRateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("receive_send_growth_rate between", value1, value2, "receiveSendGrowthRate");
            return (Criteria) this;
        }

        public Criteria andReceiveSendGrowthRateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("receive_send_growth_rate not between", value1, value2, "receiveSendGrowthRate");
            return (Criteria) this;
        }

        public Criteria andNegativeInfomationSearchIsNull() {
            addCriterion("negative_infomation_search is null");
            return (Criteria) this;
        }

        public Criteria andNegativeInfomationSearchIsNotNull() {
            addCriterion("negative_infomation_search is not null");
            return (Criteria) this;
        }

        public Criteria andNegativeInfomationSearchEqualTo(Boolean value) {
            addCriterion("negative_infomation_search =", value, "negativeInfomationSearch");
            return (Criteria) this;
        }

        public Criteria andNegativeInfomationSearchNotEqualTo(Boolean value) {
            addCriterion("negative_infomation_search <>", value, "negativeInfomationSearch");
            return (Criteria) this;
        }

        public Criteria andNegativeInfomationSearchGreaterThan(Boolean value) {
            addCriterion("negative_infomation_search >", value, "negativeInfomationSearch");
            return (Criteria) this;
        }

        public Criteria andNegativeInfomationSearchGreaterThanOrEqualTo(Boolean value) {
            addCriterion("negative_infomation_search >=", value, "negativeInfomationSearch");
            return (Criteria) this;
        }

        public Criteria andNegativeInfomationSearchLessThan(Boolean value) {
            addCriterion("negative_infomation_search <", value, "negativeInfomationSearch");
            return (Criteria) this;
        }

        public Criteria andNegativeInfomationSearchLessThanOrEqualTo(Boolean value) {
            addCriterion("negative_infomation_search <=", value, "negativeInfomationSearch");
            return (Criteria) this;
        }

        public Criteria andNegativeInfomationSearchIn(List<Boolean> values) {
            addCriterion("negative_infomation_search in", values, "negativeInfomationSearch");
            return (Criteria) this;
        }

        public Criteria andNegativeInfomationSearchNotIn(List<Boolean> values) {
            addCriterion("negative_infomation_search not in", values, "negativeInfomationSearch");
            return (Criteria) this;
        }

        public Criteria andNegativeInfomationSearchBetween(Boolean value1, Boolean value2) {
            addCriterion("negative_infomation_search between", value1, value2, "negativeInfomationSearch");
            return (Criteria) this;
        }

        public Criteria andNegativeInfomationSearchNotBetween(Boolean value1, Boolean value2) {
            addCriterion("negative_infomation_search not between", value1, value2, "negativeInfomationSearch");
            return (Criteria) this;
        }

        public Criteria andIsCourtExecuteIndividualInfoIsNull() {
            addCriterion("is_court_execute_individual_info is null");
            return (Criteria) this;
        }

        public Criteria andIsCourtExecuteIndividualInfoIsNotNull() {
            addCriterion("is_court_execute_individual_info is not null");
            return (Criteria) this;
        }

        public Criteria andIsCourtExecuteIndividualInfoEqualTo(Boolean value) {
            addCriterion("is_court_execute_individual_info =", value, "isCourtExecuteIndividualInfo");
            return (Criteria) this;
        }

        public Criteria andIsCourtExecuteIndividualInfoNotEqualTo(Boolean value) {
            addCriterion("is_court_execute_individual_info <>", value, "isCourtExecuteIndividualInfo");
            return (Criteria) this;
        }

        public Criteria andIsCourtExecuteIndividualInfoGreaterThan(Boolean value) {
            addCriterion("is_court_execute_individual_info >", value, "isCourtExecuteIndividualInfo");
            return (Criteria) this;
        }

        public Criteria andIsCourtExecuteIndividualInfoGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_court_execute_individual_info >=", value, "isCourtExecuteIndividualInfo");
            return (Criteria) this;
        }

        public Criteria andIsCourtExecuteIndividualInfoLessThan(Boolean value) {
            addCriterion("is_court_execute_individual_info <", value, "isCourtExecuteIndividualInfo");
            return (Criteria) this;
        }

        public Criteria andIsCourtExecuteIndividualInfoLessThanOrEqualTo(Boolean value) {
            addCriterion("is_court_execute_individual_info <=", value, "isCourtExecuteIndividualInfo");
            return (Criteria) this;
        }

        public Criteria andIsCourtExecuteIndividualInfoIn(List<Boolean> values) {
            addCriterion("is_court_execute_individual_info in", values, "isCourtExecuteIndividualInfo");
            return (Criteria) this;
        }

        public Criteria andIsCourtExecuteIndividualInfoNotIn(List<Boolean> values) {
            addCriterion("is_court_execute_individual_info not in", values, "isCourtExecuteIndividualInfo");
            return (Criteria) this;
        }

        public Criteria andIsCourtExecuteIndividualInfoBetween(Boolean value1, Boolean value2) {
            addCriterion("is_court_execute_individual_info between", value1, value2, "isCourtExecuteIndividualInfo");
            return (Criteria) this;
        }

        public Criteria andIsCourtExecuteIndividualInfoNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_court_execute_individual_info not between", value1, value2, "isCourtExecuteIndividualInfo");
            return (Criteria) this;
        }

        public Criteria andStationAddressIsNull() {
            addCriterion("station_address is null");
            return (Criteria) this;
        }

        public Criteria andStationAddressIsNotNull() {
            addCriterion("station_address is not null");
            return (Criteria) this;
        }

        public Criteria andStationAddressEqualTo(Boolean value) {
            addCriterion("station_address =", value, "stationAddress");
            return (Criteria) this;
        }

        public Criteria andStationAddressNotEqualTo(Boolean value) {
            addCriterion("station_address <>", value, "stationAddress");
            return (Criteria) this;
        }

        public Criteria andStationAddressGreaterThan(Boolean value) {
            addCriterion("station_address >", value, "stationAddress");
            return (Criteria) this;
        }

        public Criteria andStationAddressGreaterThanOrEqualTo(Boolean value) {
            addCriterion("station_address >=", value, "stationAddress");
            return (Criteria) this;
        }

        public Criteria andStationAddressLessThan(Boolean value) {
            addCriterion("station_address <", value, "stationAddress");
            return (Criteria) this;
        }

        public Criteria andStationAddressLessThanOrEqualTo(Boolean value) {
            addCriterion("station_address <=", value, "stationAddress");
            return (Criteria) this;
        }

        public Criteria andStationAddressIn(List<Boolean> values) {
            addCriterion("station_address in", values, "stationAddress");
            return (Criteria) this;
        }

        public Criteria andStationAddressNotIn(List<Boolean> values) {
            addCriterion("station_address not in", values, "stationAddress");
            return (Criteria) this;
        }

        public Criteria andStationAddressBetween(Boolean value1, Boolean value2) {
            addCriterion("station_address between", value1, value2, "stationAddress");
            return (Criteria) this;
        }

        public Criteria andStationAddressNotBetween(Boolean value1, Boolean value2) {
            addCriterion("station_address not between", value1, value2, "stationAddress");
            return (Criteria) this;
        }

        public Criteria andDepositAmountIsNull() {
            addCriterion("deposit_amount is null");
            return (Criteria) this;
        }

        public Criteria andDepositAmountIsNotNull() {
            addCriterion("deposit_amount is not null");
            return (Criteria) this;
        }

        public Criteria andDepositAmountEqualTo(Boolean value) {
            addCriterion("deposit_amount =", value, "depositAmount");
            return (Criteria) this;
        }

        public Criteria andDepositAmountNotEqualTo(Boolean value) {
            addCriterion("deposit_amount <>", value, "depositAmount");
            return (Criteria) this;
        }

        public Criteria andDepositAmountGreaterThan(Boolean value) {
            addCriterion("deposit_amount >", value, "depositAmount");
            return (Criteria) this;
        }

        public Criteria andDepositAmountGreaterThanOrEqualTo(Boolean value) {
            addCriterion("deposit_amount >=", value, "depositAmount");
            return (Criteria) this;
        }

        public Criteria andDepositAmountLessThan(Boolean value) {
            addCriterion("deposit_amount <", value, "depositAmount");
            return (Criteria) this;
        }

        public Criteria andDepositAmountLessThanOrEqualTo(Boolean value) {
            addCriterion("deposit_amount <=", value, "depositAmount");
            return (Criteria) this;
        }

        public Criteria andDepositAmountIn(List<Boolean> values) {
            addCriterion("deposit_amount in", values, "depositAmount");
            return (Criteria) this;
        }

        public Criteria andDepositAmountNotIn(List<Boolean> values) {
            addCriterion("deposit_amount not in", values, "depositAmount");
            return (Criteria) this;
        }

        public Criteria andDepositAmountBetween(Boolean value1, Boolean value2) {
            addCriterion("deposit_amount between", value1, value2, "depositAmount");
            return (Criteria) this;
        }

        public Criteria andDepositAmountNotBetween(Boolean value1, Boolean value2) {
            addCriterion("deposit_amount not between", value1, value2, "depositAmount");
            return (Criteria) this;
        }

        public Criteria andIsRanchiseContractIsNull() {
            addCriterion("is_ranchise_contract is null");
            return (Criteria) this;
        }

        public Criteria andIsRanchiseContractIsNotNull() {
            addCriterion("is_ranchise_contract is not null");
            return (Criteria) this;
        }

        public Criteria andIsRanchiseContractEqualTo(Boolean value) {
            addCriterion("is_ranchise_contract =", value, "isRanchiseContract");
            return (Criteria) this;
        }

        public Criteria andIsRanchiseContractNotEqualTo(Boolean value) {
            addCriterion("is_ranchise_contract <>", value, "isRanchiseContract");
            return (Criteria) this;
        }

        public Criteria andIsRanchiseContractGreaterThan(Boolean value) {
            addCriterion("is_ranchise_contract >", value, "isRanchiseContract");
            return (Criteria) this;
        }

        public Criteria andIsRanchiseContractGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_ranchise_contract >=", value, "isRanchiseContract");
            return (Criteria) this;
        }

        public Criteria andIsRanchiseContractLessThan(Boolean value) {
            addCriterion("is_ranchise_contract <", value, "isRanchiseContract");
            return (Criteria) this;
        }

        public Criteria andIsRanchiseContractLessThanOrEqualTo(Boolean value) {
            addCriterion("is_ranchise_contract <=", value, "isRanchiseContract");
            return (Criteria) this;
        }

        public Criteria andIsRanchiseContractIn(List<Boolean> values) {
            addCriterion("is_ranchise_contract in", values, "isRanchiseContract");
            return (Criteria) this;
        }

        public Criteria andIsRanchiseContractNotIn(List<Boolean> values) {
            addCriterion("is_ranchise_contract not in", values, "isRanchiseContract");
            return (Criteria) this;
        }

        public Criteria andIsRanchiseContractBetween(Boolean value1, Boolean value2) {
            addCriterion("is_ranchise_contract between", value1, value2, "isRanchiseContract");
            return (Criteria) this;
        }

        public Criteria andIsRanchiseContractNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_ranchise_contract not between", value1, value2, "isRanchiseContract");
            return (Criteria) this;
        }

        public Criteria andIsQualifiedInspectionRecordIsNull() {
            addCriterion("is_qualified_inspection_record is null");
            return (Criteria) this;
        }

        public Criteria andIsQualifiedInspectionRecordIsNotNull() {
            addCriterion("is_qualified_inspection_record is not null");
            return (Criteria) this;
        }

        public Criteria andIsQualifiedInspectionRecordEqualTo(Boolean value) {
            addCriterion("is_qualified_inspection_record =", value, "isQualifiedInspectionRecord");
            return (Criteria) this;
        }

        public Criteria andIsQualifiedInspectionRecordNotEqualTo(Boolean value) {
            addCriterion("is_qualified_inspection_record <>", value, "isQualifiedInspectionRecord");
            return (Criteria) this;
        }

        public Criteria andIsQualifiedInspectionRecordGreaterThan(Boolean value) {
            addCriterion("is_qualified_inspection_record >", value, "isQualifiedInspectionRecord");
            return (Criteria) this;
        }

        public Criteria andIsQualifiedInspectionRecordGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_qualified_inspection_record >=", value, "isQualifiedInspectionRecord");
            return (Criteria) this;
        }

        public Criteria andIsQualifiedInspectionRecordLessThan(Boolean value) {
            addCriterion("is_qualified_inspection_record <", value, "isQualifiedInspectionRecord");
            return (Criteria) this;
        }

        public Criteria andIsQualifiedInspectionRecordLessThanOrEqualTo(Boolean value) {
            addCriterion("is_qualified_inspection_record <=", value, "isQualifiedInspectionRecord");
            return (Criteria) this;
        }

        public Criteria andIsQualifiedInspectionRecordIn(List<Boolean> values) {
            addCriterion("is_qualified_inspection_record in", values, "isQualifiedInspectionRecord");
            return (Criteria) this;
        }

        public Criteria andIsQualifiedInspectionRecordNotIn(List<Boolean> values) {
            addCriterion("is_qualified_inspection_record not in", values, "isQualifiedInspectionRecord");
            return (Criteria) this;
        }

        public Criteria andIsQualifiedInspectionRecordBetween(Boolean value1, Boolean value2) {
            addCriterion("is_qualified_inspection_record between", value1, value2, "isQualifiedInspectionRecord");
            return (Criteria) this;
        }

        public Criteria andIsQualifiedInspectionRecordNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_qualified_inspection_record not between", value1, value2, "isQualifiedInspectionRecord");
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