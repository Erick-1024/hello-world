-- 审核规则表
CREATE TABLE `yundaex_audit_rule` (
  `batch_no` int(11) NOT NULL AUTO_INCREMENT COMMENT '批次号',
  `applyCustomer_address` varchar(45) DEFAULT NULL COMMENT '申请企业的地址',
  `cooperation_period` int(11) DEFAULT NULL COMMENT '与韵达合作年限(2年为标准)',
  `receive_send_growth_rate` decimal(8,5) DEFAULT NULL COMMENT '揽派件增长率(5%为标准)',
  `negative_infomation_search` tinyint(1) DEFAULT NULL COMMENT '负面信息搜索（1;有   0:无）',
  `is_court_execute_individual_info` tinyint(1) DEFAULT NULL COMMENT '是否有法院被执行人信息',
  `station_address` tinyint(1) DEFAULT NULL COMMENT '企业网点位置(1:市区or集镇   0:村镇)',
  `deposit_amount` tinyint(1) DEFAULT NULL COMMENT '保证金金额（1:>=0   0:<0）',
  `is_ranchise_contract` tinyint(1) DEFAULT NULL COMMENT '是否与韵达签署特许经营(加盟)合同',
  `is_qualified_inspection_record` tinyint(1) DEFAULT NULL COMMENT '年检记录是否合格',
  PRIMARY KEY (`batch_no`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- 韵达客户城市成本表
CREATE TABLE `yundaex_composite_cost` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `city_level` varchar(45) NOT NULL,
  `station_city` varchar(500) NOT NULL COMMENT '站点所在城市，多个城市有相同的成本，用中文逗号分割',
  `rental_cost` decimal(18,2) NOT NULL COMMENT '门店租金成本',
  `transport_cost` decimal(18,2) NOT NULL COMMENT '运输成本',
  `defect_cost` decimal(18,2) NOT NULL COMMENT '缺货损货成本',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- 签约状态表
CREATE TABLE `yundaex_contract_sign_situation` (
  `id` varchar(60) NOT NULL COMMENT '主键　（使用企业用户ｉｄ）',
  `station_name` varchar(255) NOT NULL COMMENT '网点客户名称',
  `sign_situation` int(3) DEFAULT NULL COMMENT '签约状态（机器码）',
  `sign_complete_time` timestamp NULL DEFAULT NULL COMMENT '完成时间',
  `protocol_no` varchar(60) DEFAULT NULL COMMENT '协议编号',
  `pay_account_name` varchar(45) DEFAULT NULL,
  `pay_account_no` varchar(100) DEFAULT NULL COMMENT '放款人账号',
  `pay_account_bank` varchar(255) DEFAULT NULL COMMENT '放款人银行地址',
  `pay_lian_hang_no` varchar(32) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `Finance_contract_sign_state` varchar(100) DEFAULT NULL COMMENT '融资合同签署状态（无，未签署，已签署）',
  `credit_contract_sign_state` varchar(100) DEFAULT NULL COMMENT '授权合同签署状态（无，未签署，已签署）',
  `duty_contract_sign_state` varchar(100) DEFAULT NULL COMMENT '放款人姓名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 转账（实体卡）记录表
CREATE TABLE `yundaex_credit_transfer` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `business_seq` varchar(32) NOT NULL COMMENT '业务流水号',
  `loan_id` varchar(32) NOT NULL COMMENT '放款ID',
  `transfer_status` varchar(8) NOT NULL DEFAULT '' COMMENT '转账状态。"SUCCESS"：转账成功；"HANDING"：转账中；"FAIL"：转账失败',
  `fee` decimal(18,0) DEFAULT NULL COMMENT '转账金额。精确到分',
  `transfer_type` varchar(32) NOT NULL COMMENT '转账类型。LOAN：放款',
  `from_account_no` varchar(19) NOT NULL COMMENT '转出账号',
  `from_account_name` varchar(255) DEFAULT NULL COMMENT '转出账户名',
  `to_account_no` varchar(19) NOT NULL COMMENT '转入账号',
  `to_account_name` varchar(255) DEFAULT NULL COMMENT '转入账户名',
  `to_account_address` varchar(255) DEFAULT NULL COMMENT '转入行地址',
  `operator_id` varchar(32) DEFAULT NULL COMMENT '操作员ID',
  `transfer_start_time` timestamp NULL DEFAULT NULL COMMENT '转账开始时间',
  `transfer_end_time` timestamp NULL DEFAULT NULL COMMENT '转账结束时间',
  `business_seq_history` varchar(1024) DEFAULT NULL COMMENT '历史流水号。失败时将原交易流水号追加在该字段后面，用”,“分割(退款给客户时失败时，用)',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `business_seq_UNIQUE` (`business_seq`),
  KEY `idx_yundaex_credit_transfer_loan_id` (`loan_id`),
  KEY `idx_yundaex_credit_transfer_transfer_status` (`transfer_status`),
  KEY `idx_yundaex_credit_transfer_type` (`transfer_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 客户申请表
CREATE TABLE `yundaex_customer_apply` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `apply_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '申请日期',
  `station_no` varchar(45) NOT NULL COMMENT '网点编号',
  `station_amount` int(11) DEFAULT NULL COMMENT '网点数量',
  `station_mgr` varchar(255) NOT NULL COMMENT '站点负责人/公司名称',
  `station_name` varchar(255) NOT NULL COMMENT '站点名称',
  `cust_name` varchar(255) NOT NULL COMMENT '借款人姓名',
  `cust_idno` varchar(18) NOT NULL COMMENT '借款人身份证号',
  `cust_phone` varchar(45) NOT NULL COMMENT '借款人手机号',
  `cust_email` varchar(45) DEFAULT NULL COMMENT '借款人邮箱',
  `province` varchar(100) NOT NULL COMMENT '站点经营地址-省',
  `city` varchar(100) NOT NULL COMMENT '站点经营地址-市',
  `address` varchar(255) NOT NULL COMMENT '站点经营地址-详细地址',
  `busi_limit` decimal(18,0) NOT NULL COMMENT '加盟年限',
  `regioncode` varchar(45) DEFAULT NULL COMMENT '区域代码',
  `apply_credit_limit` decimal(18,0) NOT NULL COMMENT '申请额度',
  `funds_use` varchar(255) DEFAULT NULL COMMENT '资金用途',
  `loan_limit` varchar(45) NOT NULL COMMENT '意向期限',
  `add_credit` varchar(45) DEFAULT NULL COMMENT '增信方式',
  `repayment_type` varchar(10) DEFAULT NULL COMMENT '4种还款方式（ 1：1个月，随时回购   2：3个月，按月支付服务费，到期回购本金   3：6个月，类等额本金方式回购   4：6个月，类等额本息方式回购）',
  `bank_account` varchar(45) DEFAULT NULL COMMENT '银行',
  `bank_account_name` varchar(45) DEFAULT NULL COMMENT '银行所在省份',
  `bank_account_address` varchar(255) DEFAULT NULL COMMENT '银行所在城市',
  `organization_no` varchar(50) DEFAULT NULL COMMENT '组织机构代码号码',
  `organization_media_id` varchar(50) DEFAULT NULL COMMENT '组织机构证件ID',
  `business_licence_no` varchar(50) DEFAULT NULL COMMENT '营业执照号码',
  `business_licence_media_id` varchar(50) DEFAULT NULL COMMENT '营业执照证件ID',
  `tax_registration_certificate_no` varchar(50) DEFAULT NULL COMMENT '税务登记证号码',
  `tax_registration_certificate_media_id` varchar(50) DEFAULT NULL COMMENT '税务登记证证件ID',
  `bail_balance` decimal(18,0) DEFAULT NULL COMMENT '保证金账户余额',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `access_manual_state` varchar(10) DEFAULT NULL COMMENT '是否通过人工审核（"WAIT"：未审核，"ACCESS"：通过人工审核，"NOTACCESS"：不通过人工审核）',
  `manual_audit_remarks` varchar(255) DEFAULT NULL COMMENT '人工审核时的备注',
  `access_automatic_state` varchar(10) DEFAULT NULL COMMENT '是否通过系统审核（"WAIT"：未审核，"ACCESS"：通过系统审核，"NOTACCESS"：不通过系统审核）',
  `automatic_audit_remarks` varchar(255) DEFAULT NULL COMMENT '系统审核备注，系统审核不通过时填写不通过内容。',
  `auditor_id` varchar(45) DEFAULT NULL COMMENT '审核人ID',
  `consistency_check` int(11) DEFAULT NULL COMMENT '一致性检查(位数从低到高依次为：工商信息、组织机构代码、营业执照、税务登记证，其他)',
  `whether_station_info` char(1) NOT NULL COMMENT '是否有网点信息（Y:有　N:没有　W:拉取信息失败）',
  `reason_W` varchar(255) DEFAULT NULL COMMENT '拉取站点信息结果',
  `automatic_audit_rule_batch_no` int(11) DEFAULT NULL COMMENT '准入规则批次号',
  `manual_audit_rule_batch_no` int(11) DEFAULT NULL COMMENT '人工审核规则批次号',
  `credit_limit_generate_state` varchar(10) DEFAULT NULL COMMENT '额度状态。人工审核前或则人工不通过无该状态，通过人工审核后进入额度待生成状态，额度生成后状态变为已生成（WAIT:待生成，FINISH:已生成）',
  `negative_network` varchar(255) DEFAULT NULL COMMENT '是否存在网络负面信息',
  `ranchise_contract_deadline` timestamp NULL DEFAULT NULL COMMENT '申请企业与韵达签署特许经营加盟合同到期日',
  `execute_individual_info` varchar(255) DEFAULT NULL COMMENT '法院被执行人信息',
  `qualified_inspection_record` varchar(45) DEFAULT NULL COMMENT '年检记录',
  `pay_account` varchar(45) DEFAULT NULL COMMENT '打款账户',
  `pay_account_name` varchar(45) DEFAULT NULL COMMENT '打款账户户名',
  `pay_account_address` varchar(255) DEFAULT NULL COMMENT '打款账户地址',
  `lian_hang_no` varchar(45) DEFAULT NULL COMMENT '开户行联行号',
  `controller` varchar(45) DEFAULT NULL COMMENT '实际控制人',
  `controller_idno` varchar(45) DEFAULT NULL COMMENT '实际控制人身份证号',
  `controller_email` varchar(45) DEFAULT NULL COMMENT '实际控制人邮箱',
  `controller_phone` varchar(45) DEFAULT NULL COMMENT '实际控制人手机号码',
  `controller_origin` varchar(45) DEFAULT NULL COMMENT '实际控制人籍贯',
  `controller_is_legal` char(1) DEFAULT NULL COMMENT '实际控制人与法人代表是否是同一人 （1：是   0：否）',
  `legal_person` varchar(45) DEFAULT NULL COMMENT '法人',
  `legal_email` varchar(45) DEFAULT NULL COMMENT '法人代表邮箱',
  `legal_phone` varchar(45) DEFAULT NULL COMMENT '法人代表手机号码',
  `account_owner` varchar(10) DEFAULT NULL COMMENT '开户人（法人：LEGAL     实际控制人：CONTROLLER   其他：OTHER   公司：COMPANY）',
  `account_owner_name` varchar(255) DEFAULT NULL COMMENT '开户人账户',
  `account_owner_email` varchar(45) DEFAULT NULL COMMENT '开户人邮箱',
  `account_owner_phone` varchar(45) DEFAULT NULL COMMENT '开户人手机号码',
  `short_loan` decimal(18,0) DEFAULT NULL COMMENT '短期借款',
  `loan_from` varchar(45) DEFAULT NULL COMMENT '借款来源',
  `agent_qualification` varchar(45) DEFAULT NULL COMMENT '代理资质',
  `legal_idno` varchar(50) DEFAULT NULL COMMENT '法人代表身份证号码',
  `legal_idno_front_media_id` varchar(60) DEFAULT NULL COMMENT '法人代表身份证正面图片Ｉｄ',
  `legal_idno_back_media_id` varchar(60) DEFAULT NULL COMMENT '法人身份证反面图片Ｉｄ',
  `addition_information_media_id` varchar(60) DEFAULT NULL COMMENT '补充资料附件ID',
  `whether_tb_order` char(1) DEFAULT NULL COMMENT '是否有天猫、淘宝订单',
  `tb_order_ratio` decimal(18,2) DEFAULT NULL COMMENT '天猫、淘宝订单占比',
  `yundaex_explain` varchar(255) DEFAULT NULL COMMENT '韵达说明',
  `other_explain` varchar(255) DEFAULT NULL COMMENT '其他说明',
  `pay_account_bank_name` varchar(45) DEFAULT NULL COMMENT '打款账户银行名称',
  `bank_province` varchar(45) DEFAULT NULL COMMENT '打款账户银行所在省份',
  `bank_city` varchar(45) DEFAULT NULL COMMENT '打款账户所在城市',
  `yundaex_judge` varchar(45) DEFAULT NULL COMMENT '韵达评价',
  `station_address` varchar(45) DEFAULT NULL COMMENT '站点地址区域 （市区   集镇   村镇）',
  `grade_state` varchar(45) DEFAULT NULL COMMENT '评级状态（WAIT   ACCESS  NOTACCESS）',
  `grade_state_remarks` varchar(45) DEFAULT NULL COMMENT '评级审核备注',
  `limit_info_remarks` varchar(500) DEFAULT NULL COMMENT '计算额度时，存放额度信息',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `stationNo_UNIQUE` (`station_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 客户评级表
CREATE TABLE `yundaex_customer_grade` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `station_no` varchar(45) NOT NULL COMMENT '网点编号',
  `points` decimal(5,2) DEFAULT NULL COMMENT '评级分数',
  `grade` varchar(45) DEFAULT NULL COMMENT '评级等级 ',
  `beta` decimal(5,2) DEFAULT NULL COMMENT '利率定价',
  `raito` decimal(5,2) DEFAULT NULL COMMENT '系数值',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

-- 评级配置表
CREATE TABLE `yundaex_grade_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '序号 主键',
  `min_points` decimal(5,2) DEFAULT NULL COMMENT '最小分值',
  `max_points` decimal(5,2) DEFAULT NULL COMMENT '最大分值',
  `grade` varchar(45) DEFAULT NULL COMMENT '等级',
  `beta` decimal(5,2) DEFAULT NULL COMMENT '利率定价',
  `ratio` decimal(5,2) DEFAULT NULL COMMENT '系数值',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- 评级分数配置表
CREATE TABLE `yundaex_grade_model` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `first_target` varchar(45) NOT NULL COMMENT '一级指标',
  `second_target` varchar(45) NOT NULL COMMENT '二级指标',
  `weight` varchar(45) NOT NULL COMMENT '权重×10',
  `details` varchar(500) NOT NULL COMMENT '详情',
  `type` varchar(45) NOT NULL COMMENT '类型，决定该二级指标获取分数的方法',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- 客户利率
CREATE TABLE `yundaex_interest_rate` (
  `id` varchar(60) NOT NULL,
  `repayment_method` varchar(100) DEFAULT NULL COMMENT '还款方式',
  `interest_rate` decimal(8,5) DEFAULT NULL COMMENT '利率',
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL,
  `customer_id` varchar(60) NOT NULL COMMENT '客户id',
  `rate_unit` varchar(100) DEFAULT NULL COMMENT '利率单位',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 放款流水表
CREATE TABLE `yundaex_loan_info_record` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `loan_no` varchar(100) DEFAULT NULL COMMENT '放款编号',
  `business_mode` varchar(50) NOT NULL COMMENT '业务模式（保理商+融资商等）',
  `input_method` varchar(50) NOT NULL COMMENT '录入方式（excal、手动）',
  `business_contract_no` varchar(50) DEFAULT NULL COMMENT '业务合同号',
  `factor_id` varchar(32) DEFAULT NULL COMMENT '保理商id',
  `factor_company` varchar(255) NOT NULL COMMENT '保理商公司名称（列表显示用）',
  `finance_id` varchar(32) DEFAULT NULL COMMENT '融资客户Id',
  `finance_company` varchar(255) NOT NULL COMMENT '融资客户公司名称',
  `core_company_id` varchar(32) DEFAULT NULL COMMENT '核心企业id',
  `core_company_name` varchar(255) DEFAULT NULL COMMENT '核心企业名称',
  `voucher_no` varchar(255) DEFAULT NULL COMMENT '凭证号码',
  `currency` varchar(50) DEFAULT NULL COMMENT '币种 ',
  `business_product` varchar(255) DEFAULT NULL COMMENT '业务产品名称',
  `business_product_id` varchar(32) DEFAULT NULL COMMENT '业务产品id',
  `receivables_amount` decimal(18,0) DEFAULT '0' COMMENT '应收账款金额',
  `receivables_balance` decimal(18,0) DEFAULT '0' COMMENT '应收账款余额',
  `finance_amount` decimal(18,0) DEFAULT '0' COMMENT '融资金额',
  `finance_balance` decimal(18,0) DEFAULT '0' COMMENT '融资余额',
  `interest_rate` decimal(7,5) DEFAULT '0.00000' COMMENT '利率',
  `account_supervision_id` varchar(32) DEFAULT NULL COMMENT '当前监管关系Id，对应account_supervision表',
  `account_no` varchar(50) DEFAULT NULL COMMENT '账号',
  `loan_date` varchar(10) DEFAULT NULL COMMENT '放款日',
  `due_date` varchar(10) DEFAULT NULL COMMENT '到期日',
  `repayment_method` varchar(255) DEFAULT NULL COMMENT '还本付息方式',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `upate_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `loan_period` varchar(45) DEFAULT NULL COMMENT '放款期限',
  `loan_period_unit` varchar(45) DEFAULT NULL COMMENT '放款期限单位',
  `repayment_period` int(10) DEFAULT NULL COMMENT '还款期数',
  `settle_status` varchar(50) DEFAULT NULL COMMENT '结清状态（已结清、未结清）',
  `interest_rate_unit` varchar(100) DEFAULT NULL COMMENT '利率单位',
  `change_type` varchar(50) DEFAULT NULL COMMENT '变更类型',
  `business_seq` varchar(32) DEFAULT NULL COMMENT '业务流水号',
  PRIMARY KEY (`id`),
  UNIQUE KEY `LOAN_INFO` (`loan_no`,`factor_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 外部客户平台对应表
CREATE TABLE `yundaex_out_customer` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `station_no` varchar(45) NOT NULL COMMENT '站点编号',
  `member_id` varchar(45) NOT NULL COMMENT 'Cana用户的ID',
  `institution_id` varchar(45) NOT NULL COMMENT '机构',
  `station_name` varchar(255) NOT NULL COMMENT '站点名称',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=utf8;

-- 韵达个人信息
CREATE TABLE `yundaex_personal_info` (
  `id` varchar(60) NOT NULL COMMENT '主键Ｉｄ',
  `real_name` varchar(100) NOT NULL COMMENT '真实姓名',
  `mail` varchar(50) NOT NULL COMMENT '邮箱地址',
  `cellphone` varchar(20) DEFAULT NULL COMMENT '手机号码',
  `resident_identity_card_no` varchar(30) DEFAULT NULL COMMENT '居民身份证号码',
  `resident_identity_card_front_media_id` varchar(60) DEFAULT NULL COMMENT '居民身份证正面图片Ｉｄ',
  `resident_identity_card_back_media_id` varchar(60) DEFAULT NULL COMMENT '居民身份证反面图片Ｉｄ',
  `type` varchar(100) DEFAULT NULL COMMENT '类型（法人代表、实际控制人）',
  `audit_state` varchar(100) DEFAULT NULL COMMENT '状态（待审核、审核通过）',
  `audit_apply_time` timestamp NULL DEFAULT NULL COMMENT '审核申请时间',
  `security_code` varchar(100) DEFAULT NULL COMMENT '安全码',
  `security_code_expiration_time` timestamp NULL DEFAULT NULL COMMENT '安全码过期时间',
  `cert_subject_dn` varchar(100) DEFAULT NULL COMMENT '个人数字证书ｄｎ',
  `related_customer_id` varchar(60) DEFAULT NULL COMMENT '关联的企业用户Ｉｄ',
  `station_name` varchar(255) DEFAULT NULL COMMENT '网点名称',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `auditor_id` varchar(60) DEFAULT NULL,
  `auditor_name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 韵达网点经营表
CREATE TABLE `yundaex_station_operation` (
  `id` int(11) NOT NULL COMMENT '主键\n',
  `cost_manual` decimal(5,2) NOT NULL COMMENT '人工成本（每单）',
  `cost_receipt` decimal(5,2) NOT NULL COMMENT '面单成本（每单）',
  `cost_package` decimal(5,2) NOT NULL COMMENT '包装平均成本（每单）',
  `transit_fee` decimal(5,2) NOT NULL COMMENT '中转费（每单）',
  `other_materials_cost_rate` decimal(8,2) NOT NULL COMMENT '其他物料成本占比（揽件）',
  `opposite_station_send_cost` decimal(5,2) NOT NULL COMMENT '对方网点派件成本（每单）',
  `average_profit` decimal(5,2) NOT NULL COMMENT '平均单笔揽件利润',
  `send_income` decimal(5,2) NOT NULL COMMENT '派件收入（每单）',
  `courier_fee` decimal(5,2) NOT NULL COMMENT '快递员费用（每单）',
  `other_materials_cost_rate_send` decimal(5,2) NOT NULL COMMENT '其他物料成本占比（派件）',
  `transit_fee_bail` decimal(5,2) NOT NULL COMMENT '保证金 中转费（每单）',
  `other_fee_bail` decimal(5,2) NOT NULL COMMENT '保证金 其他费用（每单）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 网点数据表
CREATE TABLE `yundaex_tstation_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键。记录ID。唯一。',
  `station_no` varchar(50) NOT NULL COMMENT '网点id，唯一',
  `statmonth` varchar(10) NOT NULL COMMENT '统计年月，yyyy-MM',
  `receive_total` int(11) NOT NULL COMMENT '揽件总数',
  `avg_receive_num` decimal(15,3) NOT NULL COMMENT '日平均揽件数',
  `receive_sum_signed` int(11) NOT NULL COMMENT '已签收总数（揽件）',
  `receive_sum_unsign` int(11) NOT NULL COMMENT '未签收总数（揽件）',
  `send_total` int(11) NOT NULL COMMENT '派件总数',
  `avg_send_num` decimal(15,3) NOT NULL COMMENT '日平均到件数',
  `send_sum_signed` int(11) NOT NULL COMMENT '已签收总数（派件）',
  `send_sum_unsign` int(11) NOT NULL COMMENT '未签收总数（派件）',
  `rec_send_dif` int(11) NOT NULL COMMENT '揽件派件月差额',
  `rec_send_ratio` decimal(15,3) NOT NULL COMMENT '揽派比',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `idx_yundaex_customer_id` (`station_no`),
  KEY `idx_yundaex_statmonth` (`statmonth`)
) ENGINE=InnoDB AUTO_INCREMENT=3037 DEFAULT CHARSET=utf8;

-- 地区编码表
CREATE TABLE `common_area_code` (
  `area_code` varchar(45) NOT NULL,
  `province` varchar(45) DEFAULT NULL,
  `city` varchar(45) DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`area_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;