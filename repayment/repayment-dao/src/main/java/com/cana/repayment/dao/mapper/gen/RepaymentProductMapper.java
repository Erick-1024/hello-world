package com.cana.repayment.dao.mapper.gen;

import com.cana.repayment.dao.po.RepaymentProduct;
import com.cana.repayment.dao.po.RepaymentProductExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RepaymentProductMapper {
    int countByExample(RepaymentProductExample example);

    int deleteByExample(RepaymentProductExample example);

    int deleteByPrimaryKey(String id);

    int insert(RepaymentProduct record);

    int insertSelective(RepaymentProduct record);

    List<RepaymentProduct> selectByExample(RepaymentProductExample example);

    RepaymentProduct selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") RepaymentProduct record, @Param("example") RepaymentProductExample example);

    int updateByExample(@Param("record") RepaymentProduct record, @Param("example") RepaymentProductExample example);

    int updateByPrimaryKeySelective(RepaymentProduct record);

    int updateByPrimaryKey(RepaymentProduct record);
}