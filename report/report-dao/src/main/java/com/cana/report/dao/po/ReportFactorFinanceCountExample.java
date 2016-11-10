package com.cana.report.dao.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReportFactorFinanceCountExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int limitStart = -1;

    protected int limitEnd = -1;

    public ReportFactorFinanceCountExample() {
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

        public Criteria andOwnerIdIsNull() {
            addCriterion("owner_id is null");
            return (Criteria) this;
        }

        public Criteria andOwnerIdIsNotNull() {
            addCriterion("owner_id is not null");
            return (Criteria) this;
        }

        public Criteria andOwnerIdEqualTo(String value) {
            addCriterion("owner_id =", value, "ownerId");
            return (Criteria) this;
        }

        public Criteria andOwnerIdNotEqualTo(String value) {
            addCriterion("owner_id <>", value, "ownerId");
            return (Criteria) this;
        }

        public Criteria andOwnerIdGreaterThan(String value) {
            addCriterion("owner_id >", value, "ownerId");
            return (Criteria) this;
        }

        public Criteria andOwnerIdGreaterThanOrEqualTo(String value) {
            addCriterion("owner_id >=", value, "ownerId");
            return (Criteria) this;
        }

        public Criteria andOwnerIdLessThan(String value) {
            addCriterion("owner_id <", value, "ownerId");
            return (Criteria) this;
        }

        public Criteria andOwnerIdLessThanOrEqualTo(String value) {
            addCriterion("owner_id <=", value, "ownerId");
            return (Criteria) this;
        }

        public Criteria andOwnerIdLike(String value) {
            addCriterion("owner_id like", value, "ownerId");
            return (Criteria) this;
        }

        public Criteria andOwnerIdNotLike(String value) {
            addCriterion("owner_id not like", value, "ownerId");
            return (Criteria) this;
        }

        public Criteria andOwnerIdIn(List<String> values) {
            addCriterion("owner_id in", values, "ownerId");
            return (Criteria) this;
        }

        public Criteria andOwnerIdNotIn(List<String> values) {
            addCriterion("owner_id not in", values, "ownerId");
            return (Criteria) this;
        }

        public Criteria andOwnerIdBetween(String value1, String value2) {
            addCriterion("owner_id between", value1, value2, "ownerId");
            return (Criteria) this;
        }

        public Criteria andOwnerIdNotBetween(String value1, String value2) {
            addCriterion("owner_id not between", value1, value2, "ownerId");
            return (Criteria) this;
        }

        public Criteria andReportDateIsNull() {
            addCriterion("report_date is null");
            return (Criteria) this;
        }

        public Criteria andReportDateIsNotNull() {
            addCriterion("report_date is not null");
            return (Criteria) this;
        }

        public Criteria andReportDateEqualTo(String value) {
            addCriterion("report_date =", value, "reportDate");
            return (Criteria) this;
        }

        public Criteria andReportDateNotEqualTo(String value) {
            addCriterion("report_date <>", value, "reportDate");
            return (Criteria) this;
        }

        public Criteria andReportDateGreaterThan(String value) {
            addCriterion("report_date >", value, "reportDate");
            return (Criteria) this;
        }

        public Criteria andReportDateGreaterThanOrEqualTo(String value) {
            addCriterion("report_date >=", value, "reportDate");
            return (Criteria) this;
        }

        public Criteria andReportDateLessThan(String value) {
            addCriterion("report_date <", value, "reportDate");
            return (Criteria) this;
        }

        public Criteria andReportDateLessThanOrEqualTo(String value) {
            addCriterion("report_date <=", value, "reportDate");
            return (Criteria) this;
        }

        public Criteria andReportDateLike(String value) {
            addCriterion("report_date like", value, "reportDate");
            return (Criteria) this;
        }

        public Criteria andReportDateNotLike(String value) {
            addCriterion("report_date not like", value, "reportDate");
            return (Criteria) this;
        }

        public Criteria andReportDateIn(List<String> values) {
            addCriterion("report_date in", values, "reportDate");
            return (Criteria) this;
        }

        public Criteria andReportDateNotIn(List<String> values) {
            addCriterion("report_date not in", values, "reportDate");
            return (Criteria) this;
        }

        public Criteria andReportDateBetween(String value1, String value2) {
            addCriterion("report_date between", value1, value2, "reportDate");
            return (Criteria) this;
        }

        public Criteria andReportDateNotBetween(String value1, String value2) {
            addCriterion("report_date not between", value1, value2, "reportDate");
            return (Criteria) this;
        }

        public Criteria andUserTypeIsNull() {
            addCriterion("user_type is null");
            return (Criteria) this;
        }

        public Criteria andUserTypeIsNotNull() {
            addCriterion("user_type is not null");
            return (Criteria) this;
        }

        public Criteria andUserTypeEqualTo(String value) {
            addCriterion("user_type =", value, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeNotEqualTo(String value) {
            addCriterion("user_type <>", value, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeGreaterThan(String value) {
            addCriterion("user_type >", value, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeGreaterThanOrEqualTo(String value) {
            addCriterion("user_type >=", value, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeLessThan(String value) {
            addCriterion("user_type <", value, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeLessThanOrEqualTo(String value) {
            addCriterion("user_type <=", value, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeLike(String value) {
            addCriterion("user_type like", value, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeNotLike(String value) {
            addCriterion("user_type not like", value, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeIn(List<String> values) {
            addCriterion("user_type in", values, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeNotIn(List<String> values) {
            addCriterion("user_type not in", values, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeBetween(String value1, String value2) {
            addCriterion("user_type between", value1, value2, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeNotBetween(String value1, String value2) {
            addCriterion("user_type not between", value1, value2, "userType");
            return (Criteria) this;
        }

        public Criteria andBusinessProductIdIsNull() {
            addCriterion("business_product_id is null");
            return (Criteria) this;
        }

        public Criteria andBusinessProductIdIsNotNull() {
            addCriterion("business_product_id is not null");
            return (Criteria) this;
        }

        public Criteria andBusinessProductIdEqualTo(String value) {
            addCriterion("business_product_id =", value, "businessProductId");
            return (Criteria) this;
        }

        public Criteria andBusinessProductIdNotEqualTo(String value) {
            addCriterion("business_product_id <>", value, "businessProductId");
            return (Criteria) this;
        }

        public Criteria andBusinessProductIdGreaterThan(String value) {
            addCriterion("business_product_id >", value, "businessProductId");
            return (Criteria) this;
        }

        public Criteria andBusinessProductIdGreaterThanOrEqualTo(String value) {
            addCriterion("business_product_id >=", value, "businessProductId");
            return (Criteria) this;
        }

        public Criteria andBusinessProductIdLessThan(String value) {
            addCriterion("business_product_id <", value, "businessProductId");
            return (Criteria) this;
        }

        public Criteria andBusinessProductIdLessThanOrEqualTo(String value) {
            addCriterion("business_product_id <=", value, "businessProductId");
            return (Criteria) this;
        }

        public Criteria andBusinessProductIdLike(String value) {
            addCriterion("business_product_id like", value, "businessProductId");
            return (Criteria) this;
        }

        public Criteria andBusinessProductIdNotLike(String value) {
            addCriterion("business_product_id not like", value, "businessProductId");
            return (Criteria) this;
        }

        public Criteria andBusinessProductIdIn(List<String> values) {
            addCriterion("business_product_id in", values, "businessProductId");
            return (Criteria) this;
        }

        public Criteria andBusinessProductIdNotIn(List<String> values) {
            addCriterion("business_product_id not in", values, "businessProductId");
            return (Criteria) this;
        }

        public Criteria andBusinessProductIdBetween(String value1, String value2) {
            addCriterion("business_product_id between", value1, value2, "businessProductId");
            return (Criteria) this;
        }

        public Criteria andBusinessProductIdNotBetween(String value1, String value2) {
            addCriterion("business_product_id not between", value1, value2, "businessProductId");
            return (Criteria) this;
        }

        public Criteria andLoanItemsIsNull() {
            addCriterion("loan_items is null");
            return (Criteria) this;
        }

        public Criteria andLoanItemsIsNotNull() {
            addCriterion("loan_items is not null");
            return (Criteria) this;
        }

        public Criteria andLoanItemsEqualTo(Integer value) {
            addCriterion("loan_items =", value, "loanItems");
            return (Criteria) this;
        }

        public Criteria andLoanItemsNotEqualTo(Integer value) {
            addCriterion("loan_items <>", value, "loanItems");
            return (Criteria) this;
        }

        public Criteria andLoanItemsGreaterThan(Integer value) {
            addCriterion("loan_items >", value, "loanItems");
            return (Criteria) this;
        }

        public Criteria andLoanItemsGreaterThanOrEqualTo(Integer value) {
            addCriterion("loan_items >=", value, "loanItems");
            return (Criteria) this;
        }

        public Criteria andLoanItemsLessThan(Integer value) {
            addCriterion("loan_items <", value, "loanItems");
            return (Criteria) this;
        }

        public Criteria andLoanItemsLessThanOrEqualTo(Integer value) {
            addCriterion("loan_items <=", value, "loanItems");
            return (Criteria) this;
        }

        public Criteria andLoanItemsIn(List<Integer> values) {
            addCriterion("loan_items in", values, "loanItems");
            return (Criteria) this;
        }

        public Criteria andLoanItemsNotIn(List<Integer> values) {
            addCriterion("loan_items not in", values, "loanItems");
            return (Criteria) this;
        }

        public Criteria andLoanItemsBetween(Integer value1, Integer value2) {
            addCriterion("loan_items between", value1, value2, "loanItems");
            return (Criteria) this;
        }

        public Criteria andLoanItemsNotBetween(Integer value1, Integer value2) {
            addCriterion("loan_items not between", value1, value2, "loanItems");
            return (Criteria) this;
        }

        public Criteria andOverdueItemsIsNull() {
            addCriterion("overdue_items is null");
            return (Criteria) this;
        }

        public Criteria andOverdueItemsIsNotNull() {
            addCriterion("overdue_items is not null");
            return (Criteria) this;
        }

        public Criteria andOverdueItemsEqualTo(Integer value) {
            addCriterion("overdue_items =", value, "overdueItems");
            return (Criteria) this;
        }

        public Criteria andOverdueItemsNotEqualTo(Integer value) {
            addCriterion("overdue_items <>", value, "overdueItems");
            return (Criteria) this;
        }

        public Criteria andOverdueItemsGreaterThan(Integer value) {
            addCriterion("overdue_items >", value, "overdueItems");
            return (Criteria) this;
        }

        public Criteria andOverdueItemsGreaterThanOrEqualTo(Integer value) {
            addCriterion("overdue_items >=", value, "overdueItems");
            return (Criteria) this;
        }

        public Criteria andOverdueItemsLessThan(Integer value) {
            addCriterion("overdue_items <", value, "overdueItems");
            return (Criteria) this;
        }

        public Criteria andOverdueItemsLessThanOrEqualTo(Integer value) {
            addCriterion("overdue_items <=", value, "overdueItems");
            return (Criteria) this;
        }

        public Criteria andOverdueItemsIn(List<Integer> values) {
            addCriterion("overdue_items in", values, "overdueItems");
            return (Criteria) this;
        }

        public Criteria andOverdueItemsNotIn(List<Integer> values) {
            addCriterion("overdue_items not in", values, "overdueItems");
            return (Criteria) this;
        }

        public Criteria andOverdueItemsBetween(Integer value1, Integer value2) {
            addCriterion("overdue_items between", value1, value2, "overdueItems");
            return (Criteria) this;
        }

        public Criteria andOverdueItemsNotBetween(Integer value1, Integer value2) {
            addCriterion("overdue_items not between", value1, value2, "overdueItems");
            return (Criteria) this;
        }

        public Criteria andExtensionItemsIsNull() {
            addCriterion("extension_items is null");
            return (Criteria) this;
        }

        public Criteria andExtensionItemsIsNotNull() {
            addCriterion("extension_items is not null");
            return (Criteria) this;
        }

        public Criteria andExtensionItemsEqualTo(Integer value) {
            addCriterion("extension_items =", value, "extensionItems");
            return (Criteria) this;
        }

        public Criteria andExtensionItemsNotEqualTo(Integer value) {
            addCriterion("extension_items <>", value, "extensionItems");
            return (Criteria) this;
        }

        public Criteria andExtensionItemsGreaterThan(Integer value) {
            addCriterion("extension_items >", value, "extensionItems");
            return (Criteria) this;
        }

        public Criteria andExtensionItemsGreaterThanOrEqualTo(Integer value) {
            addCriterion("extension_items >=", value, "extensionItems");
            return (Criteria) this;
        }

        public Criteria andExtensionItemsLessThan(Integer value) {
            addCriterion("extension_items <", value, "extensionItems");
            return (Criteria) this;
        }

        public Criteria andExtensionItemsLessThanOrEqualTo(Integer value) {
            addCriterion("extension_items <=", value, "extensionItems");
            return (Criteria) this;
        }

        public Criteria andExtensionItemsIn(List<Integer> values) {
            addCriterion("extension_items in", values, "extensionItems");
            return (Criteria) this;
        }

        public Criteria andExtensionItemsNotIn(List<Integer> values) {
            addCriterion("extension_items not in", values, "extensionItems");
            return (Criteria) this;
        }

        public Criteria andExtensionItemsBetween(Integer value1, Integer value2) {
            addCriterion("extension_items between", value1, value2, "extensionItems");
            return (Criteria) this;
        }

        public Criteria andExtensionItemsNotBetween(Integer value1, Integer value2) {
            addCriterion("extension_items not between", value1, value2, "extensionItems");
            return (Criteria) this;
        }

        public Criteria andRepaymentItemsIsNull() {
            addCriterion("repayment_items is null");
            return (Criteria) this;
        }

        public Criteria andRepaymentItemsIsNotNull() {
            addCriterion("repayment_items is not null");
            return (Criteria) this;
        }

        public Criteria andRepaymentItemsEqualTo(Integer value) {
            addCriterion("repayment_items =", value, "repaymentItems");
            return (Criteria) this;
        }

        public Criteria andRepaymentItemsNotEqualTo(Integer value) {
            addCriterion("repayment_items <>", value, "repaymentItems");
            return (Criteria) this;
        }

        public Criteria andRepaymentItemsGreaterThan(Integer value) {
            addCriterion("repayment_items >", value, "repaymentItems");
            return (Criteria) this;
        }

        public Criteria andRepaymentItemsGreaterThanOrEqualTo(Integer value) {
            addCriterion("repayment_items >=", value, "repaymentItems");
            return (Criteria) this;
        }

        public Criteria andRepaymentItemsLessThan(Integer value) {
            addCriterion("repayment_items <", value, "repaymentItems");
            return (Criteria) this;
        }

        public Criteria andRepaymentItemsLessThanOrEqualTo(Integer value) {
            addCriterion("repayment_items <=", value, "repaymentItems");
            return (Criteria) this;
        }

        public Criteria andRepaymentItemsIn(List<Integer> values) {
            addCriterion("repayment_items in", values, "repaymentItems");
            return (Criteria) this;
        }

        public Criteria andRepaymentItemsNotIn(List<Integer> values) {
            addCriterion("repayment_items not in", values, "repaymentItems");
            return (Criteria) this;
        }

        public Criteria andRepaymentItemsBetween(Integer value1, Integer value2) {
            addCriterion("repayment_items between", value1, value2, "repaymentItems");
            return (Criteria) this;
        }

        public Criteria andRepaymentItemsNotBetween(Integer value1, Integer value2) {
            addCriterion("repayment_items not between", value1, value2, "repaymentItems");
            return (Criteria) this;
        }

        public Criteria andAdjustItemsIsNull() {
            addCriterion("adjust_items is null");
            return (Criteria) this;
        }

        public Criteria andAdjustItemsIsNotNull() {
            addCriterion("adjust_items is not null");
            return (Criteria) this;
        }

        public Criteria andAdjustItemsEqualTo(Integer value) {
            addCriterion("adjust_items =", value, "adjustItems");
            return (Criteria) this;
        }

        public Criteria andAdjustItemsNotEqualTo(Integer value) {
            addCriterion("adjust_items <>", value, "adjustItems");
            return (Criteria) this;
        }

        public Criteria andAdjustItemsGreaterThan(Integer value) {
            addCriterion("adjust_items >", value, "adjustItems");
            return (Criteria) this;
        }

        public Criteria andAdjustItemsGreaterThanOrEqualTo(Integer value) {
            addCriterion("adjust_items >=", value, "adjustItems");
            return (Criteria) this;
        }

        public Criteria andAdjustItemsLessThan(Integer value) {
            addCriterion("adjust_items <", value, "adjustItems");
            return (Criteria) this;
        }

        public Criteria andAdjustItemsLessThanOrEqualTo(Integer value) {
            addCriterion("adjust_items <=", value, "adjustItems");
            return (Criteria) this;
        }

        public Criteria andAdjustItemsIn(List<Integer> values) {
            addCriterion("adjust_items in", values, "adjustItems");
            return (Criteria) this;
        }

        public Criteria andAdjustItemsNotIn(List<Integer> values) {
            addCriterion("adjust_items not in", values, "adjustItems");
            return (Criteria) this;
        }

        public Criteria andAdjustItemsBetween(Integer value1, Integer value2) {
            addCriterion("adjust_items between", value1, value2, "adjustItems");
            return (Criteria) this;
        }

        public Criteria andAdjustItemsNotBetween(Integer value1, Integer value2) {
            addCriterion("adjust_items not between", value1, value2, "adjustItems");
            return (Criteria) this;
        }

        public Criteria andOverdueCustomerIsNull() {
            addCriterion("overdue_customer is null");
            return (Criteria) this;
        }

        public Criteria andOverdueCustomerIsNotNull() {
            addCriterion("overdue_customer is not null");
            return (Criteria) this;
        }

        public Criteria andOverdueCustomerEqualTo(Integer value) {
            addCriterion("overdue_customer =", value, "overdueCustomer");
            return (Criteria) this;
        }

        public Criteria andOverdueCustomerNotEqualTo(Integer value) {
            addCriterion("overdue_customer <>", value, "overdueCustomer");
            return (Criteria) this;
        }

        public Criteria andOverdueCustomerGreaterThan(Integer value) {
            addCriterion("overdue_customer >", value, "overdueCustomer");
            return (Criteria) this;
        }

        public Criteria andOverdueCustomerGreaterThanOrEqualTo(Integer value) {
            addCriterion("overdue_customer >=", value, "overdueCustomer");
            return (Criteria) this;
        }

        public Criteria andOverdueCustomerLessThan(Integer value) {
            addCriterion("overdue_customer <", value, "overdueCustomer");
            return (Criteria) this;
        }

        public Criteria andOverdueCustomerLessThanOrEqualTo(Integer value) {
            addCriterion("overdue_customer <=", value, "overdueCustomer");
            return (Criteria) this;
        }

        public Criteria andOverdueCustomerIn(List<Integer> values) {
            addCriterion("overdue_customer in", values, "overdueCustomer");
            return (Criteria) this;
        }

        public Criteria andOverdueCustomerNotIn(List<Integer> values) {
            addCriterion("overdue_customer not in", values, "overdueCustomer");
            return (Criteria) this;
        }

        public Criteria andOverdueCustomerBetween(Integer value1, Integer value2) {
            addCriterion("overdue_customer between", value1, value2, "overdueCustomer");
            return (Criteria) this;
        }

        public Criteria andOverdueCustomerNotBetween(Integer value1, Integer value2) {
            addCriterion("overdue_customer not between", value1, value2, "overdueCustomer");
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