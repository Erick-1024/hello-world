--交易流水明细表修改:
ALTER TABLE `vbam`.`account_trade_record` ADD COLUMN `user_type` VARCHAR(50) NULL;
--执行前需要规避没有where语句的安全限制
UPDATE vbam.account_trade_record as b SET b.user_type = (select a.user_type from vbam.account as a where a.id = b.account_id); 

--监控记录开始位置修改：
UPDATE `vbam`.`common_properties` SET `value`='TKT20160505101015658881' WHERE `name`='alterable_last_record_id';
UPDATE `vbam`.`common_properties` SET `value`='TKT20160505101015658881' WHERE `name`='last_alterable_last_record_id';
--真旅客票表增加总税款字段
ALTER TABLE `travelzen`.`flight_ticket` 
ADD COLUMN `total_tax` DECIMAL(18,0) NOT NULL COMMENT '单位：分。对于正常和改签票来说，代表最新的总税款，不是差额。对于退票来说，代表退的总税款。' AFTER `ticket_price`;

--放款信息快照表
ALTER TABLE `vbam`.`repayment_loan_info_snapshot` 
ADD COLUMN `core_company_id` VARCHAR(32) NULL DEFAULT NULL COMMENT '核心企业id' AFTER `finance_company`,
ADD COLUMN `core_company_name` VARCHAR(255) NULL DEFAULT NULL COMMENT '核心企业名称' AFTER `core_company_id`;

--还款计划快照表
ALTER TABLE `vbam`.`repayment_plan_snapshot` 
ADD COLUMN `core_company_id` VARCHAR(32) NULL DEFAULT NULL COMMENT '核心企业id' AFTER `finance_company`,
ADD COLUMN `core_company_name` VARCHAR(255) NULL DEFAULT NULL COMMENT '核心企业名称' AFTER `core_company_id`;

--固定费用快照表
ALTER TABLE `vbam`.`repayment_expense_snapshot` 
ADD COLUMN `core_company_id` VARCHAR(32) NULL DEFAULT NULL COMMENT '核心企业id' AFTER `finance_company`,
ADD COLUMN `core_company_name` VARCHAR(255) NULL DEFAULT NULL COMMENT '核心企业名称' AFTER `core_company_id`;
