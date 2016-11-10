package com.cana.asset.dao.po;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AssetInvoiceInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int limitStart = -1;

    protected int limitEnd = -1;

    public AssetInvoiceInfoExample() {
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

        public Criteria andIdEqualTo(String value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("id like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("id not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andInvoiceBaseIdIsNull() {
            addCriterion("invoice_base_id is null");
            return (Criteria) this;
        }

        public Criteria andInvoiceBaseIdIsNotNull() {
            addCriterion("invoice_base_id is not null");
            return (Criteria) this;
        }

        public Criteria andInvoiceBaseIdEqualTo(String value) {
            addCriterion("invoice_base_id =", value, "invoiceBaseId");
            return (Criteria) this;
        }

        public Criteria andInvoiceBaseIdNotEqualTo(String value) {
            addCriterion("invoice_base_id <>", value, "invoiceBaseId");
            return (Criteria) this;
        }

        public Criteria andInvoiceBaseIdGreaterThan(String value) {
            addCriterion("invoice_base_id >", value, "invoiceBaseId");
            return (Criteria) this;
        }

        public Criteria andInvoiceBaseIdGreaterThanOrEqualTo(String value) {
            addCriterion("invoice_base_id >=", value, "invoiceBaseId");
            return (Criteria) this;
        }

        public Criteria andInvoiceBaseIdLessThan(String value) {
            addCriterion("invoice_base_id <", value, "invoiceBaseId");
            return (Criteria) this;
        }

        public Criteria andInvoiceBaseIdLessThanOrEqualTo(String value) {
            addCriterion("invoice_base_id <=", value, "invoiceBaseId");
            return (Criteria) this;
        }

        public Criteria andInvoiceBaseIdLike(String value) {
            addCriterion("invoice_base_id like", value, "invoiceBaseId");
            return (Criteria) this;
        }

        public Criteria andInvoiceBaseIdNotLike(String value) {
            addCriterion("invoice_base_id not like", value, "invoiceBaseId");
            return (Criteria) this;
        }

        public Criteria andInvoiceBaseIdIn(List<String> values) {
            addCriterion("invoice_base_id in", values, "invoiceBaseId");
            return (Criteria) this;
        }

        public Criteria andInvoiceBaseIdNotIn(List<String> values) {
            addCriterion("invoice_base_id not in", values, "invoiceBaseId");
            return (Criteria) this;
        }

        public Criteria andInvoiceBaseIdBetween(String value1, String value2) {
            addCriterion("invoice_base_id between", value1, value2, "invoiceBaseId");
            return (Criteria) this;
        }

        public Criteria andInvoiceBaseIdNotBetween(String value1, String value2) {
            addCriterion("invoice_base_id not between", value1, value2, "invoiceBaseId");
            return (Criteria) this;
        }

        public Criteria andCounterpartyIdIsNull() {
            addCriterion("counterparty_id is null");
            return (Criteria) this;
        }

        public Criteria andCounterpartyIdIsNotNull() {
            addCriterion("counterparty_id is not null");
            return (Criteria) this;
        }

        public Criteria andCounterpartyIdEqualTo(String value) {
            addCriterion("counterparty_id =", value, "counterpartyId");
            return (Criteria) this;
        }

        public Criteria andCounterpartyIdNotEqualTo(String value) {
            addCriterion("counterparty_id <>", value, "counterpartyId");
            return (Criteria) this;
        }

        public Criteria andCounterpartyIdGreaterThan(String value) {
            addCriterion("counterparty_id >", value, "counterpartyId");
            return (Criteria) this;
        }

        public Criteria andCounterpartyIdGreaterThanOrEqualTo(String value) {
            addCriterion("counterparty_id >=", value, "counterpartyId");
            return (Criteria) this;
        }

        public Criteria andCounterpartyIdLessThan(String value) {
            addCriterion("counterparty_id <", value, "counterpartyId");
            return (Criteria) this;
        }

        public Criteria andCounterpartyIdLessThanOrEqualTo(String value) {
            addCriterion("counterparty_id <=", value, "counterpartyId");
            return (Criteria) this;
        }

        public Criteria andCounterpartyIdLike(String value) {
            addCriterion("counterparty_id like", value, "counterpartyId");
            return (Criteria) this;
        }

        public Criteria andCounterpartyIdNotLike(String value) {
            addCriterion("counterparty_id not like", value, "counterpartyId");
            return (Criteria) this;
        }

        public Criteria andCounterpartyIdIn(List<String> values) {
            addCriterion("counterparty_id in", values, "counterpartyId");
            return (Criteria) this;
        }

        public Criteria andCounterpartyIdNotIn(List<String> values) {
            addCriterion("counterparty_id not in", values, "counterpartyId");
            return (Criteria) this;
        }

        public Criteria andCounterpartyIdBetween(String value1, String value2) {
            addCriterion("counterparty_id between", value1, value2, "counterpartyId");
            return (Criteria) this;
        }

        public Criteria andCounterpartyIdNotBetween(String value1, String value2) {
            addCriterion("counterparty_id not between", value1, value2, "counterpartyId");
            return (Criteria) this;
        }

        public Criteria andCounterpartyIsNull() {
            addCriterion("counterparty is null");
            return (Criteria) this;
        }

        public Criteria andCounterpartyIsNotNull() {
            addCriterion("counterparty is not null");
            return (Criteria) this;
        }

        public Criteria andCounterpartyEqualTo(String value) {
            addCriterion("counterparty =", value, "counterparty");
            return (Criteria) this;
        }

        public Criteria andCounterpartyNotEqualTo(String value) {
            addCriterion("counterparty <>", value, "counterparty");
            return (Criteria) this;
        }

        public Criteria andCounterpartyGreaterThan(String value) {
            addCriterion("counterparty >", value, "counterparty");
            return (Criteria) this;
        }

        public Criteria andCounterpartyGreaterThanOrEqualTo(String value) {
            addCriterion("counterparty >=", value, "counterparty");
            return (Criteria) this;
        }

        public Criteria andCounterpartyLessThan(String value) {
            addCriterion("counterparty <", value, "counterparty");
            return (Criteria) this;
        }

        public Criteria andCounterpartyLessThanOrEqualTo(String value) {
            addCriterion("counterparty <=", value, "counterparty");
            return (Criteria) this;
        }

        public Criteria andCounterpartyLike(String value) {
            addCriterion("counterparty like", value, "counterparty");
            return (Criteria) this;
        }

        public Criteria andCounterpartyNotLike(String value) {
            addCriterion("counterparty not like", value, "counterparty");
            return (Criteria) this;
        }

        public Criteria andCounterpartyIn(List<String> values) {
            addCriterion("counterparty in", values, "counterparty");
            return (Criteria) this;
        }

        public Criteria andCounterpartyNotIn(List<String> values) {
            addCriterion("counterparty not in", values, "counterparty");
            return (Criteria) this;
        }

        public Criteria andCounterpartyBetween(String value1, String value2) {
            addCriterion("counterparty between", value1, value2, "counterparty");
            return (Criteria) this;
        }

        public Criteria andCounterpartyNotBetween(String value1, String value2) {
            addCriterion("counterparty not between", value1, value2, "counterparty");
            return (Criteria) this;
        }

        public Criteria andInvoiceNoIsNull() {
            addCriterion("invoice_no is null");
            return (Criteria) this;
        }

        public Criteria andInvoiceNoIsNotNull() {
            addCriterion("invoice_no is not null");
            return (Criteria) this;
        }

        public Criteria andInvoiceNoEqualTo(String value) {
            addCriterion("invoice_no =", value, "invoiceNo");
            return (Criteria) this;
        }

        public Criteria andInvoiceNoNotEqualTo(String value) {
            addCriterion("invoice_no <>", value, "invoiceNo");
            return (Criteria) this;
        }

        public Criteria andInvoiceNoGreaterThan(String value) {
            addCriterion("invoice_no >", value, "invoiceNo");
            return (Criteria) this;
        }

        public Criteria andInvoiceNoGreaterThanOrEqualTo(String value) {
            addCriterion("invoice_no >=", value, "invoiceNo");
            return (Criteria) this;
        }

        public Criteria andInvoiceNoLessThan(String value) {
            addCriterion("invoice_no <", value, "invoiceNo");
            return (Criteria) this;
        }

        public Criteria andInvoiceNoLessThanOrEqualTo(String value) {
            addCriterion("invoice_no <=", value, "invoiceNo");
            return (Criteria) this;
        }

        public Criteria andInvoiceNoLike(String value) {
            addCriterion("invoice_no like", value, "invoiceNo");
            return (Criteria) this;
        }

        public Criteria andInvoiceNoNotLike(String value) {
            addCriterion("invoice_no not like", value, "invoiceNo");
            return (Criteria) this;
        }

        public Criteria andInvoiceNoIn(List<String> values) {
            addCriterion("invoice_no in", values, "invoiceNo");
            return (Criteria) this;
        }

        public Criteria andInvoiceNoNotIn(List<String> values) {
            addCriterion("invoice_no not in", values, "invoiceNo");
            return (Criteria) this;
        }

        public Criteria andInvoiceNoBetween(String value1, String value2) {
            addCriterion("invoice_no between", value1, value2, "invoiceNo");
            return (Criteria) this;
        }

        public Criteria andInvoiceNoNotBetween(String value1, String value2) {
            addCriterion("invoice_no not between", value1, value2, "invoiceNo");
            return (Criteria) this;
        }

        public Criteria andNominvoiceAmtIsNull() {
            addCriterion("nominvoice_amt is null");
            return (Criteria) this;
        }

        public Criteria andNominvoiceAmtIsNotNull() {
            addCriterion("nominvoice_amt is not null");
            return (Criteria) this;
        }

        public Criteria andNominvoiceAmtEqualTo(Long value) {
            addCriterion("nominvoice_amt =", value, "nominvoiceAmt");
            return (Criteria) this;
        }

        public Criteria andNominvoiceAmtNotEqualTo(Long value) {
            addCriterion("nominvoice_amt <>", value, "nominvoiceAmt");
            return (Criteria) this;
        }

        public Criteria andNominvoiceAmtGreaterThan(Long value) {
            addCriterion("nominvoice_amt >", value, "nominvoiceAmt");
            return (Criteria) this;
        }

        public Criteria andNominvoiceAmtGreaterThanOrEqualTo(Long value) {
            addCriterion("nominvoice_amt >=", value, "nominvoiceAmt");
            return (Criteria) this;
        }

        public Criteria andNominvoiceAmtLessThan(Long value) {
            addCriterion("nominvoice_amt <", value, "nominvoiceAmt");
            return (Criteria) this;
        }

        public Criteria andNominvoiceAmtLessThanOrEqualTo(Long value) {
            addCriterion("nominvoice_amt <=", value, "nominvoiceAmt");
            return (Criteria) this;
        }

        public Criteria andNominvoiceAmtIn(List<Long> values) {
            addCriterion("nominvoice_amt in", values, "nominvoiceAmt");
            return (Criteria) this;
        }

        public Criteria andNominvoiceAmtNotIn(List<Long> values) {
            addCriterion("nominvoice_amt not in", values, "nominvoiceAmt");
            return (Criteria) this;
        }

        public Criteria andNominvoiceAmtBetween(Long value1, Long value2) {
            addCriterion("nominvoice_amt between", value1, value2, "nominvoiceAmt");
            return (Criteria) this;
        }

        public Criteria andNominvoiceAmtNotBetween(Long value1, Long value2) {
            addCriterion("nominvoice_amt not between", value1, value2, "nominvoiceAmt");
            return (Criteria) this;
        }

        public Criteria andInvoiceAmtIsNull() {
            addCriterion("invoice_amt is null");
            return (Criteria) this;
        }

        public Criteria andInvoiceAmtIsNotNull() {
            addCriterion("invoice_amt is not null");
            return (Criteria) this;
        }

        public Criteria andInvoiceAmtEqualTo(Long value) {
            addCriterion("invoice_amt =", value, "invoiceAmt");
            return (Criteria) this;
        }

        public Criteria andInvoiceAmtNotEqualTo(Long value) {
            addCriterion("invoice_amt <>", value, "invoiceAmt");
            return (Criteria) this;
        }

        public Criteria andInvoiceAmtGreaterThan(Long value) {
            addCriterion("invoice_amt >", value, "invoiceAmt");
            return (Criteria) this;
        }

        public Criteria andInvoiceAmtGreaterThanOrEqualTo(Long value) {
            addCriterion("invoice_amt >=", value, "invoiceAmt");
            return (Criteria) this;
        }

        public Criteria andInvoiceAmtLessThan(Long value) {
            addCriterion("invoice_amt <", value, "invoiceAmt");
            return (Criteria) this;
        }

        public Criteria andInvoiceAmtLessThanOrEqualTo(Long value) {
            addCriterion("invoice_amt <=", value, "invoiceAmt");
            return (Criteria) this;
        }

        public Criteria andInvoiceAmtIn(List<Long> values) {
            addCriterion("invoice_amt in", values, "invoiceAmt");
            return (Criteria) this;
        }

        public Criteria andInvoiceAmtNotIn(List<Long> values) {
            addCriterion("invoice_amt not in", values, "invoiceAmt");
            return (Criteria) this;
        }

        public Criteria andInvoiceAmtBetween(Long value1, Long value2) {
            addCriterion("invoice_amt between", value1, value2, "invoiceAmt");
            return (Criteria) this;
        }

        public Criteria andInvoiceAmtNotBetween(Long value1, Long value2) {
            addCriterion("invoice_amt not between", value1, value2, "invoiceAmt");
            return (Criteria) this;
        }

        public Criteria andFinancingRatioIsNull() {
            addCriterion("financing_ratio is null");
            return (Criteria) this;
        }

        public Criteria andFinancingRatioIsNotNull() {
            addCriterion("financing_ratio is not null");
            return (Criteria) this;
        }

        public Criteria andFinancingRatioEqualTo(BigDecimal value) {
            addCriterion("financing_ratio =", value, "financingRatio");
            return (Criteria) this;
        }

        public Criteria andFinancingRatioNotEqualTo(BigDecimal value) {
            addCriterion("financing_ratio <>", value, "financingRatio");
            return (Criteria) this;
        }

        public Criteria andFinancingRatioGreaterThan(BigDecimal value) {
            addCriterion("financing_ratio >", value, "financingRatio");
            return (Criteria) this;
        }

        public Criteria andFinancingRatioGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("financing_ratio >=", value, "financingRatio");
            return (Criteria) this;
        }

        public Criteria andFinancingRatioLessThan(BigDecimal value) {
            addCriterion("financing_ratio <", value, "financingRatio");
            return (Criteria) this;
        }

        public Criteria andFinancingRatioLessThanOrEqualTo(BigDecimal value) {
            addCriterion("financing_ratio <=", value, "financingRatio");
            return (Criteria) this;
        }

        public Criteria andFinancingRatioIn(List<BigDecimal> values) {
            addCriterion("financing_ratio in", values, "financingRatio");
            return (Criteria) this;
        }

        public Criteria andFinancingRatioNotIn(List<BigDecimal> values) {
            addCriterion("financing_ratio not in", values, "financingRatio");
            return (Criteria) this;
        }

        public Criteria andFinancingRatioBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("financing_ratio between", value1, value2, "financingRatio");
            return (Criteria) this;
        }

        public Criteria andFinancingRatioNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("financing_ratio not between", value1, value2, "financingRatio");
            return (Criteria) this;
        }

        public Criteria andInvoiceDateIsNull() {
            addCriterion("invoice_date is null");
            return (Criteria) this;
        }

        public Criteria andInvoiceDateIsNotNull() {
            addCriterion("invoice_date is not null");
            return (Criteria) this;
        }

        public Criteria andInvoiceDateEqualTo(String value) {
            addCriterion("invoice_date =", value, "invoiceDate");
            return (Criteria) this;
        }

        public Criteria andInvoiceDateNotEqualTo(String value) {
            addCriterion("invoice_date <>", value, "invoiceDate");
            return (Criteria) this;
        }

        public Criteria andInvoiceDateGreaterThan(String value) {
            addCriterion("invoice_date >", value, "invoiceDate");
            return (Criteria) this;
        }

        public Criteria andInvoiceDateGreaterThanOrEqualTo(String value) {
            addCriterion("invoice_date >=", value, "invoiceDate");
            return (Criteria) this;
        }

        public Criteria andInvoiceDateLessThan(String value) {
            addCriterion("invoice_date <", value, "invoiceDate");
            return (Criteria) this;
        }

        public Criteria andInvoiceDateLessThanOrEqualTo(String value) {
            addCriterion("invoice_date <=", value, "invoiceDate");
            return (Criteria) this;
        }

        public Criteria andInvoiceDateLike(String value) {
            addCriterion("invoice_date like", value, "invoiceDate");
            return (Criteria) this;
        }

        public Criteria andInvoiceDateNotLike(String value) {
            addCriterion("invoice_date not like", value, "invoiceDate");
            return (Criteria) this;
        }

        public Criteria andInvoiceDateIn(List<String> values) {
            addCriterion("invoice_date in", values, "invoiceDate");
            return (Criteria) this;
        }

        public Criteria andInvoiceDateNotIn(List<String> values) {
            addCriterion("invoice_date not in", values, "invoiceDate");
            return (Criteria) this;
        }

        public Criteria andInvoiceDateBetween(String value1, String value2) {
            addCriterion("invoice_date between", value1, value2, "invoiceDate");
            return (Criteria) this;
        }

        public Criteria andInvoiceDateNotBetween(String value1, String value2) {
            addCriterion("invoice_date not between", value1, value2, "invoiceDate");
            return (Criteria) this;
        }

        public Criteria andDueDateIsNull() {
            addCriterion("due_date is null");
            return (Criteria) this;
        }

        public Criteria andDueDateIsNotNull() {
            addCriterion("due_date is not null");
            return (Criteria) this;
        }

        public Criteria andDueDateEqualTo(String value) {
            addCriterion("due_date =", value, "dueDate");
            return (Criteria) this;
        }

        public Criteria andDueDateNotEqualTo(String value) {
            addCriterion("due_date <>", value, "dueDate");
            return (Criteria) this;
        }

        public Criteria andDueDateGreaterThan(String value) {
            addCriterion("due_date >", value, "dueDate");
            return (Criteria) this;
        }

        public Criteria andDueDateGreaterThanOrEqualTo(String value) {
            addCriterion("due_date >=", value, "dueDate");
            return (Criteria) this;
        }

        public Criteria andDueDateLessThan(String value) {
            addCriterion("due_date <", value, "dueDate");
            return (Criteria) this;
        }

        public Criteria andDueDateLessThanOrEqualTo(String value) {
            addCriterion("due_date <=", value, "dueDate");
            return (Criteria) this;
        }

        public Criteria andDueDateLike(String value) {
            addCriterion("due_date like", value, "dueDate");
            return (Criteria) this;
        }

        public Criteria andDueDateNotLike(String value) {
            addCriterion("due_date not like", value, "dueDate");
            return (Criteria) this;
        }

        public Criteria andDueDateIn(List<String> values) {
            addCriterion("due_date in", values, "dueDate");
            return (Criteria) this;
        }

        public Criteria andDueDateNotIn(List<String> values) {
            addCriterion("due_date not in", values, "dueDate");
            return (Criteria) this;
        }

        public Criteria andDueDateBetween(String value1, String value2) {
            addCriterion("due_date between", value1, value2, "dueDate");
            return (Criteria) this;
        }

        public Criteria andDueDateNotBetween(String value1, String value2) {
            addCriterion("due_date not between", value1, value2, "dueDate");
            return (Criteria) this;
        }

        public Criteria andLoanInfoIdIsNull() {
            addCriterion("loan_info_id is null");
            return (Criteria) this;
        }

        public Criteria andLoanInfoIdIsNotNull() {
            addCriterion("loan_info_id is not null");
            return (Criteria) this;
        }

        public Criteria andLoanInfoIdEqualTo(String value) {
            addCriterion("loan_info_id =", value, "loanInfoId");
            return (Criteria) this;
        }

        public Criteria andLoanInfoIdNotEqualTo(String value) {
            addCriterion("loan_info_id <>", value, "loanInfoId");
            return (Criteria) this;
        }

        public Criteria andLoanInfoIdGreaterThan(String value) {
            addCriterion("loan_info_id >", value, "loanInfoId");
            return (Criteria) this;
        }

        public Criteria andLoanInfoIdGreaterThanOrEqualTo(String value) {
            addCriterion("loan_info_id >=", value, "loanInfoId");
            return (Criteria) this;
        }

        public Criteria andLoanInfoIdLessThan(String value) {
            addCriterion("loan_info_id <", value, "loanInfoId");
            return (Criteria) this;
        }

        public Criteria andLoanInfoIdLessThanOrEqualTo(String value) {
            addCriterion("loan_info_id <=", value, "loanInfoId");
            return (Criteria) this;
        }

        public Criteria andLoanInfoIdLike(String value) {
            addCriterion("loan_info_id like", value, "loanInfoId");
            return (Criteria) this;
        }

        public Criteria andLoanInfoIdNotLike(String value) {
            addCriterion("loan_info_id not like", value, "loanInfoId");
            return (Criteria) this;
        }

        public Criteria andLoanInfoIdIn(List<String> values) {
            addCriterion("loan_info_id in", values, "loanInfoId");
            return (Criteria) this;
        }

        public Criteria andLoanInfoIdNotIn(List<String> values) {
            addCriterion("loan_info_id not in", values, "loanInfoId");
            return (Criteria) this;
        }

        public Criteria andLoanInfoIdBetween(String value1, String value2) {
            addCriterion("loan_info_id between", value1, value2, "loanInfoId");
            return (Criteria) this;
        }

        public Criteria andLoanInfoIdNotBetween(String value1, String value2) {
            addCriterion("loan_info_id not between", value1, value2, "loanInfoId");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
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