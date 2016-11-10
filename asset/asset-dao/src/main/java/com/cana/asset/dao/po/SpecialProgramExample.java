package com.cana.asset.dao.po;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SpecialProgramExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int limitStart = -1;

    protected int limitEnd = -1;

    public SpecialProgramExample() {
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

        public Criteria andManagerNameIsNull() {
            addCriterion("manager_name is null");
            return (Criteria) this;
        }

        public Criteria andManagerNameIsNotNull() {
            addCriterion("manager_name is not null");
            return (Criteria) this;
        }

        public Criteria andManagerNameEqualTo(String value) {
            addCriterion("manager_name =", value, "managerName");
            return (Criteria) this;
        }

        public Criteria andManagerNameNotEqualTo(String value) {
            addCriterion("manager_name <>", value, "managerName");
            return (Criteria) this;
        }

        public Criteria andManagerNameGreaterThan(String value) {
            addCriterion("manager_name >", value, "managerName");
            return (Criteria) this;
        }

        public Criteria andManagerNameGreaterThanOrEqualTo(String value) {
            addCriterion("manager_name >=", value, "managerName");
            return (Criteria) this;
        }

        public Criteria andManagerNameLessThan(String value) {
            addCriterion("manager_name <", value, "managerName");
            return (Criteria) this;
        }

        public Criteria andManagerNameLessThanOrEqualTo(String value) {
            addCriterion("manager_name <=", value, "managerName");
            return (Criteria) this;
        }

        public Criteria andManagerNameLike(String value) {
            addCriterion("manager_name like", value, "managerName");
            return (Criteria) this;
        }

        public Criteria andManagerNameNotLike(String value) {
            addCriterion("manager_name not like", value, "managerName");
            return (Criteria) this;
        }

        public Criteria andManagerNameIn(List<String> values) {
            addCriterion("manager_name in", values, "managerName");
            return (Criteria) this;
        }

        public Criteria andManagerNameNotIn(List<String> values) {
            addCriterion("manager_name not in", values, "managerName");
            return (Criteria) this;
        }

        public Criteria andManagerNameBetween(String value1, String value2) {
            addCriterion("manager_name between", value1, value2, "managerName");
            return (Criteria) this;
        }

        public Criteria andManagerNameNotBetween(String value1, String value2) {
            addCriterion("manager_name not between", value1, value2, "managerName");
            return (Criteria) this;
        }

        public Criteria andManagerIdIsNull() {
            addCriterion("manager_id is null");
            return (Criteria) this;
        }

        public Criteria andManagerIdIsNotNull() {
            addCriterion("manager_id is not null");
            return (Criteria) this;
        }

        public Criteria andManagerIdEqualTo(String value) {
            addCriterion("manager_id =", value, "managerId");
            return (Criteria) this;
        }

        public Criteria andManagerIdNotEqualTo(String value) {
            addCriterion("manager_id <>", value, "managerId");
            return (Criteria) this;
        }

        public Criteria andManagerIdGreaterThan(String value) {
            addCriterion("manager_id >", value, "managerId");
            return (Criteria) this;
        }

        public Criteria andManagerIdGreaterThanOrEqualTo(String value) {
            addCriterion("manager_id >=", value, "managerId");
            return (Criteria) this;
        }

        public Criteria andManagerIdLessThan(String value) {
            addCriterion("manager_id <", value, "managerId");
            return (Criteria) this;
        }

        public Criteria andManagerIdLessThanOrEqualTo(String value) {
            addCriterion("manager_id <=", value, "managerId");
            return (Criteria) this;
        }

        public Criteria andManagerIdLike(String value) {
            addCriterion("manager_id like", value, "managerId");
            return (Criteria) this;
        }

        public Criteria andManagerIdNotLike(String value) {
            addCriterion("manager_id not like", value, "managerId");
            return (Criteria) this;
        }

        public Criteria andManagerIdIn(List<String> values) {
            addCriterion("manager_id in", values, "managerId");
            return (Criteria) this;
        }

        public Criteria andManagerIdNotIn(List<String> values) {
            addCriterion("manager_id not in", values, "managerId");
            return (Criteria) this;
        }

        public Criteria andManagerIdBetween(String value1, String value2) {
            addCriterion("manager_id between", value1, value2, "managerId");
            return (Criteria) this;
        }

        public Criteria andManagerIdNotBetween(String value1, String value2) {
            addCriterion("manager_id not between", value1, value2, "managerId");
            return (Criteria) this;
        }

        public Criteria andCreaterNameIsNull() {
            addCriterion("creater_name is null");
            return (Criteria) this;
        }

        public Criteria andCreaterNameIsNotNull() {
            addCriterion("creater_name is not null");
            return (Criteria) this;
        }

        public Criteria andCreaterNameEqualTo(String value) {
            addCriterion("creater_name =", value, "createrName");
            return (Criteria) this;
        }

        public Criteria andCreaterNameNotEqualTo(String value) {
            addCriterion("creater_name <>", value, "createrName");
            return (Criteria) this;
        }

        public Criteria andCreaterNameGreaterThan(String value) {
            addCriterion("creater_name >", value, "createrName");
            return (Criteria) this;
        }

        public Criteria andCreaterNameGreaterThanOrEqualTo(String value) {
            addCriterion("creater_name >=", value, "createrName");
            return (Criteria) this;
        }

        public Criteria andCreaterNameLessThan(String value) {
            addCriterion("creater_name <", value, "createrName");
            return (Criteria) this;
        }

        public Criteria andCreaterNameLessThanOrEqualTo(String value) {
            addCriterion("creater_name <=", value, "createrName");
            return (Criteria) this;
        }

        public Criteria andCreaterNameLike(String value) {
            addCriterion("creater_name like", value, "createrName");
            return (Criteria) this;
        }

        public Criteria andCreaterNameNotLike(String value) {
            addCriterion("creater_name not like", value, "createrName");
            return (Criteria) this;
        }

        public Criteria andCreaterNameIn(List<String> values) {
            addCriterion("creater_name in", values, "createrName");
            return (Criteria) this;
        }

        public Criteria andCreaterNameNotIn(List<String> values) {
            addCriterion("creater_name not in", values, "createrName");
            return (Criteria) this;
        }

        public Criteria andCreaterNameBetween(String value1, String value2) {
            addCriterion("creater_name between", value1, value2, "createrName");
            return (Criteria) this;
        }

        public Criteria andCreaterNameNotBetween(String value1, String value2) {
            addCriterion("creater_name not between", value1, value2, "createrName");
            return (Criteria) this;
        }

        public Criteria andCreaterIdIsNull() {
            addCriterion("creater_id is null");
            return (Criteria) this;
        }

        public Criteria andCreaterIdIsNotNull() {
            addCriterion("creater_id is not null");
            return (Criteria) this;
        }

        public Criteria andCreaterIdEqualTo(String value) {
            addCriterion("creater_id =", value, "createrId");
            return (Criteria) this;
        }

        public Criteria andCreaterIdNotEqualTo(String value) {
            addCriterion("creater_id <>", value, "createrId");
            return (Criteria) this;
        }

        public Criteria andCreaterIdGreaterThan(String value) {
            addCriterion("creater_id >", value, "createrId");
            return (Criteria) this;
        }

        public Criteria andCreaterIdGreaterThanOrEqualTo(String value) {
            addCriterion("creater_id >=", value, "createrId");
            return (Criteria) this;
        }

        public Criteria andCreaterIdLessThan(String value) {
            addCriterion("creater_id <", value, "createrId");
            return (Criteria) this;
        }

        public Criteria andCreaterIdLessThanOrEqualTo(String value) {
            addCriterion("creater_id <=", value, "createrId");
            return (Criteria) this;
        }

        public Criteria andCreaterIdLike(String value) {
            addCriterion("creater_id like", value, "createrId");
            return (Criteria) this;
        }

        public Criteria andCreaterIdNotLike(String value) {
            addCriterion("creater_id not like", value, "createrId");
            return (Criteria) this;
        }

        public Criteria andCreaterIdIn(List<String> values) {
            addCriterion("creater_id in", values, "createrId");
            return (Criteria) this;
        }

        public Criteria andCreaterIdNotIn(List<String> values) {
            addCriterion("creater_id not in", values, "createrId");
            return (Criteria) this;
        }

        public Criteria andCreaterIdBetween(String value1, String value2) {
            addCriterion("creater_id between", value1, value2, "createrId");
            return (Criteria) this;
        }

        public Criteria andCreaterIdNotBetween(String value1, String value2) {
            addCriterion("creater_id not between", value1, value2, "createrId");
            return (Criteria) this;
        }

        public Criteria andLawFirmNameIsNull() {
            addCriterion("law_firm_name is null");
            return (Criteria) this;
        }

        public Criteria andLawFirmNameIsNotNull() {
            addCriterion("law_firm_name is not null");
            return (Criteria) this;
        }

        public Criteria andLawFirmNameEqualTo(String value) {
            addCriterion("law_firm_name =", value, "lawFirmName");
            return (Criteria) this;
        }

        public Criteria andLawFirmNameNotEqualTo(String value) {
            addCriterion("law_firm_name <>", value, "lawFirmName");
            return (Criteria) this;
        }

        public Criteria andLawFirmNameGreaterThan(String value) {
            addCriterion("law_firm_name >", value, "lawFirmName");
            return (Criteria) this;
        }

        public Criteria andLawFirmNameGreaterThanOrEqualTo(String value) {
            addCriterion("law_firm_name >=", value, "lawFirmName");
            return (Criteria) this;
        }

        public Criteria andLawFirmNameLessThan(String value) {
            addCriterion("law_firm_name <", value, "lawFirmName");
            return (Criteria) this;
        }

        public Criteria andLawFirmNameLessThanOrEqualTo(String value) {
            addCriterion("law_firm_name <=", value, "lawFirmName");
            return (Criteria) this;
        }

        public Criteria andLawFirmNameLike(String value) {
            addCriterion("law_firm_name like", value, "lawFirmName");
            return (Criteria) this;
        }

        public Criteria andLawFirmNameNotLike(String value) {
            addCriterion("law_firm_name not like", value, "lawFirmName");
            return (Criteria) this;
        }

        public Criteria andLawFirmNameIn(List<String> values) {
            addCriterion("law_firm_name in", values, "lawFirmName");
            return (Criteria) this;
        }

        public Criteria andLawFirmNameNotIn(List<String> values) {
            addCriterion("law_firm_name not in", values, "lawFirmName");
            return (Criteria) this;
        }

        public Criteria andLawFirmNameBetween(String value1, String value2) {
            addCriterion("law_firm_name between", value1, value2, "lawFirmName");
            return (Criteria) this;
        }

        public Criteria andLawFirmNameNotBetween(String value1, String value2) {
            addCriterion("law_firm_name not between", value1, value2, "lawFirmName");
            return (Criteria) this;
        }

        public Criteria andAccountingFirmNameIsNull() {
            addCriterion("accounting_firm_name is null");
            return (Criteria) this;
        }

        public Criteria andAccountingFirmNameIsNotNull() {
            addCriterion("accounting_firm_name is not null");
            return (Criteria) this;
        }

        public Criteria andAccountingFirmNameEqualTo(String value) {
            addCriterion("accounting_firm_name =", value, "accountingFirmName");
            return (Criteria) this;
        }

        public Criteria andAccountingFirmNameNotEqualTo(String value) {
            addCriterion("accounting_firm_name <>", value, "accountingFirmName");
            return (Criteria) this;
        }

        public Criteria andAccountingFirmNameGreaterThan(String value) {
            addCriterion("accounting_firm_name >", value, "accountingFirmName");
            return (Criteria) this;
        }

        public Criteria andAccountingFirmNameGreaterThanOrEqualTo(String value) {
            addCriterion("accounting_firm_name >=", value, "accountingFirmName");
            return (Criteria) this;
        }

        public Criteria andAccountingFirmNameLessThan(String value) {
            addCriterion("accounting_firm_name <", value, "accountingFirmName");
            return (Criteria) this;
        }

        public Criteria andAccountingFirmNameLessThanOrEqualTo(String value) {
            addCriterion("accounting_firm_name <=", value, "accountingFirmName");
            return (Criteria) this;
        }

        public Criteria andAccountingFirmNameLike(String value) {
            addCriterion("accounting_firm_name like", value, "accountingFirmName");
            return (Criteria) this;
        }

        public Criteria andAccountingFirmNameNotLike(String value) {
            addCriterion("accounting_firm_name not like", value, "accountingFirmName");
            return (Criteria) this;
        }

        public Criteria andAccountingFirmNameIn(List<String> values) {
            addCriterion("accounting_firm_name in", values, "accountingFirmName");
            return (Criteria) this;
        }

        public Criteria andAccountingFirmNameNotIn(List<String> values) {
            addCriterion("accounting_firm_name not in", values, "accountingFirmName");
            return (Criteria) this;
        }

        public Criteria andAccountingFirmNameBetween(String value1, String value2) {
            addCriterion("accounting_firm_name between", value1, value2, "accountingFirmName");
            return (Criteria) this;
        }

        public Criteria andAccountingFirmNameNotBetween(String value1, String value2) {
            addCriterion("accounting_firm_name not between", value1, value2, "accountingFirmName");
            return (Criteria) this;
        }

        public Criteria andSupervisionBankIsNull() {
            addCriterion("supervision_bank is null");
            return (Criteria) this;
        }

        public Criteria andSupervisionBankIsNotNull() {
            addCriterion("supervision_bank is not null");
            return (Criteria) this;
        }

        public Criteria andSupervisionBankEqualTo(String value) {
            addCriterion("supervision_bank =", value, "supervisionBank");
            return (Criteria) this;
        }

        public Criteria andSupervisionBankNotEqualTo(String value) {
            addCriterion("supervision_bank <>", value, "supervisionBank");
            return (Criteria) this;
        }

        public Criteria andSupervisionBankGreaterThan(String value) {
            addCriterion("supervision_bank >", value, "supervisionBank");
            return (Criteria) this;
        }

        public Criteria andSupervisionBankGreaterThanOrEqualTo(String value) {
            addCriterion("supervision_bank >=", value, "supervisionBank");
            return (Criteria) this;
        }

        public Criteria andSupervisionBankLessThan(String value) {
            addCriterion("supervision_bank <", value, "supervisionBank");
            return (Criteria) this;
        }

        public Criteria andSupervisionBankLessThanOrEqualTo(String value) {
            addCriterion("supervision_bank <=", value, "supervisionBank");
            return (Criteria) this;
        }

        public Criteria andSupervisionBankLike(String value) {
            addCriterion("supervision_bank like", value, "supervisionBank");
            return (Criteria) this;
        }

        public Criteria andSupervisionBankNotLike(String value) {
            addCriterion("supervision_bank not like", value, "supervisionBank");
            return (Criteria) this;
        }

        public Criteria andSupervisionBankIn(List<String> values) {
            addCriterion("supervision_bank in", values, "supervisionBank");
            return (Criteria) this;
        }

        public Criteria andSupervisionBankNotIn(List<String> values) {
            addCriterion("supervision_bank not in", values, "supervisionBank");
            return (Criteria) this;
        }

        public Criteria andSupervisionBankBetween(String value1, String value2) {
            addCriterion("supervision_bank between", value1, value2, "supervisionBank");
            return (Criteria) this;
        }

        public Criteria andSupervisionBankNotBetween(String value1, String value2) {
            addCriterion("supervision_bank not between", value1, value2, "supervisionBank");
            return (Criteria) this;
        }

        public Criteria andCustodianBankIsNull() {
            addCriterion("custodian_bank is null");
            return (Criteria) this;
        }

        public Criteria andCustodianBankIsNotNull() {
            addCriterion("custodian_bank is not null");
            return (Criteria) this;
        }

        public Criteria andCustodianBankEqualTo(String value) {
            addCriterion("custodian_bank =", value, "custodianBank");
            return (Criteria) this;
        }

        public Criteria andCustodianBankNotEqualTo(String value) {
            addCriterion("custodian_bank <>", value, "custodianBank");
            return (Criteria) this;
        }

        public Criteria andCustodianBankGreaterThan(String value) {
            addCriterion("custodian_bank >", value, "custodianBank");
            return (Criteria) this;
        }

        public Criteria andCustodianBankGreaterThanOrEqualTo(String value) {
            addCriterion("custodian_bank >=", value, "custodianBank");
            return (Criteria) this;
        }

        public Criteria andCustodianBankLessThan(String value) {
            addCriterion("custodian_bank <", value, "custodianBank");
            return (Criteria) this;
        }

        public Criteria andCustodianBankLessThanOrEqualTo(String value) {
            addCriterion("custodian_bank <=", value, "custodianBank");
            return (Criteria) this;
        }

        public Criteria andCustodianBankLike(String value) {
            addCriterion("custodian_bank like", value, "custodianBank");
            return (Criteria) this;
        }

        public Criteria andCustodianBankNotLike(String value) {
            addCriterion("custodian_bank not like", value, "custodianBank");
            return (Criteria) this;
        }

        public Criteria andCustodianBankIn(List<String> values) {
            addCriterion("custodian_bank in", values, "custodianBank");
            return (Criteria) this;
        }

        public Criteria andCustodianBankNotIn(List<String> values) {
            addCriterion("custodian_bank not in", values, "custodianBank");
            return (Criteria) this;
        }

        public Criteria andCustodianBankBetween(String value1, String value2) {
            addCriterion("custodian_bank between", value1, value2, "custodianBank");
            return (Criteria) this;
        }

        public Criteria andCustodianBankNotBetween(String value1, String value2) {
            addCriterion("custodian_bank not between", value1, value2, "custodianBank");
            return (Criteria) this;
        }

        public Criteria andRatingAgencyIsNull() {
            addCriterion("rating_agency is null");
            return (Criteria) this;
        }

        public Criteria andRatingAgencyIsNotNull() {
            addCriterion("rating_agency is not null");
            return (Criteria) this;
        }

        public Criteria andRatingAgencyEqualTo(String value) {
            addCriterion("rating_agency =", value, "ratingAgency");
            return (Criteria) this;
        }

        public Criteria andRatingAgencyNotEqualTo(String value) {
            addCriterion("rating_agency <>", value, "ratingAgency");
            return (Criteria) this;
        }

        public Criteria andRatingAgencyGreaterThan(String value) {
            addCriterion("rating_agency >", value, "ratingAgency");
            return (Criteria) this;
        }

        public Criteria andRatingAgencyGreaterThanOrEqualTo(String value) {
            addCriterion("rating_agency >=", value, "ratingAgency");
            return (Criteria) this;
        }

        public Criteria andRatingAgencyLessThan(String value) {
            addCriterion("rating_agency <", value, "ratingAgency");
            return (Criteria) this;
        }

        public Criteria andRatingAgencyLessThanOrEqualTo(String value) {
            addCriterion("rating_agency <=", value, "ratingAgency");
            return (Criteria) this;
        }

        public Criteria andRatingAgencyLike(String value) {
            addCriterion("rating_agency like", value, "ratingAgency");
            return (Criteria) this;
        }

        public Criteria andRatingAgencyNotLike(String value) {
            addCriterion("rating_agency not like", value, "ratingAgency");
            return (Criteria) this;
        }

        public Criteria andRatingAgencyIn(List<String> values) {
            addCriterion("rating_agency in", values, "ratingAgency");
            return (Criteria) this;
        }

        public Criteria andRatingAgencyNotIn(List<String> values) {
            addCriterion("rating_agency not in", values, "ratingAgency");
            return (Criteria) this;
        }

        public Criteria andRatingAgencyBetween(String value1, String value2) {
            addCriterion("rating_agency between", value1, value2, "ratingAgency");
            return (Criteria) this;
        }

        public Criteria andRatingAgencyNotBetween(String value1, String value2) {
            addCriterion("rating_agency not between", value1, value2, "ratingAgency");
            return (Criteria) this;
        }

        public Criteria andAssetEvaluationAgencyIsNull() {
            addCriterion("asset_evaluation_agency is null");
            return (Criteria) this;
        }

        public Criteria andAssetEvaluationAgencyIsNotNull() {
            addCriterion("asset_evaluation_agency is not null");
            return (Criteria) this;
        }

        public Criteria andAssetEvaluationAgencyEqualTo(String value) {
            addCriterion("asset_evaluation_agency =", value, "assetEvaluationAgency");
            return (Criteria) this;
        }

        public Criteria andAssetEvaluationAgencyNotEqualTo(String value) {
            addCriterion("asset_evaluation_agency <>", value, "assetEvaluationAgency");
            return (Criteria) this;
        }

        public Criteria andAssetEvaluationAgencyGreaterThan(String value) {
            addCriterion("asset_evaluation_agency >", value, "assetEvaluationAgency");
            return (Criteria) this;
        }

        public Criteria andAssetEvaluationAgencyGreaterThanOrEqualTo(String value) {
            addCriterion("asset_evaluation_agency >=", value, "assetEvaluationAgency");
            return (Criteria) this;
        }

        public Criteria andAssetEvaluationAgencyLessThan(String value) {
            addCriterion("asset_evaluation_agency <", value, "assetEvaluationAgency");
            return (Criteria) this;
        }

        public Criteria andAssetEvaluationAgencyLessThanOrEqualTo(String value) {
            addCriterion("asset_evaluation_agency <=", value, "assetEvaluationAgency");
            return (Criteria) this;
        }

        public Criteria andAssetEvaluationAgencyLike(String value) {
            addCriterion("asset_evaluation_agency like", value, "assetEvaluationAgency");
            return (Criteria) this;
        }

        public Criteria andAssetEvaluationAgencyNotLike(String value) {
            addCriterion("asset_evaluation_agency not like", value, "assetEvaluationAgency");
            return (Criteria) this;
        }

        public Criteria andAssetEvaluationAgencyIn(List<String> values) {
            addCriterion("asset_evaluation_agency in", values, "assetEvaluationAgency");
            return (Criteria) this;
        }

        public Criteria andAssetEvaluationAgencyNotIn(List<String> values) {
            addCriterion("asset_evaluation_agency not in", values, "assetEvaluationAgency");
            return (Criteria) this;
        }

        public Criteria andAssetEvaluationAgencyBetween(String value1, String value2) {
            addCriterion("asset_evaluation_agency between", value1, value2, "assetEvaluationAgency");
            return (Criteria) this;
        }

        public Criteria andAssetEvaluationAgencyNotBetween(String value1, String value2) {
            addCriterion("asset_evaluation_agency not between", value1, value2, "assetEvaluationAgency");
            return (Criteria) this;
        }

        public Criteria andEstimateEstablishmentDateIsNull() {
            addCriterion("estimate_establishment_date is null");
            return (Criteria) this;
        }

        public Criteria andEstimateEstablishmentDateIsNotNull() {
            addCriterion("estimate_establishment_date is not null");
            return (Criteria) this;
        }

        public Criteria andEstimateEstablishmentDateEqualTo(String value) {
            addCriterion("estimate_establishment_date =", value, "estimateEstablishmentDate");
            return (Criteria) this;
        }

        public Criteria andEstimateEstablishmentDateNotEqualTo(String value) {
            addCriterion("estimate_establishment_date <>", value, "estimateEstablishmentDate");
            return (Criteria) this;
        }

        public Criteria andEstimateEstablishmentDateGreaterThan(String value) {
            addCriterion("estimate_establishment_date >", value, "estimateEstablishmentDate");
            return (Criteria) this;
        }

        public Criteria andEstimateEstablishmentDateGreaterThanOrEqualTo(String value) {
            addCriterion("estimate_establishment_date >=", value, "estimateEstablishmentDate");
            return (Criteria) this;
        }

        public Criteria andEstimateEstablishmentDateLessThan(String value) {
            addCriterion("estimate_establishment_date <", value, "estimateEstablishmentDate");
            return (Criteria) this;
        }

        public Criteria andEstimateEstablishmentDateLessThanOrEqualTo(String value) {
            addCriterion("estimate_establishment_date <=", value, "estimateEstablishmentDate");
            return (Criteria) this;
        }

        public Criteria andEstimateEstablishmentDateLike(String value) {
            addCriterion("estimate_establishment_date like", value, "estimateEstablishmentDate");
            return (Criteria) this;
        }

        public Criteria andEstimateEstablishmentDateNotLike(String value) {
            addCriterion("estimate_establishment_date not like", value, "estimateEstablishmentDate");
            return (Criteria) this;
        }

        public Criteria andEstimateEstablishmentDateIn(List<String> values) {
            addCriterion("estimate_establishment_date in", values, "estimateEstablishmentDate");
            return (Criteria) this;
        }

        public Criteria andEstimateEstablishmentDateNotIn(List<String> values) {
            addCriterion("estimate_establishment_date not in", values, "estimateEstablishmentDate");
            return (Criteria) this;
        }

        public Criteria andEstimateEstablishmentDateBetween(String value1, String value2) {
            addCriterion("estimate_establishment_date between", value1, value2, "estimateEstablishmentDate");
            return (Criteria) this;
        }

        public Criteria andEstimateEstablishmentDateNotBetween(String value1, String value2) {
            addCriterion("estimate_establishment_date not between", value1, value2, "estimateEstablishmentDate");
            return (Criteria) this;
        }

        public Criteria andEstablishmentDateIsNull() {
            addCriterion("establishment_date is null");
            return (Criteria) this;
        }

        public Criteria andEstablishmentDateIsNotNull() {
            addCriterion("establishment_date is not null");
            return (Criteria) this;
        }

        public Criteria andEstablishmentDateEqualTo(String value) {
            addCriterion("establishment_date =", value, "establishmentDate");
            return (Criteria) this;
        }

        public Criteria andEstablishmentDateNotEqualTo(String value) {
            addCriterion("establishment_date <>", value, "establishmentDate");
            return (Criteria) this;
        }

        public Criteria andEstablishmentDateGreaterThan(String value) {
            addCriterion("establishment_date >", value, "establishmentDate");
            return (Criteria) this;
        }

        public Criteria andEstablishmentDateGreaterThanOrEqualTo(String value) {
            addCriterion("establishment_date >=", value, "establishmentDate");
            return (Criteria) this;
        }

        public Criteria andEstablishmentDateLessThan(String value) {
            addCriterion("establishment_date <", value, "establishmentDate");
            return (Criteria) this;
        }

        public Criteria andEstablishmentDateLessThanOrEqualTo(String value) {
            addCriterion("establishment_date <=", value, "establishmentDate");
            return (Criteria) this;
        }

        public Criteria andEstablishmentDateLike(String value) {
            addCriterion("establishment_date like", value, "establishmentDate");
            return (Criteria) this;
        }

        public Criteria andEstablishmentDateNotLike(String value) {
            addCriterion("establishment_date not like", value, "establishmentDate");
            return (Criteria) this;
        }

        public Criteria andEstablishmentDateIn(List<String> values) {
            addCriterion("establishment_date in", values, "establishmentDate");
            return (Criteria) this;
        }

        public Criteria andEstablishmentDateNotIn(List<String> values) {
            addCriterion("establishment_date not in", values, "establishmentDate");
            return (Criteria) this;
        }

        public Criteria andEstablishmentDateBetween(String value1, String value2) {
            addCriterion("establishment_date between", value1, value2, "establishmentDate");
            return (Criteria) this;
        }

        public Criteria andEstablishmentDateNotBetween(String value1, String value2) {
            addCriterion("establishment_date not between", value1, value2, "establishmentDate");
            return (Criteria) this;
        }

        public Criteria andEstimateDueDateIsNull() {
            addCriterion("estimate_due_date is null");
            return (Criteria) this;
        }

        public Criteria andEstimateDueDateIsNotNull() {
            addCriterion("estimate_due_date is not null");
            return (Criteria) this;
        }

        public Criteria andEstimateDueDateEqualTo(String value) {
            addCriterion("estimate_due_date =", value, "estimateDueDate");
            return (Criteria) this;
        }

        public Criteria andEstimateDueDateNotEqualTo(String value) {
            addCriterion("estimate_due_date <>", value, "estimateDueDate");
            return (Criteria) this;
        }

        public Criteria andEstimateDueDateGreaterThan(String value) {
            addCriterion("estimate_due_date >", value, "estimateDueDate");
            return (Criteria) this;
        }

        public Criteria andEstimateDueDateGreaterThanOrEqualTo(String value) {
            addCriterion("estimate_due_date >=", value, "estimateDueDate");
            return (Criteria) this;
        }

        public Criteria andEstimateDueDateLessThan(String value) {
            addCriterion("estimate_due_date <", value, "estimateDueDate");
            return (Criteria) this;
        }

        public Criteria andEstimateDueDateLessThanOrEqualTo(String value) {
            addCriterion("estimate_due_date <=", value, "estimateDueDate");
            return (Criteria) this;
        }

        public Criteria andEstimateDueDateLike(String value) {
            addCriterion("estimate_due_date like", value, "estimateDueDate");
            return (Criteria) this;
        }

        public Criteria andEstimateDueDateNotLike(String value) {
            addCriterion("estimate_due_date not like", value, "estimateDueDate");
            return (Criteria) this;
        }

        public Criteria andEstimateDueDateIn(List<String> values) {
            addCriterion("estimate_due_date in", values, "estimateDueDate");
            return (Criteria) this;
        }

        public Criteria andEstimateDueDateNotIn(List<String> values) {
            addCriterion("estimate_due_date not in", values, "estimateDueDate");
            return (Criteria) this;
        }

        public Criteria andEstimateDueDateBetween(String value1, String value2) {
            addCriterion("estimate_due_date between", value1, value2, "estimateDueDate");
            return (Criteria) this;
        }

        public Criteria andEstimateDueDateNotBetween(String value1, String value2) {
            addCriterion("estimate_due_date not between", value1, value2, "estimateDueDate");
            return (Criteria) this;
        }

        public Criteria andStatutoryDueDateIsNull() {
            addCriterion("statutory_due_date is null");
            return (Criteria) this;
        }

        public Criteria andStatutoryDueDateIsNotNull() {
            addCriterion("statutory_due_date is not null");
            return (Criteria) this;
        }

        public Criteria andStatutoryDueDateEqualTo(String value) {
            addCriterion("statutory_due_date =", value, "statutoryDueDate");
            return (Criteria) this;
        }

        public Criteria andStatutoryDueDateNotEqualTo(String value) {
            addCriterion("statutory_due_date <>", value, "statutoryDueDate");
            return (Criteria) this;
        }

        public Criteria andStatutoryDueDateGreaterThan(String value) {
            addCriterion("statutory_due_date >", value, "statutoryDueDate");
            return (Criteria) this;
        }

        public Criteria andStatutoryDueDateGreaterThanOrEqualTo(String value) {
            addCriterion("statutory_due_date >=", value, "statutoryDueDate");
            return (Criteria) this;
        }

        public Criteria andStatutoryDueDateLessThan(String value) {
            addCriterion("statutory_due_date <", value, "statutoryDueDate");
            return (Criteria) this;
        }

        public Criteria andStatutoryDueDateLessThanOrEqualTo(String value) {
            addCriterion("statutory_due_date <=", value, "statutoryDueDate");
            return (Criteria) this;
        }

        public Criteria andStatutoryDueDateLike(String value) {
            addCriterion("statutory_due_date like", value, "statutoryDueDate");
            return (Criteria) this;
        }

        public Criteria andStatutoryDueDateNotLike(String value) {
            addCriterion("statutory_due_date not like", value, "statutoryDueDate");
            return (Criteria) this;
        }

        public Criteria andStatutoryDueDateIn(List<String> values) {
            addCriterion("statutory_due_date in", values, "statutoryDueDate");
            return (Criteria) this;
        }

        public Criteria andStatutoryDueDateNotIn(List<String> values) {
            addCriterion("statutory_due_date not in", values, "statutoryDueDate");
            return (Criteria) this;
        }

        public Criteria andStatutoryDueDateBetween(String value1, String value2) {
            addCriterion("statutory_due_date between", value1, value2, "statutoryDueDate");
            return (Criteria) this;
        }

        public Criteria andStatutoryDueDateNotBetween(String value1, String value2) {
            addCriterion("statutory_due_date not between", value1, value2, "statutoryDueDate");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(String value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(String value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLike(String value) {
            addCriterion("status like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotLike(String value) {
            addCriterion("status not like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<String> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<String> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(String value1, String value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(String value1, String value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andEncapsulationDateIsNull() {
            addCriterion("encapsulation_date is null");
            return (Criteria) this;
        }

        public Criteria andEncapsulationDateIsNotNull() {
            addCriterion("encapsulation_date is not null");
            return (Criteria) this;
        }

        public Criteria andEncapsulationDateEqualTo(String value) {
            addCriterion("encapsulation_date =", value, "encapsulationDate");
            return (Criteria) this;
        }

        public Criteria andEncapsulationDateNotEqualTo(String value) {
            addCriterion("encapsulation_date <>", value, "encapsulationDate");
            return (Criteria) this;
        }

        public Criteria andEncapsulationDateGreaterThan(String value) {
            addCriterion("encapsulation_date >", value, "encapsulationDate");
            return (Criteria) this;
        }

        public Criteria andEncapsulationDateGreaterThanOrEqualTo(String value) {
            addCriterion("encapsulation_date >=", value, "encapsulationDate");
            return (Criteria) this;
        }

        public Criteria andEncapsulationDateLessThan(String value) {
            addCriterion("encapsulation_date <", value, "encapsulationDate");
            return (Criteria) this;
        }

        public Criteria andEncapsulationDateLessThanOrEqualTo(String value) {
            addCriterion("encapsulation_date <=", value, "encapsulationDate");
            return (Criteria) this;
        }

        public Criteria andEncapsulationDateLike(String value) {
            addCriterion("encapsulation_date like", value, "encapsulationDate");
            return (Criteria) this;
        }

        public Criteria andEncapsulationDateNotLike(String value) {
            addCriterion("encapsulation_date not like", value, "encapsulationDate");
            return (Criteria) this;
        }

        public Criteria andEncapsulationDateIn(List<String> values) {
            addCriterion("encapsulation_date in", values, "encapsulationDate");
            return (Criteria) this;
        }

        public Criteria andEncapsulationDateNotIn(List<String> values) {
            addCriterion("encapsulation_date not in", values, "encapsulationDate");
            return (Criteria) this;
        }

        public Criteria andEncapsulationDateBetween(String value1, String value2) {
            addCriterion("encapsulation_date between", value1, value2, "encapsulationDate");
            return (Criteria) this;
        }

        public Criteria andEncapsulationDateNotBetween(String value1, String value2) {
            addCriterion("encapsulation_date not between", value1, value2, "encapsulationDate");
            return (Criteria) this;
        }

        public Criteria andRenewalPeriodIsNull() {
            addCriterion("renewal_period is null");
            return (Criteria) this;
        }

        public Criteria andRenewalPeriodIsNotNull() {
            addCriterion("renewal_period is not null");
            return (Criteria) this;
        }

        public Criteria andRenewalPeriodEqualTo(Integer value) {
            addCriterion("renewal_period =", value, "renewalPeriod");
            return (Criteria) this;
        }

        public Criteria andRenewalPeriodNotEqualTo(Integer value) {
            addCriterion("renewal_period <>", value, "renewalPeriod");
            return (Criteria) this;
        }

        public Criteria andRenewalPeriodGreaterThan(Integer value) {
            addCriterion("renewal_period >", value, "renewalPeriod");
            return (Criteria) this;
        }

        public Criteria andRenewalPeriodGreaterThanOrEqualTo(Integer value) {
            addCriterion("renewal_period >=", value, "renewalPeriod");
            return (Criteria) this;
        }

        public Criteria andRenewalPeriodLessThan(Integer value) {
            addCriterion("renewal_period <", value, "renewalPeriod");
            return (Criteria) this;
        }

        public Criteria andRenewalPeriodLessThanOrEqualTo(Integer value) {
            addCriterion("renewal_period <=", value, "renewalPeriod");
            return (Criteria) this;
        }

        public Criteria andRenewalPeriodIn(List<Integer> values) {
            addCriterion("renewal_period in", values, "renewalPeriod");
            return (Criteria) this;
        }

        public Criteria andRenewalPeriodNotIn(List<Integer> values) {
            addCriterion("renewal_period not in", values, "renewalPeriod");
            return (Criteria) this;
        }

        public Criteria andRenewalPeriodBetween(Integer value1, Integer value2) {
            addCriterion("renewal_period between", value1, value2, "renewalPeriod");
            return (Criteria) this;
        }

        public Criteria andRenewalPeriodNotBetween(Integer value1, Integer value2) {
            addCriterion("renewal_period not between", value1, value2, "renewalPeriod");
            return (Criteria) this;
        }

        public Criteria andGrossIsNull() {
            addCriterion("gross is null");
            return (Criteria) this;
        }

        public Criteria andGrossIsNotNull() {
            addCriterion("gross is not null");
            return (Criteria) this;
        }

        public Criteria andGrossEqualTo(Long value) {
            addCriterion("gross =", value, "gross");
            return (Criteria) this;
        }

        public Criteria andGrossNotEqualTo(Long value) {
            addCriterion("gross <>", value, "gross");
            return (Criteria) this;
        }

        public Criteria andGrossGreaterThan(Long value) {
            addCriterion("gross >", value, "gross");
            return (Criteria) this;
        }

        public Criteria andGrossGreaterThanOrEqualTo(Long value) {
            addCriterion("gross >=", value, "gross");
            return (Criteria) this;
        }

        public Criteria andGrossLessThan(Long value) {
            addCriterion("gross <", value, "gross");
            return (Criteria) this;
        }

        public Criteria andGrossLessThanOrEqualTo(Long value) {
            addCriterion("gross <=", value, "gross");
            return (Criteria) this;
        }

        public Criteria andGrossIn(List<Long> values) {
            addCriterion("gross in", values, "gross");
            return (Criteria) this;
        }

        public Criteria andGrossNotIn(List<Long> values) {
            addCriterion("gross not in", values, "gross");
            return (Criteria) this;
        }

        public Criteria andGrossBetween(Long value1, Long value2) {
            addCriterion("gross between", value1, value2, "gross");
            return (Criteria) this;
        }

        public Criteria andGrossNotBetween(Long value1, Long value2) {
            addCriterion("gross not between", value1, value2, "gross");
            return (Criteria) this;
        }

        public Criteria andPreferAIsNull() {
            addCriterion("prefer_a is null");
            return (Criteria) this;
        }

        public Criteria andPreferAIsNotNull() {
            addCriterion("prefer_a is not null");
            return (Criteria) this;
        }

        public Criteria andPreferAEqualTo(Long value) {
            addCriterion("prefer_a =", value, "preferA");
            return (Criteria) this;
        }

        public Criteria andPreferANotEqualTo(Long value) {
            addCriterion("prefer_a <>", value, "preferA");
            return (Criteria) this;
        }

        public Criteria andPreferAGreaterThan(Long value) {
            addCriterion("prefer_a >", value, "preferA");
            return (Criteria) this;
        }

        public Criteria andPreferAGreaterThanOrEqualTo(Long value) {
            addCriterion("prefer_a >=", value, "preferA");
            return (Criteria) this;
        }

        public Criteria andPreferALessThan(Long value) {
            addCriterion("prefer_a <", value, "preferA");
            return (Criteria) this;
        }

        public Criteria andPreferALessThanOrEqualTo(Long value) {
            addCriterion("prefer_a <=", value, "preferA");
            return (Criteria) this;
        }

        public Criteria andPreferAIn(List<Long> values) {
            addCriterion("prefer_a in", values, "preferA");
            return (Criteria) this;
        }

        public Criteria andPreferANotIn(List<Long> values) {
            addCriterion("prefer_a not in", values, "preferA");
            return (Criteria) this;
        }

        public Criteria andPreferABetween(Long value1, Long value2) {
            addCriterion("prefer_a between", value1, value2, "preferA");
            return (Criteria) this;
        }

        public Criteria andPreferANotBetween(Long value1, Long value2) {
            addCriterion("prefer_a not between", value1, value2, "preferA");
            return (Criteria) this;
        }

        public Criteria andPreferBIsNull() {
            addCriterion("prefer_b is null");
            return (Criteria) this;
        }

        public Criteria andPreferBIsNotNull() {
            addCriterion("prefer_b is not null");
            return (Criteria) this;
        }

        public Criteria andPreferBEqualTo(Long value) {
            addCriterion("prefer_b =", value, "preferB");
            return (Criteria) this;
        }

        public Criteria andPreferBNotEqualTo(Long value) {
            addCriterion("prefer_b <>", value, "preferB");
            return (Criteria) this;
        }

        public Criteria andPreferBGreaterThan(Long value) {
            addCriterion("prefer_b >", value, "preferB");
            return (Criteria) this;
        }

        public Criteria andPreferBGreaterThanOrEqualTo(Long value) {
            addCriterion("prefer_b >=", value, "preferB");
            return (Criteria) this;
        }

        public Criteria andPreferBLessThan(Long value) {
            addCriterion("prefer_b <", value, "preferB");
            return (Criteria) this;
        }

        public Criteria andPreferBLessThanOrEqualTo(Long value) {
            addCriterion("prefer_b <=", value, "preferB");
            return (Criteria) this;
        }

        public Criteria andPreferBIn(List<Long> values) {
            addCriterion("prefer_b in", values, "preferB");
            return (Criteria) this;
        }

        public Criteria andPreferBNotIn(List<Long> values) {
            addCriterion("prefer_b not in", values, "preferB");
            return (Criteria) this;
        }

        public Criteria andPreferBBetween(Long value1, Long value2) {
            addCriterion("prefer_b between", value1, value2, "preferB");
            return (Criteria) this;
        }

        public Criteria andPreferBNotBetween(Long value1, Long value2) {
            addCriterion("prefer_b not between", value1, value2, "preferB");
            return (Criteria) this;
        }

        public Criteria andDeferIsNull() {
            addCriterion("defer is null");
            return (Criteria) this;
        }

        public Criteria andDeferIsNotNull() {
            addCriterion("defer is not null");
            return (Criteria) this;
        }

        public Criteria andDeferEqualTo(Long value) {
            addCriterion("defer =", value, "defer");
            return (Criteria) this;
        }

        public Criteria andDeferNotEqualTo(Long value) {
            addCriterion("defer <>", value, "defer");
            return (Criteria) this;
        }

        public Criteria andDeferGreaterThan(Long value) {
            addCriterion("defer >", value, "defer");
            return (Criteria) this;
        }

        public Criteria andDeferGreaterThanOrEqualTo(Long value) {
            addCriterion("defer >=", value, "defer");
            return (Criteria) this;
        }

        public Criteria andDeferLessThan(Long value) {
            addCriterion("defer <", value, "defer");
            return (Criteria) this;
        }

        public Criteria andDeferLessThanOrEqualTo(Long value) {
            addCriterion("defer <=", value, "defer");
            return (Criteria) this;
        }

        public Criteria andDeferIn(List<Long> values) {
            addCriterion("defer in", values, "defer");
            return (Criteria) this;
        }

        public Criteria andDeferNotIn(List<Long> values) {
            addCriterion("defer not in", values, "defer");
            return (Criteria) this;
        }

        public Criteria andDeferBetween(Long value1, Long value2) {
            addCriterion("defer between", value1, value2, "defer");
            return (Criteria) this;
        }

        public Criteria andDeferNotBetween(Long value1, Long value2) {
            addCriterion("defer not between", value1, value2, "defer");
            return (Criteria) this;
        }

        public Criteria andTrustMethodIsNull() {
            addCriterion("trust_method is null");
            return (Criteria) this;
        }

        public Criteria andTrustMethodIsNotNull() {
            addCriterion("trust_method is not null");
            return (Criteria) this;
        }

        public Criteria andTrustMethodEqualTo(String value) {
            addCriterion("trust_method =", value, "trustMethod");
            return (Criteria) this;
        }

        public Criteria andTrustMethodNotEqualTo(String value) {
            addCriterion("trust_method <>", value, "trustMethod");
            return (Criteria) this;
        }

        public Criteria andTrustMethodGreaterThan(String value) {
            addCriterion("trust_method >", value, "trustMethod");
            return (Criteria) this;
        }

        public Criteria andTrustMethodGreaterThanOrEqualTo(String value) {
            addCriterion("trust_method >=", value, "trustMethod");
            return (Criteria) this;
        }

        public Criteria andTrustMethodLessThan(String value) {
            addCriterion("trust_method <", value, "trustMethod");
            return (Criteria) this;
        }

        public Criteria andTrustMethodLessThanOrEqualTo(String value) {
            addCriterion("trust_method <=", value, "trustMethod");
            return (Criteria) this;
        }

        public Criteria andTrustMethodLike(String value) {
            addCriterion("trust_method like", value, "trustMethod");
            return (Criteria) this;
        }

        public Criteria andTrustMethodNotLike(String value) {
            addCriterion("trust_method not like", value, "trustMethod");
            return (Criteria) this;
        }

        public Criteria andTrustMethodIn(List<String> values) {
            addCriterion("trust_method in", values, "trustMethod");
            return (Criteria) this;
        }

        public Criteria andTrustMethodNotIn(List<String> values) {
            addCriterion("trust_method not in", values, "trustMethod");
            return (Criteria) this;
        }

        public Criteria andTrustMethodBetween(String value1, String value2) {
            addCriterion("trust_method between", value1, value2, "trustMethod");
            return (Criteria) this;
        }

        public Criteria andTrustMethodNotBetween(String value1, String value2) {
            addCriterion("trust_method not between", value1, value2, "trustMethod");
            return (Criteria) this;
        }

        public Criteria andAssetPoolPrincipalBalanceIsNull() {
            addCriterion("asset_pool_principal_balance is null");
            return (Criteria) this;
        }

        public Criteria andAssetPoolPrincipalBalanceIsNotNull() {
            addCriterion("asset_pool_principal_balance is not null");
            return (Criteria) this;
        }

        public Criteria andAssetPoolPrincipalBalanceEqualTo(Long value) {
            addCriterion("asset_pool_principal_balance =", value, "assetPoolPrincipalBalance");
            return (Criteria) this;
        }

        public Criteria andAssetPoolPrincipalBalanceNotEqualTo(Long value) {
            addCriterion("asset_pool_principal_balance <>", value, "assetPoolPrincipalBalance");
            return (Criteria) this;
        }

        public Criteria andAssetPoolPrincipalBalanceGreaterThan(Long value) {
            addCriterion("asset_pool_principal_balance >", value, "assetPoolPrincipalBalance");
            return (Criteria) this;
        }

        public Criteria andAssetPoolPrincipalBalanceGreaterThanOrEqualTo(Long value) {
            addCriterion("asset_pool_principal_balance >=", value, "assetPoolPrincipalBalance");
            return (Criteria) this;
        }

        public Criteria andAssetPoolPrincipalBalanceLessThan(Long value) {
            addCriterion("asset_pool_principal_balance <", value, "assetPoolPrincipalBalance");
            return (Criteria) this;
        }

        public Criteria andAssetPoolPrincipalBalanceLessThanOrEqualTo(Long value) {
            addCriterion("asset_pool_principal_balance <=", value, "assetPoolPrincipalBalance");
            return (Criteria) this;
        }

        public Criteria andAssetPoolPrincipalBalanceIn(List<Long> values) {
            addCriterion("asset_pool_principal_balance in", values, "assetPoolPrincipalBalance");
            return (Criteria) this;
        }

        public Criteria andAssetPoolPrincipalBalanceNotIn(List<Long> values) {
            addCriterion("asset_pool_principal_balance not in", values, "assetPoolPrincipalBalance");
            return (Criteria) this;
        }

        public Criteria andAssetPoolPrincipalBalanceBetween(Long value1, Long value2) {
            addCriterion("asset_pool_principal_balance between", value1, value2, "assetPoolPrincipalBalance");
            return (Criteria) this;
        }

        public Criteria andAssetPoolPrincipalBalanceNotBetween(Long value1, Long value2) {
            addCriterion("asset_pool_principal_balance not between", value1, value2, "assetPoolPrincipalBalance");
            return (Criteria) this;
        }

        public Criteria andContractNumIsNull() {
            addCriterion("contract_num is null");
            return (Criteria) this;
        }

        public Criteria andContractNumIsNotNull() {
            addCriterion("contract_num is not null");
            return (Criteria) this;
        }

        public Criteria andContractNumEqualTo(Integer value) {
            addCriterion("contract_num =", value, "contractNum");
            return (Criteria) this;
        }

        public Criteria andContractNumNotEqualTo(Integer value) {
            addCriterion("contract_num <>", value, "contractNum");
            return (Criteria) this;
        }

        public Criteria andContractNumGreaterThan(Integer value) {
            addCriterion("contract_num >", value, "contractNum");
            return (Criteria) this;
        }

        public Criteria andContractNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("contract_num >=", value, "contractNum");
            return (Criteria) this;
        }

        public Criteria andContractNumLessThan(Integer value) {
            addCriterion("contract_num <", value, "contractNum");
            return (Criteria) this;
        }

        public Criteria andContractNumLessThanOrEqualTo(Integer value) {
            addCriterion("contract_num <=", value, "contractNum");
            return (Criteria) this;
        }

        public Criteria andContractNumIn(List<Integer> values) {
            addCriterion("contract_num in", values, "contractNum");
            return (Criteria) this;
        }

        public Criteria andContractNumNotIn(List<Integer> values) {
            addCriterion("contract_num not in", values, "contractNum");
            return (Criteria) this;
        }

        public Criteria andContractNumBetween(Integer value1, Integer value2) {
            addCriterion("contract_num between", value1, value2, "contractNum");
            return (Criteria) this;
        }

        public Criteria andContractNumNotBetween(Integer value1, Integer value2) {
            addCriterion("contract_num not between", value1, value2, "contractNum");
            return (Criteria) this;
        }

        public Criteria andWeightedAverageContractPeriodIsNull() {
            addCriterion("weighted_average_contract_period is null");
            return (Criteria) this;
        }

        public Criteria andWeightedAverageContractPeriodIsNotNull() {
            addCriterion("weighted_average_contract_period is not null");
            return (Criteria) this;
        }

        public Criteria andWeightedAverageContractPeriodEqualTo(Integer value) {
            addCriterion("weighted_average_contract_period =", value, "weightedAverageContractPeriod");
            return (Criteria) this;
        }

        public Criteria andWeightedAverageContractPeriodNotEqualTo(Integer value) {
            addCriterion("weighted_average_contract_period <>", value, "weightedAverageContractPeriod");
            return (Criteria) this;
        }

        public Criteria andWeightedAverageContractPeriodGreaterThan(Integer value) {
            addCriterion("weighted_average_contract_period >", value, "weightedAverageContractPeriod");
            return (Criteria) this;
        }

        public Criteria andWeightedAverageContractPeriodGreaterThanOrEqualTo(Integer value) {
            addCriterion("weighted_average_contract_period >=", value, "weightedAverageContractPeriod");
            return (Criteria) this;
        }

        public Criteria andWeightedAverageContractPeriodLessThan(Integer value) {
            addCriterion("weighted_average_contract_period <", value, "weightedAverageContractPeriod");
            return (Criteria) this;
        }

        public Criteria andWeightedAverageContractPeriodLessThanOrEqualTo(Integer value) {
            addCriterion("weighted_average_contract_period <=", value, "weightedAverageContractPeriod");
            return (Criteria) this;
        }

        public Criteria andWeightedAverageContractPeriodIn(List<Integer> values) {
            addCriterion("weighted_average_contract_period in", values, "weightedAverageContractPeriod");
            return (Criteria) this;
        }

        public Criteria andWeightedAverageContractPeriodNotIn(List<Integer> values) {
            addCriterion("weighted_average_contract_period not in", values, "weightedAverageContractPeriod");
            return (Criteria) this;
        }

        public Criteria andWeightedAverageContractPeriodBetween(Integer value1, Integer value2) {
            addCriterion("weighted_average_contract_period between", value1, value2, "weightedAverageContractPeriod");
            return (Criteria) this;
        }

        public Criteria andWeightedAverageContractPeriodNotBetween(Integer value1, Integer value2) {
            addCriterion("weighted_average_contract_period not between", value1, value2, "weightedAverageContractPeriod");
            return (Criteria) this;
        }

        public Criteria andFinanceNumIsNull() {
            addCriterion("finance_num is null");
            return (Criteria) this;
        }

        public Criteria andFinanceNumIsNotNull() {
            addCriterion("finance_num is not null");
            return (Criteria) this;
        }

        public Criteria andFinanceNumEqualTo(Integer value) {
            addCriterion("finance_num =", value, "financeNum");
            return (Criteria) this;
        }

        public Criteria andFinanceNumNotEqualTo(Integer value) {
            addCriterion("finance_num <>", value, "financeNum");
            return (Criteria) this;
        }

        public Criteria andFinanceNumGreaterThan(Integer value) {
            addCriterion("finance_num >", value, "financeNum");
            return (Criteria) this;
        }

        public Criteria andFinanceNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("finance_num >=", value, "financeNum");
            return (Criteria) this;
        }

        public Criteria andFinanceNumLessThan(Integer value) {
            addCriterion("finance_num <", value, "financeNum");
            return (Criteria) this;
        }

        public Criteria andFinanceNumLessThanOrEqualTo(Integer value) {
            addCriterion("finance_num <=", value, "financeNum");
            return (Criteria) this;
        }

        public Criteria andFinanceNumIn(List<Integer> values) {
            addCriterion("finance_num in", values, "financeNum");
            return (Criteria) this;
        }

        public Criteria andFinanceNumNotIn(List<Integer> values) {
            addCriterion("finance_num not in", values, "financeNum");
            return (Criteria) this;
        }

        public Criteria andFinanceNumBetween(Integer value1, Integer value2) {
            addCriterion("finance_num between", value1, value2, "financeNum");
            return (Criteria) this;
        }

        public Criteria andFinanceNumNotBetween(Integer value1, Integer value2) {
            addCriterion("finance_num not between", value1, value2, "financeNum");
            return (Criteria) this;
        }

        public Criteria andWeightedAverageInterestRateIsNull() {
            addCriterion("weighted_average_interest_rate is null");
            return (Criteria) this;
        }

        public Criteria andWeightedAverageInterestRateIsNotNull() {
            addCriterion("weighted_average_interest_rate is not null");
            return (Criteria) this;
        }

        public Criteria andWeightedAverageInterestRateEqualTo(BigDecimal value) {
            addCriterion("weighted_average_interest_rate =", value, "weightedAverageInterestRate");
            return (Criteria) this;
        }

        public Criteria andWeightedAverageInterestRateNotEqualTo(BigDecimal value) {
            addCriterion("weighted_average_interest_rate <>", value, "weightedAverageInterestRate");
            return (Criteria) this;
        }

        public Criteria andWeightedAverageInterestRateGreaterThan(BigDecimal value) {
            addCriterion("weighted_average_interest_rate >", value, "weightedAverageInterestRate");
            return (Criteria) this;
        }

        public Criteria andWeightedAverageInterestRateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("weighted_average_interest_rate >=", value, "weightedAverageInterestRate");
            return (Criteria) this;
        }

        public Criteria andWeightedAverageInterestRateLessThan(BigDecimal value) {
            addCriterion("weighted_average_interest_rate <", value, "weightedAverageInterestRate");
            return (Criteria) this;
        }

        public Criteria andWeightedAverageInterestRateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("weighted_average_interest_rate <=", value, "weightedAverageInterestRate");
            return (Criteria) this;
        }

        public Criteria andWeightedAverageInterestRateIn(List<BigDecimal> values) {
            addCriterion("weighted_average_interest_rate in", values, "weightedAverageInterestRate");
            return (Criteria) this;
        }

        public Criteria andWeightedAverageInterestRateNotIn(List<BigDecimal> values) {
            addCriterion("weighted_average_interest_rate not in", values, "weightedAverageInterestRate");
            return (Criteria) this;
        }

        public Criteria andWeightedAverageInterestRateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("weighted_average_interest_rate between", value1, value2, "weightedAverageInterestRate");
            return (Criteria) this;
        }

        public Criteria andWeightedAverageInterestRateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("weighted_average_interest_rate not between", value1, value2, "weightedAverageInterestRate");
            return (Criteria) this;
        }

        public Criteria andCyclePurchaseStructureIsNull() {
            addCriterion("cycle_purchase_structure is null");
            return (Criteria) this;
        }

        public Criteria andCyclePurchaseStructureIsNotNull() {
            addCriterion("cycle_purchase_structure is not null");
            return (Criteria) this;
        }

        public Criteria andCyclePurchaseStructureEqualTo(Boolean value) {
            addCriterion("cycle_purchase_structure =", value, "cyclePurchaseStructure");
            return (Criteria) this;
        }

        public Criteria andCyclePurchaseStructureNotEqualTo(Boolean value) {
            addCriterion("cycle_purchase_structure <>", value, "cyclePurchaseStructure");
            return (Criteria) this;
        }

        public Criteria andCyclePurchaseStructureGreaterThan(Boolean value) {
            addCriterion("cycle_purchase_structure >", value, "cyclePurchaseStructure");
            return (Criteria) this;
        }

        public Criteria andCyclePurchaseStructureGreaterThanOrEqualTo(Boolean value) {
            addCriterion("cycle_purchase_structure >=", value, "cyclePurchaseStructure");
            return (Criteria) this;
        }

        public Criteria andCyclePurchaseStructureLessThan(Boolean value) {
            addCriterion("cycle_purchase_structure <", value, "cyclePurchaseStructure");
            return (Criteria) this;
        }

        public Criteria andCyclePurchaseStructureLessThanOrEqualTo(Boolean value) {
            addCriterion("cycle_purchase_structure <=", value, "cyclePurchaseStructure");
            return (Criteria) this;
        }

        public Criteria andCyclePurchaseStructureIn(List<Boolean> values) {
            addCriterion("cycle_purchase_structure in", values, "cyclePurchaseStructure");
            return (Criteria) this;
        }

        public Criteria andCyclePurchaseStructureNotIn(List<Boolean> values) {
            addCriterion("cycle_purchase_structure not in", values, "cyclePurchaseStructure");
            return (Criteria) this;
        }

        public Criteria andCyclePurchaseStructureBetween(Boolean value1, Boolean value2) {
            addCriterion("cycle_purchase_structure between", value1, value2, "cyclePurchaseStructure");
            return (Criteria) this;
        }

        public Criteria andCyclePurchaseStructureNotBetween(Boolean value1, Boolean value2) {
            addCriterion("cycle_purchase_structure not between", value1, value2, "cyclePurchaseStructure");
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

        public Criteria andDeletedIsNull() {
            addCriterion("deleted is null");
            return (Criteria) this;
        }

        public Criteria andDeletedIsNotNull() {
            addCriterion("deleted is not null");
            return (Criteria) this;
        }

        public Criteria andDeletedEqualTo(Boolean value) {
            addCriterion("deleted =", value, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedNotEqualTo(Boolean value) {
            addCriterion("deleted <>", value, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedGreaterThan(Boolean value) {
            addCriterion("deleted >", value, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedGreaterThanOrEqualTo(Boolean value) {
            addCriterion("deleted >=", value, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedLessThan(Boolean value) {
            addCriterion("deleted <", value, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedLessThanOrEqualTo(Boolean value) {
            addCriterion("deleted <=", value, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedIn(List<Boolean> values) {
            addCriterion("deleted in", values, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedNotIn(List<Boolean> values) {
            addCriterion("deleted not in", values, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedBetween(Boolean value1, Boolean value2) {
            addCriterion("deleted between", value1, value2, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedNotBetween(Boolean value1, Boolean value2) {
            addCriterion("deleted not between", value1, value2, "deleted");
            return (Criteria) this;
        }

        public Criteria andCyclePeriodIsNull() {
            addCriterion("cycle_period is null");
            return (Criteria) this;
        }

        public Criteria andCyclePeriodIsNotNull() {
            addCriterion("cycle_period is not null");
            return (Criteria) this;
        }

        public Criteria andCyclePeriodEqualTo(Integer value) {
            addCriterion("cycle_period =", value, "cyclePeriod");
            return (Criteria) this;
        }

        public Criteria andCyclePeriodNotEqualTo(Integer value) {
            addCriterion("cycle_period <>", value, "cyclePeriod");
            return (Criteria) this;
        }

        public Criteria andCyclePeriodGreaterThan(Integer value) {
            addCriterion("cycle_period >", value, "cyclePeriod");
            return (Criteria) this;
        }

        public Criteria andCyclePeriodGreaterThanOrEqualTo(Integer value) {
            addCriterion("cycle_period >=", value, "cyclePeriod");
            return (Criteria) this;
        }

        public Criteria andCyclePeriodLessThan(Integer value) {
            addCriterion("cycle_period <", value, "cyclePeriod");
            return (Criteria) this;
        }

        public Criteria andCyclePeriodLessThanOrEqualTo(Integer value) {
            addCriterion("cycle_period <=", value, "cyclePeriod");
            return (Criteria) this;
        }

        public Criteria andCyclePeriodIn(List<Integer> values) {
            addCriterion("cycle_period in", values, "cyclePeriod");
            return (Criteria) this;
        }

        public Criteria andCyclePeriodNotIn(List<Integer> values) {
            addCriterion("cycle_period not in", values, "cyclePeriod");
            return (Criteria) this;
        }

        public Criteria andCyclePeriodBetween(Integer value1, Integer value2) {
            addCriterion("cycle_period between", value1, value2, "cyclePeriod");
            return (Criteria) this;
        }

        public Criteria andCyclePeriodNotBetween(Integer value1, Integer value2) {
            addCriterion("cycle_period not between", value1, value2, "cyclePeriod");
            return (Criteria) this;
        }

        public Criteria andCapitalAccumulationDateIsNull() {
            addCriterion("capital_accumulation_date is null");
            return (Criteria) this;
        }

        public Criteria andCapitalAccumulationDateIsNotNull() {
            addCriterion("capital_accumulation_date is not null");
            return (Criteria) this;
        }

        public Criteria andCapitalAccumulationDateEqualTo(String value) {
            addCriterion("capital_accumulation_date =", value, "capitalAccumulationDate");
            return (Criteria) this;
        }

        public Criteria andCapitalAccumulationDateNotEqualTo(String value) {
            addCriterion("capital_accumulation_date <>", value, "capitalAccumulationDate");
            return (Criteria) this;
        }

        public Criteria andCapitalAccumulationDateGreaterThan(String value) {
            addCriterion("capital_accumulation_date >", value, "capitalAccumulationDate");
            return (Criteria) this;
        }

        public Criteria andCapitalAccumulationDateGreaterThanOrEqualTo(String value) {
            addCriterion("capital_accumulation_date >=", value, "capitalAccumulationDate");
            return (Criteria) this;
        }

        public Criteria andCapitalAccumulationDateLessThan(String value) {
            addCriterion("capital_accumulation_date <", value, "capitalAccumulationDate");
            return (Criteria) this;
        }

        public Criteria andCapitalAccumulationDateLessThanOrEqualTo(String value) {
            addCriterion("capital_accumulation_date <=", value, "capitalAccumulationDate");
            return (Criteria) this;
        }

        public Criteria andCapitalAccumulationDateLike(String value) {
            addCriterion("capital_accumulation_date like", value, "capitalAccumulationDate");
            return (Criteria) this;
        }

        public Criteria andCapitalAccumulationDateNotLike(String value) {
            addCriterion("capital_accumulation_date not like", value, "capitalAccumulationDate");
            return (Criteria) this;
        }

        public Criteria andCapitalAccumulationDateIn(List<String> values) {
            addCriterion("capital_accumulation_date in", values, "capitalAccumulationDate");
            return (Criteria) this;
        }

        public Criteria andCapitalAccumulationDateNotIn(List<String> values) {
            addCriterion("capital_accumulation_date not in", values, "capitalAccumulationDate");
            return (Criteria) this;
        }

        public Criteria andCapitalAccumulationDateBetween(String value1, String value2) {
            addCriterion("capital_accumulation_date between", value1, value2, "capitalAccumulationDate");
            return (Criteria) this;
        }

        public Criteria andCapitalAccumulationDateNotBetween(String value1, String value2) {
            addCriterion("capital_accumulation_date not between", value1, value2, "capitalAccumulationDate");
            return (Criteria) this;
        }

        public Criteria andCyclePurchaseDateIsNull() {
            addCriterion("cycle_purchase_date is null");
            return (Criteria) this;
        }

        public Criteria andCyclePurchaseDateIsNotNull() {
            addCriterion("cycle_purchase_date is not null");
            return (Criteria) this;
        }

        public Criteria andCyclePurchaseDateEqualTo(String value) {
            addCriterion("cycle_purchase_date =", value, "cyclePurchaseDate");
            return (Criteria) this;
        }

        public Criteria andCyclePurchaseDateNotEqualTo(String value) {
            addCriterion("cycle_purchase_date <>", value, "cyclePurchaseDate");
            return (Criteria) this;
        }

        public Criteria andCyclePurchaseDateGreaterThan(String value) {
            addCriterion("cycle_purchase_date >", value, "cyclePurchaseDate");
            return (Criteria) this;
        }

        public Criteria andCyclePurchaseDateGreaterThanOrEqualTo(String value) {
            addCriterion("cycle_purchase_date >=", value, "cyclePurchaseDate");
            return (Criteria) this;
        }

        public Criteria andCyclePurchaseDateLessThan(String value) {
            addCriterion("cycle_purchase_date <", value, "cyclePurchaseDate");
            return (Criteria) this;
        }

        public Criteria andCyclePurchaseDateLessThanOrEqualTo(String value) {
            addCriterion("cycle_purchase_date <=", value, "cyclePurchaseDate");
            return (Criteria) this;
        }

        public Criteria andCyclePurchaseDateLike(String value) {
            addCriterion("cycle_purchase_date like", value, "cyclePurchaseDate");
            return (Criteria) this;
        }

        public Criteria andCyclePurchaseDateNotLike(String value) {
            addCriterion("cycle_purchase_date not like", value, "cyclePurchaseDate");
            return (Criteria) this;
        }

        public Criteria andCyclePurchaseDateIn(List<String> values) {
            addCriterion("cycle_purchase_date in", values, "cyclePurchaseDate");
            return (Criteria) this;
        }

        public Criteria andCyclePurchaseDateNotIn(List<String> values) {
            addCriterion("cycle_purchase_date not in", values, "cyclePurchaseDate");
            return (Criteria) this;
        }

        public Criteria andCyclePurchaseDateBetween(String value1, String value2) {
            addCriterion("cycle_purchase_date between", value1, value2, "cyclePurchaseDate");
            return (Criteria) this;
        }

        public Criteria andCyclePurchaseDateNotBetween(String value1, String value2) {
            addCriterion("cycle_purchase_date not between", value1, value2, "cyclePurchaseDate");
            return (Criteria) this;
        }

        public Criteria andIncomeRecoveryDateIsNull() {
            addCriterion("income_recovery_date is null");
            return (Criteria) this;
        }

        public Criteria andIncomeRecoveryDateIsNotNull() {
            addCriterion("income_recovery_date is not null");
            return (Criteria) this;
        }

        public Criteria andIncomeRecoveryDateEqualTo(String value) {
            addCriterion("income_recovery_date =", value, "incomeRecoveryDate");
            return (Criteria) this;
        }

        public Criteria andIncomeRecoveryDateNotEqualTo(String value) {
            addCriterion("income_recovery_date <>", value, "incomeRecoveryDate");
            return (Criteria) this;
        }

        public Criteria andIncomeRecoveryDateGreaterThan(String value) {
            addCriterion("income_recovery_date >", value, "incomeRecoveryDate");
            return (Criteria) this;
        }

        public Criteria andIncomeRecoveryDateGreaterThanOrEqualTo(String value) {
            addCriterion("income_recovery_date >=", value, "incomeRecoveryDate");
            return (Criteria) this;
        }

        public Criteria andIncomeRecoveryDateLessThan(String value) {
            addCriterion("income_recovery_date <", value, "incomeRecoveryDate");
            return (Criteria) this;
        }

        public Criteria andIncomeRecoveryDateLessThanOrEqualTo(String value) {
            addCriterion("income_recovery_date <=", value, "incomeRecoveryDate");
            return (Criteria) this;
        }

        public Criteria andIncomeRecoveryDateLike(String value) {
            addCriterion("income_recovery_date like", value, "incomeRecoveryDate");
            return (Criteria) this;
        }

        public Criteria andIncomeRecoveryDateNotLike(String value) {
            addCriterion("income_recovery_date not like", value, "incomeRecoveryDate");
            return (Criteria) this;
        }

        public Criteria andIncomeRecoveryDateIn(List<String> values) {
            addCriterion("income_recovery_date in", values, "incomeRecoveryDate");
            return (Criteria) this;
        }

        public Criteria andIncomeRecoveryDateNotIn(List<String> values) {
            addCriterion("income_recovery_date not in", values, "incomeRecoveryDate");
            return (Criteria) this;
        }

        public Criteria andIncomeRecoveryDateBetween(String value1, String value2) {
            addCriterion("income_recovery_date between", value1, value2, "incomeRecoveryDate");
            return (Criteria) this;
        }

        public Criteria andIncomeRecoveryDateNotBetween(String value1, String value2) {
            addCriterion("income_recovery_date not between", value1, value2, "incomeRecoveryDate");
            return (Criteria) this;
        }

        public Criteria andRecyclingPaymentDateIsNull() {
            addCriterion("recycling_payment_date is null");
            return (Criteria) this;
        }

        public Criteria andRecyclingPaymentDateIsNotNull() {
            addCriterion("recycling_payment_date is not null");
            return (Criteria) this;
        }

        public Criteria andRecyclingPaymentDateEqualTo(String value) {
            addCriterion("recycling_payment_date =", value, "recyclingPaymentDate");
            return (Criteria) this;
        }

        public Criteria andRecyclingPaymentDateNotEqualTo(String value) {
            addCriterion("recycling_payment_date <>", value, "recyclingPaymentDate");
            return (Criteria) this;
        }

        public Criteria andRecyclingPaymentDateGreaterThan(String value) {
            addCriterion("recycling_payment_date >", value, "recyclingPaymentDate");
            return (Criteria) this;
        }

        public Criteria andRecyclingPaymentDateGreaterThanOrEqualTo(String value) {
            addCriterion("recycling_payment_date >=", value, "recyclingPaymentDate");
            return (Criteria) this;
        }

        public Criteria andRecyclingPaymentDateLessThan(String value) {
            addCriterion("recycling_payment_date <", value, "recyclingPaymentDate");
            return (Criteria) this;
        }

        public Criteria andRecyclingPaymentDateLessThanOrEqualTo(String value) {
            addCriterion("recycling_payment_date <=", value, "recyclingPaymentDate");
            return (Criteria) this;
        }

        public Criteria andRecyclingPaymentDateLike(String value) {
            addCriterion("recycling_payment_date like", value, "recyclingPaymentDate");
            return (Criteria) this;
        }

        public Criteria andRecyclingPaymentDateNotLike(String value) {
            addCriterion("recycling_payment_date not like", value, "recyclingPaymentDate");
            return (Criteria) this;
        }

        public Criteria andRecyclingPaymentDateIn(List<String> values) {
            addCriterion("recycling_payment_date in", values, "recyclingPaymentDate");
            return (Criteria) this;
        }

        public Criteria andRecyclingPaymentDateNotIn(List<String> values) {
            addCriterion("recycling_payment_date not in", values, "recyclingPaymentDate");
            return (Criteria) this;
        }

        public Criteria andRecyclingPaymentDateBetween(String value1, String value2) {
            addCriterion("recycling_payment_date between", value1, value2, "recyclingPaymentDate");
            return (Criteria) this;
        }

        public Criteria andRecyclingPaymentDateNotBetween(String value1, String value2) {
            addCriterion("recycling_payment_date not between", value1, value2, "recyclingPaymentDate");
            return (Criteria) this;
        }

        public Criteria andExcessFundIsNull() {
            addCriterion("excess_fund is null");
            return (Criteria) this;
        }

        public Criteria andExcessFundIsNotNull() {
            addCriterion("excess_fund is not null");
            return (Criteria) this;
        }

        public Criteria andExcessFundEqualTo(Long value) {
            addCriterion("excess_fund =", value, "excessFund");
            return (Criteria) this;
        }

        public Criteria andExcessFundNotEqualTo(Long value) {
            addCriterion("excess_fund <>", value, "excessFund");
            return (Criteria) this;
        }

        public Criteria andExcessFundGreaterThan(Long value) {
            addCriterion("excess_fund >", value, "excessFund");
            return (Criteria) this;
        }

        public Criteria andExcessFundGreaterThanOrEqualTo(Long value) {
            addCriterion("excess_fund >=", value, "excessFund");
            return (Criteria) this;
        }

        public Criteria andExcessFundLessThan(Long value) {
            addCriterion("excess_fund <", value, "excessFund");
            return (Criteria) this;
        }

        public Criteria andExcessFundLessThanOrEqualTo(Long value) {
            addCriterion("excess_fund <=", value, "excessFund");
            return (Criteria) this;
        }

        public Criteria andExcessFundIn(List<Long> values) {
            addCriterion("excess_fund in", values, "excessFund");
            return (Criteria) this;
        }

        public Criteria andExcessFundNotIn(List<Long> values) {
            addCriterion("excess_fund not in", values, "excessFund");
            return (Criteria) this;
        }

        public Criteria andExcessFundBetween(Long value1, Long value2) {
            addCriterion("excess_fund between", value1, value2, "excessFund");
            return (Criteria) this;
        }

        public Criteria andExcessFundNotBetween(Long value1, Long value2) {
            addCriterion("excess_fund not between", value1, value2, "excessFund");
            return (Criteria) this;
        }

        public Criteria andCyclePurchaseTermIsNull() {
            addCriterion("cycle_purchase_term is null");
            return (Criteria) this;
        }

        public Criteria andCyclePurchaseTermIsNotNull() {
            addCriterion("cycle_purchase_term is not null");
            return (Criteria) this;
        }

        public Criteria andCyclePurchaseTermEqualTo(String value) {
            addCriterion("cycle_purchase_term =", value, "cyclePurchaseTerm");
            return (Criteria) this;
        }

        public Criteria andCyclePurchaseTermNotEqualTo(String value) {
            addCriterion("cycle_purchase_term <>", value, "cyclePurchaseTerm");
            return (Criteria) this;
        }

        public Criteria andCyclePurchaseTermGreaterThan(String value) {
            addCriterion("cycle_purchase_term >", value, "cyclePurchaseTerm");
            return (Criteria) this;
        }

        public Criteria andCyclePurchaseTermGreaterThanOrEqualTo(String value) {
            addCriterion("cycle_purchase_term >=", value, "cyclePurchaseTerm");
            return (Criteria) this;
        }

        public Criteria andCyclePurchaseTermLessThan(String value) {
            addCriterion("cycle_purchase_term <", value, "cyclePurchaseTerm");
            return (Criteria) this;
        }

        public Criteria andCyclePurchaseTermLessThanOrEqualTo(String value) {
            addCriterion("cycle_purchase_term <=", value, "cyclePurchaseTerm");
            return (Criteria) this;
        }

        public Criteria andCyclePurchaseTermLike(String value) {
            addCriterion("cycle_purchase_term like", value, "cyclePurchaseTerm");
            return (Criteria) this;
        }

        public Criteria andCyclePurchaseTermNotLike(String value) {
            addCriterion("cycle_purchase_term not like", value, "cyclePurchaseTerm");
            return (Criteria) this;
        }

        public Criteria andCyclePurchaseTermIn(List<String> values) {
            addCriterion("cycle_purchase_term in", values, "cyclePurchaseTerm");
            return (Criteria) this;
        }

        public Criteria andCyclePurchaseTermNotIn(List<String> values) {
            addCriterion("cycle_purchase_term not in", values, "cyclePurchaseTerm");
            return (Criteria) this;
        }

        public Criteria andCyclePurchaseTermBetween(String value1, String value2) {
            addCriterion("cycle_purchase_term between", value1, value2, "cyclePurchaseTerm");
            return (Criteria) this;
        }

        public Criteria andCyclePurchaseTermNotBetween(String value1, String value2) {
            addCriterion("cycle_purchase_term not between", value1, value2, "cyclePurchaseTerm");
            return (Criteria) this;
        }

        public Criteria andUnderlyingAssetQualityStandardIsNull() {
            addCriterion("underlying_asset_quality_standard is null");
            return (Criteria) this;
        }

        public Criteria andUnderlyingAssetQualityStandardIsNotNull() {
            addCriterion("underlying_asset_quality_standard is not null");
            return (Criteria) this;
        }

        public Criteria andUnderlyingAssetQualityStandardEqualTo(String value) {
            addCriterion("underlying_asset_quality_standard =", value, "underlyingAssetQualityStandard");
            return (Criteria) this;
        }

        public Criteria andUnderlyingAssetQualityStandardNotEqualTo(String value) {
            addCriterion("underlying_asset_quality_standard <>", value, "underlyingAssetQualityStandard");
            return (Criteria) this;
        }

        public Criteria andUnderlyingAssetQualityStandardGreaterThan(String value) {
            addCriterion("underlying_asset_quality_standard >", value, "underlyingAssetQualityStandard");
            return (Criteria) this;
        }

        public Criteria andUnderlyingAssetQualityStandardGreaterThanOrEqualTo(String value) {
            addCriterion("underlying_asset_quality_standard >=", value, "underlyingAssetQualityStandard");
            return (Criteria) this;
        }

        public Criteria andUnderlyingAssetQualityStandardLessThan(String value) {
            addCriterion("underlying_asset_quality_standard <", value, "underlyingAssetQualityStandard");
            return (Criteria) this;
        }

        public Criteria andUnderlyingAssetQualityStandardLessThanOrEqualTo(String value) {
            addCriterion("underlying_asset_quality_standard <=", value, "underlyingAssetQualityStandard");
            return (Criteria) this;
        }

        public Criteria andUnderlyingAssetQualityStandardLike(String value) {
            addCriterion("underlying_asset_quality_standard like", value, "underlyingAssetQualityStandard");
            return (Criteria) this;
        }

        public Criteria andUnderlyingAssetQualityStandardNotLike(String value) {
            addCriterion("underlying_asset_quality_standard not like", value, "underlyingAssetQualityStandard");
            return (Criteria) this;
        }

        public Criteria andUnderlyingAssetQualityStandardIn(List<String> values) {
            addCriterion("underlying_asset_quality_standard in", values, "underlyingAssetQualityStandard");
            return (Criteria) this;
        }

        public Criteria andUnderlyingAssetQualityStandardNotIn(List<String> values) {
            addCriterion("underlying_asset_quality_standard not in", values, "underlyingAssetQualityStandard");
            return (Criteria) this;
        }

        public Criteria andUnderlyingAssetQualityStandardBetween(String value1, String value2) {
            addCriterion("underlying_asset_quality_standard between", value1, value2, "underlyingAssetQualityStandard");
            return (Criteria) this;
        }

        public Criteria andUnderlyingAssetQualityStandardNotBetween(String value1, String value2) {
            addCriterion("underlying_asset_quality_standard not between", value1, value2, "underlyingAssetQualityStandard");
            return (Criteria) this;
        }

        public Criteria andCycleWeightedAverageInterestRateIsNull() {
            addCriterion("cycle_weighted_average_interest_rate is null");
            return (Criteria) this;
        }

        public Criteria andCycleWeightedAverageInterestRateIsNotNull() {
            addCriterion("cycle_weighted_average_interest_rate is not null");
            return (Criteria) this;
        }

        public Criteria andCycleWeightedAverageInterestRateEqualTo(BigDecimal value) {
            addCriterion("cycle_weighted_average_interest_rate =", value, "cycleWeightedAverageInterestRate");
            return (Criteria) this;
        }

        public Criteria andCycleWeightedAverageInterestRateNotEqualTo(BigDecimal value) {
            addCriterion("cycle_weighted_average_interest_rate <>", value, "cycleWeightedAverageInterestRate");
            return (Criteria) this;
        }

        public Criteria andCycleWeightedAverageInterestRateGreaterThan(BigDecimal value) {
            addCriterion("cycle_weighted_average_interest_rate >", value, "cycleWeightedAverageInterestRate");
            return (Criteria) this;
        }

        public Criteria andCycleWeightedAverageInterestRateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("cycle_weighted_average_interest_rate >=", value, "cycleWeightedAverageInterestRate");
            return (Criteria) this;
        }

        public Criteria andCycleWeightedAverageInterestRateLessThan(BigDecimal value) {
            addCriterion("cycle_weighted_average_interest_rate <", value, "cycleWeightedAverageInterestRate");
            return (Criteria) this;
        }

        public Criteria andCycleWeightedAverageInterestRateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("cycle_weighted_average_interest_rate <=", value, "cycleWeightedAverageInterestRate");
            return (Criteria) this;
        }

        public Criteria andCycleWeightedAverageInterestRateIn(List<BigDecimal> values) {
            addCriterion("cycle_weighted_average_interest_rate in", values, "cycleWeightedAverageInterestRate");
            return (Criteria) this;
        }

        public Criteria andCycleWeightedAverageInterestRateNotIn(List<BigDecimal> values) {
            addCriterion("cycle_weighted_average_interest_rate not in", values, "cycleWeightedAverageInterestRate");
            return (Criteria) this;
        }

        public Criteria andCycleWeightedAverageInterestRateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("cycle_weighted_average_interest_rate between", value1, value2, "cycleWeightedAverageInterestRate");
            return (Criteria) this;
        }

        public Criteria andCycleWeightedAverageInterestRateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("cycle_weighted_average_interest_rate not between", value1, value2, "cycleWeightedAverageInterestRate");
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