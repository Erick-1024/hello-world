insert into vbam.common_sequence_center values('bankgate_trans_id',0);
create table vbam.common_bankgate_trans(
  id varchar(32) primary key not null     comment  '主键' ,
  origin_id varchar(32)                   comment  '原流水记录ID，关联操作，Ex：解冻和冻结相关联',
  trans_date char(8) not null             comment  '交易发起时间，业务端',
  trans_type varchar(50) not null         comment  '交易类型，BangateServer',
  business_type varchar(50) not null      comment  '业务类型，业务端.业务类型必须明确给出.Ex：在BangateServer，转账、解冻、解冻支付、冻结、支付冻结都是强制转账的交易类>型',
  business_seq varchar(50) not null       comment  '业务流水号，业务端',
  gate_seq varchar(50) not null           comment  '网关流水号，BangateServer',
  bank_seq varchar(50)                    comment  '银行流水号，银行返回，可能没有',
  amount decimal(18,0)                    comment  '交易金额，分为单位',
  bank_check_date char(8)                 comment  '银行对帐时间',
  status varchar(32) not null             comment  '交易状态',
  bank_user_name not null                 comment  '银行主账号用户名',
  main_account_no not null                comment  '银行主账号',
  account_no varchar(19)                  comment  '业务发起帐号，发起该交易的帐号',
  receive_account_no varchar(19)          comment  '第三方帐号，Ex：对应于发起帐号，Ex:转账时的收款帐号',
  create_time timestamp not null          comment  '流水创建时间',
  update_time timestamp not null default current_timestamp on update current_timestamp comment  '最后一次更新该记录的时间，同一条记录因为状态变更可能会多次更新'
)
alter table vbam.common_bankgate_trans add index gate_seq_index(gate_seq);
alter table vbam.common_bankgate_trans add index business_unite_index(business_seq,business_type);
alter table vbam.common_bankgate_trans add index origin_id_index(origin_id);