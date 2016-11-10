package com.cana.asset.dao.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LoanPlanExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int limitStart = -1;

    protected int limitEnd = -1;

    public LoanPlanExample() {
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

        public Criteria andRepaymentPeriodIsNull() {
            addCriterion("repayment_period is null");
            return (Criteria) this;
        }

        public Criteria andRepaymentPeriodIsNotNull() {
            addCriterion("repayment_period is not null");
            return (Criteria) this;
        }

        public Criteria andRepaymentPeriodEqualTo(Integer value) {
            addCriterion("repayment_period =", value, "repaymentPeriod");
            return (Criteria) this;
        }

        public Criteria andRepaymentPeriodNotEqualTo(Integer value) {
            addCriterion("repayment_period <>", value, "repaymentPeriod");
            return (Criteria) this;
        }

        public Criteria andRepaymentPeriodGreaterThan(Integer value) {
            addCriterion("repayment_period >", value, "repaymentPeriod");
            return (Criteria) this;
        }

        public Criteria andRepaymentPeriodGreaterThanOrEqualTo(Integer value) {
            addCriterion("repayment_period >=", value, "repaymentPeriod");
            return (Criteria) this;
        }

        public Criteria andRepaymentPeriodLessThan(Integer value) {
            addCriterion("repayment_period <", value, "repaymentPeriod");
            return (Criteria) this;
        }

        public Criteria andRepaymentPeriodLessThanOrEqualTo(Integer value) {
            addCriterion("repayment_period <=", value, "repaymentPeriod");
            return (Criteria) this;
        }

        public Criteria andRepaymentPeriodIn(List<Integer> values) {
            addCriterion("repayment_period in", values, "repaymentPeriod");
            return (Criteria) this;
        }

        public Criteria andRepaymentPeriodNotIn(List<Integer> values) {
            addCriterion("repayment_period not in", values, "repaymentPeriod");
            return (Criteria) this;
        }

        public Criteria andRepaymentPeriodBetween(Integer value1, Integer value2) {
            addCriterion("repayment_period between", value1, value2, "repaymentPeriod");
            return (Criteria) this;
        }

        public Criteria andRepaymentPeriodNotBetween(Integer value1, Integer value2) {
            addCriterion("repayment_period not between", value1, value2, "repaymentPeriod");
            return (Criteria) this;
        }

        public Criteria andFinanceBalanceIsNull() {
            addCriterion("finance_balance is null");
            return (Criteria) this;
        }

        public Criteria andFinanceBalanceIsNotNull() {
            addCriterion("finance_balance is not null");
            return (Criteria) this;
        }

        public Criteria andFinanceBalanceEqualTo(Long value) {
            addCriterion("finance_balance =", value, "financeBalance");
            return (Criteria) this;
        }

        public Criteria andFinanceBalanceNotEqualTo(Long value) {
            addCriterion("finance_balance <>", value, "financeBalance");
            return (Criteria) this;
        }

        public Criteria andFinanceBalanceGreaterThan(Long value) {
            addCriterion("finance_balance >", value, "financeBalance");
            return (Criteria) this;
        }

        public Criteria andFinanceBalanceGreaterThanOrEqualTo(Long value) {
            addCriterion("finance_balance >=", value, "financeBalance");
            return (Criteria) this;
        }

        public Criteria andFinanceBalanceLessThan(Long value) {
            addCriterion("finance_balance <", value, "financeBalance");
            return (Criteria) this;
        }

        public Criteria andFinanceBalanceLessThanOrEqualTo(Long value) {
            addCriterion("finance_balance <=", value, "financeBalance");
            return (Criteria) this;
        }

        public Criteria andFinanceBalanceIn(List<Long> values) {
            addCriterion("finance_balance in", values, "financeBalance");
            return (Criteria) this;
        }

        public Criteria andFinanceBalanceNotIn(List<Long> values) {
            addCriterion("finance_balance not in", values, "financeBalance");
            return (Criteria) this;
        }

        public Criteria andFinanceBalanceBetween(Long value1, Long value2) {
            addCriterion("finance_balance between", value1, value2, "financeBalance");
            return (Criteria) this;
        }

        public Criteria andFinanceBalanceNotBetween(Long value1, Long value2) {
            addCriterion("finance_balance not between", value1, value2, "financeBalance");
            return (Criteria) this;
        }

        public Criteria andValueDateIsNull() {
            addCriterion("value_date is null");
            return (Criteria) this;
        }

        public Criteria andValueDateIsNotNull() {
            addCriterion("value_date is not null");
            return (Criteria) this;
        }

        public Criteria andValueDateEqualTo(String value) {
            addCriterion("value_date =", value, "valueDate");
            return (Criteria) this;
        }

        public Criteria andValueDateNotEqualTo(String value) {
            addCriterion("value_date <>", value, "valueDate");
            return (Criteria) this;
        }

        public Criteria andValueDateGreaterThan(String value) {
            addCriterion("value_date >", value, "valueDate");
            return (Criteria) this;
        }

        public Criteria andValueDateGreaterThanOrEqualTo(String value) {
            addCriterion("value_date >=", value, "valueDate");
            return (Criteria) this;
        }

        public Criteria andValueDateLessThan(String value) {
            addCriterion("value_date <", value, "valueDate");
            return (Criteria) this;
        }

        public Criteria andValueDateLessThanOrEqualTo(String value) {
            addCriterion("value_date <=", value, "valueDate");
            return (Criteria) this;
        }

        public Criteria andValueDateLike(String value) {
            addCriterion("value_date like", value, "valueDate");
            return (Criteria) this;
        }

        public Criteria andValueDateNotLike(String value) {
            addCriterion("value_date not like", value, "valueDate");
            return (Criteria) this;
        }

        public Criteria andValueDateIn(List<String> values) {
            addCriterion("value_date in", values, "valueDate");
            return (Criteria) this;
        }

        public Criteria andValueDateNotIn(List<String> values) {
            addCriterion("value_date not in", values, "valueDate");
            return (Criteria) this;
        }

        public Criteria andValueDateBetween(String value1, String value2) {
            addCriterion("value_date between", value1, value2, "valueDate");
            return (Criteria) this;
        }

        public Criteria andValueDateNotBetween(String value1, String value2) {
            addCriterion("value_date not between", value1, value2, "valueDate");
            return (Criteria) this;
        }

        public Criteria andSettleInterestDateIsNull() {
            addCriterion("settle_interest_date is null");
            return (Criteria) this;
        }

        public Criteria andSettleInterestDateIsNotNull() {
            addCriterion("settle_interest_date is not null");
            return (Criteria) this;
        }

        public Criteria andSettleInterestDateEqualTo(String value) {
            addCriterion("settle_interest_date =", value, "settleInterestDate");
            return (Criteria) this;
        }

        public Criteria andSettleInterestDateNotEqualTo(String value) {
            addCriterion("settle_interest_date <>", value, "settleInterestDate");
            return (Criteria) this;
        }

        public Criteria andSettleInterestDateGreaterThan(String value) {
            addCriterion("settle_interest_date >", value, "settleInterestDate");
            return (Criteria) this;
        }

        public Criteria andSettleInterestDateGreaterThanOrEqualTo(String value) {
            addCriterion("settle_interest_date >=", value, "settleInterestDate");
            return (Criteria) this;
        }

        public Criteria andSettleInterestDateLessThan(String value) {
            addCriterion("settle_interest_date <", value, "settleInterestDate");
            return (Criteria) this;
        }

        public Criteria andSettleInterestDateLessThanOrEqualTo(String value) {
            addCriterion("settle_interest_date <=", value, "settleInterestDate");
            return (Criteria) this;
        }

        public Criteria andSettleInterestDateLike(String value) {
            addCriterion("settle_interest_date like", value, "settleInterestDate");
            return (Criteria) this;
        }

        public Criteria andSettleInterestDateNotLike(String value) {
            addCriterion("settle_interest_date not like", value, "settleInterestDate");
            return (Criteria) this;
        }

        public Criteria andSettleInterestDateIn(List<String> values) {
            addCriterion("settle_interest_date in", values, "settleInterestDate");
            return (Criteria) this;
        }

        public Criteria andSettleInterestDateNotIn(List<String> values) {
            addCriterion("settle_interest_date not in", values, "settleInterestDate");
            return (Criteria) this;
        }

        public Criteria andSettleInterestDateBetween(String value1, String value2) {
            addCriterion("settle_interest_date between", value1, value2, "settleInterestDate");
            return (Criteria) this;
        }

        public Criteria andSettleInterestDateNotBetween(String value1, String value2) {
            addCriterion("settle_interest_date not between", value1, value2, "settleInterestDate");
            return (Criteria) this;
        }

        public Criteria andRepaymentDateIsNull() {
            addCriterion("repayment_date is null");
            return (Criteria) this;
        }

        public Criteria andRepaymentDateIsNotNull() {
            addCriterion("repayment_date is not null");
            return (Criteria) this;
        }

        public Criteria andRepaymentDateEqualTo(String value) {
            addCriterion("repayment_date =", value, "repaymentDate");
            return (Criteria) this;
        }

        public Criteria andRepaymentDateNotEqualTo(String value) {
            addCriterion("repayment_date <>", value, "repaymentDate");
            return (Criteria) this;
        }

        public Criteria andRepaymentDateGreaterThan(String value) {
            addCriterion("repayment_date >", value, "repaymentDate");
            return (Criteria) this;
        }

        public Criteria andRepaymentDateGreaterThanOrEqualTo(String value) {
            addCriterion("repayment_date >=", value, "repaymentDate");
            return (Criteria) this;
        }

        public Criteria andRepaymentDateLessThan(String value) {
            addCriterion("repayment_date <", value, "repaymentDate");
            return (Criteria) this;
        }

        public Criteria andRepaymentDateLessThanOrEqualTo(String value) {
            addCriterion("repayment_date <=", value, "repaymentDate");
            return (Criteria) this;
        }

        public Criteria andRepaymentDateLike(String value) {
            addCriterion("repayment_date like", value, "repaymentDate");
            return (Criteria) this;
        }

        public Criteria andRepaymentDateNotLike(String value) {
            addCriterion("repayment_date not like", value, "repaymentDate");
            return (Criteria) this;
        }

        public Criteria andRepaymentDateIn(List<String> values) {
            addCriterion("repayment_date in", values, "repaymentDate");
            return (Criteria) this;
        }

        public Criteria andRepaymentDateNotIn(List<String> values) {
            addCriterion("repayment_date not in", values, "repaymentDate");
            return (Criteria) this;
        }

        public Criteria andRepaymentDateBetween(String value1, String value2) {
            addCriterion("repayment_date between", value1, value2, "repaymentDate");
            return (Criteria) this;
        }

        public Criteria andRepaymentDateNotBetween(String value1, String value2) {
            addCriterion("repayment_date not between", value1, value2, "repaymentDate");
            return (Criteria) this;
        }

        public Criteria andAccountPrincipalIsNull() {
            addCriterion("account_principal is null");
            return (Criteria) this;
        }

        public Criteria andAccountPrincipalIsNotNull() {
            addCriterion("account_principal is not null");
            return (Criteria) this;
        }

        public Criteria andAccountPrincipalEqualTo(Long value) {
            addCriterion("account_principal =", value, "accountPrincipal");
            return (Criteria) this;
        }

        public Criteria andAccountPrincipalNotEqualTo(Long value) {
            addCriterion("account_principal <>", value, "accountPrincipal");
            return (Criteria) this;
        }

        public Criteria andAccountPrincipalGreaterThan(Long value) {
            addCriterion("account_principal >", value, "accountPrincipal");
            return (Criteria) this;
        }

        public Criteria andAccountPrincipalGreaterThanOrEqualTo(Long value) {
            addCriterion("account_principal >=", value, "accountPrincipal");
            return (Criteria) this;
        }

        public Criteria andAccountPrincipalLessThan(Long value) {
            addCriterion("account_principal <", value, "accountPrincipal");
            return (Criteria) this;
        }

        public Criteria andAccountPrincipalLessThanOrEqualTo(Long value) {
            addCriterion("account_principal <=", value, "accountPrincipal");
            return (Criteria) this;
        }

        public Criteria andAccountPrincipalIn(List<Long> values) {
            addCriterion("account_principal in", values, "accountPrincipal");
            return (Criteria) this;
        }

        public Criteria andAccountPrincipalNotIn(List<Long> values) {
            addCriterion("account_principal not in", values, "accountPrincipal");
            return (Criteria) this;
        }

        public Criteria andAccountPrincipalBetween(Long value1, Long value2) {
            addCriterion("account_principal between", value1, value2, "accountPrincipal");
            return (Criteria) this;
        }

        public Criteria andAccountPrincipalNotBetween(Long value1, Long value2) {
            addCriterion("account_principal not between", value1, value2, "accountPrincipal");
            return (Criteria) this;
        }

        public Criteria andAccountInterestIsNull() {
            addCriterion("account_interest is null");
            return (Criteria) this;
        }

        public Criteria andAccountInterestIsNotNull() {
            addCriterion("account_interest is not null");
            return (Criteria) this;
        }

        public Criteria andAccountInterestEqualTo(Long value) {
            addCriterion("account_interest =", value, "accountInterest");
            return (Criteria) this;
        }

        public Criteria andAccountInterestNotEqualTo(Long value) {
            addCriterion("account_interest <>", value, "accountInterest");
            return (Criteria) this;
        }

        public Criteria andAccountInterestGreaterThan(Long value) {
            addCriterion("account_interest >", value, "accountInterest");
            return (Criteria) this;
        }

        public Criteria andAccountInterestGreaterThanOrEqualTo(Long value) {
            addCriterion("account_interest >=", value, "accountInterest");
            return (Criteria) this;
        }

        public Criteria andAccountInterestLessThan(Long value) {
            addCriterion("account_interest <", value, "accountInterest");
            return (Criteria) this;
        }

        public Criteria andAccountInterestLessThanOrEqualTo(Long value) {
            addCriterion("account_interest <=", value, "accountInterest");
            return (Criteria) this;
        }

        public Criteria andAccountInterestIn(List<Long> values) {
            addCriterion("account_interest in", values, "accountInterest");
            return (Criteria) this;
        }

        public Criteria andAccountInterestNotIn(List<Long> values) {
            addCriterion("account_interest not in", values, "accountInterest");
            return (Criteria) this;
        }

        public Criteria andAccountInterestBetween(Long value1, Long value2) {
            addCriterion("account_interest between", value1, value2, "accountInterest");
            return (Criteria) this;
        }

        public Criteria andAccountInterestNotBetween(Long value1, Long value2) {
            addCriterion("account_interest not between", value1, value2, "accountInterest");
            return (Criteria) this;
        }

        public Criteria andAccountOverdueIsNull() {
            addCriterion("account_overdue is null");
            return (Criteria) this;
        }

        public Criteria andAccountOverdueIsNotNull() {
            addCriterion("account_overdue is not null");
            return (Criteria) this;
        }

        public Criteria andAccountOverdueEqualTo(Long value) {
            addCriterion("account_overdue =", value, "accountOverdue");
            return (Criteria) this;
        }

        public Criteria andAccountOverdueNotEqualTo(Long value) {
            addCriterion("account_overdue <>", value, "accountOverdue");
            return (Criteria) this;
        }

        public Criteria andAccountOverdueGreaterThan(Long value) {
            addCriterion("account_overdue >", value, "accountOverdue");
            return (Criteria) this;
        }

        public Criteria andAccountOverdueGreaterThanOrEqualTo(Long value) {
            addCriterion("account_overdue >=", value, "accountOverdue");
            return (Criteria) this;
        }

        public Criteria andAccountOverdueLessThan(Long value) {
            addCriterion("account_overdue <", value, "accountOverdue");
            return (Criteria) this;
        }

        public Criteria andAccountOverdueLessThanOrEqualTo(Long value) {
            addCriterion("account_overdue <=", value, "accountOverdue");
            return (Criteria) this;
        }

        public Criteria andAccountOverdueIn(List<Long> values) {
            addCriterion("account_overdue in", values, "accountOverdue");
            return (Criteria) this;
        }

        public Criteria andAccountOverdueNotIn(List<Long> values) {
            addCriterion("account_overdue not in", values, "accountOverdue");
            return (Criteria) this;
        }

        public Criteria andAccountOverdueBetween(Long value1, Long value2) {
            addCriterion("account_overdue between", value1, value2, "accountOverdue");
            return (Criteria) this;
        }

        public Criteria andAccountOverdueNotBetween(Long value1, Long value2) {
            addCriterion("account_overdue not between", value1, value2, "accountOverdue");
            return (Criteria) this;
        }

        public Criteria andSettleStatusIsNull() {
            addCriterion("settle_status is null");
            return (Criteria) this;
        }

        public Criteria andSettleStatusIsNotNull() {
            addCriterion("settle_status is not null");
            return (Criteria) this;
        }

        public Criteria andSettleStatusEqualTo(String value) {
            addCriterion("settle_status =", value, "settleStatus");
            return (Criteria) this;
        }

        public Criteria andSettleStatusNotEqualTo(String value) {
            addCriterion("settle_status <>", value, "settleStatus");
            return (Criteria) this;
        }

        public Criteria andSettleStatusGreaterThan(String value) {
            addCriterion("settle_status >", value, "settleStatus");
            return (Criteria) this;
        }

        public Criteria andSettleStatusGreaterThanOrEqualTo(String value) {
            addCriterion("settle_status >=", value, "settleStatus");
            return (Criteria) this;
        }

        public Criteria andSettleStatusLessThan(String value) {
            addCriterion("settle_status <", value, "settleStatus");
            return (Criteria) this;
        }

        public Criteria andSettleStatusLessThanOrEqualTo(String value) {
            addCriterion("settle_status <=", value, "settleStatus");
            return (Criteria) this;
        }

        public Criteria andSettleStatusLike(String value) {
            addCriterion("settle_status like", value, "settleStatus");
            return (Criteria) this;
        }

        public Criteria andSettleStatusNotLike(String value) {
            addCriterion("settle_status not like", value, "settleStatus");
            return (Criteria) this;
        }

        public Criteria andSettleStatusIn(List<String> values) {
            addCriterion("settle_status in", values, "settleStatus");
            return (Criteria) this;
        }

        public Criteria andSettleStatusNotIn(List<String> values) {
            addCriterion("settle_status not in", values, "settleStatus");
            return (Criteria) this;
        }

        public Criteria andSettleStatusBetween(String value1, String value2) {
            addCriterion("settle_status between", value1, value2, "settleStatus");
            return (Criteria) this;
        }

        public Criteria andSettleStatusNotBetween(String value1, String value2) {
            addCriterion("settle_status not between", value1, value2, "settleStatus");
            return (Criteria) this;
        }

        public Criteria andLastPaidDateIsNull() {
            addCriterion("last_paid_date is null");
            return (Criteria) this;
        }

        public Criteria andLastPaidDateIsNotNull() {
            addCriterion("last_paid_date is not null");
            return (Criteria) this;
        }

        public Criteria andLastPaidDateEqualTo(String value) {
            addCriterion("last_paid_date =", value, "lastPaidDate");
            return (Criteria) this;
        }

        public Criteria andLastPaidDateNotEqualTo(String value) {
            addCriterion("last_paid_date <>", value, "lastPaidDate");
            return (Criteria) this;
        }

        public Criteria andLastPaidDateGreaterThan(String value) {
            addCriterion("last_paid_date >", value, "lastPaidDate");
            return (Criteria) this;
        }

        public Criteria andLastPaidDateGreaterThanOrEqualTo(String value) {
            addCriterion("last_paid_date >=", value, "lastPaidDate");
            return (Criteria) this;
        }

        public Criteria andLastPaidDateLessThan(String value) {
            addCriterion("last_paid_date <", value, "lastPaidDate");
            return (Criteria) this;
        }

        public Criteria andLastPaidDateLessThanOrEqualTo(String value) {
            addCriterion("last_paid_date <=", value, "lastPaidDate");
            return (Criteria) this;
        }

        public Criteria andLastPaidDateLike(String value) {
            addCriterion("last_paid_date like", value, "lastPaidDate");
            return (Criteria) this;
        }

        public Criteria andLastPaidDateNotLike(String value) {
            addCriterion("last_paid_date not like", value, "lastPaidDate");
            return (Criteria) this;
        }

        public Criteria andLastPaidDateIn(List<String> values) {
            addCriterion("last_paid_date in", values, "lastPaidDate");
            return (Criteria) this;
        }

        public Criteria andLastPaidDateNotIn(List<String> values) {
            addCriterion("last_paid_date not in", values, "lastPaidDate");
            return (Criteria) this;
        }

        public Criteria andLastPaidDateBetween(String value1, String value2) {
            addCriterion("last_paid_date between", value1, value2, "lastPaidDate");
            return (Criteria) this;
        }

        public Criteria andLastPaidDateNotBetween(String value1, String value2) {
            addCriterion("last_paid_date not between", value1, value2, "lastPaidDate");
            return (Criteria) this;
        }

        public Criteria andPaidPrincipalIsNull() {
            addCriterion("paid_principal is null");
            return (Criteria) this;
        }

        public Criteria andPaidPrincipalIsNotNull() {
            addCriterion("paid_principal is not null");
            return (Criteria) this;
        }

        public Criteria andPaidPrincipalEqualTo(Long value) {
            addCriterion("paid_principal =", value, "paidPrincipal");
            return (Criteria) this;
        }

        public Criteria andPaidPrincipalNotEqualTo(Long value) {
            addCriterion("paid_principal <>", value, "paidPrincipal");
            return (Criteria) this;
        }

        public Criteria andPaidPrincipalGreaterThan(Long value) {
            addCriterion("paid_principal >", value, "paidPrincipal");
            return (Criteria) this;
        }

        public Criteria andPaidPrincipalGreaterThanOrEqualTo(Long value) {
            addCriterion("paid_principal >=", value, "paidPrincipal");
            return (Criteria) this;
        }

        public Criteria andPaidPrincipalLessThan(Long value) {
            addCriterion("paid_principal <", value, "paidPrincipal");
            return (Criteria) this;
        }

        public Criteria andPaidPrincipalLessThanOrEqualTo(Long value) {
            addCriterion("paid_principal <=", value, "paidPrincipal");
            return (Criteria) this;
        }

        public Criteria andPaidPrincipalIn(List<Long> values) {
            addCriterion("paid_principal in", values, "paidPrincipal");
            return (Criteria) this;
        }

        public Criteria andPaidPrincipalNotIn(List<Long> values) {
            addCriterion("paid_principal not in", values, "paidPrincipal");
            return (Criteria) this;
        }

        public Criteria andPaidPrincipalBetween(Long value1, Long value2) {
            addCriterion("paid_principal between", value1, value2, "paidPrincipal");
            return (Criteria) this;
        }

        public Criteria andPaidPrincipalNotBetween(Long value1, Long value2) {
            addCriterion("paid_principal not between", value1, value2, "paidPrincipal");
            return (Criteria) this;
        }

        public Criteria andPaidInterestIsNull() {
            addCriterion("paid_interest is null");
            return (Criteria) this;
        }

        public Criteria andPaidInterestIsNotNull() {
            addCriterion("paid_interest is not null");
            return (Criteria) this;
        }

        public Criteria andPaidInterestEqualTo(Long value) {
            addCriterion("paid_interest =", value, "paidInterest");
            return (Criteria) this;
        }

        public Criteria andPaidInterestNotEqualTo(Long value) {
            addCriterion("paid_interest <>", value, "paidInterest");
            return (Criteria) this;
        }

        public Criteria andPaidInterestGreaterThan(Long value) {
            addCriterion("paid_interest >", value, "paidInterest");
            return (Criteria) this;
        }

        public Criteria andPaidInterestGreaterThanOrEqualTo(Long value) {
            addCriterion("paid_interest >=", value, "paidInterest");
            return (Criteria) this;
        }

        public Criteria andPaidInterestLessThan(Long value) {
            addCriterion("paid_interest <", value, "paidInterest");
            return (Criteria) this;
        }

        public Criteria andPaidInterestLessThanOrEqualTo(Long value) {
            addCriterion("paid_interest <=", value, "paidInterest");
            return (Criteria) this;
        }

        public Criteria andPaidInterestIn(List<Long> values) {
            addCriterion("paid_interest in", values, "paidInterest");
            return (Criteria) this;
        }

        public Criteria andPaidInterestNotIn(List<Long> values) {
            addCriterion("paid_interest not in", values, "paidInterest");
            return (Criteria) this;
        }

        public Criteria andPaidInterestBetween(Long value1, Long value2) {
            addCriterion("paid_interest between", value1, value2, "paidInterest");
            return (Criteria) this;
        }

        public Criteria andPaidInterestNotBetween(Long value1, Long value2) {
            addCriterion("paid_interest not between", value1, value2, "paidInterest");
            return (Criteria) this;
        }

        public Criteria andPaidOverdueIsNull() {
            addCriterion("paid_overdue is null");
            return (Criteria) this;
        }

        public Criteria andPaidOverdueIsNotNull() {
            addCriterion("paid_overdue is not null");
            return (Criteria) this;
        }

        public Criteria andPaidOverdueEqualTo(Long value) {
            addCriterion("paid_overdue =", value, "paidOverdue");
            return (Criteria) this;
        }

        public Criteria andPaidOverdueNotEqualTo(Long value) {
            addCriterion("paid_overdue <>", value, "paidOverdue");
            return (Criteria) this;
        }

        public Criteria andPaidOverdueGreaterThan(Long value) {
            addCriterion("paid_overdue >", value, "paidOverdue");
            return (Criteria) this;
        }

        public Criteria andPaidOverdueGreaterThanOrEqualTo(Long value) {
            addCriterion("paid_overdue >=", value, "paidOverdue");
            return (Criteria) this;
        }

        public Criteria andPaidOverdueLessThan(Long value) {
            addCriterion("paid_overdue <", value, "paidOverdue");
            return (Criteria) this;
        }

        public Criteria andPaidOverdueLessThanOrEqualTo(Long value) {
            addCriterion("paid_overdue <=", value, "paidOverdue");
            return (Criteria) this;
        }

        public Criteria andPaidOverdueIn(List<Long> values) {
            addCriterion("paid_overdue in", values, "paidOverdue");
            return (Criteria) this;
        }

        public Criteria andPaidOverdueNotIn(List<Long> values) {
            addCriterion("paid_overdue not in", values, "paidOverdue");
            return (Criteria) this;
        }

        public Criteria andPaidOverdueBetween(Long value1, Long value2) {
            addCriterion("paid_overdue between", value1, value2, "paidOverdue");
            return (Criteria) this;
        }

        public Criteria andPaidOverdueNotBetween(Long value1, Long value2) {
            addCriterion("paid_overdue not between", value1, value2, "paidOverdue");
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

        public Criteria andUpateTimeIsNull() {
            addCriterion("upate_time is null");
            return (Criteria) this;
        }

        public Criteria andUpateTimeIsNotNull() {
            addCriterion("upate_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpateTimeEqualTo(Date value) {
            addCriterion("upate_time =", value, "upateTime");
            return (Criteria) this;
        }

        public Criteria andUpateTimeNotEqualTo(Date value) {
            addCriterion("upate_time <>", value, "upateTime");
            return (Criteria) this;
        }

        public Criteria andUpateTimeGreaterThan(Date value) {
            addCriterion("upate_time >", value, "upateTime");
            return (Criteria) this;
        }

        public Criteria andUpateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("upate_time >=", value, "upateTime");
            return (Criteria) this;
        }

        public Criteria andUpateTimeLessThan(Date value) {
            addCriterion("upate_time <", value, "upateTime");
            return (Criteria) this;
        }

        public Criteria andUpateTimeLessThanOrEqualTo(Date value) {
            addCriterion("upate_time <=", value, "upateTime");
            return (Criteria) this;
        }

        public Criteria andUpateTimeIn(List<Date> values) {
            addCriterion("upate_time in", values, "upateTime");
            return (Criteria) this;
        }

        public Criteria andUpateTimeNotIn(List<Date> values) {
            addCriterion("upate_time not in", values, "upateTime");
            return (Criteria) this;
        }

        public Criteria andUpateTimeBetween(Date value1, Date value2) {
            addCriterion("upate_time between", value1, value2, "upateTime");
            return (Criteria) this;
        }

        public Criteria andUpateTimeNotBetween(Date value1, Date value2) {
            addCriterion("upate_time not between", value1, value2, "upateTime");
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