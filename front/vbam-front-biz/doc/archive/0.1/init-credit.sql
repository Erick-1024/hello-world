---- 1. credit_access_rule表生成准入规则记录：

INSERT INTO `credit_access_rule` (`batch_no`, `is_check_white_customer`, `is_check_proposal_audit_result`, `cooperation_period`, `downstream_repayment_account_period`, `overdue_rate_tz`, `overdue_times_tz`, `overdue_rate_cana`, `overdue_times_cana`, `purchase_order_growth_rate`, `court_execute_company_amount`, `court_execute_company_times`, `court_execute_individual_amount`, `court_execute_individual_times`, `is_check_business_infomation`, `is_check_negative_infomation`, `create_time`, `update_time`)
VALUES
    (1, 1, 1, 26, '无账期', 0.05000, 2, 0.05000, 2, 0.10000, 3334400, 4, 3334403, 4, 1, 1, NULL, NULL);
    

---- 2. credit_white_customer_rule表生成白名单规则记录
    
INSERT INTO `credit_white_customer_rule` (`batch_no`, `produce_type`, `white_customer_number`, `enable`, `rule`, `update_time`, `create_time`)
VALUES
	(1, 'ALL', 132, 1, '{\"cooperationPeriod\":26,\"purchaseOrderGrowthRate\":0.1,\"overdueRate\":0.05,\"overdueTimes\":2}', '2016-03-02 06:14:14', '2016-02-23 05:13:03');

	
---- 3. common_rsa_key表生成真旅网和凯拿的公私钥（保证生产的和其他环境的不一样）
---- test环境

INSERT INTO `common_rsa_key` (`institution_id`, `public_key`, `private_key`)
VALUES
	('cana',
		'MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCKzr9sELAOgFwov98TEvFJD4/NYPq/kuAmgfw7EsEN+FVaMTVoMK9BfBz2PZKX74jJUJHWW8fgpQDpxfckccVvi8OVWEZxhOu1cYflZXUuPQqBb+udqLwrjNcE7Rzs9t5EhefazSQ43sgDIpSdz6vP0oqYQiIJNAyC0g/ps39InQIDAQAB',
		'MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAIrOv2wQsA6AXCi/3xMS8UkPj81g+r+S4CaB/DsSwQ34VVoxNWgwr0F8HPY9kpfviMlQkdZbx+ClAOnF9yRxxW+Lw5VYRnGE67Vxh+VldS49CoFv652ovCuM1wTtHOz23kSF59rNJDjeyAMilJ3Pq8/SiphCIgk0DILSD+mzf0idAgMBAAECgYBMor8O09vP9dBr8xsfNcsfckcLtipakKXOvN9cYgtSQjQlepuo7ZAlEHgQ/4eq+OXFeAU8mymsfo0VhGXhqiq9i+0n3S8Y+h4rfmh6hvND/JTyh7+DEazJy5LnEcPmeBR9kjHKSfAjRXg8hkenkvE/P3jLNf5BStflf5y8cy4Q5QJBANkFV4xOUf9jcDI9XAKNXuE+y14MwbPwKeNuWGksSQVD8QuZ8BYNGAXxslfMWQKtE09yrchI5lMYeL44F1l6NYMCQQCjvSVvQ4zUwj1rBAiGEDlCrCCA3XlI8U927gr8LqxyvuoEkkYDyfq8aafR4J2Ic8sWwFz9HC2lem+4991VNk9fAkEAovwrEvljBZ1ljqWca2JGxn1FeH22H/AXVXHyvhH/SRAMgLz8nWL6DsTFPsD+fE8FeJ5Uu11cdT2kuJ8hkhaBBwJANtzemSxSGMvIln3weTMgbIWOEn+i7tzkGl5iUeM1pvDvKn70dLqNh+oC8CDJx/m8d7AWuDxj0wl2O8zZXX7oEwJBAMO37in/MaURTQew9Pyh/DdwGHuxyyCpbD5HLdlCYU/CgBDRujf+mUevWSPhXE1I7DhBY0/9BH5JBU2cHc6kIPU='),
	('travelzen',
		'MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCY47ShB9oieYTRjmuLF5zOvpWcuzdyo952iqTRWWbr4AYy/CQ2nKx5+mt1L1ieyyGOT1mA8czxpb9NtdfbP8Ot1ynBmcuw1WPPaxDCr4LKkIlx9Fh9VygyzrM7G+vyCYfY3bXB/ZZe07DnFnwyUimjiF67EKlV/Nr+llHCjQXUEwIDAQAB',
		'MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAJjjtKEH2iJ5hNGOa4sXnM6+lZy7N3Kj3naKpNFZZuvgBjL8JDacrHn6a3UvWJ7LIY5PWYDxzPGlv02119s/w63XKcGZy7DVY89rEMKvgsqQiXH0WH1XKDLOszsb6/IJh9jdtcH9ll7TsOcWfDJSKaOIXrsQqVX82v6WUcKNBdQTAgMBAAECgYEAl3KgYA9eJl5HkinJIBxAyY+CtqHBmD75RGWsRHCSAj+FBIY9/RcPxzinr4o59/px//1cRN3BTPIJ4Zmq/FVaqFFghboxcZKA/P6PbnuBB/zoZlep8knve1CNu/1WnAeRzWLbGMEQNAL4rwJ0wAPKp1KdRfKlUvaXa7pht42TQKkCQQDox7zlV+ZD83GasKxFTz/UVJaBsfTfeRS0kuKA1GtY9oHoEDRQ/7QuE9qsTYCC3JlPMKH9f0u7gw9Y8LsUj9DlAkEAqCPh17aa1XG1jUn5EovD3/uybrvNx9uUAkPJexC89jFQkZXoWvwH4CJl04ncD5dSIzydgGoT/OKOBEDnGypZlwJAcV2dHCBzzxIHeKukKlkPH+xWJDGzrQX91HdYgh9xbDEZURhbMjtl167pp1JTTOf8bfmkWp2dF2QbnFgY9EmaFQJBAJsByXjWjVNtKtSAd7CTTRa2sb7IQGOZmI+l8p8TxUCqIf61VqpaYuBMldc45rkw5bY6cXErWhXuvsJJG0W9+y0CQQC3vOMhqtn7mm+VAgUNCAMqO/yVnNk7ciPjpYOETDVq7ab3dTGh1tJUSbJpv7oD+k6vNFvS7fNhNxGdVJvhcVpj');

