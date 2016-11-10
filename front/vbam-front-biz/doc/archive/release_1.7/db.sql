-- 放款表增加外部客户ID和名称
ALTER TABLE `vbam`.`repayment_loan_info` 
ADD COLUMN `out_customer_id` VARCHAR(32) NULL COMMENT '融资客户对应外部ID' AFTER `finance_company`,
ADD COLUMN `out_customer_name` VARCHAR(255) NULL COMMENT '融资客户对应外部名称' AFTER `out_customer_id`;

ALTER TABLE `vbam`.`repayment_loan_info_snapshot` 
ADD COLUMN `out_customer_id` VARCHAR(32) NULL COMMENT '融资客户对应外部ID' AFTER `finance_company`,
ADD COLUMN `out_customer_name` VARCHAR(255) NULL COMMENT '融资客户对应外部名称' AFTER `out_customer_id`;

-- 额度表增加外部客户ID和名称
ALTER TABLE `vbam`.`credit_limit` 
ADD COLUMN `out_customer_id` VARCHAR(32) NULL COMMENT '外部客户ID，目前真旅项目使用' AFTER `status`,
ADD COLUMN `out_customer_name` VARCHAR(255) NULL COMMENT '外部客户名称，目前真旅项目使用' AFTER `out_customer_id`,
ADD INDEX `idx_credit_limit_out_customer_id` (`out_customer_id` ASC);

-- 真旅客户申请表加上真旅采购商名称字段
ALTER TABLE `vbam`.`credit_customer_apply` 
ADD COLUMN `tz_customer_name` VARCHAR(255) NULL COMMENT '真旅采购商名称，如果真旅没传，则取tz_customer_info表中的最新值，如果也没有则取company_name值' AFTER `tz_customer_id`;

-- 给客户额度申请表的外部客户名称赋值
select * from vbam.credit_customer_apply apply left join travelzen.tz_customer_info tz on apply.`tz_customer_id` = tz.tz_customer_id;

update vbam.credit_customer_apply apply left join travelzen.tz_customer_info tz on apply.`tz_customer_id` = tz.tz_customer_id
set apply.tz_customer_name = tz.customer_names;
update vbam.credit_customer_apply apply set apply.tz_customer_name = apply.company_name where apply.tz_customer_name is null;

--  给额度表中的外部客户字段赋值
select * from vbam.credit_limit credit_limit
left join vbam.credit_out_customer outcustomer on credit_limit.member_id = outcustomer.`member_id`
where credit_limit.project_id = 'travelzen_finance';

update vbam.credit_limit credit_limit
left join vbam.credit_out_customer outcustomer on credit_limit.member_id = outcustomer.`member_id`
set credit_limit.`out_customer_id` = outcustomer.`out_customer_id`
where credit_limit.project_id = 'travelzen_finance';

select * from vbam.credit_limit credit_limit
left join vbam.credit_customer_apply apply on credit_limit.`out_customer_id` = apply.`tz_customer_id`
where credit_limit.project_id = 'travelzen_finance';

update vbam.credit_limit credit_limit
left join vbam.credit_customer_apply apply on credit_limit.`out_customer_id` = apply.`tz_customer_id`
set credit_limit.`out_customer_name` = apply.`tz_customer_name`
where credit_limit.project_id = 'travelzen_finance';


-- 给融资放款外部客户字段赋值
select loan.`business_product_id`, loan.finance_id, loan.`out_customer_id`, loan.`out_customer_name`, climit.*
from `vbam`.`repayment_loan_info` loan
left join `vbam`.`credit_limit` climit on loan.`finance_id` = climit.`member_id`
where loan.`business_product_id` = 'travelzen_finance';

update `vbam`.`repayment_loan_info` loan
left join `vbam`.`credit_limit` climit on loan.`finance_id` = climit.`member_id`
set loan.`out_customer_id` = climit.`out_customer_id`, loan.`out_customer_name` = climit.`out_customer_name`
where loan.`business_product_id` = 'travelzen_finance';






--- 监控、预警修改
ALTER TABLE `vbam`.`report_monitor_data` 
ADD COLUMN `out_customer_id` VARCHAR(32) NOT NULL COMMENT '外部客户ID' AFTER `member_id`,
ADD COLUMN `product_id` VARCHAR(32) NOT NULL COMMENT '产品ID' AFTER `out_customer_id`,
DROP INDEX `report_monitor_data` ,
ADD UNIQUE INDEX `report_monitor_data` (`member_id` ASC, `date` ASC, `type` ASC, `out_customer_id` ASC, `product_id` ASC);


ALTER TABLE `vbam`.`report_monitor_metric` 
ADD COLUMN `out_customer_id` VARCHAR(32) NOT NULL COMMENT '外部客户ID' AFTER `member_id`,
ADD COLUMN `product_id` VARCHAR(32) NOT NULL COMMENT '产品ID' AFTER `out_customer_id`;

CREATE UNIQUE INDEX `report_monitor_metric`  ON `vbam`.`report_monitor_metric` (member_id, out_customer_id, product_id, type, date) COMMENT '' ALGORITHM DEFAULT LOCK DEFAULT;

ALTER TABLE `vbam`.`early_warning_customer` 
ADD COLUMN `out_customer_id` VARCHAR(32) NOT NULL COMMENT '外部平台客户ID' AFTER `finance_company`;

ALTER TABLE `vbam`.`early_warning_event` 
ADD COLUMN `out_customer_id` VARCHAR(32) NOT NULL COMMENT '外部平台客户ID' AFTER `finance_company`;

ALTER TABLE `vbam`.`early_warning_event_review` 
ADD COLUMN `out_customer_id` VARCHAR(32) NOT NULL COMMENT '外部平台客户ID' AFTER `finance_company`;

ALTER TABLE `vbam`.`early_warning_level_change_history` 
ADD COLUMN `out_customer_id` VARCHAR(32) NOT NULL COMMENT '外部平台客户ID' AFTER `finance_company`;

ALTER TABLE `vbam`.`early_warning_system_event_generate_record` 
ADD COLUMN `out_customer_id` VARCHAR(32) NOT NULL COMMENT '外部平台客户ID' AFTER `finance_company`;

DROP INDEX `index_early_warning_customer_product_id_finance_id` ON `vbam`.`early_warning_customer`;

CREATE INDEX `idx_early_warning_customer_product_id_finance_id_out_customer_id`  ON `vbam`.`early_warning_customer` (product_id, finance_id, out_customer_id) COMMENT '' ALGORITHM DEFAULT LOCK DEFAULT;

DROP INDEX `index_early_warning_event_product_id_finance_id` ON `vbam`.`early_warning_event`;

CREATE INDEX `idx_early_warning_event_product_id_finance_id_out_customer_id`  ON `vbam`.`early_warning_event` (product_id, finance_id, out_customer_id) COMMENT '' ALGORITHM DEFAULT LOCK DEFAULT;

DROP INDEX `index_early_warning_event_review_product_id_finance_id` ON `vbam`.`early_warning_event_review`;

CREATE INDEX `event_review_product_id_finance_id_out_customer_id`  ON `vbam`.`early_warning_event_review` (product_id, finance_id, out_customer_id) COMMENT '' ALGORITHM DEFAULT LOCK DEFAULT;

DROP INDEX `index_early_warning_level_change_history_product_id_finance_id` ON `vbam`.`early_warning_level_change_history`;

CREATE INDEX `level_change_history_product_id_finance_id_out_customer_id`  ON `vbam`.`early_warning_level_change_history` (product_id, finance_id, out_customer_id) COMMENT '' ALGORITHM DEFAULT LOCK DEFAULT;

DROP INDEX `idx_product_id_finance_id` ON `vbam`.`early_warning_system_event_generate_record`;

CREATE INDEX `generate_record_product_id_finance_id_out_customer_id`  ON `vbam`.`early_warning_system_event_generate_record` (product_id, finance_id, out_customer_id) COMMENT '' ALGORITHM DEFAULT LOCK DEFAULT;


update vbam.report_monitor_data set product_id = 'travelzen_finance';

update vbam.report_monitor_metric set product_id = 'travelzen_finance';

update vbam.report_monitor_metric, vbam.credit_out_customer set vbam.report_monitor_metric.out_customer_id = vbam.credit_out_customer.out_customer_id where vbam.report_monitor_metric.member_id = vbam.credit_out_customer.member_id;

update vbam.report_monitor_data, vbam.credit_out_customer set vbam.report_monitor_data.out_customer_id = vbam.credit_out_customer.out_customer_id where vbam.report_monitor_data.member_id = vbam.credit_out_customer.member_id;

update vbam.early_warning_customer, vbam.credit_out_customer set vbam.early_warning_customer.out_customer_id = vbam.credit_out_customer.out_customer_id
where vbam.early_warning_customer.finance_id = vbam.credit_out_customer.member_id;

update vbam.early_warning_event, vbam.credit_out_customer set vbam.early_warning_event.out_customer_id = vbam.credit_out_customer.out_customer_id
where vbam.early_warning_event.finance_id = vbam.credit_out_customer.member_id;

update vbam.early_warning_event_review, vbam.credit_out_customer set vbam.early_warning_event_review.out_customer_id = vbam.credit_out_customer.out_customer_id
where vbam.early_warning_event_review.finance_id = vbam.credit_out_customer.member_id;

update vbam.early_warning_level_change_history, vbam.credit_out_customer set vbam.early_warning_level_change_history.out_customer_id = vbam.credit_out_customer.out_customer_id
where vbam.early_warning_level_change_history.finance_id = vbam.credit_out_customer.member_id;

update vbam.early_warning_system_event_generate_record, vbam.credit_out_customer set vbam.early_warning_system_event_generate_record.out_customer_id = vbam.credit_out_customer.out_customer_id
where vbam.early_warning_system_event_generate_record.finance_id = vbam.credit_out_customer.member_id;
