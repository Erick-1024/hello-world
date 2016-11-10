---- 1. member-user表生成管理员记录(角色id请根据实际情况而定,配置文件member-common-global.properties中有)：
INSERT INTO `vbam`.`member_user` 
(`id`,`master_id`,`username`,`password`,`company_name`,`organization_code`,`organization_code_certificate_media_id`,`business_licence_code`,
`business_licence_media_id`,`contact_name`,`contact_Tel`,`contact_mail`,`user_type`,`user_status`,`job_title`,
`role_id`,`token`,`signedIn`,`signIn_time`,`signIn_ip`,`create_time`,`upate_time`,`audit_time`,`security_code`,
`security_code_expiration_time`,`agent_company`,`contact_identity_card_front_media_id`,`contact_identity_card_back_media_id`,
`legal_person_identity_card_front_media_id`,`legal_person_identity_card_back_media_id`,`tax_registration_certificate_code`,`tax_registration_certificate_media_id`,
`job_no`,`real_name`,`pay_password`,`guide_status`,`employee_tel`,`employee_mail`,`employee_job_title`)
VALUES
('cana-user',NULL,'cana-admin','91ae55b7955b5d79dad980d111f6822eddbaba51d819ebdbf375dda0269374e0',
'CANA金融','111111','563d9eaf62b8193f6527e50d','111111','563d9eaf62b8193f6527e50c','CANA','11111111',
'11111111','CANA','ACTIVATED','111111','000',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,
NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);

---- 2. member-audit表生成审核记录(与用户配置使用)：
INSERT INTO `vbam`.`member_audit`
(`id`,`auditor_id`,`audit_message`,`create_time`,`update_time`,`audit_status`,`customer_id`)
VALUES('cana-audit','cana-user',NULL,NULL,NULL,63,'cana-user');



---- 3. member-user表生成凯拿保理记录(角色id请根据实际情况而定)：
INSERT INTO `vbam`.`member_user` 
(`id`,`master_id`,`username`,`password`,`company_name`,`organization_code`,`organization_code_certificate_media_id`,`business_licence_code`,
`business_licence_media_id`,`contact_name`,`contact_Tel`,`contact_mail`,`user_type`,`user_status`,`job_title`,
`role_id`,`token`,`signedIn`,`signIn_time`,`signIn_ip`,`create_time`,`upate_time`,`audit_time`,`security_code`,
`security_code_expiration_time`,`agent_company`,`contact_identity_card_front_media_id`,`contact_identity_card_back_media_id`,
`legal_person_identity_card_front_media_id`,`legal_person_identity_card_back_media_id`,`tax_registration_certificate_code`,`tax_registration_certificate_media_id`,
`job_no`,`real_name`,`pay_password`,`guide_status`,`employee_tel`,`employee_mail`,`employee_job_title`)
VALUES
('cana-baoli',NULL,'cana-baoli','91ae55b7955b5d79dad980d111f6822eddbaba51d819ebdbf375dda0269374e0',
'上海凯拿资产管理有限公司','111111','563d9eaf62b8193f6527e50d','111111','563d9eaf62b8193f6527e50c','CANA','11111111',
'11111111','FACTOR','ACTIVATED','111111','000',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,
NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);

---- 4. member-audit表生成审核记录(与用户配置使用)：
INSERT INTO `vbam`.`member_audit`
(`id`,`auditor_id`,`audit_message`,`create_time`,`update_time`,`audit_status`,`customer_id`)
VALUES('cana-baoli','cana-user',NULL,NULL,NULL,63,'cana-baoli');




---- 5. member-user表生成真旅网记录(角色id请根据实际情况而定)：
INSERT INTO `vbam`.`member_user` 
(`id`,`master_id`,`username`,`password`,`company_name`,`organization_code`,`organization_code_certificate_media_id`,`business_licence_code`,
`business_licence_media_id`,`contact_name`,`contact_Tel`,`contact_mail`,`user_type`,`user_status`,`job_title`,
`role_id`,`token`,`signedIn`,`signIn_time`,`signIn_ip`,`create_time`,`upate_time`,`audit_time`,`security_code`,
`security_code_expiration_time`,`agent_company`,`contact_identity_card_front_media_id`,`contact_identity_card_back_media_id`,
`legal_person_identity_card_front_media_id`,`legal_person_identity_card_back_media_id`,`tax_registration_certificate_code`,`tax_registration_certificate_media_id`,
`job_no`,`real_name`,`pay_password`,`guide_status`,`employee_tel`,`employee_mail`,`employee_job_title`)
VALUES
('201603280001',NULL,'travelzen','91ae55b7955b5d79dad980d111f6822eddbaba51d819ebdbf375dda0269374e0',
'真旅网计算机软件开发（上海）有限公司','111111','563d9eaf62b8193f6527e50d','111111','563d9eaf62b8193f6527e50c','真旅网','11111111',
'11111111','FINACE','ACTIVATED','111111','000',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,
NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);

---- 6. member-audit表生成审核记录(与用户配置使用)：
INSERT INTO `vbam`.`member_audit`
(`id`,`auditor_id`,`audit_message`,`create_time`,`update_time`,`audit_status`,`customer_id`)
VALUES('160328000000101','cana-user',NULL,NULL,NULL,63,'201603280001');