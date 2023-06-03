DROP DATABASE IF EXISTS myEMS;
CREATE DATABASE myEMS;
USE myEMS;
	
CREATE TABLE Users (
	user_id INT NOT NULL auto_increment,
    user_login VARCHAR(50) NOT NULL,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    job_title INT,
    time_stamp  TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY CLUSTERED (user_id ASC)
);

CREATE TABLE Passwords (
	hash_algorithm_id INT NOT NULL AUTO_INCREMENT,
    user_id INT NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    time_stamp  TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (hash_algorithm_id),
    FOREIGN KEY (user_id) REFERENCES Users (user_id) ON DELETE CASCADE ON UPDATE CASCADE
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
	handshake VARCHAR(1),
    PRIMARY KEY (live_data_id)
); 

CREATE TABLE Water_Live_Data(
	live_data_id INT NOT NULL AUTO_INCREMENT,
    consumption FLOAT,
    fault_detected INT,
    start_time  DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
    end_time DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
	handshake VARCHAR(1),
	PRIMARY KEY (live_data_id)
); 

CREATE TABLE Gas_Live_Data(
	live_data_id INT NOT NULL AUTO_INCREMENT,
    consumption FLOAT,
    fault_detected INT,
    start_time  DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
    end_time DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
	handshake VARCHAR(1),
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
    alarm_desc VARCHAR(200),
    time_stamp  DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (alarm_id)
);


/* App Menu -> Home, Live Data, History Data, Print reports, Settings*/









