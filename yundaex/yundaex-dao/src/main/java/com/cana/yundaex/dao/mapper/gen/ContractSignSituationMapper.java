package com.cana.yundaex.dao.mapper.gen;

import com.cana.yundaex.dao.po.ContractSignSituation;
import com.cana.yundaex.dao.po.ContractSignSituationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ContractSignSituationMapper {
    int countByExample(ContractSignSituationExample example);

    int deleteByExample(ContractSignSituationExample example);

    int deleteByPrimaryKey(String id);

    int insert(ContractSignSituation record);

    int insertSelective(ContractSignSituation record);

    List<ContractSignSituation> selectByExample(ContractSignSituationExample example);

    ContractSignSituation selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ContractSignSituation record, @Param("example") ContractSignSituationExample example);

    int updateByExample(@Param("record") ContractSignSituation record, @Param("example") ContractSignSituationExample example);

    int updateByPrimaryKeySelective(ContractSignSituation record);

    int updateByPrimaryKey(ContractSignSituation record);

    ContractSignSituation lockByPrimaryKey(@Param("id") String id);

    List<ContractSignSituation> lockByExample(ContractSignSituationExample example);
}