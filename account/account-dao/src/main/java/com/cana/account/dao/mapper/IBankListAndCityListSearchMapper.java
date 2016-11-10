package com.cana.account.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


public interface IBankListAndCityListSearchMapper {
	
	@Select("SELECT distinct(bank_name) FROM common_bank_branch_info")
    public List<String> getSupportBankList();
	
	@Select("SELECT distinct(province) FROM common_bank_branch_info")
	public List<String> getAllProvince();
	
	@Select("SELECT distinct(city) FROM common_bank_branch_info where province=#{province}")
	public List<String> getCityList(@Param("province") String province);
}
