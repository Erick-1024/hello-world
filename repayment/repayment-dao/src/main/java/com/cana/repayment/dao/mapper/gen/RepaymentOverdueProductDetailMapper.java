package com.cana.repayment.dao.mapper.gen;

import com.cana.repayment.dao.po.RepaymentOverdueProductDetail;
import com.cana.repayment.dao.po.RepaymentOverdueProductDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RepaymentOverdueProductDetailMapper {
    int countByExample(RepaymentOverdueProductDetailExample example);

    int deleteByExample(RepaymentOverdueProductDetailExample example);

    int deleteByPrimaryKey(String id);

    int insert(RepaymentOverdueProductDetail record);

    int insertSelective(RepaymentOverdueProductDetail record);

    List<RepaymentOverdueProductDetail> selectByExample(RepaymentOverdueProductDetailExample example);

    RepaymentOverdueProductDetail selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") RepaymentOverdueProductDetail record, @Param("example") RepaymentOverdueProductDetailExample example);

    int updateByExample(@Param("record") RepaymentOverdueProductDetail record, @Param("example") RepaymentOverdueProductDetailExample example);

    int updateByPrimaryKeySelective(RepaymentOverdueProductDetail record);

    int updateByPrimaryKey(RepaymentOverdueProductDetail record);

    RepaymentOverdueProductDetail lockByPrimaryKey(@Param("id") String id);

    List<RepaymentOverdueProductDetail> lockByExample(RepaymentOverdueProductDetailExample example);
}