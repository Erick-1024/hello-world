
1. 真旅客户授信额度计算公司天数改成了5（唐依红），需上线credit-scheduler
	并增加配置文件：global/properties/credit.properties
	limit_param_dayN=5

1. 放款列表接口增加放款编号字段（唐依红，水哥），需上线credit-server,credit-openapi,repayment-server
1. 真旅合同模版修改，上线biz
1. 自动账扣修改，上线account-server
1. 还款方式页面，展示真旅融资商还款账号信息，上线credit-openapi,vbam-front-biz,asset-server

1. 通知真旅审核结果和额度生效接口地址的配置文件