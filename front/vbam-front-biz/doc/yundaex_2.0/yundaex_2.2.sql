-- 2016-09-23 15:14:00

CREATE TABLE `vbam`.`yundaex_customer_apply_monitor` (
  `id` VARCHAR(32) NOT NULL COMMENT '主键',
  `station_no` VARCHAR(45) NOT NULL COMMENT '站点编号',
  `station_amount` INT(11) NULL DEFAULT 0 COMMENT '站点数量',
  `station_mgr` VARCHAR(255) NOT NULL COMMENT '站点负责人/公司名称',
  `station_name` VARCHAR(255) NOT NULL COMMENT '站点名称',
  `busi_limit` DECIMAL(18,0) NULL DEFAULT 0 COMMENT '加盟年限',
  `bail_balance` DECIMAL(18,0) NULL DEFAULT 0 COMMENT '保证金账户余额（单位：分）',
  `short_loan` DECIMAL(18,0) NULL DEFAULT 0 COMMENT '短期借款（单位：分）',
  `loan_type` VARCHAR(45) NOT NULL COMMENT '借款类型',
  `loan_limit` INT(5) NULL DEFAULT 1 COMMENT '借款期限',
  `limit_unit` VARCHAR(45) NOT NULL COMMENT '期限单位',
  `yundaex_judge` VARCHAR(45) NOT NULL COMMENT '韵达评价',
  `month` VARCHAR(7) NOT NULL COMMENT '月份（yyyy-MM）',
  `status` VARCHAR(45) NOT NULL COMMENT '状态',
  `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  INDEX `idx_station_no` (`station_no` ASC),
  INDEX `idx_status` (`status` ASC),
  INDEX `idx_create_time` (`create_time` ASC),
  INDEX `idx_update_time` (`update_time` ASC)),
  INDEX `idx_month` (`month` ASC);
  

  -- 2016-09-27 13:52:00
  
  CREATE TABLE `vbam`.`yundaex_credit_monitor` (
  `id` VARCHAR(32) NOT NULL COMMENT '主键',
  `station_no` VARCHAR(45) NOT NULL COMMENT '站点编号',
  `station_mgr` VARCHAR(255) NOT NULL COMMENT '站点负责人/公司名称',
  `station_name` VARCHAR(255) NOT NULL COMMENT '站点名称',
  `member_id` VARCHAR(45) NOT NULL COMMENT 'Cana用户的ID',
  `recandsend_growth_rate` DECIMAL(18,5) NOT NULL COMMENT '揽派件增长率',
  `day_requirements` DECIMAL(18,0) NULL DEFAULT 0 COMMENT '日资金需求（单位：分）',
  `yundaex_grade` DECIMAL(5,2) NOT NULL COMMENT '韵达评分',
  `last_yundaex_grade` DECIMAL(5,2) NOT NULL COMMENT '上个月韵达评分'
  `bail_balance` DECIMAL(18,0) NULL DEFAULT 0 COMMENT '保证金账户余额（单位：分）',
  `net_cashflow` DECIMAL(18,0) NULL DEFAULT 0 COMMENT '净现金流（单位：分）',
  `credit_limit` DECIMAL(18,0) NULL DEFAULT 0 COMMENT '最大授信金额（单位：分）',
  `last_credit_limit` DECIMAL(18,0) NULL DEFAULT 0 COMMENT '上个月最大授信金额（单位：分）'
  `overdues` INT(5) NULL DEFAULT 0 COMMENT '逾期次数',
  `interest_rate` VARCHAR(1000) NOT NULL DEFAULT '' COMMENT '利率(每个还款方式都有json字符串)',
  `month` VARCHAR(7) NOT NULL COMMENT '月份（yyyy-MM）',
  `audit_status` VARCHAR(45) NOT NULL COMMENT '审核状态',
  `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  INDEX `idx_station_no` (`station_no` ASC),
  INDEX `idx_member_id` (`member_id` ASC),
  INDEX `idx_month` (`month` ASC),
  INDEX `idx_audit_status` (`audit_status` ASC),
  INDEX `idx_create_time` (`create_time` ASC),
  INDEX `idx_update_time` (`update_time` ASC)),
  UNIQUE INDEX `yundaex_credit_monitor` (`station_no` ASC, `month` ASC);
  
  ALTER TABLE `vbam`.`yundaex_customer_apply` 
  ADD COLUMN `recandsend_growth_rate` DECIMAL(18,5) NOT NULL COMMENT '揽派件增长率' AFTER `audit_time`;
  ADD COLUMN `limit_unit` VARCHAR(45) NULL COMMENT '期限单位' AFTER `loan_from`;
  ADD COLUMN `short_loan_limit` INT(5) NULL DEFAULT 1 COMMENT '借款期限' AFTER `loan_type`;
