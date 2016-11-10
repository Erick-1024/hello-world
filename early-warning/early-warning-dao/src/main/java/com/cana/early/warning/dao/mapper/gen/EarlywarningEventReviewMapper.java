package com.cana.early.warning.dao.mapper.gen;

import com.cana.early.warning.dao.po.EarlywarningEventReview;
import com.cana.early.warning.dao.po.EarlywarningEventReviewExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EarlywarningEventReviewMapper {
    int countByExample(EarlywarningEventReviewExample example);

    int deleteByExample(EarlywarningEventReviewExample example);

    int deleteByPrimaryKey(String id);

    int insert(EarlywarningEventReview record);

    int insertSelective(EarlywarningEventReview record);

    List<EarlywarningEventReview> selectByExample(EarlywarningEventReviewExample example);

    EarlywarningEventReview selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") EarlywarningEventReview record, @Param("example") EarlywarningEventReviewExample example);

    int updateByExample(@Param("record") EarlywarningEventReview record, @Param("example") EarlywarningEventReviewExample example);

    int updateByPrimaryKeySelective(EarlywarningEventReview record);

    int updateByPrimaryKey(EarlywarningEventReview record);

    EarlywarningEventReview lockByPrimaryKey(@Param("id") String id);

    List<EarlywarningEventReview> lockByExample(EarlywarningEventReviewExample example);
}