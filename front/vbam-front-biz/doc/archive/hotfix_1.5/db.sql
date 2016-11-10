-- 2016-6-22 日修改，真旅企业改名

-- 修改SQL：

start transaction;
--OP、OP3、生产修改真旅企业名称，原名称为：真旅网计算机软件开发（上海）有限公司
update member_user set company_name = '上海长寿国际旅行社有限公司' where id = '201603280001';

--OP环境修改企业三证
update member_user set organization_code = '91310113630729373A', organization_code_certificate_media_id = '5763e75a45ce327901bdc8cd' where id = '201603280001';
update member_user set business_licence_code = '91310113630729373A', business_licence_media_id = '5763e75a45ce327901bdc8cd' where id = '201603280001';
update member_user set tax_registration_certificate_code = '91310113630729373A', tax_registration_certificate_media_id = '5763e75a45ce327901bdc8cd' where id = '201603280001';
--OP3环境修改企业三证
update member_user set organization_code = '91310113630729373A', organization_code_certificate_media_id = '5763e78645ce1a99362502d1' where id = '201603280001';
update member_user set business_licence_code = '91310113630729373A', business_licence_media_id = '5763e78645ce1a99362502d1' where id = '201603280001';
update member_user set tax_registration_certificate_code = '91310113630729373A', tax_registration_certificate_media_id = '5763e78645ce1a99362502d1' where id = '201603280001';
--生产环境修改企业三证
update member_user set organization_code = '91310113630729373A', organization_code_certificate_media_id = '5763e79fe4b0aa606911133d' where id = '201603280001';
update member_user set business_licence_code = '91310113630729373A', business_licence_media_id = '5763e79fe4b0aa606911133d' where id = '201603280001';
update member_user set tax_registration_certificate_code = '91310113630729373A', tax_registration_certificate_media_id = '5763e79fe4b0aa606911133d' where id = '201603280001';


--OP、OP3环境新增账户，原账号为3110210003631006431，开户名为：MS2
INSERT INTO `account` (`id`, `accumulation_id`, `account_apply_id`, `user_type`, `company_id`, `company_name`, `account_supervision_id`, `supervisor_id`, `supervisor_name`, `account_type`, `account_no`, `account_status`, `supervision_status`, `accumulation_status`, `buyer_name`, `is_default_repayment`, `create_time`, `update_time`, `operate_company_id`, `is_transfer_in_account`)
VALUES ('160618000000001', NULL, NULL, 'CORECOMPANY', '201603280001', '上海长寿国际旅行社有限公司', NULL, NULL, NULL, 'GENERAL', '3110210003631016378', 'NORMAL', 'NO_SUPERVISION', 'NO_ACCUMULATION', NULL, 0, '2016-06-18 00:00:00', '2016-06-18 00:00:00', NULL, 0);

--生产环境新增账户，原账号为3110210010051082423，开户名为：真旅网计算机软件开发（上海）有限公司
INSERT INTO `account` (`id`, `accumulation_id`, `account_apply_id`, `user_type`, `company_id`, `company_name`, `account_supervision_id`, `supervisor_id`, `supervisor_name`, `account_type`, `account_no`, `account_status`, `supervision_status`, `accumulation_status`, `buyer_name`, `is_default_repayment`, `create_time`, `update_time`, `operate_company_id`, `is_transfer_in_account`)
VALUES ('160618000000001', NULL, NULL, 'CORECOMPANY', '201603280001', '上海长寿国际旅行社有限公司', NULL, NULL, NULL, 'GENERAL', '3110210010051241810', 'NORMAL', 'NO_SUPERVISION', 'NO_ACCUMULATION', NULL, 0, '2016-06-18 00:00:00', '2016-06-18 00:00:00', NULL, 0);

-- 生产环境新增创建账户纪录
INSERT INTO `common_bankgate_trans` (`id`, `origin_id`, `trans_date`, `trans_type`, `business_type`, `business_seq`, `gate_seq`, `bank_seq`, `amount`, `bank_check_date`, `status`, `bank_user_name`, `main_account_no`, `account_no`, `receive_account_no`, `create_time`, `update_time`)
VALUES ('16062209184101801', NULL, '20160622', 'create_bank_account', 'create_bank_account', '102243643844277', '16062209184101801', NULL, NULL, NULL, 'success', 'knzcgl', '8110201013300209680', '3110210010051241810', NULL, '2016-06-22 09:18:42', '2016-06-22 09:19:56');


--OP、OP3环境修改产品表，原产品内容：{"accountNo":"3110210003631006431","accountName":"真旅网计算机软件开发（上海）有限公司","loanPeriodUnit":"MONTH","loanPeriod":1,"interestRateUnit":"DAY","interestRate":0.0005,"finaceRoleId":"201603312151530003","factorId":"cana-baoli","factorName":"上海凯拿资产管理有限公司","factorAccountNo":"3110210003631006466","institutionName":"真旅网","coreCompanyId":"201603280001","coreCompanyName":"真旅网计算机软件开发（上海）有限公司"}
UPDATE `vbam`.`repayment_product` SET `detail` = '{"accountNo":"3110210003631016378","accountName":"上海长寿国际旅行社有限公司","loanPeriodUnit":"MONTH","loanPeriod":1,"interestRateUnit":"DAY","interestRate":0.0005,"finaceRoleId":"201603312151530003","factorId":"cana-baoli","factorName":"上海凯拿资产管理有限公司","factorAccountNo":"3110210003631006466","institutionName":"真旅网","coreCompanyId":"201603280001","coreCompanyName":"上海长寿国际旅行社有限公司"}' WHERE `repayment_product`.`id` = 'travelzen_finance';
--生产环境修改产品表，原产品内容：{"accountNo":"3110210010051082423","accountName":"真旅网计算机软件开发（上海）有限公司","loanPeriodUnit":"MONTH","loanPeriod":1,"interestRateUnit":"DAY","interestRate":0.0005,"finaceRoleId":"201603312151530003","factorId":"cana-baoli","factorName":"上海凯拿资产管理有限公司","factorAccountNo":"3110210010051089159","institutionName":"真旅网","coreCompanyId":"201603280001","coreCompanyName":"真旅网计算机软件开发（上海）有限公司"}
UPDATE `vbam`.`repayment_product` SET `detail` = '{"accountNo":"3110210010051241810","accountName":"上海长寿国际旅行社有限公司","loanPeriodUnit":"MONTH","loanPeriod":1,"interestRateUnit":"DAY","interestRate":0.0005,"finaceRoleId":"201603312151530003","factorId":"cana-baoli","factorName":"上海凯拿资产管理有限公司","factorAccountNo":"3110210010051089159","institutionName":"真旅网","coreCompanyId":"201603280001","coreCompanyName":"上海长寿国际旅行社有限公司"}' WHERE `repayment_product`.`id` = 'travelzen_finance';

commit;
