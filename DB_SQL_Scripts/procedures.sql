

SET GLOBAL time_zone = '+00:00';
    
/* ------------ Password procedure ------------*/
DROP PROCEDURE IF EXISTS addHashPassword;
DELIMITER $$
CREATE PROCEDURE addHashPassword(
	IN puserId INT,
    IN pPassword_hash VARCHAR(255)
)
BEGIN
	INSERT INTO passwords(userId,password_hash)
    VALUES (puserId,sha2(pPassword_hash,512));
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
    DECLARE pfaultDetected INT;
    DECLARE pstartTime DATETIME;
    DECLARE pendTime DATETIME;
    
    SET pstartTime = '2022-03-20 12:00:01';
    SET pendTime = '2022-03-20 13:00:00';
    SET pfaultDetected = '0';
    
	WHILE pendTime < DATE_SUB(current_timestamp, INTERVAL 2 DAY) DO
		SET pConsumption = FLOOR(ABS(RAND())*1000);		
		SET pfaultDetected = FLOOR(ABS(RAND())*10000);
        CASE pfaultDetected
			WHEN 1 THEN 
				INSERT INTO alarmData(alarmCode,alarmDesc,timeStamp)
                VALUES(pfaultDetected,'Electricity sensor failure',pstartTime);
			WHEN 2 THEN 
				INSERT INTO alarmData(alarmCode,alarmDesc,timeStamp)
                VALUES(pfaultDetected,'Low voltage',pstartTime);
			WHEN 3 THEN 
				INSERT INTO alarmData(alarmCode,alarmDesc,timeStamp)
                VALUES(pfaultDetected,'High voltage',pstartTime);
			WHEN 4 THEN 
				INSERT INTO alarmData(alarmCode,alarmDesc,timeStamp)
                VALUES(pfaultDetected,'Electricity failure.',pstartTime);
			ELSE
				SET pfaultDetected = 0;
        END CASE;
                      
        SET pstartTime = DATE_ADD(pendTime, INTERVAL 1 SECOND);
        SET pendTime = DATE_ADD(DATE_ADD(pstartTime,INTERVAL 59 SECOND), INTERVAL 59 MINUTE);
	
		INSERT INTO electricity_live_data(consumption,faultDetected,startTime,endTime)
		VALUES (pConsumption,pfaultDetected,pstartTime,pendTime);
        
        /* Check for new day to insert history data */
		IF DATE(pstartTime) < DATE(pendTime)  THEN
			SET pDailyConsumption = (SELECT SUM(consumption)
									FROM electricity_live_data
									WHERE DATE(startTime) = DATE(pstartTime));
			INSERT INTO electricity_history_data(electricityConsumption,date)
			VALUES(pDailyConsumption,DATE(pstartTime));
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
    DECLARE pfaultDetected INT;
    DECLARE pstartTime DATETIME;
    DECLARE pendTime DATETIME;
        
    SET pstartTime = '2022-03-20 12:00:01';
    SET pendTime = '2022-03-20 13:00:00';
    SET pfaultDetected = '0';
    
	WHILE pendTime < DATE_SUB(current_timestamp, INTERVAL 2 DAY) DO
		SET pfaultDetected = FLOOR(ABS(RAND())*10000);
        CASE pfaultDetected
			WHEN 11 THEN 
				INSERT INTO alarmData(alarmCode,alarmDesc,timeStamp)
                VALUES(pfaultDetected,'Water sensor failure',pstartTime);
			WHEN 12 THEN 
				INSERT INTO alarmData(alarmCode,alarmDesc,timeStamp)
                VALUES(pfaultDetected,'Water leak',pstartTime);
			WHEN 13 THEN 
				INSERT INTO alarmData(alarmCode,alarmDesc,timeStamp)
                VALUES(pfaultDetected,'Water pipe blockage',pstartTime);
			ELSE
				SET pfaultDetected = 0;
        END CASE;
		
        IF MONTH(pstartTime) > 6 AND MONTH(pstartTime) < 9 THEN
			SET pConsumption = FLOOR(ABS(RAND())*1800);
		ELSE
			SET pConsumption = FLOOR(ABS(RAND())*(1200));
		END IF;
        
        SET pstartTime = DATE_ADD(pendTime, INTERVAL 1 SECOND);
        SET pendTime = DATE_ADD(DATE_ADD(pstartTime,INTERVAL 59 SECOND), INTERVAL 59 MINUTE);
	
		INSERT INTO Water_Live_Data(consumption,faultDetected,startTime,endTime)
		VALUES (pConsumption,pfaultDetected,pstartTime,pendTime);
        
		/* Check for new day to insert history data */
		IF DATE(pstartTime) < DATE(pendTime)  THEN
			SET pDailyConsumption = (SELECT SUM(consumption)
									FROM water_live_data
									WHERE DATE(startTime) = DATE(pstartTime));
			INSERT INTO water_history_data(waterConsumption,date)
			VALUES(pDailyConsumption,DATE(pstartTime));
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
    DECLARE pfaultDetected INT;
    DECLARE pstartTime DATETIME;
    DECLARE pendTime DATETIME;
    
    SET pstartTime = '2022-03-20 12:00:01';
    SET pendTime = '2022-03-20 13:00:00';
    SET pfaultDetected = '0';
    
	WHILE pendTime < DATE_SUB(current_timestamp, INTERVAL 2 DAY) DO
    
		SET pfaultDetected = FLOOR(ABS(RAND())*10000);
        CASE pfaultDetected
			WHEN 21 THEN 
				INSERT INTO alarmData(alarmCode,alarmDesc,timeStamp)
                VALUES(pfaultDetected,'Gas Sensor failure',pstartTime);
			WHEN 22 THEN 
				INSERT INTO alarmData(alarmCode,alarmDesc,timeStamp)
                VALUES(pfaultDetected,'Flammable gas leak',pstartTime);
			WHEN 23 THEN 
				INSERT INTO alarmData(alarmCode,alarmDesc,timeStamp)
                VALUES(pfaultDetected,'Toxic gas leak.',pstartTime);
			ELSE
				SET pfaultDetected = 0;
        END CASE;
                      
        SET pstartTime = DATE_ADD(pendTime, INTERVAL 1 SECOND);
        SET pendTime = DATE_ADD(DATE_ADD(pstartTime,INTERVAL 59 SECOND), INTERVAL 59 MINUTE);
	
		IF MONTH(pstartTime) < 5 OR MONTH(pstartTime) > 9 THEN
			SET pConsumption = FLOOR(ABS(RAND())*2000);
		ELSE
			SET pConsumption = FLOOR(ABS(RAND())*300);
        END IF;
        
		INSERT INTO gas_live_data(consumption,faultDetected,startTime,endTime)
		VALUES (pConsumption,pfaultDetected,pstartTime,pendTime);
        
        /* Check for new day to insert history data */
		IF DATE(pstartTime) < DATE(pendTime)  THEN
			SET pDailyConsumption = (SELECT SUM(consumption)
									FROM gas_live_data
									WHERE DATE(startTime) = DATE(pstartTime));
			INSERT INTO gas_history_data(gasConsumption,date)
			VALUES(pDailyConsumption,DATE(pstartTime));
		ELSE
			SET pDailyConsumption = 0;
		END IF;
	END WHILE;    
END $$
DELIMITER ;




