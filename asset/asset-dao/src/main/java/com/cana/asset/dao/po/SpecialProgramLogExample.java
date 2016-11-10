package com.cana.asset.dao.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SpecialProgramLogExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int limitStart = -1;

    protected int limitEnd = -1;

    public SpecialProgramLogExample() {
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

        public Criteria andSpecialProgramIdIsNull() {
            addCriterion("special_program_id is null");
            return (Criteria) this;
        }

        public Criteria andSpecialProgramIdIsNotNull() {
            addCriterion("special_program_id is not null");
            return (Criteria) this;
        }

        public Criteria andSpecialProgramIdEqualTo(String value) {
            addCriterion("special_program_id =", value, "specialProgramId");
            return (Criteria) this;
        }

        public Criteria andSpecialProgramIdNotEqualTo(String value) {
            addCriterion("special_program_id <>", value, "specialProgramId");
            return (Criteria) this;
        }

        public Criteria andSpecialProgramIdGreaterThan(String value) {
            addCriterion("special_program_id >", value, "specialProgramId");
            return (Criteria) this;
        }

        public Criteria andSpecialProgramIdGreaterThanOrEqualTo(String value) {
            addCriterion("special_program_id >=", value, "specialProgramId");
            return (Criteria) this;
        }

        public Criteria andSpecialProgramIdLessThan(String value) {
            addCriterion("special_program_id <", value, "specialProgramId");
            return (Criteria) this;
        }

        public Criteria andSpecialProgramIdLessThanOrEqualTo(String value) {
            addCriterion("special_program_id <=", value, "specialProgramId");
            return (Criteria) this;
        }

        public Criteria andSpecialProgramIdLike(String value) {
            addCriterion("special_program_id like", value, "specialProgramId");
            return (Criteria) this;
        }

        public Criteria andSpecialProgramIdNotLike(String value) {
            addCriterion("special_program_id not like", value, "specialProgramId");
            return (Criteria) this;
        }

        public Criteria andSpecialProgramIdIn(List<String> values) {
            addCriterion("special_program_id in", values, "specialProgramId");
            return (Criteria) this;
        }

        public Criteria andSpecialProgramIdNotIn(List<String> values) {
            addCriterion("special_program_id not in", values, "specialProgramId");
            return (Criteria) this;
        }

        public Criteria andSpecialProgramIdBetween(String value1, String value2) {
            addCriterion("special_program_id between", value1, value2, "specialProgramId");
            return (Criteria) this;
        }

        public Criteria andSpecialProgramIdNotBetween(String value1, String value2) {
            addCriterion("special_program_id not between", value1, value2, "specialProgramId");
            return (Criteria) this;
        }

        public Criteria andSpecialProgramNameIsNull() {
            addCriterion("special_program_name is null");
            return (Criteria) this;
        }

        public Criteria andSpecialProgramNameIsNotNull() {
            addCriterion("special_program_name is not null");
            return (Criteria) this;
        }

        public Criteria andSpecialProgramNameEqualTo(String value) {
            addCriterion("special_program_name =", value, "specialProgramName");
            return (Criteria) this;
        }

        public Criteria andSpecialProgramNameNotEqualTo(String value) {
            addCriterion("special_program_name <>", value, "specialProgramName");
            return (Criteria) this;
        }

        public Criteria andSpecialProgramNameGreaterThan(String value) {
            addCriterion("special_program_name >", value, "specialProgramName");
            return (Criteria) this;
        }

        public Criteria andSpecialProgramNameGreaterThanOrEqualTo(String value) {
            addCriterion("special_program_name >=", value, "specialProgramName");
            return (Criteria) this;
        }

        public Criteria andSpecialProgramNameLessThan(String value) {
            addCriterion("special_program_name <", value, "specialProgramName");
            return (Criteria) this;
        }

        public Criteria andSpecialProgramNameLessThanOrEqualTo(String value) {
            addCriterion("special_program_name <=", value, "specialProgramName");
            return (Criteria) this;
        }

        public Criteria andSpecialProgramNameLike(String value) {
            addCriterion("special_program_name like", value, "specialProgramName");
            return (Criteria) this;
        }

        public Criteria andSpecialProgramNameNotLike(String value) {
            addCriterion("special_program_name not like", value, "specialProgramName");
            return (Criteria) this;
        }

        public Criteria andSpecialProgramNameIn(List<String> values) {
            addCriterion("special_program_name in", values, "specialProgramName");
            return (Criteria) this;
        }

        public Criteria andSpecialProgramNameNotIn(List<String> values) {
            addCriterion("special_program_name not in", values, "specialProgramName");
            return (Criteria) this;
        }

        public Criteria andSpecialProgramNameBetween(String value1, String value2) {
            addCriterion("special_program_name between", value1, value2, "specialProgramName");
            return (Criteria) this;
        }

        public Criteria andSpecialProgramNameNotBetween(String value1, String value2) {
            addCriterion("special_program_name not between", value1, value2, "specialProgramName");
            return (Criteria) this;
        }

        public Criteria andUnderlyingAssetTypeIsNull() {
            addCriterion("underlying_asset_type is null");
            return (Criteria) this;
        }

        public Criteria andUnderlyingAssetTypeIsNotNull() {
            addCriterion("underlying_asset_type is not null");
            return (Criteria) this;
        }

        public Criteria andUnderlyingAssetTypeEqualTo(String value) {
            addCriterion("underlying_asset_type =", value, "underlyingAssetType");
            return (Criteria) this;
        }

        public Criteria andUnderlyingAssetTypeNotEqualTo(String value) {
            addCriterion("underlying_asset_type <>", value, "underlyingAssetType");
            return (Criteria) this;
        }

        public Criteria andUnderlyingAssetTypeGreaterThan(String value) {
            addCriterion("underlying_asset_type >", value, "underlyingAssetType");
            return (Criteria) this;
        }

        public Criteria andUnderlyingAssetTypeGreaterThanOrEqualTo(String value) {
            addCriterion("underlying_asset_type >=", value, "underlyingAssetType");
            return (Criteria) this;
        }

        public Criteria andUnderlyingAssetTypeLessThan(String value) {
            addCriterion("underlying_asset_type <", value, "underlyingAssetType");
            return (Criteria) this;
        }

        public Criteria andUnderlyingAssetTypeLessThanOrEqualTo(String value) {
            addCriterion("underlying_asset_type <=", value, "underlyingAssetType");
            return (Criteria) this;
        }

        public Criteria andUnderlyingAssetTypeLike(String value) {
            addCriterion("underlying_asset_type like", value, "underlyingAssetType");
            return (Criteria) this;
        }

        public Criteria andUnderlyingAssetTypeNotLike(String value) {
            addCriterion("underlying_asset_type not like", value, "underlyingAssetType");
            return (Criteria) this;
        }

        public Criteria andUnderlyingAssetTypeIn(List<String> values) {
            addCriterion("underlying_asset_type in", values, "underlyingAssetType");
            return (Criteria) this;
        }

        public Criteria andUnderlyingAssetTypeNotIn(List<String> values) {
            addCriterion("underlying_asset_type not in", values, "underlyingAssetType");
            return (Criteria) this;
        }

        public Criteria andUnderlyingAssetTypeBetween(String value1, String value2) {
            addCriterion("underlying_asset_type between", value1, value2, "underlyingAssetType");
            return (Criteria) this;
        }

        public Criteria andUnderlyingAssetTypeNotBetween(String value1, String value2) {
            addCriterion("underlying_asset_type not between", value1, value2, "underlyingAssetType");
            return (Criteria) this;
        }

        public Criteria andAssetPoolAmountIsNull() {
            addCriterion("asset_pool_amount is null");
            return (Criteria) this;
        }

        public Criteria andAssetPoolAmountIsNotNull() {
            addCriterion("asset_pool_amount is not null");
            return (Criteria) this;
        }

        public Criteria andAssetPoolAmountEqualTo(Long value) {
            addCriterion("asset_pool_amount =", value, "assetPoolAmount");
            return (Criteria) this;
        }

        public Criteria andAssetPoolAmountNotEqualTo(Long value) {
            addCriterion("asset_pool_amount <>", value, "assetPoolAmount");
            return (Criteria) this;
        }

        public Criteria andAssetPoolAmountGreaterThan(Long value) {
            addCriterion("asset_pool_amount >", value, "assetPoolAmount");
            return (Criteria) this;
        }

        public Criteria andAssetPoolAmountGreaterThanOrEqualTo(Long value) {
            addCriterion("asset_pool_amount >=", value, "assetPoolAmount");
            return (Criteria) this;
        }

        public Criteria andAssetPoolAmountLessThan(Long value) {
            addCriterion("asset_pool_amount <", value, "assetPoolAmount");
            return (Criteria) this;
        }

        public Criteria andAssetPoolAmountLessThanOrEqualTo(Long value) {
            addCriterion("asset_pool_amount <=", value, "assetPoolAmount");
            return (Criteria) this;
        }

        public Criteria andAssetPoolAmountIn(List<Long> values) {
            addCriterion("asset_pool_amount in", values, "assetPoolAmount");
            return (Criteria) this;
        }

        public Criteria andAssetPoolAmountNotIn(List<Long> values) {
            addCriterion("asset_pool_amount not in", values, "assetPoolAmount");
            return (Criteria) this;
        }

        public Criteria andAssetPoolAmountBetween(Long value1, Long value2) {
            addCriterion("asset_pool_amount between", value1, value2, "assetPoolAmount");
            return (Criteria) this;
        }

        public Criteria andAssetPoolAmountNotBetween(Long value1, Long value2) {
            addCriterion("asset_pool_amount not between", value1, value2, "assetPoolAmount");
            return (Criteria) this;
        }

        public Criteria andOperateCompanyNameIsNull() {
            addCriterion("operate_company_name is null");
            return (Criteria) this;
        }

        public Criteria andOperateCompanyNameIsNotNull() {
            addCriterion("operate_company_name is not null");
            return (Criteria) this;
        }

        public Criteria andOperateCompanyNameEqualTo(String value) {
            addCriterion("operate_company_name =", value, "operateCompanyName");
            return (Criteria) this;
        }

        public Criteria andOperateCompanyNameNotEqualTo(String value) {
            addCriterion("operate_company_name <>", value, "operateCompanyName");
            return (Criteria) this;
        }

        public Criteria andOperateCompanyNameGreaterThan(String value) {
            addCriterion("operate_company_name >", value, "operateCompanyName");
            return (Criteria) this;
        }

        public Criteria andOperateCompanyNameGreaterThanOrEqualTo(String value) {
            addCriterion("operate_company_name >=", value, "operateCompanyName");
            return (Criteria) this;
        }

        public Criteria andOperateCompanyNameLessThan(String value) {
            addCriterion("operate_company_name <", value, "operateCompanyName");
            return (Criteria) this;
        }

        public Criteria andOperateCompanyNameLessThanOrEqualTo(String value) {
            addCriterion("operate_company_name <=", value, "operateCompanyName");
            return (Criteria) this;
        }

        public Criteria andOperateCompanyNameLike(String value) {
            addCriterion("operate_company_name like", value, "operateCompanyName");
            return (Criteria) this;
        }

        public Criteria andOperateCompanyNameNotLike(String value) {
            addCriterion("operate_company_name not like", value, "operateCompanyName");
            return (Criteria) this;
        }

        public Criteria andOperateCompanyNameIn(List<String> values) {
            addCriterion("operate_company_name in", values, "operateCompanyName");
            return (Criteria) this;
        }

        public Criteria andOperateCompanyNameNotIn(List<String> values) {
            addCriterion("operate_company_name not in", values, "operateCompanyName");
            return (Criteria) this;
        }

        public Criteria andOperateCompanyNameBetween(String value1, String value2) {
            addCriterion("operate_company_name between", value1, value2, "operateCompanyName");
            return (Criteria) this;
        }

        public Criteria andOperateCompanyNameNotBetween(String value1, String value2) {
            addCriterion("operate_company_name not between", value1, value2, "operateCompanyName");
            return (Criteria) this;
        }

        public Criteria andOperateUsernameIsNull() {
            addCriterion("operate_username is null");
            return (Criteria) this;
        }

        public Criteria andOperateUsernameIsNotNull() {
            addCriterion("operate_username is not null");
            return (Criteria) this;
        }

        public Criteria andOperateUsernameEqualTo(String value) {
            addCriterion("operate_username =", value, "operateUsername");
            return (Criteria) this;
        }

        public Criteria andOperateUsernameNotEqualTo(String value) {
            addCriterion("operate_username <>", value, "operateUsername");
            return (Criteria) this;
        }

        public Criteria andOperateUsernameGreaterThan(String value) {
            addCriterion("operate_username >", value, "operateUsername");
            return (Criteria) this;
        }

        public Criteria andOperateUsernameGreaterThanOrEqualTo(String value) {
            addCriterion("operate_username >=", value, "operateUsername");
            return (Criteria) this;
        }

        public Criteria andOperateUsernameLessThan(String value) {
            addCriterion("operate_username <", value, "operateUsername");
            return (Criteria) this;
        }

        public Criteria andOperateUsernameLessThanOrEqualTo(String value) {
            addCriterion("operate_username <=", value, "operateUsername");
            return (Criteria) this;
        }

        public Criteria andOperateUsernameLike(String value) {
            addCriterion("operate_username like", value, "operateUsername");
            return (Criteria) this;
        }

        public Criteria andOperateUsernameNotLike(String value) {
            addCriterion("operate_username not like", value, "operateUsername");
            return (Criteria) this;
        }

        public Criteria andOperateUsernameIn(List<String> values) {
            addCriterion("operate_username in", values, "operateUsername");
            return (Criteria) this;
        }

        public Criteria andOperateUsernameNotIn(List<String> values) {
            addCriterion("operate_username not in", values, "operateUsername");
            return (Criteria) this;
        }

        public Criteria andOperateUsernameBetween(String value1, String value2) {
            addCriterion("operate_username between", value1, value2, "operateUsername");
            return (Criteria) this;
        }

        public Criteria andOperateUsernameNotBetween(String value1, String value2) {
            addCriterion("operate_username not between", value1, value2, "operateUsername");
            return (Criteria) this;
        }

        public Criteria andOperateTypeIsNull() {
            addCriterion("operate_type is null");
            return (Criteria) this;
        }

        public Criteria andOperateTypeIsNotNull() {
            addCriterion("operate_type is not null");
            return (Criteria) this;
        }

        public Criteria andOperateTypeEqualTo(String value) {
            addCriterion("operate_type =", value, "operateType");
            return (Criteria) this;
        }

        public Criteria andOperateTypeNotEqualTo(String value) {
            addCriterion("operate_type <>", value, "operateType");
            return (Criteria) this;
        }

        public Criteria andOperateTypeGreaterThan(String value) {
            addCriterion("operate_type >", value, "operateType");
            return (Criteria) this;
        }

        public Criteria andOperateTypeGreaterThanOrEqualTo(String value) {
            addCriterion("operate_type >=", value, "operateType");
            return (Criteria) this;
        }

        public Criteria andOperateTypeLessThan(String value) {
            addCriterion("operate_type <", value, "operateType");
            return (Criteria) this;
        }

        public Criteria andOperateTypeLessThanOrEqualTo(String value) {
            addCriterion("operate_type <=", value, "operateType");
            return (Criteria) this;
        }

        public Criteria andOperateTypeLike(String value) {
            addCriterion("operate_type like", value, "operateType");
            return (Criteria) this;
        }

        public Criteria andOperateTypeNotLike(String value) {
            addCriterion("operate_type not like", value, "operateType");
            return (Criteria) this;
        }

        public Criteria andOperateTypeIn(List<String> values) {
            addCriterion("operate_type in", values, "operateType");
            return (Criteria) this;
        }

        public Criteria andOperateTypeNotIn(List<String> values) {
            addCriterion("operate_type not in", values, "operateType");
            return (Criteria) this;
        }

        public Criteria andOperateTypeBetween(String value1, String value2) {
            addCriterion("operate_type between", value1, value2, "operateType");
            return (Criteria) this;
        }

        public Criteria andOperateTypeNotBetween(String value1, String value2) {
            addCriterion("operate_type not between", value1, value2, "operateType");
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