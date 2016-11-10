package com.cana.account.dao.mapper.gen;

import com.cana.account.dao.po.AccountApply;
import com.cana.account.dao.po.AccountApplyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AccountApplyMapper {
    int countByExample(AccountApplyExample example);

    int deleteByExample(AccountApplyExample example);

    int deleteByPrimaryKey(String id);

    int insert(AccountApply record);

    int insertSelective(AccountApply record);

    List<AccountApply> selectByExample(AccountApplyExample example);

    AccountApply selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") AccountApply record, @Param("example") AccountApplyExample example);

    int updateByExample(@Param("record") AccountApply record, @Param("example") AccountApplyExample example);

    int updateByPrimaryKeySelective(AccountApply record);

    int updateByPrimaryKey(AccountApply record);
}