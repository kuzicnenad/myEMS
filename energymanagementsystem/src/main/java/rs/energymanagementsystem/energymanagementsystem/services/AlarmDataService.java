package rs.energymanagementsystem.energymanagementsystem.services;

import rs.energymanagementsystem.energymanagementsystem.entities.AlarmData;

import java.util.List;

public interface AlarmDataService {

    /** ---------------------------------------------------------------------------------------
     * Get all alarm data records and get alarm data record by ID.
     * Basic CRUD functionality, used for API testing
     --------------------------------------------------------------------------------------- **/
    AlarmData saveAlarmData(AlarmData alarmData);
    List<AlarmData> getAllAlarmData();
    AlarmData getAlarmDataById(Integer id);
    AlarmData updateAlarmDate(AlarmData alarmData, Integer alarm_id);
    void deleteAlarmData(Integer alarm_id);

    /** ---------------------------------------------------------------------------------------
     * Get last 10 records (for index page)
     * Acknowledge alarm flag, 0 -> Not Acknowledged, 1 -> Acknowledged
     --------------------------------------------------------------------------------------- **/
    List<AlarmData> getLatestDate();

    void alarmAckFlag(Integer alarm_id);

}
