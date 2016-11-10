
1. 核心企业需求上线后忘记修改账户表中的用户类型，下面是修正sql，account影响1条，account_trade_record影响3条

update account set user_type = 'CORECOMPANY' where company_id = '201603280001';

update account_trade_record set user_type = 'CORECOMPANY' where company_id = '201603280001';
