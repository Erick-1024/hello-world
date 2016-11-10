package com.cana.repayment.dao.mapper.gen;

import com.cana.repayment.dao.po.RepaymentPenaltyProductDetail;
import com.cana.repayment.dao.po.RepaymentPenaltyProductDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RepaymentPenaltyProductDetailMapper {
    int countByExample(RepaymentPenaltyProductDetailExample example);

    int deleteByExample(RepaymentPenaltyProductDetailExample example);

    int deleteByPrimaryKey(String id);

    int insert(RepaymentPenaltyProductDetail record);

    int insertSelective(RepaymentPenaltyProductDetail record);

    List<RepaymentPenaltyProductDetail> selectByExample(RepaymentPenaltyProductDetailExample example);

    RepaymentPenaltyProductDetail selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") RepaymentPenaltyProductDetail record, @Param("example") RepaymentPenaltyProductDetailExample example);

    int updateByExample(@Param("record") RepaymentPenaltyProductDetail record, @Param("example") RepaymentPenaltyProductDetailExample example);

    int updateByPrimaryKeySelective(RepaymentPenaltyProductDetail record);

    int updateByPrimaryKey(RepaymentPenaltyProductDetail record);

    RepaymentPenaltyProductDetail lockByPrimaryKey(@Param("id") String id);

    List<RepaymentPenaltyProductDetail> lockByExample(RepaymentPenaltyProductDetailExample example);
}