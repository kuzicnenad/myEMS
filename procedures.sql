

SET GLOBAL time_zone = '+00:00';
    
/* ------------ Password procedure ------------*/
DROP PROCEDURE IF EXISTS addHashPassword;
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
CREATE PROCEDURE generateElectricityData ()
BEGIN
	DECLARE pConsumption INT;
    DECLARE pDailyConsumption FLOAT;
    DECLARE pFault_detected INT; 
    DECLARE pStart_time DATETIME; 
    DECLARE pEnd_time DATETIME; 
    
    SET pStart_time = '2021-03-20 12:00:01';
    SET pEnd_time = '2021-03-20 13:00:00';
    SET pFault_detected = '0';
    
	WHILE pEnd_time < DATE_SUB(DATE(current_timestamp), INTERVAL 2 DAY) DO
		SET pConsumption = FLOOR(ABS(RAND())*1000);		
		SET pFault_detected = FLOOR(ABS(RAND())*10000);
        CASE pFault_detected
			WHEN 1 THEN 
				INSERT INTO Alarm_Data(alarm_code,alarm_desc,time_stamp)
                VALUES(pFault_detected,'Electricity sensor failure',pStart_time);
			WHEN 2 THEN 
				INSERT INTO Alarm_Data(alarm_code,alarm_desc,time_stamp)
                VALUES(pFault_detected,'Low voltage',pStart_time);
			WHEN 3 THEN 
				INSERT INTO Alarm_Data(alarm_code,alarm_desc,time_stamp)
                VALUES(pFault_detected,'High voltage',pStart_time);
			WHEN 4 THEN 
				INSERT INTO Alarm_Data(alarm_code,alarm_desc,time_stamp)
                VALUES(pFault_detected,'Electricity failure.',pStart_time);
			ELSE
				SET pFault_detected = 0;
        END CASE;
                      
        SET pStart_time = DATE_ADD(pEnd_time, INTERVAL 1 SECOND);
        SET pEnd_time = DATE_ADD(DATE_ADD(pStart_time,INTERVAL 59 SECOND), INTERVAL 59 MINUTE);
	
		INSERT INTO electricity_live_data(consumption,fault_detected,start_time,end_time)
		VALUES (pConsumption,pFault_detected,pStart_time,pEnd_time);
        
        /* Check for new day to insert history data */
		IF DATE(pStart_time) < DATE(pEnd_time)  THEN 
			SET pDailyConsumption = (SELECT SUM(consumption)
									FROM electricity_live_data
									WHERE DATE(start_time) = DATE(pStart_time));
			INSERT INTO electricity_history_data(electricity_consumption,date)
			VALUES(pDailyConsumption,DATE(pStart_time));
		ELSE
			SET pDailyConsumption = 0;
		END IF;

	END WHILE;    
END $$
DELIMITER ;

/* --------- Generate Water Data Sets --------- */
DELIMITER $$
CREATE PROCEDURE generateWaterData ()
BEGIN
	DECLARE pConsumption INT;
    DECLARE pDailyConsumption FLOAT;
    DECLARE pFault_detected INT; 
    DECLARE pStart_time DATETIME; 
    DECLARE pEnd_time DATETIME; 
        
    SET pStart_time = '2021-03-20 12:00:01';
    SET pEnd_time = '2021-03-20 13:00:00';
    SET pFault_detected = '0';
    
	WHILE pEnd_time < DATE_SUB(DATE(current_timestamp), INTERVAL 2 DAY) DO				
		SET pFault_detected = FLOOR(ABS(RAND())*10000);        
        CASE pFault_detected
			WHEN 11 THEN 
				INSERT INTO Alarm_Data(alarm_code,alarm_desc,time_stamp)
                VALUES(pFault_detected,'Water sensor failure',pStart_time);
			WHEN 12 THEN 
				INSERT INTO Alarm_Data(alarm_code,alarm_desc,time_stamp)
                VALUES(pFault_detected,'Water leak',pStart_time);
			WHEN 13 THEN 
				INSERT INTO Alarm_Data(alarm_code,alarm_desc,time_stamp)
                VALUES(pFault_detected,'Water pipe blockage',pStart_time);
			ELSE
				SET pFault_detected = 0;
        END CASE;
		
        IF MONTH(pStart_time) > 6 AND MONTH(pStart_time) < 9 THEN
			SET pConsumption = FLOOR(ABS(RAND())*1800);
		ELSE
			SET pConsumption = FLOOR(ABS(RAND())*(1200));
		END IF;
        
        SET pStart_time = DATE_ADD(pEnd_time, INTERVAL 1 SECOND);
        SET pEnd_time = DATE_ADD(DATE_ADD(pStart_time,INTERVAL 59 SECOND), INTERVAL 59 MINUTE);
	
		INSERT INTO Water_Live_Data(consumption,fault_detected,start_time,end_time)
		VALUES (pConsumption,pFault_detected,pStart_time,pEnd_time);
        
		/* Check for new day to insert history data */
		IF DATE(pStart_time) < DATE(pEnd_time)  THEN 
			SET pDailyConsumption = (SELECT SUM(consumption)
									FROM water_live_data
									WHERE DATE(start_time) = DATE(pStart_time));
			INSERT INTO water_history_data(water_consumption,date)
			VALUES(pDailyConsumption,DATE(pStart_time));
		ELSE
			SET pDailyConsumption = 0;
		END IF;
        
	END WHILE;    
END $$
DELIMITER ;

/* ----------- Generate Gas Data Sets ----------- */
DELIMITER $$
CREATE PROCEDURE generateGasData ()
BEGIN
	DECLARE pConsumption INT;
    DECLARE pDailyConsumption FLOAT;
    DECLARE pFault_detected INT; 
    DECLARE pStart_time DATETIME; 
    DECLARE pEnd_time DATETIME;
    
    SET pStart_time = '2021-03-20 12:00:01';
    SET pEnd_time = '2021-03-20 13:00:00';
    SET pFault_detected = '0';
    
	WHILE pEnd_time < DATE_SUB(DATE(current_timestamp), INTERVAL 2 DAY) DO
    
		SET pFault_detected = FLOOR(ABS(RAND())*10000);
        CASE pFault_detected
			WHEN 21 THEN 
				INSERT INTO Alarm_Data(alarm_code,alarm_desc,time_stamp)
                VALUES(pFault_detected,'Gas Sensor failure',pStart_time);
			WHEN 22 THEN 
				INSERT INTO Alarm_Data(alarm_code,alarm_desc,time_stamp)
                VALUES(pFault_detected,'Flammable gas leak',pStart_time);
			WHEN 23 THEN 
				INSERT INTO Alarm_Data(alarm_code,alarm_desc,time_stamp)
                VALUES(pFault_detected,'Toxic gas leak.',pStart_time);
			ELSE
				SET pFault_detected = 0;
        END CASE;
                      
        SET pStart_time = DATE_ADD(pEnd_time, INTERVAL 1 SECOND);
        SET pEnd_time = DATE_ADD(DATE_ADD(pStart_time,INTERVAL 59 SECOND), INTERVAL 59 MINUTE);
	
		IF MONTH(pStart_time) < 5 OR MONTH(pStart_time) > 9 THEN
			SET pConsumption = FLOOR(ABS(RAND())*2000);
		ELSE
			SET pConsumption = FLOOR(ABS(RAND())*300);
        END IF;
        
		INSERT INTO gas_live_data(consumption,fault_detected,start_time,end_time)
		VALUES (pConsumption,pFault_detected,pStart_time,pEnd_time);
        
        /* Check for new day to insert history data */
		IF DATE(pStart_time) < DATE(pEnd_time)  THEN 
			SET pDailyConsumption = (SELECT SUM(consumption)
									FROM gas_live_data
									WHERE DATE(start_time) = DATE(pStart_time));
			INSERT INTO gas_history_data(gas_consumption,date)
			VALUES(pDailyConsumption,DATE(pStart_time));
		ELSE
			SET pDailyConsumption = 0;
		END IF;
	END WHILE;    
END $$
DELIMITER ;




