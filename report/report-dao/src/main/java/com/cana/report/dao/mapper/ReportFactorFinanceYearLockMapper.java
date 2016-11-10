package com.cana.report.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.cana.report.dao.po.ReportFactorFinanceYear;

public interface ReportFactorFinanceYearLockMapper {

    /**
     * 获取所有当年已经生成年报表的保理商和融资商和cana的id
     * @param currentYear 当年 yyyy
     * @return
     */
    @Select("SELECT owner_id FROM report_factor_finance_year where report_date = #{currentYear} and business_product_id=#{businessProductId}")
    public List<String> getAllFactorAndFinanceIdOfCurrentYearAndBusinessProduct(@Param("currentYear")String currentYear, @Param("businessProductId") String businessProductId);
    
    /**
     * 对某个用户某年的年报表进行加锁查询
     * @param currentYear
     * @return
     */
    @Select("SELECT * FROM report_factor_finance_year where report_date = #{currentYear} and owner_id = #{masterId} and business_product_id=#{businessProductId} for update")
    public ReportFactorFinanceYear lockAnnualReport(@Param("currentYear")String currentYear, @Param("masterId")String masterId, @Param("businessProductId") String businessProductId);
    
    /**
     * 获取当年所有的保理商的年报表
     * @param currentDay
     * @return
     */
    @Select("SELECT * FROM report_factor_finance_year where user_type = 'FACTOR' and report_date = #{currentYear} and business_product_id=#{businessProductId}")
    public List<ReportFactorFinanceYear> getAllFactorReportYearOfCurrentYearAndBusinessProduct(@Param("currentYear")String currentYear, @Param("businessProductId") String businessProductId);
}
