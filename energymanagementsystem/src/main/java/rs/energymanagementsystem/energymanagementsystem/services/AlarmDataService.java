package rs.energymanagementsystem.energymanagementsystem.services;

import rs.energymanagementsystem.energymanagementsystem.entities.AlarmData;
import rs.energymanagementsystem.energymanagementsystem.entities.WaterLiveData;

import java.util.List;

public interface AlarmDataService {

    /** ---------------------------------------------------------------------------------------
     * - Get all alarms data records. valuable for GET APIs
     * - Get alarm data record by ID, valuable for CRUD APIs
     --------------------------------------------------------------------------------------- **/
    AlarmData saveAlarmData(AlarmData alarmData);
    List<AlarmData> getAllAlarmData();
    AlarmData getAlarmDataById(Integer id);
    AlarmData updateAlarmDate(AlarmData alarmData, Integer alarm_id);
    void deleteAlarmData(Integer alarm_id);

    /** ---------------------------------------------------------------------------------------
     * - Get last 10 records (for index page)
     --------------------------------------------------------------------------------------- **/
    List<WaterLiveData> getLatestDate();
}
