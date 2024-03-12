DROP DATABASE IF EXISTS myEMS;
CREATE DATABASE myEMS;
USE myEMS;
	
drop table if exists usersRoles;
drop table if exists user;
drop table if exists role;

CREATE TABLE User (
	id BIGINT NOT NULL auto_increment,
    name VARCHAR(50) NOT NULL,
    username VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    password VARCHAR(255) NOT NULL,
    creationDate DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
    lastUpdate DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    activeFlag BOOL NOT NULL DEFAULT 1,
    PRIMARY KEY CLUSTERED (id ASC),
    UNIQUE KEY (email)
);

CREATE TABLE Role(
	id BIGINT NOT NULL auto_increment,
    name VARCHAR(50) NOT NULL,
    PRIMARY KEY CLUSTERED (id ASC)
);

CREATE TABLE usersRoles (
	id BIGINT NOT NULL auto_increment,
	userId BIGINT NOT NULL,
	roleId BIGINT NOT NULL,
	FOREIGN KEY (roleId) REFERENCES Role (id) ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY (userId) REFERENCES User (id) ON DELETE CASCADE ON UPDATE CASCADE,
    PRIMARY KEY (id)
);

/* Electricity (Electricity KWh), Water(t/h), Gas(t/h, total(t). preassure(KPa), Temperature(C) ---> table updated on consumption trigger or timing, keep records up to 48hours
	Electricity consumption is updated every 60min,
    Water consumption when water meter detecs flow and preasure but at least onces every 60min,
    Gas consumption when gas meter detecs flow and preasure but at least onces every 60min
*/
CREATE TABLE ElectricityLiveData(
	liveDataId INT NOT NULL AUTO_INCREMENT,
    consumption FLOAT,
    faultDetected INT,
    startTime  DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
    endTime DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (liveDataId)
); 

CREATE TABLE WaterLiveData(
	liveDataId INT NOT NULL AUTO_INCREMENT,
    consumption FLOAT,
    faultDetected INT,
    startTime  DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
    endTime DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY (liveDataId)
); 

CREATE TABLE GasLiveData(
	liveDataId INT NOT NULL AUTO_INCREMENT,
    consumption FLOAT,
    faultDetected INT,
    startTime  DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
    endTime DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY (liveDataId)
); 

/* Analysis and Comparison, select date to show ---> table updated every every day at set time (00:00 default)*/
CREATE TABLE ElectricityHistoryData(
	histDataId INT NOT NULL AUTO_INCREMENT,
    electricityConsumption FLOAT,
    date DATE,
    PRIMARY KEY (histDataId)
);
CREATE TABLE WaterHistoryData(
	histDataId INT NOT NULL AUTO_INCREMENT,
    waterConsumption FLOAT,
    date DATE,
    PRIMARY KEY (histDataId)
);
CREATE TABLE GasHistoryData(
	histDataId INT NOT NULL AUTO_INCREMENT,
    gasConsumption FLOAT,
    date DATE,
    PRIMARY KEY (histDataId)
);

/* Alarm messages can be sent to email*/
CREATE TABLE alarmData(
	alarmId INT NOT NULL AUTO_INCREMENT,
    alarmCode INT NOT NULL,
    alarmDesc VARCHAR(255),
    timeStamp  DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
    acknowledgedTime DATETIME,
    ackFlag BOOL NOT NULL DEFAULT 0,
    PRIMARY KEY (alarmId)
);


/* Devices in system */
CREATE TABLE Devices(
	deviceId INT NOT NULL AUTO_INCREMENT,
    deviceName VARCHAR (100) NOT NULL,
    description VARCHAR(255),
    productionDate DATE NOT NULL,
    madeIn VARCHAR (50) NOT NULL,
    lastUpdate DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    activeFlag BOOL NOT NULL DEFAULT 0,
    PRIMARY KEY (deviceId)
);

drop table if exists passwords;
drop table if exists users_roles;
drop table if exists user;
drop table if exists role;
drop table if exists alarmData;

/* App Menu -> Home, Live Data, History Data, Print reports, Settings*/

select consumption, date_format(endTime,'%H:%i') as timestamp from electricity_live_data ORDER BY liveDataId DESC LIMIT 5;












