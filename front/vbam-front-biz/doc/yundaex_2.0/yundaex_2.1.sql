-- 修改韵达线上表字段的顺序，保持和op3一致

ALTER TABLE `vbam`.`yundaex_customer_apply` 
CHANGE COLUMN `loan_type` `loan_type` VARCHAR(45) NULL DEFAULT NULL COMMENT '借款类型' AFTER `short_loan`,
CHANGE COLUMN `bail_ratio` `bail_ratio` DECIMAL(18,4) NULL DEFAULT NULL COMMENT '保证金与日资金需求的比率' AFTER `station_address`,
CHANGE COLUMN `grade_state_remarks` `grade_state_remarks` VARCHAR(45) NULL DEFAULT NULL COMMENT '评级状态备注' ,
CHANGE COLUMN `notify_flag` `notify_flag` TINYINT(1) NOT NULL COMMENT '是否发送通知' AFTER `apply_type`,
DROP INDEX `idx_yundaex_customer_apply_audit_time` ,
ADD INDEX `idx_yundaex_customer_apply_apply_type` (`apply_type` ASC),
DROP INDEX `idx_yundaex_customer_apply_apply_type` ,
ADD INDEX `idx_yundaex_customer_apply_audit_time` (`audit_time` ASC);


ALTER TABLE `vbam`.`yundaex_tstation_info` 
CHANGE COLUMN `avg_receive_num` `avg_receive_num` DECIMAL(15,3) NOT NULL DEFAULT 0.000 COMMENT '日平均揽件数' ,
CHANGE COLUMN `receive_sum_signed` `receive_sum_signed` INT(11) NOT NULL DEFAULT 0 COMMENT '已签收总数（揽件）' ,
CHANGE COLUMN `receive_sum_unsign` `receive_sum_unsign` INT(11) NOT NULL DEFAULT 0 COMMENT '未签收总数（揽件）' ,
CHANGE COLUMN `avg_send_num` `avg_send_num` DECIMAL(15,3) NOT NULL DEFAULT 0.000 COMMENT '日平均到件数' ,
CHANGE COLUMN `send_sum_signed` `send_sum_signed` INT(11) NOT NULL DEFAULT 0 COMMENT '已签收总数（派件）' ,
CHANGE COLUMN `send_sum_unsign` `send_sum_unsign` INT(11) NOT NULL DEFAULT 0 COMMENT '未签收总数（派件）' ,
CHANGE COLUMN `rec_send_dif` `rec_send_dif` INT(11) NOT NULL DEFAULT 0 COMMENT '揽件派件月差额' ,
CHANGE COLUMN `rec_send_ratio` `rec_send_ratio` DECIMAL(15,3) NOT NULL DEFAULT 0.000 COMMENT '揽派比' ;