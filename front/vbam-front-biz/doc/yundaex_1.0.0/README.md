1.在global/properties/yundaex.properties：
 增加roleID:yundaex.finace.id=201600000000000003
 增加授信最大额度500000元：yundaex.max.credit.limit=500000
 修改:
 	base.rate=0.22
	risk.float.ratio=0.08
	customer.grade.weight=0.5 

2.执行yundaex-add.sql

3.发布服务：vbam-front-biz,account-server,asset-server,yundaex-openapi,yundaex-server,yundaex-scheduler,signature-server,member-server