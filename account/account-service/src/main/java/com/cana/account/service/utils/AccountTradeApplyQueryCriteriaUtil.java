package com.cana.account.service.utils;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;

import com.cana.member.dao.po.User;
import com.cana.vbam.common.account.dto.AccountTradeApplyQueryCriteria;
import com.travelzen.framework.core.time.DateTimeUtil;

public class AccountTradeApplyQueryCriteriaUtil {

    public static AccountTradeApplyQueryCriteria getValidCriteria(User customer, AccountTradeApplyQueryCriteria criteria) {
        AccountTradeApplyQueryCriteria dbCriteria = new AccountTradeApplyQueryCriteria();
        dbCriteria.setPage(criteria.getPage() < 1 ? 1 : criteria.getPage());
        dbCriteria.setPageSize(criteria.getPageSize() < 1 ? 10 : criteria.getPageSize());
        if (StringUtils.isNotBlank(criteria.getTradeApplyId())) {
            dbCriteria.setTradeApplyId(StringUtils.trim(criteria.getTradeApplyId()));
        }
        if (StringUtils.isNotBlank(criteria.getAccountNo())) {
            dbCriteria.setAccountNo(criteria.getAccountNo());
        }
        if (StringUtils.isNotBlank(criteria.getAccountName())) {
            dbCriteria.setAccountName("%" + criteria.getAccountName() + "%");
        }
        if (StringUtils.isNotBlank(criteria.getApplyCompanyName())) {
            dbCriteria.setApplyCompanyName("%" + criteria.getApplyCompanyName() + "%");
        }
        dbCriteria.setApplyType(criteria.getApplyType());
        dbCriteria.setApplyStatus(criteria.getApplyStatus());
        if (criteria.isApplyBySelf() || criteria.isAuditBySelf()) {
            if (criteria.isApplyBySelf()) {
                dbCriteria.setApplyCompanyId(customer.getId());
            }
            if (criteria.isAuditBySelf()) {
                dbCriteria.setAuditCompanyId(customer.getId());
            }
        } else {
            dbCriteria.setCompanyId(customer.getId());
        }
        if (StringUtils.isNotBlank(criteria.getStartTime())) {
            DateTime startTime = DateTimeUtil.parseDate10(criteria.getStartTime());
            dbCriteria.setStartTime(DateTimeUtil.format(startTime, DateTimeUtil.DATE_TIME_PATTERN));
        }
        if (StringUtils.isNotBlank(criteria.getEndTime())) {
            DateTime endTime = DateTimeUtil.parseDate10(criteria.getEndTime());
            endTime = endTime.plusDays(1);
            dbCriteria.setEndTime(DateTimeUtil.format(endTime, DateTimeUtil.DATE_TIME_PATTERN));
        }
        return dbCriteria;
    }
}
