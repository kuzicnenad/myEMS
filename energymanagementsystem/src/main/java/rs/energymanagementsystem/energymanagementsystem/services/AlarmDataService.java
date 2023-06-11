package rs.energymanagementsystem.energymanagementsystem.services;

import rs.energymanagementsystem.energymanagementsystem.entities.AlarmData;

import java.util.List;

public interface AlarmDataService {
    AlarmData saveAlarmData(AlarmData alarmData);
    List<AlarmData> getAllAlarmData();

    AlarmData getAlarmDataById(Integer id);
    AlarmData updateAlarmDate(AlarmData alarmData, Integer alarm_id);
    void deleteAlarmData(Integer alarm_id);
}
