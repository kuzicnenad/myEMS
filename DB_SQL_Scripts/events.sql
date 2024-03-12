

/* 
Events, simulating new live data sets while 
application is running for a period of time 

Event is running for 15min (exam duration)
Adding new data every 20sec.
*/

/* ------------------ Active events ------------------ */
SHOW EVENTS FROM myEMS;

/* ------------------ Drop events ------------------ */
DROP EVENT IF EXISTS insert_live_data_electricity;
DROP EVENT IF EXISTS insert_live_data_gas;
DROP EVENT IF EXISTS insert_live_data_water;
           
/* ------- Simulate new Electricity Data Sets ------- */
SET GLOBAL time_zone = '+00:00';
SET GLOBAL event_scheduler = ON;

DELIMITER $$
CREATE EVENT insert_live_data_electricity
	ON SCHEDULE EVERY 20 SECOND
	STARTS current_timestamp
    ENDS current_timestamp + INTERVAL 15 MINUTE
    DO
		BEGIN
			DECLARE pConsumption INT;
			DECLARE pDailyConsumption FLOAT;
			DECLARE pfaultDetected INT;
			DECLARE pstartTime DATETIME;
			DECLARE pendTime DATETIME;
            
			SET pfaultDetected = '0';
						
			SET pfaultDetected = FLOOR(ABS(RAND())*10000);
			CASE pfaultDetected
				WHEN 11 THEN 
					INSERT INTO alarmData(alarmCode,alarmDesc,timeStamp)
					VALUES(pfaultDetected,'Water sensor failure',updateTriger);
				WHEN 12 THEN 
					INSERT INTO alarmData(alarmCode,alarmDesc,timeStamp)
					VALUES(pfaultDetected,'Water leak',updateTriger);
				WHEN 13 THEN 
					INSERT INTO alarmData(alarmCode,alarmDesc,timeStamp)
					VALUES(pfaultDetected,'Water pipe blockage',updateTriger);
				ELSE
					SET pfaultDetected = 0;
			END CASE;
			
			SET pstartTime = (	SELECT IF (endTime IS NULL, '2021-03-20 22:00:00', endTime)
								FROM electricityLiveData
								ORDER BY endTime DESC
								LIMIT 1);
			SET pstartTime = DATE_ADD(pstartTime, INTERVAL 1 SECOND);
            SET pendTime = DATE_ADD(DATE_ADD(pstartTime,INTERVAL 59 SECOND), INTERVAL 59 MINUTE);
			SET pConsumption = FLOOR(ABS(RAND())*1000);
            
			INSERT INTO electricityLiveData(consumption,faultDetected,startTime,endTime)
			VALUES (pConsumption,pfaultDetected,pstartTime,pendTime);
                        
            /* Check for new day to insert history data */
			IF DATE(pstartTime) < DATE(pendTime)  THEN
				SET pDailyConsumption = (SELECT SUM(consumption)
										FROM electricityLiveData
										WHERE DATE(startTime) = DATE(pstartTime));
                
				INSERT INTO electricityHistoryData(electricityConsumption,date)
				VALUES(pDailyConsumption,DATE(pstartTime));
                
			ELSE
				SET pDailyConsumption = 0;
			END IF;

		END $$
DELIMITER ;

DELIMITER $$
CREATE EVENT insert_live_data_water
	ON SCHEDULE EVERY 20 SECOND
	STARTS current_timestamp
    ENDS current_timestamp + INTERVAL 15 MINUTE
    DO
		BEGIN
			DECLARE pConsumption INT;
			DECLARE pDailyConsumption FLOAT;
			DECLARE pfaultDetected INT;
			DECLARE pstartTime DATETIME;
			DECLARE pendTime DATETIME;
            
			SET pfaultDetected = '0';
		
			SET pfaultDetected = FLOOR(ABS(RAND())*10000);
			CASE pfaultDetected
				WHEN 21 THEN 
					INSERT INTO alarmData(alarmCode,alarmDesc,timeStamp)
					VALUES(pfaultDetected,'Gas Sensor failure',updateTriger);
				WHEN 22 THEN 
					INSERT INTO alarmData(alarmCode,alarmDesc,timeStamp)
					VALUES(pfaultDetected,'Flammable gas leak',updateTriger);
				WHEN 23 THEN 
					INSERT INTO alarmData(alarmCode,alarmDesc,timeStamp)
					VALUES(pfaultDetected,'Toxic gas leak.',updateTriger);
				ELSE
					SET pfaultDetected = 0;
			END CASE;
			
			SET pstartTime = (	SELECT IF (endTime IS NULL, '2021-03-20 22:00:00', endTime)
								FROM gasLiveData
								ORDER BY endTime DESC
								LIMIT 1);
			SET pstartTime = DATE_ADD(pstartTime, INTERVAL 1 SECOND);
            SET pendTime = DATE_ADD(DATE_ADD(pstartTime,INTERVAL 59 SECOND), INTERVAL 59 MINUTE);
			IF MONTH(pstartTime) > 6 AND MONTH(pstartTime) < 9 THEN
				SET pConsumption = FLOOR(ABS(RAND())*1800);
			ELSE
				SET pConsumption = FLOOR(ABS(RAND())*1200);
			END IF;
                        
			INSERT INTO waterLiveData(consumption,faultDetected,startTime,endTime)
			VALUES (pConsumption,pfaultDetected,pstartTime,pendTime);
                        
            /* Check for new day to insert history data */
			IF DATE(pstartTime) < DATE(pendTime)  THEN
				SET pDailyConsumption = (SELECT SUM(consumption)
										FROM waterLiveData
										WHERE DATE(startTime) = DATE(pstartTime));
                
				INSERT INTO waterHistoryData(waterConsumption,date)
				VALUES(pDailyConsumption,DATE(pstartTime));
                
			ELSE
				SET pDailyConsumption = 0;
			END IF;

		END $$
DELIMITER ;

DELIMITER $$
CREATE EVENT insert_live_data_gas
	ON SCHEDULE EVERY 20 SECOND
	STARTS current_timestamp
    ENDS current_timestamp + INTERVAL 15 MINUTE
    DO
		BEGIN
			DECLARE pConsumption INT;
			DECLARE pDailyConsumption FLOAT;
			DECLARE pfaultDetected INT;
			DECLARE pstartTime DATETIME;
			DECLARE pendTime DATETIME;
            
			SET pfaultDetected = '0';
			
		
			SET pfaultDetected = FLOOR(ABS(RAND())*10000);
			CASE pfaultDetected
				WHEN 21 THEN 
					INSERT INTO alarmData(alarmCode,alarmDesc,timeStamp)
					VALUES(pfaultDetected,'Gas Sensor failure',updateTriger);
				WHEN 22 THEN 
					INSERT INTO alarmData(alarmCode,alarmDesc,timeStamp)
					VALUES(pfaultDetected,'Flammable gas leak',updateTriger);
				WHEN 23 THEN 
					INSERT INTO alarmData(alarmCode,alarmDesc,timeStamp)
					VALUES(pfaultDetected,'Toxic gas leak.',updateTriger);
				ELSE
					SET pfaultDetected = 0;
			END CASE;
			
			SET pstartTime = (	SELECT IF (endTime IS NULL, '2021-03-20 22:00:00', endTime)
								FROM gasLiveData
								ORDER BY endTime DESC
								LIMIT 1);
			SET pstartTime = DATE_ADD(pstartTime, INTERVAL 1 SECOND);
            SET pendTime = DATE_ADD(DATE_ADD(pstartTime,INTERVAL 59 SECOND), INTERVAL 59 MINUTE);
			IF MONTH(pstartTime) < 5 OR MONTH(pstartTime) > 9 THEN
				SET pConsumption = FLOOR(ABS(RAND())*2000);
			ELSE
				SET pConsumption = FLOOR(ABS(RAND())*300);
			END IF;
            
			INSERT INTO gasLiveData(consumption,faultDetected,startTime,endTime)
			VALUES (pConsumption,pfaultDetected,pstartTime,pendTime);
                        
            /* Check for new day to insert history data */
			IF DATE(pstartTime) < DATE(pendTime)  THEN
				SET pDailyConsumption = (SELECT SUM(consumption)
										FROM gasLiveData
										WHERE DATE(startTime) = DATE(pstartTime));
                
				INSERT INTO gasHistoryData(gasConsumption,date)
				VALUES(pDailyConsumption,DATE(pstartTime));
                
			ELSE
				SET pDailyConsumption = 0;
			END IF;

		END $$
DELIMITER ;



        