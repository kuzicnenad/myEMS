USE myEMS;

SET GLOBAL time_zone = '+00:00';

/* ---------- Generate users and passwords ---------- */
INSERT INTO User(name, username, email, password)
VALUES 	('Root User', 'Root', 'root@myems.com', '$2a$12$ayPaoeYCLYSN2eW6GvxbUOoR1GnVgVtxqCCH4NzJgQYF.gB3dzgpG'), 		/* pass -> root */
		('Admin User', 'Admin1', 'admin_1@myems.com', '$2a$12$OJM5mRrY9gC61uhk5Dbtt.5xmcT0QF6loN6HnKSr9fqSWKwrqGFLC'), 	/* pass -> admin */
		('Basic User', 'User1', 'user_1@myems.com', '$2a$12$C4brIjIkdcSF0dJBvyFsjesGqq19Spy0xGbKEPXax0NfD/j6MicrG');		/* pass -> user */
        
INSERT INTO Role(name)
VALUES	('ROOT'),
		('ADMIN'),
		('USER');
        
INSERT INTO usersRoles(userId, roleId)
VALUES	(1,1),
		(1,2),
        (1,3),
        (2,2),
        (2,3),
        (3,3);


/* ---------- Generate devices ---------- */
INSERT INTO Devices(deviceName, productionDate, madeIn, activeFlag)
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

SELECT userLogin as uname,firstName as Name,lastName as Surname, u.timeStamp as userCreationDate, passwordHash, p.timeStamp as psdAddDate FROM Users as u
INNER JOIN Passwords as p
ON u.userId = p.userId;
*/

/* --- Generate procedures to create initial data --- */
CALL generateElectricityData();
CALL generateWaterData();
CALL generateGasData();

/* units yet to be fixed for all tables */
select * from electricityLiveData order by liveDataId desc;
select histDataId as id, electricityConsumption as wattHour, date from electricityHistoryData order by id desc;

/* units yet to be fixed for all tables */
select * from waterLiveData order by liveDataId desc;
select histDataId as id, waterConsumption as literDay, date from waterHistoryData order by id desc;

/* units yet to be fixed for all tables */
select * from gasLiveData order by liveDataId desc;
select histDataId as id, gasConsumption as cubeMeter, date from gasHistoryData order by id desc;


SELECT * FROM alarmData order by timeStamp desc;

SELECT * FROM Devices;


/* ------- Clear consumption tables ------- */
truncate table electricityHistoryData;
truncate table waterLiveData;
truncate table gasLiveData;

truncate table electricityLiveData;
truncate table waterHistoryData;
truncate table gasHistoryData;

/* ---------- Clear alarms table ---------- */
truncate table alarmData;

/* ---- Clear users and passwords table ----*/  
truncate table usersRoles;
truncate table Role;
truncate table User;

/* ---- Clear users and passwords table ----*/
truncate table Devices;

