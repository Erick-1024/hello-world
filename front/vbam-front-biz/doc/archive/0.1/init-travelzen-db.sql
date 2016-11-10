
---- 在导入完真旅客票数据之后，查询出最大的客票记录ID

select record_id from flight_ticket order by record_id desc limit 0,1

---- 将最大的客票记录ID插入到 common_properties 表

INSERT INTO `common_properties` (`name`, `value`, `create_time`, `update_time`)
VALUES
	('travelzen_ticket_last_record_id', 'TKT20160229235909379334', '2016-03-31 20:00:00', '2016-03-31 20:00:00');
