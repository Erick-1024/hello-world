-- op3
grant select,insert,update,execute on vbam.* to 'yundaex_server'@'192.168.1.0/255.255.255.0' identified by 'Abc12345';
grant select,insert,update,execute on vbam.* to 'yundaex_server'@'localhost' identified by 'Abc12345';
grant select,insert,update,execute on vbam.* to 'yundaex_server'@'127.0.0.1' identified by 'Abc12345';

grant select,insert,update,execute on vbam.* to 'yundaex_schd'@'192.168.1.0/255.255.255.0' identified by 'Abc12345';
grant select,insert,update,execute on vbam.* to 'yundaex_schd'@'localhost' identified by 'Abc12345';
grant select,insert,update,execute on vbam.* to 'yundaex_schd'@'127.0.0.1' identified by 'Abc12345';


-- prod
grant select,insert,update,execute on vbam.* to 'yundaex_server'@'192.168.192.0/255.255.255.0' identified by 'Abc12345';
grant select,insert,update,execute on vbam.* to 'yundaex_server'@'localhost' identified by 'Abc12345';
grant select,insert,update,execute on vbam.* to 'yundaex_server'@'127.0.0.1' identified by 'Abc12345';

grant select,insert,update,execute on vbam.* to 'yundaex_schd'@'192.168.192.0/255.255.255.0' identified by 'Abc12345';
grant select,insert,update,execute on vbam.* to 'yundaex_schd'@'localhost' identified by 'Abc12345';
grant select,insert,update,execute on vbam.* to 'yundaex_schd'@'127.0.0.1' identified by 'Abc12345';