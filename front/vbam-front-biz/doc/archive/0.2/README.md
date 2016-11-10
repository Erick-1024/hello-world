### 第二次上线说明：

- 真旅客户额度申请增加企业三证号码字段
- 新增asset-server项目，将原来的contract和supervision_account移动到了此项目中
- 产品表增加coreCompanyId和coreCompanyName字段，需要修改数据库
- 增加促销活动表，需要建立：数据库用户，marketing-server/properties/mysql-jdbc.properties 文件