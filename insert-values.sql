USE myEMS;

SET GLOBAL time_zone = '+00:00';

/* ---------- Generate users and passwords ---------- */
INSERT INTO Users (user_login, first_name, last_name, job_title)
VALUES 	('Admin','Admin_FirstName','Admin_LastName','1'),
		('User1','User1_FirstName','User1_LastName','2'),
		('User1','User1_FirstName','User1_LastName','3')
;

INSERT INTO User(name, username, email, password)
VALUES 	('Admin_1', 'Admin_1', 'admin_1@myems.com', '$2a$12$OJM5mRrY9gC61uhk5Dbtt.5xmcT0QF6loN6HnKSr9fqSWKwrqGFLC'), 	/* pass -> admin */
		('User_1', 'User_1', 'user_1@myems.com', '$2a$12$C4brIjIkdcSF0dJBvyFsjesGqq19Spy0xGbKEPXax0NfD/j6MicrG');		/* pass -> user */
        
INSERT INTO Role(name)
VALUES	('ADMIN'),
		('USER');
        
INSERT INTO users_roles
VALUES	(1,1),
		(1,2),
		(2,2);
        
DROP TABLE IF EXISTS Users_Roles;
DROP TABLE IF EXISTS User;
DROP TABLE IF EXISTS Role;


CALL addHashPassword(1,'admin111');
CALL addHashPassword(2,'user111');
CALL addHashPassword(3,'user222');




SELECT user_login as uname,first_name as Name,last_name as Surname, u.time_stamp as userCreationDate, password_hash, p.time_stamp as psdAddDate FROM Users as u
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


