- 2016-10-31

给线上泛华公司手动增加银行账户

	INSERT INTO `common_bankgate_trans` (`id`, `origin_id`, `trans_date`, `trans_type`, `business_type`, `business_seq`, `gate_seq`, `bank_seq`, `amount`, `bank_check_date`, `status`, `bank_user_name`, `main_account_no`, `account_no`, `receive_account_no`, `create_time`, `update_time`)
	VALUES ('16103100000006901', NULL, '20161031', 'create_bank_account', 'create_bank_account', '37595476943158', '16103115550006901', NULL, NULL, NULL, 'success', 'knzcgl', '8110201013300209680', '3110210010052108036', NULL, '2016-10-31 15:55:01', '2016-10-31 15:55:03');
	
	INSERT INTO `account` (`id`, `accumulation_id`, `account_apply_id`, `user_type`, `company_id`, `company_name`, `account_supervision_id`, `supervisor_id`, `supervisor_name`, `account_type`, `account_no`, `account_status`, `supervision_status`, `accumulation_status`, `buyer_name`, `is_default_repayment`, `create_time`, `update_time`, `operate_company_id`, `is_transfer_in_account`)
	VALUES ('161031000000203', NULL, NULL, 'FINACE', '201610310199', '上海泛华国际旅行社有限公司', NULL, NULL, NULL, 'GENERAL', '3110210010052108036', 'NORMAL', 'NO_SUPERVISION', 'NO_ACCUMULATION', NULL, 0, '2016-04-08 15:41:34', '2016-04-08 15:41:34', NULL, 0);

- 2016-6-13

该客户额度因为真旅建议额度为0，导致最终额度为平台最低5k，而如果不参考真旅建议额度，则给该客户额度为4万6，下面是手动修改该客户额度的sql。

	UPDATE `vbam`.`credit_limit` SET `total_limit` = '4600000' WHERE `credit_limit`.`id` = '160613172611401';
	UPDATE `vbam`.`credit_limit_audit` SET `total_limit` = '4600000' WHERE `credit_limit_audit`.`id` = '1606131726110001302';

- 2016-6-13

修正系统错误的将中信处理中状态的转账记录为交易失败的问题，该问题产生原因为网关项目将中信的ED02091状态码记录为交易失败，而实际该状态码表示银行处理中

	-- 修改网关交易流水，影响条数1条
	update `common_bankgate_trans` set `status` = 'success' where `business_seq` = '16060812155202008';
	-- 修改账户交易流水，影响条数2条
	update `account_trade_record` set `status` = 'TRADE_SUCCESS' where `business_seq` = '16060812155202008';
	-- 修改授信模块转账记录，影响条数1条
	update `credit_transfer` set `transfer_status` = 'SUCCESS' where `business_seq` = '16060812155202008';


- 2016-6-2
凯拿自测真旅客户签合同流程回滚sql

	# 将客户的引导状态设置成待引导
	update member_user set guide_status = 'NEED_GENERATE_CONTRACT'  where id = '201606020013';
	# 将该客户的额度修改为待激活
	update credit_limit set status = 'INACTIVE' where member_id = '201606020013';
	# 删除该客户的额度通知任务
	delete from common_retry_task where task_id = 'travelzencana_test_tz_customer_id';
	delete from common_retry_task where task_id = '201606020013' and task_type='CREDIT_UPDATE_USER_ROLE';


- 2016-5-12

因真旅客户表的最早交易时间错误，执行了以下sql

	update  tz_customer_info 
	left join (select customer_id, `date` from (select * from daily_bill order by `date`)  as t group by `customer_id`) as t2
	on tz_customer_info.tz_customer_id = t2.`customer_id`
	set tz_customer_info.`first_business_time` = t2.`date`;
	

- 2016-5-9

凯拿管理的两个账号登录名修改

	update member_user set username = 'canazg' where id = 'cana-baoli';
	update member_user set username = 'cana' where id = 'cana-user';


