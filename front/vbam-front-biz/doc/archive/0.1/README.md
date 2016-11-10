### 首次上线初始化工作：

##### 上传模版文件

1. 使用MediaTest2EnvTemplateUpload.java将模版文件上传至生产环境

##### 数据库相关：

1. 检查sql脚本内容并执行
1. 使用cana-admin用户在页面完成增加角色：融资商、保理商、真旅项目融资商、核心企业
1. 修改member_user表，将“真旅网计算机软件开发（上海）有限公司”的角色ID字段改成核心企业角色的ID值
	update member_user set role_id = '201603311324330403' where id = '201603280001';
	
1. 修改member_user表，将cana-baoli的角色ID字段改成保理商角色的ID值
	update member_user set role_id = '201603311341130404' where id = 'cana-baoli';
	
1. 使用cana-baoli用户名登录系统，并完成引导页
1. 修改产品表，finaceRoleId改为真旅项目融资商角色的ID值、factorAccountNo改为凯拿实际引导后开立的银行账户

update repayment_product set detail = '{"accountNo":"3110210010051082423","accountName":"真旅网计算机软件开发（上海）有限公司","loanPeriodUnit":"MONTH","loanPeriod":1,"interestRateUnit":"DAY","interestRate":0.0005,"finaceRoleId":"201603311357550406","factorId":"cana-baoli","factorName":"上海凯拿资产管理有限公司","factorAccountNo":"4244421072739933846","institutionName":"真旅网"}'
 where id = 'travelzen_finance';

1. 导入真旅网的客票数据和还款数据到travelzen数据库，之后再启动flight-finance-scheduler定时器应用

##### 配置文件相关

1. 连接mysql、mongo、redis、rabbit等配置文件
1. 在创建完角色之后，将 member-common-global.properties 文件中的 finace.default.role.id 修改为对应环境的融资商角色ID
	201603311346520405