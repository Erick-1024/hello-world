package com.cana.account.dao.mapper.gen;

import com.cana.account.dao.po.AccountSupervision;
import com.cana.account.dao.po.AccountSupervisionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AccountSupervisionMapper {
    int countByExample(AccountSupervisionExample example);

    int deleteByExample(AccountSupervisionExample example);

    int deleteByPrimaryKey(String id);

    int insert(AccountSupervision record);

    int insertSelective(AccountSupervision record);

    List<AccountSupervision> selectByExample(AccountSupervisionExample example);

    AccountSupervision selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") AccountSupervision record, @Param("example") AccountSupervisionExample example);

    int updateByExample(@Param("record") AccountSupervision record, @Param("example") AccountSupervisionExample example);

    int updateByPrimaryKeySelective(AccountSupervision record);

    int updateByPrimaryKey(AccountSupervision record);
}