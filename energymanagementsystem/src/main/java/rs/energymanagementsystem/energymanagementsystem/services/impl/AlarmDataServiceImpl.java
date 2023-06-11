package rs.energymanagementsystem.energymanagementsystem.services.impl;

import org.springframework.stereotype.Service;
import rs.energymanagementsystem.energymanagementsystem.entities.AlarmData;
import rs.energymanagementsystem.energymanagementsystem.exception.ResourceNotFoundException;
import rs.energymanagementsystem.energymanagementsystem.repositories.AlarmDataRepository;
import rs.energymanagementsystem.energymanagementsystem.services.AlarmDataService;

import java.util.List;
import java.util.Optional;

@Service
public class AlarmDataServiceImpl implements AlarmDataService {

    private AlarmDataRepository alarmDataRepository;

    public AlarmDataServiceImpl(AlarmDataRepository alarmDataRepository) {
        this.alarmDataRepository = alarmDataRepository;
    }

    @Override
    public AlarmData saveAlarmData(AlarmData alarmData) {
        return alarmDataRepository.save(alarmData);
    }

    @Override
    public List<AlarmData> getAllAlarmData() {
        return  alarmDataRepository.findAll();
    }

    public AlarmData getAlarmDataById(Integer alarm_id){
//        Optional<AlarmData> alarmData = alarmDataRepository.findById(alarm_id);
//        if(alarmData.isPresent()){
//            return alarmData.get();
//        }else {
//            throw new ResourceNotFoundException("Alarm data", "alarm_id", alarm_id);
//        }
        // lambda expression simplify the above expression
        return alarmDataRepository.findById(alarm_id).orElseThrow(() ->
                new ResourceNotFoundException("Alarm data", "alarm_id", alarm_id));
    }

    @Override
    public AlarmData updateAlarmDate(AlarmData alarmData, Integer alarm_id) {
        // check if alarm_id exists in database
        AlarmData existingAlarmData = alarmDataRepository.findById(alarm_id).orElseThrow(() ->
                new ResourceNotFoundException("AlarmData", "alarm_id", alarm_id));
        existingAlarmData.setAlarm_code(alarmData.getAlarm_code());
        existingAlarmData.setAlarm_desc(alarmData.getAlarm_desc());
        existingAlarmData.setTime_stamp(alarmData.getTime_stamp());
        // save existing alarmData to DB
        alarmDataRepository.save(existingAlarmData);
        return existingAlarmData;
    }

    @Override
    public void deleteAlarmData(Integer alarm_id) {
        // check if alarm exists in database
        alarmDataRepository.findById(alarm_id).orElseThrow(() ->
                new ResourceNotFoundException("AlarmData", "alarm_id", alarm_id));
        alarmDataRepository.deleteById(alarm_id);
    }
}
