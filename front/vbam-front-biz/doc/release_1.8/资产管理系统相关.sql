CREATE TABLE `asset_operate_log` (
  `id` varchar(32) NOT NULL DEFAULT '' COMMENT '主键',
  `log_type` varchar(16) NOT NULL DEFAULT '' COMMENT '操作日志类型，项目',
  `target_id` varchar(32) NOT NULL DEFAULT '' COMMENT '对应类型的目标记录ID',
  `content` text NOT NULL COMMENT '操作内容',
  `user_id` varchar(32) NOT NULL DEFAULT '' COMMENT '操作人ID',
  `username` varchar(50) NOT NULL DEFAULT '' COMMENT '操作人用户名',
  `real_name` varchar(50) DEFAULT NULL COMMENT '用户真实名称',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='操作日志';

CREATE TABLE `asset_project` (
  `id` varchar(32) NOT NULL DEFAULT '' COMMENT '主键',
  `name` varchar(256) NOT NULL DEFAULT '' COMMENT '项目名称',
  `type` varchar(16) NOT NULL DEFAULT '' COMMENT '项目类型，平台、保理、租赁、小贷',
  `status` varchar(16) NOT NULL DEFAULT '' COMMENT '项目状态：立项、进行中、暂停、结束',
  `core_company_id` varchar(32) DEFAULT NULL COMMENT '核心企业ID',
  `core_company_name` varchar(256) DEFAULT '' COMMENT '核心企业名称',
  `core_organization_code` varchar(50) DEFAULT '' COMMENT '核心企业组织机构代码',
  `core_business_licence_code` varchar(50) DEFAULT '' COMMENT '核心企业营业执照号码',
  `core_tax_registration_certificate_code` varchar(50) DEFAULT '' COMMENT '核心企业税务登记证号吗',
  `core_industry` varchar(16) DEFAULT '' COMMENT '行业',
  `core_economic_category` varchar(16) DEFAULT NULL COMMENT '经济类型',
  `core_account_no` varchar(32) DEFAULT NULL COMMENT '核心企业银行账号',
  `finance_applicant` varchar(256) DEFAULT '' COMMENT '融资申请人',
  `single_loan_limit_lower` decimal(18,0) DEFAULT NULL COMMENT '单笔贷款下限',
  `single_loan_limit_upper` decimal(18,0) DEFAULT NULL COMMENT '单笔贷款上限',
  `interest_rate_unit` varchar(16) DEFAULT NULL COMMENT '利率单位，see InterestRateUnit',
  `interest_rate_lower` decimal(8,5) DEFAULT NULL COMMENT '利率区间下限',
  `interest_rate_upper` decimal(8,5) DEFAULT NULL COMMENT '利率区间上限',
  `loan_period_unit` varchar(16) DEFAULT NULL COMMENT '期限单位，see DateUnit',
  `loan_period_lower` int(11) DEFAULT NULL COMMENT '期限下限',
  `loan_period_upper` int(11) DEFAULT NULL COMMENT '期限上限',
  `repayment_methods` varchar(256) DEFAULT NULL COMMENT 'see RepaymentType，允许的还本付息方式，多个值之间使用英文逗号分隔',
  `early_repayment_charge_ratio` decimal(8,5) DEFAULT NULL COMMENT '提前还款手续费率',
  `extension_days` int(11) DEFAULT NULL COMMENT '展期天数',
  `extension_ratio_method` varchar(16) DEFAULT NULL COMMENT 'see ChargeMethod，展期收息方式，加固定值、按比例上浮',
  `extension_ratio` decimal(8,5) DEFAULT NULL COMMENT '展期上浮利率值 或者是 展期上浮比例',
  `penalty_rate_method` varchar(16) DEFAULT NULL COMMENT 'see ChargeMethod，罚息收息方式，加固定值、按比例上浮',
  `penalty_rate` decimal(8,5) DEFAULT NULL COMMENT '逾期上浮利率值 或者是 逾期上浮比例',
  `deduction_time` varchar(16) DEFAULT NULL COMMENT '扣款时点，格式为HH:mm',
  `deduction_rule` varchar(16) DEFAULT NULL COMMENT '扣款规则，see DeductionRule',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `create_user_id` varchar(32) NOT NULL DEFAULT '' COMMENT '创建用户ID',
  `create_customer_id` varchar(32) NOT NULL DEFAULT '' COMMENT '创建客户ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='资产管理项目表';


CREATE TABLE `asset_project_blob_text` (
  `project_id` varchar(32) NOT NULL DEFAULT '' COMMENT '关联项目的ID',
  `product_introduction` text COMMENT '产品描述',
  `product_type_desc` text COMMENT '产品类型描述',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`project_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `asset_project_document` (
  `id` varchar(32) NOT NULL DEFAULT '' COMMENT '主键',
  `project_id` varchar(32) NOT NULL COMMENT '关联的项目ID',
  `version` varchar(50) NOT NULL COMMENT '文件版本日期',
  `name` varchar(50) NOT NULL DEFAULT '' COMMENT '显示名称',
  `media_id` varchar(32) NOT NULL DEFAULT '' COMMENT '文件在媒体服务器的ID',
  `deleted` tinyint(1) DEFAULT NULL COMMENT '是否已经被删除了',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新记录时间',
  PRIMARY KEY (`id`),
  KEY `document_project_id` (`project_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='项目中的文件附件';


CREATE TABLE `asset_project_factor` (
  `id` varchar(32) NOT NULL DEFAULT '' COMMENT '主键',
  `project_id` varchar(32) NOT NULL COMMENT '关联的项目ID',
  `company_id` varchar(32) DEFAULT NULL COMMENT '资金方企业ID',
  `company_name` varchar(256) NOT NULL COMMENT '资金方企业名称',
  `organization_code` varchar(50) NOT NULL DEFAULT '' COMMENT '企业组织机构代码',
  `business_licence_code` varchar(50) NOT NULL DEFAULT '' COMMENT '企业营业执照号码',
  `tax_registration_certificate_code` varchar(50) NOT NULL DEFAULT '' COMMENT '企业税务登记证号吗',
  `account_no` varchar(32) DEFAULT NULL COMMENT '企业银行账号',
  `status` varchar(16) DEFAULT NULL COMMENT '合作状态，正常、暂停，@see ProjectFactorStatusEnum',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `index_project_id` (`project_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='项目中的资金方信息';




-- op3
grant select,insert,update,execute on vbam.* to 'asset_server'@'192.168.1.0/255.255.255.0' identified by 'Abc12345';
grant select,insert,update,execute on vbam.* to 'asset_server'@'localhost' identified by 'Abc12345';
grant select,insert,update,execute on vbam.* to 'asset_server'@'127.0.0.1' identified by 'Abc12345';

grant delete on vbam.asset_project_factor to 'asset_server'@'192.168.1.0/255.255.255.0' identified by 'Abc12345';
grant delete on vbam.asset_project_factor to 'asset_server'@'localhost' identified by 'Abc12345';
grant delete on vbam.asset_project_factor to 'asset_server'@'127.0.0.1' identified by 'Abc12345';


-- prod
grant delete on vbam.asset_project_factor to 'asset_server'@'192.168.192.0/255.255.255.0' identified by 'Abc12345';
grant delete on vbam.asset_project_factor to 'asset_server'@'localhost' identified by 'Abc12345';
grant delete on vbam.asset_project_factor to 'asset_server'@'127.0.0.1' identified by 'Abc12345';