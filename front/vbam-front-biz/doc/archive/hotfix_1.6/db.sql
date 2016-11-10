-- 授信额度表支持多项目
-- 额度表新增项目ID字段：
ALTER TABLE `vbam`.`credit_limit` 
ADD COLUMN `project_id` VARCHAR(32) NOT NULL COMMENT '关联的项目ID' AFTER `member_id`;

ALTER TABLE `vbam`.`credit_limit` 
ADD INDEX `idx_credit_limit_project_id` (`project_id` ASC);

--将目前该表中的所有记录设置为真旅项目：
UPDATE `vbam`.`credit_limit` set `project_id`='travelzen_finance';

ALTER TABLE `vbam`.`credit_limit_audit` 
ADD COLUMN `loan_id` VARCHAR(32) NULL COMMENT '放款ID，目前用在韵达项目中' AFTER `pledge_rate`,
ADD COLUMN `loan_no` VARCHAR(100) NULL COMMENT '放款编号，目前用在韵达项目中' AFTER `loan_id`;

--修改字段备注：
ALTER TABLE `vbam`.`credit_limit_audit` 
CHANGE COLUMN `type` `type` VARCHAR(32) NOT NULL COMMENT '详见 @see CreditLimitAuditType，有如下类型：总额度变化、额度消耗、项目中额度恢复、融资模块还款恢复' ,
CHANGE COLUMN `request_id` `request_id` VARCHAR(32) NULL DEFAULT NULL COMMENT '当客户在融资模块进行还款时，会触发额度恢复，此时使用的是重试框架来恢复额度，而此字段是用来识别融资发来的恢复额度请求是否已经被处理过的字段，具体参见真旅项目中的实现。' ,
CHANGE COLUMN `trade_id` `trade_id` VARCHAR(32) NULL DEFAULT NULL COMMENT '真旅项目中，引起额度变化的交易记录ID' ,
CHANGE COLUMN `sale_growth_rate` `sale_growth_rate` DECIMAL(18,5) NULL DEFAULT NULL COMMENT '真旅项目中，总额度变化时的销售增长率定值' ,
CHANGE COLUMN `pledge_rate` `pledge_rate` DECIMAL(18,5) NULL DEFAULT NULL COMMENT '真旅项目中，总额度变化时的质押率' ;

ALTER TABLE `vbam`.`credit_limit` 
CHANGE COLUMN `credit_mode` `credit_mode` VARCHAR(12) NOT NULL COMMENT '详见@see CreditMode ，授信方式（SYNTHETICAL:综合授信、SINGLE:单笔授信）' ,
CHANGE COLUMN `status` `status` VARCHAR(9) NOT NULL COMMENT '详见@see CreditLimitStatus ，额度状态（NORMAL:正常、FREEZE:冻结、DISABLED:停用、INACTIVE:未激活）' ;



-- 同一个客户同时执行多个放款账扣任务时会同时发送扣款请求BUG
-- task表增加融资客户ID字段
ALTER TABLE `vbam`.`repayment_daily_batch_task` 
ADD COLUMN `customer_id` VARCHAR(32) NOT NULL COMMENT '放款中的融资客户ID' AFTER `loan_info_id`;

-- 修改已有任务的融资客户ID
update `vbam`.`repayment_daily_batch_task` task left join `vbam`.`repayment_loan_info` loan on task.`loan_info_id` = loan.`id` set task.`customer_id` = loan.`finance_id` where loan.`finance_id` is not null;

-- 增加列宽
ALTER TABLE `vbam`.`repayment_daily_batch_task` 
CHANGE COLUMN `fail_message` `fail_message` VARCHAR(256) NULL DEFAULT NULL COMMENT '失败原因' ;
ALTER TABLE `vbam`.`repayment_daily_batch_task_item` 
CHANGE COLUMN `detail` `detail` VARCHAR(256) NULL DEFAULT NULL COMMENT '执行描述' ;


-- 增加列宽
ALTER TABLE `vbam`.`common_sequence_center` 
CHANGE COLUMN `sequence_name` `sequence_name` VARCHAR(64) NOT NULL COMMENT '序列名' ;



-- 真旅非白名单需求
ALTER TABLE `vbam`.`credit_customer_apply` 
ADD COLUMN `in_whitelist` TINYINT(1) NULL COMMENT '是否在白名单内' AFTER `auditor_id`,
ADD COLUMN `attachment` TEXT NULL COMMENT '附件（json串，有type(trust　增信资料; sale 销售资料,supply 补充资料)，文件名，后缀，mediaId）（非白名单客户）' AFTER `in_whitelist`,
ADD COLUMN `pledge_rate` DECIMAL(18,5) NULL COMMENT '质押率（非白名单客户）' AFTER `attachment`,
ADD COLUMN `audit_limit` DECIMAL(18,0) NULL COMMENT '审核时填写的额度（非白名单客户）' AFTER `pledge_rate`,
CHANGE COLUMN `create_time` `create_time` TIMESTAMP NULL DEFAULT NULL COMMENT '创建日期' ;

update vbam.credit_customer_apply set in_whitelist = true;

ALTER TABLE `vbam`.`credit_customer_apply` 
CHANGE COLUMN `in_whitelist` `in_whitelist` TINYINT(1) NOT NULL DEFAULT '1' COMMENT '是否在白名单内' ;

ALTER TABLE `vbam`.`credit_customer_apply` 
ADD COLUMN `approver_id` VARCHAR(32) NULL COMMENT '审批人ID（非白名单客户）' AFTER `audit_limit`;

ALTER TABLE `vbam`.`credit_customer_apply` 
CHANGE COLUMN `access_manual_state` `access_manual_state` VARCHAR(15) NULL DEFAULT NULL COMMENT '是否通过人工审核（\"WAIT\"：未审核，\"WAIT\":待审批（非白名单客户），\"ACCESS\"：通过人工审核，\"NOTACCESS\"：不通过人工审核）' ;



-- 预警
CREATE TABLE `early_warning_customer` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `product_id` varchar(32) NOT NULL COMMENT '产品id',
  `finance_id` varchar(32) DEFAULT NULL COMMENT '融资客户Id',
  `finance_company` varchar(255) NOT NULL COMMENT '融资客户公司名称',
  `level` varchar(32) DEFAULT NULL COMMENT '预警等级',
  `action` varchar(32) DEFAULT NULL COMMENT '预警措施',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `index_early_warning_customer_product_id_finance_id` (`product_id`,`finance_id`) COMMENT '客户预警信息汇总表'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `early_warning_event` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `product_id` varchar(32) NOT NULL COMMENT '产品id',
  `finance_id` varchar(32) DEFAULT NULL COMMENT '融资客户Id',
  `finance_company` varchar(255) NOT NULL COMMENT '融资客户公司名称',
  `type` varchar(32) NOT NULL COMMENT '预警种类',
  `sub_type` varchar(32) DEFAULT NULL COMMENT '预警种类下面的子类型',
  `level` varchar(32) DEFAULT NULL COMMENT '该事件的预警等级',
  `entry_user_id` varchar(32) DEFAULT NULL COMMENT '录入人的用户id',
  `entry_real_name` varchar(255) DEFAULT NULL COMMENT '录入人的真实姓名',
  `entry_review_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '录入事件的审核通过时间',
  `cancel_user_id` varchar(32) DEFAULT NULL COMMENT '事件解除人的用户id',
  `cancel_real_name` varchar(255) DEFAULT NULL COMMENT '事件解除人的真实姓名',
  `cancel_review_time` timestamp NULL DEFAULT NULL COMMENT '解除事件的审核通过时间',
  `occur_time` timestamp NULL DEFAULT NULL COMMENT '发生时间',
  `amount` decimal(18,0) DEFAULT NULL COMMENT '金额。如：法院判决金额',
  `represent` varchar(1000) DEFAULT NULL COMMENT '事件描述',
  `extra_data` varchar(1000) DEFAULT NULL COMMENT '附加数据',
  `state` varchar(32) NOT NULL COMMENT '状态',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `index_early_warning_event_product_id_finance_id` (`product_id`,`finance_id`) COMMENT '预警事件表'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `early_warning_event_review` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `product_id` varchar(32) NOT NULL COMMENT '产品id',
  `finance_id` varchar(32) DEFAULT NULL COMMENT '融资客户Id',
  `finance_company` varchar(255) NOT NULL COMMENT '融资客户公司名称',
  `event_id` varchar(32) NOT NULL COMMENT '预警事件id',
  `event_type` varchar(32) NOT NULL COMMENT '预警种类',
  `event_sub_type` varchar(32) DEFAULT NULL COMMENT '预警种类下面的子类型',
  `apply_type` varchar(32) NOT NULL COMMENT '申请类型：新增，解除',
  `review_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '审核时间',
  `reviewer_user_id` varchar(32) DEFAULT NULL COMMENT '审核人的用户id',
  `reviewer_real_name` varchar(255) DEFAULT NULL COMMENT '审核人的真实姓名',
  `prev_level` varchar(32) DEFAULT NULL COMMENT '该事件审核完成之前的预警等级',
  `extra_data` varchar(1000) DEFAULT NULL COMMENT '附加数据',
  `state` varchar(32) NOT NULL COMMENT '状态',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `index_early_warning_event_review_event_id` (`event_id`),
  KEY `index_early_warning_event_review_product_id_finance_id` (`product_id`,`finance_id`) COMMENT '预警事件审核表'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `early_warning_level_change_history` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `product_id` varchar(32) NOT NULL COMMENT '产品id',
  `finance_id` varchar(32) DEFAULT NULL COMMENT '融资客户Id',
  `finance_company` varchar(255) NOT NULL COMMENT '融资客户公司名称',
  `level` varchar(32) DEFAULT NULL COMMENT '预警等级',
  `in_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '转入时间',
  `out_time` timestamp NULL DEFAULT NULL COMMENT '转出时间',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `index_early_warning_level_change_history_product_id_finance_id` (`product_id`,`finance_id`) COMMENT '预警级别变化历史'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `early_warning_system_event_generate_record` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `product_id` varchar(32) NOT NULL COMMENT '产品id',
  `finance_id` varchar(32) DEFAULT NULL COMMENT '融资客户Id',
  `finance_company` varchar(255) NOT NULL COMMENT '融资客户公司名称',
  `date` char(10) DEFAULT NULL COMMENT '日期: yyyy-MM-dd',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_product_id_finance_id` (`product_id`,`finance_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
SELECT * FROM vbam.early_warning_system_event_generate_record;


grant select,insert,update,execute on vbam.* to 'earlywarning'@'127.0.0.1' identified by 'Abc12345';
grant select,insert,update,execute on vbam.* to 'earlywarning'@'localhost' identified by 'Abc12345';
grant select,insert,update,execute on vbam.* to 'earlywarning'@'192.168.192.0/255.255.255.0' identified by 'Abc12345';