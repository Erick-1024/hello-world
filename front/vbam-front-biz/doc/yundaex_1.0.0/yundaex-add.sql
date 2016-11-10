# 删除韵达项目放款流水未用到的字段
ALTER TABLE `yundaex_loan_info_record` 
DROP COLUMN change_type;
ALTER TABLE `yundaex_loan_info_record` 
DROP COLUMN repayment_period;

# 增加韵达项目放款流水字段
ALTER TABLE `yundaex_loan_info_record` 
ADD factor_transfer_in_account_no varchar(50) DEFAULT NULL COMMENT '保理商回款账号';
ALTER TABLE `yundaex_loan_info_record` 
ADD deduction_time varchar(50) DEFAULT NULL COMMENT '保理商指定账扣时间';
ALTER TABLE `yundaex_loan_info_record` 
ADD deduction_rule varchar(50) DEFAULT NULL COMMENT '扣款规则';
ALTER TABLE `yundaex_loan_info_record` 
ADD extension_days int(20) DEFAULT NULL COMMENT '展期天数';
ALTER TABLE `yundaex_loan_info_record` 
ADD extension_ratio decimal(8,5) DEFAULT '0.00000' COMMENT '展期上浮利率值 或者是 展期上浮比例';
ALTER TABLE `yundaex_loan_info_record` 
ADD extension_charge_method varchar(50) DEFAULT NULL COMMENT '展期率计算方式';
ALTER TABLE `yundaex_loan_info_record` 
ADD early_repayment_charge_ratio  decimal(8,5) DEFAULT '0.00000' COMMENT '提前还款手续费率';
ALTER TABLE `yundaex_loan_info_record` 
ADD penalty_rate decimal(8,5) DEFAULT '0.00000' COMMENT '罚息率或者是 罚息上浮比例';
ALTER TABLE `yundaex_loan_info_record` 
ADD charge_method varchar(50) DEFAULT NULL COMMENT '罚息计算方式';


# 修改展期利率方式为固定值
update `asset_project` set `extension_ratio_method`='AMOUNT' where id='yundaex_project_id'



# 评级模型相关表  修改
ALTER TABLE `yundaex_customer_grade` 
DROP COLUMN policy_description;

ALTER TABLE `yundaex_customer_apply` 
ADD loan_type varchar(45) DEFAULT NULL COMMENT '借款类型';

ALTER TABLE `yundaex_customer_apply` 
MODIFY repayment_type varchar(50) DEFAULT NULL COMMENT '还款方式';


DROP TABLE IF EXISTS yundaex_grade_info;

CREATE TABLE `yundaex_grade_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '序号 主键',
  `min_points` decimal(5,2) DEFAULT NULL COMMENT '最小分值',
  `max_points` decimal(5,2) DEFAULT NULL COMMENT '最大分值',
  `grade` varchar(45) DEFAULT NULL COMMENT '等级',
  `beta` decimal(5,2) DEFAULT NULL COMMENT '利率定价',
  `ratio` decimal(5,2) DEFAULT NULL COMMENT '系数值',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `yundaex_grade_info` 
VALUES 
     (1,95.00,100.00,'AAA',0.13,1.00),(2,90.00,95.00,'AA',0.14,0.95),(3,80.00,90.00,'A',0.16,0.90),(4,70.00,80.00,'BBB',0.18,0.70),(5,60.00,70.00,'BB',0.00,0.50),(6,50.00,60.00,'B',0.00,0.35),(7,40.00,50.00,'CCC',0.00,0.40),(8,30.00,40.00,'CC',NULL,0.20),(9,20.00,30.00,'C',NULL,0.15),(10,0.00,20.00,'D',NULL,0.01);
 
     
DROP TABLE IF EXISTS yundaex_grade_model;

CREATE TABLE `yundaex_grade_model` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `first_target` varchar(45) NOT NULL COMMENT '一级指标',
  `second_target` varchar(45) NOT NULL COMMENT '二级指标',
  `weight` varchar(45) NOT NULL COMMENT '权重×10',
  `details` varchar(500) NOT NULL COMMENT '详情',
  `type` varchar(45) NOT NULL COMMENT '类型，决定该二级指标获取分数的方法',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `yundaex_grade_model` 
VALUES 
     (1,'baseInfo','controllerOrigin','0.18595625','{\"福建省\":\"0\",\"OTHER\":\"10\"}','SINGLE'),(2,'baseInfo','cityLevel','0.27822747','{\"FIRST_TIER\":\"10\",\"SECOND_TIER\":\"8\",\"THIRD_TIER\":\"6\",\"FOUR_TIER\":\"4\"}','SINGLE'),(3,'baseInfo','stationAddress','0.3753887','{\"DOWNTOWN\":\"10\",\"MARKET\":\"6\",\"COUNTRYSIDE\":\"0\"}','SINGLE'),(4,'cooperateInfo','busiLimit','2.91705004','{\"0-2\":\"0\",\"2-3\":\"7\",\"3-6\":\"8\",\">5\":\"10\"}','INTERVAL'),(5,'cooperateInfo','stationAmount','1.92962522','{\"0-6\":\"5\",\"6-21\":\"9\",\">20\":\"10\"}','INTERVAL'),(6,'transactionInfo','recAndSendGrowthRate','1.77617055','{\"<0\":\"0\",\"0-0.05\":\"5\",\"0.05-0.10001\":\"8\",\">0.1\":\"10\"}','INTERVAL'),(7,'transactionInfo','yundaexJudge','1.03656922','{\"excellent\":\"10\",\"fine\":\"8\",\"bad\":\"0\"}','SINGLE'),(8,'baseInfo','city','0.75429474','{\"上海市，义乌市，金华市，东阳市，杭州市，广州市\":\"10\",\"北京市，苏州市，常熟市\":\"8\",\"OTHER\":\"0\"}','SINGLE'),(9,'baseInfo','loanType','0.74671782','{\"FINANCIAL\":\"10\",\"NONFINANCIAL\":\"6\",\"NONE\":\"4\",\"UNKNOWN\":\"2\"}','SINGLE');




