package com.cana.repayment.dao.mapper.gen;

import com.cana.repayment.dao.po.RepaymentSingleDistributeDetail;
import com.cana.repayment.dao.po.RepaymentSingleDistributeDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RepaymentSingleDistributeDetailMapper {
    int countByExample(RepaymentSingleDistributeDetailExample example);

    int deleteByExample(RepaymentSingleDistributeDetailExample example);

    int deleteByPrimaryKey(String id);

    int insert(RepaymentSingleDistributeDetail record);

    int insertSelective(RepaymentSingleDistributeDetail record);

    List<RepaymentSingleDistributeDetail> selectByExample(RepaymentSingleDistributeDetailExample example);

    RepaymentSingleDistributeDetail selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") RepaymentSingleDistributeDetail record, @Param("example") RepaymentSingleDistributeDetailExample example);

    int updateByExample(@Param("record") RepaymentSingleDistributeDetail record, @Param("example") RepaymentSingleDistributeDetailExample example);

    int updateByPrimaryKeySelective(RepaymentSingleDistributeDetail record);

    int updateByPrimaryKey(RepaymentSingleDistributeDetail record);

    RepaymentSingleDistributeDetail lockByPrimaryKey(@Param("id") String id);

    List<RepaymentSingleDistributeDetail> lockByExample(RepaymentSingleDistributeDetailExample example);
}