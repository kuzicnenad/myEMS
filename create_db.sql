DROP DATABASE IF EXISTS myEMS;
CREATE DATABASE myEMS;
USE myEMS;
	
CREATE TABLE User (
	id BIGINT NOT NULL auto_increment,
    name VARCHAR(50) NOT NULL,
    username VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    password VARCHAR(255) NOT NULL,
    creation_date DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
    last_update DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id ASC),
    UNIQUE KEY (email)
);

CREATE TABLE Role(
	id BIGINT NOT NULL auto_increment,
    name VARCHAR(50) NOT NULL,
    PRIMARY KEY CLUSTERED (id ASC)
);

CREATE TABLE users_roles (
  user_id BIGINT NOT NULL,
  role_id BIGINT NOT NULL,
  FOREIGN KEY (role_id) REFERENCES Role (id) ON DELETE CASCADE,
  FOREIGN KEY (user_id) REFERENCES User (id) ON DELETE CASCADE ON UPDATE CASCADE
);

/* Electricity (Electricity KWh), Water(t/h), Gas(t/h, total(t). preassure(KPa), Temperature(C) ---> table updated on consumption trigger or timing, keep records up to 48hours
	Electricity consumption is updated every 60min,
    Water consumption when water meter detecs flow and preasure but at least onces every 60min,
    Gas consumption when gas meter detecs flow and preasure but at least onces every 60min
*/
CREATE TABLE Electricity_Live_Data(
	live_data_id INT NOT NULL AUTO_INCREMENT,
    consumption FLOAT,
    fault_detected INT,
    start_time  DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
    end_time DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (live_data_id)
); 

CREATE TABLE Water_Live_Data(
	live_data_id INT NOT NULL AUTO_INCREMENT,
    consumption FLOAT,
    fault_detected INT,
    start_time  DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
    end_time DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY (live_data_id)
); 

CREATE TABLE Gas_Live_Data(
	live_data_id INT NOT NULL AUTO_INCREMENT,
    consumption FLOAT,
    fault_detected INT,
    start_time  DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
    end_time DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY (live_data_id)
); 

/* Analysis and Comparison, select date to show ---> table updated every every day at set time (00:00 default)*/
CREATE TABLE Electricity_History_Data(
	hist_data_id INT NOT NULL AUTO_INCREMENT,
    electricity_consumption FLOAT,
    date DATE,
    PRIMARY KEY (hist_data_id)
);
CREATE TABLE Water_History_Data(
	hist_data_id INT NOT NULL AUTO_INCREMENT,
    water_consumption FLOAT,
    date DATE,
    PRIMARY KEY (hist_data_id)
);
CREATE TABLE Gas_History_Data(
	hist_data_id INT NOT NULL AUTO_INCREMENT,
    gas_consumption FLOAT,
    date DATE,
    PRIMARY KEY (hist_data_id)
);

/* Alarm messages can be sent to email*/
CREATE TABLE Alarm_Data(
	alarm_id INT NOT NULL AUTO_INCREMENT,
    alarm_code INT NOT NULL,
    alarm_desc VARCHAR(255),
    time_stamp  DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (alarm_id)
);


/* Devices in system */
CREATE TABLE Devices(
	device_id INT NOT NULL AUTO_INCREMENT,
    device_name VARCHAR (100) NOT NULL,
    description VARCHAR(255),
    production_date DATE NOT NULL,
    made_in VARCHAR (50) NOT NULL,
    last_update DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    active_flag BOOL NOT NULL DEFAULT 0,
    PRIMARY KEY (device_id)
);

drop table if exists passwords;
drop table if exists users_roles;
drop table if exists user;
drop table if exists role;
drop table if exists Alarm_Data;

/* App Menu -> Home, Live Data, History Data, Print reports, Settings*/

select consumption, date_format(end_time,'%H:%i') as timestamp from electricity_live_data ORDER BY live_data_id DESC LIMIT 5;












