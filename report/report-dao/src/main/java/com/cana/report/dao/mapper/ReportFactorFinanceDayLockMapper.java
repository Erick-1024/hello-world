package com.cana.report.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.cana.report.dao.po.ReportFactorFinanceDay;

public interface ReportFactorFinanceDayLockMapper {

    /**
     * 获取所有当天已经生成日报表的保理商和融资商和cana的id
     * @param currentDay 当天日期 yyyy-MM-dd
     * @return
     */
    @Select("SELECT owner_id FROM report_factor_finance_day where report_date = #{currentDay} and business_product_id=#{businessProductId}")
    public List<String> getAllFactorAndFinanceIdOfCurrentDayAndBusinessProduct(@Param("currentDay")String currentDay, @Param("businessProductId") String businessProductId);
    
    /**
     * 对某个用户某天的日报表进行加锁查询
     * @param currentDay
     * @return
     */
    @Select("SELECT * FROM report_factor_finance_day where report_date = #{currentDay} and owner_id = #{masterId} and business_product_id=#{businessProductId} for update")
    public ReportFactorFinanceDay lockDailyReport(@Param("currentDay")String currentDay, @Param("masterId")String masterId, @Param("businessProductId") String businessProductId);
    
    /**
     * 获取当天所有的保理商的日报表
     * @param currentDay
     * @return
     */
    @Select("SELECT * FROM report_factor_finance_day where user_type = 'FACTOR' and report_date = #{currentDay} and business_product_id=#{businessProductId}")
    public List<ReportFactorFinanceDay> getAllFactorReportDayOfCurrentDayAndBusinessProduct(@Param("currentDay")String currentDay, @Param("businessProductId") String businessProductId);
    
}
