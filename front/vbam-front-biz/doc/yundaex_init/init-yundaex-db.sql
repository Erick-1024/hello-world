-- 插入核心企业 韵达
INSERT INTO `member_user` (`id`, `master_id`, `username`, `password`, `company_name`, `organization_code`, `organization_code_certificate_media_id`, `business_licence_code`, `business_licence_media_id`, `contact_name`, `contact_Tel`, `contact_mail`, `user_type`, `user_status`, `job_title`, `role_id`, `token`, `signedIn`, `signIn_time`, `signIn_ip`, `create_time`, `upate_time`, `audit_time`, `security_code`, `security_code_expiration_time`, `agent_company`, `contact_identity_card_front_media_id`, `contact_identity_card_back_media_id`, `legal_person_identity_card_front_media_id`, `legal_person_identity_card_back_media_id`, `tax_registration_certificate_code`, `tax_registration_certificate_media_id`, `job_no`, `real_name`, `pay_password`, `guide_status`, `employee_tel`, `employee_mail`, `employee_job_title`, `cert_subject_dn`)
VALUES ('201603280002', NULL, 'yundaex', '91ae55b7955b5d79dad980d111f6822eddbaba51d819ebdbf375dda0269374e0','上海韵达速递（物流）有限公司', '111111', '563d9eaf62b8193f6527e50d', '111111', '563d9eaf62b8193f6527e50c', '韵达', '11111111', '11111111', 'CORECOMPANY', 'ACTIVATED', '111111', '201603312146580002', 'DzDxHhXuD8GiykDkT0Tl2ljwjwDymlD8jkzmijmhTwT3A=MYOAfRZpfBZJN1ZRLUOlYNMEMAP5dxP4PdYZY1YFLAZAO=', 1, '2016-06-06 09:40:01', '116.228.239.158', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '123456', NULL, NULL, NULL, '91ae55b7955b5d79dad980d111f6822eddbaba51d819ebdbf375dda0269374e0', NULL, NULL, NULL, NULL, NULL);

-- 插入核心企业审批
INSERT INTO `member_audit` (`id`, `auditor_id`, `audit_message`, `create_time`, `update_time`, `audit_status`, `customer_id`) VALUES
('yundaex', 'cana-user', '', '2016-07-05 08:00:44', NULL, 63, '201603280002');

-- 插入韵达项目
INSERT INTO `asset_project` (`id`, `name`, `type`, `status`, `core_company_id`, `core_company_name`, `core_organization_code`, `core_business_licence_code`, `core_tax_registration_certificate_code`, `core_industry`, `core_economic_category`, `core_account_no`, `finance_applicant`, `single_loan_limit_lower`, `single_loan_limit_upper`, `interest_rate_unit`, `interest_rate_lower`, `interest_rate_upper`, `loan_period_unit`, `loan_period_lower`, `loan_period_upper`, `repayment_methods`, `early_repayment_charge_ratio`, `extension_days`, `extension_ratio_method`, `extension_ratio`, `penalty_rate_method`, `penalty_rate`, `deduction_time`, `deduction_rule`, `create_time`, `update_time`, `create_user_id`, `create_customer_id`)
VALUES
	('yundaex_project_id', '信韵融', 'platform', 'GOING', '201603280002', '上海韵达速递（物流）有限公司', '111111', '111111', '123456', 'APPLIANCE', 'HMT', '', '韵达网点', 0, 200000000, 'MONTH', 0.00040, 0.00050, 'MONTH', 1, 1, 'ORDER,MONTHLY,EQUALALL,EQUALPRINCIPAL', 0.00000, 0, 'RATIO', 0.00000, 'RATIO', 0.50000, '20:00', 'ALL', '2016-05-30 09:12:23', '2016-05-30 09:12:23', '201607050033', '201607050033');

-- OP3 插入资金方
INSERT INTO `asset_project_factor` (`id`, `project_id`, `company_id`, `company_name`, `organization_code`, `business_licence_code`, `tax_registration_certificate_code`, `account_no`, `status`, `create_time`, `update_time`)
VALUES
	('150525164420001', 'yundaex_project_id', '201607050033', '凯拿商业保理（深圳）有限公司', '111111', '111111', '123456', '3110210003631017752', 'NORMAL', '2016-05-25 16:44:21', '2016-05-25 16:44:21');

-- OP3 yd私钥
INSERT INTO `common_rsa_key` (`institution_id`, `public_key`, `private_key`)
VALUES
	('yd', '', 'MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAJ/bBMc8xt0rj5D4G3fjmfvS64x6abBV2svFVywNrUs84s4yEMYMLztlVbjlqfuRrk5kKJUxiQk/tcSCFexDDJg6eBKn/yhoaN5KPTQYWK4gvFC8GDvyiTYbIrUDBCUBgTlUw03KuOYfGig/FU64yo9fwncCptHpSGLGKG0PG6yRAgMBAAECgYBbWCLV2qPkNAUYmL3RdyxBWtggcL699YFOGVjjonkY2hTcMH2nPcUrko82VX4Zc7h/sPoDrseYkmBChvcSB4pE8nD7WYXyJAcn3awI7KgDLjGFXLECjAFxoWpUV/MeLl0eOHF0Aas/QKLCbNA+pSPlr9eTuL+cWv+bJKMGrsOHRQJBAPgygafNnorKMfHaQLm/gsqhPsn34fDre3c3KIcYaNXw8xnbENKiunjqBCN4tIFCeJ8koigvz8egBewLZ7LFjD8CQQCk4YmEzWabIZboqsPlV6lO3lXqEjFTTJMyoMIVPBPFDmaUtOWhyaR2QtLFB5iE2zci2rctHcHcx1zicDuiAdMvAkEA7HIhNp0aZpoNTpxfgRVDIorOaFYv9vY/Hul7QIguh3IOBhm+QwwnnT1DSA+b1nBevS2HFcXGqiR2v9J1rceovQJALWiT2Ag9huHUTtWgx8vBrB/dZ26FORywuVO+v9xg0Xdq1u+PAmSnL46tv2edIJHC+I1lvz4fwu8A7LfeJHr+LwJATG/AwsWYKu2yDx38+XNxcwGF32JpTnzzO2CMW2ofqvGu85PuT056mcniGb+2n9G1hN8Oh9YJzw9F2UzYI8JVUw==');

-- 生产 上线后资金方
INSERT INTO `asset_project_factor` (`id`, `project_id`, `company_id`, `company_name`, `organization_code`, `business_licence_code`, `tax_registration_certificate_code`, `account_no`, `status`, `create_time`, `update_time`)
VALUES
('150525164420001', 'yundaex_project_id', '201607050033', '凯拿商业保理（深圳）有限公司', '111111', '111111', '123456', '3110210010051296981', 'NORMAL', '2016-05-25 16:44:21', '2016-05-25 16:44:21');

-- 插入韵达站点基础信息表	
INSERT INTO `yundaex_station_operation`(`id`,`cost_manual`,`cost_receipt`,`cost_package`,`transit_fee`,`other_materials_cost_rate`,`opposite_station_send_cost`,`average_profit`,`send_income`,`courier_fee`,`other_materials_cost_rate_send`,`transit_fee_bail`,`other_fee_bail`)
VALUES
    ('1','0.20','1.00','0.30','2.00','0.01','1.70','7.00','1.70','0.50','0.01','2.00','1.00');

-- 插入韵达融资角色
INSERT INTO `member_role` (`id`, `role_name`, `description`, `type`, `master_id`, `permissions`, `ord`, `createTime`, `updateTime`, `role_type`)
VALUES
	('201600000000000003', '韵达融资客户', NULL, 'LEVEL1', NULL, 'AM;AM_ACCOUNT_APPLY;AM_ACCOUNT;AM_ACCOUNT_DETAIL;AM_ACCOUNT_SUPERVISION_CREATE;AM_ACCOUNT_SUPERVISION_REMOVE;AM_ACCOUNT_WITHDRAW_FUND;AM_ACCOUNT_TRANSFER_FUND;AM_ACCOUNT_TRADE_RECORD_LIST;AM_ACCOUNT_AUDIT_CENTER_LIST;AM_ACCOUNT_SUPERVISION_APPLY_DETAIL;AM_ACCOUNT_SUPERVISION_APPLY_AUDIT;AM_ACCOUNT_TRADE_APPLY_DETAIL;AM_ACCOUNT_TRADE_APPLY_AUDIT;FM;FM_REPAYMENTINFO_MANAGE;FM_REPAYMENTINFO_MANAGE_PLANDETAIL;FM_REPAYMENTINFO_MANAGE_PLANDETAIL_INFO;FM_REPAYMENTINFO_MANAGE_REPAYMENTDETAILSHISTORY;FM_ACTIVE_REPAYMENT_PLAN;FM_OVERDUE_REPAYMENT_PLAN;FM_OVERDUE_ACTIVE_REPAYMENT;FM_SEVEN_REPAYMENT_PLAN;FM_SEVEN_ACTIVE_REPAYMENT;PM;PM_EMPLOYEE_ROLE;PM_EMPLOYEE_ROLE_ADD;PM_EMPLOYEE_ROLE_DETAIL;PM_EMPLOYEE_ROLE_UPDATE;PM_EMPLOYEE_LIST;PM_EMPLOYEE_ADD;PM_EMPLOYEE_LIST_DETAIL;PM_EMPLOYEE_LIST_UPDATE;PM_EMPLOYEE_LIST_DELETE;PM_EMPLOYEE_LIST_RESET_PASSWORD;PM_EMPLOYEE_LIST_RETRY_MAIL;PC;PC_COMPANY_INFO;PC_COMPANY_INFO_UPDATE;PC_CONTACTS_INFO;PC_CONTACTS_INFO_MODIFY;PC_ACCOUNT_INFO;PC_ACCOUNT_NUMBER_INFO_SET_PAYPWD;PC_ACCOUNT_NUMBER_INFO_UPDATE_PAYPWD;PC_CONTACTS;MA;MA_ACCOUNT_SUPERVISION_APPLY_DETAIL_SELF;MA_ACCOUNT_TRADE_APPLY_DETAIL_SELF;SR;SR_REPAYMENT_DAILY_REPORT;SR_REPAYMENT_ANNUAL_REPORT;SR_REPAYMENT_COUNT_REPORT;SR_FUND_DAILY_REPORT;SR_FUND_YEAR_REPORT;SR_FUND_DAILY_COUNT_REPORT;YD;YD_LOAN_LOANDETAIL;YD_LOAN_LOANFLOW', NULL, '2016-04-01 11:24:44', '2016-07-05 10:48:33', 'FINACE');

-- 插入信韵融用户默认roleId
INSERT INTO `repayment_product`(`id`,`name`,`detail`)
VALUES    
    ('yundaex_project_id','信韵融','{"finaceRoleId":"201600000000000003"}');

-- 插入城市等级及基本成本信息表
INSERT INTO `yundaex_composite_cost` (`id`,`city_level`,`station_city`,`rental_cost`,`transport_cost`,`defect_cost`)
VALUES 
     (1,'FIRST_TIER','北京市、上海市、广州市、深圳市、天津市、重庆市、沈阳市、南京市、武汉市、成都市、大连市、杭州市、青岛市、厦门市、西安市、长沙市、苏州市、宁波市、无锡市、',10000.00,5000.00,5000.00),(2,'SECOND_TIER','哈尔滨市、长春市、合肥市、郑州市、佛山市、南昌市、贵阳市、南宁市、石家庄市、太原市、温州市、烟台市、珠海市、常州市、南通市、扬州市、徐州市、东莞市、福州市、潍坊市、嘉兴市、惠州市、泉州市、洛阳市、济南市、昆明市、海口市、金华市、汕头市、乌鲁木齐市、',5000.00,3000.00,2000.00),(3,'THIRD_TIER','兰州市、桂林市、三亚市、绍兴市、绵阳市、秦州市、银川市、中山市、保定市、西宁市、芜湖市、宜昌市、莆田市、怀化市、威海市、邯郸市、临沂市、唐山市、台州市、漳州市、湖州市、包头市、济宁市、盐城市、鞍山市、廊坊市、衡阳市、梅州市、吉林市、大庆市、淮安市、丽江市、揭阳市、荆州市、榆林市、南充市、遵义市、上饶市、龙岩市、衢州市、赤峰市、湛江市、运城市、宝鸡市、岳阳市、安阳市、株洲市、镇江市、淄博市、南平市、宜春市、常德市、柳州市、咸阳市、宜宾市、泸州市、蚌埠市、邢台市、舟山市、抚顺市、德阳市、张家口市、鄂尔多斯市、齐齐哈尔市、连云港市、呼伦贝尔市、呼和浩特市、秦皇岛市、赣州市、郴州市、',3000.00,3000.00,2000.00),(4,'FOUR_TIER','临汾市、南阳市、新乡市、肇庆市、丹东市、德州市、菏泽市、江门市、黄山市、渭南市、营口市、娄底市、九江市、永州市、邵阳市、清远市、大同市、枣庄市、北海市、丽水市、孝感市、沧州市、玉溪市、聊城市、三明市、开封市、锦州市、汉中市、商丘市、秦安市、通辽市、延安市、曲靖市、东营市、韶关市、拉萨市、襄阳市、湘潭市、盘锦市、黄石市、酒泉市、安庆市、宁德市、四平市、晋州市、滁州市、衡水市、钦州市、茂名市、十堰市、宿迁市、潮州市、承德市、广元市、黄冈市、本溪市、绥化市、萍乡市、许昌市、日照市、铁岭市、池州市、淮南市、六安市、咸阳市、信阳市、吕梁市、辽阳市、朝阳市、梧州市、达州市、益阳市、通化市、白色市、白山市、乐山市、阜阳市、抚州市、铜陵市、河源市、阳江市、驻马店市、平顶山市、佳木斯市、马鞍山市、牡丹江市、葫芦岛市、延边市、大理市、恩施市、',3000.00,3000.00,2000.00);
     

-- 插入韵达评级模型分数对应等级表
INSERT INTO `yundaex_grade_info` 
VALUES 
     (1,95.00,100.00,'AAA',0.13,1.00),(2,90.00,95.00,'AA',0.14,0.95),(3,80.00,90.00,'A',0.16,0.90),(4,70.00,80.00,'BBB',0.18,0.70),(5,60.00,70.00,'BB',0.00,0.50),(6,50.00,60.00,'B',0.00,0.35),(7,40.00,50.00,'CCC',0.00,0.40),(8,30.00,40.00,'CC',NULL,0.20),(9,20.00,30.00,'C',NULL,0.15),(10,0.00,20.00,'D',NULL,0.01);
     
-- 插入韵达评级模型表
INSERT INTO `yundaex_grade_model` 
VALUES 
     (1,'baseInfo','controllerOrigin','0.18595625','{\"福建省\":\"0\",\"OTHER\":\"10\"}','SINGLE'),(2,'baseInfo','cityLevel','0.27822747','{\"FIRST_TIER\":\"10\",\"SECOND_TIER\":\"8\",\"THIRD_TIER\":\"6\",\"FOUR_TIER\":\"4\"}','SINGLE'),(3,'baseInfo','stationAddress','0.3753887','{\"DOWNTOWN\":\"10\",\"TOWN\":\"6\",\"COUNTRYSIDE\":\"0\"}','SINGLE'),(4,'cooperateInfo','busiLimit','2.91705004','{\"0-2\":\"0\",\"2-3\":\"7\",\"3-5\":\"8\",\">5\":\"10\"}','INTERVAL'),(5,'cooperateInfo','stationAmount','1.92962522','{\"0-5\":\"5\",\"6-20\":\"9\",\">21\":\"10\"}','INTERVAL'),(6,'transactionInfo','recAndSendGrowthRate','1.77617055','{\"<0\":\"0\",\"0-0.05\":\"5\",\"0.05-0.1\":\"8\",\">0.1\":\"10\"}','INTERVAL'),(7,'transactionInfo','yundaexJudge','1.03656922','{\"优秀\":\"10\",\"良好\":\"8\",\"差\":\"0\"}','SINGLE'),(8,'baseInfo','city','0.75429474','{\"上海市，义乌市，金华市，东阳市，杭州市，广州市\":\"10\",\"北京市，苏州市，常熟市\":\"8\",\"OTHER\":\"0\"}','SINGLE'),(9,'baseInfo','loanType','0.74671782','{\"FINANCIAL\":\"10\",\"NONFINANCIAL\":\"6\",\"NONE\":\"4\",\"UNKNOWN\":\"2\"}','SINGLE');
     
-- 插入韵达系统规则表
INSERT INTO `yundaex_audit_rule` (`batch_no`,`applyCustomer_address`,`cooperation_period`,`receive_send_growth_rate`,`negative_infomation_search`,`is_court_execute_individual_info`,`station_address`,`deposit_amount`,`is_ranchise_contract`,`is_qualified_inspection_record`)
VALUES 
     (1,'西藏自治区/新疆维吾尔自治区',2,0.05000,0,1,1,1,1,1); 

-- 修改合同表
ALTER TABLE `vbam`.`asset_contract` 
CHANGE COLUMN `account_no` `account_no` VARCHAR(19) NULL COMMENT '监管账号' ,
CHANGE COLUMN `account_supervision_id` `account_supervision_id` VARCHAR(32) NULL COMMENT '监管关系ID' ,
CHANGE COLUMN `asset_transferee_ratio` `asset_transferee_ratio` VARCHAR(10) NULL COMMENT '资产受让比例' ,
CHANGE COLUMN `transfer_price` `transfer_price` DECIMAL(18,0) NULL COMMENT '转让价款' ,
CHANGE COLUMN `expected_profit_rate` `expected_profit_rate` VARCHAR(32) NULL COMMENT '预期收益率' ;
