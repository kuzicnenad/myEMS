USE myEMS;

SET GLOBAL time_zone = '+00:00';

/* ---------- Generate users and passwords ---------- */
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


/* --- Generate procedures to create initial data --- */
CALL generateElectricityData();
CALL generateWaterData();
CALL generateGasData();

/* units yet to be fixed for all tables */
select * from Electricity_Live_Data order by live_data_id desc;
select hist_data_id as id, electricity_consumption as wattHour, date from Electricity_History_Data order by id desc;

/* units yet to be fixed for all tables */
select * from Water_Live_Data order by live_data_id desc;
select hist_data_id as id, water_consumption as literDay, date from Water_History_Data order by id desc;

/* units yet to be fixed for all tables */
select * from Gas_Live_Data order by live_data_id desc;
select hist_data_id as id, gas_consumption as cubeMeter, date from Gas_History_Data order by id desc;


SELECT * FROM Alarm_Data order by time_stamp desc;

/* ------- Clear consumption tables ------- */
truncate table Electricity_History_Data;
truncate table Water_Live_Data;
truncate table Gas_Live_Data;

truncate table Electricity_Live_Data;
truncate table Water_History_Data;
truncate table Gas_History_Data;

/* ---------- Clear alarms table ---------- */
truncate table Alarm_Data;

/* ---- Clear users and passwords table ----*/
truncate table passwords;
truncate table users;


