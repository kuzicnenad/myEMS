
/* ------------ Password procedure ------------*/
DROP PROCEDURE IF EXISTS addHashPassword
DELIMITER $$
CREATE PROCEDURE addHashPassword(
	IN pUser_id INT,
    IN pPassword_hash VARCHAR(255)
)
BEGIN
	INSERT INTO passwords(user_id,password_hash)
    VALUES (pUser_id,sha2(pPassword_hash,512));
END $$
DELIMITER ;

/* --------- Drop consumption procedures ---------*/
DROP PROCEDURE IF EXISTS generateElectricityData;
DROP PROCEDURE IF EXISTS generateWaterData;
DROP PROCEDURE IF EXISTS generateGasData;
DROP PROCEDURE IF EXISTS generateElectricityHistoryData;
DROP PROCEDURE IF EXISTS generateWaterHistoryData;
DROP PROCEDURE IF EXISTS generateGasHistoryData;

/* ------- Generate Electricity Data Sets ------- */
DELIMITER $$
CREATE PROCEDURE generateElectricityData (
	IN data_sets INT
)
BEGIN
	DECLARE pConsumption INT;
    DECLARE pDailyConsumption FLOAT;
    DECLARE pFault_detected INT; 
    DECLARE pStart_time DATETIME; 
    DECLARE pEnd_time DATETIME; 
    DECLARE pHandshake VARCHAR(1);
    DECLARE pReturnHandshake VARCHAR(1);
	DECLARE updateTriger DATETIME;
    
	SET GLOBAL time_zone = '+00:00';
	SET updateTriger = '2019-03-15 23:59:59';
    SET pFault_detected = '0';
    SET pStart_time = '2019-03-15 12:00:01';
    SET pEnd_time = '2019-03-15 13:00:00';
    
	WHILE data_sets > 0 DO
		SET pConsumption = FLOOR(ABS(RAND())*1000);
		SET pDailyConsumption = pDailyCOnsumption + pCOnsumption;
		
		SET pFault_detected = FLOOR(ABS(RAND())*10000);
        CASE pFault_detected
			WHEN 1 THEN 
				INSERT INTO Alarm_Data(alarm_code,alarm_desc,time_stamp)
                VALUES(pFault_detected,'Electricity sensor failure',updateTriger);
			WHEN 2 THEN 
				INSERT INTO Alarm_Data(alarm_code,alarm_desc,time_stamp)
                VALUES(pFault_detected,'Low voltage',updateTriger);
			WHEN 3 THEN 
				INSERT INTO Alarm_Data(alarm_code,alarm_desc,time_stamp)
                VALUES(pFault_detected,'High voltage',updateTriger);
			WHEN 4 THEN 
				INSERT INTO Alarm_Data(alarm_code,alarm_desc,time_stamp)
                VALUES(pFault_detected,'Electricity failure.',updateTriger);
			ELSE
				SET pFault_detected = 0;
        END CASE;
	          
		IF DATE(updateTriger) < DATE(pEnd_time)  THEN 
			SET pHandshake = 1;			
			SET updateTriger =  DATE(pEnd_time);            
            CALL generateElectricityHistoryData(pHandshake,pDailyConsumption,updateTriger,pReturnHandshake);
            IF pReturnHandshake = 0 THEN
				SET pDailyConsumption = 0;
                SET pHandshake = 0;
			ELSE
				SET pDailyConsumption = 0;
				SET pHandshake = 2;
			END IF;
		ELSE
			SET pHandshake = 0;
		END IF;
                      
        SET pStart_time = DATE_ADD(pEnd_time, INTERVAL 1 SECOND);
        SET pEnd_time = DATE_ADD(DATE_ADD(pStart_time,INTERVAL 59 SECOND), INTERVAL 59 MINUTE);
	
		INSERT INTO Electricity_Live_Data(consumption,fault_detected,start_time,end_time,handshake)
		VALUES (pConsumption,pFault_detected,pStart_time,pEnd_time,pHandshake);
        
		SET data_sets = data_sets - 1;
	END WHILE;    
END $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE generateElectricityHistoryData(
	IN pHandshake VARCHAR(1),
	IN pDailyConsumption FLOAT,
    IN pDate DATE,
    OUT pReturnHandshake VARCHAR(1)
)
BEGIN
	IF pHandshake = 1 THEN
		INSERT INTO Electricity_History_Data(electricity_consumption,date)
		VALUES(pDailyConsumption,pDate);
        SET pReturnHandshake = 0;
    END IF;
END $$
DELIMITER ;

/* --------- Generate Water Data Sets --------- */
DELIMITER $$
CREATE PROCEDURE generateWaterData (
	IN data_sets INT
)
BEGIN
	DECLARE pConsumption INT;
    DECLARE pDailyConsumption FLOAT;
    DECLARE pFault_detected INT; 
    DECLARE pStart_time DATETIME; 
    DECLARE pEnd_time DATETIME; 
    DECLARE pHandshake VARCHAR(1);
    DECLARE pReturnHandshake VARCHAR(1);
	DECLARE updateTriger DATETIME;
    
	SET GLOBAL time_zone = '+00:00';
	SET updateTriger = '2019-03-15 23:59:59';
    SET pFault_detected = '0';
    SET pStart_time = '2019-03-15 12:00:01';
    SET pEnd_time = '2019-03-15 13:00:00';
    
	WHILE data_sets > 0 DO
		IF MONTH(updateTriger) > 6 OR MONTH(updateTriger) < 9 THEN
			SET pConsumption = FLOOR(ABS(RAND())*(2000/3));
			SET pDailyConsumption = pDailyCOnsumption + pCOnsumption;
		ELSE
			SET pConsumption = FLOOR(ABS(RAND())*(2000/3));
			SET pDailyConsumption = pDailyCOnsumption + pCOnsumption;
		END IF;
		
		SET pFault_detected = FLOOR(ABS(RAND())*10000);
        CASE pFault_detected
			WHEN 11 THEN 
				INSERT INTO Alarm_Data(alarm_code,alarm_desc,time_stamp)
                VALUES(pFault_detected,'Water sensor failure',updateTriger);
			WHEN 12 THEN 
				INSERT INTO Alarm_Data(alarm_code,alarm_desc,time_stamp)
                VALUES(pFault_detected,'Water leak',updateTriger);
			WHEN 13 THEN 
				INSERT INTO Alarm_Data(alarm_code,alarm_desc,time_stamp)
                VALUES(pFault_detected,'Water pipe blockage',updateTriger);
			ELSE
				SET pFault_detected = 0;
        END CASE;
	          
		IF DATE(updateTriger) < DATE(pEnd_time)  THEN 
			SET pHandshake = 1;			
			SET updateTriger =  DATE(pEnd_time);            
            CALL generateWaterHistoryData(pHandshake,pDailyConsumption,updateTriger,pReturnHandshake);
            IF pReturnHandshake = 0 THEN
				SET pDailyConsumption = 0;
                SET pHandshake = 0;
			ELSE
				SET pDailyConsumption = 0;
				SET pHandshake = 2;
			END IF;
		ELSE
			SET pHandshake = 0;
		END IF;
                      
        SET pStart_time = DATE_ADD(pEnd_time, INTERVAL 1 SECOND);
        SET pEnd_time = DATE_ADD(DATE_ADD(pStart_time,INTERVAL 59 SECOND), INTERVAL 59 MINUTE);
	
		INSERT INTO Water_Live_Data(consumption,fault_detected,start_time,end_time,handshake)
		VALUES (pConsumption,pFault_detected,pStart_time,pEnd_time,pHandshake);
        
		SET data_sets = data_sets - 1;
	END WHILE;    
END $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE generateWaterHistoryData(
	IN pHandshake VARCHAR(1),
	IN pDailyConsumption FLOAT,
    IN pDate DATE,
    OUT pReturnHandshake VARCHAR(1)
)
BEGIN
	IF pHandshake = 1 THEN
		INSERT INTO Water_History_Data(water_consumption,date)
		VALUES(pDailyConsumption,pDate);
        SET pReturnHandshake = 0;
    END IF;
END $$
DELIMITER ;

/* ----------- Generate Gas Data Sets ----------- */
DELIMITER $$
CREATE PROCEDURE generateGasData (
	IN data_sets INT
)
BEGIN
	DECLARE pConsumption INT;
    DECLARE pDailyConsumption FLOAT;
    DECLARE pFault_detected INT; 
    DECLARE pStart_time DATETIME; 
    DECLARE pEnd_time DATETIME; 
    DECLARE pHandshake VARCHAR(1);
    DECLARE pReturnHandshake VARCHAR(1);
	DECLARE updateTriger DATETIME;
    
	SET GLOBAL time_zone = '+00:00';
	SET updateTriger = '2019-03-15 23:59:59';
    SET pFault_detected = '0';
    SET pStart_time = '2019-03-15 12:00:01';
    SET pEnd_time = '2019-03-15 13:00:00';
    
	WHILE data_sets > 0 DO
		IF MONTH(updateTriger) < 5 OR MONTH(updateTriger) > 9 THEN
			SET pConsumption = FLOOR(ABS(RAND())*2000);
			SET pDailyConsumption = pDailyCOnsumption + pCOnsumption;
		ELSE
			SET pConsumption = FLOOR(ABS(RAND())*300);
			SET pDailyConsumption = pDailyCOnsumption + pCOnsumption;
        END IF;
		
		SET pFault_detected = FLOOR(ABS(RAND())*10000);
        CASE pFault_detected
			WHEN 21 THEN 
				INSERT INTO Alarm_Data(alarm_code,alarm_desc,time_stamp)
                VALUES(pFault_detected,'Gas Sensor failure',updateTriger);
			WHEN 22 THEN 
				INSERT INTO Alarm_Data(alarm_code,alarm_desc,time_stamp)
                VALUES(pFault_detected,'Flammable gas leak',updateTriger);
			WHEN 23 THEN 
				INSERT INTO Alarm_Data(alarm_code,alarm_desc,time_stamp)
                VALUES(pFault_detected,'Toxic gas leak.',updateTriger);
			ELSE
				SET pFault_detected = 0;
        END CASE;
	          
		IF DATE(updateTriger) < DATE(pEnd_time)  THEN 
			SET pHandshake = 1;			
			SET updateTriger =  DATE(pEnd_time);            
            CALL generateGasHistoryData(pHandshake,pDailyConsumption,updateTriger,pReturnHandshake);
            IF pReturnHandshake = 0 THEN
				SET pDailyConsumption = 0;
                SET pHandshake = 0;
			ELSE
				SET pDailyConsumption = 0;
				SET pHandshake = 2;
			END IF;
		ELSE
			SET pHandshake = 0;
		END IF;
                      
        SET pStart_time = DATE_ADD(pEnd_time, INTERVAL 1 SECOND);
        SET pEnd_time = DATE_ADD(DATE_ADD(pStart_time,INTERVAL 59 SECOND), INTERVAL 59 MINUTE);
	
		INSERT INTO Gas_Live_Data(consumption,fault_detected,start_time,end_time,handshake)
		VALUES (pConsumption,pFault_detected,pStart_time,pEnd_time,pHandshake);
        
		SET data_sets = data_sets - 1;
	END WHILE;    
END $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE generateGasHistoryData(
	IN pHandshake VARCHAR(1),
	IN pDailyConsumption FLOAT,
    IN pDate DATE,
    OUT pReturnHandshake VARCHAR(1)
)
BEGIN
	IF pHandshake = 1 THEN
		INSERT INTO Gas_History_Data(gas_consumption, date)
		VALUES(pDailyConsumption,pDate);
        SET pReturnHandshake = 0;
    END IF;
END $$
DELIMITER ;