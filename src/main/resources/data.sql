INSERT into inventory_config (config_id,can_replace, can_return, sellable)
VALUES (1,'false','false','false');

INSERT into manufacture_details (manufacturing_id,city , pincode , state )
VALUES (1,'Bangalore','560037','Karnataka');

INSERT INTO inventory_details (item_id,available_units, base_price, country_code, created_date, item_name, manufacturer, max_discount_allowed, updated_date, config_id , manufacturing_id )
VALUES (1,20, 700, 'IN', '2021-01-29T06:43:30.484+00:00', 'PenDrive','Sandisk',40, '2021-01-29T06:43:30.484+00:00',1,1);







