USE myEMS;

SET GLOBAL time_zone = '+00:00';

/* ---------- Generate users and passwords ---------- */
INSERT INTO Users (user_login, first_name, last_name, job_title)
VALUES 	('Admin','Admin_FirstName','Admin_LastName','1'),
		('User1','User1_FirstName','User1_LastName','2'),
		('User1','User1_FirstName','User1_LastName','3')
;

INSERT INTO User(name, username, email, password)
VALUES 	('Root', 'Root', 'root@myems.com', '$2a$12$ayPaoeYCLYSN2eW6GvxbUOoR1GnVgVtxqCCH4NzJgQYF.gB3dzgpG'), 		/* pass -> root */
		('Admin1', 'Admin1', 'admin_1@myems.com', '$2a$12$OJM5mRrY9gC61uhk5Dbtt.5xmcT0QF6loN6HnKSr9fqSWKwrqGFLC'), 	/* pass -> admin */
		('User1', 'User1', 'user_1@myems.com', '$2a$12$C4brIjIkdcSF0dJBvyFsjesGqq19Spy0xGbKEPXax0NfD/j6MicrG');		/* pass -> user */
        
INSERT INTO Role(name)
VALUES	('ROOT'),
		('ADMIN'),
		('USER');
        
INSERT INTO users_roles(user_id, role_id)
VALUES	(1,1),
		(1,2),
        (1,3),
        (2,2),
        (2,3),
        (3,3);


/* ---------- Generate devices ---------- */
INSERT INTO Devices(device_name, production_date, made_in, active_flag)
VALUES	('device_one','2017-11-20','Serbia', 1),
		('device_two','2014-09-22','Germany', 0),
		('device_three','2022-05-11','Germany', 1),
		('device_four','2021-12-10','Serbia', 0),
		('device_five','2022-07-05','Croatia', 1),
		('device_six','2009-04-09','Slovenia', 1);

/* This part is invalid, logics were improved from this.
CALL addHashPassword(1,'admin111');
CALL addHashPassword(2,'user111');
CALL addHashPassword(3,'user222');

SELECT user_login as uname,first_name as Name,last_name as Surname, u.time_stamp as userCreationDate, password_hash, p.time_stamp as psdAddDate FROM Users as u
INNER JOIN Passwords as p
ON u.user_id = p.user_id;
*/

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

SELECT * FROM Devices;


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
truncate table users_roles;
truncate table Role;
truncate table User;

/* ---- Clear users and passwords table ----*/
truncate table Devices;

