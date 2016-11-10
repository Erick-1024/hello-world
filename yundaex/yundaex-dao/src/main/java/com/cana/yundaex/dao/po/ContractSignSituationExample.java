package com.cana.yundaex.dao.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ContractSignSituationExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int limitStart = -1;

    protected int limitEnd = -1;

    public ContractSignSituationExample() {
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

        public Criteria andStationNameIsNull() {
            addCriterion("station_name is null");
            return (Criteria) this;
        }

        public Criteria andStationNameIsNotNull() {
            addCriterion("station_name is not null");
            return (Criteria) this;
        }

        public Criteria andStationNameEqualTo(String value) {
            addCriterion("station_name =", value, "stationName");
            return (Criteria) this;
        }

        public Criteria andStationNameNotEqualTo(String value) {
            addCriterion("station_name <>", value, "stationName");
            return (Criteria) this;
        }

        public Criteria andStationNameGreaterThan(String value) {
            addCriterion("station_name >", value, "stationName");
            return (Criteria) this;
        }

        public Criteria andStationNameGreaterThanOrEqualTo(String value) {
            addCriterion("station_name >=", value, "stationName");
            return (Criteria) this;
        }

        public Criteria andStationNameLessThan(String value) {
            addCriterion("station_name <", value, "stationName");
            return (Criteria) this;
        }

        public Criteria andStationNameLessThanOrEqualTo(String value) {
            addCriterion("station_name <=", value, "stationName");
            return (Criteria) this;
        }

        public Criteria andStationNameLike(String value) {
            addCriterion("station_name like", value, "stationName");
            return (Criteria) this;
        }

        public Criteria andStationNameNotLike(String value) {
            addCriterion("station_name not like", value, "stationName");
            return (Criteria) this;
        }

        public Criteria andStationNameIn(List<String> values) {
            addCriterion("station_name in", values, "stationName");
            return (Criteria) this;
        }

        public Criteria andStationNameNotIn(List<String> values) {
            addCriterion("station_name not in", values, "stationName");
            return (Criteria) this;
        }

        public Criteria andStationNameBetween(String value1, String value2) {
            addCriterion("station_name between", value1, value2, "stationName");
            return (Criteria) this;
        }

        public Criteria andStationNameNotBetween(String value1, String value2) {
            addCriterion("station_name not between", value1, value2, "stationName");
            return (Criteria) this;
        }

        public Criteria andSignSituationIsNull() {
            addCriterion("sign_situation is null");
            return (Criteria) this;
        }

        public Criteria andSignSituationIsNotNull() {
            addCriterion("sign_situation is not null");
            return (Criteria) this;
        }

        public Criteria andSignSituationEqualTo(Integer value) {
            addCriterion("sign_situation =", value, "signSituation");
            return (Criteria) this;
        }

        public Criteria andSignSituationNotEqualTo(Integer value) {
            addCriterion("sign_situation <>", value, "signSituation");
            return (Criteria) this;
        }

        public Criteria andSignSituationGreaterThan(Integer value) {
            addCriterion("sign_situation >", value, "signSituation");
            return (Criteria) this;
        }

        public Criteria andSignSituationGreaterThanOrEqualTo(Integer value) {
            addCriterion("sign_situation >=", value, "signSituation");
            return (Criteria) this;
        }

        public Criteria andSignSituationLessThan(Integer value) {
            addCriterion("sign_situation <", value, "signSituation");
            return (Criteria) this;
        }

        public Criteria andSignSituationLessThanOrEqualTo(Integer value) {
            addCriterion("sign_situation <=", value, "signSituation");
            return (Criteria) this;
        }

        public Criteria andSignSituationIn(List<Integer> values) {
            addCriterion("sign_situation in", values, "signSituation");
            return (Criteria) this;
        }

        public Criteria andSignSituationNotIn(List<Integer> values) {
            addCriterion("sign_situation not in", values, "signSituation");
            return (Criteria) this;
        }

        public Criteria andSignSituationBetween(Integer value1, Integer value2) {
            addCriterion("sign_situation between", value1, value2, "signSituation");
            return (Criteria) this;
        }

        public Criteria andSignSituationNotBetween(Integer value1, Integer value2) {
            addCriterion("sign_situation not between", value1, value2, "signSituation");
            return (Criteria) this;
        }

        public Criteria andSignCompleteTimeIsNull() {
            addCriterion("sign_complete_time is null");
            return (Criteria) this;
        }

        public Criteria andSignCompleteTimeIsNotNull() {
            addCriterion("sign_complete_time is not null");
            return (Criteria) this;
        }

        public Criteria andSignCompleteTimeEqualTo(Date value) {
            addCriterion("sign_complete_time =", value, "signCompleteTime");
            return (Criteria) this;
        }

        public Criteria andSignCompleteTimeNotEqualTo(Date value) {
            addCriterion("sign_complete_time <>", value, "signCompleteTime");
            return (Criteria) this;
        }

        public Criteria andSignCompleteTimeGreaterThan(Date value) {
            addCriterion("sign_complete_time >", value, "signCompleteTime");
            return (Criteria) this;
        }

        public Criteria andSignCompleteTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("sign_complete_time >=", value, "signCompleteTime");
            return (Criteria) this;
        }

        public Criteria andSignCompleteTimeLessThan(Date value) {
            addCriterion("sign_complete_time <", value, "signCompleteTime");
            return (Criteria) this;
        }

        public Criteria andSignCompleteTimeLessThanOrEqualTo(Date value) {
            addCriterion("sign_complete_time <=", value, "signCompleteTime");
            return (Criteria) this;
        }

        public Criteria andSignCompleteTimeIn(List<Date> values) {
            addCriterion("sign_complete_time in", values, "signCompleteTime");
            return (Criteria) this;
        }

        public Criteria andSignCompleteTimeNotIn(List<Date> values) {
            addCriterion("sign_complete_time not in", values, "signCompleteTime");
            return (Criteria) this;
        }

        public Criteria andSignCompleteTimeBetween(Date value1, Date value2) {
            addCriterion("sign_complete_time between", value1, value2, "signCompleteTime");
            return (Criteria) this;
        }

        public Criteria andSignCompleteTimeNotBetween(Date value1, Date value2) {
            addCriterion("sign_complete_time not between", value1, value2, "signCompleteTime");
            return (Criteria) this;
        }

        public Criteria andProtocolNoIsNull() {
            addCriterion("protocol_no is null");
            return (Criteria) this;
        }

        public Criteria andProtocolNoIsNotNull() {
            addCriterion("protocol_no is not null");
            return (Criteria) this;
        }

        public Criteria andProtocolNoEqualTo(String value) {
            addCriterion("protocol_no =", value, "protocolNo");
            return (Criteria) this;
        }

        public Criteria andProtocolNoNotEqualTo(String value) {
            addCriterion("protocol_no <>", value, "protocolNo");
            return (Criteria) this;
        }

        public Criteria andProtocolNoGreaterThan(String value) {
            addCriterion("protocol_no >", value, "protocolNo");
            return (Criteria) this;
        }

        public Criteria andProtocolNoGreaterThanOrEqualTo(String value) {
            addCriterion("protocol_no >=", value, "protocolNo");
            return (Criteria) this;
        }

        public Criteria andProtocolNoLessThan(String value) {
            addCriterion("protocol_no <", value, "protocolNo");
            return (Criteria) this;
        }

        public Criteria andProtocolNoLessThanOrEqualTo(String value) {
            addCriterion("protocol_no <=", value, "protocolNo");
            return (Criteria) this;
        }

        public Criteria andProtocolNoLike(String value) {
            addCriterion("protocol_no like", value, "protocolNo");
            return (Criteria) this;
        }

        public Criteria andProtocolNoNotLike(String value) {
            addCriterion("protocol_no not like", value, "protocolNo");
            return (Criteria) this;
        }

        public Criteria andProtocolNoIn(List<String> values) {
            addCriterion("protocol_no in", values, "protocolNo");
            return (Criteria) this;
        }

        public Criteria andProtocolNoNotIn(List<String> values) {
            addCriterion("protocol_no not in", values, "protocolNo");
            return (Criteria) this;
        }

        public Criteria andProtocolNoBetween(String value1, String value2) {
            addCriterion("protocol_no between", value1, value2, "protocolNo");
            return (Criteria) this;
        }

        public Criteria andProtocolNoNotBetween(String value1, String value2) {
            addCriterion("protocol_no not between", value1, value2, "protocolNo");
            return (Criteria) this;
        }

        public Criteria andPayAccountNameIsNull() {
            addCriterion("pay_account_name is null");
            return (Criteria) this;
        }

        public Criteria andPayAccountNameIsNotNull() {
            addCriterion("pay_account_name is not null");
            return (Criteria) this;
        }

        public Criteria andPayAccountNameEqualTo(String value) {
            addCriterion("pay_account_name =", value, "payAccountName");
            return (Criteria) this;
        }

        public Criteria andPayAccountNameNotEqualTo(String value) {
            addCriterion("pay_account_name <>", value, "payAccountName");
            return (Criteria) this;
        }

        public Criteria andPayAccountNameGreaterThan(String value) {
            addCriterion("pay_account_name >", value, "payAccountName");
            return (Criteria) this;
        }

        public Criteria andPayAccountNameGreaterThanOrEqualTo(String value) {
            addCriterion("pay_account_name >=", value, "payAccountName");
            return (Criteria) this;
        }

        public Criteria andPayAccountNameLessThan(String value) {
            addCriterion("pay_account_name <", value, "payAccountName");
            return (Criteria) this;
        }

        public Criteria andPayAccountNameLessThanOrEqualTo(String value) {
            addCriterion("pay_account_name <=", value, "payAccountName");
            return (Criteria) this;
        }

        public Criteria andPayAccountNameLike(String value) {
            addCriterion("pay_account_name like", value, "payAccountName");
            return (Criteria) this;
        }

        public Criteria andPayAccountNameNotLike(String value) {
            addCriterion("pay_account_name not like", value, "payAccountName");
            return (Criteria) this;
        }

        public Criteria andPayAccountNameIn(List<String> values) {
            addCriterion("pay_account_name in", values, "payAccountName");
            return (Criteria) this;
        }

        public Criteria andPayAccountNameNotIn(List<String> values) {
            addCriterion("pay_account_name not in", values, "payAccountName");
            return (Criteria) this;
        }

        public Criteria andPayAccountNameBetween(String value1, String value2) {
            addCriterion("pay_account_name between", value1, value2, "payAccountName");
            return (Criteria) this;
        }

        public Criteria andPayAccountNameNotBetween(String value1, String value2) {
            addCriterion("pay_account_name not between", value1, value2, "payAccountName");
            return (Criteria) this;
        }

        public Criteria andPayAccountNoIsNull() {
            addCriterion("pay_account_no is null");
            return (Criteria) this;
        }

        public Criteria andPayAccountNoIsNotNull() {
            addCriterion("pay_account_no is not null");
            return (Criteria) this;
        }

        public Criteria andPayAccountNoEqualTo(String value) {
            addCriterion("pay_account_no =", value, "payAccountNo");
            return (Criteria) this;
        }

        public Criteria andPayAccountNoNotEqualTo(String value) {
            addCriterion("pay_account_no <>", value, "payAccountNo");
            return (Criteria) this;
        }

        public Criteria andPayAccountNoGreaterThan(String value) {
            addCriterion("pay_account_no >", value, "payAccountNo");
            return (Criteria) this;
        }

        public Criteria andPayAccountNoGreaterThanOrEqualTo(String value) {
            addCriterion("pay_account_no >=", value, "payAccountNo");
            return (Criteria) this;
        }

        public Criteria andPayAccountNoLessThan(String value) {
            addCriterion("pay_account_no <", value, "payAccountNo");
            return (Criteria) this;
        }

        public Criteria andPayAccountNoLessThanOrEqualTo(String value) {
            addCriterion("pay_account_no <=", value, "payAccountNo");
            return (Criteria) this;
        }

        public Criteria andPayAccountNoLike(String value) {
            addCriterion("pay_account_no like", value, "payAccountNo");
            return (Criteria) this;
        }

        public Criteria andPayAccountNoNotLike(String value) {
            addCriterion("pay_account_no not like", value, "payAccountNo");
            return (Criteria) this;
        }

        public Criteria andPayAccountNoIn(List<String> values) {
            addCriterion("pay_account_no in", values, "payAccountNo");
            return (Criteria) this;
        }

        public Criteria andPayAccountNoNotIn(List<String> values) {
            addCriterion("pay_account_no not in", values, "payAccountNo");
            return (Criteria) this;
        }

        public Criteria andPayAccountNoBetween(String value1, String value2) {
            addCriterion("pay_account_no between", value1, value2, "payAccountNo");
            return (Criteria) this;
        }

        public Criteria andPayAccountNoNotBetween(String value1, String value2) {
            addCriterion("pay_account_no not between", value1, value2, "payAccountNo");
            return (Criteria) this;
        }

        public Criteria andPayAccountBankIsNull() {
            addCriterion("pay_account_bank is null");
            return (Criteria) this;
        }

        public Criteria andPayAccountBankIsNotNull() {
            addCriterion("pay_account_bank is not null");
            return (Criteria) this;
        }

        public Criteria andPayAccountBankEqualTo(String value) {
            addCriterion("pay_account_bank =", value, "payAccountBank");
            return (Criteria) this;
        }

        public Criteria andPayAccountBankNotEqualTo(String value) {
            addCriterion("pay_account_bank <>", value, "payAccountBank");
            return (Criteria) this;
        }

        public Criteria andPayAccountBankGreaterThan(String value) {
            addCriterion("pay_account_bank >", value, "payAccountBank");
            return (Criteria) this;
        }

        public Criteria andPayAccountBankGreaterThanOrEqualTo(String value) {
            addCriterion("pay_account_bank >=", value, "payAccountBank");
            return (Criteria) this;
        }

        public Criteria andPayAccountBankLessThan(String value) {
            addCriterion("pay_account_bank <", value, "payAccountBank");
            return (Criteria) this;
        }

        public Criteria andPayAccountBankLessThanOrEqualTo(String value) {
            addCriterion("pay_account_bank <=", value, "payAccountBank");
            return (Criteria) this;
        }

        public Criteria andPayAccountBankLike(String value) {
            addCriterion("pay_account_bank like", value, "payAccountBank");
            return (Criteria) this;
        }

        public Criteria andPayAccountBankNotLike(String value) {
            addCriterion("pay_account_bank not like", value, "payAccountBank");
            return (Criteria) this;
        }

        public Criteria andPayAccountBankIn(List<String> values) {
            addCriterion("pay_account_bank in", values, "payAccountBank");
            return (Criteria) this;
        }

        public Criteria andPayAccountBankNotIn(List<String> values) {
            addCriterion("pay_account_bank not in", values, "payAccountBank");
            return (Criteria) this;
        }

        public Criteria andPayAccountBankBetween(String value1, String value2) {
            addCriterion("pay_account_bank between", value1, value2, "payAccountBank");
            return (Criteria) this;
        }

        public Criteria andPayAccountBankNotBetween(String value1, String value2) {
            addCriterion("pay_account_bank not between", value1, value2, "payAccountBank");
            return (Criteria) this;
        }

        public Criteria andPayLianHangNoIsNull() {
            addCriterion("pay_lian_hang_no is null");
            return (Criteria) this;
        }

        public Criteria andPayLianHangNoIsNotNull() {
            addCriterion("pay_lian_hang_no is not null");
            return (Criteria) this;
        }

        public Criteria andPayLianHangNoEqualTo(String value) {
            addCriterion("pay_lian_hang_no =", value, "payLianHangNo");
            return (Criteria) this;
        }

        public Criteria andPayLianHangNoNotEqualTo(String value) {
            addCriterion("pay_lian_hang_no <>", value, "payLianHangNo");
            return (Criteria) this;
        }

        public Criteria andPayLianHangNoGreaterThan(String value) {
            addCriterion("pay_lian_hang_no >", value, "payLianHangNo");
            return (Criteria) this;
        }

        public Criteria andPayLianHangNoGreaterThanOrEqualTo(String value) {
            addCriterion("pay_lian_hang_no >=", value, "payLianHangNo");
            return (Criteria) this;
        }

        public Criteria andPayLianHangNoLessThan(String value) {
            addCriterion("pay_lian_hang_no <", value, "payLianHangNo");
            return (Criteria) this;
        }

        public Criteria andPayLianHangNoLessThanOrEqualTo(String value) {
            addCriterion("pay_lian_hang_no <=", value, "payLianHangNo");
            return (Criteria) this;
        }

        public Criteria andPayLianHangNoLike(String value) {
            addCriterion("pay_lian_hang_no like", value, "payLianHangNo");
            return (Criteria) this;
        }

        public Criteria andPayLianHangNoNotLike(String value) {
            addCriterion("pay_lian_hang_no not like", value, "payLianHangNo");
            return (Criteria) this;
        }

        public Criteria andPayLianHangNoIn(List<String> values) {
            addCriterion("pay_lian_hang_no in", values, "payLianHangNo");
            return (Criteria) this;
        }

        public Criteria andPayLianHangNoNotIn(List<String> values) {
            addCriterion("pay_lian_hang_no not in", values, "payLianHangNo");
            return (Criteria) this;
        }

        public Criteria andPayLianHangNoBetween(String value1, String value2) {
            addCriterion("pay_lian_hang_no between", value1, value2, "payLianHangNo");
            return (Criteria) this;
        }

        public Criteria andPayLianHangNoNotBetween(String value1, String value2) {
            addCriterion("pay_lian_hang_no not between", value1, value2, "payLianHangNo");
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

        public Criteria andFinanceContractSignStateIsNull() {
            addCriterion("Finance_contract_sign_state is null");
            return (Criteria) this;
        }

        public Criteria andFinanceContractSignStateIsNotNull() {
            addCriterion("Finance_contract_sign_state is not null");
            return (Criteria) this;
        }

        public Criteria andFinanceContractSignStateEqualTo(String value) {
            addCriterion("Finance_contract_sign_state =", value, "financeContractSignState");
            return (Criteria) this;
        }

        public Criteria andFinanceContractSignStateNotEqualTo(String value) {
            addCriterion("Finance_contract_sign_state <>", value, "financeContractSignState");
            return (Criteria) this;
        }

        public Criteria andFinanceContractSignStateGreaterThan(String value) {
            addCriterion("Finance_contract_sign_state >", value, "financeContractSignState");
            return (Criteria) this;
        }

        public Criteria andFinanceContractSignStateGreaterThanOrEqualTo(String value) {
            addCriterion("Finance_contract_sign_state >=", value, "financeContractSignState");
            return (Criteria) this;
        }

        public Criteria andFinanceContractSignStateLessThan(String value) {
            addCriterion("Finance_contract_sign_state <", value, "financeContractSignState");
            return (Criteria) this;
        }

        public Criteria andFinanceContractSignStateLessThanOrEqualTo(String value) {
            addCriterion("Finance_contract_sign_state <=", value, "financeContractSignState");
            return (Criteria) this;
        }

        public Criteria andFinanceContractSignStateLike(String value) {
            addCriterion("Finance_contract_sign_state like", value, "financeContractSignState");
            return (Criteria) this;
        }

        public Criteria andFinanceContractSignStateNotLike(String value) {
            addCriterion("Finance_contract_sign_state not like", value, "financeContractSignState");
            return (Criteria) this;
        }

        public Criteria andFinanceContractSignStateIn(List<String> values) {
            addCriterion("Finance_contract_sign_state in", values, "financeContractSignState");
            return (Criteria) this;
        }

        public Criteria andFinanceContractSignStateNotIn(List<String> values) {
            addCriterion("Finance_contract_sign_state not in", values, "financeContractSignState");
            return (Criteria) this;
        }

        public Criteria andFinanceContractSignStateBetween(String value1, String value2) {
            addCriterion("Finance_contract_sign_state between", value1, value2, "financeContractSignState");
            return (Criteria) this;
        }

        public Criteria andFinanceContractSignStateNotBetween(String value1, String value2) {
            addCriterion("Finance_contract_sign_state not between", value1, value2, "financeContractSignState");
            return (Criteria) this;
        }

        public Criteria andCreditContractSignStateIsNull() {
            addCriterion("credit_contract_sign_state is null");
            return (Criteria) this;
        }

        public Criteria andCreditContractSignStateIsNotNull() {
            addCriterion("credit_contract_sign_state is not null");
            return (Criteria) this;
        }

        public Criteria andCreditContractSignStateEqualTo(String value) {
            addCriterion("credit_contract_sign_state =", value, "creditContractSignState");
            return (Criteria) this;
        }

        public Criteria andCreditContractSignStateNotEqualTo(String value) {
            addCriterion("credit_contract_sign_state <>", value, "creditContractSignState");
            return (Criteria) this;
        }

        public Criteria andCreditContractSignStateGreaterThan(String value) {
            addCriterion("credit_contract_sign_state >", value, "creditContractSignState");
            return (Criteria) this;
        }

        public Criteria andCreditContractSignStateGreaterThanOrEqualTo(String value) {
            addCriterion("credit_contract_sign_state >=", value, "creditContractSignState");
            return (Criteria) this;
        }

        public Criteria andCreditContractSignStateLessThan(String value) {
            addCriterion("credit_contract_sign_state <", value, "creditContractSignState");
            return (Criteria) this;
        }

        public Criteria andCreditContractSignStateLessThanOrEqualTo(String value) {
            addCriterion("credit_contract_sign_state <=", value, "creditContractSignState");
            return (Criteria) this;
        }

        public Criteria andCreditContractSignStateLike(String value) {
            addCriterion("credit_contract_sign_state like", value, "creditContractSignState");
            return (Criteria) this;
        }

        public Criteria andCreditContractSignStateNotLike(String value) {
            addCriterion("credit_contract_sign_state not like", value, "creditContractSignState");
            return (Criteria) this;
        }

        public Criteria andCreditContractSignStateIn(List<String> values) {
            addCriterion("credit_contract_sign_state in", values, "creditContractSignState");
            return (Criteria) this;
        }

        public Criteria andCreditContractSignStateNotIn(List<String> values) {
            addCriterion("credit_contract_sign_state not in", values, "creditContractSignState");
            return (Criteria) this;
        }

        public Criteria andCreditContractSignStateBetween(String value1, String value2) {
            addCriterion("credit_contract_sign_state between", value1, value2, "creditContractSignState");
            return (Criteria) this;
        }

        public Criteria andCreditContractSignStateNotBetween(String value1, String value2) {
            addCriterion("credit_contract_sign_state not between", value1, value2, "creditContractSignState");
            return (Criteria) this;
        }

        public Criteria andDutyContractSignStateIsNull() {
            addCriterion("duty_contract_sign_state is null");
            return (Criteria) this;
        }

        public Criteria andDutyContractSignStateIsNotNull() {
            addCriterion("duty_contract_sign_state is not null");
            return (Criteria) this;
        }

        public Criteria andDutyContractSignStateEqualTo(String value) {
            addCriterion("duty_contract_sign_state =", value, "dutyContractSignState");
            return (Criteria) this;
        }

        public Criteria andDutyContractSignStateNotEqualTo(String value) {
            addCriterion("duty_contract_sign_state <>", value, "dutyContractSignState");
            return (Criteria) this;
        }

        public Criteria andDutyContractSignStateGreaterThan(String value) {
            addCriterion("duty_contract_sign_state >", value, "dutyContractSignState");
            return (Criteria) this;
        }

        public Criteria andDutyContractSignStateGreaterThanOrEqualTo(String value) {
            addCriterion("duty_contract_sign_state >=", value, "dutyContractSignState");
            return (Criteria) this;
        }

        public Criteria andDutyContractSignStateLessThan(String value) {
            addCriterion("duty_contract_sign_state <", value, "dutyContractSignState");
            return (Criteria) this;
        }

        public Criteria andDutyContractSignStateLessThanOrEqualTo(String value) {
            addCriterion("duty_contract_sign_state <=", value, "dutyContractSignState");
            return (Criteria) this;
        }

        public Criteria andDutyContractSignStateLike(String value) {
            addCriterion("duty_contract_sign_state like", value, "dutyContractSignState");
            return (Criteria) this;
        }

        public Criteria andDutyContractSignStateNotLike(String value) {
            addCriterion("duty_contract_sign_state not like", value, "dutyContractSignState");
            return (Criteria) this;
        }

        public Criteria andDutyContractSignStateIn(List<String> values) {
            addCriterion("duty_contract_sign_state in", values, "dutyContractSignState");
            return (Criteria) this;
        }

        public Criteria andDutyContractSignStateNotIn(List<String> values) {
            addCriterion("duty_contract_sign_state not in", values, "dutyContractSignState");
            return (Criteria) this;
        }

        public Criteria andDutyContractSignStateBetween(String value1, String value2) {
            addCriterion("duty_contract_sign_state between", value1, value2, "dutyContractSignState");
            return (Criteria) this;
        }

        public Criteria andDutyContractSignStateNotBetween(String value1, String value2) {
            addCriterion("duty_contract_sign_state not between", value1, value2, "dutyContractSignState");
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