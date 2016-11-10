
-- 城市等级评分修改
update `yundaex_grade_model` set details='{"FIRST_TIER":"10","SECOND_TIER":"8","THIRD_TIER":"6","FOUR_TIER":"4","OTHER":"0"}' where id='2';

-- 城市等级 新增OTHER
insert into vbam.`yundaex_composite_cost` VALUES (5,'OTHER','OTHER',2000,2000,1000);

ALTER TABLE `vbam`.`yundaex_customer_apply` 
ADD COLUMN `notify_flag` TINYINT(1) NOT NULL COMMENT '是否发送通知';
ALTER TABLE `vbam`.`yundaex_customer_apply` 
ADD COLUMN `apply_type` VARCHAR(45) NOT NULL COMMENT '申请类型';
ALTER TABLE `vbam`.`yundaex_customer_apply` 
ADD COLUMN `audit_time` VARCHAR(20) NULL COMMENT '审核时间';


ALTER TABLE `vbam`.`yundaex_customer_apply` 
CHANGE COLUMN `bail_ratio` `bail_ratio` DECIMAL(18,4) NULL DEFAULT NULL COMMENT '保证金与日资金需求的比率' ;
