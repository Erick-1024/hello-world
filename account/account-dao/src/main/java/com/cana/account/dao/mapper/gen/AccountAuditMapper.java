package com.cana.account.dao.mapper.gen;

import com.cana.account.dao.po.AccountAudit;
import com.cana.account.dao.po.AccountAuditExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AccountAuditMapper {
    int countByExample(AccountAuditExample example);

    int deleteByExample(AccountAuditExample example);

    int deleteByPrimaryKey(String id);

    int insert(AccountAudit record);

    int insertSelective(AccountAudit record);

    List<AccountAudit> selectByExample(AccountAuditExample example);

    AccountAudit selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") AccountAudit record, @Param("example") AccountAuditExample example);

    int updateByExample(@Param("record") AccountAudit record, @Param("example") AccountAuditExample example);

    int updateByPrimaryKeySelective(AccountAudit record);

    int updateByPrimaryKey(AccountAudit record);
}