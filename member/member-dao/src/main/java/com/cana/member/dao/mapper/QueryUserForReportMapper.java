package com.cana.member.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.cana.member.dao.po.User;

public interface QueryUserForReportMapper {

    /**
     * 获取所有的已激活的保理商和融资商帐户
     * @return
     */
    @Select("SELECT * FROM member_user where user_status = 'ACTIVATED' and user_type is not null and user_type != 'CANA'")
    public List<User> getAllFactorAndFinanceUsers();
}
