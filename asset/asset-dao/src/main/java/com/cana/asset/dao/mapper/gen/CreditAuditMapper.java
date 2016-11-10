package com.cana.asset.dao.mapper.gen;

import com.cana.asset.dao.po.CreditAudit;
import com.cana.asset.dao.po.CreditAuditExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CreditAuditMapper {
    int countByExample(CreditAuditExample example);

    int deleteByExample(CreditAuditExample example);

    int deleteByPrimaryKey(String id);

    int insert(CreditAudit record);

    int insertSelective(CreditAudit record);

    List<CreditAudit> selectByExample(CreditAuditExample example);

    CreditAudit selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") CreditAudit record, @Param("example") CreditAuditExample example);

    int updateByExample(@Param("record") CreditAudit record, @Param("example") CreditAuditExample example);

    int updateByPrimaryKeySelective(CreditAudit record);

    int updateByPrimaryKey(CreditAudit record);

    CreditAudit lockByPrimaryKey(@Param("id") String id);

    List<CreditAudit> lockByExample(CreditAuditExample example);
}