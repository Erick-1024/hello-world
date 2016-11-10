package com.cana.repayment.dao.mapper.gen;

import com.cana.repayment.dao.po.RepaymentSingleCollect;
import com.cana.repayment.dao.po.RepaymentSingleCollectExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RepaymentSingleCollectMapper {
    int countByExample(RepaymentSingleCollectExample example);

    int deleteByExample(RepaymentSingleCollectExample example);

    int deleteByPrimaryKey(String id);

    int insert(RepaymentSingleCollect record);

    int insertSelective(RepaymentSingleCollect record);

    List<RepaymentSingleCollect> selectByExample(RepaymentSingleCollectExample example);

    RepaymentSingleCollect selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") RepaymentSingleCollect record, @Param("example") RepaymentSingleCollectExample example);

    int updateByExample(@Param("record") RepaymentSingleCollect record, @Param("example") RepaymentSingleCollectExample example);

    int updateByPrimaryKeySelective(RepaymentSingleCollect record);

    int updateByPrimaryKey(RepaymentSingleCollect record);

    RepaymentSingleCollect lockByPrimaryKey(@Param("id") String id);

    List<RepaymentSingleCollect> lockByExample(RepaymentSingleCollectExample example);
}