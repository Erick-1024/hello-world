- 依次执行create_table.sql,init-yundaex-db.sql,init-common-area-code.sql
- 发布服务：vbam-front-biz,account-server,asset-server,yundaex-openapi,yundaex-server,yundaex-scheduler,signature-server,member-server
- 发布脚本增加韵达三个应用
- nginx 配置韵达openapi地址

- 配置文件修改

1. 新增 yundaex-server/properties/mysql-jdbc.properties
jdbc.username=yundaex_server
jdbc.password=Abc12345
jdbc.initialPoolSize=0
jdbc.minPoolSize=0
jdbc.maxPoolSize=20
jdbc.dataSourceName=yundaex-server-dataSource

2. 新增 yundaex-scheduler/properties/mysql-jdbc.properties
jdbc.username=yundaex_schd
jdbc.password=Abc12345
jdbc.initialPoolSize=0
jdbc.minPoolSize=0
jdbc.maxPoolSize=20
jdbc.dataSourceName=yundaex-scheduler-dataSource

3. OP3 新增 global/properties/yundaex-url.properties
yundaex.station.url.prefix=http://116.228.72.130:15141/ydbss/call/cana/supplyInfo.do
yundaex.audit.result.url.prefix=http://116.228.72.130:15141/ydbss/call/cana/recApplyInfo.do
yundaex.loan.result.url.prefix=http://116.228.72.130:15141/ydbss/call/cana/reciveApplyGetInfo.do
yundaex.loan.repayment.url.prefix=http://116.228.72.130:15141/ydbss/call/cana/reciveApplyPayInfo.do

3. PROD 新增 global/properties/yundaex-url.properties
#yundaex.station.url.prefix=http://116.228.72.130:15141/ydbss/call/cana/supplyInfo.do
#yundaex.audit.result.url.prefix=http://116.228.72.130:15141/ydbss/call/cana/recApplyInfo.do
#yundaex.loan.result.url.prefix=http://116.228.72.130:15141/ydbss/call/cana/reciveApplyGetInfo.do
#yundaex.loan.repayment.url.prefix=http://116.228.72.130:15141/ydbss/call/cana/reciveApplyPayInfo.do

4. 新增 global/properties/yundaex.properties
yundaex.assest.project.id=yundaex_project_id

# 国内保理合同－网点贷
# 编号前缀
serial.number.prefix=CN
# 保理融资比例不超过
factor.financing.radio=80
# 融资额度最大值/元
finance.credit.max=200万
# 额度有效期/月
credit.due.period=24
# 融资利率
finance.radio=0.03%日-0.05%/日
# 保理费率
factor.radio=0%/次-2%/次
# 合同有效期/年
contract.due.period=2

#利率
base.rate=0.21
risk.float.ratio=0.09
customer.grade.weight=0.2





