package com.cana.credit.limit.dao.mapper.gen;

import com.cana.credit.limit.dao.po.CreditLimitAudit;
import com.cana.credit.limit.dao.po.CreditLimitAuditExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CreditLimitAuditMapper {
    int countByExample(CreditLimitAuditExample example);

    int deleteByExample(CreditLimitAuditExample example);

    int deleteByPrimaryKey(String id);

    int insert(CreditLimitAudit record);

    int insertSelective(CreditLimitAudit record);

    List<CreditLimitAudit> selectByExample(CreditLimitAuditExample example);

    CreditLimitAudit selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") CreditLimitAudit record, @Param("example") CreditLimitAuditExample example);

    int updateByExample(@Param("record") CreditLimitAudit record, @Param("example") CreditLimitAuditExample example);

    int updateByPrimaryKeySelective(CreditLimitAudit record);

    int updateByPrimaryKey(CreditLimitAudit record);

    CreditLimitAudit lockByPrimaryKey(@Param("id") String id);

    List<CreditLimitAudit> lockByExample(CreditLimitAuditExample example);
}