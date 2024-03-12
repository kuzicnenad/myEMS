

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
			DECLARE pFault_detected INT; 
			DECLARE pStart_time DATETIME; 
			DECLARE pEnd_time DATETIME; 
            
			SET pFault_detected = '0';
						
			SET pFault_detected = FLOOR(ABS(RAND())*10000);
			CASE pFault_detected
				WHEN 11 THEN 
					INSERT INTO alarmData(alarmCode,alarmDesc,timeStamp)
					VALUES(pFault_detected,'Water sensor failure',updateTriger);
				WHEN 12 THEN 
					INSERT INTO alarmData(alarmCode,alarmDesc,timeStamp)
					VALUES(pFault_detected,'Water leak',updateTriger);
				WHEN 13 THEN 
					INSERT INTO alarmData(alarmCode,alarmDesc,timeStamp)
					VALUES(pFault_detected,'Water pipe blockage',updateTriger);
				ELSE
					SET pFault_detected = 0;
			END CASE;
			
			SET pStart_time = (	SELECT IF (end_time IS NULL, '2021-03-20 22:00:00', end_time)
								FROM electricity_live_data 
								ORDER BY end_time DESC
								LIMIT 1);
			SET pStart_time = DATE_ADD(pStart_time, INTERVAL 1 SECOND);
            SET pEnd_time = DATE_ADD(DATE_ADD(pStart_time,INTERVAL 59 SECOND), INTERVAL 59 MINUTE);
			SET pConsumption = FLOOR(ABS(RAND())*1000);
            
			INSERT INTO Electricity_Live_Data(consumption,fault_detected,start_time,end_time)
			VALUES (pConsumption,pFault_detected,pStart_time,pEnd_time); 
                        
            /* Check for new day to insert history data */
			IF DATE(pStart_time) < DATE(pEnd_time)  THEN 
				SET pDailyConsumption = (SELECT SUM(consumption)
										FROM Electricity_Live_Data
										WHERE DATE(start_time) = DATE(pStart_time));
                
				INSERT INTO Electricity_History_Data(electricityConsumption,date)
				VALUES(pDailyConsumption,DATE(pStart_time));
                
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
			DECLARE pFault_detected INT; 
			DECLARE pStart_time DATETIME; 
			DECLARE pEnd_time DATETIME; 
            
			SET pFault_detected = '0';			
		
			SET pFault_detected = FLOOR(ABS(RAND())*10000);
			CASE pFault_detected
				WHEN 21 THEN 
					INSERT INTO alarmData(alarmCode,alarmDesc,timeStamp)
					VALUES(pFault_detected,'Gas Sensor failure',updateTriger);
				WHEN 22 THEN 
					INSERT INTO alarmData(alarmCode,alarmDesc,timeStamp)
					VALUES(pFault_detected,'Flammable gas leak',updateTriger);
				WHEN 23 THEN 
					INSERT INTO alarmData(alarmCode,alarmDesc,timeStamp)
					VALUES(pFault_detected,'Toxic gas leak.',updateTriger);
				ELSE
					SET pFault_detected = 0;
			END CASE;
			
			SET pStart_time = (	SELECT IF (end_time IS NULL, '2021-03-20 22:00:00', end_time)
								FROM gas_live_data 
								ORDER BY end_time DESC
								LIMIT 1);
			SET pStart_time = DATE_ADD(pStart_time, INTERVAL 1 SECOND);
            SET pEnd_time = DATE_ADD(DATE_ADD(pStart_time,INTERVAL 59 SECOND), INTERVAL 59 MINUTE);
			IF MONTH(pStart_time) > 6 AND MONTH(pStart_time) < 9 THEN
				SET pConsumption = FLOOR(ABS(RAND())*1800);
			ELSE
				SET pConsumption = FLOOR(ABS(RAND())*1200);
			END IF;
                        
			INSERT INTO water_live_data(consumption,fault_detected,start_time,end_time)
			VALUES (pConsumption,pFault_detected,pStart_time,pEnd_time); 
                        
            /* Check for new day to insert history data */
			IF DATE(pStart_time) < DATE(pEnd_time)  THEN 
				SET pDailyConsumption = (SELECT SUM(consumption)
										FROM water_live_data
										WHERE DATE(start_time) = DATE(pStart_time));
                
				INSERT INTO water_history_data(waterConsumption,date)
				VALUES(pDailyConsumption,DATE(pStart_time));
                
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
			DECLARE pFault_detected INT; 
			DECLARE pStart_time DATETIME; 
			DECLARE pEnd_time DATETIME; 
            
			SET pFault_detected = '0';
			
		
			SET pFault_detected = FLOOR(ABS(RAND())*10000);
			CASE pFault_detected
				WHEN 21 THEN 
					INSERT INTO alarmData(alarmCode,alarmDesc,timeStamp)
					VALUES(pFault_detected,'Gas Sensor failure',updateTriger);
				WHEN 22 THEN 
					INSERT INTO alarmData(alarmCode,alarmDesc,timeStamp)
					VALUES(pFault_detected,'Flammable gas leak',updateTriger);
				WHEN 23 THEN 
					INSERT INTO alarmData(alarmCode,alarmDesc,timeStamp)
					VALUES(pFault_detected,'Toxic gas leak.',updateTriger);
				ELSE
					SET pFault_detected = 0;
			END CASE;
			
			SET pStart_time = (	SELECT IF (end_time IS NULL, '2021-03-20 22:00:00', end_time)
								FROM gas_live_data 
								ORDER BY end_time DESC
								LIMIT 1);
			SET pStart_time = DATE_ADD(pStart_time, INTERVAL 1 SECOND);
            SET pEnd_time = DATE_ADD(DATE_ADD(pStart_time,INTERVAL 59 SECOND), INTERVAL 59 MINUTE);
			IF MONTH(pStart_time) < 5 OR MONTH(pStart_time) > 9 THEN
				SET pConsumption = FLOOR(ABS(RAND())*2000);
			ELSE
				SET pConsumption = FLOOR(ABS(RAND())*300);
			END IF;
            
			INSERT INTO Gas_Live_Data(consumption,fault_detected,start_time,end_time)
			VALUES (pConsumption,pFault_detected,pStart_time,pEnd_time); 
                        
            /* Check for new day to insert history data */
			IF DATE(pStart_time) < DATE(pEnd_time)  THEN 
				SET pDailyConsumption = (SELECT SUM(consumption)
										FROM gas_live_data
										WHERE DATE(start_time) = DATE(pStart_time));
                
				INSERT INTO gas_history_data(gasConsumption,date)
				VALUES(pDailyConsumption,DATE(pStart_time));
                
			ELSE
				SET pDailyConsumption = 0;
			END IF;

		END $$
DELIMITER ;



        