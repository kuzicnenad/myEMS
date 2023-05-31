USE myEMS;

INSERT INTO Users (user_login, first_name, last_name, job_title)
VALUES 	('Admin','Admin_FirstName','Admin_LastName','1'),
		('User1','User1_FirstName','User1_LastName','2'),
		('User1','User1_FirstName','User1_LastName','3')
;

CALL addHashPassword(1,'admin111');
CALL addHashPassword(2,'user111');
CALL addHashPassword(3,'user222');


SELECT user_login as uname,first_name as Name,last_name Surname, u.time_stamp as userCreationDate, password_hash, p.time_stamp as psdAddDate FROM Users as u
INNER JOIN Passwords as p
ON u.user_id = p.user_id;

CALL generateElectricityData(5000);
CALL generateWaterData(5000);
CALL generateGasData(5000);

SET GLOBAL time_zone = '+00:00';

/* units yet to be fixed for all tables */
select * from Electricity_Live_Data;
select hist_data_id as id, electricity_consumption as wattHour, date from Electricity_History_Data;

/* units yet to be fixed for all tables */
select * from Water_Live_Data;
select hist_data_id as id, water_consumption as literDay, date from Water_History_Data;

/* units yet to be fixed for all tables */
select * from Gas_Live_Data;
select hist_data_id as id, gas_consumption as cubeMeter, date from Gas_History_Data;

DELETE FROM Electricity_Live_Data WHERE live_data_id > 0;

/* ------- Clear consumption tables ------- */
truncate table Electricity_History_Data;
truncate table Water_Live_Data;
truncate table Gas_Live_Data;

truncate table Electricity_Live_Data;
truncate table Water_Live_Data;
truncate table Gas_Live_Data;

/* --------- Alarms data tables --------- */
SELECT * FROM Alarm_Data order by time_stamp desc;

truncate table Alarm_Data;


